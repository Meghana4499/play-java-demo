package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.HashMap;
import java.util.Map;

public class hellocontroller extends Controller {
    Map<Integer,String> map = new HashMap<Integer, String>();
    public Result first() {
        String msg="Hello Good Morning!!";
        return ok(msg);
    }
    public Result hellouser(String name){
        String msg="Hello "+name;
        return ok(msg);
    }
    public Result hellouserpost(){
        JsonNode requestJson = request().body().asJson();
        String firstName = null;
        String id  = null;
        if (requestJson.has("first_name")) {
            firstName = requestJson.get("first_name").asText();
        }
        if (requestJson.has("id")) {
            id = requestJson.get("id").asText();
        }
        if (firstName!=null && id!=null){
            String msg = "Hello "+firstName+" "+id;
            return ok(msg);
        }
        return badRequest("please provide both");
    }
    public Result details(){
        JsonNode requestJson = request().body().asJson();
        String userName = null;
        int userId = 0;
        String msg = null;


        if (requestJson.has("name")) {
            userName = requestJson.get("name").asText();
        }
        if (requestJson.has("id")) {
            userId = requestJson.get("id").asInt();
        }
        if (userName != null && userId != 0) {
            map.put(userId , userName);

            for (Integer id: map.keySet()){
                String key = id.toString();
                String value = map.get(id).toString();
                msg = "Your details are inserted "+value;
                return ok(msg);
            }


        }
        return badRequest("Provide user_name and user_id");
    }
    public Result DetailsGet(String userId){
        int uid = Integer.parseInt((userId));
        String msg = null;
        for (Integer id: map.keySet()) {
            if (id == uid) {
                String key = id.toString();
                String value = map.get(id).toString();
                msg = "Your name is " + value + " and your id is " + key;
                return ok(msg);
            }
        }
        return badRequest("UserId doesn't exist");
    }
}
