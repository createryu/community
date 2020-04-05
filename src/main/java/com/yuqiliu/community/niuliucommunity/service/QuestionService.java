package com.yuqiliu.community.niuliucommunity.service;

import com.yuqiliu.community.niuliucommunity.dto.PaginationDTO;
import com.yuqiliu.community.niuliucommunity.dto.QuestionDTO;
import com.yuqiliu.community.niuliucommunity.mapper.QuestionMapper;
import com.yuqiliu.community.niuliucommunity.mapper.UserMapper;
import com.yuqiliu.community.niuliucommunity.model.Question;
import com.yuqiliu.community.niuliucommunity.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yuqiliu
 * @create 2020-03-26  21:51
 */

@Service
public class QuestionService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionMapper questionMapper;

    public PaginationDTO list(Integer page,Integer size) {

        PaginationDTO paginationDTO = new PaginationDTO();
        Integer totalCount = questionMapper.count();
        paginationDTO.setPagination(totalCount,page,size);

        if (page<1)
        {
            page=1;
        }
        if (page>paginationDTO.getTotalPage())
        {
            page=paginationDTO.getTotalPage();
        }


        Integer offset = size * (page-1);

        List<Question> questions = questionMapper.list(offset,size);
        List<QuestionDTO> questionDTOList =new ArrayList<QuestionDTO>();



        for (Question question : questions) {
            User user = userMapper.fingById(question.getCreator());
            QuestionDTO questionDTO=new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }

        paginationDTO.setQuestions(questionDTOList);



        return paginationDTO;
    }
}
