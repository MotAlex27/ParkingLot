<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:pageTemplate pageTitle="Cars">
    <h1>Cars</h1>
<div class="container">
    <div class="row">
        <div class="col-md-12">
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th>Plate</th>
                        <th>Brand</th>
                        <th>Model</th>
                        <th>Color</th>
                        <th>Owner</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:if test="${empty cars}">
                        <tr>
                            <td colspan="6">No cars found</td>
                        </tr>
                    <c:forEach var="car" items="${cars}">
                        <tr>
                            <td>${car.plate}</td>
                            <td>${car.brand}</td>
                            <td>${car.model}</td>
                            <td>${car.color}</td>
                            <td>${car.owner}</td>
                            <td>
                                <a href="${pageContext.request.contextPath}/cars/edit/${car.plate}">Edit</a>
                                <a href="${pageContext.request.contextPath}/cars/delete/${car.plate}">Delete</a>
                            </td>
                        </tr>
                    </c:forEach>

                </tbody>
            </table>
        </div>
    </div>
  <h5>Free parking spots: ${numberOfFreeParkingSpots}</h5>
</t:pageTemplate>
