<!-- Todo: Add select items for status. Add placehoders for messages. -->
<%@page import="todo.*, java.util.List" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:h="http://java.sun.com/jsf/html"
      xmlns:f="http://xmlns.jcp.org/jsf/core"
      xmlns:p="http://primefaces.org/ui">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"></meta>
	<link rel="stylesheet" href="style.css"/>
	<link rel="stylesheet" href="style-small.css" media="(max-width:480px)"/>
	<title>Active todo item list</title>
	<script>
		function confirmDelete(todoItemId) {
			console.log("in confirmDelete");
			if(confirm("The todo item will be deleted. Please confirm.")) {
				// send XHR request
				var xhr = new XMLHttpRequest();
				xhr.onreadystatechange = function() {
					console.log("in onreadystatechange " + xhr.readyState);
					if(xhr.readyState == 4 && xhr.status == 200) {
						console.log(xhr.responseText);
						alert("Todo item deleted. The page will be refreshed automtically.");
						window.open("<%= pageContext.getServletContext().getContextPath()%>/todoItemList.spr",
								"_top", null, null);
					}
				}

				xhr.open("POST", 
					"<%= pageContext.getServletContext().getContextPath()%>/deleteTodoItem.spr");
				xhr.send(todoItemId);
			}
		}
	</script>
</head>
<body>

<h1>Active todo items list</h1>
<form>
	<table border=1>
		<thead>
			<tr>
				<td>Summary</td>
				<td>Description</td>
				<td>Actions</td>
			</tr>
		</thead>
	<% 	final List<TodoItem> list = (List<TodoItem>)request.getAttribute("todoItemList");
		for(final TodoItem ti : list) { %>
		<tr>
			<td><%= ti.getSummary() %></td>
			<td><%= ti.getDescription() %></td>
			<td><input type="button" name=<%= "button" + ti.getId() %> 
			value="Delete" onclick="confirmDelete(<%= ti.getId() %>)"></td>
		</tr>
	<% } %>
	</table><br/>
	<a href="<%= pageContext.getServletContext().getContextPath()%>/index.jsp" accesskey="h">
Home page</a><br/>
</form>
</body>
</html>
