/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.classes;

import Classes.Projet;
import Classes.Sujet;
import Classes.User;
import DAO.Interfaces.IprojectDAO;
import Util.DataSource;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;


/**
 *
 * @author GSC
 */
public class ProjectDAO implements IprojectDAO{


     private Connection connection;

    public ProjectDAO() {
        connection = DataSource.getInstance().getConnection();
        
    }
     @Override
    public boolean Ajoutsujet(Projet p) {
 
        try 
            
        {
            String req ="INSERT INTO project(description,Name,datecreation,iduser,type,file,filelink,helptype,target,prize,tdiscription,htask,taskdate) "
                    + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
           
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setString(1, p.getDiscription());
            ps.setString(2,p.getName());
            ps.setDate(3, p.getDate());
                   ps.setInt(4,p.getCreateur().getId());
                   ps.setString(5,p.getType());
                   ps.setString(6,p.getFile());
            ps.setString(7,p.getFilelink());
            ps.setString(8,p.getHelpType());
         if (p.getTarget() == null) {
        ps.setNull(9,0);
    } else {
        ps.setFloat(9,p.getTarget());
    }

            
                 ps.setString(10, p.getPrize());
                ps.setString(11,p.gettDiscription());
                ps.setString(12, p.getHtask());
               ps.setDate(13, p.getTaskDate());
            ps.executeUpdate();
            return true;
        } 
        catch (SQLException ex)
        {
            Logger.getLogger(Sujet.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
            return false ;
        }
    }

    
    public void SupprimeProjet(Projet p) {
         
        String requete = "delete from project where Name=?";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setString(1, p.getName());
            ps.executeUpdate();
            System.out.println("Pays supprimée");
        } catch (SQLException ex) {
            System.out.println("erreur lors de la suppression " + ex.getMessage());
        }
   
    
    }


    public List<Projet> findAllbyuser(int i ) {
              List<Projet> listep = new ArrayList<>();
        String requete = "select fos_user.username as username ,project.*  from fos_user,project where project.iduser=fos_user.id and project.iduser="+i;
        try{
            Statement statement = connection
                    .createStatement();
            ResultSet resultat = statement.executeQuery(requete);

            while (resultat.next()) {
               Projet p = new Projet();
              p.setId(resultat.getInt("iduser"));
              p.setName(resultat.getString("Name"));
              User u = new User () ;
              u.setId(i);
              u.setUsername(resultat.getString("username"));
          p.setCreateur(u);
    p.setPrize(resultat.getString("prize"));
    p.setTarget(resultat.getFloat("Target"));
    p.setHelpType(resultat.getString("HelpType"));
    p.settDiscription(resultat.getString("tdiscription"));
    p.setTaskDate(resultat.getDate("taskdate"));
   p.setHtask(resultat.getString("htask"));
              
              
            
              
              p.setDiscription(resultat.getString("description"));
                   p.setDate(resultat.getDate("datecreation"));
      p.setFile(resultat.getString("file"));
      p.setFilelink(resultat.getString("filelink"));
      listep.add(p);
            }
        }
            catch (Exception ex) {
                    Alert alert = new Alert(AlertType.INFORMATION);
alert.setTitle("Information Dialog");
alert.setHeaderText("alert");
alert.setContentText(ex.getMessage());

alert.showAndWait();
                    }
     
                return listep;
        
                    }
       
    
    
    
    public List<Projet> findAllbycat(String s) {
              List<Projet> listep = new ArrayList<>();
        String requete = "select * from project where type='"+s+"'";
        try {
            Statement statement = connection
                    .createStatement();
            ResultSet resultat = statement.executeQuery(requete);

            while (resultat.next()) {
               Projet p = new Projet();
              p.setId(resultat.getInt(1));
              p.setName(resultat.getString("Name"));
         
              p.setDiscription(resultat.getString("description"));
                   p.setDate(resultat.getDate("datecreation"));

              p.setFile(resultat.getString("file"));
              p.setFilelink(resultat.getString("filelink"));
               
                listep.add(p);
            }
         
        } catch (SQLException ex) {
            System.out.println("erreur lors du chargement des depots " + ex.getMessage());
            return null;
        }
           return listep;
    }
    
public Map<String,Integer> calculatecat(){ 
    
    Map v = new HashMap<String ,Integer>();
    try {
               String requete1 = "select count(*)  as me from project where type='Art'";
                         String requete2 = "select count(*) as me from project where type='Social'";
                              String requete3 = "select count(*) as me from project where type='Technology'";
                                   String requete4 = "select count(*) as me  from project where type='Services'";
                                        String requete5 = "select count(*) as me  from project where type='Science'";
                                         Statement statement = connection
                    .createStatement();
                                
                                         
                                         
                                   
                                            
                                                    ResultSet rs1 = statement.executeQuery(requete1);
                                          rs1.next();
                                          
                                          v.put("Art", rs1.getInt("me"));
                                          System.out.println(rs1.getInt("me"));
                                          
                                      
                                            ResultSet rs2 = statement.executeQuery(requete2);
                                          rs2.next();
                                            
                                                v.put("Social", rs2.getInt("me"));
                                                
                                                      ResultSet rs3 = statement.executeQuery(requete3);
                                                rs3.next();
                                                     
                                                           v.put("Technology", rs3.getInt("me"));
                                                           
                                                           ResultSet rs4 = statement.executeQuery(requete4);
                                                           rs4.next();
                                                                 
                                                           v.put("Science", rs4.getInt("me"));
                                                           
                                                             ResultSet rs5 = statement.executeQuery(requete5);
                                          
                                                           rs5.next();
                                                                 
                                                           v.put("Services", rs5.getInt("me"));
                                                           
                                                           
                                           
                                                
                                         
               
        
        
    }
    catch ( Exception e){
         Logger.getLogger(ProjectDAO.class.getName()).log(Level.SEVERE, null, e);
        
    }
    
    return v ;
}

  
    public Projet findProjectbyname(String s) {
                 Projet p = new Projet();
                 
        String requete = "select fos_user.username as username ,project.*,project.rating/project.timerated as rating1  from fos_user,project where project.iduser=fos_user.id and project.name='"+s+"'";
        try {
            Statement statement = connection
                    .createStatement();
            ResultSet resultat = statement.executeQuery(requete);

            while (resultat.next()) {
                    p.setPrize(resultat.getString("prize"));
    p.setTarget(resultat.getFloat("Target"));
    p.setHelpType(resultat.getString("HelpType"));
    p.settDiscription(resultat.getString("tdiscription"));
    p.setTaskDate(resultat.getDate("taskdate"));
   p.setHtask(resultat.getString("htask"));
      p.setRating(resultat.getFloat("rating1"));
      User u = new User () ;
             
              u.setUsername(resultat.getString("username"));
          p.setCreateur(u);
           
              p.setId(resultat.getInt("idproject"));
              p.setName(resultat.getString("Name"));
         
              p.setDiscription(resultat.getString("description"));
                   p.setDate(resultat.getDate("datecreation"));

              p.setFile(resultat.getString("file"));
              p.setFilelink(resultat.getString("filelink"));
               
            
            }
         
        } catch (SQLException ex) {
            System.out.println("erreur lors du chargement des depots " + ex.getMessage());
            return null;
        }
            return p ;
    }
    
      
    public Projet findProjectbyid(int s) {
                 Projet p = new Projet();
                 
        String requete = "select fos_user.username as username ,project.*, project.rating/project.timerated as rating1  from fos_user,project where project.iduser=fos_user.id and project.idproject='"+s+"'";
        
        
        try {
            Statement statement = connection
                    .createStatement();
            ResultSet resultat = statement.executeQuery(requete);

            while (resultat.next()) {
                    p.setPrize(resultat.getString("prize"));
    p.setTarget(resultat.getFloat("Target"));
    p.setHelpType(resultat.getString("HelpType"));
    p.settDiscription(resultat.getString("tdiscription"));
    p.setTaskDate(resultat.getDate("taskdate"));
   p.setHtask(resultat.getString("htask"));
   p.setRating(resultat.getFloat("rating1"));
 
      User u = new User () ;
             
              u.setUsername(resultat.getString("username"));
          p.setCreateur(u);
           
              p.setId(resultat.getInt("idproject"));
              p.setName(resultat.getString("Name"));
         
              p.setDiscription(resultat.getString("description"));
                   p.setDate(resultat.getDate("datecreation"));

              p.setFile(resultat.getString("file"));
              p.setFilelink(resultat.getString("filelink"));
               
            
            }
         
        } catch (SQLException ex) {
            System.out.println("erreur lors du chargement des depots " + ex.getMessage());
            return null;
        }
            return p ;
    }
    
    
  public void rate(int i , int rate){ 
    int v1=0 ;
    int v2;

    try {
               String requete1 = "select timerated from project where idproject="+i;
                         String requete2 = "select rating from project where idproject="+i;
                           
                                         Statement statement = connection
                    .createStatement();
                                
                                         
                                         
                                   
                                            
                                                    ResultSet rs1 = statement.executeQuery(requete1);
                                          rs1.next();
                                          
                                          v1=rs1.getInt("timerated");
                                
                                          
                                      
                                            ResultSet rs2 = statement.executeQuery(requete2);
                                          rs2.next();
                                            
                                               v2=rs2.getInt("rating");
                                                
                                                 
                                          v1++;
                                          v2=v2+rate;
                                          
                                                           
                                                           
                                           
                             String req3=   "UPDATE project SET rating= ?, timerated = ? WHERE idproject = ?";  
                                      PreparedStatement ps = connection.prepareStatement(req3);
            ps.setInt(1, v2);
            ps.setInt(2,v1);
            ps.setInt(3, i);
            ps.executeUpdate();
                                         
               
        
        
    }
    catch ( Exception e){
         Logger.getLogger(ProjectDAO.class.getName()).log(Level.SEVERE, null, e);
        
    }
}
  public void  update(Projet p){
            connection = DataSource.getInstance().getConnection();
        


 
        try 
            
        {
            String req ="update project  set name=?, description=? ,type=? where idproject=? ";
               
           
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setString(1, p.getName());
            ps.setString(2,p.getDiscription());
            ps.setString(3, p.getType());
                   ps.setInt(4,p.getId());
        
    

            
              
            ps.executeUpdate();
        } 
        catch (SQLException ex)
        {
            Logger.getLogger(Sujet.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getMessage());
       
        }
  }
  
  
  

    public List<Projet> findAllbyname(String s ) {
              List<Projet> listep = new ArrayList<>();
        String requete = "select fos_user.username as username ,project.*  from fos_user,project where project.iduser=fos_user.id and project.name like '%"+s+"%'";
        try{
            Statement statement = connection
                    .createStatement();
            ResultSet resultat = statement.executeQuery(requete);

            while (resultat.next()) {
               Projet p = new Projet();
              p.setId(resultat.getInt("iduser"));
              p.setName(resultat.getString("Name"));
              User u = new User () ;
              
              u.setUsername(resultat.getString("username"));
          p.setCreateur(u);
    p.setPrize(resultat.getString("prize"));
    p.setTarget(resultat.getFloat("Target"));
    p.setHelpType(resultat.getString("HelpType"));
    p.settDiscription(resultat.getString("tdiscription"));
    p.setTaskDate(resultat.getDate("taskdate"));
   p.setHtask(resultat.getString("htask"));
              
              
            
              
              p.setDiscription(resultat.getString("description"));
                   p.setDate(resultat.getDate("datecreation"));
      p.setFile(resultat.getString("file"));
      p.setFilelink(resultat.getString("filelink"));
      listep.add(p);
            }
        }
            catch (Exception ex) {
                    Alert alert = new Alert(AlertType.INFORMATION);
alert.setTitle("Information Dialog");
alert.setHeaderText("alert");
alert.setContentText(ex.getMessage());

alert.showAndWait();
                    }
     
                return listep;
        
                    }
}
