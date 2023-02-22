/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.hariharansundaram.shoppingcart;

import java.io.IOException;
import java.util.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author asundaram
 */
@WebServlet(name = "ShoppingController", urlPatterns = {"/store"})
public class ShoppingController extends HttpServlet {

    List<ProductDataModel> allProductList = Arrays.asList(
            new ProductDataModel("101", "Apple Mac Book", "computers"),
            new ProductDataModel("102", "Alienware", "computers"),
            new ProductDataModel("103", "HP Pavillion", "computers"),
            new ProductDataModel("104", "Sony VAIO", "computers"),
            new ProductDataModel("201", "Java Servlet Programming", "books"),
            new ProductDataModel("202", "Oracle Database Programming", "books"),
            new ProductDataModel("203", "System Analysis and Design", "books"),
            new ProductDataModel("204", "Java web services", "books"),
            new ProductDataModel("301", "I'm going to tell you a secret by Madonna", "music"),
            new ProductDataModel("302", "Baby one more time by Britney Spears", "music"),
            new ProductDataModel("303", "Loose by Nelly Furtado", "music"),
            new ProductDataModel("304", "Gold Digger by Kanye West", "music")
    );

    private Map<String, String> credentials = new HashMap<String, String>() {
        {
            put("hari", "hari");
            put("root", "root");
            put("test", "test");
        }
    };

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String authorization = request.getHeader("Authorization");
        if (authorization == null) {
            handleUnauthorized(response);
        } else {
            // Authorization headers looks like "Basic",
            // where blahblah is the base64 encoded username and
            // password. We want the part after "Basic ".
            String userInfo = authorization.substring(6).trim();
            Base64.Decoder decoder = Base64.getDecoder();
            String nameAndPassword = new String(decoder.decode(userInfo));
            // Decoded part looks like "username:password".
            int index = nameAndPassword.indexOf(":");
            String user = nameAndPassword.substring(0, index);
            String password = nameAndPassword.substring(index + 1);
            // High security: username must be reverse of password.
            if (checkCredential(user, password)) {
                // Auth success flow

                if (request.getMethod().equals("GET")) {
                    handleGetRequest(request, response);
                } else if (request.getMethod().equals("POST")) {
                    handlePostRequest(request, response);
                }
            } else {
                // Auth failure flow
                handleUnauthorized(response);
            }
        }
    }

    private void handleGetRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String category = request.getParameter("category");
        if (category != null) {

            if (category.equals("cart")) {
                List<ProductDataModel> pageProductList = fetchProductsWithCategory(category);
                Set<String> cartIdList = (Set<String>) request.getSession().getAttribute("cart");
                if (cartIdList != null) {
                    for (String id : cartIdList) {
                        pageProductList.add(fetchProductWithId(id));
                    }
                }
                request.setAttribute("pageProductList", pageProductList);
                request.setAttribute("action", "remove");
            } else {
                List<ProductDataModel> pageProductList = fetchProductsWithCategory(category);
                request.setAttribute("pageProductList", pageProductList);
                request.setAttribute("action", "add");
            }
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("/JSP/ShoppingMall.jsp");
        dispatcher.forward(request, response);
    }

    private List<ProductDataModel> fetchProductsWithCategory(String category) {
        List<ProductDataModel> list = new ArrayList<>();
        for (ProductDataModel product : allProductList) {
            if (product.category.equals(category)) {
                list.add(product);
            }
        }
        return list;
    }

    private ProductDataModel fetchProductWithId(String id) {
        for (ProductDataModel product : allProductList) {
            if (product.id.equals(id)) {
                return product;
            }
        }
        return null;
    }

    private void handlePostRequest(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String[] itemIds = request.getParameterValues("item");
        String successMessage = "";
        String actionType = request.getParameter("action");

        Set<String> cartList = new HashSet<>();
        if (itemIds == null) {
            return;
        }
        if (request.getSession().getAttribute("cart") != null) {
            cartList = (Set<String>) request.getSession().getAttribute("cart");
        }

        if (actionType.equals("add")) {
            for (String itemId : itemIds) {
                cartList.add(itemId);
            }
            successMessage = "Added item(s) to cart successfully";
        } else if (actionType.equals("remove")) {
            for (String itemId : itemIds) {
                cartList.remove(itemId);
            }
            successMessage = "Removed item(s) from cart successfully";
        }
        request.getSession().setAttribute("cart", cartList);
        request.setAttribute("successMessage", successMessage);
        request.setAttribute("action", "remove");

        RequestDispatcher dispatcher = request.getRequestDispatcher("/JSP/ShoppingCartModifySuccess.jsp");
        dispatcher.forward(request, response);
    }

    private void handleUnauthorized(HttpServletResponse response) {
        response.setStatus(response.SC_UNAUTHORIZED);
        response.setHeader("WWW-Authenticate", "BASIC");
    }

    private boolean checkCredential(String username, String password) {
        username = username.toLowerCase();
        String actualPassword = credentials.getOrDefault(username, "");
        if (actualPassword.isBlank()) {
            return false;
        }
        return actualPassword.equals(password);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
