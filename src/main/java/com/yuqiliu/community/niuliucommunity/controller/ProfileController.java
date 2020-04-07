package com.yuqiliu.community.niuliucommunity.controller;

import com.yuqiliu.community.niuliucommunity.dto.PaginationDTO;
import com.yuqiliu.community.niuliucommunity.model.User;
import com.yuqiliu.community.niuliucommunity.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * @author yuqiliu
 * @create 2020-04-05  20:15
 */

@Controller
public class ProfileController
{

    @Autowired
    private QuestionService questionService;

    @GetMapping("/profile/{action}")
    public String profile(@PathVariable(name = "action") String action,
                          Model model, HttpServletRequest request,
                          @RequestParam(name = "page",defaultValue = "1") Integer page,
                          @RequestParam(name = "size",defaultValue = "5") Integer size)
    {

        User user = (User)request.getSession().getAttribute("user");
        if (user == null)
        {
            return "redirect:/";
        }

        if ("questions".equals(action))
        {
            model.addAttribute("section","questions");
            model.addAttribute("sectionName","我的提问");

        }
        else if ("replies".equals(action))
        {
            model.addAttribute("section","replies");
            model.addAttribute("sectionName","最新回复");
        }

        PaginationDTO paginationDTO = questionService.list(user.getId(), page, size);
        model.addAttribute("pagination",paginationDTO);
        return "profile";
    }
}
