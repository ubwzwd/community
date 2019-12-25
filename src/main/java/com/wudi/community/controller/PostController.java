package com.wudi.community.controller;

import com.wudi.community.dto.PostDTO;
import com.wudi.community.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping("/post/{id}")
    public String post(@PathVariable("id") Integer id,
                       Model model){
        PostDTO postDTO = postService.getById(id);
        model.addAttribute(postDTO);
        return "post";
    }
}
