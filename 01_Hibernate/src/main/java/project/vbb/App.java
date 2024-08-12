package project.vbb;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import project.vbb.entity.Users;

public class App {

	public static void main(String[] args) {
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Users.class)
								.buildSessionFactory();

		Session session = factory.getCurrentSession();
		
		try {
	
			//create object of entity class type
			Users user = new Users("username", "password", "firstname", "lastname");
			
			//start transaction
			session.beginTransaction();
			
			//perform operation
			session.save(user);
			
			//commit transaction
			session.getTransaction().commit();
			System.out.println("Row Added!");
			
		}finally {
		session.close();
		factory.close();
	}
	}
}
