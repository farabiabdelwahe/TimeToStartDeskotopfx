/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;
import Classes.Projet;
import Util.DataSource;
import com.dropbox.core.*;
import java.awt.Desktop;
import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Locale;
import java.util.Optional;
import javafx.scene.control.TextInputDialog;

public class DropBox {
   
            
        
    public static  String upload(String s,String fn, int id ,String t)  throws IOException, DbxException, URISyntaxException, SQLException {
        // Get your app key and secret from the Dropbox developers website.
            final String APP_KEY ="ppnlezxmuvhqs31";
        final String APP_SECRET = "2pwr8uu3kaszul1";


        DbxAppInfo appInfo = new DbxAppInfo(APP_KEY, APP_SECRET);

        DbxRequestConfig config = new DbxRequestConfig("TimeToStart",
            Locale.getDefault().toString());
        DbxWebAuthNoRedirect webAuth = new DbxWebAuthNoRedirect(config, appInfo);

        // Have the user sign in and authorize your app.
        String authorizeUrl = webAuth.start();
         


      
               TextInputDialog dialog = new TextInputDialog();


 
     


      
// Traditional way to get the response value.



        // This will fail if the user enters an invalid authorization code.
        DbxAuthFinish authFinish = webAuth.finish(t);
        String accessToken = authFinish.accessToken;

        DbxClient client = new DbxClient(config, accessToken);

        System.out.println("Linked account: " + client.getAccountInfo().displayName);

        File inputFile = new File(s);
        FileInputStream inputStream = new FileInputStream(inputFile);
      
            DbxEntry.File uploadedFile = client.uploadFile("/TTs"+fn,
                DbxWriteMode.add(), inputFile.length(), inputStream);
            System.out.println();
            
     
            inputStream.close();
        

        DbxEntry.WithChildren listing = client.getMetadataWithChildren("/"+fn);
   
  return client.createShareableUrl(uploadedFile.path);
    }
    
    
    
 public  static String GetLINK(){
     
     
                 final String APP_KEY ="ppnlezxmuvhqs31";
        final String APP_SECRET = "2pwr8uu3kaszul1";


        DbxAppInfo appInfo = new DbxAppInfo(APP_KEY, APP_SECRET);

        DbxRequestConfig config = new DbxRequestConfig("TimeToStart",
            Locale.getDefault().toString());
        DbxWebAuthNoRedirect webAuth = new DbxWebAuthNoRedirect(config, appInfo);

        // Have the user sign in and authorize your app.
        String authorizeUrl = webAuth.start();
         

       return authorizeUrl ;
     
     
       
      
      
  }



    
    
}

   
   
   
 
