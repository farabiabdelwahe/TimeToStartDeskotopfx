/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.sql.Date;
import java.text.SimpleDateFormat;

/**
 *
 * @author GSC
 */
public class Contribution {
   
    private int  idcontribution;
    private String content ;
    private Date date ;
    private Sujet sujet;
    private User user ; 
    
    
    
    public Contribution ()
    {
    }

    public void setIdcontribution(int idcontribution) {
        this.idcontribution = idcontribution;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setSujet(Sujet sujet) {
        this.sujet = sujet;
    }

    public void setUser(User user) {
        this.user = user;
    }
    

    public Contribution(int idcontribution , String content , Date date , Sujet sujet , User user)
    {
        this.idcontribution = idcontribution;
        this.content = content;
        this.date = date;
        this.sujet=sujet;
        this.user=user;
    }

    public int getIdcontribution() {
        return idcontribution;
    }

    public String getContent() {
        return content;
    }

    public Date getDate() {
        return date;
    }

    public Sujet getSujet() {
        return sujet;
    }

    public User getUser() {
        return user;
    }

             
             
    
}
