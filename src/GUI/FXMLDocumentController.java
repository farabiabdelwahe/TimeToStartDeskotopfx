/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;


import Services.DropBox;
import Classes.Projet;
import Classes.User;
import Classes.*;
import DAO.classes.FeedBackDAO;
import DAO.classes.ProjectDAO;
import DAO.classes.SponsoringDAO;
import GUI.Controllers.savedusers;
import GUI.Translators.FeedBackt;
import GUI.Translators.Sponsorint;
import com.dropbox.core.DbxException;
import java.awt.Desktop;
import java.awt.Desktop.Action;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import static jdk.nashorn.internal.runtime.Debug.id;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Modality;

import javax.swing.AbstractAction;







/**
 *
 * @author Phil
 */
public class FXMLDocumentController implements Initializable,Runnable {
    
        ProjectDAO o = new ProjectDAO();
        FeedBackDAO fd= new FeedBackDAO();
        SponsoringDAO s= new SponsoringDAO();
             User us=new User();
             
        @FXML
       private WebView donate ;
    @FXML
    private Label tlabel;
    
    @FXML
    private Label invalid_label;
    
    @FXML
    private TextField username_box;
    
    @FXML
    private TextField password_box;
    //project
     @FXML
    private Label cf;
     
    private Object fileChooser;
     @FXML
    private ChoiceBox cat;
      @FXML
    private TextField name;
       @FXML
    private TextArea dis;
       
    @FXML
    private TextField target ;
      @FXML
    private TextArea prize;
    
        @FXML
        private TextArea tdis ;
          @FXML 
          private  TextField hdis;
          
            @FXML 
          private DatePicker  dtask ;
            @FXML 
            private  ChoiceBox helpType ;       
       
       //editproject
       @FXML
          private Label ecf;
     
   
     @FXML
    private ChoiceBox ecat;
      @FXML
    private TextField ename;
       @FXML
    private TextArea edis;
       
       
//user project table 
       
           @FXML
    private TableView<Projet> projetTable;
    @FXML
    private TableColumn<Projet, String> projectname;
    @FXML
    private TableColumn<Projet, String> projectdiscripton;
    
     @FXML
      private TableColumn<Projet, Date> projectdate;
     
       @FXML
        private TableColumn<Projet, String> projectfile;
             @FXML
        private TableColumn<Projet, Integer> projectid;
                     @FXML
        private TableColumn<Projet, Integer> projectid1;
       
    
       
       @FXML
               private ImageView projects;
       
       
       @FXML
               private ImageView icon1;
       
       @FXML
               private ImageView icon2;
       @FXML
               private ImageView icon3;
       @FXML
               private ImageView icon4;
       @FXML
               private ImageView icon5;
       
      
    
    //internal
       @FXML
       Projet sp = new Projet () ;
       
       //project search 
         @FXML
           private TableView<Projet> projetTables;
    @FXML
    private TableColumn<Projet, String> projectnames;
    @FXML
    private TableColumn<Projet, String> projectdiscriptons;
             @FXML
           private TableView<Projet> projetTables1;
    @FXML
    private TableColumn<Projet, String> projectnames1;
    @FXML
    private TableColumn<Projet, String> projectdiscriptons1;
    
     @FXML
      private TableColumn<Projet, Date> projectdates;
     
       @FXML
        private TableColumn<Projet, String> projectfiles;
       
       @FXML
       private ChoiceBox cats;

    //picharts
       @FXML
        private PieChart  catcharts;
       
       
       
       
       
       //
          @FXML
        private AnchorPane mainp;
       
       
    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        System.out.println("You clicked me!");
        Parent home_page_parent = FXMLLoader.load(getClass().getResource("FXML.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                app_stage.hide(); 
                app_stage.setScene(home_page_scene);
                app_stage.show(); 
    
                
   
                
                
            
       
    }
    
    @FXML
        private void loadpr(javafx.scene.input.MouseEvent g) throws IOException {
   try {                    
        URL url = getClass().getResource("ProjectMain.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(url);
        fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
        AnchorPane page = (AnchorPane) fxmlLoader.load(url.openStream()); 

        mainp.getChildren().clear();
        mainp.getChildren().add(page);
        
    } 
    catch (IOException e) {
        e.printStackTrace();
    }
   
   
                
            
       
    }
        
        
    
           @FXML
        private void loaduser(javafx.scene.input.MouseEvent a)  { 
   try {                    
        URL url = getClass().getResource("Main.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(url);
        fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
        AnchorPane page = (AnchorPane) fxmlLoader.load(url.openStream()); 

        mainp.getChildren().clear();
        mainp.getChildren().add(page);
        
    } 
    catch (IOException e) {
        e.printStackTrace();
    }
                
            
       
    }
        
        @FXML
             private void logout(javafx.scene.input.MouseEvent g) throws IOException {
   try {                    
   System.out.println("You clicked me!");
        Parent home_page_parent = FXMLLoader.load(getClass().getResource("Screen1.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) g.getSource()).getScene().getWindow();
                app_stage.hide(); 
                app_stage.setScene(home_page_scene);
                app_stage.show(); 
    
        
    } 
    catch (IOException e) {
        e.printStackTrace();
    }
        }
        
@FXML protected void Choose(ActionEvent event) throws Exception {
    FileChooser chooser = new FileChooser();
   chooser.setTitle("Open File");
   File file = chooser.showOpenDialog(new Stage());
   
   
   

    cf.setText( file.getPath());


   
  
}

@FXML 
 private TextField dropass;
@FXML 
 private Button dbb;
@FXML protected void getdropass(ActionEvent event) {
    SelectedValue.pass=dropass.getText();
    Stage stage = (Stage)  dbb.getScene().getWindow();
    stage.close();
    
    
}

@FXML protected void add(ActionEvent event) throws IOException, DbxException, URISyntaxException, SQLException {

try {
    System.out.println("*********************************"+savedusers.savedlogedin.getId());
         tlabel.setText("Processing your request");
    Projet p = new Projet() ;

     us.setId(savedusers.savedlogedin.getId());
    p.setCreateur(us);
         Calendar calendare = Calendar.getInstance();
    java.sql.Date ourJavaDateObject = new java.sql.Date(calendare.getTime().getTime());
    p.setDate(ourJavaDateObject);
    
    
    
      
      p.setName(name.getText());
      p.setDiscription(dis.getText());
     System.out.println(p.getDiscription());
      p.setType((String)cat.getValue());
      p.setHelpType(helpType.getValue().toString());
          if (helpType.getValue().toString().equals("")){
  Alert alert = new Alert(AlertType.ERROR);
               DialogPane dialogPane = alert.getDialogPane();
dialogPane.getStylesheets().add(
   getClass().getResource("dialog.css").toExternalForm());
dialogPane.getStyleClass().add("dialog");
alert.setTitle("Information Dialog");

alert.setTitle("Error Dialog");
alert.setHeaderText("an Error Dialog");
alert.setContentText("you must Select a help type");


alert.showAndWait();
throw new Exception() ;
          
      }
      
      
      if (helpType.getValue().toString().equals("Financial")){
      p.setPrize(prize.getText());
          p.setTarget(Float.parseFloat(target.getText()));
          
      }
       if (helpType.getValue().toString().equals("Human Resources")){
          Date date = Date.valueOf(dtask.getValue());
          
          p.setTaskDate(date);
          
          p.setHtask(hdis.getText());
       }
        if (helpType.getValue().toString().equals("Technical")){
     
          
          p.settDiscription(tdis.getText() );
       }

       
       
          



         
      
      if (cf.getText().equals("Choose a file")){
          
          Alert alert = new Alert(AlertType.INFORMATION);
alert.setTitle("no file Chosen");

alert.setContentText("Choose a file first");

alert.showAndWait();
throw new Exception();
          
      }
      
File ft =new File(cf.getText());
     p.setFile(Paths.get(ft.getPath()).getFileName().toString());
                
 





   SelectedValue.link=DropBox.GetLINK();

      FXMLLoader loader = new FXMLLoader(getClass().getResource("FinancialS.fxml"));  
Parent root = (Parent) loader.load();  
Scene scene = new Scene(root);  
Stage stage = new Stage();  
stage.setScene(scene);  
stage.setTitle("authetification for dropbox");  
stage.showAndWait(); 
 





p.setFilelink(   DropBox.upload(cf.getText(),
          Paths.get(ft.getPath()).getFileName().toString(),
          us.getId(),SelectedValue.pass));
if( o.Ajoutsujet(p)){
    tlabel.setText("project has been added successfully");
    
}
    
}
catch ( Exception E){
       tlabel.setText("Oups something went wrong , check your date Plesas");
      E.printStackTrace();
      System.out.println(E.getMessage());
    
}
    

    
    
    //tasks 
    
 
    } 
@FXML protected void addlistener() {
    // piechart
     Map<String,Integer> v=o.calculatecat();
         ObservableList<PieChart.Data> pieChartData = 
                FXCollections.observableArrayList(
                   );
     
     
     for (Entry<String, Integer> entry  : v.entrySet()){ 
         pieChartData.add(new PieChart.Data(entry.getKey(),entry.getValue()));
      

     }
   

         
       catcharts.setTitle("Projects By Categories");
       catcharts.setData(pieChartData);
       
    
    

               projectnames.setCellValueFactory(new PropertyValueFactory<Projet, String>("name"));
       projectdiscriptons.setCellValueFactory(new PropertyValueFactory<Projet, String>("Discription"));
              projectid.setCellValueFactory(new PropertyValueFactory<Projet, Integer>("idproject"));
   

       projetTables.getItems().setAll(o.findAllbycat((String)cats.getValue()));
cats.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>(){


    
    
    

    @Override
    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
          System.out.println((String)cats.getValue());//To change body of generated methods, choose Tools | Templates.
           projectnames.setCellValueFactory(new PropertyValueFactory<Projet, String>("name"));
       projectdiscriptons.setCellValueFactory(new PropertyValueFactory<Projet, String>("Discription"));
   

       projetTables.getItems().setAll(o.findAllbycat((String)cats.getValue()));
    }
});
      
    } 

//tabs 


@FXML protected void addlistener2() {

helpType.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>(){


    

    @Override
    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
         tdis.setDisable(true);
   dtask.setDisable(true);
           hdis.setDisable(true);
        
        
 if(((String)helpType.getValue()).equals("Financial")){
     
 tdis.setDisable(true);
   dtask.setDisable(true);
           hdis.setDisable(true);
    target.setDisable(false);
    prize.setDisable(false);
     
 }
 
  if(((String)helpType.getValue()).equals("Technical")){
     dtask.setDisable(true);
           hdis.setDisable(true);
                target.setDisable(true);
           prize.setDisable(true);
        tdis.setDisable(false);
      
           
      
     
 }
   if(((String)helpType.getValue()).equals("Human Resources")){
            target.setDisable(true);
           prize.setDisable(true);
            tdis.setDisable(true);
              dtask.setDisable(false);
           hdis.setDisable(false);
     
     
 }
    }
});
      
    } 






//get user projects 

@FXML protected void userp() {
   projectname.setCellValueFactory(new PropertyValueFactory<Projet, String>("name"));
        projectdate.setCellValueFactory(new PropertyValueFactory<Projet,Date>("date"));
       projetTable.getItems().setAll(o.findAllbyuser(savedusers.savedlogedin.getId()));
   
      
    } 

//delete project 

@FXML protected void deletep() {
ObservableList selectedItems = projetTable.getSelectionModel().getSelectedItems();
   Alert alert = new Alert(AlertType.CONFIRMATION);
alert.setTitle("Confirmation Dialog");
alert.setHeaderText("Look, a Confirmation Dialog");
alert.setContentText("delete?");

Optional<ButtonType> result = alert.showAndWait();

           
   

       
    
     
if (result.get() == ButtonType.OK){
      for (Object o1: selectedItems){
          System.out.println(((Projet)(o1)).toString());
           Projet p1=(Projet)(o1);
     o.SupprimeProjet(p1);
      }
} else {
    // ... user chose CANCEL or closed the dialog
}
    
         userp();
    } 



@FXML protected void update() throws SQLException {
    
       ProjectDAO o = new ProjectDAO();
       Projet p = SelectedValue.p;
       p.setDiscription(edis.getText());
       p.setName(ename.getText());
           System.out.println("zfqdfsfz"+p.getId());
       p.setType((String)ecat.getValue());
       o.update(p);
       SelectedValue.p=o.findProjectbyname(ename.getText());
    

       
    
    

   
 
    } 
 @FXML TextField search ;
@FXML protected void search() {
    try{
       ProjectDAO o = new ProjectDAO();
        

               projectnames1.setCellValueFactory(new PropertyValueFactory<Projet, String>("name"));
       projectdiscriptons1.setCellValueFactory(new PropertyValueFactory<Projet, String>("Discription"));
              projectid1.setCellValueFactory(new PropertyValueFactory<Projet,Integer>("idproject"));
   

       projetTables1.getItems().setAll(o.findAllbyname(search.getText()));
       for (Projet p : o.findAllbyname(search.getText())){
           System.out.println(p);
           
       }

       
    }
    
    catch ( Exception e){
        System.out.println(e.getMessage());
    }
   
 
    } 



 @FXML  public Projet selectedproject ( javafx.scene.input.MouseEvent g){

      ObservableList selectedItems = projetTable.getSelectionModel().getSelectedItems();
   Projet p1=null;
      for (Object o1: selectedItems){
          System.out.println(((Projet)(o1)).toString());
         p1=(Projet)(o1);
           
          sp=p1;
          
          System.out.println(sp);
        
break;
    
      }
       System.out.println(sp);
       SelectedValue.p=sp;
       SelectedValue.pc=null;
      return p1;
     
        
    
      
   
    
    }
 
@FXML
 TextField etarget ;
@FXML
 TextArea eprize ;
  @FXML
 TextArea etdis ;
  @FXML
  TextField ehdis;
 
  @FXML  public void setOnMouseEntered( javafx.scene.input.MouseEvent g){
               

try{
   Projet p = o.findProjectbyname(SelectedValue.p.getName());
   
   
   ename.setText(p.getName());
   edis.setText(p.getDiscription());
       etarget.setText(p.getTarget().toString());
       eprize.setText(p.getPrize());
       etdis.setText(p.gettDiscription());
       ehdis.setText(p.getHtask());
}
catch(Exception e) {
    
}
       
       
    

      
      

      
    
   
    
    }
  
  
  

  
  





     





 
    
    
   
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        
    }    

    @Override
    public void run() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
    
    
    
    
    
    
    //Paroject details 
    
   @FXML 
 private   AnchorPane dpane ;
   
   
   
   
   @FXML protected void openew(ActionEvent event) throws IOException {

 
    
  try {                    
        URL url = getClass().getResource("EditProject.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(url);
        fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
        AnchorPane page = (AnchorPane) fxmlLoader.load(url.openStream()); 
FadeTransition ft = new FadeTransition(Duration.millis(3000), page);

  

ft.setFromValue(0.0);
ft.setToValue(1.0);
ft.play();
        dpane.getChildren().clear();
        dpane.getChildren().add(page);
        
    } 
    catch (IOException e) {
        e.printStackTrace();
    }
   }
  
  
     @FXML protected void pdetails(ActionEvent event) throws IOException {

 
    
  try {                    
        URL url = getClass().getResource("ProjectDetails.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(url);
        fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
        AnchorPane page = (AnchorPane) fxmlLoader.load(url.openStream()); 
FadeTransition ft = new FadeTransition(Duration.millis(3000), page);

  

ft.setFromValue(0.0);
ft.setToValue(1.0);
ft.play();
        dpane.getChildren().clear();
        dpane.getChildren().add(page);
        
    } 
    catch (IOException e) {
        e.printStackTrace();
    }
  
  
  


   

    
      
}
     
     
       @FXML
       Label po;
       
        @FXML
       Label pf;
         @FXML
       Label pc;
         
          @FXML
       Label ph;
           @FXML
       Label pd;
           
               @FXML
       Label rating;
                
       
     
         @FXML  public void setOnMouseEnteredpd( javafx.scene.input.MouseEvent g){
             try {
             
               Projet p=new Projet() ;
if (SelectedValue.p!=null){

        p = o.findProjectbyname(SelectedValue.p.getName());
   
   
}

else {
       p = o.findProjectbyid(SelectedValue.pc.getId());
      
    
}

po.setText(p.getCreateur().getUsername());
pf.setText(p.getFile());
pd.setText(p.getDiscription());
pc.setText(p.getDate().toString());
ph.setText(p.getHelpType());
rating.setText(p.getRating().toString());
       fbo.setCellValueFactory(new PropertyValueFactory<FeedBackt, String>("Owner"));
    fbt.setCellValueFactory(new PropertyValueFactory<FeedBackt, String>("text"));
         List<Feedback> listep;
   
 
   

     listep =fd.findAllbyprojectid(p.getId());
                List<FeedBackt> listep2  =new ArrayList<FeedBackt>() ;
     
     for (Feedback f : listep){
         FeedBackt t = new FeedBackt();
      t.setDate(f.getDate());
        t.setText(f.getText());
        t.setOwner(f.getEditeur().getUsername());
        listep2.add(t);
      
      
         
     }
     
     fb.getItems().setAll(listep2);
     List<Sponsoring> listf;
      listf =s.findAllbyprojectid(p.getId());
                List<Sponsorint> listef2  =new ArrayList<Sponsorint>() ;
     
           spo.setCellValueFactory(new PropertyValueFactory<Sponsorint, String>("owner"));
    spondate.setCellValueFactory(new PropertyValueFactory<Sponsorint, Date>("date"));
    
                 for (Sponsoring fa : listf){
         Sponsorint t = new Sponsorint();
  t.setId(fa.getId());
         t.setOwner(fa.getSponsor().getUsername());
      t.setDate(fa.getDate());
        t.setText(fa.getText());
        t.setFile(fa.getFile());
        t.setFilelint(fa.getFilelint());
        
        listef2.add(t);
      
      
         
     }
    
   

     spon.getItems().setAll(listef2);



     
   
             }
             catch (Exception e){
                 
             }







       
    

      
      

      
    
   
    
    }
         
         
         
         
         //seearch category treatments
         @FXML
         AnchorPane catsp ;

 @FXML  public void selectedprojectc ( javafx.scene.input.MouseEvent g){

      ObservableList selectedItems = projetTables.getSelectionModel().getSelectedItems();
   Projet p1=null;
      for (Object o1: selectedItems){
          System.out.println(((Projet)(o1)).toString());
         p1=(Projet)(o1);
           
          sp=p1;
          
          System.out.println(sp);
        
break;
    
      }
      SelectedValue.pc=sp ;
      System.out.println(SelectedValue.pc.getId());
      SelectedValue.p=null;
     
      
      

         
      
     
        
    
      
   
    
      
    }
  @FXML  public void selectedprojectc2 ( javafx.scene.input.MouseEvent g){

      ObservableList selectedItems = projetTables1.getSelectionModel().getSelectedItems();
   Projet p1=null;
      for (Object o1: selectedItems){
          System.out.println(((Projet)(o1)).toString());
         p1=(Projet)(o1);
           
          sp=p1;
          
          System.out.println(sp);
        
break;
    
      }
      SelectedValue.pc=sp ;
      System.out.println(SelectedValue.pc.getId());
      SelectedValue.p=null;
     
      
      

         
      
     
        
    
      
   
    
      
    }
 
  @FXML  public void getselectedsponsor( javafx.scene.input.MouseEvent g){

      ObservableList selectedItems =spon.getSelectionModel().getSelectedItems();
Sponsorint s =  null;
      SponsoringDAO s1= new SponsoringDAO() ;
      for (Object o1: selectedItems){
          System.out.println(((Sponsorint)(o1)).toString());
        s=(Sponsorint)(o1);
           
        SelectedValue.selectedsponsoring=s1.findSponsbyid(s.getId());
        System.out.println(SelectedValue.selectedsponsoring+"aaaaaaaazdfvqv");
break;
    
      }
  
      
      

         
      
     
        
    
      
   
    
      
    }
 
 
 
      @FXML protected void opendc(ActionEvent event) throws IOException {

 
    
  try {                    
        URL url = getClass().getResource("ProjectDetails.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(url);
        fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
        AnchorPane page = (AnchorPane) fxmlLoader.load(url.openStream()); 
FadeTransition ft = new FadeTransition(Duration.millis(3000), page);

  

ft.setFromValue(0.0);
ft.setToValue(1.0);
ft.play();
        catsp.getChildren().clear();
        catsp.getChildren().add(page);
        
    } 
    catch (IOException e) {
        e.printStackTrace();
    }
      }

      
      @FXML  AnchorPane catsp1;
            @FXML protected void opendc1(ActionEvent event) throws IOException {

 
    
  try {                    
        URL url = getClass().getResource("ProjectDetails.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(url);
        fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
        AnchorPane page = (AnchorPane) fxmlLoader.load(url.openStream()); 
FadeTransition ft = new FadeTransition(Duration.millis(3000), page);

  

ft.setFromValue(0.0);
ft.setToValue(1.0);
ft.play();
        catsp1.getChildren().clear();
        catsp1.getChildren().add(page);
        
    } 
    catch (IOException e) {
        e.printStackTrace();
    }
      }
  
       @FXML protected void download(ActionEvent event) throws IOException {
 VBox vbox = new VBox(500);
 
 WebView browser = new WebView();
 

  try {   
      if (SelectedValue.p!=null) {
          
             Desktop.getDesktop().browse(new URL(SelectedValue.p.getFilelink()).toURI()); 
          
      }
      
      else {
                Desktop.getDesktop().browse(new URL(SelectedValue.pc.getFilelink()).toURI());
          
      }
    } 
  
    catch (Exception e) {
        e.printStackTrace();
    }
       }
       
        @FXML protected void download2(ActionEvent event) throws IOException {

 

  try {   

          
             Desktop.getDesktop().browse(new URL(SelectedValue.selectedsponsoring.getFilelint()).toURI()); 
     
      
  }
  
    catch (Exception e) {
        e.printStackTrace();
    }
       }
  
        @FXML protected void feed(ActionEvent event) throws IOException {
 
 
try{ 
    
    TextInputDialog dialog = new TextInputDialog("walter");
    DialogPane dialogPane = dialog.getDialogPane();
dialogPane.getStylesheets().add(
   getClass().getResource("dialog.css").toExternalForm());
dialogPane.getStyleClass().add("dialog");
   
dialog.setTitle("Feedback");

dialog.setHeaderText("Project Feedback");
dialog.setContentText("enter Feedback Text");


Optional<String> result = dialog.showAndWait();
if (result.isPresent()){
    Feedback f = new Feedback();
   us.setId(savedusers.savedlogedin.getId());
   f.setEditeur(us);
   f.setProjet(SelectedValue.pc);
   f.setText(result.get());
   FeedBackDAO fd= new FeedBackDAO();

   
     Calendar calendare = Calendar.getInstance();
    java.sql.Date ourJavaDateObject = new java.sql.Date(calendare.getTime().getTime());
    f.setDate(ourJavaDateObject);
       fd.addFeedback(f);
   

}
}
    catch (Exception e) {
      Alert alert = new Alert(AlertType.INFORMATION);
alert.setTitle("Information Dialog");
alert.setHeaderText(null);
alert.setContentText(e.getMessage());

alert.showAndWait();
    }
  
  
  


    

   

    
      
}
    
 
 
 
//FeedBackTable
 
 
          @FXML
    private TableView<FeedBackt> fb;
    @FXML
    private TableColumn<FeedBackt, String> fbo;
    @FXML
    private TableColumn<FeedBackt, String> fbt;
    
         @FXML  public void setOnMouselefpd( javafx.scene.input.MouseEvent g){
               

   
   
   
po.setText("");
pf.setText("");
pd.setText("");
pc.setText("");
ph.setText("");
 
         List<Feedback> listep=new ArrayList<>();

            List<FeedBackt> listep2  =new ArrayList<FeedBackt>() ;
     
     for (Feedback f : listep){
         FeedBackt t = new FeedBackt();
      t.setDate(f.getDate());
        t.setText(f.getText());
        t.setOwner(f.getEditeur().getUsername());
        listep2.add(t); 
      
      
         
     }
    
    

       fb.getItems().setAll(listep2);
       
            

            List<Sponsorint> listeps  =new ArrayList<Sponsorint>() ;
     

    
    

       spon.getItems().setAll(listeps);

}
         
         
         
                 @FXML protected void sd(ActionEvent event)  {
                     Projet p=new Projet() ;
             
                 
                 
                     
                 if (SelectedValue.pc==null)
                 {
                     p=o.findProjectbyname(SelectedValue.p.getName());
                     
                 }
                 else {
                         p=o.findProjectbyname(SelectedValue.pc.getName());
                         }
                 
                     if (ph.getText().equals("Financial")){
                         Alert alert = new Alert(AlertType.INFORMATION);
                          DialogPane dialogPane = alert.getDialogPane();
dialogPane.getStylesheets().add(
   getClass().getResource("dialog.css").toExternalForm());
dialogPane.getStyleClass().add("dialog");
alert.setTitle("Information Dialog");
alert.getDialogPane().setPrefSize(480, 320);
alert.setHeaderText("Look, an Information Dialog");
alert.setContentText(" Projetct Target:"+p.getTarget().toString()+"\n  Help Prize:"+p.getPrize());
alert.showAndWait();
                         
                     }
                     
                                      if (ph.getText().equals("Technical")){
                                          
                         Alert alert = new Alert(AlertType.INFORMATION);
                         alert.getDialogPane().setPrefSize(480, 320);
                                      DialogPane dialogPane = alert.getDialogPane();
dialogPane.getStylesheets().add(
   getClass().getResource("dialog.css").toExternalForm());
dialogPane.getStyleClass().add("dialog");
alert.setTitle("Information Dialog");
alert.getDialogPane().setPrefSize(480, 320);
alert.setTitle("Information Dialog");
alert.setHeaderText("Look, an Information Dialog");
alert.setContentText(" Promblem DisCription: "+p.gettDiscription());
alert.showAndWait();
                         
                     }
                                      
    if (ph.getText().equals("Human Resources")){
                         Alert alert = new Alert(AlertType.INFORMATION);
                                  DialogPane dialogPane = alert.getDialogPane();
dialogPane.getStylesheets().add(
   getClass().getResource("dialog.css").toExternalForm());
dialogPane.getStyleClass().add("dialog");
alert.setTitle("Information Dialog");
alert.getDialogPane().setPrefSize(480, 320);
alert.setTitle("Information Dialog");
alert.setHeaderText("Look, an Information Dialog");
alert.setContentText(" Task Date:"+p.getTaskDate().toString()+"\n  TaskDiscription:"+p.getHtask());
alert.showAndWait();
                         
                     }
 
                 }


       
    

      
      
       @FXML protected void sponsor(ActionEvent event) throws IOException {
  Projet p = o.findProjectbyid(SelectedValue.pc.getId());
 try{


  
  if (p.getHelpType().equals("Technical")){
   FXMLLoader loader = new FXMLLoader(getClass().getResource("Sponsort.fxml"));  
Parent root = (Parent) loader.load();  
Scene scene = new Scene(root);  
Stage stage = new Stage();  
stage.setScene(scene);  
stage.setTitle("technical sponsoring");  
stage.showAndWait(); 

  }
 }
  
  catch (Exception e ) {
          
          }

  
      
       
  
    if (p.getHelpType().equals("Financial")){
               Alert alert = new Alert(AlertType.INFORMATION);
                          DialogPane dialogPane = alert.getDialogPane();
dialogPane.getStylesheets().add(
   getClass().getResource("dialog.css").toExternalForm());
dialogPane.getStyleClass().add("dialog");
alert.setTitle("Information Dialog");
alert.getDialogPane().setPrefSize(320, 320);
alert.setHeaderText("Look, an Information Dialog");
alert.setContentText(" Financial Sposoring  is Only Available on Our Website , pay it a Visit dont be afraid !s");
alert.showAndWait();
  
      
       }
        if (p.getHelpType().equals("Financial")){
               Alert alert = new Alert(AlertType.INFORMATION);
                          DialogPane dialogPane = alert.getDialogPane();
dialogPane.getStylesheets().add(
   getClass().getResource("dialog.css").toExternalForm());
dialogPane.getStyleClass().add("dialog");
alert.setTitle("Information Dialog");
alert.getDialogPane().setPrefSize(320, 320);
alert.setHeaderText("Look, an Information Dialog");
alert.setContentText(" Financial Sposoring  is Only Available on Our Website , pay it a Visit dont be afraid !s");
alert.showAndWait();
  
      
       }
        
              if (p.getHelpType().equals("Human Resources")){
                  
                  Alert alert = new Alert(AlertType.CONFIRMATION);
alert.setTitle("Confirmation Dialog");
alert.setHeaderText("Human");
alert.setContentText("by pressing ok you commit to help in the task ");

Optional<ButtonType> result = alert.showAndWait();
if (result.get() == ButtonType.OK){
    Sponsoring S = new Sponsoring();
      Calendar calendare = Calendar.getInstance();
    java.sql.Date ourJavaDateObject = new java.sql.Date(calendare.getTime().getTime());
    S.setDate(ourJavaDateObject);
    S.setDate(ourJavaDateObject);
    S.setIsGoing(1);
    User u = new User();
            u.setId(savedusers.savedlogedin.getId());
     S.setSponsor(u);
 S.setProjet(SelectedValue.pc);
 s.createshponsoring(S);

} else {
 
}
                  
      
      
       }
        
       }
       
       
      
    
         @FXML  public void LoadWeb( javafx.scene.input.MouseEvent g){
               
donate.setFontScale(2);
   WebEngine webEngine = donate.getEngine();
     //    webEngine.setUserStyleSheetLocation("data:,body { -fx-font: 12px Arial;   -fx-font-scale: 1; }");
       
   
webEngine.load(SelectedValue.link);

      donate.setZoom(0.75);


      
         
     }
    
         
         @FXML
         TextArea stextf;
          @FXML
         Label filanme;
           @FXML
       Button stb;
         
          @FXML protected void createts(ActionEvent event) throws IOException, DbxException, URISyntaxException, SQLException {

 Sponsoring S= new  Sponsoring();
 
 User u = new User();
 u.setId(savedusers.savedlogedin.getId());

 S.setSponsor(u);
 S.setProjet(SelectedValue.pc);
S.setText(stextf.getText());
if (filanme.getText().equals("")){
    
}

File ft =new File(filanme.getText());
     S.setFile(Paths.get(ft.getPath()).getFileName().toString());
                
 




   SelectedValue.link=DropBox.GetLINK();
   FXMLLoader loader = new FXMLLoader(getClass().getResource("FinancialS.fxml"));  
Parent root = (Parent) loader.load();  
Scene scene = new Scene(root);  
Stage stage = new Stage();  
stage.setScene(scene);  
stage.setTitle("authetification for dropbox");  
stage.showAndWait(); 




    

S.setFilelint(DropBox.upload(filanme.getText(),
          Paths.get(ft.getPath()).getFileName().toString(),
          us.getId(),SelectedValue.pass));

s.createstponsoring(S);

 Stage stage2 = (Stage)  stb.getScene().getWindow();

    stage2.close();

  


  
       }
         
            @FXML protected void choose2(ActionEvent event)  {

 
   FileChooser chooser = new FileChooser();
   chooser.setTitle("Open File");
   File file = chooser.showOpenDialog(new Stage());
    filanme.setText( file.getPath());
   
   
   

    
  
  
       }
            
            
            
             
          @FXML
    private TableView<Sponsorint> spon;
    @FXML
    private TableColumn<Sponsorint, String> spo;
    @FXML
    private TableColumn<Sponsorint, Date> spondate;
    
       @FXML
    private TableColumn<Sponsorint, Date> sponsoringid;
    

 
    
    
    @FXML Slider rslide ;
    @FXML Button  submitrating ;
    
    @FXML Label ratem ;
    
    
         @FXML protected void rate(ActionEvent event) throws IOException  {
             rslide.setVisible(true);
             submitrating.setVisible(true);
         
             rslide.setMin(0);
rslide.setMax(5);
rslide.setValue(2);
 rslide.getStylesheets().add(getClass().getResource("me.css").toExternalForm());


             

}
        
         
                @FXML protected void submitrating(ActionEvent event)   {
             
System.out.println(Math.round(rslide.getValue()));
o.rate(SelectedValue.pc.getId(), (int) Math.round(rslide.getValue()));
rslide.setVisible(false);
             submitrating.setVisible(false);
   
             

}
                               @FXML protected void sponsordet(ActionEvent event) throws IOException   {

   
              FXMLLoader loader = new FXMLLoader(getClass().getResource("spdetails.fxml"));  
Parent root = (Parent) loader.load();  
Scene scene = new Scene(root);  
Stage stage = new Stage();  
stage.setScene(scene);  
stage.setTitle("Sponsorisation details");  
stage.showAndWait(); 

}



  @FXML  Label lbl1;
  @FXML  Label lbl2;
  @FXML  Label lbl3;
  @FXML  Label lbl4;
  @FXML  Label lbl5;
     @FXML  private void animate(javafx.scene.input.MouseEvent g) throws IOException {
                  
   icon1.setFitWidth(68);
   icon1.setFitHeight(68);
    lbl1.setVisible(true);
   
        
icon1.setPreserveRatio(false);
  
     }
   
      @FXML  private void animate1(javafx.scene.input.MouseEvent g) throws IOException {
   try {                    

  
   
      icon2.setFitWidth(68);
   icon2.setFitHeight(68);
    lbl2.setVisible(true);
   
        
icon1.setPreserveRatio(false);
   
        
    } 
   
   
   
   
    catch (Exception e) {
   
    }
      }
    @FXML  private void animate2(javafx.scene.input.MouseEvent g) throws IOException {
   try {                    

  
   
      icon3.setFitWidth(68);
   icon3.setFitHeight(68);
    lbl3.setVisible(true);
   
        
icon1.setPreserveRatio(false);
   
        
    } 
   
   
   
   
    catch (Exception e) {
   
    }
    }
   
    @FXML  private void animate4(javafx.scene.input.MouseEvent g) throws IOException {
   try {                    

  
   
      icon4.setFitWidth(68);
   icon4.setFitHeight(68);
    lbl4.setVisible(true);
   
        
icon1.setPreserveRatio(false);
   
        
    } 
   
   
   
   
    catch (Exception e) {
   
    }
    }
   
    @FXML  private void animate5(javafx.scene.input.MouseEvent g) throws IOException {
   try {                    

  
   
      icon5.setFitWidth(68);
   icon5.setFitHeight(68);
    lbl5.setVisible(true);
   
        
icon1.setPreserveRatio(false);
   
        
    } 
   
   
   
   
    catch (Exception e) {
   
    }
    }
       
    @FXML  private void animates(javafx.scene.input.MouseEvent g) throws IOException {
   try {                    

  
   
      icon4.setFitWidth(58);
   icon4.setFitHeight(58);
    lbl4.setVisible(false);
      icon5.setFitWidth(58);
   icon5.setFitHeight(58);
    lbl5.setVisible(false);
       icon3.setFitWidth(58);
   icon3.setFitHeight(58);
    lbl3.setVisible(false);
      icon2.setFitWidth(58);
   icon2.setFitHeight(58);
    lbl2.setVisible(false);
    
     icon1.setFitWidth(58);
   icon1.setFitHeight(58);
    lbl1.setVisible(false);
    
   
        

   
        
    } 
   
   
   
   
    catch (Exception e) {
   
    }
       
    }  
           @FXML
        private void loadforum(javafx.scene.input.MouseEvent g) throws IOException {
   try {                    
        URL url = getClass().getResource("/screensframework/aee.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(url);
        fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
        AnchorPane page = (AnchorPane) fxmlLoader.load(url.openStream()); 

        mainp.getChildren().clear();
        mainp.getChildren().add(page);
        
    } 
    catch (IOException e) {
        e.printStackTrace();
    }
                
            
       
    }
        
        
        @FXML Label spflbl;
        @FXML Label splbl;
        
        
            @FXML private void loadponinfo(javafx.scene.input.MouseEvent g) throws IOException {
   try {                    
spflbl.setText(SelectedValue.selectedsponsoring.getFile());
splbl.setText(SelectedValue.selectedsponsoring.getText());
        
    } 
    catch (Exception e) {
        e.printStackTrace();
    }
   
            }
           
            @FXML private void opentuto(javafx.scene.input.MouseEvent g) throws IOException {

                 URL url = getClass().getResource("/GUI/ArticleClient.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(url);
        fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
        AnchorPane page = (AnchorPane) fxmlLoader.load(url.openStream()); 

        mainp.getChildren().clear();
        mainp.getChildren().add(page);
       
    }
           
            
       
    }
   
   
   

    
  
  
   
         
             
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    


   
   
   
   
   
   
   
   
   
   
   
    

