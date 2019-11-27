package com.wudi.community.mapper;

import com.wudi.community.dto.PostDTO;
import com.wudi.community.model.Post;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PostMapper {

    @Insert("insert into post (title,description,gmt_create,gmt_modified,user_id,tag) values (#{title},#{description},#{gmtCreate},#{gmtModified},#{userId},#{tag})")
    public void create(Post post);

    @Select("select * from POST")
    List<Post> list();
}
