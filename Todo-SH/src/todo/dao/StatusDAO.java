package todo.dao;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import todo.Status;
import todo.Util;

//@Stateless
public class StatusDAO {
	
	private static final Logger lgr = Logger.getLogger(StatusDAO.class.getName());

	public Status get(final String name) {
		lgr.log(Level.FINEST, "Entry. name={0}", name);
		Session s = null;
		try {
			s=Util.getSessionFactory().openSession();
			lgr.log(Level.FINEST, "Exit. Returning from try block");
			final Query q = s.getNamedQuery("Status.getByName");
			q.setString("name", name);
			final Object o = q.uniqueResult();
			if(o != null && o instanceof Status) {
				lgr.log(Level.FINEST, "Exit. Returning in try block");
				return (Status)o;
			}
			lgr.log(Level.FINEST, "Exit. Returning null in try block. o = {0}", o);
			return null;
		}
		catch(final HibernateException e) {
			lgr.log(Level.SEVERE, "HibernateException", e);
			lgr.log(Level.FINEST, "Exit. Returning in catch block");
			return null;
		}
		finally {
			Util.closeSession(s);
		}
	}

}
