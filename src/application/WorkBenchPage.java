package application;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.sun.deploy.uitoolkit.impl.fx.HostServicesFactory;
import com.sun.javafx.application.HostServicesDelegate;
import javax.imageio.ImageIO;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class WorkBenchPage extends Application {
	static boolean accCheck = false;
	static String url;
	private photo p;
	private boolean isServer = false;
	private NetworkConnection connection = isServer ? createServer() : createClient();

	public static void setProject(String x) {
		url = x;
	}

	public static String getProjectName(int userID, String url) throws ClassNotFoundException, SQLException {
		String driverClassName = "com.mysql.jdbc.Driver";
		String dbURL = "jdbc:mysql://localhost/GlitchUsers?user=root&password=root&useSSL=false";

		Connection conn = null; // create the connection to database
		Statement st = null; // executes any sql command
		PreparedStatement ps = null;
		ResultSet rs = null; // retrieve data that comes back (from select statement), a table

		Class.forName(driverClassName);
		conn = DriverManager.getConnection(dbURL);
		st = conn.createStatement();
		ps = conn.prepareStatement(" SELECT s.imageID, s.userID, s.imageName");
		rs = st.executeQuery("SELECT * FROM Images;");

		String out = "";

		while (rs.next()) {
			int userID_ = rs.getInt("userID");
			String url_ = rs.getString("imageName");
			String projName = rs.getString("projectName");

			if (userID_ == userID && url_.equals(url)) {

				out = projName;
			}
		}
		return out;
	}
	@Override
	public void init() throws Exception {
		System.out.println("init");
		connection.startConnection();
	}

	@Override
	public void stop() throws Exception {
		connection.closeConnection();
	}

	@Override
	public void start(Stage primaryStage) {
		try {
			init();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		primaryStage.setTitle("New Project Page");
		Font font = Font.loadFont(getClass().getResourceAsStream("desdemon.ttf"), 20);
		StackPane root = new StackPane();
		Scene scene = new Scene(root, 1080, 600);
		p = new photo(root, scene, url);
		selectionBox sb = new selectionBox(root, scene, p, this);

		// nav bar rectangle
		Rectangle navbar = new Rectangle();
		navbar.setId("navbar");
		navbar.setWidth(1020);
		navbar.setHeight(40);
		navbar.setTranslateY(-260);

		Label welcome = new Label("|||  ");
		welcome.setId("welcome");
		welcome.setTranslateY(-260);
		welcome.setTranslateX(-485);
		welcome.setTextFill(Color.WHITE);

		// linked list filter
		Hyperlink scramble = new Hyperlink(" Scramble ");
		scramble.setId("welcome");
		scramble.setTranslateY(-260);
		scramble.setTranslateX(-397);
		scramble.setBorder(Border.EMPTY);
		scramble.setTextFill(Color.WHITE);
		scramble.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// connect to filter class
				sb.setType(3);
			}
		});
		scramble.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				if (accCheck) {
					Media sound = new Media(new File("scramble.mp3").toURI().toString());
					MediaPlayer mp = new MediaPlayer(sound);
					mp.play();
				}
			}
		});

		// stack filter
		Hyperlink vertical = new Hyperlink(" Vertical ");
		vertical.setId("welcome");
		vertical.setTranslateY(-260);
		vertical.setTranslateX(-270);
		vertical.setBorder(Border.EMPTY);
		vertical.setTextFill(Color.WHITE);
		vertical.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// connect to filter class
				sb.setType(4);

			}
		});
		vertical.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				if (accCheck) {
					Media sound = new Media(new File("vertical.mp3").toURI().toString());
					MediaPlayer mp = new MediaPlayer(sound);
					mp.play();
				}
			}
		});

		// horizontal stack filter
		Hyperlink horizontal = new Hyperlink(" Horizontal ");
		horizontal.setId("welcome");
		horizontal.setTranslateY(-260);
		horizontal.setTranslateX(-135);
		horizontal.setBorder(Border.EMPTY);
		horizontal.setTextFill(Color.WHITE);
		horizontal.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// connect to filter class
				sb.setType(5);
			}
		});
		horizontal.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				if (accCheck) {
					Media sound = new Media(new File("horizontal.mp3").toURI().toString());
					MediaPlayer mp = new MediaPlayer(sound);
					mp.play();
				}
			}
		});

		// smooth filter
		Hyperlink smooth = new Hyperlink(" Smooth ");
		smooth.setId("welcome");
		smooth.setTranslateY(-260);
		smooth.setTranslateX(-3);
		smooth.setBorder(Border.EMPTY);
		smooth.setTextFill(Color.WHITE);
		smooth.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// connect to filter class
				sb.setType(2);
			}
		});
		smooth.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				if (accCheck) {
					Media sound = new Media(new File("smooth.mp3").toURI().toString());
					MediaPlayer mp = new MediaPlayer(sound);
					mp.play();
				}
			}
		});

		// rough filter
		Hyperlink rough = new Hyperlink(" rough ");
		rough.setId("welcome");
		rough.setTranslateY(-260);
		rough.setTranslateX(103);
		rough.setBorder(Border.EMPTY);
		rough.setTextFill(Color.WHITE);
		rough.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// connect to filter class
				sb.setType(1);
			}
		});
		rough.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				if (accCheck) {
					Media sound = new Media(new File("rough.mp3").toURI().toString());
					MediaPlayer mp = new MediaPlayer(sound);
					mp.play();
				}
			}
		});

		// random filter
		Hyperlink random = new Hyperlink(" random ");
		random.setId("welcome");
		random.setTranslateY(-260);
		random.setTranslateX(210);
		random.setBorder(Border.EMPTY);
		random.setTextFill(Color.WHITE);
		random.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// connect to filter class
				sb.setType(6);
			}
		});
		random.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				if (accCheck) {
					Media sound = new Media(new File("random.mp3").toURI().toString());
					MediaPlayer mp = new MediaPlayer(sound);
					mp.play();
				}
			}
		});

		// login button
		Hyperlink login = new Hyperlink(" login ");
		login.setId("welcome");
		login.setTranslateY(-260);
		login.setTranslateX(310);
		login.setBorder(Border.EMPTY);
		login.setTextFill(Color.WHITE);
		login.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				SignUpLogIn s = new SignUpLogIn();
				s.start(primaryStage);
			}
		});
		login.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				if (accCheck) {
					Media sound = new Media(new File("login.mp3").toURI().toString());
					MediaPlayer mp = new MediaPlayer(sound);
					mp.play();
				}
			}
		});

		Hyperlink save = new Hyperlink(" save ");
		save.setId("welcome");
		save.setTranslateY(-260);
		save.setTranslateX(310);
		save.setBorder(Border.EMPTY);
		save.setTextFill(Color.WHITE);
		save.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// should pop up whether the user wants to save or not
				// only save when logged in
				try {

					FileChooser fileChooser = new FileChooser();
					fileChooser.setTitle("Save Image");
					File file = fileChooser.showSaveDialog(primaryStage);
					if (file != null) {
						try {
							ImageIO.write(SwingFXUtils.fromFXImage(p.wi, null), "png", file);
						} catch (IOException ex) {
							System.out.println(ex.getMessage());
						}
					}

					System.out.println("new save path: " + file.getAbsolutePath().toString());

					int user = SignUpLogIn.getUserID();
					ArrayList<String> projects = UserPage.getProjects(user);
					boolean createNew = true;
					url = url.replace("\\\\", "\\");

					String projectName = getProjectName(user, url);

					System.out.println("url: " + url);
					System.out.println("projectname: " + projectName);

					for (int i = 0; i < projects.size(); i++) {
						System.out.println("comparing to: " + projects.get(i));
						if (projects.get(i).equals(url)) {
							// a url already exists for this project
							createNew = false;
						}
					}

					String driverClassName = "com.mysql.jdbc.Driver";
					String dbURL = "jdbc:mysql://localhost/GlitchUsers?user=root&password=root&useSSL=false";

					Connection conn = null; // create the connection to database
					Statement st = null; // executes any sql command
					PreparedStatement ps = null;
					ResultSet rs = null; // retrieve data that comes back (from select statement), a table

					Class.forName(driverClassName);
					conn = DriverManager.getConnection(dbURL);
					st = conn.createStatement();
					ps = conn.prepareStatement(" SELECT s.imageID, s.userID, s.imageName");
					rs = st.executeQuery("SELECT * FROM Images;");

					int imgID = 0;

					if (!createNew) {

						while (rs.next()) { // as long as there are more rows
							int imageID = rs.getInt("imageID");
							int userID = rs.getInt("userID");
							String projName = rs.getString("projectName");

							if (user == userID && projName.equals(projectName)) {
								imgID = imageID;
							}

						}

						System.out.println("updating image: " + imgID);

						String editted = file.getAbsolutePath().toString();

						editted = editted.replace("\\", "\\\\");
						ps = conn.prepareStatement(
								"update images set imageName= '" + editted + "' where imageID = " + imgID);

						ps.executeUpdate();

					}

					else if (createNew) {

						System.out.println("creating new");

						ps = conn.prepareStatement("INSERT INTO Images (userID,imageName,projectName) VALUES('" + user
								+ "','" + url + "','" + projectName + "')");

						ps.executeUpdate();
					}

				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		});

		// logo button
		Hyperlink glitch = new Hyperlink("glitch");
		glitch.setId("logo");
		glitch.setTranslateY(-260);
		glitch.setTranslateX(450);
		glitch.setBorder(Border.EMPTY);
		glitch.setTextFill(Color.WHITE);
		glitch.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				Main s = new Main();
				s.start(primaryStage);
			}
		});
		glitch.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				if (accCheck) {
					Media sound = new Media(new File("Glitch.mp3").toURI().toString());
					MediaPlayer mp = new MediaPlayer(sound);
					mp.play();
				}
			}
		});

		// accessibility button
		Hyperlink acc = new Hyperlink("Accessibility");
		acc.setId("share");
		acc.setTranslateY(280);
		acc.setTranslateX(-480);
		acc.setBorder(Border.EMPTY);
		acc.setTextFill(Color.BLACK);
		acc.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				accCheck = !accCheck;
			}
		});

		// share button
		HostServicesDelegate hostServices = HostServicesFactory.getInstance(this);
		Hyperlink share = new Hyperlink("share");
		share.setId("share");
		share.setTranslateY(280);
		share.setTranslateX(500);
		share.setBorder(Border.EMPTY);
		share.setTextFill(Color.BLACK);
		share.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				hostServices
						.showDocument("https://www.facebook.com/dialog/share?" + "app_id=589605958083859&display=popup&"
								+ "href=https://github.com/GregoryFarn/GlitchBrush" + "&hashtag=#GlitchArt");
			}
		});

		// work frame
		Rectangle workFrame = new Rectangle();
		workFrame.setId("navbar");
		workFrame.setWidth(900);
		workFrame.setHeight(500);
		workFrame.setTranslateY(25);

		// project image
		/*
		 * ImageView selectedImage = new ImageView(); Image image1 = null; try { image1
		 * = new Image(new FileInputStream("test.jpg")); } catch (FileNotFoundException
		 * e) { // TODO Auto-generated catch block e.printStackTrace(); }
		 * selectedImage.setImage(image1);
		 */
		// selectedImage.setFitWidth(850);
		// selectedImage.setFitHeight(450);
		// selectedImage.setTranslateY(25);

		root.setId("pane");
		root.getChildren().add(navbar);
		root.getChildren().add(welcome);
		root.getChildren().add(scramble);
		root.getChildren().add(vertical);
		root.getChildren().add(horizontal);
		root.getChildren().add(smooth);
		root.getChildren().add(rough);
		root.getChildren().add(random);

		if (!SignUpLogIn.loginCheck()) {
			root.getChildren().add(login);
		} else {
			root.getChildren().add(save);
		}
		root.getChildren().add(glitch);
		root.getChildren().add(workFrame);
		root.getChildren().add(share);
		root.getChildren().add(acc);
		p.toFront();
		sb.toFront();
		sb.setType(6);
		// root.getChildren().add(selectedImage);
		primaryStage.setScene(scene);
		java.net.URL url = this.getClass().getResource("WorkBenchPage.css");
		if (url == null) {
			System.out.println("Resource not found. Aborting.");
			System.exit(-1);
		}
		String css = url.toExternalForm();
		scene.getStylesheets().add(css);
		primaryStage.show();
	}

	private Server createServer() {
		System.out.println("Server");
		return new Server(55555, data -> {
			Platform.runLater(() -> {// runLater is a javafx thing
				
				// handling the data goes here!
				System.out.println(data);
				
			});
		});
	}

	public void activate() {
		try {
			connection.send(p);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private Client createClient() {
		// right now it's connected to localhost
		System.out.println("Client");
		return new Client("localhost", 55555, data -> {
			Platform.runLater(() -> {

				System.out.println(data);
					//p.resetPhoto(((photo)(new ObjectInputStream((InputStream)data)).readObject()).getPR());
				

			});
		});
	}

	public static void main(String[] args) {
		launch(args);
	}


}
