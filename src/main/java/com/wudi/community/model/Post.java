package com.wudi.community.model;

import lombok.Data;

@Data
public class Post {
    private String title;
    private String description;
    private Long gmtCreate;
    private Long gmtModified;
    private Integer authorId;
    private Integer commentCount;
    private Integer viewCount;
    private Integer likeCount;
    private Integer id;
    private String tag;
}
