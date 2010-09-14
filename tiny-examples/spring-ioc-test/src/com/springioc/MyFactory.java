/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.springioc;

/**
 *
 * @author Jeff
 */
public class MyFactory {

    public static MyBean createInstance() {
        return new MyBean("Bean-From-Static-Method");
    }

    public MyBean createInstance2() {
        return new MyBean("Bean-From-Instance-Method");
    }

}
