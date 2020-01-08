package com.wudi.community.service;

import com.wudi.community.dto.PaginationDTO;
import com.wudi.community.dto.PostDTO;
import com.wudi.community.exception.CustomizedErrorCode;
import com.wudi.community.exception.CustomizedException;
import com.wudi.community.mapper.PostExtMapper;
import com.wudi.community.mapper.PostMapper;
import com.wudi.community.mapper.UserMapper;
import com.wudi.community.model.Post;
import com.wudi.community.model.PostExample;
import com.wudi.community.model.User;
import org.apache.ibatis.session.RowBounds;
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

    @Autowired
    private PostExtMapper postExtMapper;

    public PaginationDTO list(Integer page, Integer size) {

        // set pagination
        // totalCount is the total number of posts
        Integer totalCount = (int) postMapper.countByExample(new PostExample());
        PaginationDTO paginationDTO = new PaginationDTO();
        paginationDTO.setPagination(totalCount, page, size);
        page = paginationDTO.getPage();

        // set posts
        // the begin index- of each page
        Integer offset = size*(page-1);
//        List<Post> posts = postMapper.list(offset, (totalCount-offset>=size ? size : totalCount-offset));
        // List<Post> posts = postMapper.count();
        List<Post> posts = postMapper.selectByExampleWithBLOBsWithRowbounds(new PostExample(), new RowBounds(offset, size));
        List<PostDTO> postDTOS = new ArrayList<>();
        for(Post post : posts){
            User user = userMapper.selectByPrimaryKey(post.getUserId());
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
        PostExample postExample = new PostExample();
        postExample.createCriteria()
                .andUserIdEqualTo(userId);
        Integer totalCount = (int) postMapper.countByExample(postExample);
        PaginationDTO paginationDTO = new PaginationDTO();
        paginationDTO.setPagination(totalCount, page, size);
        page = paginationDTO.getPage();

        // set posts
        // the begin index- of each page
        Integer offset = size*(page-1);
//        List<Post> posts = postMapper.list(offset, (totalCount-offset>=size ? size : totalCount-offset));
//        List<Post> posts = postMapper.listByUserId(userId, offset, size);
        PostExample postExample1 = new PostExample();
        postExample1.createCriteria()
                .andUserIdEqualTo(userId);
        List<Post> posts = postMapper.selectByExampleWithBLOBsWithRowbounds(postExample1, new RowBounds(offset, size));
        List<PostDTO> postDTOS = new ArrayList<>();
        for(Post post : posts){
            User user = userMapper.selectByPrimaryKey(post.getUserId());
            PostDTO postDTO = new PostDTO();
            BeanUtils.copyProperties(post, postDTO);
            postDTO.setUser(user);
            postDTOS.add(postDTO);
        }
        paginationDTO.setPosts(postDTOS);

        return paginationDTO;
    }

    public PostDTO getById(Integer id) {
        Post post = postMapper.selectByPrimaryKey(id);
        if(post == null){
            throw new CustomizedException(CustomizedErrorCode.QUESTION_NOT_FOUND);
        }
        PostDTO postDTO = new PostDTO();
        BeanUtils.copyProperties(post, postDTO);
        User user = userMapper.selectByPrimaryKey(post.getUserId());
        postDTO.setUser(user);
        return postDTO;
    }

    public void createOrUpdate(Post post) {
        if(post.getId() == null){
            // create
            post.setGmtCreate(System.currentTimeMillis());
            post.setGmtModified(post.getGmtCreate());
            postMapper.insert(post);
        } else{
            // update
            post.setGmtModified(System.currentTimeMillis());
            int updated = postMapper.updateByPrimaryKeySelective(post);
            if(updated != 1){
                throw new CustomizedException(CustomizedErrorCode.QUESTION_NOT_FOUND);
            }
        }
    }

    public void incView(Integer id) {
        Post updatePost = postMapper.selectByPrimaryKey(id);
        postExtMapper.incView(updatePost);
    }
}
