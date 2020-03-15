/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.beans.News;
import com.daos.NewsDao;
import com.utility.FileUploader;
import javax.servlet.http.HttpSession;
public class NewsController extends HttpServlet {

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        
        String op = request.getParameter("op");
        if(op!=null && op.equalsIgnoreCase("delete"))
        {
            int id = Integer.parseInt(request.getParameter("id"));
            System.out.println("id:"+id);
            NewsDao ndo = new NewsDao();
            if(ndo.removeById(id))
            {
                response.sendRedirect("reporter/viewNews.jsp");
            }
        }
        
        
    }

     
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        
        String op = request.getParameter("op");
        if(op!=null && op.equalsIgnoreCase("add")){
            HttpSession session = request.getSession();
            News news = (News)session.getAttribute("news");
            String catids[] = (String[]) session.getAttribute("catids");
            NewsDao nd = new NewsDao();
            String imagePath ="";
            imagePath = FileUploader.getUploadedPath(getServletContext(), "media/news", request);
            news.setImage(imagePath);
            if(nd.add(news, catids)){
                session.removeAttribute("news");
                session.removeAttribute("catids");
                out.println("News added Successfully !!");
                response.sendRedirect("reporter/dashboard.jsp");
            }
        }
        if(op!=null && op.equalsIgnoreCase("update")){
            HttpSession session = request.getSession();
            News news = (News)session.getAttribute("news");
            //int id = Integer.parseInt(request.getParameter("id"));
               //System.out.println("id:"+id);
            String catids = (String) session.getAttribute("catids");
             System.out.println("catids:"+catids);
            NewsDao nd = new NewsDao();
              String imagePath ="";
            imagePath = FileUploader.getUploadedPath(getServletContext(), "media/news", request);
               System.out.println("imagepath:"+imagePath);
            if(imagePath.equals("media/news/"))
                news.setImage(news.getImage());
            else
                news.setImage(imagePath);
                
            if(nd.update(news, catids)){
                 session.removeAttribute("news");
                session.removeAttribute("catids");
                out.println("News updated Successfully !!");
                response.sendRedirect("reporter/dashboard.jsp");
            }
        }
        }
    }

   


