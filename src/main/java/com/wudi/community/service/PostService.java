package com.wudi.community.service;

import com.wudi.community.dto.PostDTO;
import com.wudi.community.mapper.PostMapper;
import com.wudi.community.mapper.UserMapper;
import com.wudi.community.model.Post;
import com.wudi.community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {
    // this middleware combine the user and post table in database
    @Autowired(required = false)
    private PostMapper postMapper;

    @Autowired(required = false)
    private UserMapper userMapper;

    public List<PostDTO> list() {
        List<Post> posts = postMapper.list();
        List<PostDTO> postDTOS = new ArrayList<>();
        for(Post post : posts){
            User user = userMapper.findById(post.getUserId());
            PostDTO postDTO = new PostDTO();
            BeanUtils.copyProperties(post, postDTO);
            postDTO.setUser(user);
            postDTOS.add(postDTO);
        }
        return postDTOS;
    }


}
