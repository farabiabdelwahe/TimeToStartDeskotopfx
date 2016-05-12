/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Translators;

import Classes.Projet;
import Classes.User;
import java.sql.Date;

/**
 *
 * @author GSC
 */
public class FeedBackt  {
      private String text ;
    private User editeur ;
    private String Owner ;

    private Projet projet ;
    private String project ;
    private Date Date ;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getEditeur() {
        return editeur;
    }

    public void setEditeur(User editeur) {
        this.editeur = editeur;
    }

    public String getOwner() {
        return Owner;
    }

    public void setOwner(String Owner) {
        this.Owner = Owner;
    }

    public Projet getProjet() {
        return projet;
    }

    public void setProjet(Projet projet) {
        this.projet = projet;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public Date getDate() {
        return Date;
    }

    public void setDate(Date Date) {
        this.Date = Date;
    }
    
    
    
    
}
