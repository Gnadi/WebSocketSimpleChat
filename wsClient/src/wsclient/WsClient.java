/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wsclient;

import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javax.websocket.ClientEndpoint;
import javax.websocket.Session;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.WebSocketContainer;
/**
 *
 * @author gnadl
 */
public class WsClient extends Application {
    
  private Session session;
  private static final Logger LOGGER = Logger.getLogger(WsClient.class.getName());
  
  
    @Override
    public void start(Stage primaryStage) throws URISyntaxException, DeploymentException, IOException {
        final TextField textField = new TextField();
        Button sendButton = new Button("Send");
        HBox hbox = new HBox();
        hbox.getChildren().addAll(textField,sendButton);
        TextArea textArea = new TextArea();
        textArea.setFocusTraversable(false);
        textArea.setEditable(false);
        VBox vBox = new VBox();
        vBox.getChildren().addAll(textArea, hbox);
        StackPane stackPane = new StackPane();
        stackPane.getChildren().add(vBox);
        primaryStage.setScene(new Scene(stackPane,250,200));
        final WsClientEndpoint endpoint = new WsClientEndpoint(textArea);
        sendButton.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent arg0){
                try{
                endpoint.sendMessage(textField.getText());
                textField.clear();

                }catch(IOException ex){
                    ex.printStackTrace();
                }
            }
        });
        
        primaryStage.show();
    }
    
   
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    
    
}
