package todo.controller;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import todo.TodoItem;
import todo.Util;

public class TodoItemListController implements Controller {

	private static final Logger lgr = Logger.getLogger(TodoItemListController.class.getName());
	
	public ModelAndView handleRequest(HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		lgr.log(Level.FINEST, "Entry");
		final List<TodoItem> list = Util.getTodoItemService().listActive(0, 20);
		req.setAttribute("todoItemList", list);
		lgr.log(Level.FINEST, "Exit. Returning /todoItemList.jsp");
		return new ModelAndView("/todoItemList.jsp");
	}
}
