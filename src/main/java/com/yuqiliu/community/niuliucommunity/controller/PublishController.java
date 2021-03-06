package com.yuqiliu.community.niuliucommunity.controller;

import com.yuqiliu.community.niuliucommunity.dto.QuestionDTO;
import com.yuqiliu.community.niuliucommunity.mapper.QuestionMapper;
import com.yuqiliu.community.niuliucommunity.model.Question;
import com.yuqiliu.community.niuliucommunity.model.User;
import com.yuqiliu.community.niuliucommunity.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * @author yuqiliu
 * @create 2020-03-25  19:37
 */
@Controller
public class PublishController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/publish")
    public String publish()
    {
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(
            @RequestParam(value = "title",required = false) String title,
            @RequestParam(value = "tag",required = false) String tag,
            @RequestParam(value = "description",required = false) String description,
            @RequestParam(value = "id",required = false) String id,
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
        User user= (User) request.getSession().getAttribute("user");

        if (user == null)
        {
            model.addAttribute("error","用户未登录");
            return "publish";
        }
        question.setDescription(description);
        question.setCreator(user.getId());
        question.setTitle(title);
        question.setTag(tag);
        if (id!=null && id!="")
        {
            question.setId(Integer.parseInt(id));
        }


        questionService.createOrUpdate(question);


        return "redirect:/";
    }


    @GetMapping("/publish/{id}")
    public String edit(@PathVariable("id") Integer id,Model model)
    {
        QuestionDTO question = questionService.getById(id);
        model.addAttribute("title",question.getTitle());
        model.addAttribute("description",question.getDescription());
        model.addAttribute("tag",question.getTag());
        model.addAttribute("id",question.getId());
        return "publish";
    }
}
