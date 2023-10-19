package com.thanhtran.servlets;

import com.thanhtran.dao.UserDAO;
import com.thanhtran.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "Register", value = "/Register")
public class RegisterServlet extends HttpServlet  {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("username") != null) {
            response.sendRedirect("/Lab5/Product");
        } else {
            request.getRequestDispatcher("/jsp/register.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username").trim();
        String email = request.getParameter("email").trim();
        String password = request.getParameter("password").trim();
        String confirmPassword = request.getParameter("password_confirm").trim();

        if (username.equals("") || email.equals("") || password.equals("") || confirmPassword.equals("")) {
            request.setAttribute("error", "Please fill all fields");
            request.getRequestDispatcher("/jsp/register.jsp").forward(request, response);
        } else if (!password.equals(confirmPassword)) {
            request.setAttribute("error", "Password and confirm password must be the same");
            request.getRequestDispatcher("/jsp/register.jsp").forward(request, response);
        } else {
            User userSave = new User(username, email, password);
            boolean isSuccess = UserDAO.register(userSave);
            if (!isSuccess) {
                request.setAttribute("error", "Username or email already exists");
                request.getRequestDispatcher("/jsp/register.jsp").forward(request, response);
            } else {
//               set session
                request.getSession().setAttribute("username", username);
                request.setAttribute("username", username);
                response.sendRedirect("/Lab5/Product");
            }
        }
    }
}
