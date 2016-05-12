/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.classes;

import Classes.Contribution;
import Classes.Sujet;
import Classes.User;
import DAO.Interfaces.IContributionDAO;
import Util.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Youssef
 */
public class ContributionDAO implements IContributionDAO{
     private Connection connection;
    
    public ContributionDAO() {
       connection = DataSource.getInstance().getConnection();
    }

    @Override
    public void AjoutContribution(Contribution c)
    {
         try 
        {
            String req ="INSERT INTO contribution(idcontribution,content,date,idsubject,iduser) "
                    + "VALUES('"+c.getIdcontribution()+"', '"+c.getContent()+"', '"+c.getDate()+"','"+c.getSujet().getIdsubject()+"','"+c.getUser().getId()+"')";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.executeUpdate();
        } 
        catch (SQLException ex)
        {
            Logger.getLogger(Contribution.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void SupprimerContribution(Contribution c) 
    {
        System.out.print(c.getIdcontribution());
          try 
        {
            String req =" delete from contribution where idcontribution ='"+c.getIdcontribution()+"'";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.executeUpdate();
        } 
        catch (SQLException ex)
        {
            Logger.getLogger(Contribution.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

    @Override
    public void ModifierContribution(Contribution c)
    {
         try 
        {
          String req ="update contribution set content='"+c.getContent()+"' where idcontribution ='"+c.getIdcontribution()+"'";
               PreparedStatement ps = connection.prepareStatement(req);
            ps.executeUpdate();
        } 
        catch (SQLException ex)
        {
            Logger.getLogger(Contribution.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    @Override
    public ObservableList<Contribution> AfficherContribution(int id )
    {
        
         
        ObservableList<Contribution> listData = FXCollections.observableArrayList();
          
        try {
            
        
            String sql = "select contribution.* ,fos_user.username as owner      from contribution ,fos_user where contribution.iduser=fos_user.id and  contribution.idsubject='"+id+"'";
            ResultSet rs =connection.createStatement().executeQuery(sql);
            while (rs.next())
            {   Contribution cont = new Contribution();
                User u = new User();
                u.setUsername(rs.getString("owner"));
                cont.setUser(u);
                cont.setIdcontribution(rs.getInt(1));
                cont.setContent(rs.getString(2));
                cont.setDate(rs.getDate(3)); 
               // cont.setSujet((Sujet)rs.getObject(4));
             //   cont.setUser((User)rs.getObject(5));
                  listData.add(cont);
             }
            
        }
        
                 catch (SQLException ex) {
             Logger.getLogger(ContributionDAO.class.getName()).log(Level.SEVERE, null, ex);                 

              
            }
        return listData;
        
    }


    @Override
    public int commentateur(int c)
    {
      
              System.out.println("dkhalt");
              int x=-1;

    try {
        System.out.println("dkhalt l try ");
            String sql = "select iduser from contribution where idcontribution='"+c+"'";
            ResultSet rs =connection.createStatement().executeQuery(sql);
            rs.next();
     x=rs.getInt("iduser");
    
        } catch (Exception ex) {
            System.out.println("me dkhaltch l try ");
            Logger.getLogger(Sujet.class.getName()).log(Level.SEVERE, null, ex);
            
        }
    return x ; 
    }
    
}
