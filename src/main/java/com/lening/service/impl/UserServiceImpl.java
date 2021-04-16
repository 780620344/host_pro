package com.lening.service.impl;

import com.lening.entity.*;
import com.lening.mapper.*;
import com.lening.service.UserService;
import com.lening.utils.MD5key;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private MeunMapper meunMapper;
    @Autowired
    private DeptMapper deptMapper;

    @Override
    public List<UserBean> getUserList() {
        return userMapper.selectByExample(null);
    }

    @Override
    public List<MeunBean> getMeunList() {
        List<MeunBean> list = meunMapper.selectByExample(null);
        return list;
    }

    @Override
    public UserBean getUserVoById(Long id) {
        UserBean userBean = userMapper.selectByPrimaryKey(id);
        /**
         * 去查询这个用户有那些部门，咱们的设计是一对多，所以页面待会儿要不用复选框接收，
         * 要是不用下拉框接收，但是下拉框要支持多选
         */
        Long[] deptids = userMapper.getUserDeptidsById(id);
        userBean.setDeptids(deptids);
        List<DeptBean> deptBeanList = deptMapper.selectByExample(null);
        userBean.setDlist(deptBeanList);
        return userBean;
    }

    @Override
    public void saveUserDept(Long id, Long[] deptids) {
        /**
         * 修改科室，要把该用户原来的科室删掉，再次新增
         * 还需要把原来的职位也删除掉（科室都变了，职位没了）
         */
        userMapper.deleteUserPostById(id);
        userMapper.deleteUserDeptById(id);
        if(deptids!=null&&deptids.length>=1){
            for (Long deptid : deptids) {
                userMapper.insertUserDept(id,deptid);
            }
        }
    }

    @Override
    public void saveUserPost(UserBean userBean) {
        /**
         * 先去删除用户的职位
         */

        if(userBean!=null){
            userMapper.deleteUserPostById(userBean.getId());
            if(userBean.getDlist()!=null&&userBean.getDlist().size()>=1){
                for (DeptBean deptBean : userBean.getDlist()) {
                    if(deptBean.getPostids()!=null&&deptBean.getPostids().length>=1){
                        for (Long postid : deptBean.getPostids()) {
                            userMapper.saveUserPost(userBean.getId(),postid);
                        }
                    }
                }
            }
        }
    }

    @Override
    public UserBean getUserInfo(Long id) {
        UserBean userBean = userMapper.selectByPrimaryKey(id);
        /**
         * 开始去查询这个用户有没有部门
         */
        List<DeptBean> dlist = userMapper.getUserDeptById(id);

        /**
         * 开始循环部门，查询部门里面的职位
         */
        if(dlist!=null&&dlist.size()>=1){
            for (DeptBean deptBean : dlist) {
                List<PostBean> plist = deptMapper.getDeptPostList(deptBean.getId());
                Long[] postids = deptMapper.getUserPostByIdAndDeptid(id,deptBean.getId());
                deptBean.setPostids(postids);
                deptBean.setPlist(plist);
            }
        }
        userBean.setDlist(dlist);

        return userBean;
    }

    @Override
    public UserBean getLogin(UserBean ub) {
        //先用户用户名或者手机号，都是用用户名接收的，有可能用户输入的手机号，都是strint类型，无所谓

        if(ub!=null){
            List<UserBean> list = userMapper.getLogin(ub);
            if(list!=null&&list.size()==1){
                //到了这里表示用用户名或者手机号码查询到了
                //开始比对密码，比对密码之前需要加盐加密
                //加密算法有很多，常用的md5，bscript等
                UserBean userBean = list.get(0);
                //页面用户输入的密码，进行加密加盐后和数据库里面的密码进行比较，相等正确，不相等就错误

                String pwd = ub.getPwd();
                //也就是这里的加盐加密规则和注册的要一致，就好了，都是一个人负责的
                pwd = userBean.getPwdsalt()+pwd+userBean.getPwdsalt();

                MD5key md5key = new MD5key();
                String newpwd = md5key.getkeyBeanofStr(pwd);

                //相等就返回，其他都是空
                if(newpwd.equals(userBean.getPwd())){
                    return userBean;
                }
            }
        }

        return null;
    }


    @Test
    public void test(){
        String pwd = "857857";
        pwd = "1234"+pwd+"1234";
        MD5key md5key = new MD5key();
        String newpwd = md5key.getkeyBeanofStr(pwd);
        System.out.println(newpwd);
    }









    public List<MeunBean> getMeunList2(){
        Long[] ids = {1L,2L,3L};
        List<MeunBean> list = meunMapper.selectByExample(null);
        for (Long id : ids){
            for (MeunBean bean : list){
                if (id.equals(bean.getId())){
                    bean.setChecked(true);
                }
            }
        }
        return list;
    }


}
