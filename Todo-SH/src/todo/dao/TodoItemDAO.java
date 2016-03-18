package todo.dao;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.hibernate.Transaction;
import org.hibernate.Query;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import todo.Status;
import todo.TodoItem;
import todo.Util;

//@Stateless
//@WebService
public class TodoItemDAO {
	
	private static final Logger lgr = Logger.getLogger(TodoItemDAO.class.getName());

	public TodoItem create(TodoItem todoItem) {
		lgr.log(Level.FINEST, "Entry. todoItem={0}", todoItem);
		Session s = null;
		Transaction t=null;
		try {
			s=Util.getSessionFactory().openSession();
			t=s.beginTransaction();
			s.save(todoItem);
			t.commit();
			lgr.log(Level.FINEST, "Exit. Returning from try block");
			return todoItem;
		}
		catch(final HibernateException e) {
			lgr.log(Level.SEVERE, "HibernateException", e);
			t.rollback();
			lgr.log(Level.FINEST, "Exit. Returning from catch block");
			return null;
		}
		finally {
			Util.closeSession(s);
		}
	}

	public TodoItem update(TodoItem todoItem) {
		lgr.log(Level.FINEST, "Entry. todoItem={0}", todoItem);
		Session s = null;
		Transaction t=null;
		try {
			s=Util.getSessionFactory().openSession();
			t=s.beginTransaction();
			s.update(todoItem);
			t.commit();
			lgr.log(Level.FINEST, "Exit. Returning from try block");
			return todoItem;
		}
		catch(final HibernateException e) {
			lgr.log(Level.SEVERE, "HibernateException", e);
			t.rollback();
			lgr.log(Level.FINEST, "Exit. Returning from catch block");
			return null;
		}
		finally {
			Util.closeSession(s);
		}
	}

	public TodoItem get(long id) {
		lgr.log(Level.FINEST, "Entry. id={0}", id);
		Session s = null;
		try {
			s=Util.getSessionFactory().openSession();
			lgr.log(Level.FINEST, "Exit. Returning from try block");
			return (TodoItem)s.load(TodoItem.class, id);
		}
		catch(final HibernateException e) {
			lgr.log(Level.SEVERE, "HibernateException", e);
			lgr.log(Level.FINEST, "Exit. Returning from catch block");
			return null;
		}
		finally {
			Util.closeSession(s);
		}
	}

	public void delete(long id) {
		lgr.log(Level.FINEST, "Entry. id={0}", id);
		Session s = null;
		Transaction t=null;
		try {
			s=Util.getSessionFactory().openSession();
			final TodoItem ti = get(id);
			if(ti != null) {
				t=s.beginTransaction();
				s.delete(ti);
				t.commit();
			}
			lgr.log(Level.FINEST, "Exit. Returning from try block");
		}
		catch(final HibernateException e) {
			lgr.log(Level.SEVERE, "HibernateException", e);
			t.rollback();
			lgr.log(Level.FINEST, "Exit. Returning from catch block");
		}
		finally {
			Util.closeSession(s);
		}
	}

	public List<TodoItem> list(int start, int max) {
		throw new RuntimeException("Not implemented");
	}

	public int getCount() {
		Session s = null;
		try {
			s=Util.getSessionFactory().openSession();
			final Query q = s.getNamedQuery("TodoItem.getCount");
			final Object o = q.uniqueResult();
			if(o != null) {
				lgr.log(Level.FINEST, "Exit. Returning in try block");
				return (Integer)o;
			}
			return 0;// this statement should not be reached because either a result should be returned
			// or an exception should be thrown.
		}
		catch(final HibernateException e) {
			lgr.log(Level.SEVERE, "HibernateException", e);
			lgr.log(Level.FINEST, "Exit. Returning from catch block");
			throw e;
		}
		finally {
			Util.closeSession(s);
		}
	}

	public List<TodoItem> listActive(final int start, final int max) {
		lgr.log(Level.FINEST, "Entry. start={0}, max={1}", new Object[] {start, max});
		Session s = null;
		try {
			s=Util.getSessionFactory().openSession();
			//final Query q = s.getNamedQuery("TodoItem.getActive");
			final Query q = s.createQuery("select t from TodoItem t where t.status <> :statusCompleted or t.status is null");

			final StatusDAO statusDAO = new StatusDAO();
			final Status statusCompleted = statusDAO.get("Completed");
			q.setParameter("statusCompleted",statusCompleted).setFirstResult(start).setMaxResults(max);

			final List list = q.list();
			return list;
		}
		catch(final HibernateException e) {
			lgr.log(Level.SEVERE, "HibernateException", e);
			lgr.log(Level.FINEST, "Exit");
			throw e;
		}
		finally {
			Util.closeSession(s);
		}
	}
	
	/**
	 * This method returns a TodoItem that has the given summary.
	 * 
	 * @return
	 */
	public TodoItem getBySummary(final String summary) {
		lgr.log(Level.FINEST, "Entry. summary={0}", summary);
		Session s = null;
		try {
			s=Util.getSessionFactory().openSession();
			final Query q = s.getNamedQuery("TodoItem.getBySummary");
			final Object o = q.uniqueResult();
			if(o != null) {
				lgr.log(Level.FINEST, "Exit. Returning in try block");
				return (TodoItem)o;
			}
			lgr.log(Level.FINEST, "Exit. Returning null in try block");
			return null;
		}
		catch(final HibernateException e) {
			lgr.log(Level.SEVERE, "HibernateException", e);
			lgr.log(Level.FINEST, "Exit");
			throw e;
		}
		finally {
			Util.closeSession(s);
		}
	}
}
