/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.sql.Date;
import java.text.SimpleDateFormat;


public class Sujet {
    private int idsubject;
    private String categories ; 
    private String name ;
    private String content ;
    private Date date ; 

    private  User Createur;

    public Sujet()
    {
    }

    @Override
    public String toString() {
        return "Sujet{" + "idsubject=" + idsubject + ", categories=" + categories + ", name=" + name + ", content=" + content + ", date=" + date + ", Createur=" + Createur + '}';
    }
    
    
    public Sujet(int idsubject, String categories, String name, String content, Date date, User Createur) {
        this.idsubject = idsubject;
        this.categories = categories;
        this.name = name;
        this.content = content;
        this.date = date;
        this.Createur = Createur;
    }
    
    

    public void setIdsubject(int idsubject) {
        this.idsubject = idsubject;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setDate(Date date) {
        this.date = date;
    }

 

    public void setCreateur(User Createur) {
        this.Createur = Createur;
    }

    public int getIdsubject() {
        return idsubject;
    }

    public String getCategories() {
        return categories;
    }

    public String getName() {
        return name;
    }

    public String getContent() {
        return content;
    }

    public Date getDate() {
        return date;
    }


    public User getCreateur() {
        return Createur;
    }

    public void getCategories(Date date) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }



    


    
    
    
    
}
