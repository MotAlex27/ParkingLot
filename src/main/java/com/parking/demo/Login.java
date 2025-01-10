package com.parking.demo;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "Login", value = "/Login")
public class Login extends HttpServlet {

    private static final Logger logger = Logger.getLogger(Login.class.getName());

    @Override
    public void init() throws ServletException {
        super.init();
        logger.info("Login servlet initialized");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("Processing GET request for Login page");

        try {
            request.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(request, response);
            logger.info("Forwarded to login.jsp");
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error forwarding to login.jsp", e);
            throw e;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        logger.info("Received login request for username: " + username);

        try {
            // Log the hashed password for debugging (only if applicable)
            logger.info("Attempting login with username: " + username);

            // Perform login
            request.login(username, password);
            logger.info("Login successful for username: " + username);

            // Redirect to the dashboard
            response.sendRedirect(request.getContextPath() + "/dashboard");
        } catch (ServletException e) {
            logger.warning("Login failed for username: " + username);
            logger.log(Level.WARNING, "Error details:", e);

            // Forward back to the login page with error message
            request.setAttribute("message", "Invalid username or password");
            request.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(request, response);
        }
    }


    @Override
    public void destroy() {
        logger.info("Login servlet destroyed");
        super.destroy();
    }
}
