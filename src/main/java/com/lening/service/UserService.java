package com.lening.service;

import com.github.pagehelper.Page;
import com.lening.entity.*;

import java.util.List;


public interface UserService {
    List<UserBean> getUserList();

    List<MeunBean> getMeunList();

    UserBean getUserVoById(Long id);

    void saveUserDept(Long id, Long[] deptids);

    void saveUserPost(UserBean userBean);

    UserBean getUserInfo(Long id);

    UserBean getLogin(UserBean ub);
}
