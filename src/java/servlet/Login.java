/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import Application.bean.FuncionariosBean;
import Application.dao.DaoFuncionarios;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Marcos Lima marcoslima82@gmail.com
 */
public class Login extends HttpServlet {

    private DaoFuncionarios dao;
    
    public void init(){
        dao = new DaoFuncionarios();
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
        
            String option = request.getParameter("option");

            if (option.equalsIgnoreCase("validUser")) {
                validUser(request, response);
            }else if (option.equalsIgnoreCase("insert")) {
                insertRecord(request, response);
            }else if (option.equalsIgnoreCase("listar")) {
                listRecord(request, out);
        }
            
        
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

    private void validUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String login = request.getParameter("txLogin");
        String password = request.getParameter("txPassword");

        boolean isValidUser;

        if (login == null || password == null) {
            isValidUser = false;
        } else {
            isValidUser = validUserMethod(login, password);
        }
        
        if (isValidUser){
            request.getSession().setAttribute("user", login);
            response.sendRedirect("ponto.jsp");
        }else{
            request.getSession().setAttribute("msg", "InvalidUser/Password");
            request.getSession().setAttribute("link", "index.html");
            response.sendRedirect("alert.jsp");
        }
    }

    private void deleteRecord(HttpServletRequest request, HttpServletResponse response) {
        
    }

    private boolean validUserMethod(String login, String password) {
        try {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            String url = "jdbc:derby://localhost:1527/db";
            String user = "root";
            String pass = "root";

            try {
                Connection con = DriverManager.getConnection(url, user, pass);

                PreparedStatement pstmt = con.prepareStatement("SELECT * FROM FUNCIONARIOS WHERE USUARIO = ? AND SENHA = ?");

                pstmt.setString(1, login);
                pstmt.setString(2, password);

                ResultSet rs = pstmt.executeQuery();

                if (rs.next()) {
                    return true;
                } else {
                    return false;
                }

            } catch (SQLException ex) {
                System.out.println("Error: " + ex.getMessage());
                return false;
            }
            } catch (ClassNotFoundException ex) {
            System.out.println("Error: " + ex.getMessage());
            return false;
        }

    }
    private void insertRecord(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
        String name = request.getParameter("txUsuario");
        String tipo = request.getParameter("txTipo");
        String data = request.getParameter("txData");
        
        FuncionariosBean funcionario = new FuncionariosBean();
        funcionario.setNome(name);
        funcionario.setBatida_tipo(tipo);
        funcionario.setHora(data);
        
        boolean isOk = dao.insert(funcionario);
        if (isOk){
            request.getSession().setAttribute("msg", "Insert Success");
            request.getSession().setAttribute("link", "menu.jsp");
        } else {
            request.getSession().setAttribute("msg", "Insert Error");
            request.getSession().setAttribute("link", "menu.jsp");
            response.sendRedirect("alert.jsp");
        }      
    }

    private void listRecord(HttpServletRequest request, PrintWriter out) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        ArrayList records = dao.list();
        
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Servlet ManageDBServlet</title>");            
        out.println("</head>");
        out.println("<body>");
        for(int i = 0;i < records.size();i++){
            FuncionariosBean funcionario = (FuncionariosBean) records.get(i);
            
            out.println(funcionario.getNome() + " " + funcionario.getBatida_tipo() + " " + 
                    funcionario.getHora() + "<hr>");
        }
        out.println("<br/><br/><br/><hr>");
        out.println("<a href=\"insert.jsp\"> Inserir </a><br/>");
        out.println("<a href=\"update.jsp\"> Atualizar </a><br/>");
        out.println("<a href=\"delete.jsp\"> Apagar </a><br/>");
        out.println("<a href=\"ManageDBServlet?action=list\"> Listar </a><br/>");
        out.println("</body>");
        out.println("</html>");
    }

}
