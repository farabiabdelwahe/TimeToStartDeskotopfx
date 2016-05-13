/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.text.SimpleDateFormat;
import java.util.List;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author GSC
 */
public class User {
    
   private int id ;
   private String username ;
    private String password ;
    private String firstname ;
   private String lastname ;
  private String email;
    private java.sql.Date birthdate;
      private String country ;
      private String qualification ;
      private int ban;
   private String fbid;
    List<Projet> projets;
      List<Feedback> feedbacks;
        List<Sponsoring> sponsorings;
        private String facebookid ;
        private String roles;
    

        public User(){}
        
    public User(int id, String username,String email, String password, String firstname, String lastname, java.sql.Date birthdate, String country, String qualification,int ban, List<Projet> projets, List<Feedback> feedbacks, List<Sponsoring> sponsorings) {
        this.id = id;
        this.username = username;
        this.email=email;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthdate = birthdate;
        
        this.country = country;
        this.qualification = qualification;
        this.ban=ban;
        this.projets = projets;
        this.feedbacks = feedbacks;
        this.sponsorings = sponsorings;
    }

    public String getFacebookid() {
        return facebookid;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public void setFacebookid(String facebookid) {
        this.facebookid = facebookid;
    }

    public User(String username,String lastname, String firstname, String email) {
this.username=username;
        this.lastname=lastname;
this.firstname=firstname;
this.email=email;
    }

    public String getFbid() {
        return fbid;
    }

    public void setFbid(String fbid) {
        this.fbid = fbid;
    }

 

  

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getBan() {
        return ban;
    }

    public void setBan(int ban) {
        this.ban = ban;
    }
        
        
    


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
  
     

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public int getId() {
        return id;
    }

    public String getLastname() {
        return lastname;
    }

     
    public String getPassword() {
        return password;
    }

    public java.sql.Date getBirthdate() {
        return birthdate;
    }

    public List<Projet> getProjets() {
        return projets;
    }

    public List<Feedback> getFeedbacks() {
        return feedbacks;
    }

    public List<Sponsoring> getSponsorings() {
        return sponsorings;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setBirthdate(java.sql.Date birthdate) {
        this.birthdate = birthdate;
    }

    public void setProjets(List<Projet> projets) {
        this.projets = projets;
    }

    public void setFeedbacks(List<Feedback> feedbacks) {
        this.feedbacks = feedbacks;
    }

    public void setSponsorings(List<Sponsoring> sponsorings) {
        this.sponsorings = sponsorings;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", username=" + username + ", password=" + password + ", firstname=" + firstname + ", lastname=" + lastname + ", email=" + email + ", birthdate=" + birthdate + ", country=" + country + ", qualification=" + qualification + ", ban=" + ban + ", fbid=" + fbid + ", projets=" + projets + ", feedbacks=" + feedbacks + ", sponsorings=" + sponsorings + '}';
    }

    
    
    public User(String username,int id,String email, String lastname, String password, java.sql.Date birthdate, List<Projet> projets, List<Feedback> feedbacks, List<Sponsoring> sponsorings) {
       this.username=username;
        this.id = id;
        this.email=email;
        this.lastname = lastname;
        this.password = password;
        this.birthdate = birthdate;
        this.projets = projets;
        this.feedbacks = feedbacks;
        this.sponsorings = sponsorings;
    }

  public User(String username,String email, String lastname, String password,String country,String firstname,String facebookid) {
       this.username=username;
       this.facebookid=facebookid;
        this.email=email;
        this.lastname = lastname;
        
        this.password = password;
        this.country = country;
        this.firstname = firstname;
       
    }

  

  

  
   
    
    
    
    
      
      
    
    
    
    
}
