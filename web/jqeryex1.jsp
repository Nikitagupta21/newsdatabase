<%-- 
    Document   : jqeryex1
    Created on : Feb 28, 2020, 10:17:45 AM
    Author     : nikita
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <jsp:include page="base.jsp"></jsp:include>
        <%--
        if we write jquery in the top of page we have to write ready function compulsory--%>
    <%--    <script>
            $(document).ready(function(){
                $("#b1").click(function(){
                    $("#div1").slideUp(4000);
                });
                $("#b2").click(function(){
                    $("#div1").slideDown(2000);
                });
            });
            
            </script>--%>
    </head>
    <body>
        <input type="button" value="Hide div" id="b1"/>
        <input type="button" value="show div" id="b2"/>
        <input type="button" value="hide/show" id="b3"/>
        <div id="div1" style="background-color: gray; height: 100px">
    </div>
        
        
        
        
      <%-- but if we dont want ready function then we write our jquery ata the last of page above the </body> tag--%>  
        <script>
            
                $("#b1").click(function(){
                    $("#div1").slideUp(2000,function(){ alert("div is hidden now");});
                });
                $("#b2").click(function(){
                    $("#div1").slideDown(2000,function(){ alert("div is shown now");});
                });
                $("#b3").click(function(){
                    $("#div1").slideToggle(2000);
                });
            
            </script>
    </body>
</html>
