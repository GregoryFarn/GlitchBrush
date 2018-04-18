package application;

import java.sql.Statement;
import java.sql.PreparedStatement;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;


import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Border;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class SignUpLogIn extends Application{
	
	public static int getUserID() {
		return userIDtoSend;
	}
	public static String getUsername() {
		return user;
	}
	public static String getPassword() {
		return pass;
	}
	public static String getEmail() {
		return email;
	}
	public static boolean loginCheck() {
		return logged;
	}
	
    public static void main(String[] args) {
        launch(args);
    }
    
	 //once the int is passed to the next .java file, get SQL data for their projects 
	 //project name: GlitchUsers / table name: images
	 //data column names: imageID, userID, imageName
	 
	 static int userIDtoSend = 0;
	 static String user = "";
	 static String pass = "";
	 static String email = "";
	 static boolean logged = false;

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
		PasswordField liPassword = new PasswordField();
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
		Label loginMsg = new Label();
		loginMsg.setTranslateY(0);
		loginMsg.setTranslateX(-425);

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
				
				user = liUsername.getText();
				pass = liPassword.getText();
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
						loginMsg.setText("Successful login");
		    			loginMsg.setTextFill(Color.GREEN);
		    			
		    			email = rs.getString("email");
		    			logged = true;
		    			UserPage u = new UserPage();
			    			u.start(primaryStage);
						
					}
					
					if((user.equals(username) && !pass.equals(password))) { //password incorrect
						System.out.println("Password incorrect");

						loginMsg.setText("Incorrect password");
		    			loginMsg.setTextFill(Color.RED);
		    			userIDtoSend = userID;
		    			
		    			
					}
					
				}
				
				if(!userFound && userIDtoSend==0) {
					System.out.println("No user found");
					loginMsg.setText("User not found");
					loginMsg.setTextFill(Color.RED);
				}
				
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} // provide driverName, dbURL, user, password...
	   		 catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	   		 
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
   	 PasswordField suPassword= new PasswordField();
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
   	 Label signupMsg = new Label();
   	 signupMsg.setTranslateY(50);
   	 signupMsg.setTranslateX(75);
   	 
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
				
				user = suUsername.getText();
				pass = suPassword.getText();
				email = suEmail.getText();
				boolean add = true;
   				
				while(rs.next()) { //as long as there are more rows
					String username = rs.getString("username");
					String userEmail = rs.getString("email");
					
					if(user.equals(username)) { //same username
						signupMsg.setText("Username already exists");
						signupMsg.setTextFill(Color.RED);
						add = false;
						
					}
					else if(email.equals(userEmail)) { //same email
						signupMsg.setText("Email already exists");
						signupMsg.setTextFill(Color.RED);
		    			add = false;
					}
					
				}
   				
				if(add) {
					ps = conn.prepareStatement("INSERT INTO Users (username,password,email) VALUES('" 
  								+ suUsername.getText() + "','" + suPassword.getText() + "','" 
  								+ suEmail.getText() + "')");
	
					System.out.println("User added: \t username: " + suUsername.getText() + "\t pass: " + suPassword.getText() 
								+ "\t email: " + suEmail.getText());

					ps.executeUpdate();

					signupMsg.setText("Successfully added new user!");
					signupMsg.setTextFill(Color.GREEN);
					
					String temp = rs.getString("userID");
					userIDtoSend = Integer.parseInt(temp);
					logged = true;
					
					UserPage u = new UserPage();
	    			u.start(primaryStage);
					
				}

   			   	conn.close();
 
   				
   			} catch (ClassNotFoundException | SQLException e) {
   				// TODO Auto-generated catch block
   				System.out.println("error: " + e.getMessage());
   			} // provide driverName, dbURL, user, password...
   	   		 catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
   	   		 
   	        }
   	   	 
   	   	 });

   	 
	   	 	StackPane root = new StackPane();
	   	 	root.getChildren().add(LogIn);
	   	 	root.getChildren().add(liUsername);
	   	 	root.getChildren().add(liPassword);
	   	 	root.getChildren().add(SignUp);
	   	 	root.getChildren().add(suUsername);
	   	 	root.getChildren().add(suEmail);
	   	 	root.getChildren().add(suPassword);
	   	 	root.getChildren().add(liSubmit);
	   	 	root.getChildren().add(suSubmit);
	   	 	root.getChildren().add(loginMsg);
	   	 	root.getChildren().add(signupMsg);
	    
	   	 	Scene scene = new Scene(root, 1080, 600);
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
