<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Купленные билеты</h1>
<h2>
    <ul>
        <c:forEach var="ticket" items="${requestScope.tickets}">
            <li>${ticket.description}</li>
        </c:forEach>
    </ul>
</h2>
</body>
</html>
