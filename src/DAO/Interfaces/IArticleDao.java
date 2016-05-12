/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.Interfaces;

import Classes.Article;
import java.sql.ResultSet;
import java.util.List;

/**
 *
 * @author Mehdi Rekik
 */
public interface IArticleDao {
    /**
     * 
     * @return 
     */
    List<Article> getAllArticles();
    /**
     * 
     * @param id
     * @return 
     */
    boolean deleteArticleById(int id);
    /**
     * 
     * @param article
     * @return 
     */
    Article save(Article article);
    /**
     * 
     * @param article
     * @return 
     */
    Article update(Article article);
    /**
     * 
     * @param id
     * @return 
     */
    Article getArticleById(int id);
    /**
     * 
     * @param type
     * @return 
     */
    List<Article> getArticleByType(String type);
    /**
     * 
     * @param author
     * @return 
     */
    List<Article> getArticleByAuthor(String author);
    
    /**
     * Convert ResultSet to Article
     * @param rs
     * @return 
     */
    List<Article> convertResultSetToArticle(ResultSet rs);
    
    List<Article> getArticleByTypeAndByAuthor(String type, String author);
}
