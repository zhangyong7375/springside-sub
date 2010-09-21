/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.st;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

/**
 *
 * @author jeff.huang
 */
public class MyBeanWithExplicitInit implements InitializingBean, DisposableBean {
    private String x;
    private String id;

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.printf("x = %s/%s when doing initialization\n", x, id);
    }

    @Override
    public void destroy() throws Exception {
        System.out.printf("x = %s/%s when doing disposing\n", x, id);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    
}
