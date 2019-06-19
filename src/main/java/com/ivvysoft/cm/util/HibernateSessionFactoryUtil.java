package com.ivvysoft.cm.util;

import org.hibernate.Session;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.ivvysoft.cm.model.Person;
import com.ivvysoft.cm.model.User;

public class HibernateSessionFactoryUtil {

	private static Session session;
	private HibernateSessionFactoryUtil() {

	}

	private static SessionFactory factory = new Configuration().configure().addAnnotatedClass(User.class)
			.addAnnotatedClass(Person.class).buildSessionFactory();

	public static Session getSessionFactory() {
		if (session == null || !session.isConnected()) {
			try {
				session = factory.getCurrentSession();
			} catch (Exception e) {
				System.out.println("Исключение!" + e);
			}
		}

		return session;
	}

	public static void closeCurrentSession() {
		if (session != null) {
			session.close();
			session = null;
		}
	}

	public static void factoryClose() {
		factory.close();
		factory = null;
	}
}
