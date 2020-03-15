package com.daos;

import com.beans.News;
import com.beans.NewsCategory;
import com.jdbc.ConnectionPool;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class NewsDao {

    public boolean add(News news, String[] catids) {
        boolean status = false;
        ConnectionPool cp = ConnectionPool.getInstance();
        cp.initialize();
        Connection con = cp.getConnection();

        if (con != null) {
           try {
                  con.setAutoCommit(false);

            
                String sql = "Insert into news(title, description, image, reporter_id, status,status_text) values(?,?,?,?,?,?)";
                PreparedStatement smt = con.prepareStatement(sql);
                smt.setString(1, news.getTitle());
                smt.setString(2, news.getDescription());
                smt.setString(3, news.getImage());
                smt.setInt(4, news.getReporter_id());
                smt.setString(5, news.getStatus());
                smt.setString(6, news.getStatus_text());

                smt.executeUpdate();

                sql = "select id from news order by id desc limit 1";
                smt = con.prepareStatement(sql);
                ResultSet rs = smt.executeQuery();
                int news_id = -1;
                if (rs.next()) {
                    news_id = rs.getInt("id");

                    for (String catid : catids) {
                        sql = "insert into newstype (news_id,cat_id) values(?,?)";
                        smt = con.prepareStatement(sql);
                        smt.setInt(1, news_id);
                        smt.setInt(2, Integer.parseInt(catid));
                        smt.executeUpdate();
                    }
                }
                con.commit();
                status = true;
                smt.close();

            } catch (Exception e) {
             try{   con.rollback(); } catch(Exception ex) {System.out.println("Rollback error");}
                System.out.println("Error " + e.getMessage());
            }
           finally{
                cp.putConnection(con);
          }
        }
        return status;
    }

    public boolean removeById(int id) {
        boolean status = false;
        try {
            ConnectionPool cp = ConnectionPool.getInstance();
            cp.initialize();
            Connection con = cp.getConnection();
            if (con != null) {
                String sql = "Delete from news where id=?";
                PreparedStatement smt = con.prepareStatement(sql);
                smt.setInt(1, id);

                int n = smt.executeUpdate();
                if (n > 0) {
                    status = true;
                    System.out.println("News Removed !!");
                }

                cp.putConnection(con);
                smt.close();

            }

        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
        }

        return status;
    }

    public News getById(int id) {
        News news = null;
        try {
            ConnectionPool cp = ConnectionPool.getInstance();
            cp.initialize();
            Connection con = cp.getConnection();
            if (con != null) {
                String sql = "select * from news where id=?";
                PreparedStatement smt = con.prepareStatement(sql);
                smt.setInt(1, id);
                ResultSet rs = smt.executeQuery();
                if (rs.next()) {
                    news = new News();
                    news.setId(rs.getInt("id"));
                    news.setTitle(rs.getString("title"));
                    news.setDescription(rs.getString("description"));
                    news.setImage(rs.getString("image"));
                    news.setReporter_id(rs.getInt("reporter_id"));
                    news.setStatus(rs.getString("status"));
                    news.setStatus_text(rs.getString("status_text"));
                }
                cp.putConnection(con);
                smt.close();
            }
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
        }

        return news;
    }

    public ArrayList<News> getAllNews() {
        ArrayList<News> newsList = new ArrayList();
        try {
            ConnectionPool cp = ConnectionPool.getInstance();
            cp.initialize();
            Connection con = cp.getConnection();
            if (con != null) {
                String sql = "select * from news";
                PreparedStatement smt = con.prepareStatement(sql);

                ResultSet rs = smt.executeQuery();
                while (rs.next()) {
                    News news = new News();
                    news.setId(rs.getInt("id"));
                    news.setTitle(rs.getString("title"));
                    news.setDescription(rs.getString("description"));
                    news.setImage(rs.getString("image"));
                    news.setReporter_id(rs.getInt("reporter_id"));
                    news.setStatus(rs.getString("status"));
                    news.setStatus_text(rs.getString("status_text"));

                    newsList.add(news);
                }
                cp.putConnection(con);
                smt.close();
            }
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
        }

        return newsList;
    }

    public ArrayList<News> getNewsByLimit(int start, int stop) {
        ArrayList<News> newsList = new ArrayList();
        try {
            ConnectionPool cp = ConnectionPool.getInstance();
            cp.initialize();
            Connection con = cp.getConnection();
            if (con != null) {
                String sql = "select * from news limit ?, ?";
                PreparedStatement smt = con.prepareStatement(sql);
                smt.setInt(1, start);
                smt.setInt(2, stop);
                ResultSet rs = smt.executeQuery();
                while (rs.next()) {
                    News news = new News();
                    news.setId(rs.getInt("id"));
                    news.setTitle(rs.getString("title"));
                    news.setDescription(rs.getString("description"));
                    news.setImage(rs.getString("image"));
                    news.setReporter_id(rs.getInt("reporter_id"));
                    news.setStatus(rs.getString("status"));
                    news.setStatus_text(rs.getString("status_text"));

                    newsList.add(news);
                }
                cp.putConnection(con);
                smt.close();
            }
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
        }
        return newsList;
    }

    public int getNewsCount() {
        int total = 0;
        try {
            ConnectionPool cp = ConnectionPool.getInstance();
            cp.initialize();
            Connection con = cp.getConnection();
            if (con != null) {
                String sql = "select count(*) from news";
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

    public ArrayList<News> getNewsByCatId(String cat_id) {
        ArrayList<News> newsList = new ArrayList();
        try {
            ConnectionPool cp = ConnectionPool.getInstance();
            cp.initialize();
            Connection con = cp.getConnection();
            if (con != null) {
                String sql = "select * from news where id in (select news_id from newstype where cat_id =?)";
                PreparedStatement smt = con.prepareStatement(sql);
                smt.setString(1, cat_id);
                ResultSet rs = smt.executeQuery();
                while (rs.next()) {
                    News news = new News();
                    news.setId(rs.getInt("id"));
                    news.setTitle(rs.getString("title"));
                    news.setDescription(rs.getString("description"));
                    news.setImage(rs.getString("image"));
                    news.setReporter_id(rs.getInt("reporter_id"));
                    news.setStatus(rs.getString("status"));
                    news.setStatus_text(rs.getString("status_text"));

                    newsList.add(news);
                }
                cp.putConnection(con);
                smt.close();
            }
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
        }
        return newsList;
    }

    
     public ArrayList<News> getNewsByReporterId(int reporterid) {
        ArrayList<News> newsList = new ArrayList();
        try {
            ConnectionPool cp = ConnectionPool.getInstance();
            cp.initialize();
            Connection con = cp.getConnection();
            if (con != null) {
                String sql = "select * from news where reporter_id=?)";
                PreparedStatement smt = con.prepareStatement(sql);
                smt.setInt(1, reporterid);
                ResultSet rs = smt.executeQuery();
                while (rs.next()) {
                    News news = new News();
                    news.setId(rs.getInt("id"));
                    news.setTitle(rs.getString("title"));
                    news.setDescription(rs.getString("description"));
                    news.setImage(rs.getString("image"));
                    news.setReporter_id(rs.getInt("reporter_id"));
                    news.setStatus(rs.getString("status"));
                    news.setStatus_text(rs.getString("status_text"));

                    newsList.add(news);
                }
                cp.putConnection(con);
                smt.close();
            }
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
        }
        return newsList;
    }
 public ArrayList<News> getAllNewsByStatus(String status) {
        ArrayList<News> newsList = new ArrayList();
        try {
            ConnectionPool cp = ConnectionPool.getInstance();
            cp.initialize();
            Connection con = cp.getConnection();
            if (con != null) {
                String sql = "select * from news where status=?";
                PreparedStatement smt = con.prepareStatement(sql);
                 smt.setString(1, status);
                ResultSet rs = smt.executeQuery();
                while (rs.next()) {
                    News news = new News();
                    news.setId(rs.getInt("id"));
                    news.setTitle(rs.getString("title"));
                    news.setDescription(rs.getString("description"));
                    news.setImage(rs.getString("image"));
                    news.setReporter_id(rs.getInt("reporter_id"));
                    news.setStatus(rs.getString("status"));
                    news.setStatus_text(rs.getString("status_text"));

                    newsList.add(news);
                }
                cp.putConnection(con);
                smt.close();
            }
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
        }

        return newsList;
    }
     
     
     public ArrayList<News> getAllNewsByStatus(String status, int reporterid) {
        ArrayList<News> newsList = new ArrayList();
        try {
            ConnectionPool cp = ConnectionPool.getInstance();
            cp.initialize();
            Connection con = cp.getConnection();
            if (con != null) {
                String sql = "select * from news where status=? and reporter_id=?";
                PreparedStatement smt = con.prepareStatement(sql);
                smt.setString(1, status);
                smt.setInt(2, reporterid);
                ResultSet rs = smt.executeQuery();
                while (rs.next()) {
                    News news = new News();
                    news.setId(rs.getInt("id"));
                    news.setTitle(rs.getString("title"));
                    news.setDescription(rs.getString("description"));
                    news.setImage(rs.getString("image"));
                    news.setReporter_id(rs.getInt("reporter_id"));
                    news.setStatus(rs.getString("status"));
                    news.setStatus_text(rs.getString("status_text"));

                    newsList.add(news);
                }
                cp.putConnection(con);
                smt.close();
            }
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
        }

        return newsList;
    }
 
     public int getNewsCountByCategory(int cat_id) {
        int total = 0;
        try {
            ConnectionPool cp = ConnectionPool.getInstance();
            cp.initialize();
            Connection con = cp.getConnection();
            if (con != null) {
                String sql = "select count(*) from newstype where cat_id=?";
                PreparedStatement smt = con.prepareStatement(sql);
                smt.setInt(1, cat_id);
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
public boolean update(News news, String cat_ids) {
        boolean status = false;
        System.out.println("news:"+news);
        System.out.println("catisd:"+cat_ids);
            String catids[] = cat_ids.split(",");
        ConnectionPool cp = ConnectionPool.getInstance();
        cp.initialize();
        Connection con = cp.getConnection();

        if (con != null) {
            try {
                 con.setAutoCommit(false);
                 System.out.println("title:"+news.getTitle());
                 System.out.println("desc:"+news.getDescription());
                String sql = "update news set title=?,description=?,image=? where id=?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, news.getTitle());
                ps.setString(2, news.getDescription());
                ps.setString(3, news.getImage());
                ps.setInt(4, news.getId());
                int n = ps.executeUpdate();
                
                int news_id = news.getId();
                System.out.println("news_id:"+news_id);
                ps = con.prepareStatement("delete from newstype where news_id=?");
                ps.setInt(1, news_id);
                n = ps.executeUpdate();
                System.out.println("deleted");
                int x = 0;
                     for (String cat_id : catids) {
                         System.out.println("cat_id:"+cat_id);
                        ps = con.prepareStatement("insert into newstype (news_id,cat_id) values(?,?)");
                        ps.setInt(1, news_id);
                        ps.setInt(2, Integer.parseInt(cat_id));
                        x = ps.executeUpdate();
                    }
                if(x>0){
                status = true;
                System.out.println("Record Updated...");
                }
                con.commit();
                con.close();
            } catch (Exception e) {
                try {
                    con.rollback();
                } catch (Exception x) {
                }
                System.out.println("Error:" + e.getMessage());
            } finally {
                cp.releaseConnection(con);
            }

        }

        return status;
    }

public boolean updateNewsStatus(int id, String status,String statusText){
    boolean sts = false;
    ConnectionPool cp = ConnectionPool.getInstance();
        cp.initialize();
        Connection con = cp.getConnection();
        System.out.println("cp:"+cp);
        if (con != null) {
           try {
                String sql = "update news set status=?, status_text=? where id = ?";
                PreparedStatement smt = con.prepareStatement(sql);
                smt.setString(1, status);
                smt.setString(2, statusText);
                smt.setInt(3, id);
                smt.executeUpdate();
                
                sts = true;
                smt.close();
                cp.releaseConnection(con);
             //   System.out.println("status:"+sts);
            } catch (Exception e) {
                
              System.out.println("Error " + e.getMessage());
            }
       }
     return sts;
}

    public ArrayList<NewsCategory> getNewsCategory() {
        ArrayList<NewsCategory> AllNewsCategory = new ArrayList();

        try {
            ConnectionPool cp = ConnectionPool.getInstance();
            cp.initialize();
            Connection con = cp.getConnection();
            if (con != null) {
                String sql = "select * from category";
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    NewsCategory news_cat = new NewsCategory();
                    news_cat.setCat_Id(rs.getInt("id"));
                    news_cat.setName(rs.getString("name"));

                    AllNewsCategory.add(news_cat);
                }
                cp.releaseConnection(con);
                ps.close();
            }
        } catch (Exception e) {
            System.out.println("Error:" + e.getMessage());
        }

        return AllNewsCategory;
    }
    public ArrayList<News> getNewsByCatId(String cat_id,int reporter_id) {
        ArrayList<News> AllNews = new ArrayList();
        try {
            ConnectionPool cp = ConnectionPool.getInstance();
            cp.initialize();
            Connection con = cp.getConnection();
            if (con != null) {
                String sql = "select * from news where id in(select news_id from newstype where cat_id=? ) and reporter_id=?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, cat_id);
                ps.setInt(2, reporter_id);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    News news = new News();
                    news.setTitle(rs.getString("title"));
                    news.setDescription(rs.getString("description"));
                    news.setImage(rs.getString("image"));
                    news.setReporter_id(rs.getInt("reporter_id"));
                    news.setStatus(rs.getString("status"));
                    news.setStatus_text(rs.getString("status_text"));

                    AllNews.add(news);
                }
                cp.releaseConnection(con);
                ps.close();
            }
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
        }
        return AllNews;
    }

    public int getRecordCount(int reporter_id) {
        int total = 0;
        try {
            ConnectionPool cp = ConnectionPool.getInstance();
            cp.initialize();
            Connection con = cp.getConnection();
            if (con != null) {
                String sql = "select count(*) from news where reporter_id=?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1,reporter_id);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    total = rs.getInt(1);
                    System.out.println("total records : " + total);
                }
                cp.releaseConnection(con);
                ps.close();
            }
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
        }
        return total;
    }
    public int getRecordCount(int reporter_id,String status) {
        int total = 0;
         System.out.println("status:"+status);
        try {
            ConnectionPool cp = ConnectionPool.getInstance();
            cp.initialize();
            Connection con = cp.getConnection();
            if (con != null) {
                String sql = "select count(*) from news where reporter_id=? and status=?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1,reporter_id);
                ps.setString(2,status);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    total = rs.getInt(1);
                    System.out.println("total records : " + total);
                }
                cp.releaseConnection(con);
                ps.close();
            }
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
        }
        return total;
    }

    public static void main(String[] args) {
        System.out.println("hello");
    }
}
