/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 2013 Oracle and/or its affiliates. All rights reserved.
 *
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common Development
 * and Distribution License("CDDL") (collectively, the "License"). You
 * may not use this file except in compliance with the License. You can
 * obtain a copy of the License at
 * https://glassfish.dev.java.net/public/CDDL+GPL_1_1.html
 * or packager/legal/LICENSE.txt.  See the License for the specific
 * language governing permissions and limitations under the License.
 *
 * When distributing the software, include this License Header Notice in each
 * file and include the License file at packager/legal/LICENSE.txt.
 *
 * GPL Classpath Exception:
 * Oracle designates this particular file as subject to the "Classpath"
 * exception as provided by Oracle in the GPL Version 2 section of the License
 * file that accompanied this code.
 *
 * Modifications:
 * If applicable, add the following below the License Header, with the fields
 * enclosed by brackets [] replaced by your own identifying information:
 * "Portions Copyright [year] [name of copyright owner]"
 *
 * Contributor(s):
 * If you wish your version of this file to be governed by only the CDDL or
 * only the GPL Version 2, indicate your decision by adding "[Contributor]
 * elects to include this software in this distribution under the [CDDL or GPL
 * Version 2] license."  If you don't indicate a single choice of license, a
 * recipient has the option to distribute your version of this file under
 * either the CDDL, the GPL Version 2 or to extend the choice of license to
 * its licensees as provided above.  However, if you add GPL Version 2 code
 * and therefore, elected the GPL Version 2 license, then the option applies
 * only if the new code is made subject to such option by the copyright
 * holder.
 */ 

package GUI.Controllers;

import Classes.User;
import Classes.facebook;
import DAO.classes.UserDAO;
import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.DefaultJsonMapper;
import com.restfb.Facebook;
import com.restfb.FacebookClient;
import com.restfb.FacebookClient.AccessToken;
import com.restfb.JsonMapper;
import com.restfb.Parameter;
import com.restfb.json.JsonArray;
import com.restfb.json.JsonObject;
import com.restfb.types.Page;
import com.restfb.types.Post;
import com.restfb.types.Url;
import com.sun.corba.se.impl.orbutil.graph.Graph;
import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.String;
import java.io.IOException;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import static java.lang.String.format;
import static java.lang.System.currentTimeMillis;
import static java.lang.System.out;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Worker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javax.mail.MessagingException;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.parser.ParserDelegator;
import GUI.Controllers.SelectedValue;
import static java.lang.String.format;
import static java.lang.String.format;
import static java.lang.String.format;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Angie
 */
public class Screen1Controller implements Initializable {
    //5555555555555555555555555555555
    
     FacebookClient facebookClient= new DefaultFacebookClient(access_token);
   UserDAO mdao = new UserDAO();
    
     @FXML
    private Button btn;
     

    public static String API_KEY = "904970166288632";
    public static String SECRET = "3e42f7cde89d58b599ee8a70ee98a9bf";

    public static String firstRequest = "https://graph.facebook.com/oauth/authorize?"
            + "client_id="
            + API_KEY
            + "&redirect_uri=http://www.facebook.com/connect/login_success.html&"
            + "scope=publish_stream,offline_access,create_event,read_stream,email,user_birthday";

    public static String secondRequest = "https://graph.facebook.com/oauth/access_token?"
            + "client_id="
            + API_KEY
            + "&redirect_uri=http://www.facebook.com/connect/login_success.html&"
            + "client_secret=" + SECRET + "&code=";

    public static String access_token = "";
    public static boolean firstRequestDone = false;
    public static boolean secondRequestDone = false;
    @FXML
    private WebView wv;
    WebEngine webEngine;
    Label message = new Label("Information are beeing retreived"
            + "Hold on Please");
    @FXML
    private AnchorPane ap;

    int i = 0;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
   
    }

    @FXML
    private void RetrieveInformation(ActionEvent event) throws IOException {
        String tmp = "CAAM3EMlLQPgBAM5dSuws71tLjlkl9mTvlOCPOOZBshBHZAQiS50eOJdxqP57rfxZC2ZARpTOMbCZCq8AsOYjoauZAK1bUZC2jwZAgyou8vZA3H0ufHCrzoVAcrZAHSZB2GZAyCdRDrTMLay8ueEQx1FpGbCSH1KZBsRGrZAFFXZAcwjHdoFZBpKJOxmAb8QTa0ZCZBlVBT1WaxT9Lp74xXhSoxU530FBqZB";
        if (access_token.length() != tmp.length()) {

            try {
              
                    new GraphReaderExample(access_token).runEverything();
                    System.out.println("ffffffffffffffffffffffffffffffffffffffff=>");
                    ap.getChildren().remove(wv);
                    message.setStyle("-fx-text-fill:white;");
                    System.out.println("sssssssssssssssssssssssssssssssssssssssss=>");
                    ap.getChildren().add(message);
                  
                 
                }
            catch (Exception e) {
                ap.getChildren().remove(wv);
              //  System.out.println("erreur en récupérant les infos: " + e.getMessage());
              //  message.setText("Erreur lors de l'inscription !");
              
              Alert dialog = new Alert(Alert.AlertType.INFORMATION);
dialog.setHeaderText("Retrieving Report");
dialog.setContentText("Retreiving Succeeded");

//FIXME: Remove after release 8u40
dialog.setResizable(true);
dialog.getDialogPane().setPrefSize(480, 320);

dialog.showAndWait();
            }

        } 
        
      
         
}


      @FXML
    public void loadFbPage() {
        webEngine = wv.getEngine();
        webEngine.load(firstRequest);
        webEngine.getLoadWorker().stateProperty().addListener(
                new ChangeListener<Worker.State>() {
                    @Override
        public void changed(ObservableValue ov, Worker.State oldState, Worker.State newState) {
                        if (newState == Worker.State.SUCCEEDED) {
                            if (webEngine.getLocation().contains("http://www.facebook.com/connect/login_success.html?code=")) {
                                String[] splits = webEngine.getLocation().split("=");
                                String stage2temp = secondRequest + splits[1];
                                webEngine.load(stage2temp);
                                firstRequestDone = true;
                            } else {
                                if (!secondRequestDone) {
                                    String html = (String) webEngine.executeScript("document.documentElement.innerHTML");
                                    StringReader readerSTR = new StringReader(html);
                                    HTMLEditorKit.ParserCallback callback
                                    = new HTMLEditorKit.ParserCallback() {
                                        @Override
        public void handleText(char[] data, int pos) {
                                            try {
                                                String string = new String(data);
                                                String[] temp1 = string.split("&");
                                                String[] temp2 = temp1[0].split("=");
                                                System.out.println("Access token=" + temp2[1]);
                                                access_token = temp2[1];
                                                webEngine.load("http://www.facebook.com");
                                            } catch (Exception e) {
                                               // System.out.println("erreur facebook: " + e.getMessage());
                                            }
                                        }
                                    };
                                    try {
                                        new ParserDelegator().parse(readerSTR, callback, false);
                                    } catch (IOException e1) {
                                        e1.printStackTrace();
                                    }
                                }
                            }
                        }
                    }
                });

    }
    
    //555555555555555555555555555555555
   @FXML
    private TextField username;
   @FXML
    private TextField usernamef;
         @FXML
    private TextField password;
            @FXML
    private TextField firstname;
              @FXML
    private TextField ffuser;
                @FXML
    private TextField ffid;
            
                      @FXML
    private TextField sendermail;
                @FXML
    private TextField senderemailpass;
  
        
               @FXML
               TableView<User>tableid;
                 @FXML
                  TableColumn<User,String>nome;
                       @FXML
                  TableColumn<User,String>prenome;
            
                 @FXML
    private TextField cffid;
                 
                   @FXML
    private TextField usernameed;
                   @FXML
    private TextField passworded;
                   @FXML
    private TextField qualificationed;
                     @FXML
    private TextField firstnameed;
                       @FXML
    private TextField lastnameed;
                         @FXML
    private TextField emailed;
                 
                  @FXML
    private TextField cffuser;
                   @FXML
    private TextField cmail;
                    @FXML
    private TextField cbday;
                @FXML
    private Label fusername;
                @FXML
    private Label firstnamelbl;
                @FXML
    private Label lastnamelbl;
                @FXML
    private Label usernamelbl;
                @FXML
    private Label emaillbl;
               
                  @FXML
    private Label firstnameview;
                    
                @FXML
    private Label lastnameview;
                
                
                @FXML
    private Label usernameview;
                @FXML
    private Label emailview;
                 @FXML
    private Label warning;
                
       @FXML
    private TextField fbid;
         @FXML
    private TextField email;
            
            @FXML
    private Label firstnamef;
               @FXML
    private TextField lastname;
               @FXML
    private TextField gemail;
               @FXML
    private TextField gsujet;
               @FXML
    private TextArea gtxt;
               @FXML
    private Label lastnamef;
            
         @FXML
       User sp = new User  () ;
               
       @FXML
    private TextField country;
        @FXML
    private TextField qualification;
        
        @FXML
    private Label maillb;
          @FXML
    private TextField mailed;
          @FXML
    private TextField countryed;
            @FXML
    private TextField bday;
        
           @FXML
    private TextField ban;
            @FXML
    private TextField login;
             @FXML
    private PasswordField pass;
        
    @FXML
    private TextField usernamesearch;
    
    
       
    @FXML
    private void gotosearch(ActionEvent event) throws IOException, SQLException{
     
      Parent home_page_parent = FXMLLoader.load(getClass().getResource("/GUI/UserFound.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                app_stage.hide(); 
                app_stage.setScene(home_page_scene);
                app_stage.setTitle("Finding A User");
                app_stage.show(); 
              
                
    }
    
     @FXML
    private void gotofzb(ActionEvent event) throws IOException{
     
      Parent home_page_parent = FXMLLoader.load(getClass().getResource("/GUI/inscription.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                app_stage.hide(); 
                app_stage.setScene(home_page_scene);
                app_stage.setTitle("Facebook Inscription");
                app_stage.show(); 
    
    }
    
     
    
     @FXML
    private void save(ActionEvent event) throws IOException{
        String mmail;
            UserDAO fa=new UserDAO();
 mmail=fa.trouver();
 
     
      Parent home_page_parent = FXMLLoader.load(getClass().getResource("/GUI/Main.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                app_stage.hide(); 
                app_stage.setScene(home_page_scene);
                app_stage.setTitle("Main");
                app_stage.show(); 
                
   }
     @FXML
    private void fbonmouse(javafx.scene.input.MouseEvent g) throws IOException, Exception{
        
      
  
        try {
 
      ffuser.setText(Facebookuser.fbu.getNom());
        // ffid.setText(Facebookuser.fbu.getFbid());
         //Facebookuser.fbu=null;
        } 
        catch (Exception e)
        {}
   
    }
    
    
    @FXML
    private void goToScreen1(ActionEvent event) throws IOException{
     
      Parent home_page_parent = FXMLLoader.load(getClass().getResource("/GUI/Screen1.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                app_stage.hide(); 
                app_stage.setScene(home_page_scene);
                app_stage.setTitle("Loging In");
                app_stage.show(); 
    
    }

 
    
    
    
    
    
    
    @FXML
    private void goToScreen2(ActionEvent event) throws IOException{
        
        int ch=0;
        
         UserDAO ud = new UserDAO();
    
   
     User us=new User();
     
    us.setUsername(username.getText());
    us.setPassword(password.getText());
    us.setPrenom(firstname.getText());
    us.setNom(lastname.getText());
    
     Calendar calendare = Calendar.getInstance();
    java.sql.Date ourJavaDateObject = new java.sql.Date(calendare.getTime().getTime());
    us.setBirthdate(ourJavaDateObject);
    
    us.setCountry(country.getText());
      us.setQualification(qualification.getText());
//      if (ban.getText().equals("")){
//          
//      }
//      else {
//      
//     
//      ch=Integer.parseInt(ban.getText());
//    us.setBan(ch);
//      }
   
    us.setEmail(email.getText());
        
    
   
  ud.CreerCompte(us);
        savedusers.savedlogedin=us;
       Parent home_page_parent = FXMLLoader.load(getClass().getResource("/GUI/FXML.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                app_stage.hide(); 
                app_stage.setScene(home_page_scene);
                app_stage.setTitle("Create User");
                app_stage.show(); 
    }
    
    @FXML
    private void goToScreen3(ActionEvent event) throws IOException, SQLException{
        
       
       Parent home_page_parent = FXMLLoader.load(getClass().getResource("/GUI/Screen3.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                app_stage.hide(); 
                app_stage.setScene(home_page_scene);
                  app_stage.setTitle("Create User");
                app_stage.show(); 
    
    }
    
    @FXML
    private void maine(ActionEvent event) throws IOException, SQLException, Exception{
        
       
       Parent home_page_parent = FXMLLoader.load(getClass().getResource("/GUI/FXML.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                app_stage.hide(); 
                app_stage.setScene(home_page_scene);
                  app_stage.setTitle("Main");
                app_stage.show(); 
    
    }
    
    
       @FXML
    private void gomain(ActionEvent event) throws IOException, SQLException, Exception{
        
         UserDAO fa=new UserDAO();
          User uu=new User();
           User uu2=new User();
   uu= fa.Connecter(login.getText());
   uu2= fa.Connecter2(pass.getText());
    if( uu==null) {
        
        warning.setText("Verify Your Login ");}
    else if( uu2==null) {
        warning.setText("Verify Your Password");}
    else {
        
       savedusers.savedlogedin=uu;
       
       Parent home_page_parent = FXMLLoader.load(getClass().getResource("/GUI/FXML.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                app_stage.hide(); 
                app_stage.setScene(home_page_scene);
                  app_stage.setTitle("Main");
                app_stage.show(); 
    }
    }
    
     @FXML
    private void editprofile(ActionEvent event) throws IOException{
     
      Parent home_page_parent = FXMLLoader.load(getClass().getResource("/GUI/editing.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                app_stage.hide(); 
                app_stage.setScene(home_page_scene);
                  app_stage.setTitle("Editing Profile");
                app_stage.show(); 
    
    }
    
    @FXML
    private void sendmail(ActionEvent event) throws IOException{
     
        
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/mailing.fxml"));  
Parent root = (Parent) loader.load();  
Scene scene = new Scene(root);  
Stage stage = new Stage();  

stage.setScene(scene);  
stage.setTitle("Emailing");
stage.showAndWait();
        
     
    
    }
    
    @FXML
    private void sentmail(ActionEvent event) throws IOException{
     UserDAO snd = new UserDAO();
     User sender = new User();
     sender=savedusers.savedlogedin;
            sendermail.setText(sender.getEmail());
        gemail.getText();
        gtxt.getText();
        gsujet.getText();
       if( snd.sendml(gemail.getText(), gsujet.getText(),gtxt.getText(),sendermail.getText(),senderemailpass.getText())){
        
        Alert dialog = new Alert(Alert.AlertType.INFORMATION);
dialog.setHeaderText("Sending Report");
dialog.setContentText("Email Sent Successfully");

//FIXME: Remove after release 8u40
dialog.setResizable(true);
dialog.getDialogPane().setPrefSize(480, 320);

dialog.showAndWait();}
       else 
       {  Alert dialog = new Alert(Alert.AlertType.INFORMATION);
dialog.setHeaderText("Sending Report");
dialog.setContentText("Email Was Not Sent password Incorrect");

//FIXME: Remove after release 8u40
dialog.setResizable(true);
dialog.getDialogPane().setPrefSize(480, 320);

dialog.showAndWait();}
       
      Parent home_page_parent = FXMLLoader.load(getClass().getResource("/GUI/Main.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                app_stage.hide(); 
                app_stage.setScene(home_page_scene);
                  app_stage.setTitle("Emailing");
                app_stage.show(); 
    
    }
    
     @FXML
     private void showemailing( javafx.scene.input.MouseEvent g) {
             UserDAO fa=new UserDAO();
          User savving=new User();
         
          savving=savedusers.savedresultarecherche;
 gemail.setText(savedusers.savedresultarecherche.getEmail());
 sendermail.setText(savedusers.savedlogedin.getEmail());
     
    
     }
    
    
      @FXML
     private void showinfo( javafx.scene.input.MouseEvent g) {
             UserDAO fa=new UserDAO();
             
          User voirloged = new User();  
         

          if (Facebookuser.fbusav==null)
          { voirloged =savedusers.savedlogedin;
         
         
  usernameed.setText(voirloged.getUsername());
               firstnameed.setText(voirloged.getPrenom());
    countryed.setText(voirloged.getCountry());
     passworded.setText(voirloged.getPassword());
      emailed.setText(voirloged.getEmail());
       lastnameed.setText(voirloged.getNom());
        countryed.setText(voirloged.getCountry());
          }
          else 
               { 
         
         
   usernameed.setText(voirloged.getUsername());
               firstnameed.setText(Facebookuser.fbusav.getPrenom());
    countryed.setText(Facebookuser.fbusav.getCountry());
     passworded.setText(Facebookuser.fbusav.getPassword());
      emailed.setText(Facebookuser.fbusav.getEmail());
       lastnameed.setText(Facebookuser.fbusav.getNom());
        countryed.setText(Facebookuser.fbusav.getCountry());
          }
     }
     
     
     
     @FXML
    private void saveprofile(ActionEvent event) throws IOException, SQLException{

         UserDAO ud = new UserDAO();
    User us= new User();
   User userfb =  new User ();
 
   userfb=savedusers.savedlogedin;
      System.out.println(userfb.getFbid());
       System.out.println(userfb.getNom());
   if (userfb==null){
      us=savedusers.savedlogedin;
      
   
       
      
  
     us.setPrenom(firstnameed.getText());
    us.setNom(lastnameed.getText());
    us.setPassword(passworded.getText());
   

    
    us.setCountry(countryed.getText());
      us.setQualification(qualificationed.getText());
     
   
    us.setEmail(emailed.getText());
        
    
   
  ud.CreerComptemodiffacebook(userfb);
 Facebookuser.fbusav=savedusers.savedlogedin;
        
  
        
       Parent home_page_parent = FXMLLoader.load(getClass().getResource("/GUI/Main.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                app_stage.hide(); 
                app_stage.setScene(home_page_scene);
                app_stage.show(); 
    }
    else
        
      
   
             System.out.println("t3adalha toul hetha");
   if (userfb.getId()==0)
   {  System.out.println(userfb.getId());
   System.out.println(userfb.getNom());
   System.out.println("user 3adi");
   userfb.setUsername(usernameed.getText());
     userfb.setPrenom(firstnameed.getText());
    userfb.setNom(lastnameed.getText());
   
    userfb.setPassword(passworded.getText());
   

    
    userfb.setCountry(countryed.getText());
      userfb.setQualification(qualificationed.getText());
     
   
    userfb.setEmail(emailed.getText());
        
    
   
  ud.updateComptemodif(userfb);
  Facebookuser.fbusav=savedusers.savedlogedin;
   savedusers.savedlogedin=userfb;
   
       Parent home_page_parent = FXMLLoader.load(getClass().getResource("/GUI/Main.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                app_stage.hide(); 
                app_stage.setScene(home_page_scene);
                app_stage.show(); }
    else if (userfb.getId()!=0)
        
    { System.out.println("FacebookUser");
        userfb.setPrenom(firstnameed.getText());
    userfb.setNom(lastnameed.getText());
    userfb.setPassword(passworded.getText());
   

    
    userfb.setCountry(countryed.getText());
      userfb.setQualification(qualificationed.getText());
     
   
    userfb.setEmail(emailed.getText());
        
    
        ud.CreerComptemodiffacebook(userfb);
        Facebookuser.fbusav=savedusers.savedlogedin;
    }
    }
    
    
    
    
     @FXML
    private void searching(ActionEvent event) throws IOException, Exception{
        
     
          UserDAO fe=new UserDAO();
      
        User u = fe.getByUserName(usernamef.getText());
      
        try {
           prenome.setCellValueFactory(new PropertyValueFactory<User, String>("Nom"));
            nome.setCellValueFactory(new PropertyValueFactory<User, String>("Prenom"));
 
            for(User e : fe.findAllbyuser(usernamef.getText())){
              
tableid.getItems().setAll(fe.findAllbyuser(usernamef.getText()));

            }
            
            
               
      
          
        firstnamef.setText(u.getPrenom());
          lastnamef.setText(u.getNom());

        } 
        catch (Exception e)
        {}
   
    }
    
     @FXML  public void showit( javafx.scene.input.MouseEvent g) throws Exception{
           UserDAO fe=new UserDAO();
             User mailar = new User();
                

          
          ObservableList selectedItems = tableid.getSelectionModel().getSelectedItems();
   User p1=null;
      for (Object o1: selectedItems){
          System.out.println(((User)(o1)).toString());
         p1=(User)(o1);
         User u =   fe.getByUserName(p1.getUsername());
    
          
          
         usernamelbl.setText("User Name : ");
     firstnamelbl.setText("First Name :");         
        lastnamelbl.setText("Last Name :");    
            emaillbl.setText("Email Mail:");
          
     
 usernameview.setText(u.getUsername());
     firstnameview.setText(u.getPrenom());         
       lastnameview.setText(u.getNom());    
          emailview.setText(u.getEmail());  
          
          savedusers.savedresultarecherche=u;
                  
           }

   
    
     
   
     
      
      
      
           

  
     }
    
    @FXML
    private void createfbusers(ActionEvent event) throws IOException{
        UserDAO a = new UserDAO();
        
       
    
    }
    
    
      
    
    
    
    
    
   @FXML
    private void verfsavinguser (ActionEvent event) throws IOException, SQLException{
         UserDAO fe=new UserDAO();
         User u = new User();
         User us = new User();
         User urez= new User();
          u=Facebookuser.fbu;
        System.out.println(u.getFbid());
        System.out.println(u.getNom());
        us=fe.ConnecterFb(u.getFbid());
        
         if (us!=null){
         System.out.println("existe toul");
         savedusers.savedlogedin=Facebookuser.fbu;
             System.out.println("tsajel hal maset -----?");
             System.out.println(savedusers.savedlogedin.getNom());
             System.out.println(savedusers.savedlogedin.getNom());
              System.out.println(Facebookuser.fbu.getFbid());
      Parent home_page_parent = FXMLLoader.load(getClass().getResource("/GUI/Main.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                app_stage.hide(); 
                app_stage.setScene(home_page_scene);
                  app_stage.setTitle("Main");
                app_stage.show(); }
     else {
    
   
           try {
 
           System.out.println(" mana3erfouch famchy inserer");
     urez=fe.fbusers(u.getNom(),u.getFbid());
     Facebookuser.fbusav=urez;
     savedusers.savedlogedin=Facebookuser.fbu;
      Parent home_page_parent = FXMLLoader.load(getClass().getResource("/GUI/Main.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                app_stage.hide(); 
                app_stage.setScene(home_page_scene);
                  app_stage.setTitle("Main");
                app_stage.show(); 
        } 
        catch (Exception e)
        {}
   
    }
     
    
    
    
    
    
}}
    
  
    
