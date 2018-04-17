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
	   	
	   	//linked list filter
	   	Hyperlink scramble = new Hyperlink(" Scramble ");
        scramble.setId("welcome");
        scramble.setTranslateY(-350);
        scramble.setTranslateX(-465);
        scramble.setBorder(Border.EMPTY);
        scramble.setTextFill(Color.WHITE);
        scramble.setOnAction(new EventHandler<ActionEvent>() {
       	 
            @Override
            public void handle(ActionEvent event) {
            		//connect to filter class
            		//selectionBox.linkedListSOrt();
            }
        });
        
        
        //stack filter
       	Hyperlink vertical = new Hyperlink(" Vertical ");
        vertical.setId("welcome");
        vertical.setTranslateY(-350);
        vertical.setTranslateX(-330);
        vertical.setBorder(Border.EMPTY);
        vertical.setTextFill(Color.WHITE);
        vertical.setOnAction(new EventHandler<ActionEvent>() {
          	 
            @Override
            public void handle(ActionEvent event) {
            		//connect to filter class
            		//selectionBox.linkedListSOrt();
            }
        });
        
        //horizontal stack filter
        Hyperlink horizontal = new Hyperlink(" Horizontal ");
        horizontal.setId("welcome");
        horizontal.setTranslateY(-350);
        horizontal.setTranslateX(-180);
        horizontal.setBorder(Border.EMPTY);
        horizontal.setTextFill(Color.WHITE);
        horizontal.setOnAction(new EventHandler<ActionEvent>() {
          	 
            @Override
            public void handle(ActionEvent event) {
            		//connect to filter class
            		//selectionBox.linkedListSOrt();
            }
        });
        
        //smooth filter
        Hyperlink smooth = new Hyperlink(" Smooth ");
        smooth.setId("welcome");
        smooth.setTranslateY(-350);
        smooth.setTranslateX(-35);
        smooth.setBorder(Border.EMPTY);
        smooth.setTextFill(Color.WHITE);
        smooth.setOnAction(new EventHandler<ActionEvent>() {
          	 
            @Override
            public void handle(ActionEvent event) {
            		//connect to filter class
            		//selectionBox.linkedListSOrt();
            }
        });
        
        //rough filter
        Hyperlink rough = new Hyperlink(" rough ");
        rough.setId("welcome");
        rough.setTranslateY(-350);
        rough.setTranslateX(75);
        rough.setBorder(Border.EMPTY);
        rough.setTextFill(Color.WHITE);
        rough.setOnAction(new EventHandler<ActionEvent>() {
          	 
            @Override
            public void handle(ActionEvent event) {
            		//connect to filter class
            		//selectionBox.linkedListSOrt();
            }
        });
        
 
        
        
        
        
        
        //login button
       	Hyperlink login = new Hyperlink(" login ");
        login.setId("welcome");
        login.setTranslateY(-349);
        login.setTranslateX(400);
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
        root.getChildren().add(scramble);
        root.getChildren().add(vertical);
        root.getChildren().add(horizontal);
        root.getChildren().add(smooth);
        root.getChildren().add(rough);
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

