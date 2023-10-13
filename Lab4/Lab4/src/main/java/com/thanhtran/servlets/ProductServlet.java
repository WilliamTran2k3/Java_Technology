package com.thanhtran.servlets;

import com.google.gson.Gson;
import com.thanhtran.models.Product;
import com.thanhtran.models.Response;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;


@WebServlet("/ProductService/*")
    public class ProductServlet extends HttpServlet {
    private Gson _gson = null;
    private Map<Long, Product> productList;

    @Override
    public void init() {
        this._gson = new Gson();
        this.productList = new HashMap<Long, Product>();
        productList.put(1L, new Product(1L, "Xiaomi", 500.0));
        productList.put(2L, new Product(2L, "Iphone", 2000.0));
        productList.put(3L, new Product(3L, "Galaxy", 700.0));

    }

    private void sendAsJson(HttpServletResponse response, Object obj) throws IOException {
        response.setContentType("application/json");
        String res = _gson.toJson(obj);
        PrintWriter out = response.getWriter();
        out.print(res);
        out.flush();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String key = request.getParameter("id");
        Response responseDTO;
        if (key == null) {
            responseDTO = new Response(0, "All the products", this.productList);
            sendAsJson(response, responseDTO);
            return;
        }
        Long productId = Long.valueOf(key);
        if (!this.productList.containsKey(productId)) {
            responseDTO = new Response(HttpServletResponse.SC_NOT_FOUND, "The product is not found", null);
            sendAsJson(response, responseDTO);
            return;
        }
        responseDTO = new Response(0, "Found the product!", productList.get(productId));
        sendAsJson(response, responseDTO);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Product product = this._gson.fromJson(request.getReader(), Product.class);
        this.productList.put(product.id, product);
        Response responseDTO = new Response(0, "The product is added", product);
        sendAsJson(response, responseDTO);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Product product = this._gson.fromJson(request.getReader(), Product.class);
        Response responseDTO;
        if (product.id == null) {
            responseDTO = new Response(404, "The product is not found!", null);
            sendAsJson(response, responseDTO);
            return;
        }
        this.productList.put(product.id, product);
        responseDTO = new Response(0, "The product is updated", product);
        sendAsJson(response, responseDTO);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Product product = this._gson.fromJson(request.getReader(), Product.class);
        Response responseDTO;
        if (product.id == null || !this.productList.containsKey(product.id)) {
            responseDTO = new Response(404, "The product is not found!", null);
            sendAsJson(response, responseDTO);
            return;
        }
        this.productList.remove(product.id);
        responseDTO = new Response(0, "The product is deleted", null);
        sendAsJson(response, responseDTO);
    }

}
