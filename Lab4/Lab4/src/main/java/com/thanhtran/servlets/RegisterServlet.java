package com.thanhtran.servlets;

import com.thanhtran.models.Info;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/pages/register.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String birthday = request.getParameter("birthday");
        String birthtime = request.getParameter("birthtime");
        String country = request.getParameter("country");
        String toeic = request.getParameter("toeic");
        String message = request.getParameter("message");
        String gender = request.getParameter("gender");
        String[] selectedValues = request.getParameterValues("favorite_ide[]");

        Info t = new Info(name, email, birthday, birthtime, gender, country, selectedValues, Double.parseDouble(toeic), message);

        if(name != null && !name.isEmpty() && email != null && !email.isEmpty() && birthtime != null && !birthtime.isEmpty()
                && birthday != null && !birthday.isEmpty() && country != null && !country.isEmpty()
                && toeic != null && !toeic.isEmpty() && gender != null && !gender.isEmpty()
                && message != null && !message.isEmpty() && selectedValues != null && selectedValues.length > 0
        ) {
            Double toeicParam = Double.parseDouble(toeic);
            Info inf = new Info(name, email, birthday, birthtime, gender, country, selectedValues, toeicParam, message);

            request.setAttribute("info", inf);
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/pages/result.jsp");
            dispatcher.forward(request, response);

        } else {
            response.getWriter().println("Error");
        }


    }
}
