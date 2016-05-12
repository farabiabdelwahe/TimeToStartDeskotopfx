/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.classes;

import Classes.Sujet;
import Classes.User;
import DAO.Interfaces.IUserDAO;
import GUI.Controllers.Facebookuser;
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
import GUI.Controllers.savedusers;
import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.security.MessageDigest;
import java.security.SecureRandom;
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
public class UserDAO  implements IUserDAO{
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

public static String hashPassword(String password, String salt) throws Exception {
        String result = password;
        String res1="";
        String res2="";
        String appendedSalt = new StringBuilder().append('{').append(salt).append('}').toString();
        String appendedSalt2 = new StringBuilder().append(password).append('{').append(salt).append('}').toString();
        if(password != null) {
            //Security.addProvider(new BouncyCastleProvider());
            MessageDigest mda = MessageDigest.getInstance("SHA-512");
            byte[] pwdBytes = password.getBytes("UTF-8");
            byte[] saltBytes = appendedSalt.getBytes("UTF-8");
            byte[] saltBytes2 = appendedSalt2.getBytes("UTF-8");
            byte[] digesta = encode(mda, pwdBytes, saltBytes);
            //result = new String(digesta);
             
//           System.out.println("first hash: " + new String(new sun.misc.BASE64Encoder().encode(digesta)));
                for (int i = 1; i < 5000; i++) {
                    digesta = encode(mda, digesta, saltBytes2);
                }
//                System.out.println("last hash: " + new String(new sun.misc.BASE64Encoder().encode(digesta)));
                result = new String(new sun.misc.BASE64Encoder().encode(digesta));
                
                //concaténation pour avoir le code adequqt émoticône tongue
                res1= result.substring(0,77).trim();
                res2=result.substring(78,90);
                res2=res1.concat(res2);
        }
        return res2;
    }
private static byte[] encode(MessageDigest mda, byte[] pwdBytes,byte[] saltBytes) {
        mda.update(pwdBytes);
        byte [] digesta = mda.digest(saltBytes);
        return digesta;
    }



     
    @Override
    public void CreerCompte(Classes.User val) {
        if (Facebookuser.fbu !=null){
//         String f=  Facebookuser.fbu.getFbid();
    System.out.println("*************************------------------/////////"+Facebookuser.fbu.getFbid());
        System.out.println("username"+val.getUsername());
         System.out.println("firstname"+val.getFirstname());
          System.out.println("lastname"+val.getLastname());
           System.out.println("birth"+val.getBirthdate());
            System.out.println("country"+val.getCountry());
             System.out.println("qual"+val.getQualification());
              System.out.println("email"+val.getEmail());
            


    
        
    
        int en=1;
        int lock=0;
       
        String pwd = "";

        try {
             final Random r = new SecureRandom();
byte[] salt = new byte[32];
r.nextBytes(salt);
String encodedSalt = new sun.misc.BASE64Encoder().encode(salt);
encodedSalt=encodedSalt.substring(0,31); 
            System.out.println("ddddddddddddddddddddddddd"+val.getPassword());
 pwd= hashPassword(val.getPassword(),encodedSalt);
               System.out.println("tryyyyyyyyyy"+val.getPassword());
             String requete = "INSERT INTO fos_user(username,username_canonical,email,email_canonical,enabled,salt,locked,expired,credentials_expired,password,firstname,country,birthdate,facebookid) "
                     + ""
                    + "VALUES('"+val.getUsername()+"', '"+val.getUsername()+"','"+val.getEmail()+"','"+val.getEmail()+"','"+en+"','"+encodedSalt+"','"+lock+"','"+lock+"','"+lock+"','"+pwd+"','"+val.getFirstname()+"', '"+val.getCountry()+"', '"+val.getBirthdate()+"', '"+Facebookuser.fbu.getFbid()+"')";
             PreparedStatement ps = connection.prepareStatement(requete);
            ps.executeUpdate();
            System.out.println("ddddddddddddddddddddddddd"+val.getPassword());
             
           
            
            
            
 
//            ps.setString(1, val.getUsername());
//            ps.setString(2, val.getUsername());
//            ps.setString(3, val.getEmail());
//            ps.setString(4, val.getEmail());
//            ps.setString(5,"1");
//            ps.setString(6,encodedSalt);
//            ps.setString(10,pwd);
//            ps.setString(7,"0");
//            ps.setString(8,"0");
//            
//            
//            ps.setString(12, val.getCountry());
//            ps.setString(11, val.getPrenom());
//            ps.setDate(13, (Date) val.getBirthdate());
////            ps.setString(15, "a:1:{i:0;s:11:\"ROLE_CLIENT\";}");
           
            ps.executeUpdate(); 
     
            ResultSet rs1 = ps.getGeneratedKeys();
           
          

            
        } catch (Exception ex) {
            System.out.println(ex);
//        } catch (SQLException ex) {
//           System.out.println(ex);
//        } catch (Exception ex) {
//        Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
//    }
//        
//        
        
        }
        
}
        else { 
        System.out.println("username"+val.getUsername());
         System.out.println("firstname"+val.getFirstname());
          System.out.println("lastname"+val.getLastname());
           System.out.println("birth"+val.getBirthdate());
            System.out.println("country"+val.getCountry());
             System.out.println("qual"+val.getQualification());
              System.out.println("email"+val.getEmail());
            


    
        
    
        int en=1;
        int lock=0;
       
        String pwd = "";

        try {
             final Random r = new SecureRandom();
byte[] salt = new byte[32];
r.nextBytes(salt);
String encodedSalt = new sun.misc.BASE64Encoder().encode(salt);
encodedSalt=encodedSalt.substring(0,31); 
            System.out.println("ddddddddddddddddddddddddd"+val.getPassword());
 pwd= hashPassword(val.getPassword(),encodedSalt);
               System.out.println("tryyyyyyyyyy"+val.getPassword());
             String requete = "INSERT INTO fos_user(username,username_canonical,email,email_canonical,enabled,salt,locked,expired,credentials_expired,password,firstname,country,birthdate,qualification) "
                     + ""
                    + "VALUES('"+val.getUsername()+"', '"+val.getUsername()+"','"+val.getEmail()+"','"+val.getEmail()+"','"+en+"','"+encodedSalt+"','"+lock+"','"+lock+"','"+lock+"','"+pwd+"','"+val.getFirstname()+"', '"+val.getCountry()+"', '"+val.getBirthdate()+"', '"+val.getQualification()+"')";
             PreparedStatement ps = connection.prepareStatement(requete);
            ps.executeUpdate();
            System.out.println("ddddddddddddddddddddddddd"+val.getPassword());
            ps.executeUpdate(); 
     
            ResultSet rs1 = ps.getGeneratedKeys();
           
          
                       
if (rs1.next()){
//    idpersonne=rs1.getInt(1);
}  
            
        } catch (Exception ex) {
            System.out.println(ex);
//        } catch (SQLException ex) {
//           System.out.println(ex);
//        } catch (Exception ex) {
//        Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
//    }
//        
//        
        
        }
        
    }
         savedusers.savedlogedin=val;
    }  
//    try{
// 
//       
//      
//            String req ="INSERT INTO fos_user(username,password,firstname,lastname,birthdate,country,qualification,email) "
//                    + "VALUES('"+u.getUsername()+"', '"+u.getPassword()+"','"+u.getPrenom()+"','"+u.getNom()+"','"+u.getBirthdate()+"', '"+u.getCountry()+"', '"+u.getQualification()+"','"+u.getEmail()+"')";
//          PreparedStatement ps = connection.prepareStatement(req);
//            ps.executeUpdate();
//        } 
//        catch (SQLException ex)
//        {
//            Logger.getLogger(Sujet.class.getName()).log(Level.SEVERE, null, ex);
//        }


    
    
    
     public void CreerComptemodif(Classes.User u) {


        
 
       
        int en=1;
        int lock=0;
       
        String pwd = "";

        try {
             final Random r = new SecureRandom();
byte[] salt = new byte[32];
r.nextBytes(salt);
String encodedSalt = new sun.misc.BASE64Encoder().encode(salt);
encodedSalt=encodedSalt.substring(0,31); 
            System.out.println("ddddddddddddddddddddddddd"+u.getPassword());
 pwd= hashPassword(u.getPassword(),encodedSalt);
               System.out.println("tryyyyyyyyyy"+u.getPassword());
               
  
             String requete = "UPDATE fos_user SET username='"+u.getUsername()+"',username_canonical='"+u.getUsername()+"',email='"+u.getEmail()+"',email_canonical='"+u.getEmail()+"',enabled='"+en+"',salt='"+encodedSalt+"',locked='"+lock+"',expired='"+lock+"',credentials_expired='"+lock+"',password='"+pwd+"',firstname='"+u.getFirstname()+"',country='"+u.getCountry()+"',birthdate='"+u.getBirthdate()+"')";
             PreparedStatement ps = connection.prepareStatement(requete);
            ps.executeUpdate();
            System.out.println("ddddddddddddddddddddddddd"+u.getPassword());
             
           
            
            
            
 
//            ps.setString(1, val.getUsername());
//            ps.setString(2, val.getUsername());
//            ps.setString(3, val.getEmail());
//            ps.setString(4, val.getEmail());
//            ps.setString(5,"1");
//            ps.setString(6,encodedSalt);
//            ps.setString(10,pwd);
//            ps.setString(7,"0");
//            ps.setString(8,"0");
//            
//            
//            ps.setString(12, val.getCountry());
//            ps.setString(11, val.getPrenom());
//            ps.setDate(13, (Date) val.getBirthdate());
////            ps.setString(15, "a:1:{i:0;s:11:\"ROLE_CLIENT\";}");
           
            ps.executeUpdate(); 
     
            ResultSet rs1 = ps.getGeneratedKeys();
           
          
                       
if (rs1.next()){
//    idpersonne=rs1.getInt(1);
}  
            
        } catch (Exception ex) {
            System.out.println(ex);
//        } catch (SQLException ex) {
//           System.out.println(ex);
//        } catch (Exception ex) {
//        Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
//    }
//        
//        
        
        }
        
}
      
//            String req ="UPDATE fos_user SET  password='"+u.getPassword()+"',firstname= '"+u.getFirstname()+"',lastname= '"+u.getLastname()+"', country = '"+u.getCountry()+"',qualification= '"+u.getQualification()+"',email= '"+u.getEmail()+"' WHERE firstname='"+u.getFirstname()+"' ";
//          PreparedStatement ps = connection.prepareStatement(req);
//            ps.executeUpdate();
//        } 
//        catch (SQLException ex)
//        {
//            Logger.getLogger(Sujet.class.getName()).log(Level.SEVERE, null, ex);
//        }
     
    
    
     
     
      public void updateComptemodif(Classes.User u) {


       
       
        int en=1;
        int lock=0;
       
        String pwd = "";

        try {
             final Random r = new SecureRandom();
byte[] salt = new byte[32];
r.nextBytes(salt);
String encodedSalt = new sun.misc.BASE64Encoder().encode(salt);
encodedSalt=encodedSalt.substring(0,31); 
            System.out.println("ddddddddddddddddddddddddd"+u.getPassword());
 pwd= hashPassword(u.getPassword(),encodedSalt);
               System.out.println("tryyyyyyyyyy"+u.getPassword());
               
  
             String requete = "UPDATE fos_user SET username='"+u.getUsername()+"',username_canonical='"+u.getUsername()+"',email='"+u.getEmail()+"',email_canonical='"+u.getEmail()+"',enabled='"+en+"',salt='"+encodedSalt+"',locked='"+lock+"',expired='"+lock+"',credentials_expired='"+lock+"',password='"+pwd+"',firstname='"+u.getFirstname()+"',country='"+u.getCountry()+"',qualification='"+u.getQualification()+"' WHERE id='"+u.getId()+"' ";
             PreparedStatement ps = connection.prepareStatement(requete);
            ps.executeUpdate();
            System.out.println("mchet mchet mcheeeeeetd"+u.getUsername());
             
           
            
            
            
 
//            ps.setString(1, val.getUsername());
//            ps.setString(2, val.getUsername());
//            ps.setString(3, val.getEmail());
//            ps.setString(4, val.getEmail());
//            ps.setString(5,"1");
//            ps.setString(6,encodedSalt);
//            ps.setString(10,pwd);
//            ps.setString(7,"0");
//            ps.setString(8,"0");
//            
//            
//            ps.setString(12, val.getCountry());
//            ps.setString(11, val.getPrenom());
//            ps.setDate(13, (Date) val.getBirthdate());
////            ps.setString(15, "a:1:{i:0;s:11:\"ROLE_CLIENT\";}");
           
            ps.executeUpdate(); 
     
            ResultSet rs1 = ps.getGeneratedKeys();
           
          
                       
if (rs1.next()){
//    idpersonne=rs1.getInt(1);
}  
            
        } catch (Exception ex) {
            System.out.println(ex);
//        } catch (SQLException ex) {
//           System.out.println(ex);
//        } catch (Exception ex) {
//        Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
//    }
//        
//        
        
        }
        
            String req ="UPDATE fos_user SET  username_canonical='"+u.getUsername()+"',username='"+u.getUsername()+"',password='"+u.getPassword()+"',firstname= '"+u.getFirstname()+"',lastname= '"+u.getLastname()+"', country = '"+u.getCountry()+"',qualification= '"+u.getQualification()+"',email= '"+u.getEmail()+"' WHERE id='"+u.getId()+"' ";
        
      
    
    }
    
      public void CreerComptemodiffacebook(Classes.User u) {


        try{
 
       
      
           String req ="INSERT INTO fos_user(username,password,firstname,lastname,country,qualification,email,facebookid) "
                    + "VALUES('"+u.getUsername()+"', '"+u.getPassword()+"','"+u.getFirstname()+"','"+u.getLastname()+"', '"+u.getCountry()+"', '"+u.getQualification()+"','"+u.getEmail()+"','"+u.getFbid()+"')";
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
        us.setFirstname(rs2.getString("firstname"));
        us.setLastname(rs2.getString("lastname"));
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
    
public boolean veriffierPwd(User user){
    try {
         String req="SELECT * FROM fos_user WHERE username='"+user.getUsername()+"'  " ;
         System.out.println("veeeeeeeeeeeeeeeerif"+user.getUsername());
//         Statement st = connection.createStatement();
//         ResultSet rs = st.executeQuery(req);
PreparedStatement ps = connection.prepareStatement(req);
            ps.executeQuery();
                 ResultSet rs =connection.createStatement().executeQuery(req);
                    System.out.println("veeeeeeeeeeeeeeeerif el msata"+user.getUsername());
        String test = user.getPassword();
         while(rs.next())
         { String pwdbase=rs.getString("password");
             System.out.println("pass base"+pwdbase);
             String pwdinter = hashPassword(test,rs.getString("salt"));
            if(pwdinter.equals(pwdbase))
            {
                return true;
            } 
             
         }
        
              } catch (SQLException ex) {
            System.out.println(ex);
        } catch (Exception ex) {
            System.out.println(ex);
        }
return false;
}


    public User Connecter2(Classes.User u) throws SQLException {
       String loged;
  
       if (password.equals(""))
     return null;
        if (veriffierPwd(u)){
           return u;}
        else return null;
//           
//            String req ="SELECT * FROM fos_user where password='"+password+"' " ;
//                   
//          PreparedStatement ps = connection.prepareStatement(req);
//            ps.executeQuery();
//                 ResultSet rs2 =connection.createStatement().executeQuery(req);
//                 
//                  if(rs2.next()){
//                loged="Does Exist";
//                 
//                  User us = new User();
//                  us.setUsername(rs2.getString("username"));
//         us.setPassword(rs2.getString("password"));
//        us.setFirstname(rs2.getString("firstname"));
//        us.setLastname(rs2.getString("lastname"));
//        us.setEmail(rs2.getString("email"));
//        us.setBirthdate(rs2.getDate("Birthdate"));
//        
//        
//          us.setCountry(rs2.getString("country"));
//        us.setQualification(rs2.getString("qualification"));
//        us.setBan(rs2.getInt("ban"));
//                   return us ;}}
//       else 
//                  {   loged="Login does not exist";
//return null;}
//   return null;
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
        us.setFirstname(rs2.getString("firstname"));
        us.setLastname(rs2.getString("lastname"));
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
        us.setFirstname(rs2.getString("firstname"));
        us.setLastname(rs2.getString("lastname"));
        us.setEmail(rs2.getString("email"));
        us.setBirthdate(rs2.getDate("Birthdate"));
        
        
          us.setCountry(rs2.getString("country"));
        us.setQualification(rs2.getString("qualification"));
        us.setBan(rs2.getInt("ban"));
        return us;}
        else return null;


    }
    

  
 

    @Override
 public void AjouterMembre(String lastname, int id){
        try{
 
       
      
            String req ="INSERT INTO fos_user (lastname, fbid) VALUES ( '" + lastname + "', '" + id+"');";
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
//            us.setId(Integer.parseInt(rs2.getString("id")));
              us.setUsername(rs2.getString("username"));
        us.setPassword(rs2.getString("password"));
        us.setFirstname(rs2.getString("firstname"));
       us.setLastname(rs2.getString("lastname"));
        us.setEmail(rs2.getString("email"));
       us.setBirthdate(rs2.getDate("Birthdate"));
        us.setFbid(rs2.getString("facebookid"));
//        
         us.setCountry(rs2.getString("country"));
        us.setQualification(rs2.getString("qualification"));
//        us.setBan(rs2.getInt("ban"));
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
        us.setFirstname(rs2.getString("firstname"));
        us.setLastname(rs2.getString("lastname"));
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
        us.setFirstname(rs2.getString("username"));
        us.setLastname(rs2.getString("username"));
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
        us.setFirstname(rs2.getString("username"));
        us.setLastname(rs2.getString("username"));
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
try{
       
            Message message = new MimeMessage(session);
            
            message.setFrom(new InternetAddress(user));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(sub);
            message.setText(msg);

            Transport.send(message);
            
            return true;
            
        } catch (MessagingException e) 
            
        {
            System.out.println("msata"+e.getMessage());
            return false;
       
        }
        
    }
   
  public void updater(Classes.User val) {
    System.out.println("*************************"+val.getPassword());
        System.out.println("username"+val.getUsername());
         System.out.println("firstname"+val.getFirstname());
          System.out.println("lastname"+val.getLastname());
           System.out.println("birth"+val.getBirthdate());
            System.out.println("country"+val.getCountry());
             System.out.println("qual"+val.getQualification());
              System.out.println("email"+val.getEmail());
            

    
        int en=1;
        int lock=0;
     String f=  Facebookuser.fbu.getFbid();
      System.out.println("fazzzzzzzzzzzzzzerbook"+f);
        String pwd = "";

        try {
             final Random r = new SecureRandom();
byte[] salt = new byte[32];
r.nextBytes(salt);
String encodedSalt = new sun.misc.BASE64Encoder().encode(salt);
encodedSalt=encodedSalt.substring(0,31); 
            
 pwd= hashPassword(val.getPassword(),encodedSalt);
              
             String requete = "INSERT INTO fos_user(usernamed,facebookid) "
                     + ""
                    + "VALUES('"+val.getUsername()+"', '"+f+"')";
             PreparedStatement ps = connection.prepareStatement(requete);
            ps.executeUpdate();
            System.out.println("fazzzzzzzzzzzzzzerbook"+f);
             
           
            
            
            
 
//            ps.setString(1, val.getUsername());
//            ps.setString(2, val.getUsername());
//            ps.setString(3, val.getEmail());
//            ps.setString(4, val.getEmail());
//            ps.setString(5,"1");
//            ps.setString(6,encodedSalt);
//            ps.setString(10,pwd);
//            ps.setString(7,"0");
//            ps.setString(8,"0");
//            
//            
//            ps.setString(12, val.getCountry());
//            ps.setString(11, val.getPrenom());
//            ps.setDate(13, (Date) val.getBirthdate());
////            ps.setString(15, "a:1:{i:0;s:11:\"ROLE_CLIENT\";}");
           
            ps.executeUpdate(); 
     
            ResultSet rs1 = ps.getGeneratedKeys();
           
          
                       
if (rs1.next()){
//    idpersonne=rs1.getInt(1);
}  
            
        } catch (Exception ex) {
            System.out.println(ex);
//        } catch (SQLException ex) {
//           System.out.println(ex);
//        } catch (Exception ex) {
//        Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
//    }
//        
//        
        
        }
        
}
public User getotherinfo(Classes.User a) throws SQLException{

 String req2 ="SELECT * FROM fos_user WHERE  facebookid ='"+a.getFacebookid()+"'" ;
                   
          PreparedStatement ps2;
            ps2 = connection.prepareStatement(req2);
        
            ps2.executeQuery(); 
            ResultSet rs2 =connection.createStatement().executeQuery(req2);
         
             if(rs2.next()){
             User us = new User();
           
              
              us.setId(Integer.parseInt(rs2.getString("id")));
         us.setPassword("******");
        us.setFirstname(rs2.getString("username"));
        us.setLastname(rs2.getString("username"));
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

}
