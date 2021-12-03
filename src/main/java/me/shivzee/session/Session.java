package me.shivzee.session;

import me.shivzee.Config;
import me.shivzee.io.IO;
import org.json.simple.JSONObject;

public class Session {

    /**
     * Generates a new Session Token for api  Read More in OpenTDB Docs
     * @return string the token
     */
    public static String create(){
        try{
            JSONObject json = (JSONObject) Config.parser.parse(IO.requestGET(Config.baseUrl+"/api_token.php?command=request").getResponse());
            return json.get("token").toString();
        }catch (Exception e){
            System.out.println("|NO LOGGER| Exception Caught "+e);
        }
        return "";
    }

    /**
     * Destroys / Reset the Session Token More at API Docs of OpenTDB
     * @param token the token to rest
     * @return status True/False
     */
    public static boolean destroy(String token){
        try{
            JSONObject json = (JSONObject) Config.parser.parse(IO.requestGET(Config.baseUrl+"/api_token.php?command=reset&token="+token).getResponse());
            return json.get("response_code").toString().equals("0");
        }catch (Exception e){
            System.out.println("|NO LOGGER| Exception Caught "+e);
        }
        return false;
    }

}
