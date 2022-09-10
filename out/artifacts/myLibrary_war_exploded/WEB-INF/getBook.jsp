<%@ page import="model.Book" %>
<%@ page import="java.util.List" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Get Book</title>
</head>
<body>
<%
    List<Book> book = (List<Book>) request.getAttribute("books");%>

<table border="1">
    <tr>
        <th>BookPic</th>
        <th>id</th>
        <th>title</th>
        <th>description</th>
        <th>price</th>
        <th>action</th>
    </tr>
    <%
        for (Book books : book) {
    %>
    <tr>
        <td>
            <%=books.getBookPic()%>
        </td>
        <td><%=books.getId()%>
        </td>
        <td><%=books.getTitle()%>
        </td>
        <td><%=books.getDescription()%>
        </td>
        <td><%=books.getPrice()%>
        </td>
        <td><%=books.getAuthor().getId()%>
        </td>


        <td><a href="/delete/Book?bookId=<%=books.getId()%>">Delete</a> |
            <a   href="/book/edit?bookId=<%=books.getId()%>">Edit</a>
        </td>
    </tr>
    <% }
    %>
</table>
</body>
</html>
