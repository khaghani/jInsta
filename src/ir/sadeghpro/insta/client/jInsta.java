/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ir.sadeghpro.insta.client;

//import crawel.db_connect;
import java.io.IOException;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;

/**
 *
 * @author peter
 */
public class jInsta {

    private final static String URL = "https://www.instagram.com/";
    private final static String POST_ADDRESS = URL + "graphql/query/?query_id=17888483320059182&variables=";
    private final static String COMMENT_ADDRESS = URL + "graphql/query/?query_id=17852405266163336";

    public void getAccount(String username) {
        String link = URL + username + "/?__a=1";
        try {
            Response r = Jsoup.connect(link).ignoreContentType(true).execute();
        } catch (IOException ex) {
            Logger.getLogger(jInsta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void getPost(String instaId){
        getPost(instaId, 12, "");
    }
    
    public void getPost(String instaId, String after){
        getPost(instaId, 12, after);
    }
    
    public void getPost(String instaId, int first){
        getPost(instaId, first, "");
    }

    public void getPost(String instaId, int first, String after) {
        String link = POST_ADDRESS + "{\"id\":\"" + instaId + "\",\"first\":" + first + (after.length() > 0 ? ",\"after\":\"" + after + "\"" : "") + "}";
        try {
            Response r = Jsoup.connect(link).ignoreContentType(true).execute();
            
        } catch (IOException ex) {
            Logger.getLogger(jInsta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void getComment(String shortcode, int first, String after){
        String link = COMMENT_ADDRESS + "&shortcode=" + shortcode + "&first=" + first + (after.length() > 0 ? "&after=" + after : "");
    }

}
