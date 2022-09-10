<%@ page import="model.Author" %>
<%@ page import="model.AuthorRole" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Index</title>
</head>
<body>
<%
    Author author = (Author) session.getAttribute("author");
%>


<a href="/get/author">Get Author</a> |
<%
    if (author != null && author.getAuthorRole() == AuthorRole.ADMIN) {
%>
<a href="/add/Book">Add Book</a> |
<%}%>

<%
    if (author != null) {
%>
<a href="/logout">Logout</a>

<%} else {%>
<a href="/add/Author">Register</a> |
<a href="/login">Login</a> |
<%}%>
<a href="/get/book">Get Book</a>|


</body>
</html>
