<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<t:pageTemplate pageTitle="Cars">
    <h1>Cars</h1>
    <a href="${pageContext.request.contextPath}/AddCar" class="btn btn-primary btn-lg">Add Car</a>
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <table class="table table-striped">
                    <thead>
                    <tr>
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
                            <td>${car.licensePlate}</td>
                            <td>${car.brand}</td>
                            <td>${car.model}</td>
                            <td>${car.color}</td>

                            <td>
                                <a href="${pageContext.request.contextPath}/cars/edit/${car.licensePlate}">Edit</a>
                                <a href="${pageContext.request.contextPath}/cars/delete/${car.licensePlate}">Delete</a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        <h5>Free parking spots: ${numberOfFreeParkingSpots}</h5>
    </div>
</t:pageTemplate>
