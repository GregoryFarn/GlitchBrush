/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author 007fa
 */
public class GlitchBrush extends Application {
	selectionBox selection;
    @Override
    public void start(Stage primaryStage) throws Exception{
    	StackPane root = new StackPane();
    	Scene scene = new Scene(root,1920);
        primaryStage.setTitle("Glitch Brush");
        primaryStage.setScene(scene);
        primaryStage.setFullScreen(true);
        Canvas canvas = new Canvas(scene.getWidth(),scene.getHeight());
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.BLUE);
        gc.fillRect(75,75,100,100);
        root.getChildren().add(canvas);
        root.setStyle("-fx-background-color: red");
        canvas.addEventHandler(MouseEvent.MOUSE_PRESSED, 
     	       new EventHandler<MouseEvent>() {
     	           @Override
     	           public void handle(MouseEvent e) {
     	               selection = new selectionBox(e.getX(),e.getY(),gc);
     	           }
     	       });
        canvas.addEventHandler(MouseEvent.MOUSE_DRAGGED, 
        	       new EventHandler<MouseEvent>() {
        	           @Override
        	           public void handle(MouseEvent e) {
        	        	   selection.updateSize(e.getX(), e.getY());
        	        	   selection.drawBox();
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
