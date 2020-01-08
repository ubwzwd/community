package com.wudi.community.mapper;

import com.wudi.community.model.Post;
import com.wudi.community.model.PostExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface PostExtMapper {
    int incView(Post record);
}