package todo.controller;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import todo.Util;

public class DeleteTodoItemController implements Controller {

	private static final Logger lgr = Logger.getLogger(DeleteTodoItemController.class.getName());

	private static final int SIZE = 1024;
	
	public ModelAndView handleRequest(HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		lgr.log(Level.FINEST, "Entry");
		final InputStream inStream = req.getInputStream();
		final ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		final byte[] buffer = new byte[SIZE];
		int numRead = 0;
		while((numRead = inStream.read(buffer)) != -1) {
			outStream.write(buffer,0,numRead);
		}
		final String todoItemId = new String(outStream.toByteArray());
		lgr.log(Level.FINEST, "todo item id: {0}", todoItemId);
		Util.getTodoItemService().delete(Long.parseLong(todoItemId));
		
		lgr.log(Level.FINEST, "Exit");
		return null;
	}
}
