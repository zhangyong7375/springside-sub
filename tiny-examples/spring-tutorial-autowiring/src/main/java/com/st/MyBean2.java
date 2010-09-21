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

    public MyBean getMyBean() {
        return myBean;
    }

    public void setMyBean(MyBean myBean) {
        this.myBean = myBean;
    }

    

}
