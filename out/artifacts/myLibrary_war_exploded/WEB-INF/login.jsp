<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login Page</title>
</head>
<body>

<%
    String msg = (String) request.getAttribute("msg");
%>
<%if (msg != null) {%>
<p style="color: red"><%=msg%></p>
<%}%>
<form action="/login" method="post">
    <input type="email" name="email" placeholder="Please input Email"><br>
    <input type="password" name="password" placeholder="Please input Password"><br>
    <input type="submit" value="login">
</form>
</body>
</html>
