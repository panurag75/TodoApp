package todo.servlet;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import todo.TodoItem;
import todo.dao.TodoItemDAORemote;

/**
 * Servlet implementation class TodoServlet
 */
@WebServlet("/TodoServlet")
public class TodoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final int SIZE = 1024;
       
	private static final Logger lgr = Logger.getLogger(TodoServlet.class.getName());
	
	@EJB
	private TodoItemDAORemote todoItemDAORemote;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public TodoServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		final String requestURI = request.getRequestURI();
		lgr.log(Level.FINEST, "RequestURI={0}", requestURI);
		final int lastIndex = requestURI.lastIndexOf('/');
		lgr.log(Level.FINEST, "lastIndex ={0}", lastIndex);
		final String action = requestURI.substring(lastIndex+1);
		lgr.log(Level.FINEST, "action={0}", action);
		switch(action) {
		case "addTodoItem": 
			try {
				addTodoItem(request, response);
			} catch (ParseException e) {
				throw new ServletException("ParseException", e);
			}
			break;
		case "deleteTodoItem":
			deleteTodoItem(request, response);
			break;
		default: break;
		}
	}

	private void deleteTodoItem(HttpServletRequest request, HttpServletResponse response) 
			throws IOException {
		final InputStream inStream = request.getInputStream();
		final ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		final byte[] buffer = new byte[SIZE];
		int numRead = 0;
		while((numRead = inStream.read(buffer)) != -1) {
			outStream.write(buffer,0,numRead);
		}
		final String todoItemId = new String(outStream.toByteArray());
		lgr.log(Level.FINEST, "todo item id: {0}", todoItemId);
		todoItemDAORemote.delete(Long.parseLong(todoItemId));
	}

	private void addTodoItem(HttpServletRequest request, 
			HttpServletResponse response) throws IOException, ParseException, ServletException {
		final TodoItem ti = new TodoItem();
		ti.setSummary(request.getParameter("summary"));
		ti.setDescription(request.getParameter("description"));
		ti.setCreatedDate(new Date());
		final String dueDateTime = request.getParameter("dueDateTime");
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
		todoItemDAORemote.create(ti);
		
		// setting response
		final String responseHTML = 
			"<!DOCTYPE html>"
			+ "<html><head>"
			+ "<meta charset=\"ISO-8859-1\">"
			+ "<link rel=\"stylesheet\" href=\"style.css\"/>"
			+ "<title>Add todo item result</title>"
			+ "</head>"
			+ "<body>"
			+ "Todo item is added.<br>"
			+ "<a href=\"" 
			+ request.getServletContext().getContextPath() 
			+ "/index.jsp\">"
			+ "Home page</a><br/></body></html>";

		response.getOutputStream().write(responseHTML.getBytes());
	}
}
