/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.classes;

import Classes.Sujet;
import Classes.User;
import DAO.Interfaces.ISujetDAO;
import Util.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author GSC
 */
public class SujetDAO implements ISujetDAO
{
 private Connection connection;

    public SujetDAO() {
        connection = DataSource.getInstance().getConnection();
    }
    @Override
    public void Ajoutsujet(Sujet s)
    {
        
      
        try 
        {
            String req ="INSERT INTO subject(idsubject,name,content,categories,date,iduser) "
                    + "VALUES(null, '"+s.getName()+"', '"+s.getContent()+"','"+s.getCategories()+"','"+s.getDate()+"','"+s.getCreateur().getId()+"')";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.executeUpdate();
        } 
        catch (SQLException ex)
        {
            Logger.getLogger(Sujet.class.getName()).log(Level.SEVERE, null, ex);
        }
 
    }

    @Override
    public void Supprimersujet(Sujet s)
    {
         try 
        {
            String req =" delete from subject where idsubject ='"+s.getIdsubject()+"'";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.executeUpdate();
        } 
        catch (SQLException ex)
        {
            Logger.getLogger(Sujet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

    @Override
    public void Modifiersujet(Sujet s) 
    {
         try 
        {
            String req ="update subject set name='"+s.getName()+"', content='"+s.getContent()+"',categories='"+s.getCategories()+"',date='"+s.getDate()+"' where idsubject ='"+s.getIdsubject()+"'";
           
            PreparedStatement ps = connection.prepareStatement(req);
            ps.executeUpdate();
        } 
        catch (SQLException ex)
        {
            Logger.getLogger(Sujet.class.getName()).log(Level.SEVERE, null, ex);
        }
     
    }
   
   @Override
    public ObservableList<Sujet> AfficherSujet() {
       
        ObservableList<Sujet> listData = FXCollections.observableArrayList();
            Sujet s = new Sujet();
          
        try {
            String sql = "select * from subject";
            ResultSet rs =connection.createStatement().executeQuery(sql);
            while (rs.next()) {   
            
                s.setIdsubject(rs.getInt(1));
                s.setName(rs.getString(2));
                s.setContent(rs.getString(3));
                s.setCategories(rs.getString(4));
                s.setDate(rs.getDate(5));
//                s.setCreateur((User)rs.getObject(6));
                listData.add(s);
            }
        } catch (Exception ex) {
            Logger.getLogger(Sujet.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listData;
    }
    
   @Override
   public int returnidcr(int id )
   {
       
              System.out.println("dkhalt");
              int x=-1;

    try {
        System.out.println("dkhalt l try ");
            String sql = "select iduser from subject where idsubject='"+id+"'";
            ResultSet rs =connection.createStatement().executeQuery(sql);
            rs.next();
     x=rs.getInt("iduser");
    
        } catch (Exception ex) {
            System.out.println("me dkhaltch l try ");
            Logger.getLogger(Sujet.class.getName()).log(Level.SEVERE, null, ex);
            
        }
    return x ; 
   }
     @Override
    public ObservableList<Sujet> AfficherSujetSelonCategorie(String categ) {
       
        ObservableList<Sujet> listData = FXCollections.observableArrayList();
            
          
        try {
            String sql = "select * from subject where categories='"+categ+"'";
            ResultSet rs =connection.createStatement().executeQuery(sql);
            while (rs.next()) {  
                Sujet s = new Sujet();
            
                s.setIdsubject(rs.getInt(1));
                s.setName(rs.getString(2));
                s.setContent(rs.getString(3));
                s.setCategories(rs.getString(4));
                s.setDate(rs.getDate(5));
           
//                s.setCreateur((User)rs.getObject(6));
                listData.add(s);
            }
        } catch (Exception ex) {
            Logger.getLogger(Sujet.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listData;
    }

  public Map<String,Integer> calculatecat(){ 
    
    Map v = new HashMap<String ,Integer>();
    try {
               String requete1 = "select count(*)  as me from subject where categories='Art'";
                         String requete2 = "select count(*) as me from subject where categories='Social'";
                              String requete3 = "select count(*) as me from subject where categories='Technology'";
                                   String requete4 = "select count(*) as me  from subject where categories='Services'";
                                        String requete5 = "select count(*) as me  from subject where categories='Science'";
                                         Statement statement = connection
                    .createStatement();
                                
                                         
                                         
                                   
                                            
                                                    ResultSet rs1 = statement.executeQuery(requete1);
                                          rs1.next();
                                          
                                          v.put("Art", rs1.getInt("me"));
                                          System.out.println(rs1.getInt("me"));
                                          
                                      
                                            ResultSet rs2 = statement.executeQuery(requete2);
                                          rs2.next();
                                            
                                                v.put("Social", rs2.getInt("me"));
                                                 System.out.println(rs2.getInt("me"));
                                                
                                                      ResultSet rs3 = statement.executeQuery(requete3);
                                                rs3.next();
                                                     
                                                           v.put("Technology", rs3.getInt("me"));
                                                            System.out.println(rs3.getInt("me"));
                                                           
                                                           ResultSet rs4 = statement.executeQuery(requete4);
                                                           rs4.next();
                                                                 
                                                           v.put("Science", rs4.getInt("me"));
                                                            System.out.println(rs4.getInt("me"));
                                                           
                                                             ResultSet rs5 = statement.executeQuery(requete5);
                                          
                                                           rs5.next();
                                                                
                                                           v.put("Services", rs5.getInt("me"));
                                                           
                                                             System.out.println(rs5.getInt("me"));
                                           
                                                
                                         
               
        
        
    }
    catch ( Exception e){
         Logger.getLogger(SujetDAO.class.getName()).log(Level.SEVERE, null, e);
        
    }
    
    return v ;
}

  public Map<String,Integer> calculatecat1(){ 
    
    Map v = new HashMap<String ,Integer>();
    try {
               String requete1 = "select count(*)  as me from subject where categories='Art'";
                         String requete2 = "select count(*) as me from subject where categories='Social'";
                              String requete3 = "select count(*) as me from subject where categories='Technology'";
                                   String requete4 = "select count(*) as me  from subject where categories='Services'";
                                        String requete5 = "select count(*) as me  from subject where categories='Science'";
                                         Statement statement = connection
                    .createStatement();
                                
                                         
                                         
                                   
                                            
                                                    ResultSet rs1 = statement.executeQuery(requete1);
                                          rs1.next();
                                          
                                          v.put("Art", rs1.getInt("me"));
                                          System.out.println(rs1.getInt("me"));
                                          
                                      
                                            ResultSet rs2 = statement.executeQuery(requete2);
                                          rs2.next();
                                            
                                                v.put("Social", rs2.getInt("me"));
                                                 System.out.println(rs2.getInt("me"));
                                                
                                                      ResultSet rs3 = statement.executeQuery(requete3);
                                                rs3.next();
                                                     
                                                           v.put("Technology", rs3.getInt("me"));
                                                            System.out.println(rs3.getInt("me"));
                                                           
                                                           ResultSet rs4 = statement.executeQuery(requete4);
                                                           rs4.next();
                                                                 
                                                           v.put("Science", rs4.getInt("me"));
                                                            System.out.println(rs4.getInt("me"));
                                                           
                                                             ResultSet rs5 = statement.executeQuery(requete5);
                                          
                                                           rs5.next();
                                                                
                                                           v.put("Services", rs5.getInt("me"));
                                                           
                                                             System.out.println(rs5.getInt("me"));
                                           
                                                
                                         
               
        
        
    }
    catch ( Exception e){
         Logger.getLogger(SujetDAO.class.getName()).log(Level.SEVERE, null, e);
        
    }
    
    return v ;
}
    
}
