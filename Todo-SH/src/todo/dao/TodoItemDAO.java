package todo.dao;

import java.util.List;

import todo.TodoItem;

public interface TodoItemDAO {

	TodoItem create(TodoItem todoItem);

	TodoItem update(TodoItem todoItem);

	TodoItem get(long id);

	void delete(long id);

	List<TodoItem> list(int start, int max);

	int getCount();

	List<TodoItem> listActive(int start, int max);

	/**
	 * This method returns a TodoItem that has the given summary.
	 * 
	 * @return
	 */
	TodoItem getBySummary(String summary);

}