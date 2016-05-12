/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetostart;

import Classes.Projet;
import Classes.Sujet;
import Classes.User;
import DAO.classes.ProjectDAO;
import DAO.classes.SujetDAO;
import DAO.classes.UserDAO;
import Util.DataSource;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Map;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;

/**
 *
 * @author GSC
 */
public class TimeToStart {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws MalformedURLException, IOException {
        // TODO code application logic here
        //Connection connection = DataSource.getInstance().getConnection();
        
        
      /*try {
        UserDAO fa=new UserDAO();
        User us=new User();
    
        us.setUsername("Raaaaaaaaaid");
        us.setPassword("sqd45");
        us.setPrenom("Raid");
        us.setNom("Raddaoui");
        
          Calendar calendare = Calendar.getInstance();
    java.sql.Date ourJavaDateObject = new java.sql.Date(calendare.getTime().getTime());
           System.out.println(ourJavaDateObject);
        us.setBirthdate(ourJavaDateObject);
          us.setCountry("Canada");
        us.setQualification("programmation");
        us.setBan(1);
        
        fa.CreerCompte(us);
        
      
  }
       catch (Exception E){
         
       }
     
       
       
 
   */
      
     //         UserDAO fa=new UserDAO();
    //UserDAO.Connecter("Raaaaaaaaaid", "sqd45");
    
ProjectDAO p = new ProjectDAO();
p.rate(43
, 3);



         
    } 
    

    }
         
         
