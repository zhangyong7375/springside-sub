/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.springtutorial;

/**
 *
 * @author jeff.huang
 */
public class MyChild extends MyParent {
    private String z;

    public String getZ() {
        return z;
    }

    public void setZ(String z) {
        this.z = z;
    }

    @Override
    public String toString() {
        return super.toString() + "/MyChild{" + "z=" + z + '}' + getList().toString();
    }

    
}
