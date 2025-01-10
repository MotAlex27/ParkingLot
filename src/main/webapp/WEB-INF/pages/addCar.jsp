<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<t:pageTemplate pageTitle="AddCar">
    <h1>Add Car</h1>
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <form action="${pageContext.request.contextPath}/AddCar" method="post">
                    <div class="form-group">
                        <label for="licensePlate">License Plate</label>
                        <input type="text" class="form-control" id="licensePlate" name="licensePlate" value="${licensePlate}" required>
                    </div>
                    <div class="form-group">
                        <label for="brand">Brand</label>
                        <input type="text" class="form-control" id="brand" name="brand" value="${brand}" required>
                    </div>
                    <div class="form-group">
                        <label for="model">Model</label>
                        <input type="text" class="form-control" id="model" name="model" value="${model}" required>
                    </div>
                    <div class="form-group">
                        <label for="color">Color</label>
                        <input type="text" class="form-control" id="color" name="color" value="${color}" required>
                    </div>
                    <div class="form-group">
                        <label for="parkingSpot">Parking Spot</label>
                        <input type="text" class="form-control" id="parkingSpot" name="parkingSpot" value="${parkingSpot}" required>
                    </div>
                    <div class="form-group">
                        <label for="owner_id">Owner</label>
                        <select class="custom-select d-block w-100" id="owner_id" name="owner_id" required>
                            <option value="">Select Owner</option>
                            <c:forEach var="user" items="${users}">
                                <option value="${user.id}">${user.username}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <button type="submit" class="btn btn-primary">Add Car</button>
                </form>
                <c:if test="${not empty error}">
                    <div class="alert alert-danger">${error}</div>
                </c:if>
                <c:if test="${not empty id}">
                    <div class="alert alert-info">id: ${id}</div>
                </c:if>
                <c:if test="${not empty licensePlate}">
                    <div class="alert alert-info">licensePlate: ${licensePlate}</div>
                </c:if>
                <c:if test="${not empty brand}">
                    <div class="alert alert-info">brand: ${brand}</div>
                </c:if>
                <c:if test="${not empty model}">
                    <div class="alert alert-info">model: ${model}</div>
                </c:if>
                <c:if test="${not empty color}">
                    <div class="alert alert-info">color: ${color}</div>
                </c:if>
                <c:if test="${not empty parkingSpot}">
                    <div class="alert alert-info">parkingSpot: ${parkingSpot}</div>
                </c:if>
                <c:if test="${not empty owner_id}">
                    <div class="alert alert-info">owner_id: ${owner_id}</div>
                </c:if>
            </div>
        </div>
    </div>
</t:pageTemplate>