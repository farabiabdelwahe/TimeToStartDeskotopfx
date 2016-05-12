/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.io.InputStream;

/**
 *
 * @author Mehdi Rekik
 */
public class Article {
    private int id ;
    private Long date; 
    private String type ;
    private String author ;
    private InputStream inputStream;
    private String fileName;

    public Article() {
    }

    public Article(int id, Long date, String type, String author, InputStream inputStream) {
        this.id = id;
        this.date = date;
        this.type = type;
        this.author = author;
        this.inputStream = inputStream;
    }

    public Article(int id, String type, String author, InputStream inputStream, String fileName) {
        this.id = id;
        this.type = type;
        this.author = author;
        this.inputStream = inputStream;
        this.fileName = fileName;
    }
    

    public Article(String type, String author, InputStream inputStream, String fileName) {
        this.type = type;
        this.author = author;
        this.inputStream = inputStream;
        this.fileName = fileName;
    }

    public Article(Long date, String type, String author, InputStream inputStream, String fileName) {
        this.date = date;
        this.type = type;
        this.author = author;
        this.inputStream = inputStream;
        this.fileName = fileName;
    }

    public Article(int id, Long date, String type, String author, InputStream inputStream, String fileName) {
        this.id = id;
        this.date = date;
        this.type = type;
        this.author = author;
        this.inputStream = inputStream;
        this.fileName = fileName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public String toString() {
        return "Article{" + "id=" + id + ", date=" + date + ", type=" + type + ", author=" + author + ", inputStream=" + inputStream + ", fileName=" + fileName + '}';
    }

    
    
    
    
    
}
