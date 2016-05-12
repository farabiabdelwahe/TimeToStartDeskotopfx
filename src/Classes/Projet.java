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
public class Projet {
    private int id ;
    private String name;
    private User Createur ;
    private String Discription ;
    private String type ;
     private Date date;
     private String file ;
     private String filelink;
     private String helpType;
     private String tDiscription;
     private Float target ;
     private String prize ;
    private String htask;
    private   java.sql.Date taskDate;
    private Float Rating ;
     
     
     
public Projet() {};

    public Projet(User Createur, String Discription, String type, SimpleDateFormat date) {
        this.Createur = Createur;
        this.Discription = Discription;
        this.type = type;
    
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
   
    

    public Projet(User Createur, String Discription, String type) {
        this.Createur = Createur;
        this.Discription = Discription;
        this.type = type;
    }


    
    public User getCreateur() {
        return Createur;
    }

    public String getDiscription() {
        return Discription;
    }

    public String getType() {
        return type;
    }

    public void setCreateur(User Createur) {
        this.Createur = Createur;
    }

    public void setDiscription(String Discription) {
        this.Discription = Discription;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Projet{" + "Createur=" + Createur + ", Discription=" + Discription + ", type=" + type + '}';
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getFile() {
        return file;
    }

    public String getFilelink() {
        return filelink;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public void setFilelink(String filelink) {
        this.filelink = filelink;
    }

    public String getHelpType() {
        return helpType;
    }

    public String gettDiscription() {
        return tDiscription;
    }

  

    public String getPrize() {
        return prize;
    }

    public String getHtask() {
        return htask;
    }

    public Date getTaskDate() {
        return taskDate;
    }

    public void setHelpType(String helpType) {
        this.helpType = helpType;
    }

    public void settDiscription(String tDiscription) {
        this.tDiscription = tDiscription;
    }



    public void setPrize(String prize) {
        this.prize = prize;
    }

    public void setHtask(String htask) {
        this.htask = htask;
    }

    public void setTaskDate(Date taskDate) {
        this.taskDate = taskDate;
    }

    public Float getTarget() {
        return target;
    }

    public void setTarget(Float target) {
        this.target = target;
    }

    public Float getRating() {
        return Rating;
    }

    public void setRating(Float Rating) {
        this.Rating = Rating;
    }


  
       

}
