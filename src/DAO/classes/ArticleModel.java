/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.classes;

import Classes.*;
import java.io.InputStream;
import java.util.Date;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author FC
 */
public class ArticleModel {

    private StringProperty id;
    private ObjectProperty<Date> date;
    private StringProperty type;
    private StringProperty author;
    private ObjectProperty<InputStream> file;

    public ArticleModel(StringProperty id, ObjectProperty<Date> date, StringProperty type, StringProperty author, ObjectProperty<InputStream> file) {
        this.id = id;
        this.date = date;
        this.type = type;
        this.author = author;
        this.file = file;
    }

    public StringProperty getId() {
        return id;
    }

    public void setId(StringProperty id) {
        this.id = id;
    }

    public ObjectProperty<Date> getDate() {
        return date;
    }

    public void setDate(ObjectProperty<Date> date) {
        this.date = date;
    }

    public StringProperty getType() {
        return type;
    }

    public void setType(StringProperty type) {
        this.type = type;
    }

    public StringProperty getAuthor() {
        return author;
    }

    public void setAuthor(StringProperty author) {
        this.author = author;
    }

    public ObjectProperty<InputStream> getFile() {
        return file;
    }

    public void setFile(ObjectProperty<InputStream> file) {
        this.file = file;
    }

    @Override
    public String toString() {
        return "ArticleModel{" + "id=" + id + ", date=" + date + ", type=" + type + ", author=" + author + ", file=" + file + '}';
    }

    public static Article convertArticleModelToArticle(ArticleModel articleModel) {
        return new Article(Integer.parseInt(articleModel.getId().getValue()), articleModel.getDate().getValue().getTime(), articleModel.getType().getValue(), articleModel.getAuthor().getValue(), articleModel.getFile().getValue());
    }
    
    public static ArticleModel convertArticleToArticleModel(Article a) {
        return new ArticleModel(new SimpleStringProperty(String.valueOf(a.getId())), new SimpleObjectProperty<>(new Date(a.getDate())), new SimpleStringProperty(a.getType()), new SimpleStringProperty(a.getAuthor()),new SimpleObjectProperty<>(a.getInputStream()));
    }

}
