package com.lening.service;

import com.lening.entity.*;
import com.lening.utils.Page;

import java.util.List;

public interface PostService {
    Page<PostBean> getPostListConn(PostBean postBean, Integer pageNum, Integer pageSize);

    void savePostMeun(Long postid, Long[] ids);

    List<MeunBean> getMeunListById(Long id);
}
