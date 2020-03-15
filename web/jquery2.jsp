<%-- 
    Document   : jquery2
    Created on : Feb 28, 2020, 11:03:55 AM
    Author     : nikita
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <jsp:include page="base.jsp"></jsp:include>
    </head>
    <body>
        <pre>
            <input type="button" value="click me" id="c1"/>
            enter first no:<input type="text" id="t1"/>
            enter second no:<input type="text" id="t2"/>
              <input type="button" value="find sum" id="b1"/>
      
              <span id="result"></span>

        </pre>
        <script>
            $("#b1").click(function(){
                <%-- if we pass parameter in val function it will work like setter function otherwise work like getter function
                ex=$("#t1").val(2000);
                --%>
                a=Number($("#t1").val()) + Number($("#t2").val());
                $("#result").html("<b> SUM = "+ a +"</b>");
            });
            $("input").focus(function(){
                $("#result").html("");
            });
            $("#c1").click(function(){
                $(this).val("you  have clicked me!!!!");
            });
            
            </script>
            
    </body>
</html>
