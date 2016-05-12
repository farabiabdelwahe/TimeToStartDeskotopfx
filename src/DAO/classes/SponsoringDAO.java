/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.classes;

import Classes.Feedback;
import Classes.Projet;
import Classes.Sponsoring;
import Classes.Sujet;
import Classes.User;
import Util.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author GSC
 */
public class SponsoringDAO {
   private Connection connection;

    public SponsoringDAO() {
        connection = DataSource.getInstance().getConnection();
        
    }
  
    public boolean createsfponsoring(Sponsoring  p) {
 
        try 
            
        {
            String req ="INSERT INTO Sponsoring(idproject,iduser,sum,date) "
                    + "VALUES(?,?,?,?)";
           
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1, p.getProjet().getId());
            ps.setInt(2,p.getSponsor().getId());
         
                   ps.setDate(4,p.getDate());
            
         if (p.getSum()== null) {
        ps.setNull(9,0);
    } else {
        ps.setFloat(9,p.getSum());
    }

            
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
    
    
       public boolean createshponsoring(Sponsoring  p) {
 
        try 
            
        {
            String req ="INSERT INTO Sponsoring(idproject,iduser,isgoing,date) "
                    + "VALUES(?,?,?,?)";
           
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1, p.getProjet().getId());
            ps.setInt(2,p.getSponsor().getId());
            ps.setInt(3, 1);
         
                   ps.setDate(4,p.getDate());
            


            
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
    
            public boolean createstponsoring(Sponsoring  p) throws SQLException {
 
       
            String req ="INSERT INTO Sponsoring(idproject,iduser,text,date,file,filelink,type) "
                    + "VALUES(?,?,?,?,?,?,?)";
           
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1, p.getProjet().getId());
             ps.setString(7, p.getProjet().getHelpType());
            ps.setInt(2,p.getSponsor().getId());
      
           ps.setString(3,p.getText());
         
                   ps.setDate(4,p.getDate());
                    ps.setString(5,p.getFile());
                     ps.setString(6,p.getFilelint());
                
                   
            
  

            
            ps.executeUpdate();
            return true;
        } 
            
            
                  public List<Sponsoring> findAllbyprojectid(Integer s) {
              List<Sponsoring> listep = new ArrayList<>();
        String requete = "select Sponsoring.*,fos_user.username as owner  from Sponsoring,fos_user,project where"
                + "  Sponsoring.iduser=fos_user.id  and project.idproject=Sponsoring.idproject and project.idproject="+s;
        try {
            Statement statement = connection
                    .createStatement();
            ResultSet resultat = statement.executeQuery(requete);

            while (resultat.next()) {
            User o= new User();
            o.setUsername(resultat.getString("owner"));
            Sponsoring p =new Sponsoring();
            p.setSponsor(o);
            p.setText(resultat.getString("Text"));
                p.setDate(resultat.getDate("date"));
                p.setFile(resultat.getString("file"));
                   p.setFilelint(resultat.getString("filelink"));
                   p.setId(resultat.getInt("idsponsor"));
                
            
            
            
          
               
                listep.add(p);
            }
         
        } catch (SQLException ex) {
            System.out.println("erreur lors du chargement des depots " + ex.getMessage());
            return null;
        }
        return listep;
  
    }
               
                  
                  
                  
                  
                      public Sponsoring findSponsbyid(int s) {
                Sponsoring p = new Sponsoring();
        String requete = "Select * From  Sponsoring where idsponsor="+s;
        
        
        try {
            Statement statement = connection
                    .createStatement();
            ResultSet resultat = statement.executeQuery(requete);

            while (resultat.next()) {
   
 
 
          
           
              p.setId(resultat.getInt("idSponsor"));
              p.setFile(resultat.getString("file"));
              
             p.setText(resultat.getString("Text"));
                p.setDate(resultat.getDate("date"));
                p.setFile(resultat.getString("file"));
                   p.setFilelint(resultat.getString("filelink"));
           
               
            
            }
         
        } catch (SQLException ex) {
            System.out.println("erreur lors du chargement des depots " + ex.getMessage());
            return null;
        }
            return p ;
    }
    
}
    
    

