/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.hariharansundaram.moviestore;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;

/**
 *
 * @author asundaram
 */
@WebServlet(name = "MovieController", urlPatterns = {"/movie.do"})
public class MovieController extends HttpServlet {

    private String username = "root";
    private String password = "test@123";

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
        if (request.getMethod().equals("GET")) {
            handleGetRequests(request, response);
        } else if (request.getMethod().equals("POST")) {
            handlePostRequests(request, response);
        }
    }

    private void handleGetRequests(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String actionType = (String) request.getParameter("action");
        if (actionType == null) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/JSP/MovieLanding.jsp");
            dispatcher.forward(request, response);
        } else if (actionType.equals("add")) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/JSP/MovieAdd.jsp");
            dispatcher.forward(request, response);
        } else if (actionType.equals("browse")) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/JSP/MovieSearch.jsp");
            dispatcher.forward(request, response);
        } else if (actionType.equals("search")) {
            handleSearch(request, response);
        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/JSP/MovieLanding.jsp");
            dispatcher.forward(request, response);
        }
    }

    private void handleSearch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String searchTerm = (String) request.getParameter("searchTerm");
        String searchType = (String) request.getParameter("searchType");
        ArrayList<MovieDataModel> movieList = new ArrayList<>();
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/moviedb", username, password);    
            Statement st = con.createStatement();
            ResultSet cursor = st.executeQuery("SELECT * FROM movies WHERE "+searchType+" LIKE '"+searchTerm+"%'");
            while(cursor.next()) {
                movieList.add(new MovieDataModel(
                        cursor.getString("title"),
                        cursor.getString("actor"),
                        cursor.getString("actress"),
                        cursor.getString("genre"),
                        cursor.getInt("year")
                ));
            }
            cursor.close();
            st.close();
            con.close();
        } catch (Exception ex) {
            System.out.println(ex.getLocalizedMessage());
        }
        
        request.setAttribute("movieList", movieList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/JSP/MovieDisplay.jsp");
        dispatcher.forward(request, response);
    }

    private void handlePostRequests(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //TODO: Add data to db
        insertDataIntoDatabase(request);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/JSP/MovieLanding.jsp");
        dispatcher.forward(request, response);
    }

    private void insertDataIntoDatabase(HttpServletRequest request) throws ServletException, IOException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/moviedb", username, password);
            PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO movies VALUES(?, ?, ?, ?, ?)");
            String title = (String) request.getParameter("title");
            String actor = (String) request.getParameter("actor");
            String actress = (String) request.getParameter("actress");
            String genre = (String) request.getParameter("genre");
            Integer year = Integer.valueOf(request.getParameter("year"));

            preparedStatement.setString(1, title);
            preparedStatement.setString(2, actor);
            preparedStatement.setString(3, actress);
            preparedStatement.setString(4, genre);
            preparedStatement.setInt(5, year);
            preparedStatement.executeUpdate();
            
            preparedStatement.close();
            con.close();
        } catch (Exception ex) {
            System.out.println(ex.getLocalizedMessage());
        }
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
