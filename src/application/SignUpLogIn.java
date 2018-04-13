package application;

import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class SignUpLogIn extends Application {
	
    public static void main(String[] args) {
        launch(args);
    }
    
	 int userIDtoSend = 0; //NETWORKING: SEND THIS INT
	 //once the int is passed to the next .java file, get SQL data for their projects 
	 //project name: GlitchUsers / table name: images
	 //data column names: imageID, userID, imageName
	 
	 String user = "username";
	 String pw = "pass";
	 String checkUser, checkPw;
	 private UserDataAccessor dataAccessor ;
	 
    public void start(Stage primaryStage) {
	 
    	primaryStage.setTitle("SignUpLogIn Page");
   	 	Font font = Font.loadFont(getClass().getResourceAsStream("desdemon.ttf"), 60);
   	 
   	 	//Login banner
   	 	Label LogIn = new Label("LogIn");
   	 	LogIn.setTranslateY(-200);
   	 	LogIn.setTranslateX(-400);
   	 	LogIn.setId("LogIn");
   	 	TextField liUsername = new TextField();
   	 	liUsername.setTranslateY(-150);
   	 	liUsername.setTranslateX(-400);
   	 	liUsername.setText("USERNAME");
		liUsername.setMaxWidth(150);
		liUsername.setId("liUsername");
		TextField liPassword = new TextField();
		liPassword.setTranslateY(-100);
		liPassword.setTranslateX(-400);
		liPassword.setText("PASSWORD");
		liPassword.setMaxWidth(150);
		Hyperlink liSubmit = new Hyperlink("Submit");
		liSubmit.setTranslateY(-50);
		liSubmit.setTranslateX(-425);
		liSubmit.setBorder(Border.EMPTY);
		liSubmit.setTextFill(Color.BLACK);
		liSubmit.setId("liSubmit");
		Label lblMessage = new Label();
		 
		liSubmit.setOnAction(new EventHandler<ActionEvent>() {
   	 
			@Override
        public void handle(ActionEvent event) {
   		 	//verify
   		 
	   		String driverClassName = "com.mysql.jdbc.Driver";
	   		String dbURL = "jdbc:mysql://localhost/GlitchUsers?user=root&password=root&useSSL=false";
	   		 
	   		 try {
			//	dataAccessor = new UserDataAccessor(driverClassName, dbURL);
				
	   			Connection conn = null; //create the connection to database
				Statement st = null; //executes any sql command
				PreparedStatement ps = null;
				ResultSet rs = null; //retrieve data that comes back (from select statement), a table	
					
				Class.forName(driverClassName);
				conn = DriverManager.getConnection(dbURL);
				st = conn.createStatement();
				ps = conn.prepareStatement(" SELECT s.username, s.lastname, s.userID");
				rs = st.executeQuery("SELECT * FROM Users;");
				
				String user = liUsername.getText();
				String pass = liPassword.getText();
				boolean userFound = false;
				
				while(rs.next()) { //as long as there are more rows
					String username = rs.getString("username");
					String password = rs.getString("password");
					int userID = rs.getInt("userID");
	
					//varchar = string
					System.out.println(username + "\t" + password + "\t" + userID);
					
					if(user.equals(username) && pass.equals(password)) { //successful login
						System.out.println("Logging in for user " + userID + ": " + username);
						userIDtoSend = userID;
						
						userFound = true;
						
						//REDIRECT TO WORKBENCH PAGE
						lblMessage.setText("Successful login");
		    			lblMessage.setTextFill(Color.GREEN);
						
					}
					
					if(user.equals(username) && !pass.equals(password)) { //password incorrect
						System.out.println("Password incorrect");
						lblMessage.setText("Incorrect password");
		    			lblMessage.setTextFill(Color.RED);
		    			userIDtoSend = userID;
					}
					
				}
				
				if(!userFound && userIDtoSend==0) { //user does not exist
					System.out.println("User does not exist");
					lblMessage.setText("User does not exist. Please try again");
	    			lblMessage.setTextFill(Color.RED);
				}
				
				userIDtoSend = 0;
				
				
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} // provide driverName, dbURL, user, password...
	   		 
	        }
   	 });
   	
   	 
   	//Sign Up banner
   	 Label SignUp = new Label("SignUp");
   	 SignUp.setTranslateY(-200);
   	 SignUp.setTranslateX(100);
   	 SignUp.setId("SignUp");
   	 TextField suUsername = new TextField();
   	 suUsername.setTranslateY(-150);
   	 suUsername.setTranslateX(100);
   	 suUsername.setText("USERNAME");
   	 suUsername.setMaxWidth(150);
   	 TextField suEmail = new TextField();
   	 suEmail.setTranslateY(-100);
   	 suEmail.setTranslateX(100);
   	 suEmail.setText("EMAIL");
   	 suEmail.setMaxWidth(150);
   	 TextField suPassword= new TextField();
   	 suPassword.setTranslateY(-50);
   	 suPassword.setTranslateX(100);
   	 suPassword.setText("PASSWORD");
   	 suPassword.setMaxWidth(150);
   	 Hyperlink suSubmit = new Hyperlink("Submit");
   	 suSubmit.setTranslateY(0);
   	 suSubmit.setTranslateX(75);
   	 suSubmit.setBorder(Border.EMPTY);
   	 suSubmit.setTextFill(Color.BLACK);
   	 suSubmit.setId("suSubmit");
   	 
   	 suSubmit.setOnAction(new EventHandler<ActionEvent>() {
   	   	 @Override
   	        public void handle(ActionEvent event) {
   	   		 	//verify
   	   		 
   	   		 String driverClassName = "com.mysql.jdbc.Driver";
   	   		 String dbURL = "jdbc:mysql://localhost/GlitchUsers?user=root&password=root&useSSL=false";
   	   		 
   	   		 try {
   		//		dataAccessor = new UserDataAccessor(driverClassName, dbURL);
   				
   				Connection conn = null; //create the connection to database
   				Statement st = null; //executes any sql command
   				PreparedStatement ps = null;
   				ResultSet rs = null; //retrieve data that comes back (from select statement), a table	
   				
   				Class.forName(driverClassName);
   				conn = DriverManager.getConnection(dbURL);
   				st = conn.createStatement();
   				
   				//check if username already exists
   				ps = conn.prepareStatement(" SELECT s.username s.email");
				rs = st.executeQuery("SELECT * FROM Users;");
				
				String user = suUsername.getText();
				String pass = suPassword.getText();
				String email = suEmail.getText();
				boolean add = true;
   				
				while(rs.next()) { //as long as there are more rows
					String username = rs.getString("username");
					String userEmail = rs.getString("email");
					
					if(user.equals(username)) { //same username
						lblMessage.setText("Username already exists");
		    			lblMessage.setTextFill(Color.RED);
						add = false;
						
					}
					else if(email.equals(userEmail)) { //same email
						lblMessage.setText("Email already exists");
		    			lblMessage.setTextFill(Color.RED);
		    			add = false;
					}
					
				}
   				
				if(add) {
					ps = conn.prepareStatement("INSERT INTO Users (username,password,email) VALUES('" 
  								+ suUsername.getText() + "','" + suPassword.getText() + "','" 
  								+ suEmail.getText() + "')");
	
					System.out.println("User added: '\t username: " + suUsername.getText() + "\t pass: " + suPassword.getText() 
								+ "\t email: " + suEmail.getText());

					ps.executeUpdate();

					lblMessage.setText("Successfully added new user!");
	    			lblMessage.setTextFill(Color.GREEN);
					
				}

   			   	conn.close();
 
   				
   			} catch (ClassNotFoundException | SQLException e) {
   				// TODO Auto-generated catch block
   				System.out.println("error: " + e.getMessage());
   			} // provide driverName, dbURL, user, password...
   	   		 
   	        }
   	   	 
   	   	 });

   	 
	   	 	StackPane root = new StackPane();
	   	 	root.getChildren().add(LogIn);
	   	 	root.getChildren().add(liUsername);
	   	 	root.getChildren().add(liPassword);
	   	 	root.getChildren().add(liSubmit);
	   	 	root.getChildren().add(SignUp);
	   	 	root.getChildren().add(suUsername);
	   	 	root.getChildren().add(suPassword);
	   	 	root.getChildren().add(suSubmit);
	   	 	root.getChildren().add(suEmail);
	   	 	root.getChildren().add(lblMessage);
	    
	   	 	Scene scene = new Scene(root, 1200, 800);
	   	 	primaryStage.setScene(scene);
	   	 	java.net.URL url = this.getClass().getResource("SignUpLogIn.css");
	   	 	if (url == null) {
	   	 		System.out.println("Resource not found. Aborting.");
	   	 		System.exit(-1);
	   	 	}
	   	 	String css = url.toExternalForm(); 
	   	 	scene.getStylesheets().add(css);
	   	 	primaryStage.show();
        
       }
 
}
