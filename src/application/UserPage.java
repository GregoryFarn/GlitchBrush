package application;


import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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
	
	public static void main(String[] args) {
        launch(args);
    }
	
	public ArrayList<String> getProjects(int userID) throws SQLException, ClassNotFoundException {
		
		ArrayList<String> result = new ArrayList<String>();
		String driverClassName = "com.mysql.jdbc.Driver";
   		String dbURL = "jdbc:mysql://localhost/GlitchUsers?user=root&password=root&useSSL=false";
   		
		Connection conn = null; //create the connection to database
		Statement st = null; //executes any sql command
		PreparedStatement ps = null;
		ResultSet rs = null; //retrieve data that comes back (from select statement), a table	
			
		Class.forName(driverClassName);
		conn = DriverManager.getConnection(dbURL);
		st = conn.createStatement();
		ps = conn.prepareStatement(" SELECT s.imageID, s.userID, s.imageName");
		rs = st.executeQuery("SELECT * FROM Images;");
		
		
		while(rs.next()) { //as long as there are more rows
			int imgID = rs.getInt("imageID");
			int usrID = rs.getInt("userID");
			String imgName = rs.getString("imageName");
			
			if(usrID == userID) {
				result.add(imgID + " " + imgName);
			}

				
		}
		
		return result;
		
	}
	
	public void start(Stage primaryStage) throws ClassNotFoundException, SQLException {
		
		String username = "guest";
		String password = "guest";
		String email = "guest@email.com";
		int userID = 0;
		boolean loggedIn = SignUpLogIn.loginCheck();
		
		if(loggedIn) {
			username = SignUpLogIn.getUsername();
			if(username.length()>10) {
				username = username.substring(0, 10); //cut off username at 10 chars
			}
			password = SignUpLogIn.getPassword();
			email = SignUpLogIn.getEmail();
			userID = SignUpLogIn.getUserID();
		}
		
		ArrayList<String> projects = getProjects(userID);
		
		
		for(int i=0; i<projects.size(); i++) {
			System.out.println(projects.get(i));
		}
		
		
		
        primaryStage.setTitle("User Page");
        Font font = Font.loadFont(getClass().getResourceAsStream("desdemon.ttf"), 60);
        
        //glitch home button
        Hyperlink glitch = new Hyperlink("Glitch");
        glitch.setId("glitch");
        glitch.setTranslateY(-270);
        glitch.setTranslateX(450);
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
        Label welcome = new Label("Welcome " + username);
        welcome.setId("welcome");
	   	welcome.setTranslateY(-270);
	   	
	   	//start new project button
	   	Hyperlink project = new Hyperlink("Start New Project");
        project.setId("project");
        project.setTranslateY(250);
   //     project.setTranslateX(-35);
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
        Scene scene = new Scene(root, 1080, 600);
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

