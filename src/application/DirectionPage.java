package application;

import javafx.scene.text.Font;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.Border;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
 
public class DirectionPage extends Application {
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
        
        //Import Photo Button
        Hyperlink importPhoto = new Hyperlink("Import Photo");
        importPhoto.setId("importPhoto");
        importPhoto.setTranslateY(134);
        importPhoto.setBorder(Border.EMPTY);
        importPhoto.setTextFill(Color.BLACK);
        importPhoto.setOnAction(new EventHandler<ActionEvent>() {
        	 @Override
             public void handle(ActionEvent event) {
                 System.out.println("Link to Import page");
             }
        });
        
        StackPane root = new StackPane();
        root.getChildren().add(suli);
        root.getChildren().add(importPhoto);
        Scene scene = new Scene(root, 1200, 800);
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
