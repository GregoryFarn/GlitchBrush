package application;

import javafx.scene.text.Font;

import java.io.File;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
 
public class DirectionPage extends Application {
    static boolean accCheck = false;
	public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Direction Page");
        Font font = Font.loadFont(getClass().getResourceAsStream("desdemon.ttf"), 130);
        //Sign Up button
        Hyperlink suli = new Hyperlink("Sign Up // Login");
        suli.setId("suli");
        suli.setTranslateY(-134);
        suli.setBorder(Border.EMPTY);
        suli.setTextFill(Color.BLACK);
        suli.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {
                SignUpLogIn s = new SignUpLogIn();
                s.start(primaryStage);
            }
        });
        suli.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				if (accCheck) {
				Media sound = new Media(new File("signuporlogin.mp3").toURI().toString());
				MediaPlayer mp = new MediaPlayer(sound);
				mp.play();}
			}
        });
        
        //Import Photo Button
        Hyperlink importPhoto = new Hyperlink("Import Photo");
        importPhoto.setId("importPhoto");
        importPhoto.setTranslateY(134);
        importPhoto.setBorder(Border.EMPTY);
        importPhoto.setTextFill(Color.BLACK);
        importPhoto.setOnAction(new EventHandler<ActionEvent>() {
        	 @Override
             public void handle(ActionEvent event) {
                  newProjectPage p = new newProjectPage();
                  p.start(primaryStage);
             }
        });
        importPhoto.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				if (accCheck) {
				Media sound = new Media(new File("importphoto.mp3").toURI().toString());
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
        root.getChildren().add(suli);
        root.getChildren().add(importPhoto);
        root.getChildren().add(acc);
        Scene scene = new Scene(root, 1080, 600);
        primaryStage.setScene(scene);
        java.net.URL url = this.getClass().getResource("DirectionPage.css");
        if (url == null) {
            System.out.println("Resource not found. Aborting.");
            System.exit(-1);
        }
        String css = url.toExternalForm(); 
        scene.getStylesheets().add(css);
        primaryStage.show();
    }
}
