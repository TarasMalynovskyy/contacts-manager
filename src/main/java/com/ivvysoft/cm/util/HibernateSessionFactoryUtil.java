package com.ivvysoft.cm.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import com.ivvysoft.cm.model.Person;
import com.ivvysoft.cm.model.User;

public class HibernateSessionFactoryUtil {

	private static Session session;
	private static StandardServiceRegistry registry;
	private static SessionFactory sessionFactory;

	private HibernateSessionFactoryUtil() {

	}

	private static SessionFactory getSessionFactory() {
		if (sessionFactory == null) {
			try {
				StandardServiceRegistryBuilder registryBuilder = new StandardServiceRegistryBuilder();

				registryBuilder.configure("hibernate.cfg.xml");
				registry = registryBuilder.build();

				MetadataSources sources = new MetadataSources(registry).addAnnotatedClass(Person.class)
						.addAnnotatedClass(User.class);

				Metadata metadata = sources.getMetadataBuilder().build();
				sessionFactory = metadata.getSessionFactoryBuilder().build();
			} catch (Exception e) {
				if (registry != null) {
					StandardServiceRegistryBuilder.destroy(registry);
				}
				e.printStackTrace();
			}
		}
		return sessionFactory;
	}

	public static Session getSession() {
		if (session == null || !session.isConnected()) {
			try {
				session = getSessionFactory().openSession();
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
		sessionFactory.close();
		sessionFactory = null;
	}
}
