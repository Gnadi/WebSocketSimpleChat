/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wsclient;

import java.io.StringReader;
import javafx.scene.control.TextArea;
import javax.json.Json;
import javax.websocket.MessageHandler;
import javax.websocket.Session;



/**
 *
 * @author gnadl
 */
public class WsMessageHandler implements MessageHandler.Whole<String>{

    TextArea textArea = null;

    public WsMessageHandler(TextArea textArea) {
        this.textArea = textArea;
    }
    
    @Override
    public void onMessage(String t) {
        textArea.appendText(Json.createReader(new StringReader(t)).readObject().getString("message")+"\n");
    }
    
}
