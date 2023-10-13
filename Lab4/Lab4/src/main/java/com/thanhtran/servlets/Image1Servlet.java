package com.thanhtran.servlets;


import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;

@WebServlet("/images/image1")
public class Image1Servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("image/jpeg");
        ServletOutputStream out;
        out = response.getOutputStream();
        System.out.println(getServletContext().getRealPath("/"));
        FileInputStream fin = new FileInputStream(getServletContext().getRealPath("/WEB-INF/images/cat.jpeg"));
        BufferedInputStream bin = new BufferedInputStream(fin);
        BufferedOutputStream bout = new BufferedOutputStream(out);
        int ch = 0;
        while ((ch = bin.read()) != -1) {
            bout.write(ch);
        }
        bin.close();
        fin.close();
        bout.close();
        out.close();
    }
}