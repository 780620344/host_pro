package com.lening.controller;

import com.lening.entity.*;
import com.lening.service.UserService;
import com.lening.utils.ResultInfo;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.HttpServletBean;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;


    @RequestMapping("/saveUserPost")
    public ResultInfo saveUserPost(@RequestBody UserBean userBean){
        try {
            userService.saveUserPost(userBean);
            return new ResultInfo(true, "编辑成功");
        }catch (Exception e){
            return new ResultInfo(false, "编辑失败");
        }
    }

    /**
     * 去给用户分配职位
     */
    @RequestMapping("/getUserInfo")
    public UserBean getUserInfo(Long id){
        return userService.getUserInfo(id);
    }

    @RequestMapping("/saveUserDept")
    public ResultInfo saveUserDept(Long id, @RequestBody Long[] deptids){
        try {
            userService.saveUserDept(id,deptids);
            return new ResultInfo(true, "编辑成功");
        }catch (Exception e){
            return new ResultInfo(false, "编辑失败");
        }
    }

    @RequestMapping("/getUserList")
    public List<UserBean> getUserList(){
        return userService.getUserList();
    }

    @RequestMapping("/getLogin")
    public ResultInfo getLogin(@RequestBody UserBean ub, HttpServletRequest request){
        UserBean userBean = userService.getLogin(ub);
        if (userBean==null){
            return new ResultInfo(false,"登录失败");
        }else {
            request.getSession().setAttribute("ub",userBean);
            return new ResultInfo(true,"登录成功");
        }
    }

    @RequestMapping("/getMeunList")
    public List<MeunBean> getMeunList(){
        return userService.getMeunList();
    }

    @RequestMapping("/getUserVoById")
    public UserBean getUserVoById(Long id){
        return userService.getUserVoById(id);
    }


    //添加测试
}
