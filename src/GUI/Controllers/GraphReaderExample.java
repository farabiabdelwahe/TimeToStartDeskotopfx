package GUI.Controllers;

import static java.lang.String.format;
import static java.lang.System.currentTimeMillis;
import static java.lang.System.out;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.DefaultJsonMapper;
import com.restfb.Facebook;
import com.restfb.FacebookClient;
import com.restfb.JsonMapper;
import com.restfb.Parameter;
import com.restfb.json.JsonArray;
import com.restfb.json.JsonObject;
import com.restfb.types.Page;
import com.restfb.types.Post;
import com.restfb.types.Url;
import com.restfb.types.User;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import javax.mail.MessagingException;
import DAO.classes.UserDAO;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

@SuppressWarnings("deprecation")
public class GraphReaderExample {

   String ffusername;
   String ffid;
    private final FacebookClient facebookClient;

    UserDAO mdao = new UserDAO();

    public GraphReaderExample(String accessToken) {
        facebookClient = new DefaultFacebookClient(accessToken);
    }

    public void runEverything() throws SQLException, ClassNotFoundException, UnsupportedEncodingException, MessagingException, IOException {
       fetchObject();
        fetchObjectsAsJsonObject();
        fetchObjects();
        fetchDifferentDataTypesAsJsonObject();
    }

    void fetchObject() throws SQLException, ClassNotFoundException, UnsupportedEncodingException, MessagingException, IOException {
        out.println("* Fetching single objects *");

        User user = facebookClient.fetchObject("me", User.class);
        Page page = facebookClient.fetchObject("cocacola", Page.class);
        
        ffusername=user.getName();
       Classes.User u = new Classes.User();
    
   
       u.setNom(user.getName());
       u.setFbid(user.getId());
          Facebookuser.fbu=u;
      
       System.out.println("Facebook User Name : "+Facebookuser.fbu.getNom());
        System.out.println("Facebook Id : "+Facebookuser.fbu.getFbid());
        System.out.println("Facebook Birthday : "+Facebookuser.fbu.getBirthdate());
        System.out.println("Facebook Email : "+Facebookuser.fbu.getEmail());
   
        mdao.AjouterMembre(user.getName(),Integer.parseInt(user.getId()));
        System.out.println("test");
        
        
    }

    void fetchObjectsAsJsonObject() throws SQLException, ClassNotFoundException, UnsupportedEncodingException, MessagingException {
        out.println("* Fetching multiple objects at once as a JsonObject *");

        List<String> ids = new ArrayList<String>();
        ids.add("btaylor");
        ids.add("http://MyLogin35.com/");

        // Make the API call
        JsonObject results = facebookClient.fetchObjects(ids, JsonObject.class);

        // Pull out JSON data by key and map each type by hand.
        JsonMapper jsonMapper = new DefaultJsonMapper();
        User user = jsonMapper.toJavaObject(results.getString("btaylor"), User.class);
        Url url = jsonMapper.toJavaObject(results.getString("http://MyLogin35.com/"), Url.class);

        out.println("User is " + user);
        out.println("URL is " + url);
    }

    void fetchObjects() throws SQLException, ClassNotFoundException, UnsupportedEncodingException, MessagingException {
        out.println("* Fetching multiple objects at once *");

        FetchObjectsResults fetchObjectsResults
                = facebookClient.fetchObjects(Arrays.asList("me", "cocacola"), FetchObjectsResults.class);

        out.println("User name: " + fetchObjectsResults.me.getName());
        out.println("Page likes: " + fetchObjectsResults.page.getLikes());
    }

    void fetchDifferentDataTypesAsJsonObject() throws SQLException, ClassNotFoundException, UnsupportedEncodingException, MessagingException {
        out.println("* Fetching different types of data as JsonObject *");

        JsonObject btaylor = facebookClient.fetchObject("btaylor", JsonObject.class);
        out.println(btaylor.getString("name"));

        JsonObject photosConnection = facebookClient.fetchObject("me/photos", JsonObject.class);
        JsonArray photosConnectionData = photosConnection.getJsonArray("data");

        if (photosConnectionData.length() > 0) {
            String firstPhotoUrl = photosConnectionData.getJsonObject(0).getString("source");
            out.println(firstPhotoUrl);
        }

        String query = "SELECT uid, name FROM user WHERE uid=220439 or uid=966725940007541";
        List<JsonObject> queryResults = facebookClient.executeQuery(query, JsonObject.class);

        if (queryResults.size() > 0) {
            out.println(queryResults.get(0).getString("name"));
        }
    }

    public static class FetchObjectsResults {

        @Facebook
        User me;

        @Facebook(value = "cocacola")
        Page page;
    }

    void fetchConnections() throws SQLException, ClassNotFoundException, UnsupportedEncodingException, MessagingException {
        out.println("* Fetching connections *");
        Connection<User> myFriends = facebookClient.fetchConnection("me/friends", User.class);
        Connection<Post> myFeed = facebookClient.fetchConnection("me/feed", Post.class);
        out.println("Count of my friends: " + myFriends.getData().size());
        if (myFeed.getData().size() > 0) {
            out.println("First item in my feed: " + myFeed.getData().get(0).getMessage());
        }
    }

    void query() {
        out.println("* FQL Query *");
        List<FqlUser> users
                = facebookClient.executeQuery("SELECT uid, name FROM user WHERE uid=220439 or uid=966725940007541", FqlUser.class);
        out.println("User: " + users);
    }

    void multiquery() {
        out.println("* FQL Multiquery *");
        Map<String, String> queries = new HashMap<String, String>();
        queries.put("users", "SELECT uid, name FROM user WHERE uid=220439 OR uid=966725940007541");
        queries.put("likers", "SELECT user_id FROM like WHERE object_id=966725940007541");
        MultiqueryResults multiqueryResults = facebookClient.executeMultiquery(queries, MultiqueryResults.class);
        out.println("Users: " + multiqueryResults.users);
        out.println("People who liked: " + multiqueryResults.likers);
    }

    public static class FqlUser {

        @Facebook
        String uid;

        @Facebook
        String name;

        @Override
        public String toString() {
            return format("%s (%s)", name, uid);
        }
    }

    public static class FqlLiker {

        @Facebook("user_id")
        String userId;

        @Override
        public String toString() {
            return userId;
        }
    }

    public static class MultiqueryResults {

        @Facebook
        List<FqlUser> users;

        @Facebook
        List<FqlLiker> likers;
    }

    void search() {
        out.println("* Searching connections *");

        Connection<Post> publicSearch
                = facebookClient.fetchConnection("search", Post.class, Parameter.with("q", "ben foulen"),
                        Parameter.with("type", "post"));

        Connection<User> targetedSearch
                = facebookClient.fetchConnection("me/home", User.class, Parameter.with("q", "foulen"),
                        Parameter.with("type", "user"));

        if (publicSearch.getData().size() > 0) {
            out.println("Public search: " + publicSearch.getData().get(0).getMessage());
        }

        out.println("Posts on my wall by friends named Mark: " + targetedSearch.getData().size());
    }

    void metadata() {
        out.println("* Metadata *");

        User userWithMetadata = facebookClient.fetchObject("me", User.class, Parameter.with("metadata", 1));

        out.println("User metadata: has albums? " + userWithMetadata.getMetadata().getConnections().hasAlbums());
    }

    void paging() {
        out.println("* Paging support *");

        Connection<User> myFriends = facebookClient.fetchConnection("me/friends", User.class);
        Connection<Post> myFeed = facebookClient.fetchConnection("me/feed", Post.class, Parameter.with("limit", 100));

        out.println("Count of my friends: " + myFriends.getData().size());

        if (myFeed.getData().size() > 0) {
            out.println("First item in my feed: " + myFeed.getData().get(0));
        }

        for (List<Post> myFeedConnectionPage : myFeed) {
            for (Post post : myFeedConnectionPage) {
                out.println("Post from my feed: " + post);
            }
        }
    }

    void selection() {
        out.println("* Selecting specific fields *");

        User user = facebookClient.fetchObject("me", User.class, Parameter.with("fields", "id,name"));

        out.println("User name: " + user.getName());
    }

    void parameters() {
        out.println("* Parameter support *");

        Date oneWeekAgo = new Date(currentTimeMillis() - 1000L * 60L * 60L * 24L * 7L);

        Connection<Post> filteredFeed
                = facebookClient.fetchConnection("me/feed", Post.class, Parameter.with("limit", 3),
                        Parameter.with("until", "yesterday"), Parameter.with("since", oneWeekAgo));

        out.println("Filtered feed count: " + filteredFeed.getData().size());
    }

    void rawJsonResponse() {
        out.println("* Raw JSON *");
        out.println("User object JSON: " + facebookClient.fetchObject("me", String.class));
    }
}
