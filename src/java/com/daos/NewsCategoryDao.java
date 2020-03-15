package com.daos;

import com.beans.NewsCategory;
import com.jdbc.ConnectionPool;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class NewsCategoryDao {

    public boolean add(NewsCategory newsCategory) {
        boolean status = false;
        ConnectionPool cp = ConnectionPool.getInstance();
        cp.initialize();
        Connection con = cp.getConnection();

        if (con != null) {
            try {
                String sql = "Insert into category(name) values(?)";
                PreparedStatement smt = con.prepareStatement(sql);
                smt.setString(1, newsCategory.getName());
                smt.executeUpdate();

                status = true;
                cp.putConnection(con);
                smt.close();

            } catch (Exception e) {
                System.out.println("Error " + e.getMessage());
            }

        }
        return status;
    }

    public boolean removeById(int cat_id) {
        boolean status = false;
        try {
            ConnectionPool cp = ConnectionPool.getInstance();
            cp.initialize();
            Connection con = cp.getConnection();
            if (con != null) {
                String sql = "Delete from category where cat_id=?";
                PreparedStatement smt = con.prepareStatement(sql);
                smt.setInt(1, cat_id);

                int n = smt.executeUpdate();
                if (n > 0) {
                    status = true;
                    System.out.println("Category Removed !!");
                }

                cp.putConnection(con);
                smt.close();

            }

        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
        }

        return status;
    }

    public NewsCategory getById(int cat_id) {
        NewsCategory newsCategory = null;
        try {
            ConnectionPool cp = ConnectionPool.getInstance();
            cp.initialize();
            Connection con = cp.getConnection();
            if (con != null) {
                String sql = "select * from category where cat_id=?";
                PreparedStatement smt = con.prepareStatement(sql);
                smt.setInt(1, cat_id);
                ResultSet rs = smt.executeQuery();
                if (rs.next()) {
                    newsCategory = new NewsCategory();
                    newsCategory.setCat_id(rs.getInt("cat_id"));
                    newsCategory.setName(rs.getString("name"));
                }
                cp.putConnection(con);
                smt.close();
            }
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
        }

        return newsCategory;
    }

    public ArrayList<NewsCategory> getAllNewsCategory() {
        ArrayList<NewsCategory> newsCategoryList = new ArrayList();
        try {
            ConnectionPool cp = ConnectionPool.getInstance();
            cp.initialize();
            Connection con = cp.getConnection();
            if (con != null) {
                String sql = "select * from category";
                PreparedStatement smt = con.prepareStatement(sql);

                ResultSet rs = smt.executeQuery();
                while (rs.next()) {
                    NewsCategory newsCategory = new NewsCategory();
                    newsCategory.setCat_id(rs.getInt("cat_id"));
                    newsCategory.setName(rs.getString("name"));
                    newsCategoryList.add(newsCategory);
                }
                cp.putConnection(con);
                smt.close();
            }
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
        }

        return newsCategoryList;
    }

    public ArrayList<NewsCategory> getNewsCategoryByLimit(int start, int stop) {
        ArrayList<NewsCategory> newsCategoryList = new ArrayList();
        try {
            ConnectionPool cp = ConnectionPool.getInstance();
            cp.initialize();
            Connection con = cp.getConnection();
            if (con != null) {
                String sql = "select * from category limit ?, ?";
                PreparedStatement smt = con.prepareStatement(sql);
                smt.setInt(1, start);
                smt.setInt(2, stop);
                ResultSet rs = smt.executeQuery();
                while (rs.next()) {
                    NewsCategory newsCategory = new NewsCategory();
                    newsCategory.setCat_id(rs.getInt("cat_id"));
                    newsCategory.setName(rs.getString("name"));
                    newsCategoryList.add(newsCategory);
                }
                cp.putConnection(con);
                smt.close();
            }
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
        }
        return newsCategoryList;
    }

    public int getNewsCategoryCount() {
        int total = 0;
        try {
            ConnectionPool cp = ConnectionPool.getInstance();
            cp.initialize();
            Connection con = cp.getConnection();
            if (con != null) {
                String sql = "select count(*) from category";
                PreparedStatement smt = con.prepareStatement(sql);

                ResultSet rs = smt.executeQuery();
                if (rs.next()) {
                    total = rs.getInt(1);
                }
                cp.putConnection(con);
                smt.close();
            }
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
        }

        return total;

    }

    public boolean update(NewsCategory newsCategory) {
        boolean status = false;
        ConnectionPool cp = ConnectionPool.getInstance();
        cp.initialize();
        Connection con = cp.getConnection();

        if (con != null) {
            try {
                String sql = "update category set name=?  where cat_id = ?";
                PreparedStatement smt = con.prepareStatement(sql);
                smt.setString(1, newsCategory.getName());
                smt.setInt(2, newsCategory.getCat_id());
                int n = smt.executeUpdate();
                if (n > 0) {
                    status = true;
                }
                smt.close();

            } catch (Exception e) {

                System.out.println("Error " + e.getMessage());
            }

        }

        return status;
    }

    public static void main(String[] args) {
        System.out.println("hello");
    }
}
