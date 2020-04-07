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
        Integer totalCount = questionMapper.count();   //0
        Integer totalPage;
        if ((totalCount%size)==0)
        {
            totalPage=totalCount/size;  //0
        }
        else
        {
            totalPage=(totalCount/size)+1;
        }



        if (page<1)
        {
            page=1;       //page=1;
        }
        if (page>totalPage)
        {
            page=totalPage;  //这边page又变成0了
        }

        paginationDTO.setPagination(totalPage,page);//0 ，0
        Integer offset = page < 1 ? 0 : size * (page - 1);
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

    public PaginationDTO list(Integer userId, Integer page, Integer size) {
        PaginationDTO paginationDTO = new PaginationDTO();
        Integer totalCount = questionMapper.countByUserId(userId);
        Integer totalPage;
        if ((totalCount%size)==0)
        {
            totalPage=totalCount/size;
        }
        else
        {
            totalPage=(totalCount/size)+1;
        }



        if (page<1)
        {
            page=1;
        }
        if (page>totalPage)
        {
            page=totalPage;
        }

        paginationDTO.setPagination(totalPage,page);


        Integer offset = size * (page-1);

        List<Question> questions = questionMapper.listByUserId(userId,offset,size);
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

    public QuestionDTO getById(Integer id) {

        Question question= questionMapper.getById(id);
        QuestionDTO questionDTO=new QuestionDTO();
        BeanUtils.copyProperties(question,questionDTO);
        User user = userMapper.fingById(question.getCreator());
        questionDTO.setUser(user);
        return questionDTO;
    }

    public void createOrUpdate(Question question) {
        if (question.getId()==null)
        {
            //创建
            question.setGmt_create(System.currentTimeMillis());
            question.setGmt_modified(question.getGmt_create());
            questionMapper.create(question);
        }
        else
        {
            //更新
            question.setGmt_modified(System.currentTimeMillis());
            questionMapper.update(question);
        }
    }
}
