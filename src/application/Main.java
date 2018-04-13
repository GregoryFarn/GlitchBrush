package application;
	
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			 
			primaryStage.setTitle("Launch Page");
	        Font font = Font.loadFont(getClass().getResourceAsStream("desdemon.ttf"), 130);
			Hyperlink suli = new Hyperlink("Glitch");
	        suli.setId("suli");
	        suli.setTranslateY(-20);	
	        suli.setBorder(Border.EMPTY);
	        suli.setTextFill(Color.BLACK);
	        suli.setOnAction(new EventHandler<ActionEvent>() {
	 
	            @Override
	            public void handle(ActionEvent event) {
	                DirectionPage d = new DirectionPage();
	                d.start(primaryStage);
	            }
	        });
	        
	        
	        StackPane root = new StackPane();
	        root.setId("pane");
	        root.getChildren().add(suli);
	        Scene scene = new Scene(root, 1200, 800);
	        scene.getStylesheets().addAll(this.getClass().getResource("LaunchPage.css").toExternalForm());
	        primaryStage.setScene(scene);
	        java.net.URL url = this.getClass().getResource("LaunchPage.css");
	        if (url == null) {
	            System.out.println("Resource not found. Aborting.");
	            System.exit(-1);
	        }
	        String css = url.toExternalForm(); 
	        scene.getStylesheets().add(css);
	        primaryStage.show();
	        
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
