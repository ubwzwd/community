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

        // set pagination
        // totalCount is the total number of posts
        Integer totalCount = postMapper.count();
        PaginationDTO paginationDTO = new PaginationDTO();
        paginationDTO.setPagination(totalCount, page, size);
        page = paginationDTO.getPage();

        // set posts
        // the begin index- of each page
        Integer offset = size*(page-1);
//        List<Post> posts = postMapper.list(offset, (totalCount-offset>=size ? size : totalCount-offset));
        List<Post> posts = postMapper.list(offset, size);
        List<PostDTO> postDTOS = new ArrayList<>();
        for(Post post : posts){
            User user = userMapper.findById(post.getUserId());
            PostDTO postDTO = new PostDTO();
            BeanUtils.copyProperties(post, postDTO);
            postDTO.setUser(user);
            postDTOS.add(postDTO);
        }
        paginationDTO.setPosts(postDTOS);

        return paginationDTO;
    }


    public PaginationDTO list(Integer userId, Integer page, Integer size) {
        // set pagination
        // totalCount is the total number of posts
        Integer totalCount = postMapper.countByUserId(userId);
        PaginationDTO paginationDTO = new PaginationDTO();
        paginationDTO.setPagination(totalCount, page, size);
        page = paginationDTO.getPage();

        // set posts
        // the begin index- of each page
        Integer offset = size*(page-1);
//        List<Post> posts = postMapper.list(offset, (totalCount-offset>=size ? size : totalCount-offset));
        List<Post> posts = postMapper.listByUserId(userId, offset, size);
        List<PostDTO> postDTOS = new ArrayList<>();
        for(Post post : posts){
            User user = userMapper.findById(post.getUserId());
            PostDTO postDTO = new PostDTO();
            BeanUtils.copyProperties(post, postDTO);
            postDTO.setUser(user);
            postDTOS.add(postDTO);
        }
        paginationDTO.setPosts(postDTOS);

        return paginationDTO;
    }

    public PostDTO getById(Integer id) {
        Post post = postMapper.getById(id);
        PostDTO postDTO = new PostDTO();
        BeanUtils.copyProperties(post, postDTO);
        User user = userMapper.findById(post.getUserId());
        postDTO.setUser(user);
        return postDTO;
    }
}
