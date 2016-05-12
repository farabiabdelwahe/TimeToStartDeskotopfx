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
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.print.PrinterJob;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.DirectoryChooser;

/**
 * FXML Controller class
 *
 * @author FC
 */
public class ArticleClientController implements Initializable {
    @FXML
    private TextField searchType1;
    @FXML
    private TextField searchAuthorKeyPressed1;
    @FXML
    private TextField searchAuthor1;
    @FXML
    private Button searchBtn1;
    @FXML
    private Button downloadFileBtn1;
    @FXML
    private Button printTableViewBtn1;
    @FXML
    private TableView<ArticleModel> tabArticle1;
    @FXML
    private TableColumn<ArticleModel, String> id1;
    @FXML
    private TableColumn<ArticleModel, Date> date1;
    @FXML
    private TableColumn<ArticleModel, String> type2;
    @FXML
    private TableColumn<ArticleModel, String> author2;
    @FXML
    private TableColumn<ArticleModel, InputStream> file1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        IArticleDao articleDao = new ArticleDao();
        List<Article> articlesByTypeAndByAuthor = articleDao.getArticleByTypeAndByAuthor(searchType1.getText(), searchAuthor1.getText());
        List<ArticleModel> articlesModel = new ArrayList<>();
        for (Article a : articlesByTypeAndByAuthor) {
            ArticleModel articleModel = ArticleModel.convertArticleToArticleModel(a);
            articlesModel.add(articleModel);
        }

        ObservableList<ArticleModel> data = FXCollections.observableArrayList(articlesModel);
        tabArticle1.setItems(data);
        author2.setCellValueFactory(cellData -> cellData.getValue().getAuthor());
        type2.setCellValueFactory(cellData -> cellData.getValue().getType());
        date1.setCellValueFactory(cellData -> cellData.getValue().getDate());
        file1.setCellValueFactory(cellData -> cellData.getValue().getFile());
        id1.setCellValueFactory(cellData -> cellData.getValue().getId());
    }    


    @FXML
    private void search1(ActionEvent event) {
        IArticleDao articleDao = new ArticleDao();
        List<Article> articlesByTypeAndByAuthor = articleDao.getArticleByTypeAndByAuthor(searchType1.getText(), searchAuthor1.getText());
        List<ArticleModel> articlesModel = new ArrayList<>();
        for (Article a : articlesByTypeAndByAuthor) {
            ArticleModel articleModel = ArticleModel.convertArticleToArticleModel(a);
            articlesModel.add(articleModel);
        }

        ObservableList<ArticleModel> data = FXCollections.observableArrayList(articlesModel);
        tabArticle1.setItems(data);
        author2.setCellValueFactory(cellData -> cellData.getValue().getAuthor());
        type2.setCellValueFactory(cellData -> cellData.getValue().getType());
        date1.setCellValueFactory(cellData -> cellData.getValue().getDate());
        file1.setCellValueFactory(cellData -> cellData.getValue().getFile());
        id1.setCellValueFactory(cellData -> cellData.getValue().getId());
    }

    @FXML
    private void downloadFile1(ActionEvent event) {
        ArticleModel selectedItem = tabArticle1.getSelectionModel().getSelectedItem();
        if(selectedItem == null) {
            return;
        }
        FileOutputStream fos = null;
        try {
            IArticleDao articleDao = new ArticleDao();
            Article articleById = articleDao.getArticleById(Integer.parseInt(selectedItem.getId().getValue()));
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
                if(fos != null) {
                    fos.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(ArticleController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void print1(ActionEvent event) {
          //Printer printer = Printer.getDefaultPrinter();

        PrinterJob job = PrinterJob.createPrinterJob();
        if (job != null) {
            job.getPrinter();
            boolean success = job.printPage(tabArticle1);
            if (success) {
                job.endJob();
            }
        }
    }

    @FXML
    private void handelSearchKeyReleased1(KeyEvent event) {
         IArticleDao articleDao = new ArticleDao();
        List<Article> articlesByAuthor = articleDao.getArticleByAuthor(searchAuthorKeyPressed1.getText());
        List<ArticleModel> articlesModel = new ArrayList<>();
        for (Article a : articlesByAuthor) {
            ArticleModel articleModel = ArticleModel.convertArticleToArticleModel(a);
            articlesModel.add(articleModel);
        }

        ObservableList<ArticleModel> data = FXCollections.observableArrayList(articlesModel);
        tabArticle1.setItems(data);
        author2.setCellValueFactory(cellData -> cellData.getValue().getAuthor());
        type2.setCellValueFactory(cellData -> cellData.getValue().getType());
        date1.setCellValueFactory(cellData -> cellData.getValue().getDate());
        file1.setCellValueFactory(cellData -> cellData.getValue().getFile());
        id1.setCellValueFactory(cellData -> cellData.getValue().getId());
    }

    
    

    
}
 

