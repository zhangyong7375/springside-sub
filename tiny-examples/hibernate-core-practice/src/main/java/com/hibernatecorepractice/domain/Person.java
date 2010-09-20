/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hibernatecorepractice.domain;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Judy
 */
public class Person {
    private Long id;

    private int age;
    private String firstname;
    private String lastname;
    private Set events = new HashSet();
    private Set emailAddresses = new HashSet();

    public Person() {
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public Long getId() {
        return id;
    }

    private void setId(Long id) {
        this.id = id;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Set getEvents() {
        return events;
    }

    public void setEvents(Set Events) {
        this.events = Events;
    }

    public Set getEmailAddresses() {
        return emailAddresses;
    }

    public void setEmailAddresses(Set emailAddresses) {
        this.emailAddresses = emailAddresses;
    }

    public void addToEvent(Event event) {
        this.getEvents().add(event);
        event.getParticipants().add(this);
    }

    public void removeFromEvent(Event event){
        this.getEvents().remove(event);
        event.getParticipants().remove(this);
    }

}
