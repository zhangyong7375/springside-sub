/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hibernatecorepractice.web;

import com.hibernatecorepractice.util.HibernateUtil;
import java.io.IOException;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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



}
