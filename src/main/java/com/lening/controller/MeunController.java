package com.lening.controller;

import com.lening.entity.MeunBean;
import com.lening.service.MeunService;
import com.lening.utils.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.zip.Deflater;

@RestController
@RequestMapping("/meun")
public class MeunController {

    @Resource
    private MeunService meunService;

    @RequestMapping("/saveMeun")
    public ResultInfo getMeunListConn(@RequestBody MeunBean meunBean){
        //使用递归
        try {
            meunService.saveMeun(meunBean);
            return new ResultInfo(true,"编辑成功");
        } catch (Exception e){
            return new ResultInfo(false,"编辑失败");
        }
    }

    @RequestMapping("/getMeunListByPid")
    public List<MeunBean> getMeunListByPid(@RequestParam(defaultValue = "1") Long pid){
        return meunService.getMeunListByPid(pid);
    }

    @RequestMapping("/deleteMeunById")
    public ResultInfo deleteMeunById(Long id){
        try {
            meunService.deleteMeunById(id);
            return new ResultInfo(true,"删除成功");
        }catch (Exception e){
            return new ResultInfo(false,"删除失败");
        }
    }

}
