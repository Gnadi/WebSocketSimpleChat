/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wsclient;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import javafx.scene.control.TextArea;
import javax.websocket.ClientEndpoint;
import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.Endpoint;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

/**
 *
 * @author gnadl
 */
@ClientEndpoint
public class WsClientEndpoint extends Endpoint {

   
    Session session = null;

    public WsClientEndpoint(TextArea text) throws URISyntaxException, DeploymentException, IOException {
        URI uri = new URI("ws://localhost:8080/ws/echo");
        ContainerProvider.getWebSocketContainer().connectToServer(this, uri);
        session.addMessageHandler(new WsMessageHandler(text));
    }
    
    
    @OnOpen
    public void onOpen(Session sn, EndpointConfig ec) {
         this.session = sn;
    }
    public void sendMessage(String msg) throws IOException {
        session.getBasicRemote().sendText(msg);

    }
    /* @OnMessage
    public void onMessage(InputStream input) {
        System.out.println("WebSocket message Received!");

    }
    @OnClose
    public void onClose() {
        connectToWebSocket();
    }
    
    @OnOpen
    public void onOpen(Session session) {
        System.out.println("Connected ... " + session.getId());
        try {
            session.getBasicRemote().sendText("start");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    
    private void connectToWebSocket() {

        WebSocketContainer container = ContainerProvider.getWebSocketContainer();
        try {
            URI uri = URI.create("ws://localhost:8080/ws/echo");
            container.connectToServer(this, uri);
        } catch (DeploymentException | IOException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
            System.exit(-1);
        }
    }*/

    
}
