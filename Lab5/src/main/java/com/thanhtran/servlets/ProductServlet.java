package com.thanhtran.servlets;

import com.thanhtran.dao.ProductDAO;
import com.thanhtran.model.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

import java.io.IOException;

@WebServlet(name = "Product", value = "/Product")
public class ProductServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        /product?action=del&id=1
        if (request.getSession().getAttribute("username") == null) {
            response.sendRedirect("/Lab5/Login");
        } else {
            String action = request.getParameter("action");
            String id = request.getParameter("id");
            if (action != null && action.equals("del") && id != null) {
                Product product = ProductDAO.findById(Long.parseLong(id));
                if (product != null) {
                    ProductDAO.delete(product);
                    request.setAttribute("message", "Delete product successfully");
                    request.setAttribute("type", "success");
                } else {
                    request.setAttribute("message", "Cannot delete product");
                    request.setAttribute("type", "danger");
                }
            }

            List<Product> products = ProductDAO.findAll();
            request.setAttribute("products", products);
            request.getRequestDispatcher("/jsp/index.jsp").forward(request, response);
        }


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name").trim();
        String price = request.getParameter("price").trim();

        Product product = ProductDAO.save(new Product(name, Double.parseDouble(price)));
        if (product == null) {
            request.setAttribute("message", "Cannot add product");
            request.setAttribute("type", "danger");
        } else {
            request.setAttribute("message", "Add product successfully");
            request.setAttribute("type", "success");
        }

        List<Product> products = ProductDAO.findAll();
        request.setAttribute("products", products);
        request.getRequestDispatcher("/jsp/index.jsp").forward(request, response);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }
}
