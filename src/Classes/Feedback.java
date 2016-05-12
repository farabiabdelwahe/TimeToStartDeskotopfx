/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.sql.Date;

/**
 *
 * @author GSC
 */
public class Feedback {
    
    private String text ;
    private User editeur ;

    private Projet projet ;
    private Date Date ;



    public Projet getProjet() {
        return projet;
    }

    public void setProjet(Projet projet) {
        this.projet = projet;
    }
    
  
    


    public String getText() {
        return text;
    }

    public User getEditeur() {
        return editeur;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setEditeur(User editeur) {
        this.editeur = editeur;
    }

    public Date getDate() {
        return Date;
    }

    public void setDate(Date Date) {
        this.Date = Date;
    }
    
    
    
}
