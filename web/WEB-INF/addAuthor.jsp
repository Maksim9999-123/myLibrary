<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Book</title>
</head>
<body>


<h1>Add Author</h1>
Please input Author data:
<form action="/add/Author" method="post">
    <input type="text" name="name" placeholder="Please Input Name"/> <br>
    <input type="text" name="surname" placeholder="Please Input Surname"/> <br>
    <input type="email" name="email" placeholder="Please Input Email"/> <br>
    <input type="number" name="age" placeholder="Please Input Age"/> <br>
    <input type="submit" value="add author">
</form>
</body>
</html>
