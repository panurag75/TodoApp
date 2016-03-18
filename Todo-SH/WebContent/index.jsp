<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta charset="ISO-8859-1"></meta>
<link rel="stylesheet" href="style.css"/>
<link rel="stylesheet" href="style-small.css" media="(max-width:480px)"/>
<title>Todo Manager Application</title>
</head>
<body>
<h1>Todo Manager Application</h1>
Actions:<br/>
<!-- a href="<%= pageContext.getServletContext().getContextPath()%>/todoItemList.spr" accesskey="1">
View todo items list</a><br/>
<a href="addTodoItem.jsp" accesskey="2">Add todo item</a><br/-->
<input type="button" value="Show list"  
onclick="window.open('<%= pageContext.getServletContext().getContextPath()%>/todoItemList.spr',
'_top',null,null)" accesskey="s"/><br/>
<input type="button" value="Add todo item" onclick="window.open('addTodoItem.jsp','_top',null,null)"
accesskey="a"/>
</body>
</html>
