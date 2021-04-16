package com.lening.service;

import com.lening.entity.DeptBean;
import com.lening.utils.Page;

public interface DeptService {
    Page<DeptBean> getDeptListConn(DeptBean deptBean, Integer pageNum, Integer pageSize);

    DeptBean getDeptByDeptid(Long deptid);

    void saveDeptPost(Long deptid, Long[] postids);
}
