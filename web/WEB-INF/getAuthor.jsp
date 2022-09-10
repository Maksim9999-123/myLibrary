<%@ page import="java.util.List" %>
<%@ page import="model.Author" %><%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 04.09.2022
  Time: 11:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Authors</title>
    <link rel="stylesheet" type="text/css" href="../style.css">
</head>
<body>
<%
    List<Author> authorList = (List<Author>) request.getAttribute("authors");
%>
<table border="1" class="authorTable">
    <tr class="tableName">
        <th>Pic</th>
        <th>id</th>
        <th>name</th>
        <th>surname</th>
        <th>email</th>
        <th>age</th>
        <th>edit</th>
        <th>delete</th>
    </tr>
    <% for (Author author : authorList) { %>
    <tr>
        <td>
            <%if (author.getProfilePic() == null || author.getProfilePic().length() == 0) {%>
            <img src="C:\Users\PC user\Desktop\JavaPic" width="100"/>
            <%} else {%>
            <img src="/getImage?profilePic.png"><%=author.getProfilePic()%>
            <%}%>
        </td>
        <td><%=author.getId()%>
        </td>
        <td><%=author.getName()%>
        </td>
        <td><%=author.getSurname()%>
        </td>
        <td><%=author.getEmail()%>
        </td>
        <td><%=author.getAge()%>
        </td>
        <td class="editTable"><a href="/author/edit?authorId=<%=author.getId()%>">EDIT</a></td>
        <td class="deleteTable"><a href="/deleteAuthor?authorId=<%=author.getId()%>">DELETE</a></td>
    </tr>

    <% } %>
</table>

</body>
</html>

