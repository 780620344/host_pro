package com.lening.entityvo;

import com.lening.entity.*;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class UserVo extends UserBean implements Serializable {

    private Long[] deptids;

    private List<DeptBean> dlist;

}
