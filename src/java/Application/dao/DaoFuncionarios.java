/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Application.dao;

import Application.bean.FuncionariosBean;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Marcos Lima marcoslima82@gmail.com
 */
public class DaoFuncionarios {
    private Connection con;
    private PreparedStatement pstmtInsert;
    private PreparedStatement pstmtRecords;
    private ResultSet rsRecords;
        
    public DaoFuncionarios(){
        
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            String url = "jdbc:derby://localhost:1527/db";
            String user = "root";
            String pass = "root";
        
            con = DriverManager.getConnection(url, user, pass);
        
            pstmtInsert = con.prepareStatement("INSERT INTO PONTO VALUES ( ?, ?, ? )");
            pstmtRecords = con.prepareStatement("SELECT * FROM PONTO");
        
            rsRecords = pstmtRecords.executeQuery();
            
        } catch (ClassNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    public FuncionariosBean first () {
        try {
            rsRecords.first();
            
            FuncionariosBean funcionario = new FuncionariosBean();
            
            funcionario.setNome(rsRecords.getString("USUARIO"));
            funcionario.setBatida_tipo(rsRecords.getString("BATIDA"));
            funcionario.setHora(rsRecords.getString("HORA"));
            
            return funcionario;
            
        } catch (SQLException e){
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }
    
    public FuncionariosBean previous() {
        try {
            if (!rsRecords.isFirst()){
                rsRecords.previous();
            }
            
            FuncionariosBean funcionario = new FuncionariosBean();
            
            funcionario.setNome(rsRecords.getString("USUARIO"));
            funcionario.setBatida_tipo(rsRecords.getString("BATIDA"));
            funcionario.setHora(rsRecords.getString("HORA"));
            
            return funcionario;
            
        } catch (SQLException e){
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }
    
    public FuncionariosBean next() {
        try {
            if (!rsRecords.isLast()){
                rsRecords.next();
            }
            
            FuncionariosBean funcionario = new FuncionariosBean();
            
            funcionario.setNome(rsRecords.getString("USUARIO"));
            funcionario.setBatida_tipo(rsRecords.getString("BATIDA"));
            funcionario.setHora(rsRecords.getString("HORA"));
            
            return funcionario;
            
        } catch (SQLException e){
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }
    
    public FuncionariosBean last() {
        try {
            rsRecords.last();
            
            FuncionariosBean funcionario = new FuncionariosBean();
            
            funcionario.setNome(rsRecords.getString("USUARIO"));
            funcionario.setBatida_tipo(rsRecords.getString("BATIDA"));
            funcionario.setHora(rsRecords.getString("HORA"));
            
            return funcionario;
            
        } catch (SQLException e){
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }
    public boolean insert(FuncionariosBean funcionario){
        try {
            pstmtInsert.setString(1, funcionario.getNome());
            pstmtInsert.setString(2, funcionario.getBatida_tipo());
            pstmtInsert.setString(3, funcionario.getHora());
                    
            pstmtInsert.executeUpdate();
            
            rsRecords = pstmtRecords.executeQuery();
            
            return true;
        } catch (SQLException e){
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }
    public ArrayList list() {
        try {
            ArrayList listReturn = new ArrayList();
            while (rsRecords.next()){
                FuncionariosBean student = new FuncionariosBean();
                student.setNome(rsRecords.getString("USUARIO"));
                student.setBatida_tipo(rsRecords.getString("BATIDA"));
                student.setHora(rsRecords.getString("HORA"));
                listReturn.add(student);
            }
            return listReturn;
            } catch (SQLException ex) {
                    Logger.getLogger(DaoFuncionarios.class.getName()).log(Level.SEVERE, null, ex);
            }
        return null;
    }
}
