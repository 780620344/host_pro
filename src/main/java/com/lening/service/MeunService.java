package com.lening.service;

import com.lening.entity.MeunBean;

import java.util.List;

public interface MeunService {

    void saveMeun(MeunBean meunBean);

    List<MeunBean> getMeunListByPid(Long pid);

    void deleteMeunById(Long id);
}
