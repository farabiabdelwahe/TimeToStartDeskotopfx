/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.classes;

import Classes.Sujet;
import Classes.User;
import DAO.Interfaces.IUserDAO;
import Util.DataSource;
import static com.sun.jndi.toolkit.dir.SearchFilter.format;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Int;

import java.io.UnsupportedEncodingException;
import static java.lang.String.format;
import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import static java.text.MessageFormat.format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeTableColumn.CellDataFeatures;
import javax.mail.MessagingException;
import javax.security.auth.callback.Callback;
import GUI.Controllers.MailClass;
import GUI.Controllers.Protocole;
import GUI.Controllers.Protocole;
import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;


/**
 *
 * @author Mohamed Raid Raddaou
 */
public class UserDAO implements IUserDAO {
     private int port = 465;
private String host = "smtp.example.com";
private String from = "matt@example.com";
private boolean auth = true;
private String username = "matt@example.com";
private String password = "secretpw";
private Protocole protocol = Protocole.SMTPS;
private boolean debug = true;
    private  static Connection connection;
        
   private TableView tableview;
private ObservableList<ObservableList> data;

    public UserDAO() {
        connection = DataSource.getInstance().getConnection();
    }

     
    @Override
    public void CreerCompte(Classes.User u) {

if (u.getUsername().equals("") || u.getPassword().equals("") || u.getPrenom().equals("") || u.getNom().equals("")  || u.getBirthdate().toString().equals("") || u.getCountry().equals("") || u.getQualification().equals("") || u.getEmail().equals(""))
{
    System.out.println("Fill All TextField Please");
}    
else  {
    if (u.getEmail().contains("@"))
    {
    try{
 
       
      
            String req ="INSERT INTO fos_user(username,password,firstname,lastname,birthdate,country,qualification,email) "
                    + "VALUES('"+u.getUsername()+"', '"+u.getPassword()+"','"+u.getPrenom()+"','"+u.getNom()+"','"+u.getBirthdate()+"', '"+u.getCountry()+"', '"+u.getQualification()+"','"+u.getEmail()+"')";
          PreparedStatement ps = connection.prepareStatement(req);
            ps.executeUpdate();
        } 
        catch (SQLException ex)
        {
            Logger.getLogger(Sujet.class.getName()).log(Level.SEVERE, null, ex);
        }
}else System.out.println("invalid Email Address");
}

    
    }
    
     public void CreerComptemodif(Classes.User u) {


        try{
 
       
      
            String req ="UPDATE fos_user SET  username='"+u.getUsername()+"',password='"+u.getPassword()+"',firstname= '"+u.getPrenom()+"',lastname= '"+u.getNom()+"', country = '"+u.getCountry()+"',qualification= '"+u.getQualification()+"',email= '"+u.getEmail()+"' WHERE firstname='"+u.getPrenom()+"' ";
          PreparedStatement ps = connection.prepareStatement(req);
            ps.executeUpdate();
        } 
        catch (SQLException ex)
        {
            Logger.getLogger(Sujet.class.getName()).log(Level.SEVERE, null, ex);
        }
     
    
    }
     
     
      public void updateComptemodif(Classes.User u) {


        try{
 
       
      
            String req ="UPDATE fos_user SET  username='"+u.getUsername()+"',password='"+u.getPassword()+"',firstname= '"+u.getPrenom()+"',lastname= '"+u.getNom()+"', country = '"+u.getCountry()+"',qualification= '"+u.getQualification()+"',email= '"+u.getEmail()+"' WHERE username='"+u.getNom()+"' ";
          PreparedStatement ps = connection.prepareStatement(req);
            ps.executeUpdate();
        } 
        catch (SQLException ex)
        {
            Logger.getLogger(Sujet.class.getName()).log(Level.SEVERE, null, ex);
        }
     
    
    }
    
      public void CreerComptemodiffacebook(Classes.User u) {


        try{
 
       
      
           String req ="INSERT INTO fos_user(username,password,firstname,lastname,country,qualification,email,facebookid) "
                    + "VALUES('"+u.getNom()+"', '"+u.getPassword()+"','"+u.getPrenom()+"','"+u.getNom()+"', '"+u.getCountry()+"', '"+u.getQualification()+"','"+u.getEmail()+"','"+u.getFbid()+"')";
          PreparedStatement ps = connection.prepareStatement(req);
            ps.executeUpdate();
        } 
        catch (SQLException ex)
        {
            Logger.getLogger(Sujet.class.getName()).log(Level.SEVERE, null, ex);
        }
     
    
    }
    
     
    @Override
    public String trouver() {

String mail = "";
        try{
 
       
      
                String req ="SELECT * FROM fos_user";
         
          PreparedStatement ps = connection.prepareStatement(req);
            ps.executeQuery();
             ResultSet rs =connection.createStatement().executeQuery(req);
              if(rs.next()){
                mail=rs.getString("email");
                  System.out.println(mail);}
        } 
        catch (SQLException ex)
        {
            Logger.getLogger(Sujet.class.getName()).log(Level.SEVERE, null, ex);
        }
 return mail;
    
    }
public User Connecter(String username) throws SQLException {
       String loged;
  if (username.equals(""))
      return null;
  else {
     
            String req ="SELECT * FROM fos_user where username ='"+username+"'  " ;
                   
          PreparedStatement ps = connection.prepareStatement(req);
            ps.executeQuery();
                 ResultSet rs2 =connection.createStatement().executeQuery(req);
                 
                  if(rs2.next()){
                loged="Does Exist";
                 
                  User us = new User();
                  us.setUsername(rs2.getString("username"));
                   us.setId(rs2.getInt("id"));
         us.setPassword(rs2.getString("password"));
        us.setPrenom(rs2.getString("firstname"));
        us.setNom(rs2.getString("lastname"));
        us.setEmail(rs2.getString("email"));
        us.setBirthdate(rs2.getDate("Birthdate"));
        
        
          us.setCountry(rs2.getString("country"));
        us.setQualification(rs2.getString("qualification"));
        us.setBan(rs2.getInt("ban"));
                   return us ;}
       else 
                  {   loged="Login does not exist";
return null;}}
   
    }
    
    public User Connecter2(String password) throws SQLException {
       String loged;
  
       if (password.equals(""))
     return null;
       else{
            String req ="SELECT * FROM fos_user where password='"+password+"' " ;
                   
          PreparedStatement ps = connection.prepareStatement(req);
            ps.executeQuery();
                 ResultSet rs2 =connection.createStatement().executeQuery(req);
                 
                  if(rs2.next()){
                loged="Does Exist";
                 
                  User us = new User();
                  us.setUsername(rs2.getString("username"));
         us.setPassword(rs2.getString("password"));
        us.setPrenom(rs2.getString("firstname"));
        us.setNom(rs2.getString("lastname"));
        us.setEmail(rs2.getString("email"));
        us.setBirthdate(rs2.getDate("Birthdate"));
        
        
          us.setCountry(rs2.getString("country"));
        us.setQualification(rs2.getString("qualification"));
        us.setBan(rs2.getInt("ban"));
                   return us ;}
       else 
                  {   loged="Login does not exist";
return null;}}
   
    }

  
    public User ModifierProfile(User e) throws Exception {

           String req ="SELECT * FROM fos_user WHERE  username ='"+e.getUsername()+"'" ;
                   
          PreparedStatement ps = connection.prepareStatement(req);
            ps.executeQuery();
                 ResultSet rs2 =connection.createStatement().executeQuery(req);
        if(rs2.next()){
                User us = new User();
            us.setId(Integer.parseInt(rs2.getString("id")));
              us.setUsername(rs2.getString("username"));
         us.setPassword(rs2.getString("password"));
        us.setPrenom(rs2.getString("firstname"));
        us.setNom(rs2.getString("lastname"));
        us.setEmail(rs2.getString("email"));
        us.setBirthdate(rs2.getDate("Birthdate"));
      
        
          us.setCountry(rs2.getString("country"));
        us.setQualification(rs2.getString("qualification"));
        us.setBan(rs2.getInt("ban"));
        return us;}
        else return null;


    }
    
     public User updating(User e) throws Exception {

         
           String req ="SELECT * FROM fos_user WHERE  id ='"+e.getId()+"'" ;
                   
          PreparedStatement ps = connection.prepareStatement(req);
            ps.executeQuery();
                 ResultSet rs2 =connection.createStatement().executeQuery(req);
        if(rs2.next()){
                User us = new User();
           us.setId(Integer.parseInt(rs2.getString("id")));
              us.setUsername(rs2.getString("username"));
         us.setPassword(rs2.getString("password"));
        us.setPrenom(rs2.getString("firstname"));
        us.setNom(rs2.getString("lastname"));
        us.setEmail(rs2.getString("email"));
        us.setBirthdate(rs2.getDate("Birthdate"));
        
        
          us.setCountry(rs2.getString("country"));
        us.setQualification(rs2.getString("qualification"));
        us.setBan(rs2.getInt("ban"));
        return us;}
        else return null;


    }
    

  
 

    @Override
 public void AjouterMembre(String nom, int id){
        try{
 
       
      
            String req ="INSERT INTO fos_user (lastname, fbid) VALUES ( '" + nom + "', '" + id+"');";
          PreparedStatement ps = connection.prepareStatement(req);
            ps.executeUpdate();
        } 
        catch (SQLException ex)
        {
            Logger.getLogger(Sujet.class.getName()).log(Level.SEVERE, null, ex);
        }
     
     
       
      
     
    }
 
  public void savingfbuser(String fusername, String fid){
        try{
 
       
      
            String req ="INSERT INTO fos_user (username, id) VALUES ( '" + fusername + "', '" + fid+"');";
          PreparedStatement ps = connection.prepareStatement(req);
            ps.executeUpdate();
        } 
        catch (SQLException ex)
        {
            Logger.getLogger(Sujet.class.getName()).log(Level.SEVERE, null, ex);
        }
     
     
       
      
     
    }
  
   public User ConnecterFb(String id) throws SQLException {
       
  
       
     
            String req ="SELECT * FROM fos_user WHERE  facebookid ='"+id+"'" ;
                   
          PreparedStatement ps = connection.prepareStatement(req);
            ps.executeQuery();
                 ResultSet rs2 =connection.createStatement().executeQuery(req);
                 
                  if(rs2.next()){
                User us = new User();
            us.setId(Integer.parseInt(rs2.getString("id")));
              us.setUsername(rs2.getString("username"));
         us.setPassword(rs2.getString("password"));
        us.setPrenom(rs2.getString("firstname"));
        us.setNom(rs2.getString("lastname"));
        us.setEmail(rs2.getString("email"));
        us.setBirthdate(rs2.getDate("Birthdate"));
        us.setFbid(rs2.getString("facebookid"));
        
          us.setCountry(rs2.getString("country"));
        us.setQualification(rs2.getString("qualification"));
        us.setBan(rs2.getInt("ban"));
        return us;}
                else return null;
                 


   
    }


  public User fbusers(String username,String id) throws SQLException{
   
    
       
      
            String req ="INSERT INTO fos_user (username, facebookid) VALUES ( '" +username+ "', '" +id+ "');";
          PreparedStatement ps = connection.prepareStatement(req);
            ps.executeUpdate();
            
             String req2 ="SELECT * FROM fos_user WHERE  username ='"+username+"' and facebookid='"+id+"'" ;
                   
          PreparedStatement ps2;
            ps2 = connection.prepareStatement(req2);
        
            ps2.executeQuery(); 
            ResultSet rs2 =connection.createStatement().executeQuery(req2);
         
             if(rs2.next()){
             User us = new User();
           
              us.setUsername(username);
         us.setPassword(rs2.getString("password"));
        us.setPrenom(rs2.getString("firstname"));
        us.setNom(rs2.getString("lastname"));
        us.setEmail(rs2.getString("email"));
        us.setBirthdate(rs2.getDate("Birthdate"));
         us.setFbid(rs2.getString("facebookid"));
        
          us.setCountry(rs2.getString("country"));
        us.setQualification(rs2.getString("qualification"));
        us.setBan(rs2.getInt("ban"));
    return us;}
             else return null;
    
     
    }
     
    public  User getByUserName(String username) throws Exception {
   

   
       
         
         String req2 ="SELECT * FROM fos_user WHERE  username ='"+username+"'" ;
                   
          PreparedStatement ps2;
            ps2 = connection.prepareStatement(req2);
        
            ps2.executeQuery(); 
            ResultSet rs2 =connection.createStatement().executeQuery(req2);
         
             if(rs2.next()){
             User us = new User();
           
              us.setUsername(username);
              us.setId(Integer.parseInt(rs2.getString("id")));
         us.setPassword(rs2.getString("password"));
        us.setPrenom(rs2.getString("username"));
        us.setNom(rs2.getString("username"));
        us.setEmail(rs2.getString("email"));
        us.setBirthdate(rs2.getDate("Birthdate"));
        
        
          us.setCountry(rs2.getString("country"));
        us.setQualification(rs2.getString("qualification"));
        us.setBan(rs2.getInt("ban"));
             
                 System.out.println("User found");
                 
                
             return us;             
             }
             else 
             {System.out.println("User not found ");
                         return null;}
            
    
     
  
    }
    /*
    @Override
   public void buildData(String username) {



          data = FXCollections.observableArrayList();

         
   try {
         String req2 ="SELECT * FROM user WHERE  username ='"+username+"'" ;
                   
          PreparedStatement ps2;
        
            ps2 = connection.prepareStatement(req2);
              ps2.executeQuery(); 
            ResultSet rs2 =connection.createStatement().executeQuery(req2);
      
        
          
 
 

            /**********************************

             * TABLE COLUMN ADDED DYNAMICALLY *

             **********************************/
/*
            for(int i=0 ; i<rs2.getMetaData().getColumnCount(); i++){

                //We are using non property style for making dynamic table

                final int j = i;               

                TableColumn col = new TableColumn(rs2.getMetaData().getColumnName(i+1));

                col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList,String>,ObservableValue<String>>(){                   

                    public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {                                                                                             

                        return new SimpleStringProperty(param.getValue().get(j).toString());                       

                    }                   

                });
  } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
                

                tableview.getColumns().addAll(col);
                System.out.println("Column ["+i+"] ");

            } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

}

*/
    
   
    
    public  List<User>  findAllbyuser(String usern) {
    List<User> listep = new ArrayList<>();
    
 try {
     
         String req2 ="SELECT * FROM fos_user WHERE  username LIKE '%"+usern+"%'" ;
                   
          PreparedStatement ps2;
        
            ps2 = connection.prepareStatement(req2);
      
            ps2.executeQuery(); 
            ResultSet rs2 =connection.createStatement().executeQuery(req2);
         
             while(rs2.next()){
             User us = new User();
             us.setId(Integer.parseInt(rs2.getString("id")));
        us.setUsername(rs2.getString("username"));
        us.setPrenom(rs2.getString("username"));
        us.setNom(rs2.getString("username"));
        us.setEmail(rs2.getString("email"));
        

     listep.add(us);
    }
               } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
  return listep;
}
    
    public static boolean sendml(String to, String sub,String msg, final String user, final String pass) 
    {
        Properties props = new Properties();

        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");	
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        
        Session session = Session.getDefaultInstance(props,new javax.mail.Authenticator() 
        {
            @Override
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() 
            {
                return new javax.mail.PasswordAuthentication(user, pass);
            }
        });

        try 
        {
            Message message = new MimeMessage(session);
            
            message.setFrom(new InternetAddress(user));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(sub);
            message.setText(msg);

            Transport.send(message);
            
            return true;
            
        } catch (MessagingException e) 
        {
            return false;
       
        }
        
    }

}