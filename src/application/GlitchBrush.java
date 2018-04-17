/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 *
 * @author 007fa
 */
public class GlitchBrush extends Application {
    @Override
	public void start(Stage primaryStage) throws Exception {
		StackPane root = new StackPane();
		Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
		Scene scene = new Scene(root, screenBounds.getWidth(), screenBounds.getHeight());
		primaryStage.setTitle("Glitch Brush");
		primaryStage.setScene(scene);
		primaryStage.setFullScreen(true);
		photo p = new photo(root, scene, "apple.jpg");
		selectionBox sb = new selectionBox(root, scene, p);
		sb.setType(6);
		primaryStage.show();
	}
	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String[] args) {
		launch(args);
	}
    
}
