package todo;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import todo.service.TodoItemService;

public class Util {

	private static final Logger lgr = Logger.getLogger(Util.class.getName());
	private static TodoItemService todoItemService = new TodoItemService();

	public static TodoItemService getTodoItemService() {
		return todoItemService;
	}
	
	/* Hibernate related */
	private static SessionFactory sf;

	public static SessionFactory getSessionFactory() {
		if(sf == null) {
			Configuration c = new Configuration();
			c.configure("/hibernate.cfg-todo.xml");
			//sf = c.buildSessionFactory(new StandardServiceRegistryBuilder().build());
			sf = c.buildSessionFactory();
		}
		return sf;
	}

	public static void closeSession(final Session s) {
		if(s != null) {
			try {
				s.close();
			} catch (final HibernateException e2) {
				lgr .log(Level.SEVERE, "HibernateException", e2);
			}
		}
	}
}
