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
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 *
 * @author 007fa
 */
public class GlitchBrush extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
    	Group root = new Group();
    	Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
    	Scene scene = new Scene(root,screenBounds.getWidth(),screenBounds.getHeight());
        primaryStage.setTitle("Glitch Brush");
        primaryStage.setScene(scene);
        primaryStage.setFullScreen(true);
        pixelSort pS = new pixelSort(root,scene,"photo.jpg");
        selectionBox sb = new selectionBox(root,scene);
        primaryStage.show();
        
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
