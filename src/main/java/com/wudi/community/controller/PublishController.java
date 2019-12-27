package com.wudi.community.controller;

import com.wudi.community.dto.PostDTO;
import com.wudi.community.model.Post;
import com.wudi.community.model.User;
import com.wudi.community.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {

    @Autowired
    private PostService questionService;

    @GetMapping("/publish/{id}")
    public String edit(@PathVariable(name = "id") Integer postId,
                       Model model){
        // get the content of the postDTO
        PostDTO postDTO = questionService.getById(postId);
        model.addAttribute("title", postDTO.getTitle());
        model.addAttribute("description", postDTO.getDescription());
        model.addAttribute("tag", postDTO.getTag());
        model.addAttribute("id", postDTO.getId());
        return "publish";
    }

    @GetMapping("/publish")
    public String publish() {
        System.out.println("11\n");
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam(value = "tag", required = false) String tag,
            @RequestParam(value = "id", required = false) Integer id,
            HttpServletRequest request,
            Model model) {

        model.addAttribute("title", title);
        model.addAttribute("description", description);
        model.addAttribute("tag", tag);

        if (title == null || title.equals("")) {
            model.addAttribute("error", "The title cannot be empty");
            return "publish";
        }

        if (description == null || description.equals("")) {
            model.addAttribute("error", "The description cannot be empty");
            return "publish";
        }

        if (tag == null || tag.equals("")) {
            model.addAttribute("error", "The tag cannot be empty");
            return "publish";
        }

        User user = (User)request.getSession().getAttribute("user");

        if (user == null) {
            model.addAttribute("error", "not logged in");
            return "publish";
        }

        Post post = new Post();
        post.setTitle(title);
        post.setDescription(description);
        post.setTag(tag);
        post.setUserId(user.getId());
        post.setId(id);
        questionService.createOrUpdate(post);

        return "redirect:/";
    }
}
