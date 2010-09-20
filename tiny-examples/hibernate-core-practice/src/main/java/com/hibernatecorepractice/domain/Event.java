/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hibernatecorepractice.domain;

import java.util.Date;

/**
 *
 * @author Judy
 */
public class Event {
    private Long id;
    private String title;
    private Date date;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    private void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    
}
