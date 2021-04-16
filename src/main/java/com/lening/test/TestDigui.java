package com.lening.test;

import org.junit.Test;

public class TestDigui {

    /**
     * 计算阶乘,
     * 1 1 2 3 5 8 13 21 34 55
     * an=an-1 +an-2
     */

    @Test
    public void test(){
        Long jiecheng = getFeishi(30L);
        System.out.println(jiecheng);
    }

    public Long getFeishi(Long x){
        if (x==1 || x==2){
            return 1L;
        }
        return getFeishi(x-1)+getFeishi(x-2);
    }

    public Long getjiecheng(Long x){
        if (x==1){
            return 1L;
        }
        return x*getjiecheng(x-1);
    }
}
