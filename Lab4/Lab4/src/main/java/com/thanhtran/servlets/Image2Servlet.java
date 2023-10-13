package com.thanhtran.servlets;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@WebServlet("/images/image2")
public class Image2Servlet extends HttpServlet {
    private final int ARBITARY_SIZE = 1848;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("image/jpeg");
        response.setHeader("Content-disposition", "attachment; filename=image2.jpg");

        try (InputStream in = request.getServletContext().getResourceAsStream("/WEB-INF/images/dog.jpeg");
             OutputStream out = response.getOutputStream()) {
            byte[] buffer = new byte[ARBITARY_SIZE];
            int numBytesRead;
            while ((numBytesRead = in.read(buffer)) > 0) {
                out.write(buffer, 0, numBytesRead);
            }
        }
    }
}