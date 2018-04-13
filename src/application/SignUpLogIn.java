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
    
	 
	 String user = "username";
	 String pw = "pass";
	 String checkUser, checkPw;
	 private UserDataAccessor dataAccessor ;
	 
    public void start(Stage primaryStage) {
    	
    	/*	 EMILY'S CODE
   	  * 
   	  * 
   	  	primaryStage.setTitle("SignUp/LogIn");
    	Font font = Font.loadFont(getClass().getResourceAsStream("desdemon.ttf"), 60);
    	
	  
	 BorderPane bp = new BorderPane();
	 bp.setPadding(new Insets(10,50,50,50));
	 
	 HBox hb = new HBox();
	 hb.setPadding(new Insets(20,20,30,30));
	 
	 GridPane gp = new GridPane();
	 gp.setPadding(new Insets(20,20,20,20));
	 gp.setHgap(5);
	 gp.setVgap(5);
	 
	 Label lblUserName = new Label("username");
	 final TextField txtUserName = new TextField();
	 Label lblPassword = new Label("password");
	 final PasswordField pf = new PasswordField();
	 Button btnLogin = new Button("login");
	 final Label lblMessage = new Label();
	 
	 gp.add(lblUserName, 0, 0);
	 gp.add(txtUserName, 1, 0);
	 gp.add(lblPassword, 0, 1);
	 gp.add(pf, 1, 1);
	 gp.add(btnLogin, 2, 1);
	 gp.add(lblMessage, 1, 2);

//	 hb.getChildren().add(text);
	 
	 bp.setId("bp");
	 gp.setId("root");
	 btnLogin.setId("btnLogin");

	 
     btnLogin.setOnAction(new EventHandler<ActionEvent>() {
    	 public void handle(ActionEvent event) {
    		 checkUser = txtUserName.getText().toString();
    		 checkPw = pf.getText().toString();
    		 if(checkUser.equals(user) && checkPw.equals(pw)){
    			 lblMessage.setText("Congratulations!");
    			 lblMessage.setTextFill(Color.GREEN);
    	      }
    		 else{
    			 lblMessage.setText("Incorrect user or pw.");
    			 lblMessage.setTextFill(Color.RED);
    		 }
    		 	txtUserName.setText("");
    		 	pf.setText("");
    	 	}
     	});
     
	 	bp.setTop(hb);
	 	bp.setCenter(gp);
	 	
	 	Scene scene = new Scene(bp);
	// 	scene.getStylesheets().add(getClass().getClassLoader().getResource("SignUpLogIn.css").toExternalForm());
	 	primaryStage.setScene(scene);
	 	primaryStage.titleProperty().bind(
	 			scene.widthProperty().asString().concat(" : ").concat(scene.heightProperty().asString())	 			
	 			);
	 	primaryStage.show();*/
	 	
	// ELI'S CODE
	 
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
   	 
   	 liSubmit.setOnAction(new EventHandler<ActionEvent>() {
   	 @Override
        public void handle(ActionEvent event) {
   		 	//verify
   		 
   		 String driverClassName = "com.mysql.jdbc.Driver";
   		 String dbURL = "jdbc:mysql://localhost/GlitchUsers?user=root&password=root&useSSL=false";
   		 
   		 try {
			dataAccessor = new UserDataAccessor(driverClassName, dbURL);
			
			
			
			
			
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
   				
   				
   				Class.forName(driverClassName);
   				conn = DriverManager.getConnection(dbURL);
   				st = conn.createStatement();
   				ps = conn.prepareStatement("INSERT INTO Users (username,password,email) VALUES('" 
   			   								+ suUsername.getText() + "','" + suPassword.getText() + "','" 
   			   								+ suEmail.getText() + "')");
   				
   				System.out.println("User added: '\t username: " + suUsername.getText() + "\t pass: " + suPassword.getText() 
   						+ "\t email: " + suEmail.getText());
   				ps.executeUpdate();
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
