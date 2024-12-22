package com.parking.demo;

import com.parking.demo.common.UserDto;
import com.parking.demo.ejb.CarsBean;
import com.parking.demo.ejb.UserBean;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "AddCar", value = "/AddCar")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AddCar extends HttpServlet {

    @Inject
    UserBean userBean;
    @Inject
    CarsBean carsBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse
            response) throws ServletException, IOException {
        List<UserDto> users = userBean.findAllUsers();
        request.setAttribute("users", users);
        request.getRequestDispatcher("/WEB-INF/pages/addCar.jsp").forward(request,
                response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve parameters
        Long id = Long.parseLong(request.getParameter("id"));
        String licensePlate = request.getParameter("licensePlate");
        String brand = request.getParameter("brand");
        String model = request.getParameter("model");
        String color = request.getParameter("color");
        String parkingSpot = request.getParameter("parkingSpot");
        String ownerIdStr = request.getParameter("owner_id");

        try {
            Long ownerId = Long.parseLong(ownerIdStr);

            // Create the car
            carsBean.createCar(id, licensePlate, brand, model, color, parkingSpot, ownerId);

            // Redirect to success page or list of cars
            response.sendRedirect(request.getContextPath() + "/cars");

        } catch (NumberFormatException e) {
            // Handle invalid owner_id
            request.setAttribute("error", "Invalid owner ID.");
            doGet(request, response);
        } catch (Exception e) {
            // Handle general errors
            request.setAttribute("error", "An error occurred while adding the car.");
            doGet(request, response);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String carIdStr = request.getParameter("car_id");
        try {
            Long carId = Long.parseLong(carIdStr);
            carsBean.deleteCarsById(carId);
            response.sendRedirect(request.getContextPath() + "/cars");
        } catch (NumberFormatException e) {
            // Handle invalid car_id
            request.setAttribute("error", "Invalid car ID.");
            doGet(request, response);
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String carIdStr = request.getParameter("car_id");
        String licensePlate = request.getParameter("licensePlate");
        String brand = request.getParameter("brand");
        String model = request.getParameter("model");
        String color = request.getParameter("color");
        String parkingSpot = request.getParameter("parkingSpot");
        try {
            Long carId = Long.parseLong(carIdStr);
            carsBean.updateCar(carId, licensePlate, brand, model, color, parkingSpot);
            response.sendRedirect(request.getContextPath() + "/cars");
        } catch (NumberFormatException e) {
            // Handle invalid car_id
            request.setAttribute("error", "Invalid car ID.");
            doGet(request, response);
        }
    }

    @Override
    protected void doOptions(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, DELETE");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
    }
}
