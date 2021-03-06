package todo.dao;

import java.util.List;

import javax.ejb.Remote;

import todo.TodoItem;

@Remote
public interface TodoItemDAORemote {
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
	
	/**
	 * This method returns a TodoItem that has the given summary.
	 * 
	 * @return
	 */
	public TodoItem getBySummary(String summary);
}
