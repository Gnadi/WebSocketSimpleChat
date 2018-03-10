package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.io.IOException;
import java.net.Proxy;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private Button btn;

    private Socket socket;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void onClick() throws IOException {


        socket = new Socket("ws://localhost/ws/echo",80);

        socket.connect();
        System.out.println("help");
    }
}
