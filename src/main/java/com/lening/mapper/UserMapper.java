package com.lening.mapper;

import com.lening.entity.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    long countByExample(UserBeanExample example);

    int deleteByExample(UserBeanExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UserBean record);

    int insertSelective(UserBean record);

    List<UserBean> selectByExample(UserBeanExample example);

    UserBean selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UserBean record, @Param("example") UserBeanExample example);

    int updateByExample(@Param("record") UserBean record, @Param("example") UserBeanExample example);

    int updateByPrimaryKeySelective(UserBean record);

    int updateByPrimaryKey(UserBean record);

    Long[] getUserDeptidsById(@Param("id") Long id);

    void deleteUserPostById(@Param("id") Long id);

    void deleteUserDeptById(@Param("id") Long id);

    void insertUserDept(@Param("userid") Long userid, @Param("deptid") Long deptid);

    List<DeptBean> getUserDeptById(@Param("id") Long id);

    void deleteUserPost(@Param("id") Long id);

    void saveUserPost(@Param("userid") Long userid, @Param("postid") Long postid);

    List<UserBean> getLogin(UserBean ub);
}
