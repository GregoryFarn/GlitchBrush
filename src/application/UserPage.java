package application;


import javafx.stage.Stage;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TextField;
import javafx.scene.layout.Border;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;


public class UserPage extends Application {
	String username = "Eli";
	public static void main(String[] args) {
        launch(args);
    }
	public void start(Stage primaryStage) {
        primaryStage.setTitle("User Page");
        Font font = Font.loadFont(getClass().getResourceAsStream("desdemon.ttf"), 60);
        
        //glitch home button
        Hyperlink glitch = new Hyperlink("Glitch");
        glitch.setId("glitch");
        glitch.setTranslateY(-350);
        glitch.setTranslateX(500);
        glitch.setBorder(Border.EMPTY);
        glitch.setTextFill(Color.BLACK);
        glitch.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {
                Main s = new Main();
                s.start(primaryStage);
            }
        });
        
        //welcome banner
        Label welcome = new Label("Welcome "+username);
        welcome.setId("welcome");
	   	welcome.setTranslateY(-350);
	   	welcome.setTranslateX(-450);
	   	
	   	//start new project button
	   	Hyperlink project = new Hyperlink("Start New Project");
        project.setId("project");
        project.setTranslateY(365);
        project.setTranslateX(-35);
        project.setBorder(Border.EMPTY);
        project.setTextFill(Color.WHITE);
        project.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {
                newProjectPage s = new newProjectPage();
                s.start(primaryStage);
            }
        });
        
        //making scrollPane to display projects
        ScrollPane scrollPane = new ScrollPane();
        
        //make grid with projects and titles
        GridPane projectGrid = new GridPane();
        projectGrid.setHgap(10);
        projectGrid.setVgap(10);
        
        scrollPane.setPannable(true);
        scrollPane.setPrefSize(200, 200);
        scrollPane.setContent(projectGrid);
        scrollPane.setVbarPolicy(ScrollBarPolicy.ALWAYS);
        
        StackPane root = new StackPane();
        root.setId("pane");
        root.getChildren().add(glitch);
        root.getChildren().add(welcome);
        root.getChildren().add(project);
        //root.getChildren().add(scrollPane);
        Scene scene = new Scene(root, 1200, 800);
        primaryStage.setScene(scene);
        java.net.URL url = this.getClass().getResource("UserPage.css");
        if (url == null) {
            System.out.println("Resource not found. Aborting.");
            System.exit(-1);
        }
        String css = url.toExternalForm(); 
        scene.getStylesheets().add(css);
        primaryStage.show();
}
	}

