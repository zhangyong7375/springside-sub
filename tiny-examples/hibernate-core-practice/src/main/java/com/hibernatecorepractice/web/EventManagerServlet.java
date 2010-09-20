/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hibernatecorepractice.web;

import com.hibernatecorepractice.domain.Event;
import com.hibernatecorepractice.domain.Person;
import com.hibernatecorepractice.util.HibernateUtil;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Session;

/**
 *
 * @author Judy
 */
public class EventManagerServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

        try{
            //Begin unit of work
            HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();

            //Process request and render page...

            //Write HTML header
            PrintWriter out = resp.getWriter();

            //Handle actions
            if( "store".equals(req.getParameter("action"))){
                String eventTitle = req.getParameter("eventTitle");
                String eventDate = req.getParameter("eventDate");

                if("".equals(eventTitle) || "".equals(eventDate)){
                    out.println("<b><i>Please enter event title and date.</i></b>");
                }else{
                    createAndStoreEvent(eventTitle, dateFormat.parse(eventDate));
                    out.println("<b><i>Added event.</i></b>");
                }
            }

            //Print page
            printEventForm(out);
            listEvents(out,dateFormat);

            //Write HTML footer
            out.println("</body></html>");
            out.flush();
            out.close();

            //End unit of work
            HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
        }catch(Exception ex){
            HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().rollback();
            if(ServletException.class.isInstance(ex)){
                throw (ServletException)ex;
            }else{
                throw new ServletException(ex);
            }
        }
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

    private void printEventForm(PrintWriter out) {
        out.println("<h2>Add new event:</h2>");
        out.println("<form>");
        out.println("Title: <input name='eventTitle' length='50'/><br/>");
        out.println("Date (e.g. 24.12.2009): <input name='eventDate' length='10'/><br/>");
        out.println("<input type='submit' name='action' value='store'/>");
        out.println("</form>");
    }

    private void listEvents(PrintWriter out, SimpleDateFormat dateFormatter) {

        List result = HibernateUtil.getSessionFactory()
                .getCurrentSession().createCriteria(Event.class).list();
        if (result.size() > 0) {
            out.println("<h2>Events in database:</h2>");
            out.println("<table border='1'>");
            out.println("<tr>");
            out.println("<th>Event title</th>");
            out.println("<th>Event date</th>");
            out.println("</tr>");
            Iterator it = result.iterator();
            while (it.hasNext()) {
                Event event = (Event) it.next();
                out.println("<tr>");
                out.println("<td>" + event.getTitle() + "</td>");
                out.println("<td>" + dateFormatter.format(event.getDate()) + "</td>");
                out.println("</tr>");
            }
            out.println("</table>");
        }
    }

}
