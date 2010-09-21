/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.springtutorial;

import java.util.List;

/**
 *
 * @author jeff.huang
 */
public abstract class MyParent {
    private String x;
    private int y;
    private List list;

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "MyParent{" + "x=" + x + "y=" + y + '}';
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

    

}
