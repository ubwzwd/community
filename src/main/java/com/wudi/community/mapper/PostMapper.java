package com.wudi.community.mapper;

import com.wudi.community.model.Post;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PostMapper {

    @Insert("insert into post (title,description,gmt_create,gmt_modified,user_id,tag) values (#{title},#{description},#{gmtCreate},#{gmtModified},#{userId},#{tag})")
    void create(Post post);

    @Select("select * from POST limit #{size} offset #{offset}")
    List<Post> list(@Param(value="offset") Integer offset, @Param(value="size") Integer size);

    @Select("select count(1) from POST")
    Integer count();

    @Select("select * from POST where USER_ID=#{userId} limit #{size} offset #{offset}")
    List<Post> listByUserId(Integer userId, Integer offset, Integer size);

    @Select("select count(1) from POST where USER_ID=#{userId}")
    Integer countByUserId(Integer userId);

    @Select("select * from POST where ID=#{id}")
    Post getById(@Param("id") Integer id);

    @Update("update post set title = #{title}, description = #{description}, tag = #{tag}, gmt_modified = #{gmtModified} where id = #{id}")
    void update(Post post);
}