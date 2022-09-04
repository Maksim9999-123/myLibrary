<%@ page import="model.Author" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Get Author</title>
</head>
<body>

<%
    List<Author> author = (List<Author>) request.getAttribute("authors");%>

<table border="1">
    <tr>
        <th>id</th>
        <th>name</th>
        <th>surname</th>
        <th>email</th>
        <th>age</th>
        <th>action</th>
    </tr>
    <%
        for (Author authors : author) {
    %>
    <tr>
        <td><%=authors.getId()%>
        </td>
        <td><%=authors.getName()%>
        </td>
        <td><%=authors.getSurname()%>
        </td>
        <td><%=authors.getEmail()%>
        </td>
        <td><%=authors.getAge()%>
        </td>

        <td><a href="/delete/Author?authorId=<%=authors.getId()%>">Delete</a> | <a
                href="/author/edit?authorId<%=authors.getId()%>">Edit</a></td>

    </tr>
    <% }
    %>
</table>
</body>
</html>
