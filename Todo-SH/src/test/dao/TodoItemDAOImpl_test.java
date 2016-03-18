package test.dao;

import java.util.List;

import java.util.Map;
import java.util.Set;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;

import todo.Status;
import todo.TodoItem;
import todo.dao.TodoItemDAO;

public class TodoItemDAOImpl_test implements TodoItemDAO {

	private Map<Long, TodoItem> map = new LinkedHashMap();
	private long id = 1;
	
	@Override
	public synchronized TodoItem create(TodoItem todoItem) {
		todoItem.setId(id);
		map.put(id, todoItem);
		id++;
		return todoItem;
	}

	@Override
	public synchronized TodoItem update(TodoItem todoItem) {
		if(map.containsKey(todoItem.getId())) {
			map.put(todoItem.getId(), todoItem);
			return todoItem;
		}
		else {
			throw new RuntimeException("Element does not exist");
		}
	}

	@Override
	public synchronized TodoItem get(long id) {
		return map.get(id);
	}

	@Override
	public synchronized void delete(long id) {
		if(map.containsKey(id)) {
			map.remove(id);
		}
		else {
			throw new RuntimeException("Element does not exist");
		}
	}

	@Override
	public synchronized List<TodoItem> list(int start, int max) {
		final int size = getCount();
		if(start > size || size < 0) {
			throw new IllegalArgumentException("start is invalid");
		}
		if(max > size || max < 0) {
			throw new IllegalArgumentException("max is invalid");
		}

		final Set<Long> keySet = map.keySet();
		Long[] arKey = new Long[size];
		arKey = keySet.toArray(arKey);
		final List<TodoItem> list = new ArrayList();
		for(int i=start; i < max; i++) {
			list.add(map.get(arKey[i]));
		}
		return list;
	}

	@Override
	public synchronized int getCount() {
		return map.size();
	}

	@Override
	public synchronized List<TodoItem> listActive(int start, int max) {
		int size = getCount();
		if(start < 0) {
			throw new IllegalArgumentException("start is invalid");
		}
		if(max < 0) {
			throw new IllegalArgumentException("max is invalid");
		}

		if(start > max) return null;
		
		// get all Active items
		final Set<Long> keySet = map.keySet();
		Long[] arKey = new Long[size];
		arKey = keySet.toArray(arKey);
		List<TodoItem> list = new ArrayList();
		for(int i=0; i < size; i++) {
			TodoItem todoItem = map.get(arKey[i]);
			Status status = todoItem.getStatus();
			if(status == null || !status.getName().equals("Completed")) {
				list.add(todoItem);
			}
		}

		// get the required sublist
		size = list.size();
		if(start > size) return null;
		list = list.subList(start, max > size? size : max);
		return list;
	}

	@Override
	public synchronized TodoItem getBySummary(final String summary) {
		if(summary != null) {
			final Collection<TodoItem> values = map.values();
			final Iterator<TodoItem> iterator = values.iterator(); 
			while(iterator.hasNext()) {
				final TodoItem todoItem = iterator.next();
				if(todoItem.getSummary().equals(summary)) return todoItem;
			}
		}
		return null;
	}
}
