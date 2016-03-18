<!-- Todo: Add select items for status. Add placeholders for messages. -->

<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
	<link rel="stylesheet" href="style.css"/>
	<link rel="stylesheet" href="style-small.css" media="(max-width:480px)"/>
	<title>Add todo item</title>
</head>
<body>

<h1>Add todo item</h1>
<form method="post" 
action="<%= pageContext.getServletContext().getContextPath()%>/addTodoItem.spr">
	<table>
	<tr><td>
	Summary:</td>
	<td>
	<input name="summary" type="text" maxlength="100" id="summary" required/>
	</td></tr>
	
	<tr><td>
	Description:</td>
	<td>
	<textarea name="description" maxlength="500" id="description" draggable="true"></textarea>
	</td></tr>
	
	<!-- tr><td>
	Status:</td>
	<td>
	<input label="status" type="text" id="status"/>
	</td></tr-->
	
	<tr><td>
	Due date and time (expected format is dd-MM-yy hh:mm:ss):</td>
	<td>
	<input name="dueDateTime" type="text" id="dueDateTime"/> 
	</td></tr>
	</table>
	<p><input type="submit" value="Add"/></p><br>
	<a href="<%= pageContext.getServletContext().getContextPath()%>/index.jsp">
Home page</a><br/>
</form>
</body>
</html>
