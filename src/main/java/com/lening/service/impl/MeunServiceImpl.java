package com.lening.service.impl;

import com.lening.entity.*;
import com.lening.mapper.MeunMapper;
import com.lening.service.MeunService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class MeunServiceImpl implements MeunService {

    @Resource
    private MeunMapper meunMapper;


    @Override
    public void saveMeun(MeunBean meunBean) {
        if (meunBean!=null){
            if (meunBean.getId()!=null){
                //这个修改是遇到实体类中字段值为空的时候，不修改，一般密码等
                meunMapper.updateByPrimaryKeySelective(meunBean);
                /**
                 * 实体类传递过来是什么，把数据库中全部修改为实体类中的值，即便实体类中为空，也把数据库中修改为空
                 * meunMapper.updateByPrimaryKey(meunBean);
                 */
            }else {
                meunMapper.insertSelective(meunBean);
            }
        }
    }

    @Override
    public List<MeunBean> getMeunListByPid(Long pid) {
        MeunBeanExample example = new MeunBeanExample();
        MeunBeanExample.Criteria criteria = example.createCriteria();
        criteria.andPidEqualTo(pid);
        return meunMapper.selectByExample(example);
    }

    List<Long> ids = new ArrayList<>();
    @Override
    public void deleteMeunById(Long id) {
        getMeunListByPidToDelete(id);
        //在这里拿到ids,去删除
        for (Long id1 : ids){
            meunMapper.deleteByPrimaryKey(id1);
        }
    }

    private void getMeunListByPidToDelete(Long pid) {
        ids.add(pid);
        MeunBeanExample example = new MeunBeanExample();
        MeunBeanExample.Criteria criteria = example.createCriteria();
        criteria.andPidEqualTo(pid);
        List<MeunBean> list = meunMapper.selectByExample(example);
        /**
         * 和我们阶乘的思想有点不一样，阶乘是到了最后满足条件了结束
         * 不组满条件的时候，一直执行
         *
         * 只要满足条件就执行，不满足条件了结束。
         *
         */
        if (list!=null&&list.size()>=1){
            for (MeunBean bean : list){
                getMeunListByPidToDelete(bean.getId());
            }
        }
    }
}
