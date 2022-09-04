<%@ page import="java.util.List" %>
<%@ page import="model.Author" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Book</title>
</head>
<body>
<%
    List<Author> authors = (List<Author>) request.getAttribute("authors");

%>
<h1>Add Book</h1>
Please input book data:
<form action="/add/Book" method="post" >
    <input type="text" name="title" placeholder="Please Input Title"/> <br>
    <input type="text" name="description" placeholder="Please Input description"/> <br>
    <input type="number" name="price"/> <br>
    <select name="authorId">
        <%
            for (Author author : authors) {%>
        <option value="<%=author.getId()%>"><%=author.getName()%> <%=author.getSurname()%></option>
        <%}%>
    </select>

    <input type="submit" value="add book">
</form>
</body>
</html>
