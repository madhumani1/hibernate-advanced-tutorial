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
public class DeleteInstructorDetailDemo {

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
			boolean flag = session.isOpen();
			session.beginTransaction();
			
			// now use the session object to save/retrieve Java objects
			//get the instructor detail object
			/**
			 * if theId does not fetch records, you will get error
			 * Exception in thread "main" java.lang.NullPointerException: 
			 * Cannot invoke "com.main.java.demo.entity.InstructorDetail.getInstructor()" because "tempInstructorDetail" is null
			 * at com.main.java.demo.GetInstructorDetailDemo.main(GetInstructorDetailDemo.java:51)
			 */
			int theId=3;	// make sure to use correct detail id. check in database
			InstructorDetail tempInstructorDetail = session.get(InstructorDetail.class, theId);
			
			// print detail
			System.out.println("tempInstructorDetail: "+tempInstructorDetail);
			
			//print the associated instructor
			System.out.println("the associated instructor "+ tempInstructorDetail.getInstructor());
			
			// delete the instructor detail. it will also delete instructor neil martis
			System.out.println("Deleting tempInstructorDetail: " + tempInstructorDetail);
			session.delete(tempInstructorDetail);
			
			// commit the transaction
			session.getTransaction().commit();
			System.out.println("Done!");
		}	catch(Exception e)	{
			e.printStackTrace();
		}	finally	{
			// handle connection leak issue
			if(session.isOpen())	{
				session.close();
			}
			factory.close();
		}

	}

}
