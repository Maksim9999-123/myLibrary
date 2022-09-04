<%@ page import="model.Book" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Book</title>
</head>
<body>
<%Book book = (Book) request.getAttribute("book");%>

<h1>Edit Book</h1>
Please input Book data:
<form action="/book/edit" method="post">
    <input type="hidden" name="bookId" value="<%=book.getId()%>">
    <input type="text" name="title" value="<%=book.getTitle()%>"/> <br>
    <input type="text" name="description" value="<%=book.getDescription()%>"/> <br>
    <input type="number" name="price" value="<%=book.getPrice()%>"/> <br>
    <input type="submit" value="add book">
</form>
</body>
</html>