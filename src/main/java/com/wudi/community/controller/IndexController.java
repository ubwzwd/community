package com.wudi.community.controller;

import com.wudi.community.dto.PaginationDTO;
import com.wudi.community.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {


    @Autowired
    private PostService postService;

    @GetMapping("/")
    public String index(
                        Model model,
                        @RequestParam(name="page", defaultValue = "1") Integer page,
                        @RequestParam(name="size", defaultValue = "2") Integer size) {
        PaginationDTO pagination = postService.list(page, size);
        model.addAttribute("pagination", pagination);
        return "index";
    }
}
