/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.classes;

import DAO.Interfaces.IArticleDao;
import Classes.Article;
import static Datasource.DbConnection.INSTANCE;
import com.mysql.jdbc.StringUtils;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mehdi Rekik
 */
public class ArticleDao implements IArticleDao {

    private Connection dbConnection = null;

    @Override
    public List<Article> getAllArticles() {

        String query = "select ID, DATE, TYPE, AUTHOR, FILE, FILENAME FROM ARTICLE";
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        List<Article> articles = null;
        try {
            dbConnection = INSTANCE.getDBConnection();
            preparedStatement = dbConnection.prepareStatement(query);
            rs = preparedStatement.executeQuery();
            if (rs != null) {
                articles = convertResultSetToArticle(rs);
            }
        } catch (SQLException e) {
            Logger.getLogger(ArticleDao.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (rs != null) {
                    rs.close();
                }
                if (dbConnection != null) {
                    dbConnection.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(ArticleDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return articles;
    }

    @Override
    public boolean deleteArticleById(int id) {
        String query = "DELETE FROM ARTICLE WHERE id = ?";
        PreparedStatement preparedStatement = null;
        boolean success = false;
        int res = 0;
        try {
            dbConnection = INSTANCE.getDBConnection();
            preparedStatement = dbConnection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            res = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(ArticleDao.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (dbConnection != null) {
                    dbConnection.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(ArticleDao.class.getName()).log(Level.SEVERE, null, ex);
                throw new RuntimeException();
            }
        }
        if(res> 0) {
            return true;
        }
        return success;
    }

    @Override
    public Article save(Article article) {
        String query = "INSERT INTO ARTICLE"
                + "(DATE, TYPE, AUTHOR, FILE, FILENAME) VALUES"
                + "(?,?,?,?,?)";
        PreparedStatement preparedStatement = null;
        try {
            dbConnection = INSTANCE.getDBConnection();
            dbConnection.setAutoCommit(false);
            preparedStatement = dbConnection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, INSTANCE.getCurrentDate());
            preparedStatement.setString(2, article.getType());
            preparedStatement.setString(3, article.getAuthor());
            preparedStatement.setBlob(4, article.getInputStream());
            preparedStatement.setString(5, article.getFileName());
            int affectedRows = preparedStatement.executeUpdate();
            dbConnection.commit();
            if (affectedRows == 0) {
                throw new SQLException("Creating user failed, no rows affected.");
            }
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    article.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            Logger.getLogger(ArticleDao.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (dbConnection != null) {
                    dbConnection.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(ArticleDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return article;
    }

    @Override
    public Article update(Article article) {
        Article ar = getArticleById(article.getId());
        if (StringUtils.isNullOrEmpty(article.getAuthor())) {
            article.setAuthor(ar.getAuthor());
        }
        if (StringUtils.isNullOrEmpty(article.getType())) {
            article.setType(ar.getType());
        }
        if (article.getInputStream() == null) {
            article.setInputStream(ar.getInputStream());
        }
        if (StringUtils.isNullOrEmpty(article.getFileName())) {
            article.setFileName(ar.getFileName());
        }
        String query = "UPDATE ARTICLE SET DATE = ?, TYPE = ?, AUTHOR = ?, FILE = ?, FILENAME = ?"
                + "WHERE ID = ?";
        PreparedStatement preparedStatement = null;
        try {
            dbConnection = INSTANCE.getDBConnection();
            dbConnection.setAutoCommit(false);
            preparedStatement = dbConnection.prepareStatement(query);
            preparedStatement.setLong(1, INSTANCE.getCurrentDate());
            preparedStatement.setString(2, article.getType());
            preparedStatement.setString(3, article.getAuthor());
            preparedStatement.setBlob(4, article.getInputStream());
            preparedStatement.setString(5, article.getFileName());
            preparedStatement.setLong(6, article.getId());
            preparedStatement.executeUpdate();
            dbConnection.commit();
        } catch (SQLException e) {
            Logger.getLogger(ArticleDao.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (dbConnection != null) {
                    dbConnection.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(ArticleDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return article;
    }

    @Override
    public Article getArticleById(int id) {
        String query = "select ID, DATE, TYPE, AUTHOR, FILE, FILENAME FROM ARTICLE WHERE ID = ?";
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        Article article = null;
        try {
            dbConnection = INSTANCE.getDBConnection();
            preparedStatement = dbConnection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            rs = preparedStatement.executeQuery();
            if (rs != null) {
                article = convertResultSetToArticle(rs).get(0);
            }
        } catch (SQLException e) {
            Logger.getLogger(ArticleDao.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (rs != null) {
                    rs.close();
                }
                if (dbConnection != null) {
                    dbConnection.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(ArticleDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return article;
    }

    @Override
    public List<Article> getArticleByType(String type) {
        String query = "select ID, DATE, TYPE, AUTHOR, FILE, FILENAME FROM ARTICLE WHERE TYPE = ?";
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        List<Article> articles = null;
        try {
            dbConnection = INSTANCE.getDBConnection();
            preparedStatement = dbConnection.prepareStatement(query);
            preparedStatement.setString(1, type);
            rs = preparedStatement.executeQuery();
            if (rs != null) {
                articles = convertResultSetToArticle(rs);
            }
        } catch (SQLException e) {
            Logger.getLogger(ArticleDao.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (rs != null) {
                    rs.close();
                }
                if (dbConnection != null) {
                    dbConnection.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(ArticleDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return articles;
    }

    @Override
    public List<Article> getArticleByAuthor(String author) {
        String query = "select ID, DATE, TYPE, AUTHOR, FILE, FILENAME FROM ARTICLE WHERE AUTHOR like  %?%";
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        List<Article> articles = null;
        try {
            dbConnection = INSTANCE.getDBConnection();
            preparedStatement = dbConnection.prepareStatement(query);
            preparedStatement.setString(1, author);
            rs = preparedStatement.executeQuery();
            if (rs != null) {
                articles = convertResultSetToArticle(rs);
            }
        } catch (SQLException e) {
            Logger.getLogger(ArticleDao.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (rs != null) {
                    rs.close();
                }
                if (dbConnection != null) {
                    dbConnection.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(ArticleDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return articles;
    }

    @Override
    public List<Article> convertResultSetToArticle(ResultSet rs) {
        List<Article> articles = new ArrayList<>();
        try {
            while (rs.next()) {
                int id = rs.getInt("ID");
                Long date = rs.getLong("DATE");
                String type = rs.getString("TYPE");
                String author = rs.getString("AUTHOR");
                InputStream inputStream = rs.getBlob("FILE").getBinaryStream();
                String fileName = rs.getString("FILENAME");
                articles.add(new Article(id, date, type, author, inputStream, fileName));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ArticleDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return articles;
    }

    @Override
    public List<Article> getArticleByTypeAndByAuthor(String type, String author) {
        String query = "select ID, DATE, TYPE, AUTHOR, FILE, FILENAME FROM ARTICLE ";
        String authorQuery = "AUTHOR = ?";
        String typeQuery = "TYPE = ?";
        if (!StringUtils.isNullOrEmpty(type) && !StringUtils.isNullOrEmpty(author)) {
            query += "WHERE " + authorQuery + " AND " + typeQuery;
        } else if (!StringUtils.isNullOrEmpty(author)) {
            query += "WHERE " + authorQuery;
        } else if (!StringUtils.isNullOrEmpty(type)) {
            query += "WHERE " + typeQuery;
        }
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        List<Article> articles = null;
        try {
            dbConnection = INSTANCE.getDBConnection();
            preparedStatement = dbConnection.prepareStatement(query);
            if (!StringUtils.isNullOrEmpty(type) && !StringUtils.isNullOrEmpty(author)) {
                preparedStatement.setString(1, author);
                preparedStatement.setString(2, type);
            } else if (!StringUtils.isNullOrEmpty(type)) {
                preparedStatement.setString(1, type);
            } else if (!StringUtils.isNullOrEmpty(author)) {
                preparedStatement.setString(1, author);
            }
            rs = preparedStatement.executeQuery();
            if (rs != null) {
                articles = convertResultSetToArticle(rs);
            }
        } catch (SQLException e) {
            Logger.getLogger(ArticleDao.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (rs != null) {
                    rs.close();
                }
                if (dbConnection != null) {
                    dbConnection.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(ArticleDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return articles;
    }

}
