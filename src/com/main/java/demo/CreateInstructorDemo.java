/**
 * 
 */
package com.main.java.demo;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.main.java.demo.entity.Instructor;
import com.main.java.demo.entity.InstructorDetail;

/**
 * @author 15197
 *
 */
public class CreateInstructorDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// create session factory
		/*SessionFactory factory = new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(Student.class)
									.buildSessionFactory();*/
		//SessionFactory factory = new Configuration().configure().buildSessionFactory();
		SessionFactory factory = new Configuration()
								.configure("emp.hibernate.cfg.xml")
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try	{
			// now use the session object to save/retrieve Java objects
			// create an Instructor object
			Instructor tempInstructor = new Instructor("Madhukar", "Mani","madhumani1@gmail.com");
			InstructorDetail tempInstructorDetail = new InstructorDetail("http://youtube.com","Playing chess!!!");
			
			// associate the objects together
			tempInstructor.setInstructorDetail(tempInstructorDetail);
			
			// start transaction
			session.beginTransaction();
			
			// save the Instructor
			// this will also save the details object because of CascadeType.ALL
			System.out.println("Saving Instructor: "+tempInstructor);
			session.save(tempInstructor);
			
			// commit the transaction
			session.getTransaction().commit();
		}	finally	{
			factory.close();
		}

	}

}
