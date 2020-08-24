package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.beans.Admin;
import com.daos.AdminDao;
import com.beans.Reporter;
import com.daos.ReporterDao;
import java.util.Date;

public final class login_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html lang=\"en\">\n");
      out.write("\n");
      out.write("<head>\n");
      out.write("\n");
      out.write("  <meta charset=\"utf-8\">\n");
      out.write("  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\">\n");
      out.write("  <meta name=\"description\" content=\"\">\n");
      out.write("  <meta name=\"author\" content=\"\">\n");
      out.write("\n");
      out.write("  <title>contact Information</title>\n");
      out.write("  <link rel=\"icon\" href=\"assets/images/favicon.ico\" type=\"x/image\"/>\n");
      out.write("  <!-- Bootstrap core CSS -->\n");
      out.write("  ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "base.jsp", out, false);
      out.write("\n");
      out.write("\n");
      out.write("  <!-- Custom styles for this template -->\n");
      out.write("  <link href=\"assets/css/shop-homepage.css\" rel=\"stylesheet\">\n");
      out.write("  <style>\n");
      out.write("      body{\n");
      out.write("    background: -webkit-linear-gradient(left, #0072ff, #00c6ff);\n");
      out.write("}\n");
      out.write(".contact-form{\n");
      out.write("    background: #fff;\n");
      out.write("    margin-top: 10%;\n");
      out.write("    margin-bottom: 5%;\n");
      out.write("    width: 70%;\n");
      out.write("}\n");
      out.write(".contact-form .form-control{\n");
      out.write("    border-radius:1rem;\n");
      out.write("}\n");
      out.write(".contact-image{\n");
      out.write("    text-align: center;\n");
      out.write("}\n");
      out.write(".contact-image img{\n");
      out.write("    border-radius: 6rem;\n");
      out.write("    width: 11%;\n");
      out.write("    margin-top: -3%;\n");
      out.write("    transform: rotate(0deg);\n");
      out.write("}\n");
      out.write(".contact-form form{\n");
      out.write("    padding: 14%;\n");
      out.write("}\n");
      out.write(".contact-form form .row{\n");
      out.write("    margin-bottom: -7%;\n");
      out.write("}\n");
      out.write(".contact-form h3{\n");
      out.write("    margin-bottom: 8%;\n");
      out.write("    margin-top: -10%;\n");
      out.write("    text-align: center;\n");
      out.write("    color: #0062cc;\n");
      out.write("}\n");
      out.write(".contact-form .btnContact {\n");
      out.write("    width: 50%;\n");
      out.write("    border: none;\n");
      out.write("    border-radius: 1rem;\n");
      out.write("    padding: 1.5%;\n");
      out.write("    background: #dc3545;\n");
      out.write("    font-weight: 600;\n");
      out.write("    color: #fff;\n");
      out.write("    cursor: pointer;\n");
      out.write("}\n");
      out.write(".btnContactSubmit\n");
      out.write("{\n");
      out.write("    width: 50%;\n");
      out.write("    border-radius: 1rem;\n");
      out.write("    padding: 1.5%;\n");
      out.write("    color: #fff;\n");
      out.write("    background-color: #0062cc;\n");
      out.write("    border: none;\n");
      out.write("    cursor: pointer;\n");
      out.write("}\n");
      out.write("  </style>\n");
      out.write("</head>\n");
      out.write("\n");
      out.write("<body>\n");

          //Reading cookie 
           Cookie cookies[] = request.getCookies();
           String userid="",password="";
           
           for(Cookie c : cookies){
               if(c.getName().equals("userid"))
               {
                   userid=c.getValue();
               }
               if (c.getName().equals("password"))
               {
                   password = c.getValue();
               }
           }
       
      out.write("    \n");
      out.write("    \n");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "nav.jsp", out, false);
      out.write("\n");
      out.write("  \n");
      out.write("  <!-- Page Content -->\n");
      out.write("  <div class=\"container\">\n");
      out.write("\n");
      out.write("    <div class=\"row\">\n");
      out.write("\n");
      out.write("   ");
      out.write("\n");
      out.write("      <!-- /.col-lg-3 -->\n");
      out.write("\n");
      out.write("      <div class=\"col-lg-12\">\n");
      out.write("       <div class=\"row\">\n");
      out.write("       \n");
      out.write("        \n");
      out.write("<div class=\"container contact-form\">\n");
      out.write("            <div class=\"contact-image\">\n");
      out.write("                <img src=\"assets/images/login.png\" alt=\"rocket_contact\"/>\n");
      out.write("            </div>\n");
      out.write("     <p>\n");
      out.write("      <span style=\"color:red; font-family: corble; font-size: 16px\">\n");
      out.write("        ");
      out.print(request.getParameter("msg")!=null?request.getParameter("msg"):"");
      out.write("\n");
      out.write("        </span>\n");
      out.write("    </p> \n");
      out.write("      <form class=\"form-signin\"  method=\"post\">\n");
      out.write("   <h1 class=\"h3 mb-3 font-weight-normal\">Please sign in</h1>\n");
      out.write("   <input type=\"text\" id=\"inputEmail\" class=\"form-control\" placeholder=\"userid\" value=\"");
      out.print(userid);
      out.write("\"  autocomplete=\"off\" required autofocus name=\"userid\">\n");
      out.write("   <br/><br/>\n");
      out.write("   <input type=\"password\" id=\"inputPassword\" class=\"form-control\" placeholder=\"Password\"  value=\"");
      out.print(password);
      out.write("\" name=\"password\" required>\n");
      out.write("  <br/>\n");
      out.write("  <div style=\"border-style: solid; border-radius: 10px; border-width:1px\"> \n");
      out.write("      <h6>Select Role </h6> \n");
      out.write("      <input type=\"radio\" name=\"role\" id=\"role1\" value=\"admin\"/>  <label for=\"role1\"><b>Admin </b></label>\n");
      out.write("      <input type=\"radio\" name=\"role\" id=\"role2\" value=\"reporter\"/> <label for=\"role2\"><b>Reporter</b></label> \n");
      out.write("  </div>\n");
      out.write("  <br/>\n");
      out.write("  \n");
      out.write("  <div class=\"checkbox mb-3\">\n");
      out.write("    <label>\n");
      out.write("      <input type=\"checkbox\" name=\"remember\" value=\"remember\"> Remember me\n");
      out.write("    </label>\n");
      out.write("  </div>\n");
      out.write("  <button class=\"btn btn-lg btn-primary btn-block\" type=\"submit\" name=\"submit\">Sign in</button>\n");
      out.write(" </form>\n");
      out.write("\n");
      out.write("</div>   \n");
      out.write("           \n");
      out.write("       </div>\n");
      out.write("     \n");
      out.write("      </div>\n");
      out.write("   \n");
      out.write("  </div>\n");
      out.write("    <!-- /.row -->\n");
      out.write("\n");
      out.write("  </div>\n");
      out.write("  <!-- /.container -->\n");
      out.write("\n");
      out.write("  <!-- Footer -->\n");
      out.write("      ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "footer.jsp", out, false);
      out.write("\n");
      out.write("\n");
      out.write("  <!-- Bootstrap core JavaScript -->\n");
      out.write("  <script src=\"assets/jquery/jquery.min.js\"></script>\n");
      out.write("  <script src=\"assets/js/bootstrap.bundle.min.js\"></script>\n");
      out.write("\n");
      out.write("  \n");
      out.write("  ");
if(request.getParameter("submit")!=null){
           userid= request.getParameter("userid");
           password = request.getParameter("password");
           String role = request.getParameter("role");
           
        if(role==null){
            response.sendRedirect("login.jsp?msg=Please Select Any One Role");
            //return;
        }
        else if(role.equals("admin")){
               AdminDao ad = new AdminDao();
               Admin admin = ad.getByLoginDetail(userid, password);
               if(admin==null)
                   response.sendRedirect("login.jsp?msg=Invalid Userid or password");
               else {
                   session.setAttribute("admin", admin);
                   response.sendRedirect("admin/addReporter.jsp");
               }
           }
    else if (role.equals("reporter")){
           ReporterDao rd = new ReporterDao();
           int id = rd.isValid(userid, password);
           if(id!=-1)
           {
               Reporter reporter = rd.getById(id);
               session.setAttribute("reporter", reporter);
               response.sendRedirect("reporter/dashboard.jsp");
           }
           else 
               response.sendRedirect("login.jsp?msg=Invalid Userid or password"); 
           }
           
           }
      
      out.write("\n");
      out.write("  \n");
      out.write("  \n");
      out.write("</body>\n");
      out.write("\n");
      out.write("</html>\n");
      out.write("\n");
      out.write("\n");
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
