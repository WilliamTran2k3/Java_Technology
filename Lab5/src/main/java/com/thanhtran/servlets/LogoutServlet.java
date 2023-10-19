package com.thanhtran.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/Logout")
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // Xóa cookie
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                cookie.setMaxAge(0); // Đặt thời gian sống của cookie thành 0 để hết hạn
                resp.addCookie(cookie); // Thêm cookie với thời gian sống 0 vào phản hồi để xóa nó trên máy khách
            }
        }
        req.getSession().invalidate(); // Xóa tất cả session attributes và session ID
        resp.sendRedirect("/Lab5/Login");
    }
}
