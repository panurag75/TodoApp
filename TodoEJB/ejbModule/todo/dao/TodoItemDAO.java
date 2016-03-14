package todo.dao;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;
import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import todo.Status;
import todo.TodoItem;

@Stateless
@WebService
public class TodoItemDAO implements TodoItemDAOLocal, TodoItemDAORemote {
	
	private static final Logger lgr = Logger.getLogger(TodoItemDAO.class.getName());

	@PersistenceContext(unitName="TodoPU")
	private EntityManager em;

	@EJB
	private StatusDAOLocal statusDAOLocal;
	
	@Override
	public TodoItem create(TodoItem todoItem) {
		lgr.log(Level.FINEST, "entry");
		em.persist(todoItem);
		lgr.log(Level.FINEST, "exit");
		return todoItem;
	}

	@Override
	public TodoItem update(TodoItem todoItem) {
		return em.merge(todoItem);
	}

	@Override
	public TodoItem get(long id) {
		return em.find(TodoItem.class, id);
	}

	@Override
	public void delete(long id) {
		lgr.log(Level.FINEST, "Entry. id={0}", id);
		final TodoItem todoItem = get(id);
		if(todoItem != null) em.remove(todoItem);
		lgr.log(Level.FINEST, "exit");
	}

	@Override
	public List<TodoItem> list(int start, int max) {
		final TypedQuery<TodoItem> typedQuery = em.createNamedQuery("findAll", TodoItem.class);
		typedQuery.setFirstResult(start);
		typedQuery.setMaxResults(max);
		return typedQuery.getResultList();
	}

	@Override
	public int getCount() {
		final String query = "select count(*) from todoitem";
		final Query q = em.createNativeQuery(query);
		final Object o = q.getSingleResult();
		return ((Long)o).intValue();
	}

	@Override
	public List<TodoItem> listActive(final int start, final int max) {
		lgr.log(Level.FINEST, "Entry. start={0}, max={1}", new Object[] {start, max});
		final Status status_completed = statusDAOLocal.get("Completed");
		final TypedQuery<TodoItem> tq = em.createNamedQuery("getActive", TodoItem.class);
		tq.setParameter("status", status_completed)
			.setFirstResult(start)
			.setMaxResults(max);
		List<TodoItem> resultList = tq.getResultList();
		lgr.log(Level.FINEST, "Entry. result={0}", resultList);
		return resultList;
	}
}
