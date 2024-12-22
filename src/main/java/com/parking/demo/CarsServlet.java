package com.parking.demo;

import com.parking.demo.common.CarsDto;
import com.parking.demo.ejb.CarsBean;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "Cars", value = "/Cars")
public class CarsServlet extends HttpServlet {


    @Inject
    CarsBean carsBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse
            response) throws ServletException, IOException {
        List<CarsDto> cars = carsBean.findAllCars();
        request.setAttribute("cars", cars);
        request.setAttribute("numberOfFreeParkingSpots", 10);
        request.getRequestDispatcher("/WEB-INF/pages/cars.jsp").forward(request,
                response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       String[] carIdsAsString = request.getParameterValues("car_id");
        try {
            for (String carIdStr : carIdsAsString) {
                Long carId = Long.parseLong(carIdStr);
                carsBean.deleteCarsById(carId);
            }
            response.sendRedirect(request.getContextPath() + "/cars");
        } catch (NumberFormatException e) {
            // Handle invalid car_id
            request.setAttribute("error", "Invalid car ID.");
            doGet(request, response);
        } catch (Exception e) {
            // Handle general errors
            request.setAttribute("error", "An error occurred while deleting the car.");
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
        } catch (Exception e) {
            // Handle general errors
            request.setAttribute("error", "An error occurred while deleting the car.");
            doGet(request, response);
        }
    }

    @Override

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve parameters
        String licensePlate = request.getParameter("licensePlate");
        String brand = request.getParameter("brand");
        String model = request.getParameter("model");
        String color = request.getParameter("color");
        String parkingSpot = request.getParameter("parkingSpot");
        String carIdStr = request.getParameter("car_id");

        try {
            Long carId = Long.parseLong(carIdStr);

            // Update the car
            carsBean.updateCar(carId, licensePlate, brand, model, color, parkingSpot);

            // Redirect to success page or list of cars
            response.sendRedirect(request.getContextPath() + "/cars");
        } catch (NumberFormatException e) {
            // Handle invalid car_id
            request.setAttribute("error", "Invalid car ID.");
            doGet(request, response);
        } catch (Exception e) {
            // Handle general errors
            request.setAttribute("error", "An error occurred while updating the car.");
            doGet(request, response);
        }
    }


    @Override
    protected void doOptions(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
        response.setHeader("Access-Control-Allow-Origin", "*");
    }
}

