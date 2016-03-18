package todo.service;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;

import todo.TodoItem;
import todo.dao.TodoItemDAO;

public class TodoItemService {
	
	private static final Logger lgr = Logger.getLogger(TodoItemService.class.getName());

	@EJB
	private TodoItemDAO todoItemDAO = new TodoItemDAO();
	
	public TodoItem create(TodoItem todoItem) {
		lgr.log(Level.FINEST, "entry");
		todoItemDAO.create(todoItem);
		lgr.log(Level.FINEST, "exit");
		return todoItem;
	}

	public TodoItem update(TodoItem todoItem) {
		return todoItemDAO.update(todoItem);
	}

	public TodoItem get(long id) {
		return todoItemDAO.get(id);
	}

	public void delete(long id) {
		lgr.log(Level.FINEST, "Entry. id={0}", id);
		todoItemDAO.delete(id);
		lgr.log(Level.FINEST, "exit");
	}

	public List<TodoItem> list(int start, int max) {
		return todoItemDAO.list(start, max);
	}

	public int getCount() {
		return todoItemDAO.getCount();
	}

	public List<TodoItem> listActive(final int start, final int max) {
		return todoItemDAO.listActive(start, max);
	}
	
	/**
	 * This method returns a TodoItem that has the given summary.
	 * 
	 * @return
	 */
	public TodoItem getBySummary(final String summary) {
		return todoItemDAO.getBySummary(summary);
	}
}
