/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package screensframework;

import Classes.Contribution;
import Classes.Sujet;
import Classes.User;
import DAO.classes.ContributionDAO;
import DAO.classes.SujetDAO;
import DAO.classes.UserDAO;
import GUI.Controllers.savedusers;
import edu.cmu.sphinx.frontend.util.Microphone;
import edu.cmu.sphinx.linguist.Linguist;
import edu.cmu.sphinx.recognizer.Recognizer;
import edu.cmu.sphinx.result.Result;
import edu.cmu.sphinx.util.props.ConfigurationManager;
import edu.cmu.sphinx.util.props.PropertyException;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Date;
import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Youssef
 */
public class AeeController implements Initializable {

    SujetDAO daosujet = new SujetDAO();
    ContributionDAO daocontribution = new ContributionDAO();
    @FXML
    Sujet sujetinitial = new Sujet();
    @FXML
    Contribution contributioninitial = new Contribution();
    @FXML

    private Label alertname;
    @FXML
    private Label nmtopic;
    @FXML
    private TextField commentaire;
    @FXML
    private Label cttopic;
    @FXML
    private Label alertcontent;
    @FXML
    private TextField modifname;
    @FXML
    private TextField modifcontent;

    @FXML
    private TextField modifid;
    @FXML
    private ChoiceBox<String> modiftopicategorie;
    @FXML
    private TextField newtopicname;
    @FXML
    private TextArea newtopicontent;
    @FXML
    private ChoiceBox<String> newtopicategorie;
    @FXML
    private ChoiceBox<String> modifcategorie;
    @FXML
    private ChoiceBox<String> categorieforum;

    @FXML
    private TableView<Sujet> typecategorie;
    @FXML
    private TableColumn<Sujet, Integer> idcolumn;
    @FXML
    private TableColumn<Sujet, String> namecolumn;
    @FXML
    private TableColumn<Sujet, String> contentcolumn;
    @FXML
    private TableColumn<Sujet, Date> datecolumn;
    @FXML
    private TableColumn<Sujet, String> namecrcolumn;

    @FXML
    private TableView<trascontribution> tableviewcomment;
    @FXML
    private TableColumn<trascontribution, String> forumusercolumn;
    @FXML
    private TableColumn<trascontribution, String> forumcommentcolumn;
    @FXML
    private TableColumn<trascontribution, Integer> forumidcolumn;
    @FXML
    private TextField idmodifforum;
    @FXML
    private TextField contentmodifforum;
    @FXML
    private Label namemodif;
     @FXML
    private Label conseil;
    
    @FXML
    private Label namemodif1;
     @FXML
    private Label idlabelmodifforum ; 
      @FXML
    private Label contentlabelmodifforum ; 
        @FXML
    private Label contentmodif;
        @FXML
    private Label categorimodif;
       @FXML
    private BarChart<String , Integer> catcharts;
       
    @FXML
    private CategoryAxis xAxis;

    private ObservableList<String> monthNames = FXCollections.observableArrayList();
    
    static ConfigurationManager cm;
    @FXML
    private Button Validermodif;
        @FXML
    private Button butmodifforum;
        
    public void myVoice() throws PropertyException, InstantiationException, MalformedURLException, IOException {
  // FirstVoice();
        URL url;
        url = AeeController.class.getResource("helloworld.config.xml");

        System.out.println(url);
        System.out.println("Loading...");
        try {
            cm = new ConfigurationManager(url);
            System.out.println("pas de blém de place ");
        } catch (Exception e) {
            System.out.println("Le problem de load est ici : " + e);
        }

        Recognizer recognizer = (Recognizer) cm.lookup("recognize"
                + "r");
        System.out.println("pas de blém de place rec ");
        Microphone microphone = (Microphone) cm.lookup("microphone");
        System.out.println("pas de blém de place mic ");


        /* allocate the resource necessary for the recognizer */
        recognizer.allocate();

        System.out.println("pas de blém de place alo ");

    System.out.println("tkalam");
        /* the microphone will keep recording until the program exits */
        if (microphone.startRecording()) {




            /*
             * This method will return when the end of speech
             * is reached. Note that the endpointer will determine
             * the end of speech.
             */
            Result result = recognizer.recognize();
            System.out.println(result.getBestPronunciationResult());

            if (result != null) {
                String resultText = result.getBestFinalResultNoFiller();
                System.out.println("You said: " + resultText + "\n");
                if (resultText.equalsIgnoreCase("help")) {
                    System.out.println("aaa");
                    final URL resource = getClass().getResource("promo.mp3");
                    final Media media = new Media(resource.toString());
                    final MediaPlayer mediaPlayer = new MediaPlayer(media);
                   
                    mediaPlayer.play();
            
                   
                       
                       
                       
                 
                }

            } else {
                System.out.println("I can't hear what you said.\n");
            }

            //swapGrammar("hello");
            microphone.clear();
            microphone.stopRecording();

//		}
        } else {
            System.out.println("Cannot start microphone.");
            recognizer.deallocate();
            System.exit(1);
        }

    }
     public void FirstVoice() throws PropertyException, InstantiationException, MalformedURLException, IOException {
         System.out.println("hani dkhalt");
        URL url;
        url = AeeController.class.getResource("helloworld.config.xml");

                    System.out.println("first");
                    final URL resource = getClass().getResource("helph.mp3");
                    final Media media1 = new Media(resource.toString());
                    final MediaPlayer mediaPlayer = new MediaPlayer(media1);
                    mediaPlayer.play();
               

    }

    static void swapGrammar(String newGrammarName) {
        try {
            cm = new ConfigurationManager();
            System.out.println("Swapping to grammar " + newGrammarName);
            Linguist linguist = (Linguist) cm.lookup("flatLinguist");
            System.out.println("Swapping to grammar problem flat");
            linguist.deallocate();
            System.out.println("Swapping to grammar delocate ");
            //cm.setGlobalProperty("jsgfGrammar", newGrammarName);
            linguist.allocate();
        } catch (IOException ex) {
            System.out.println("after talk : " + ex);
        }
    }

    @FXML
    private void MoliEar() throws PropertyException, InstantiationException, MalformedURLException, IOException {
     
        myVoice();
    }
       @FXML
    private void MoliEar2() throws PropertyException, InstantiationException, MalformedURLException, IOException {
     
        FirstVoice();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    

    }
    
    

    //aller au forum
    @FXML
    private void gotocategorie(ActionEvent event) throws IOException {

        ObservableList selectedItems = typecategorie.getSelectionModel().getSelectedItems();
        Sujet p1 = null;
        for (Object o1 : selectedItems) {

            p1 = (Sujet) (o1);
            sujetinitial = p1;

        }
        passage.suj = sujetinitial;
     
if(passage.suj.getIdsubject()!=0)
{ 
       

              URL url = getClass().getResource("topicbycategorie.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(url);
        fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
        AnchorPane page = (AnchorPane) fxmlLoader.load(url.openStream()); 

       load.getChildren().clear();
        load.getChildren().add(page);

        
}
else
{
   Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Il faut choisir un sujer");

                alert.setContentText("Choisissez un sujet!!");
                alert.showAndWait();
}
     

    }

//charger page creation sujet
    @FXML
    private void Creerprojet(javafx.scene.input.MouseEvent event) throws IOException {


        URL url = getClass().getResource("AjoutSujet.fxml");
         
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(url);
        fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
        AnchorPane page = (AnchorPane) fxmlLoader.load(url.openStream()); 

       load.getChildren().clear();
        load.getChildren().add(page);
      

    }
    


   

    //CREATION NEW TOPIC
    @FXML
    private void Validerprojet(ActionEvent event) throws IOException {

        try {
            if (newtopicontent.getText().equals("")) {

                alertcontent.setText("NO CONTENT !!!");

            } else if (newtopicname.getText().equals("")) {
                alertname.setText("NO NAME !!!");

            } else {

                User a = savedusers.savedlogedin;
                System.out.println(a.getId()+"test1");
            
                Calendar calendare = Calendar.getInstance();
                java.sql.Date ourJavaDateObject = new java.sql.Date(calendare.getTime().getTime());
                Sujet s = new Sujet();

                s.setName(newtopicname.getText());
                s.setContent(newtopicontent.getText());
                s.setCategories(newtopicategorie.getValue());
                s.setDate(ourJavaDateObject);
                s.setCreateur(a);
                s.setIdsubject(0);
                daosujet.Ajoutsujet(s);
                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Ajouté Avec succés");

                alert.setContentText("votre sujet est ajouté ");
                alert.showAndWait();

            }

        } catch (Exception e) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("ERROR");

            alert.setContentText("FAILED");

            alert.showAndWait();
        }

    }

    @FXML
    private void remplir(javafx.scene.input.MouseEvent event) throws IOException {
        cttopic.setText(passage.suj.getContent());
        nmtopic.setText(passage.suj.getName());
        forumusercolumn.setCellValueFactory(new PropertyValueFactory<trascontribution, String>("owner"));
        forumcommentcolumn.setCellValueFactory(new PropertyValueFactory<trascontribution, String>("content"));
        forumidcolumn.setCellValueFactory(new PropertyValueFactory<trascontribution, Integer>("idcontribution"));
       
        List<trascontribution> f = new ArrayList<trascontribution>();

        for (Contribution c : daocontribution.AfficherContribution(passage.suj.getIdsubject())) {

            trascontribution a = new trascontribution();
            a.setOwner(c.getUser().getUsername());
            a.setIdcontribution(c.getIdcontribution());
            a.setContent(c.getContent());
            f.add(a);

        }
        tableviewcomment.getItems().setAll(f);
    }

    @FXML
    private void Resetnewprojet(ActionEvent event) throws IOException {

        newtopicontent.setText("");
        newtopicname.setText("");

    }

//premier affichage selon les categories
    @FXML
    private void affichercategorie(javafx.scene.input.MouseEvent event) throws IOException {
        namecolumn.setCellValueFactory(new PropertyValueFactory<Sujet, String>("name"));
        idcolumn.setCellValueFactory(new PropertyValueFactory<Sujet, Integer>("idsubject"));
        contentcolumn.setCellValueFactory(new PropertyValueFactory<Sujet, String>("content"));
        datecolumn.setCellValueFactory(new PropertyValueFactory<Sujet, Date>("date"));
        namecrcolumn.setCellValueFactory(new PropertyValueFactory<Sujet, String>("Createur"));

        typecategorie.setItems(daosujet.AfficherSujetSelonCategorie(categorieforum.getValue()));

    }

    @FXML
    private void Edit(ActionEvent event) throws IOException {

        ObservableList selectedItems = typecategorie.getSelectionModel().getSelectedItems();
        User a = savedusers.savedlogedin;
        Sujet p1 = null;
        for (Object o1 : selectedItems) {

            p1 = (Sujet) (o1);
            sujetinitial = p1;

        }
     
        if (selectedItems.size()!=0 ) 
        {
         
                
     
               modifname.setText(sujetinitial.getName());
        modifcontent.setText(sujetinitial.getContent());

        modifid.setText(String.valueOf(sujetinitial.getIdsubject()));
        if(daosujet.returnidcr(Integer.parseInt(modifid.getText()))!= savedusers.savedlogedin.getId())
            {
            Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Not Your Topic");

                alert.setContentText("Not Your Topic");
                alert.showAndWait();

            }
        else
        {     modifid.setVisible(true);
        modifname.setVisible(true);
        modifcontent.setVisible(true);
        namemodif.setVisible(true);
        namemodif1.setVisible(true);
        categorimodif.setVisible(true);
        contentmodif.setVisible(true);
        modifcategorie.setVisible(true);
        Validermodif.setVisible(true);}
          
         
   
        }else
        {
             Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Aucun choix !! ");

                alert.setContentText("Choisissez un Sujet ");
                alert.showAndWait();
        }
     

    }

    //Modifier les sujets            
    @FXML
    private void ValiderModif(ActionEvent event) throws IOException {

        User a = savedusers.savedlogedin;
     
        Calendar calendare = Calendar.getInstance();
        java.sql.Date ourJavaDateObject = new java.sql.Date(calendare.getTime().getTime());
        Sujet s = new Sujet();
        
        s.setName(modifname.getText());
        s.setContent(modifcontent.getText());
        s.setCategories(modifcategorie.getValue());
        s.setDate(ourJavaDateObject);
        s.setCreateur(a);
        s.setIdsubject(Integer.parseInt(modifid.getText()));
        daosujet.Modifiersujet(s);
                modifid.setVisible(false);
        modifname.setVisible(false);
        modifcontent.setVisible(false);
        namemodif.setVisible(false);
        namemodif1.setVisible(false);
        categorimodif.setVisible(false);
        contentmodif.setVisible(false);
        modifcategorie.setVisible(false);
        Validermodif.setVisible(false);

    }

    @FXML
    private void Supprimersujet(ActionEvent event) throws IOException {
         User a = savedusers.savedlogedin;
        ObservableList selectedItems = typecategorie.getSelectionModel().getSelectedItems();
        Sujet p1 = null;
        for (Object o1 : selectedItems) {

            p1 = (Sujet) (o1);
            sujetinitial = p1;

        }
        
         System.out.println(daosujet.returnidcr(sujetinitial.getIdsubject()));
  
       if(daosujet.returnidcr(sujetinitial.getIdsubject())==savedusers.savedlogedin.getId())
       {
       daosujet.Supprimersujet(sujetinitial);
                
 namecolumn.setCellValueFactory(new PropertyValueFactory<Sujet, String>("name"));
        idcolumn.setCellValueFactory(new PropertyValueFactory<Sujet, Integer>("idsubject"));
        contentcolumn.setCellValueFactory(new PropertyValueFactory<Sujet, String>("content"));
        datecolumn.setCellValueFactory(new PropertyValueFactory<Sujet, Date>("date"));
        namecrcolumn.setCellValueFactory(new PropertyValueFactory<Sujet, String>("Createur"));

        typecategorie.setItems(daosujet.AfficherSujetSelonCategorie(categorieforum.getValue()));
           
       }
        else
        {
            
             Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("IMPOSSIBLE");

                alert.setContentText("Ce n'est pas votre sujet ");
                alert.showAndWait();

        }
              
    }

    @FXML
    private void postercom(ActionEvent event) throws IOException {

        
            Contribution c = new Contribution();
            User u = savedusers.savedlogedin;
            Calendar calendare = Calendar.getInstance();
            java.sql.Date ourJavaDateObject = new java.sql.Date(calendare.getTime().getTime());

           
            c.setContent(commentaire.getText());
            c.setDate(ourJavaDateObject);
            c.setSujet(passage.suj);
            c.setUser(u);
            daocontribution.AjoutContribution(c);

            forumusercolumn.setCellValueFactory(new PropertyValueFactory<trascontribution, String>("owner"));
            forumcommentcolumn.setCellValueFactory(new PropertyValueFactory<trascontribution, String>("content"));
            forumidcolumn.setCellValueFactory(new PropertyValueFactory<trascontribution, Integer>("idcontribution"));

            List<trascontribution> f = new ArrayList<trascontribution>();

            for (Contribution co : daocontribution.AfficherContribution(passage.suj.getIdsubject())) {

                trascontribution a = new trascontribution();
                a.setOwner(co.getUser().getUsername());
                a.setIdcontribution(co.getIdcontribution());
                a.setContent(co.getContent());
                f.add(a);

            }
            tableviewcomment.getItems().setAll(f);
         
     

    }
       @FXML
        private AnchorPane mainp;
   

   

    @FXML
    private void Supprimercommentaire(ActionEvent event) throws IOException {
        User u = savedusers.savedlogedin;
        ObservableList selectedItems = tableviewcomment.getSelectionModel().getSelectedItems();
        trascontribution p1 = null;
        Contribution ca = new Contribution();

        for (Object o1 : selectedItems) {

            p1 = (trascontribution) (o1);
            ca.setIdcontribution(p1.getIdcontribution());

        }
        
       if(daocontribution.commentateur(ca.getIdcontribution())==savedusers.savedlogedin.getId())
       {
            daocontribution.SupprimerContribution(ca);
      
       }
        else
        {
            
             Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("IMPOSSIBLE");

                alert.setContentText("Ce n'est pas votre commentaire");
                alert.showAndWait();

        }
             
               
        forumusercolumn.setCellValueFactory(new PropertyValueFactory<trascontribution, String>("owner"));
        forumcommentcolumn.setCellValueFactory(new PropertyValueFactory<trascontribution, String>("content"));
        forumidcolumn.setCellValueFactory(new PropertyValueFactory<trascontribution, Integer>("idcontribution"));

        List<trascontribution> f = new ArrayList<trascontribution>();

        for (Contribution co : daocontribution.AfficherContribution(passage.suj.getIdsubject())) {

            trascontribution a = new trascontribution();
            a.setOwner(co.getUser().getUsername());
            a.setIdcontribution(co.getIdcontribution());
            a.setContent(co.getContent());
            f.add(a);

        }
        tableviewcomment.getItems().setAll(f);
        //statistique      
    }

    @FXML
    private void preparemodif(ActionEvent event) throws IOException {

        ObservableList selectedItems = tableviewcomment.getSelectionModel().getSelectedItems();
        trascontribution p1 = null;
        Contribution ca = new Contribution();

        for (Object o1 : selectedItems) {

            p1 = (trascontribution) (o1);
            ca.setIdcontribution(p1.getIdcontribution());
            ca.setContent(p1.getContent());

        }
  
       if(daocontribution.commentateur(ca.getIdcontribution())==savedusers.savedlogedin.getId())
       {
            idmodifforum.setText(String.valueOf(ca.getIdcontribution()));
        contentmodifforum.setText(ca.getContent());
        contentlabelmodifforum.setVisible(true);
        idlabelmodifforum.setVisible(true);
        butmodifforum.setVisible(true);
        contentmodifforum.setVisible(true);
        idmodifforum.setVisible(true);
         tableviewcomment.setVisible(false);
      
       }
        else
        {
            
             Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("IMPOSSIBLE");

                alert.setContentText("Ce n'est pas votre commentaire");
                alert.showAndWait();

        }
   

        //statistique      
    }

    @FXML
    private void Modifiercommentaire(ActionEvent event) throws IOException {
        tableviewcomment.setVisible(false);
        Contribution ccc = new Contribution();
        ccc.setIdcontribution(Integer.parseInt(idmodifforum.getText()));
        ccc.setContent(contentmodifforum.getText());
        daocontribution.ModifierContribution(ccc);
        forumusercolumn.setCellValueFactory(new PropertyValueFactory<trascontribution, String>("owner"));
        forumcommentcolumn.setCellValueFactory(new PropertyValueFactory<trascontribution, String>("content"));
        forumidcolumn.setCellValueFactory(new PropertyValueFactory<trascontribution, Integer>("idcontribution"));

        List<trascontribution> f = new ArrayList<trascontribution>();

        for (Contribution co : daocontribution.AfficherContribution(passage.suj.getIdsubject())) {

            trascontribution a = new trascontribution();
            a.setOwner(co.getUser().getUsername());
            a.setIdcontribution(co.getIdcontribution());
            a.setContent(co.getContent());
            f.add(a);

        }
        tableviewcomment.getItems().setAll(f);
        tableviewcomment.setVisible(true);

        //statistique      
    }
     @FXML
    private void getstat(ActionEvent event) throws IOException 
    {
          
catcharts.setVisible(true);
 conseil.setVisible(true);
        XYChart.Series<String, Integer> series = new XYChart.Series<>();

     Map<String,Integer> v=daosujet.calculatecat();
        XYChart.Series<String,Number> series1 = new XYChart.Series();
        series1.setName("XYChart.Series 1");
           
        series1.getData().add(new XYChart.Data("January", 100));
     
     for (Entry<String, Integer> entry  : v.entrySet())
     { 
          series.getData().add(new XYChart.Data<>(entry.getKey(),entry.getValue()) );
          System.out.println(entry.getKey());
          System.out.println(entry.getValue());
          
      

     }
 
        Timeline tl = new Timeline();
        tl.getKeyFrames().add(new KeyFrame(Duration.millis(500), 
            new EventHandler<ActionEvent>() {
                @Override public void handle(ActionEvent actionEvent) {
                for (XYChart.Series<String, Integer> series : catcharts.getData()) {
                   
                }
            }
        }));
        tl.setCycleCount(Animation.INDEFINITE);
        tl.play();


        catcharts.getData().add(series);
        
    }
    @FXML AnchorPane load ;
    @FXML AnchorPane interf ;
    
    
    
    }
    
    


