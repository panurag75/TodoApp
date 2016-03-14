package todo.beans;

import java.util.List;

import javax.ejb.EJB;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import todo.dao.TodoItemDAORemote;

public class TodoItemListBean {

//	private static final Logger lgr = Logger.getLogger(TodoItemListBean.class.getName());

	@EJB
	private TodoItemDAORemote todoItemDAORemote; 

	private List<todo.TodoItem> todoItemList;

	public TodoItemListBean() {
		lookup();
		if(todoItemDAORemote != null) {
			setTodoItemList(todoItemDAORemote.listActive(0, 10));
		}
	}

	private void lookup() {
		try {
			final Context c = new InitialContext();
			final Object o = c.lookup(
					"java:global/todo/TodoEJB/TodoItemDAO!todo.dao.TodoItemDAORemote");
			if(o instanceof TodoItemDAORemote) {
				todoItemDAORemote = (TodoItemDAORemote)o;
			}
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	public List<todo.TodoItem> getTodoItemList() {
		return todoItemList;
	}

	public void setTodoItemList(final List<todo.TodoItem> todoItemList) {
		this.todoItemList = todoItemList;
	}

	/**
	 * action method.
	 */
//	public void addTodoItem() {
//		lgr.log(Level.FINEST, "entry");
//		todo.TodoItem todoItem = todoItemDAORemote.create(getTodoItem());
//		setValues(todoItem);
//		lgr.log(Level.FINEST, "exit");
//	}
}
