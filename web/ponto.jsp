<%-- 
    Document   : ponto
    Created on : 20/06/2018, 12:56:56
    Author     : Marcos Lima marcoslima82@gmail.com
--%>

<%@ page import = "java.io.*,java.util.*" %>
<%@ page import = "javax.servlet.*,java.text.*" %>

<%
    String nomeU = (String) request.getSession().getAttribute("user");
    Date dNow = new Date( );
    SimpleDateFormat ft = new SimpleDateFormat ("dd/MM/yyyy hh:mm:ss");
    String hrs = ft.format(dNow);
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
        <title>Ponto</title>
    </head>
    <body>

        <div class="container">
            <div class="row">
                <div class="col-sm-4">
                    <h3>Registro de ponto</h3>
                    <form action="/GamaSistemas/ControllerServlet?option=insert" method="post">
                        <div class="form-group">
                            <label for="nomeUsuario">Usuario: </label><br>
                            <input type="text" name="txUsuario" placeholder="<%=nomeU%>" disabled><br/>
                            <small id="emailHelp" class="form-text text-muted">Nunca compartilhe seu usuário.</small>
                        </div>

                        <div class="form-group">  
                            <label for="exampleFormControlSelect1">Registrar</label>
                            <select class="form-control" id="exampleFormControlSelect1" name="txTipo">
                                <option>Selecionar tipo...</option>
                                <option>Entrada</option>
                                <option>Saída Almoço</option>
                                <option>Retorno Almoço</option>
                                <option>Saída</option>      
                            </select>
                        </div>

                        <div class="form-group">
                            
                            <label>Data/Hora </label>
                            <input type="text" name="txData" placeholder="<%=hrs%>" disabled><br/>
                        </div>
                        <input type="submit" value="Registrar"/>
                        <input type="reset" value="Cancelar"/>
                    </form>
                    <a href="home.jsp">Voltar</a>
                </div>
            </div>
        </div>
    </body>
</html>