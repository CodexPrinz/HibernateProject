package org.projects;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.projects.model.Employee;
import org.projects.model.Employee1;
import org.projects.util.HibernateUtil;

import java.util.Date;

/**
 * Hello world!
 *
 */

public class App 
{
    public static void main( String[] args )
    {
        /**  xml based mapping */
        System.out.println("---------- xml based mapping -----------");
        sessionFactoryTest();

        // annotation
        System.out.println("---------- annotation -----------");
        sessionAnnotationFactory();

        //  java configuration
        System.out.println("---------- java configuration -----------");
        sessionJavaConfiguration();


    }

    public static void sessionFactoryTest(){
        Employee emp = new Employee();
        emp.setName("James Tosland");
        emp.setRole("Rider");
        emp.setInsertTime(new Date());

        //  get session
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        //  start transaction
        session.beginTransaction();

        //  save the model object
        session.save(emp);

        //  commit transaction
        session.getTransaction().commit();
        System.out.println("Employee ID= "+emp.getId());

        //  terminate session factory, otherwise program won't end
        HibernateUtil.getSessionFactory().close();
    }

    public static void sessionAnnotationFactory(){
        Employee1 emp = new Employee1();
        emp.setName("Ben Spies");
        emp.setRole("Rider");
        emp.setInsertTime(new Date());

        //  get session
        SessionFactory sessionFactory = HibernateUtil.getSessionAnnotationFactory();
        Session session = sessionFactory.getCurrentSession();

        //   start transaction
        session.beginTransaction();
        session.save(emp);
        session.getTransaction().commit();
        System.out.println("Employee ID= "+emp.getId());

        sessionFactory.close();

    }

    public static void sessionJavaConfiguration(){
        Employee1 emp = new Employee1();
        emp.setName("Lisa");
        emp.setRole("Manager");
        emp.setInsertTime(new Date());

        //Get Session
        SessionFactory sessionFactory = HibernateUtil.getSessionJavaConfigFactory();
        Session session = sessionFactory.getCurrentSession();
        //start transaction
        session.beginTransaction();
        //Save the Model object
        session.save(emp);
        //Commit transaction
        session.getTransaction().commit();
        System.out.println("Employee ID="+emp.getId());

        //terminate session factory, otherwise program won't end
        sessionFactory.close();
    }


}
