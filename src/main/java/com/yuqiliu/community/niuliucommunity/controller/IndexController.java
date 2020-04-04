package com.yuqiliu.community.niuliucommunity.controller;

import com.yuqiliu.community.niuliucommunity.dto.QuestionDTO;
import com.yuqiliu.community.niuliucommunity.mapper.QuestionMapper;
import com.yuqiliu.community.niuliucommunity.mapper.UserMapper;
import com.yuqiliu.community.niuliucommunity.model.Question;
import com.yuqiliu.community.niuliucommunity.model.User;
import com.yuqiliu.community.niuliucommunity.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yuqiliu
 * @create 2020-03-19  11:58
 */


@Controller
public class IndexController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private QuestionService questionService;

    @RequestMapping("/")
    public String index(HttpServletRequest request, Model model){
        Cookie[] cookies = request.getCookies();
        if (cookies!=null&&cookies.length!=0)
        {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token"))
                {
                    String token = cookie.getValue();
                    User user=userMapper.findByToken(token);
                    if(user != null)
                    {
                        request.getSession().setAttribute("user",user);
                    }
                    break;
                }
            }
        }

        List<QuestionDTO> questionList = questionService.list();
        model.addAttribute("questions",questionList);

        return "index";
    }
}
