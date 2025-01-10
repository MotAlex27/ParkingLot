package com.parking.demo;

import com.parking.demo.ejb.UserBean;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Arrays;

@WebServlet(name = "AddUser", value = "/AddUser")
public class AddUserServlet extends HttpServlet {

    @Inject
    private UserBean userBean;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Set available user groups for the form
        request.setAttribute("userGroups", new String[]{"READ_CARS", "WRITE_CARS", "READ_USERS", "WRITE_USERS"});
        request.getRequestDispatcher("/WEB-INF/pages/addUser.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve form parameters
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String[] userGroups = request.getParameterValues("user_groups");

        if (userGroups == null) {
            userGroups = new String[0]; // Handle case where no roles are selected
        }

        // Call the createUser method in UserBean
        userBean.createUser(username, email, password, Arrays.asList(userGroups));

        // Redirect to the user listing page
        response.sendRedirect(request.getContextPath() + "/User");
    }
}
