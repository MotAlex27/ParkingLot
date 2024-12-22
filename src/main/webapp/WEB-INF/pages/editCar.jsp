<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:pageTemplate pageTitle="editCar">
    <h1>Edit Car</h1>
    <form class="needs-validation" novalidate method="POST" action="${pageContext.request.contextPath}/EditCar">
        <div class="row">
            <div class="col-md-6 mb-3">
                <label class="form-control-label">License Plate</label>
                <input type="text" class="form-control" id="license_plate" placeholder="" name="license_plate"
                       value="${car.licensePlate}" required>
                <div class="invalid-feedback">
                    License Plate is required.
                </div>
            </div>
            <div class="col-md-6 mb-3">
                <label for="parking_spot">Parking Spot</label>
                <input type="text" class="form-control" id="parking_spot" name="parking_spot" placeholder=""
                       value="${car.parkingSpot}" required>
                <div class="invalid-feedback"></div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-6 mb-3">
                <label for="brand">Brand</label>
                <input type="text" class="form-control" id="brand" name="brand" placeholder="" value="${car.brand}"
                       required>
                <div class="invalid-feedback"></div>
            </div>
            <div class="col-md-6 mb-3">
                <label for="model">Model</label>
                <input type="text" class="form-control" id="model" name="model" placeholder="" value="${car.model}"
                       required>
                <div class="invalid-feedback"></div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-6 mb-3">
                <label for="color">Color</label>
                <input type="text" class="form-control" id="color" name="color" placeholder="" value="${car.color}"
                       required>
                <div class="invalid-feedback"></div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-6 mb-3">
                <label for="owner_id">Owner</label>
                <select class="custom-select d-block w-100" id="owner_id" name="owner_id" required>
                    <option value="">Choose...</option>
                    <c:forEach var="user" items="${users}" varStatus="status">
                        <option value="${user.id}" ${car.ownerName eq user.username ? 'selected' : ''}>${user.username}</option>
                    </c:forEach>
                </select>
                <div class="invalid-feedback"></div>
            </div>
        </div>
        <input type="hidden" name="car_id" value="${car.id}"/>
    </form>

    <button class="btn btn-primary" type="submit">Save</button>
    <button class="btn btn-secondary" type="reset">Reset</button>

    <c:if test="${not empty error}">
        <div class="alert alert-danger">${error}</div>
    </c:if>


</t:pageTemplate>
