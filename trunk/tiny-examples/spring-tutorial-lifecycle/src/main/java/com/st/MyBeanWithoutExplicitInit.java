/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.st;

import java.util.concurrent.atomic.AtomicInteger;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 *
 * @author jeff.huang
 */
public class MyBeanWithoutExplicitInit {
    private String x;
    private String id;
    private AtomicInteger counter = new AtomicInteger(0);

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    @PostConstruct
    public void postConstruct(){
        System.out.printf("x = %s/%s/%d after construct\n", x, id, counter.incrementAndGet());
    }

    @PreDestroy
    public void preDestruct() {
        System.out.printf("x = %s/%s before destruct", x, id);
    }

    public void init() {
        System.out.printf("x = %s/%s/%d when doing init\n", x, id, counter.incrementAndGet());
    }

    public void destroy() {
        System.out.printf("x = %s/%s when disposing\n", x, id);
    }

}
