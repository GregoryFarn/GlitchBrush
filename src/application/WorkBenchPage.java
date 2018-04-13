package application;


import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class WorkBenchPage extends Application {
	
	public static void main(String[] args) {
        launch(args);
    }
	public void start(Stage primaryStage) {
        primaryStage.setTitle("New Project Page");
        Font font = Font.loadFont(getClass().getResourceAsStream("desdemon.ttf"), 20);
        
        //nav bar rectangle
        Rectangle navbar = new Rectangle();
        navbar.setId("navbar");
        navbar.setWidth(1150);
        navbar.setHeight(50);
        navbar.setTranslateY(-350);
        
        Label welcome = new Label("|||  ");
        welcome.setId("welcome");
	   	welcome.setTranslateY(-350);
	   	welcome.setTranslateX(-555);
	   	welcome.setTextFill(Color.WHITE);
	   	
	   	//filter button
	   	Hyperlink filter = new Hyperlink(" filter ");
        filter.setId("welcome");
        filter.setTranslateY(-350);
        filter.setTranslateX(-485);
        filter.setBorder(Border.EMPTY);
        filter.setTextFill(Color.WHITE);
        
        filter.setOnAction(new EventHandler<ActionEvent>() {
       	 
            @Override
            public void handle(ActionEvent event) {
            		//connect to filter class
            		
            }
        });
        
        
        //tool button
       	Hyperlink tool = new Hyperlink(" tool ");
        tool.setId("welcome");
        tool.setTranslateY(-350);
        tool.setTranslateX(-392);
        tool.setBorder(Border.EMPTY);
        tool.setTextFill(Color.WHITE);
        
        //login button
       	Hyperlink login = new Hyperlink(" login ");
        login.setId("welcome");
        login.setTranslateY(-350);
        login.setTranslateX(-300);
        login.setBorder(Border.EMPTY);
        login.setTextFill(Color.WHITE);
        login.setOnAction(new EventHandler<ActionEvent>() {
       	 
            @Override
            public void handle(ActionEvent event) {
                SignUpLogIn s = new SignUpLogIn();
                s.start(primaryStage);
            }
        });
        
        //logo button
       	Hyperlink glitch = new Hyperlink("glitch");
        glitch.setId("logo");
        glitch.setTranslateY(-350);
        glitch.setTranslateX(500);
        glitch.setBorder(Border.EMPTY);
        glitch.setTextFill(Color.WHITE);
        glitch.setOnAction(new EventHandler<ActionEvent>() {
        	 
            @Override
            public void handle(ActionEvent event) {
                Main s = new Main();
                s.start(primaryStage);
            }
        });
        
        //work frame
        Rectangle workFrame = new Rectangle();
        workFrame.setId("navbar");
        workFrame.setWidth(1050);
        workFrame.setHeight(650);
        workFrame.setTranslateY(25);
        
        //project image
        final ImageView selectedImage = new ImageView();   
        Image image1 = null;
		try {
			image1 = new Image(new FileInputStream("test.jpg"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        selectedImage.setImage(image1);

        
        
        StackPane root = new StackPane();
        root.setId("pane");
        root.getChildren().add(navbar);
        root.getChildren().add(welcome);
        root.getChildren().add(filter);
        root.getChildren().add(tool);
        root.getChildren().add(login);
        root.getChildren().add(glitch);
        root.getChildren().add(workFrame);
        root.getChildren().add(selectedImage);
        Scene scene = new Scene(root, 1200, 800);
        primaryStage.setScene(scene);
        java.net.URL url = this.getClass().getResource("WorkBenchPage.css");
        if (url == null) {
            System.out.println("Resource not found. Aborting.");
            System.exit(-1);
        }
        String css = url.toExternalForm(); 
        scene.getStylesheets().add(css);
        primaryStage.show();
	}
}

