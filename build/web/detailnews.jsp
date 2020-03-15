<%-- 
    Document   : detailnews
    Created on : Feb 16, 2020, 5:01:48 PM
    Author     : Arpit mishra
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" import="java.sql.*"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>News Details</title>
        <jsp:include page="base.jsp"></jsp:include>
        <style>
img {
  display: block;
  margin-left: auto;
  margin-right: auto;
}
</style>

    </head>
    <body>
        <%
    int id = request.getParameter("id") != null ? Integer.parseInt(request.getParameter("id")) : 0;
    try{
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/store","root","nikita");
        String sql = "select * from news where id=?";
        PreparedStatement smt = con.prepareStatement(sql);
        smt.setInt(1, id);
        ResultSet rs = smt.executeQuery();
        if(rs.next())
        {%>
                
<form class="form" method="post" enctype="multipart/form-data">
    <input type="hidden" name="id" value="<%=id%>"/>    
    <h2>Title</h2>    <div class="container">
        <b class="text-center text-primary"><%=rs.getString("title")%></b>
    </div><br/>
    <img class="card-img-top" src="<%=rs.getString("image")%>" alt="Card image" style="width:30%">
    </br>
    
    <h2>Description</h2> 
    <div class="container">
               <%=rs.getString("description")%> 
    </div>
<br/><br/>
<a href="<%=request.getHeader("referer")%>" class="btn btn-primary">Back </a>
 
</form>
        
    <%}
        
    }catch(Exception e){
        System.out.println("Error : "+ e.getMessage());
    }
    %>

    </body>
</html>
