package com.yuqiliu.community.niuliucommunity.service;

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

    public List<QuestionDTO> list() {

        List<Question> questions = questionMapper.list();
        List<QuestionDTO> questionDTOList =new ArrayList<QuestionDTO>();
        for (Question question : questions) {
            User user = userMapper.fingById(question.getCreator());
            QuestionDTO questionDTO=new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        return questionDTOList;
    }
}
