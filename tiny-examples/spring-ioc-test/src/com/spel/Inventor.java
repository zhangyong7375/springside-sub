/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.spel;

import java.util.Date;

/**
 *
 * @author jeff.huang
 */
public class Inventor {
    private String name;
    private Date birthDay;
    private String country;

    public Inventor(String name, Date birthDay, String country) {
        this.name = name;
        this.birthDay = birthDay;
        this.country = country;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    

}
