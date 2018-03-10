
import java.io.IOException;
import java.io.StringWriter;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonWriter;
import javax.websocket.Session;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.server.ServerEndpoint;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author gnadl
 */
@ServerEndpoint("/echo")
public class Server {
    static Set<Session> sessions = Collections.synchronizedSet(
            new HashSet<Session>());
    
    @OnOpen
   public void connect(Session session){
       sessions.add(session);
        System.out.println("session= "+session);
   }
   @OnClose
   public void close(){
       this.sessions = null;
       System.out.println("Vorbei is burschen!");
   }
   @OnMessage
   public void onMessage(String msg, Session userSession) throws IOException{
       String username = (String) userSession.getUserProperties()
               .get("username");
       
       if(username==null){
           userSession.getUserProperties().put("username", msg);
           userSession.getBasicRemote()
                   .sendText(
                           buildJsonData("System","You are now connected as "
                                   +msg));
       }else{
           Iterator<Session> iter = sessions.iterator();
           while(iter.hasNext()) iter.next().getBasicRemote()
                   .sendText(buildJsonData(username,msg));
       }
       //this.sessions.getAsyncRemote().sendText("Echo "+msg);
   }
   private String buildJsonData(String username, String msg){
       JsonObject jsonObject = Json.createObjectBuilder()
               .add("message",username+": "+msg).build();
       StringWriter wr = new StringWriter();
       try(JsonWriter jsonWriter = Json.createWriter(wr)){
           jsonWriter.write(jsonObject);
       }
       return wr.toString();
   }
}
