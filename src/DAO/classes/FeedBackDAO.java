/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.classes;



import Classes.Feedback;
import Classes.User;
import Util.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Karray
 */
public class FeedBackDAO{

    private Connection connection;

    public FeedBackDAO() {
        connection = DataSource.getInstance().getConnection();
    }


    public void addFeedback(Feedback st) {

        String requete = "insert into Feedback(text,date,iduser,idproject) values (?,?,?,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(requete);
            ps.setString(1, st.getText());
            ps.setDate(2, st.getDate());
            ps.setInt(3, st.getEditeur().getId());
             ps.setInt(4, st.getProjet().getId());
            
            ps.executeUpdate();
            System.out.println("Ajout effectué avec succès");
        } catch (SQLException ex) {
            System.out.println("erreur lors de l'insertion " + ex.getMessage());
        }
    }
    

    
        public List<Feedback> findAllbyprojectName(String s) {
              List<Feedback> listep = new ArrayList<>();
        String requete = "select feedback.*,user.username as owner  from feedback,user,project where"
                + "  feedback.iduser=user.iduser  and project.idproject=feedback.idproject and project.name='"+s+"'";
        try {
            Statement statement = connection
                    .createStatement();
            ResultSet resultat = statement.executeQuery(requete);

            while (resultat.next()) {
            User o= new User();
            o.setUsername(resultat.getString("owner"));
            Feedback p =new Feedback ();
            p.setText(resultat.getString("Text"));
                p.setDate(resultat.getDate("date"));
                p.setEditeur(o);
            
            
            
          
               
                listep.add(p);
            }
         
        } catch (SQLException ex) {
            System.out.println("erreur lors du chargement des depots " + ex.getMessage());
            return null;
        }
           return listep;
    }
    
        
             public List<Feedback> findAllbyprojectid(Integer s) {
              List<Feedback> listep = new ArrayList<>();
        String requete = "select feedback.*,fos_user.username as owner  from feedback,fos_user,project where"
                + "  feedback.iduser=fos_user.id  and project.idproject=feedback.idproject and project.idproject="+s;
        try {
            Statement statement = connection
                    .createStatement();
            ResultSet resultat = statement.executeQuery(requete);

            while (resultat.next()) {
            User o= new User();
            o.setUsername(resultat.getString("owner"));
            Feedback p =new Feedback ();
            p.setText(resultat.getString("Text"));
                p.setDate(resultat.getDate("date"));
                p.setEditeur(o);
            
            
            
          
               
                listep.add(p);
            }
         
        } catch (SQLException ex) {
            System.out.println("khlifa " + ex.getMessage());
            return null;
        }
           return listep;
    }
}

   
