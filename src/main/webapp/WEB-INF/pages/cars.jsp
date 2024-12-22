<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:pageTemplate pageTitle="Cars">
    <h1>Cars</h1>
    <form action="${pageContext.request.contextPath}/Cars" method="post">
        <a href="${pageContext.request.contextPath}/AddCar" class="btn btn-primary btn-lg">Add Car</a>
        <button class="btn btn-danger" type="submit">Delete Cars</button>
        <div class="container">
            <form class="row">
                <div class="col-md-12">
                    <table class="table table-striped">
                        <thead>
                        <tr>
                            <th></th>
                            <th>licensePlate</th>
                            <th>Brand</th>
                            <th>Model</th>
                            <th>Color</th>
                            <th>Actions</th>
                        </tr>
                        </thead>
                        <tbody>
                        <!-- Check if cars list is empty -->
                        <c:if test="${empty cars}">
                            <tr>
                                <td colspan="6">No cars found</td>
                            </tr>
                        </c:if>

                        <!-- Loop through the cars list -->
                        <c:forEach var="car" items="${cars}">
                            <tr>
                                <td><input type="checkbox" name="car_ids" value="${car.id}"></td>
                                <td>${car.licensePlate}</td>
                                <td>${car.brand}</td>
                                <td>${car.model}</td>
                                <td>${car.color}</td>

                                <td>
                                    <a class="btn btn-secondary"
                                       href="${pageContext.request.contextPath}/EditCar?id=${car.id}">Edit Car</a>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </form>
        </div>
    </form>
    </div>
    <h5>Free parking spots: ${numberOfFreeParkingSpots}</h5>
    </div>
</t:pageTemplate>
