/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hibernatecorepractice;

import com.hibernatecorepractice.domain.Event;
import com.hibernatecorepractice.domain.Person;
import com.hibernatecorepractice.util.HibernateUtil;
import java.util.Date;
import java.util.List;
import java.util.Random;
import org.hibernate.Session;

/**
 *
 * @author Judy
 */
public class EventManager {

    public static void main(String[] args) {
        EventManager mgr = new EventManager();

        if(args[0].equals("store")){
            mgr.createAndStoreEvent("My Event", new Date());
        }else if(args[0].equals("list")){
            List<Event> events = mgr.listEvents();
            for(Event event : events){
                System.out.println("Event: " + event.getTitle() + " Time: " + event.getDate());
            }
        }else if(args[0].equals("addpersontoevent")){
            Long eventId = mgr.createAndStoreEvent("My Event", new Date());
            Long personId = mgr.createAndStorePerson("Foo", "Bar");
            mgr.addPersonToEvent(personId, eventId);
            System.out.println("Add person " + personId + " to event " + eventId);
        }

        HibernateUtil.getSessionFactory().close();
    }

    private Long createAndStoreEvent(String string, Date date) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        Event event = new Event();
        event.setTitle(string);
        event.setDate(date);
        session.save(event);

        session.getTransaction().commit();
        return event.getId();
    }

    private Long createAndStorePerson(String firstname, String lastname){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        Person person = new Person();
        person.setFirstname(firstname);
        person.setLastname(lastname);
        person.setAge(Math.abs(new Random().nextInt()) % 50 + 20);

        session.save(person);
        session.getTransaction().commit();
        return person.getId();
    }

    private List<Event> listEvents() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List<Event> result = session.createQuery("from Event").list();
        session.getTransaction().commit();
        return result;
    }

    private void addPersonToEvent(Long personId, Long eventId){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        Person aPerson = (Person) session.load(Person.class, personId);
        Event anEvent = (Event) session.load(Event.class, eventId);
        aPerson.getEvents().add(anEvent);

        session.getTransaction().commit();
    }

    private void addEmailToPerson(Long personId, String emailAddress){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        Person person = (Person)session.load(Person.class, personId);
        person.getEmailAddresses().add(emailAddress);

        session.getTransaction().commit();
    }

}
