package chat;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class ChatApp extends Application {
	
	//TO START AS A SERVER, SET THIS TO TRUE
	
	//TO START AS A CLIENT, SET IT TO FALSE
    private boolean isServer = false;
    
    //variable project name?
    

    private TextArea messages = new TextArea();
    private NetworkConnection connection = isServer ? createServer() : createClient();

    private Parent createContent() {
        messages.setFont(Font.font(72));
        messages.setPrefHeight(550);
        TextField input = new TextField();
        //i dont know how photos will be saved/how to indicate they will be saved
        //
        input.setOnAction(event -> {//when you hit enter on input text field, this code happens
            String message = isServer ? "Server: " : "Client: ";
            message += input.getText();
            input.clear();

            messages.appendText(message + "\n");

            try {
                connection.send(message);
            }
            catch (Exception e) {
                messages.appendText("Failed to send\n");
            }
        });

        VBox root = new VBox(20, messages, input);
        root.setPrefSize(600, 600);
        return root;
    }

    @Override
    public void init() throws Exception {
        connection.startConnection();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setScene(new Scene(createContent()));
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        connection.closeConnection();
    }

    private Server createServer() {
        return new Server(55555, data -> {
            Platform.runLater(() -> {//runLater is a javafx thing
            	
            	//handling the data goes here!
            	
            	//right now it's just appending to messages because it's a chatapp
                messages.appendText(data.toString() + "\n");
            });
        });
    }

    private Client createClient() {
    	//right now it's connected to localhost
        return new Client("127.0.0.1", 55555, data -> {
            Platform.runLater(() -> {
            	
            	
                messages.appendText(data.toString() + "\n");
                
                
                
                
            });
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}