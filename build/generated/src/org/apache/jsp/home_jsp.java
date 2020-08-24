package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.sql.*;

public final class home_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write('\n');
      out.write('\n');
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html lang=\"en\">\n");
      out.write("\n");
      out.write("    <head>\n");
      out.write("\n");
      out.write("        <meta charset=\"utf-8\">\n");
      out.write("        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\">\n");
      out.write("        <meta name=\"description\" content=\"\">\n");
      out.write("        <meta name=\"author\" content=\"\">\n");
      out.write("\n");
      out.write("        <title>Gist News Home</title>\n");
      out.write("\n");
      out.write("        <!-- Bootstrap core CSS -->\n");
      out.write("        ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "base.jsp", out, false);
      out.write("\n");
      out.write("\n");
      out.write("            <!-- Custom styles for this template -->\n");
      out.write("            <link href=\"assets/css/shop-homepage.css\" rel=\"stylesheet\">\n");
      out.write("\n");
      out.write("        </head>\n");
      out.write("\n");
      out.write("        <body>\n");
      out.write("        ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "nav.jsp", out, false);
      out.write("\n");
      out.write("\n");
      out.write("            <!-- Page Content -->\n");
      out.write("            <div class=\"container\">\n");
      out.write("\n");
      out.write("                <div class=\"row\">\n");
      out.write("\n");
      out.write("                ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "sidebar.jsp", out, false);
      out.write("\n");
      out.write("                    <!-- /.col-lg-3 -->\n");
      out.write("\n");
      out.write("                    <div class=\"col-lg-9\">\n");
      out.write("                    ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "crousel.jsp", out, false);
      out.write("\n");
      out.write("                        <div class=\"row\" id=\"news_body\">\n");
      out.write("                            <div class=\"col col-md-12\" style=\"text-align: center;z-index:10;position:fixed\" id=\"loading\" >\n");
      out.write("                                <img src=\"assets/images/ajax_loading.gif\"/>\n");
      out.write("                            </div>\n");
      out.write("\n");
      out.write("                        ");

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
                                while (rs.next()) {
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("                        <div class=\"card col col-md-4\" style=\"width:400px\">\n");
      out.write("                            ");
      out.write("\n");
      out.write("                            <img class=\"card-img-top img-thumbnail\" src=\"");
      out.print(rs.getString("image"));
      out.write("\" alt=\"Card image\" style=\"width:100%;height: 200px\">\n");
      out.write("                            <div class=\"card-body\">\n");
      out.write("                                <h4 class=\"card-title\"> ");
      out.print(rs.getString("title"));
      out.write(" </h4>\n");
      out.write("                                ");
 String news = rs.getString("description").trim();
      out.write("\n");
      out.write("                                <p class=\"card-text\"> ");
      out.print( news.length() > 100 ? news.substring(0, 100) : news);
      out.write(" ... </p>\n");
      out.write("                                ");
      out.write("\n");
      out.write("                                <a href=\"detailnews.jsp?id=");
      out.print(rs.getString("id"));
      out.write("\" class=\"btn btn-primary\" >view Detailed news</a>\n");
      out.write("                                <br/>\n");
      out.write("                            </div>\n");
      out.write("                        </div>\n");
      out.write("\n");
      out.write("                        ");
  System.out.println(rs.getString("title") + "<br/>");
                                }
                                con.close();
                            } catch (Exception e) {
                                System.out.println("Error :" + e.getMessage());
                            }
                        
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("                    </div>\n");
      out.write("                    <!-- /.row -->\n");
      out.write("\n");
      out.write("                </div>\n");
      out.write("                <!-- /.col-lg-9 -->\n");
      out.write("\n");
      out.write("            </div>\n");
      out.write("            <!-- /.row -->\n");
      out.write("\n");
      out.write("        </div>\n");
      out.write("        <!-- /.container -->\n");
      out.write("\n");
      out.write("        <!-- Footer -->\n");
      out.write("        ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "footer.jsp", out, false);
      out.write("\n");
      out.write("\n");
      out.write("        <!-- Bootstrap core JavaScript -->\n");
      out.write("        <script src=\"assets/jquery/jquery.min.js\"></script>\n");
      out.write("        <script src=\"assets/js/bootstrap.bundle.min.js\"></script>\n");
      out.write("\n");
      out.write("\n");
      out.write("        <!-- Consuming Rest api -->\n");
      out.write("\n");
      out.write("        <script>\n");
      out.write("\n");
      out.write("\n");
      out.write("            $(\"#loading\").fadeOut(500);\n");
      out.write("\n");
      out.write("\n");
      out.write("            $(document).ajaxStart(function () {\n");
      out.write("                $(\"#loading\").fadeIn(500);\n");
      out.write("            });\n");
      out.write("\n");
      out.write("            $(document).ajaxComplete(function () {\n");
      out.write("                $(\"#loading\").fadeOut();\n");
      out.write("            });\n");
      out.write("\n");
      out.write("            $('#search').click(function () {\n");
      out.write("                $.get(\"https://newsapi.org/v2/everything?q=\" + $(\"#keyword\").val() + \"&from=2020-03-3&sortBy=popularity&apiKey=951eb8ae5c074f42a7b053d6067525ef\", function (data) {\n");
      out.write("                    news = data;\n");
      out.write("                    articles = news[\"articles\"]\n");
      out.write("\n");
      out.write("                    $('#news_body').html('');\n");
      out.write("\n");
      out.write("                    for (i = 0; i < articles.length; i++)\n");
      out.write("                    {\n");
      out.write("                        article = articles[i];\n");
      out.write("                        result = '<div class=\"card\" class=\"col col-md-3\" style=\"width:300px;margin:20px;\">\\\n");
      out.write("<h3>News-' + (i + 1) + ' </h3>\\\n");
      out.write("<img class=\"card-img-top\" src=\"' + article.urlToImage + '\" alt=\"Card image\" style=\"width:300px;height:300px;\"> \\\n");
      out.write("<div class=\"card-body\">  \\\n");
      out.write("<h4 class=\"card-title\">' + article.title + '</h4> \\\n");
      out.write("<p class=\"card-text\"> ' + article.description + '</p>  \\\n");
      out.write("<a href=\"' + article.url + '\" class=\"btn btn-primary\">View More ...</a>\\\n");
      out.write("</div>\\\n");
      out.write("</div>\\\n");
      out.write("<br/>';\n");
      out.write("                        $('#news_body').append(result);\n");
      out.write("                    }\n");
      out.write("                });\n");
      out.write("            });\n");
      out.write("\n");
      out.write("        </script>\n");
      out.write("\n");
      out.write("    </body>\n");
      out.write("\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
