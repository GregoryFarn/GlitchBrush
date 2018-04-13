package application;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;


import java.util.logging.Logger;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.scene.layout.Border;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


public class newProjectPage extends Application {
	private Desktop desktop = Desktop.getDesktop();
	public static void main(String[] args) {
        launch(args);
    }
	public void start(Stage primaryStage) {
        primaryStage.setTitle("New Project Page");
        Font font = Font.loadFont(getClass().getResourceAsStream("desdemon.ttf"), 100);
        
        //project title field 
        TextField nameField = new TextField();
	   	 nameField.setTranslateY(-100);
	   	 nameField.setText("Project Name");
	   	 nameField.setMaxWidth(150);
	   	 nameField.setId("nameField");
        
	   	 //file chooser
	   	final FileChooser fileChooser = new FileChooser();
	    
        final Hyperlink importButton = new Hyperlink("Import Photo");
        importButton.setBorder(Border.EMPTY);
        importButton.setTextFill(Color.BLACK);
        importButton.setId("importButton");
 
        importButton.setOnAction(new EventHandler<ActionEvent>() {
        	 
            @Override
            public void handle(ActionEvent event) {
            		File file = fileChooser.showOpenDialog(primaryStage);
                if (file != null) {
                		//do something with file here
                    openFile(file);
                }
            }
        });
        
        
        StackPane root = new StackPane();
        root.getChildren().add(nameField);
        root.getChildren().add(importButton);
        Scene scene = new Scene(root, 1200, 800);
        primaryStage.setScene(scene);
        java.net.URL url = this.getClass().getResource("NewProjectPage.css");
        if (url == null) {
            System.out.println("Resource not found. Aborting.");
            System.exit(-1);
        }
        String css = url.toExternalForm(); 
        scene.getStylesheets().add(css);
        primaryStage.show();
	}
	
	private void openFile(File file) {
        try {
            desktop.open(file);
        } catch (IOException ex) {
            Logger.getLogger(
                newProjectPage.class.getName()).log(
                    Level.SEVERE, null, ex
                );
        }
    }
}

