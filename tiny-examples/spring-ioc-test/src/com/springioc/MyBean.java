/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.springioc;

/**
 *
 * @author Jeff
 */
public class MyBean {

    private String name;

    public MyBean() {
        this("MyBean");
    }

    public MyBean(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public void sayHello() {
        System.out.printf("%s - Hello world!\n", name);
    }

}
