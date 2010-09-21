/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.springtutorial;

/**
 *
 * @author jeff.huang
 */
public class MyBean2 {
    private String id;
    private String beanId;
    private MyBean bean = new MyBean();
    private MyBean beanX;

    public MyBean getBean() {
        return bean;
    }

    public void setBean(MyBean bean) {
        this.bean = bean;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBeanId() {
        return beanId;
    }

    public void setBeanId(String beanId) {
        this.beanId = beanId;
    }

    public MyBean getBeanX() {
        return beanX;
    }

    public void setBeanX(MyBean beanX) {
        this.beanX = beanX;
    }

    
    

}
