/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.st;

import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author jeff.huang
 */
public class MyBean2 {

    @Autowired
    private MyBean myBean;
    //@Autowired
    private String s;

    public MyBean getMyBean() {
        return myBean;
    }

    public void setMyBean(MyBean myBean) {
        this.myBean = myBean;
    }

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }

    

}
