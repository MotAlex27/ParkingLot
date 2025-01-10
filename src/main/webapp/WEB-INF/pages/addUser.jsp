<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:pageTemplate pageTitle="Add User">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Add User</title>
</head>
<body>
<h1>Add New User</h1>
<form method="post" action="${pageContext.request.contextPath}/AddUser">
    <label for="username">Username:</label>
    <input type="text" id="username" name="username" required><br>

    <label for="email">Email:</label>
    <input type="email" id="email" name="email" required><br>

    <label for="password">Password:</label>
    <input type="password" id="password" name="password" required><br>

    <label>Roles:</label><br>
    <c:forEach var="role" items="${userGroups}">
        <input type="checkbox" name="user_groups" value="${role}"> ${role}<br>
    </c:forEach>

    <button type="submit">Create User</button>
</form>
</body>
</html>

</t:pageTemplate>