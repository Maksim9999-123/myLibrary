<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Book</title>
</head>
<body>
<%
    String msg = (String) request.getAttribute("msg");
%>
<%if (msg != null) {%>
<p style="color: red"><%=msg%></p>
<%}%>

<h1>Add Author</h1>
Please input Author data:
<form action="/add/Author" method="post" enctype="multipart/form-data">
    <input type="text" name="name" placeholder="Please Input Name"/> <br>
    <input type="text" name="surname" placeholder="Please Input Surname"/> <br>
    <input type="email" name="email" placeholder="Please Input Email"/> <br>
    <input type="number" name="age" placeholder="Please Input Age"/> <br>
    <input type="file" name = "profilePic">
    <input type="password" name="password" placeholder="Please Input password"/> <br>
    <select name="author_role">
        <option value="ADMIN">Admin</option>
        <option value="USER">User</option>
    </select>
    <input type="submit" value="add author">
</form>
</body>
</html>
