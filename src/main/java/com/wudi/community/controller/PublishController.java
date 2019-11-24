package com.wudi.community.controller;

import com.wudi.community.mapper.PostMapper;
import com.wudi.community.mapper.UserMapper;
import com.wudi.community.model.Post;
import com.wudi.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {

    @Autowired(required = false)
    private PostMapper postMapper;

    @Autowired(required = false)
    private UserMapper userMapper;

    @GetMapping("/publish")
    public String publish(){
        System.out.println("11\n");
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam(value = "tag", required = false) String tag,
            HttpServletRequest request,
            Model model){

        model.addAttribute("title", title);
        model.addAttribute("description", description);
        model.addAttribute("tag", tag);

        if(title==null || title==""){
            model.addAttribute("error","The title cannot be empty");
            return "publish";
        }

        if(description==null || description==""){
            model.addAttribute("error","The description cannot be empty");
            return "publish";
        }

        if(tag==null || tag==""){
            model.addAttribute("error","The tag cannot be empty");
            return "publish";
        }

        User user = null;
        Cookie[] cookies = request.getCookies();
        for(Cookie cookie : cookies){
            if(cookie.getName().equals("token")){
                String token = cookie.getValue();
                user = userMapper.findByToken(token);
                if(user != null){
                    request.getSession().setAttribute("user", user);
                }
                break;
            }
        }

        if(user == null){
            model.addAttribute("error", "not logged in");
            return "publish";
        }

        Post post =new Post();
        post.setTitle(title);
        post.setDescription(description);
        post.setTag(tag);
        post.setAuthorId(user.getId());
        post.setGmtCreate(System.currentTimeMillis());
        post.setGmtModified(post.getGmtCreate());
        postMapper.create(post);

        System.out.println(title);

        return "redirect:/";
    }
}
