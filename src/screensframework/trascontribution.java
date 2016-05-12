/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package screensframework;

import Classes.Sujet;
import Classes.User;
import java.sql.Date;

/**
 *
 * @author Youssef
 */
public class trascontribution {
     private int  idcontribution;
    private String content ;
    private Date date ;
    private String owner ;

    public int getIdcontribution() {
        return idcontribution;
    }

    public void setIdcontribution(int idcontribution) {
        this.idcontribution = idcontribution;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
    
    
}
