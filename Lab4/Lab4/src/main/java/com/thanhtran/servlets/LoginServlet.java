package com.thanhtran.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

public class LoginServlet extends HttpServlet {
    private HashMap<String, String> userlist;
    @Override
    public void init() {
        System.out.println("Starting Login Servlet!!!");
        System.out.println("Initializing user list....");
        userlist = new HashMap<>();
        userlist.put("admin", "admin123");
        userlist.put("thanhtran", "abc123");
    }

    @Override
    public void destroy() { System.out.println("Deleting Servlet!!!"); }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        PrintWriter pw = res.getWriter();
        res.setContentType("text/html");
        String user = req.getParameter("username");
        String pass = req.getParameter("password");
        if (userlist.get(user).equals(pass))
            pw.println("Login Success...!");
        else
            pw.println("Login Failed...");
        pw.close();
    }
}
