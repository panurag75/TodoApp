package todo.dao;

import java.util.List;

import javax.ejb.Local;

import todo.TodoItem;

@Local
public interface TodoItemDAOLocal {
	public TodoItem create(TodoItem todoItem);
	public TodoItem update(TodoItem todoItem);
	public TodoItem get(long id);
	public void delete(long id);
	public List<TodoItem> list(int start, int max);
	
	/**
	 * Method to get the list of todo items that are active i.e. not completed.
	 * 
	 * @param start
	 * @param max
	 * @return
	 */
	public List<TodoItem> listActive(int start, int max);
	public int getCount();
}
