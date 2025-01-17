<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<t:pageTemplate pageTitle="Users">
    <h1>Users</h1>
    <div class="container">
        <!-- Add User Button -->
        <div class="row mb-3">
            <div class="col-md-12">
                <a href="${pageContext.request.contextPath}/AddUser" class="btn btn-primary">Add User</a>
            </div>
        </div>

        <div class="row">
            <div class="col-md-12">
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th>Username</th>
                        <th>Email</th>
                        <th>Age</th>
                        <th>Actions</th>
                    </tr>
                    </thead>
                    <tbody>
                    <!-- Check if users list is empty -->
                    <c:if test="${empty users}">
                        <tr>
                            <td colspan="4">No users found</td>
                        </tr>
                    </c:if>

                    <!-- Loop through the users list -->
                    <c:forEach var="user" items="${users}">
                        <tr>
                            <td>${user.username}</td>
                            <td>${user.email}</td>
                            <td>${user.age}</td>

                            <td>
                                <a href="${pageContext.request.contextPath}/users/edit/${user.username}">Edit</a>
                                <a href="${pageContext.request.contextPath}/users/delete/${user.username}">Delete</a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        <h5>Total Users: ${totalUsers}</h5>
    </div>
</t:pageTemplate>
