<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/login" method="post">
    <label for="emailId">Email:
        <input type="text" name="email" id="emailId">
    </label><br>
    <label for="passwordId">Password:
        <input type="text" name="password" id="passwordId">
    </label><br>
    <button type="submit">Send</button>
</form>
</body>
</html>
