/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Controllers;

import DAO.classes.ArticleDao;
import DAO.Interfaces.IArticleDao;
import GUI.Translators.ArticleModel;
import Classes.Article;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.print.PrinterJob;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author Mehdi Rekik
 */
public class ArticleController implements Initializable {

    @FXML
    private Button addFile;
    @FXML
    private Label label;
    @FXML
    private TextField author;
    @FXML
    private Button save;
    @FXML
    private TextField type;

    private File selectedFile = null;
    @FXML
    private Label errorMessage;
    @FXML
    private TextField searchType;
    @FXML
    private TextField searchAuthor;
    @FXML
    private TableView<ArticleModel> tabArticle;
    @FXML
    private Button searchBtn;
    @FXML
    private TableColumn<ArticleModel, String> id;
    @FXML
    private TableColumn<ArticleModel, Date> date;
    @FXML
    private TableColumn<ArticleModel, InputStream> file;
    @FXML
    private TableColumn<ArticleModel, String> type1;
    @FXML
    private TableColumn<ArticleModel, String> author1;
    @FXML
    private Pane paneView;
    @FXML
    private TextField idm;
    @FXML
    private TextField authorm;
    @FXML
    private TextField typem;
    @FXML
    private Button modifierbtn;
    @FXML
    private Button downloadFileBtn;
    @FXML
    private TextField searchAuthorKeyPressed;
    @FXML
    private Button updateBtn;
    @FXML
    private Label pathFile;
    @FXML
    private Button chooseFileBtn;
    @FXML
    private Button printTableViewBtn;
    @FXML
    private Button supprimerbtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        chooseFileBtn.setVisible(false);
        updateBtn.setVisible(false);
        pathFile.setVisible(false);
        idm.setEditable(false);
        modifierbtn.setVisible(false);
        search(null);
    }

    @FXML
    private void click(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {
            label.setText(selectedFile.getPath());
        } else {
            System.out.println("File selection cancelled.");
            selectedFile = null;
            label.setText("");
        }
    }

    @FXML
    private void save(ActionEvent event) {
        if (selectedFile == null) {
            errorMessage.setText("noselected file");
        } else if (author.getText() == null || "".equals(author.getText())) {
            errorMessage.setText("error insert");
        } else {
            try {
                Article article = new Article(type.getText(), author.getText(), new FileInputStream(selectedFile), selectedFile.getName());
                IArticleDao articleDao = new ArticleDao();
                Article res = articleDao.save(article);
                errorMessage.setText("success");
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ArticleController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void search(ActionEvent event) {
        IArticleDao articleDao = new ArticleDao();
        List<Article> articlesByTypeAndByAuthor = articleDao.getArticleByTypeAndByAuthor(searchType.getText(), searchAuthor.getText());
        List<ArticleModel> articlesModel = new ArrayList<>();
        for (Article a : articlesByTypeAndByAuthor) {
            ArticleModel articleModel = ArticleModel.convertArticleToArticleModel(a);
            articlesModel.add(articleModel);
        }

        ObservableList<ArticleModel> data = FXCollections.observableArrayList(articlesModel);
        tabArticle.setItems(data);
        author1.setCellValueFactory(cellData -> cellData.getValue().getAuthor());
        type1.setCellValueFactory(cellData -> cellData.getValue().getType());
        date.setCellValueFactory(cellData -> cellData.getValue().getDate());
        file.setCellValueFactory(cellData -> cellData.getValue().getFile());
        id.setCellValueFactory(cellData -> cellData.getValue().getId());
    }

    @FXML
    private void viewDetail(MouseEvent event) {
        ArticleModel selectedItem = tabArticle.getSelectionModel().getSelectedItem();
        idm.setText(selectedItem.getId().getValue());
        typem.setText(selectedItem.getType().getValue());
        authorm.setText(selectedItem.getAuthor().getValue());
        modifierbtn.setVisible(true);
    }

    @FXML
    private void downloadFile(ActionEvent event) {
        FileOutputStream fos = null;
        try {
            IArticleDao articleDao = new ArticleDao();
            Article articleById = articleDao.getArticleById(Integer.parseInt(idm.getText()));
            BufferedInputStream is = new BufferedInputStream(articleById.getInputStream());

            DirectoryChooser chooser = new DirectoryChooser();
            File showDialog = chooser.showDialog(null);
            File file = new File(showDialog.getPath() + "/" + articleById.getFileName());
            fos = new FileOutputStream(file);
            // you can set the size of the buffer
            byte[] buffer = new byte[2048];
            int r = 0;
            while ((r = is.read(buffer)) != -1) {
                fos.write(buffer, 0, r);
            }
            fos.flush();
            fos.close();
            is.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ArticleController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ArticleController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fos.close();
            } catch (IOException ex) {
                Logger.getLogger(ArticleController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void handelSearchKeyReleased(KeyEvent event) {
        IArticleDao articleDao = new ArticleDao();
        List<Article> articlesByAuthor = articleDao.getArticleByAuthor(searchAuthorKeyPressed.getText());
        List<ArticleModel> articlesModel = new ArrayList<>();
        for (Article a : articlesByAuthor) {
            ArticleModel articleModel = ArticleModel.convertArticleToArticleModel(a);
            articlesModel.add(articleModel);
        }

        ObservableList<ArticleModel> data = FXCollections.observableArrayList(articlesModel);
        tabArticle.setItems(data);
        author1.setCellValueFactory(cellData -> cellData.getValue().getAuthor());
        type1.setCellValueFactory(cellData -> cellData.getValue().getType());
        date.setCellValueFactory(cellData -> cellData.getValue().getDate());
        file.setCellValueFactory(cellData -> cellData.getValue().getFile());
        id.setCellValueFactory(cellData -> cellData.getValue().getId());
    }

    @FXML
    private void updateBtn(ActionEvent event) {
        try {
            FileInputStream fileInputStream = null;
            String fileName = null;
            if (selectedFile != null) {
                fileInputStream = new FileInputStream(selectedFile);
                fileName = selectedFile.getName();
            }

            Article article = new Article(Integer.parseInt(idm.getText()), typem.getText(), authorm.getText(), fileInputStream, fileName);
            IArticleDao articleDao = new ArticleDao();
            Article res = articleDao.update(article);
            //errorMessage.setText("success");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ArticleController.class.getName()).log(Level.SEVERE, null, ex);
        }
        chooseFileBtn.setVisible(false);
        updateBtn.setVisible(false);
        pathFile.setVisible(false);
        downloadFileBtn.setVisible(true);
        search(null);
    }

    @FXML
    private void selectFileBtn(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {
            pathFile.setText(selectedFile.getPath());
        } else {
            System.out.println("File selection cancelled.");
            selectedFile = null;
            pathFile.setText("");
        }
    }

    @FXML
    private void setUpdateBtn(ActionEvent event) {
        chooseFileBtn.setVisible(true);
        updateBtn.setVisible(true);
        pathFile.setVisible(true);
        downloadFileBtn.setVisible(false);
    }

    @FXML
    private void print(ActionEvent event) {
        //Printer printer = Printer.getDefaultPrinter();

        PrinterJob job = PrinterJob.createPrinterJob();
        if (job != null) {
            job.getPrinter();
            boolean success = job.printPage(tabArticle);
            if (success) {
                job.endJob();
            }
        }
    }

    @FXML
    private void deleteBtn(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("supprimer l article");
        alert.setHeaderText("voulez-vous vraiment supprimer cette article");
        alert.setContentText("Are you ok with this?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            // ... user chose OK
            try {
                IArticleDao articleDao = new ArticleDao();
                boolean deleteArticleById = articleDao.deleteArticleById(Integer.parseInt(idm.getText()));
                if (deleteArticleById) {
                    search(null);
                    idm.setText("");
                    typem.setText("");
                    authorm.setText("");
                }
            } catch (Exception e) {
                //errorMessage.setText("");
            }
        } else {
    // ... user chose CANCEL or closed the dialog
        }

    }

}
