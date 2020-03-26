package com.yuqiliu.community.niuliucommunity.controller;

import com.yuqiliu.community.niuliucommunity.mapper.QuestionMapper;
import com.yuqiliu.community.niuliucommunity.mapper.UserMapper;
import com.yuqiliu.community.niuliucommunity.model.Question;
import com.yuqiliu.community.niuliucommunity.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @author yuqiliu
 * @create 2020-03-25  19:37
 */
@Controller
public class PublishController {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private QuestionMapper questionMapper;

    @GetMapping("/publish")
    public String publish()
    {
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(
            @RequestParam("title") String title,
            @RequestParam("tag") String tag,
            @RequestParam("description") String description,
            Model model,
            HttpServletRequest request)
    {
        model.addAttribute("title",title);
        model.addAttribute("description",description);
        model.addAttribute("tag",tag);

        if (title==null || title=="")
        {
            model.addAttribute("error","标题不能为空！");
            return "publish";
        }
        if (description==null || description=="")
        {
            model.addAttribute("error","问题补充不能为空！");
            return "publish";
        }
        if (tag==null || tag=="")
        {
            model.addAttribute("error","标签不能为空！");
            return "publish";
        }

        Question question=new Question();
        User user=null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length != 0)
        {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token"))
                {
                    String token = cookie.getValue();
                    user=userMapper.findByToken(token);
                    if(user != null)
                    {
                        request.getSession().setAttribute("user",user);
                    }
                    break;
                }
            }
        }
        if (user == null)
        {
            model.addAttribute("error","用户未登录");
            return "publish";
        }
        question.setDescription(description);
        question.setCreator(user.getId());
        question.setGmt_create(System.currentTimeMillis());
        question.setGmt_modified(question.getGmt_create());
        question.setTitle(title);
        question.setTag(tag);
        questionMapper.create(question);

        return "redirect:/";
    }
}
