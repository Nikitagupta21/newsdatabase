/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controllers;
import com.beans.Person;
import com.daos.PersonDao;
import com.utility.FileUploader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Base64;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author nikita
 */
public class PersonControllers extends HttpServlet {

   
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out=response.getWriter();
        response.setContentType("text/html");
        String op=request.getParameter("op");
        if(op!=null && op.equalsIgnoreCase("delete"))
        {
            int id=Integer.parseInt(request.getParameter("id"));
            PersonDao pd=new PersonDao();
            if(pd.removeById(id))
            {
                response.sendRedirect("show.jsp");
            }
        }
        if (op != null && op.equalsIgnoreCase("check_userid")) {
            String userid = request.getParameter("userid");
            PersonDao pd = new PersonDao();
            if (userid == null || userid.equals("")) {
                out.println("<font size='4'>Please provide Userid</font>");
                return;
            }
            else if (pd.isUseridExist(userid)) {
                out.println("<font color='red' size='4'>Sorry This Userid already Exist!</font>");
            } else {
                out.println("<font color='blue' size='4'>Congrats! this userid is available!!</font>");
            }
        }

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

        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        String op = request.getParameter("op");

        if (op != null && op.equalsIgnoreCase("add")) {
            System.out.println("Request Found......");

            try {
                //check the enctype of the incomming request -
                boolean isMultipart = ServletFileUpload.isMultipartContent(request);
                String encodedPassword = "";
                String photo = "", imagePath = "";
                if (isMultipart) {
                    imagePath = FileUploader.getUploadedPath(getServletContext(), "media/person", request);
                  /*  System.out.println("Multipart data found.......");
                    FileItemFactory factory = new DiskFileItemFactory();
                    ServletFileUpload upload = new ServletFileUpload(factory);
                    List items = null;
                    items = upload.parseRequest(request);

                    Iterator itr = items.iterator();
                    while (itr.hasNext()) {
                        FileItem item = (FileItem) itr.next();
                        if (!item.isFormField()) {
                            photo = item.getName();
                            imagePath = "media/person/" + photo;
                            File savedFile = new File(getServletContext().getRealPath("/") + imagePath);
                            item.write(savedFile);

                        }

                    }*/
                }  
//JDBC Code 
                    PersonDao pd = new PersonDao();
                    HttpSession session = request.getSession();
                    Person person = (Person) session.getAttribute("person");
                    person.setImage(imagePath);

                    encodedPassword = Base64.getEncoder().encodeToString(person.getPassword().getBytes("UTF-8"));
                    person.setPassword(encodedPassword);

                    if (pd.add(person)) {
                        session.removeAttribute("person");
                        response.sendRedirect("show.jsp");
                    }

                

            } catch (Exception e) {
                System.out.println("Error : " + e.getMessage());
            }

        }

    }
        
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    


