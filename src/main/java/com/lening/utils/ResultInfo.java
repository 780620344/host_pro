package com.lening.utils;

import lombok.Data;

import java.io.Serializable;


@Data
public class ResultInfo implements Serializable {
    private boolean flag;
    private String msg;

    public ResultInfo() {
    }

    public ResultInfo(boolean flag, String msg) {
        this.flag = flag;
        this.msg = msg;
    }
}
