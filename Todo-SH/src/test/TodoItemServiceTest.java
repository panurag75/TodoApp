/**
 * 
 */
package test;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import test.dao.TodoItemDAOImpl_test;
import todo.Status;
import todo.TodoItem;
import todo.dao.TodoItemDAO;
import todo.service.TodoItemService;

/**
 * @author anu
 *
 */
public class TodoItemServiceTest {

	private TodoItemDAO todoItemDAO = new TodoItemDAOImpl_test();
	private TodoItemService service = new TodoItemService();

	private Date now, dueDate;
	private Status status_completed, status_new;

	public TodoItemServiceTest() {
		service.setTodoItemDAO(todoItemDAO);

		final Calendar cal = Calendar.getInstance();
		now = cal.getTime();
		cal.add(Calendar.DAY_OF_MONTH, 1);
		dueDate = cal.getTime();
		
		status_completed = new Status();
		status_completed.setName("Completed");
		status_completed.setStatusId(2);//id is hardcoded for now
		
		status_new = new Status();
		status_new.setName("New");
		status_new.setStatusId(1);//id is hardcoded for now
	}
	/**
	 * Test method for {@link todo.service.TodoItemService#create(todo.TodoItem)}.
	 */
	@Test
	public void testCreate() {
		final TodoItem ti = new TodoItem();
		ti.setSummary("test todoitem 1");
		ti.setDescription("description of test todoitem 1");
		ti.setCreatedDate(now);
		ti.setDueDateTime(dueDate);
		service.create(ti);
	}

//	/**
//	 * Test method for {@link todo.service.TodoItemService#update(todo.TodoItem)}.
//	 */
//	@Test
//	public void testUpdate() {
//		fail("Not yet implemented"); // TODO
//	}

//	/**
//	 * Test method for {@link todo.service.TodoItemService#get(long)}.
//	 */
//	@Test
//	public void testGet() {
//		fail("Not yet implemented"); // TODO
//	}

	/**
	 * Test method for {@link todo.service.TodoItemService#delete(long)}.
	 */
	@Test
	public void testDelete() {
		/*
		 Procedure:
		 Create a todo item. Get the object representing persisted data & 
		 call delete method to delete it.
		 */
		TodoItem ti6 = new TodoItem();
		final String summary6 = "test todoitem 6";
		ti6.setSummary(summary6);
		ti6.setDescription("description of test todoitem 6");
		ti6.setCreatedDate(now);
		ti6.setDueDateTime(dueDate);
		ti6.setStatus(status_completed);
		service.create(ti6);
		
		// get the object representing persisted data
		ti6 = service.getBySummary(summary6);
		
		// call delete method
		service.delete(ti6.getId());
	}

//	/**
//	 * Test method for {@link todo.service.TodoItemService#getCount()}.
//	 */
//	@Test
//	public void testGetCount() {
//		fail("Not yet implemented"); // TODO
//	}

	/**
	 * Test method for {@link todo.service.TodoItemService#listActive(int, int)}.
	 */
	@Test
	public void testListActive() {
		/*
		 Procedure:
		 # Create some items, with status including New, Completed.
		 # Call getActive.
		 # Verify that all items with status Completed are not returned.
		 All other items should be returned.
		 */
		// create two items with status New.
		TodoItem ti2 = new TodoItem();
		final String summary2 = "test todoitem 2";
		ti2.setSummary(summary2);
		ti2.setDescription("description of test todoitem 2");
		ti2.setCreatedDate(now);
		ti2.setDueDateTime(dueDate);
		ti2.setStatus(status_completed);
		service.create(ti2);

		TodoItem ti3 = new TodoItem();
		final String summary3 = "test todoitem 3";
		ti3.setSummary(summary3);
		ti3.setDescription("description of test todoitem 3");
		ti3.setCreatedDate(now);
		ti3.setDueDateTime(dueDate);
		ti3.setStatus(status_completed);
		service.create(ti3);

		// create 1 item with status Completed
		TodoItem ti4 = new TodoItem();
		final String summary4 = "test todoitem 4";
		ti4.setSummary(summary4);
		ti4.setDescription("description of test todoitem 4");
		ti4.setCreatedDate(now);
		ti4.setDueDateTime(dueDate);
		ti4.setStatus(status_new);
		service.create(ti4);

		// call getActive
		final List<TodoItem> listActive = service.listActive(0, 100);
		System.out.println("Returned list: " + listActive.toString());

		// To compare objects, have to get the corresponding objects from the
		// persistence layer, because in the returned objects, the id is also
		// set, so equals method would return false. The objects have to be
		// got by summary. It should be ensured before testing that there
		// would be unique row for a value of summary.

		ti2 = service.getBySummary(summary2);
		ti3 = service.getBySummary(summary3);
		
		if(listActive.contains(ti2) || listActive.contains(ti3)) {
			System.err.println(
					"Test failed. Todoitem with status Completed is/are present.");
		}
		
		ti4 = service.getBySummary(summary4);
		if (!listActive.contains(ti4)) {
			System.err.println(
					"Test failed. Todoitem with status New is not present.");
		}
	}

	/**
	 * Test method for {@link todo.service.TodoItemService#getBySummary(java.lang.String)}.
	 */
	@Test
	public void testGetBySummary() {
		TodoItem ti5 = new TodoItem();
		final String summary5 = "test todoitem 5";
		ti5.setSummary(summary5);
		final String desc5 = "description of test todoitem 5";
		ti5.setDescription(desc5);
		ti5.setCreatedDate(now);
		ti5.setDueDateTime(dueDate);
		service.create(ti5);

		ti5 = service.getBySummary(summary5);
		if(!ti5.getSummary().equals(summary5)) {
			System.err.println(
					"Test failed. getBySummary returned invalid object. Summary did not match.");
		}
		if(!ti5.getDescription().equals(desc5)) {
			System.err.println(
					"Test failed. getBySummary returned invalid object. Description did not match.");
		}
	}
}
