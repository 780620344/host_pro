package com.lening.controller;

import com.lening.entity.*;
import com.lening.service.PostService;
import com.lening.utils.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {

    @Resource
    private PostService postService;

    @RequestMapping("/getPostListConn")
    public Page<PostBean> getPostListConn(@RequestBody PostBean postBean, @RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "3") Integer pageSize){

        return postService.getPostListConn(postBean,pageNum,pageSize);

    }

    @RequestMapping("/savePostMeun")
    public ResultInfo savePostMeun(Long postid,@RequestBody Long[] ids){
        try {
            postService.savePostMeun(postid,ids);
            return new ResultInfo(true, "保存成功");
        }catch (Exception e){
            return new ResultInfo(false, "保存失败");
        }
    }

    @RequestMapping("/getMeunListById")
    public List<MeunBean> getMeunListById(Long id){
        return postService.getMeunListById(id);
    }

}
