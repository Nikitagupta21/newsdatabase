<%--
    Document   : home
    Created on : 19-Feb-2020, 10:54:25
    Author     : Dell
--%>
<%--
<%@page contentType="text/html" pageEncoding="UTF-8" import="java.sql.*;"%>
<jsp:include page="base.jsp"></jsp:include>
    <!DOCTYPE html>
    <html lang="en">

        <head>

            <meta charset="utf-8">
            <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
            <meta name="description" content="">
            <meta name="author" content="">

            <title>GIST News - be updated</title>
            <link rel="icon" href="assets/images/rockets.png" type="image/x-icon"/>
            <!-- Bootstrap core CSS -->
        <jsp:include page="base.jsp"></jsp:include>

            <!-- Custom styles for this template -->
            <link href="assets/css/shop-homepage.css" rel="stylesheet">

        </head>

        <body>

            <!-- Navigation -->
        <jsp:include page="nav.jsp"></jsp:include>

            <!-- Page Content -->
            <div class="container">

                <div class="row">

                <jsp:include page="sidebar.jsp"></jsp:include>
                    <!-- /.col-lg-3 -->

                    <div class="col-lg-9">
                    <jsp:include page="crousel.jsp"></jsp:include>


                        <div class="row" id="livenews">

                        <%
                            int cat_id = request.getParameter("cat_id") != null ? Integer.parseInt(request.getParameter("cat_id")) : 0;

                            Connection con = null;
                            PreparedStatement smt = null;

                            try {
                                Class.forName("com.mysql.jdbc.Driver");
                                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/store", "root", "nikita");

                                String sql = "";
                                if (cat_id == 0) {
                                    sql = "select * from news order by id desc";
                                    smt = con.prepareStatement(sql);
                                } else {
                                    sql = " select * from news where id in (select news_id from newstype where cat_id=?)";
                                    smt = con.prepareStatement(sql);
                                    smt.setInt(1, cat_id);
                                }
                                ResultSet rs = smt.executeQuery();
                                while (rs.next()) {%>

                        <div class="col-lg-4 col-md-6 mb-4">
                            <div class="card h-100">
                                <a href="#"><img class="card-img-top" src="../media/<%=rs.getString("image")%>"  alt=""></a>
                                <div class="card-body">
                                    <h4 class="card-title">
                                        <a href="#"><%=rs.getString("title")%></a>
                                    </h4>

                                    <%
                                        String description = rs.getString("description");
                                        if (description.length() > 100) {
                                            description = description.substring(0, 100);
                                        }
                                    %>

                                    <p class="card-text"> <%=description%> </p>
                                </div>
                                <div class="card-footer">
                                    <small class="text-muted"> <a href="detailnews.jsp?id=<%=rs.getString("id")%>" class="btn btn-primary"> View More </a>
                                    </small>
                                </div>
                            </div>
                        </div>
                        <%}
                            } catch (Exception e) {
                                System.out.println("Error -> " + e.getMessage());
                            } finally {
                                con.close();
                                smt.close();
                            }
                        %>





                    </div>
                    <!-- /.row -->

                </div>
                <!-- /.col-lg-9 -->

            </div>
            <!-- /.row -->

        </div>
        <!-- /.container -->

        <!-- Footer -->
        <jsp:include page="footer.jsp"></jsp:include>

        <!-- Bootstrap core JavaScript -->
        <script src="assets/js/bootstrap.bundle.min.js"></script>

        <script>
            $(document).ready(function () {


                $("#search").click(function () {
                    $("#livenews").html("");
                    $.get("http://newsapi.org/v2/everything?q=" + $("#keyword").val() + "&from=2020-03-03&to=2020-03-03&sortBy=popularity&apiKey=1ddf8e8ce40b46c0b2d99f02215351c9", function (responseText) {
                        news = responseText;
                        articals = news['articles'];
                        for ( i = 0; i < articals.length; i++) {
                            article = '<div class="col-lg-4 col-md-6 mb-4">\
                            <div class="card h-100">\
                                <a href="#"><img class="card-img-top" src="' + articals[i].urlToImage + '"  alt=""></a>\
                                <div class="card-body">\
                                    <h4 class="card-title">\
                                        <a href="#">' + articals[i].title + '</a>\
                                    </h4>\
\
                                    <p class="card-text"> ' + articals[i].description + ' </p>\
                                </div>\
                                <div class="card-footer">\
                                    <small class="text-muted"> <a href="' + articals[i].url + '" class="btn btn-primary"> View More </a>\
                            </small>\
                                </div>\
                            </div>\
                        </div>';
                            $("#livenews").append(article);
                        }


                    });
                });

            });
        </script>


    </body>

</html>

--%>
<%-- 
    Document   : home
    Created on : 17 Feb, 2020, 4:28:12 PM
    Author     : Lenovo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" import="java.sql.*"%>

<!DOCTYPE html>
<html lang="en">

    <head>

        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">

        <title>Gist News Home</title>

        <!-- Bootstrap core CSS -->
        <jsp:include page="base.jsp"></jsp:include>

            <!-- Custom styles for this template -->
            <link href="assets/css/shop-homepage.css" rel="stylesheet">

        </head>

        <body>
        <jsp:include page="nav.jsp"></jsp:include>

            <!-- Page Content -->
            <div class="container">

                <div class="row">

                <jsp:include page="sidebar.jsp"></jsp:include>
                    <!-- /.col-lg-3 -->

                    <div class="col-lg-9">
                    <jsp:include page="crousel.jsp"></jsp:include>
                        <div class="row" id="news_body">
                            <div class="col col-md-12" style="text-align: center;z-index:10;position:fixed" id="loading" >
                                <img src="assets/images/ajax_loading.gif"/>
                            </div>

                        <%
                            String cat_id = request.getParameter("cat_id");
                            if (cat_id == null) {
                                cat_id = "-1";
                            }

                            String sql = "";

                            try {

                                Class.forName("com.mysql.jdbc.Driver");
                                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/store", "root", "nikita");
                                PreparedStatement smt = null;

                                if (cat_id == null || cat_id.equals("") || cat_id.equals("-1")) {
                                    sql = "select * from news";
                                    smt = con.prepareStatement(sql);
                                } else {
                                    sql = "select * from news where id in (select news_id from newstype where cat_id=?)";
                                    smt = con.prepareStatement(sql);
                                    smt.setString(1, cat_id);
                                }

                                ResultSet rs = smt.executeQuery();
                                int i = 1;
                                while (rs.next()) {%>


                        <div class="card col col-md-4" style="width:400px">
                            <%-- <h2><%=i++%></h2>--%>
                            <img class="card-img-top img-thumbnail" src="<%=rs.getString("image")%>" alt="Card image" style="width:100%;height: 200px">
                            <div class="card-body">
                                <h4 class="card-title"> <%=rs.getString("title")%> </h4>
                                <% String news = rs.getString("description").trim();%>
                                <p class="card-text"> <%= news.length() > 100 ? news.substring(0, 100) : news%> ... </p>
                                <%--<button onclick="loadNews('<%=rs.getString("id")%>', newspara);" class="btn btn-primary" data-toggle="modal" data-target="#myModal" > view Detailed news</button>--%>
                                <a href="detailnews.jsp?id=<%=rs.getString("id")%>" class="btn btn-primary" >view Detailed news</a>
                                <br/>
                            </div>
                        </div>

                        <%  System.out.println(rs.getString("title") + "<br/>");
                                }
                                con.close();
                            } catch (Exception e) {
                                System.out.println("Error :" + e.getMessage());
                            }
                        %>




                    </div>
                    <!-- /.row -->

                </div>
                <!-- /.col-lg-9 -->

            </div>
            <!-- /.row -->

        </div>
        <!-- /.container -->

        <!-- Footer -->
        <jsp:include page="footer.jsp"></jsp:include>

        <!-- Bootstrap core JavaScript -->
        <script src="assets/jquery/jquery.min.js"></script>
        <script src="assets/js/bootstrap.bundle.min.js"></script>


        <!-- Consuming Rest api -->

        <script>


            $("#loading").fadeOut(500);


            $(document).ajaxStart(function () {
                $("#loading").fadeIn(500);
            });

            $(document).ajaxComplete(function () {
                $("#loading").fadeOut();
            });

            $('#search').click(function () {
                $.get("https://newsapi.org/v2/everything?q=" + $("#keyword").val() + "&from=2020-03-3&sortBy=popularity&apiKey=951eb8ae5c074f42a7b053d6067525ef", function (data) {
                    news = data;
                    articles = news["articles"]

                    $('#news_body').html('');

                    for (i = 0; i < articles.length; i++)
                    {
                        article = articles[i];
                        result = '<div class="card" class="col col-md-3" style="width:300px;margin:20px;">\
<h3>News-' + (i + 1) + ' </h3>\
<img class="card-img-top" src="' + article.urlToImage + '" alt="Card image" style="width:300px;height:300px;"> \
<div class="card-body">  \
<h4 class="card-title">' + article.title + '</h4> \
<p class="card-text"> ' + article.description + '</p>  \
<a href="' + article.url + '" class="btn btn-primary">View More ...</a>\
</div>\
</div>\
<br/>';
                        $('#news_body').append(result);
                    }
                });
            });

        </script>

    </body>

</html>
