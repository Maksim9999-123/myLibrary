<%@ page import="model.Author" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Book</title>
</head>
<body>
<%Author author = (Author) request.getAttribute("author");%>

<h1>Edit Author</h1>
Please input Author data:
<form action="/author/edit" method="post">
    <input type="hidden" name="authorId" value="<%=author.getId()%>">
    <input type="text" name="name" value="<%=author.getName()%>"/> <br>
    <input type="text" name="surname" value="<%=author.getSurname()%>"/> <br>
    <input type="email" name="email" value="<%=author.getEmail()%>"/> <br>
    <input type="number" name="age" value="<%=author.getAge()%>"/> <br>
    <input type="submit" value="add author">
</form>
</body>
</html>
