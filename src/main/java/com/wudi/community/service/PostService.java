package com.wudi.community.service;

import com.wudi.community.dto.PaginationDTO;
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

    public PaginationDTO list(Integer page, Integer size) {

        // the begin index- of each page
        Integer offset = size*(page-1);

        List<Post> posts = postMapper.list(offset, size);
        List<PostDTO> postDTOS = new ArrayList<>();

        PaginationDTO paginationDTO = new PaginationDTO();
        for(Post post : posts){
            User user = userMapper.findById(post.getUserId());
            PostDTO postDTO = new PostDTO();
            BeanUtils.copyProperties(post, postDTO);
            postDTO.setUser(user);
            postDTOS.add(postDTO);
        }

        paginationDTO.setPosts(postDTOS);
        // totalCount is the total number of posts
        Integer totalCount = postMapper.count();
        paginationDTO.setPagination(totalCount, page, size);
        return paginationDTO;
    }


}
