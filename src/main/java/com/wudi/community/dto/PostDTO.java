package com.wudi.community.dto;

import com.wudi.community.model.User;
import lombok.Data;

@Data
public class PostDTO {
    private String title;
    private String description;
    private Long gmtCreate;
    private Long gmtModified;
    private Integer userId;
    private Integer commentCount;
    private Integer viewCount;
    private Integer likeCount;
    private Integer id;
    private String tag;
    private User user;
}
