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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


public class newProjectPage extends Application {
	private Desktop desktop = Desktop.getDesktop();
	static boolean accCheck = false;
	static String proj;
	
	public static String getProjectName() {
		return proj;
	}
	
	public static void main(String[] args) {
        launch(args);
    }
	public void start(Stage primaryStage) {
        primaryStage.setTitle("New Project Page");
        Font font = Font.loadFont(getClass().getResourceAsStream("desdemon.ttf"), 100);
        
        //project title field 
        TextField nameField = new TextField();
	   	 nameField.setTranslateY(-200);
	   	 nameField.setText("Project Name");
	   	 nameField.setMaxWidth(150);
	   	 nameField.setId("nameField");
	   	 
	   	
        
	   	 //file chooser
	   	final FileChooser fileChooser = new FileChooser();
	    
        final Hyperlink importButton = new Hyperlink("Import Photo");
        importButton.setTranslateY(-100);
        importButton.setBorder(Border.EMPTY);
        importButton.setTextFill(Color.BLACK);
        importButton.setId("importButton");
 
        importButton.setOnAction(new EventHandler<ActionEvent>() {
        	 
            @Override
            public void handle(ActionEvent event) {
            		File file = fileChooser.showOpenDialog(primaryStage);
                if (file != null) {
                	 proj = nameField.getText();
                		//do something with file here
                		String dataForSQL = file.getAbsolutePath();
                		System.out.println(dataForSQL);
                //		openFile(dataForSQL);

                		String s = dataForSQL.replace("\\", "\\\\");
                		
                		WorkBenchPage w = new WorkBenchPage();
                		WorkBenchPage.setProject(s);
                		w.start(primaryStage);
                		
                }
            }
        });
        importButton.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				if (accCheck) {
				Media sound = new Media(new File("importphoto.mp3").toURI().toString());
				MediaPlayer mp = new MediaPlayer(sound);
				mp.play();}
			}
        });
        
        final Hyperlink joinButton = new Hyperlink("Join Project");
        joinButton.setTranslateY(20);
        joinButton.setBorder(Border.EMPTY);
        joinButton.setTextFill(Color.BLACK);
        joinButton.setId("importButton");
 
        joinButton.setOnAction(new EventHandler<ActionEvent>() {
        	 
            @Override
            public void handle(ActionEvent event) {
            		
            }
        });
        joinButton.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				if (accCheck) {
				Media sound = new Media(new File("joinproject.mp3").toURI().toString());
				MediaPlayer mp = new MediaPlayer(sound);
				mp.play();}
			}
        });
        
      //accessibility button
       	Hyperlink acc = new Hyperlink("Accessibility");
        acc.setId("share");
        acc.setTranslateY(280);
        acc.setTranslateX(-480);
        acc.setBorder(Border.EMPTY);
        acc.setTextFill(Color.BLACK);
        acc.setOnAction(new EventHandler<ActionEvent>() {
        	 
            @Override
            public void handle(ActionEvent event) {
            	accCheck = !accCheck;
            }
        });
        
        
        StackPane root = new StackPane();
        root.getChildren().add(nameField);
        root.getChildren().add(importButton);
        root.getChildren().add(joinButton);
        root.getChildren().add(acc);
        Scene scene = new Scene(root, 1080, 600);
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
	
	private void openFile(String file) {
		
		
        /*try {
            desktop.open(file);
        } catch (IOException ex) {
            Logger.getLogger(
                newProjectPage.class.getName()).log(
                    Level.SEVERE, null, ex
                );
        }*/
    }
}

