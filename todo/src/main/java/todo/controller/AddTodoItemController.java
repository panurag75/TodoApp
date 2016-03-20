package todo.controller;

import org.springframework.web.servlet.*;
import org.springframework.web.servlet.mvc.Controller;

import todo.TodoItem;
import todo.Util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.*;

public class AddTodoItemController implements Controller {

	private static final Logger lgr = Logger.getLogger(AddTodoItemController.class.getName());
	
	public ModelAndView handleRequest(HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		lgr.log(Level.FINEST, "Entry");
		final TodoItem ti = new TodoItem();
		ti.setSummary(req.getParameter("summary"));
		ti.setDescription(req.getParameter("description"));
		ti.setCreatedDate(new Date());
		final String dueDateTime = req.getParameter("dueDateTime");
		if(dueDateTime != null) {
			final DateFormat df = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
			final Date d = df.parse(dueDateTime);
			ti.setDueDateTime(d);
		}
		else {
			lgr.log(Level.WARNING, "Due date time is null");
//			lgr.log(Level.FINEST, "Parameter map: {0}", 
//					request.getParameterMap());
		}
		Util.getTodoItemService().create(ti);
		
		lgr.log(Level.FINEST, "Exit. Returning /addTodoItemResult.jsp");
		return new ModelAndView("/addTodoItemResult.jsp");
	}
}
