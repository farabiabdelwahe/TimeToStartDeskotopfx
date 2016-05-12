/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datasource;

import DAO.classes.ArticleDao;
import Classes.Article;
import static Datasource.DbConnection.INSTANCE;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author FC
 */
public class Test {
    public static void main(String[] argv) {
        InputStream inputStream = null;
        try {
            String filePath = "D:/TestDb/tp.pdf";
            inputStream = new FileInputStream(new File(filePath));
            Article a = new Article("type", "author", inputStream, "test.pdf");
            ArticleDao artDao = new ArticleDao();
            Article save = artDao.save(a);
            System.out.println(save);
            Article aA = new Article(8, "aaaaa", "bbbb", null, "test.pdf");
            Article se = artDao.update(aA);
            Article aa = artDao.getArticleById(8);
            System.out.println(aa);
            List<Article> daa = artDao.getArticleByType("aaaaa");
            System.out.println(aa);
            
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                inputStream.close();
            } catch (IOException ex) {
                Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static void main2(String[] argv) throws SQLException {

        Connection dbConnection = null;
        PreparedStatement preparedStatementInsert = null;
        PreparedStatement preparedStatementUpdate = null;

        String insertTableSQL = "INSERT INTO ARTICLE"
                + "(DATE, TYPE, AUTHOR, FILE) VALUES"
                + "(?,?,?,?)";

		//String updateTableSQL = "UPDATE DBUSER SET USERNAME =? "
        //+ "WHERE USER_ID = ?";
        try {
            dbConnection = INSTANCE.getDBConnection();

            dbConnection.setAutoCommit(false);

            String filePath = "D:/TestDb/tp.pdf";
            InputStream inputStream = new FileInputStream(new File(filePath));

            preparedStatementInsert = dbConnection.prepareStatement(insertTableSQL);
            preparedStatementInsert.setLong(1, INSTANCE.getCurrentDate());
            preparedStatementInsert.setString(2, "type1");
            preparedStatementInsert.setString(3, "mehdi");
            preparedStatementInsert.setBlob(4, inputStream);

            preparedStatementInsert.executeUpdate();

			//preparedStatementUpdate = dbConnection.prepareStatement(updateTableSQL);
            // preparedStatementUpdate.setString(1,
            // "A very very long string caused db error");
            //preparedStatementUpdate.setString(1, "new string");
            //preparedStatementUpdate.setInt(2, 999);
            //preparedStatementUpdate.executeUpdate();
            dbConnection.commit();

            System.out.println("Done!");

        } catch (SQLException e) {

            System.out.println(e.getMessage());
            if (dbConnection != null) {
                dbConnection.rollback();
            }
        } catch (FileNotFoundException ex) {
            System.out.println("--------------> " + ex.getMessage());
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (preparedStatementInsert != null) {
                preparedStatementInsert.close();
            }
            if (preparedStatementUpdate != null) {
                preparedStatementUpdate.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
        }

    }
}
