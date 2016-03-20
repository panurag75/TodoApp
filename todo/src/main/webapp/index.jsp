<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="description" content="Application for managing todo items">
<meta name="keywords" content="todo">
<!-- link rel="stylesheet" href="style.css"/-->
<!-- link rel="stylesheet" href="style-small.css" media="(max-width:480px)"/-->
<!-- Bootstrap -->
<link href="bootstrap.min.css" rel="stylesheet">
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="bootstrap.min.js"></script>
<title>To-do Manager Application</title>
</head>
<body>
<h1>To-do Manager Application</h1>
Actions:<br/>
<!-- a href="<%= pageContext.getServletContext().getContextPath()%>/todoItemList.spr" accesskey="1">
View todo items list</a><br/>
<a href="addTodoItem.jsp" accesskey="2">Add todo item</a><br/-->
<input type="button" value="Show list"  
onclick="window.open('<%= pageContext.getServletContext().getContextPath()%>/todoItemList.spr',
'_top',null,null)" accesskey="s" class="btn btn-default"/><br/>
<input type="button" value="Add to-do item" onclick="window.open('addTodoItem.jsp','_top',null,null)"
accesskey="a" class="btn btn-default"/>
</body>
</html>
