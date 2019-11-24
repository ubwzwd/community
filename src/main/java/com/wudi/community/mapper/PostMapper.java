package com.wudi.community.mapper;

import com.wudi.community.model.Post;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PostMapper {

    @Insert("insert into post (title,description,gmt_create,gmt_modified,author_id,tag) values (#{title},#{description},#{gmtCreate},#{gmtModified},#{authorId},#{tag})")
    public void create(Post post);
}
