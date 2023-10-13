package com.thanhtran.servlets;

import com.thanhtran.models.Response;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = request.getParameter("page");
        RequestDispatcher dispatcher = null;
        if(page != null) {
            switch (page) {
                case "about":
                    dispatcher = request.getRequestDispatcher("/WEB-INF/pages/About.jsp");
                    break;
                case "contact":
                    dispatcher = request.getRequestDispatcher("/WEB-INF/pages/Contact.jsp");
                    break;
                case "help":
                    dispatcher = request.getRequestDispatcher("/WEB-INF/pages/Help.jsp");
                    break;
                default:
                    dispatcher = request.getRequestDispatcher("/WEB-INF/pages/Welcome.jsp");
            }
        } else {
            dispatcher = request.getRequestDispatcher("/WEB-INF/pages/Welcome.jsp");
        }
        dispatcher.forward(request, response);
    }
}
