package application;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.Serializable;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.StackPane;

public class photo implements Serializable {
	Canvas cs;
	Image image;
	ImageView iv;
	double x;
	double y;
	PixelReader pr;
	WritableImage wi;
	WritableImage temp;
	PixelWriter pw;
	int width;
	int height;
	int dispX;
	int dispY;
	boolean edited;
	String type;
	// WILL FIT TO SIZE OF THE SCENE PROVIDED (fileAdd is ADDRESS OF PICTURE)
	public photo(StackPane gr, Scene scene, String fileAdd) {
		edited = false;
		try {
			image = new Image(new FileInputStream(fileAdd));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		type = fileAdd.substring(fileAdd.indexOf('.'));
		
		width = 900;
		height = 500;
		dispX = 0;
		dispY = 25;
		
		
		// CODE TO CENTER AND FIT IMAGE TO SCENE SIZE
		this.iv = new ImageView(image);
		gr.getChildren().add(iv);
		iv.setPreserveRatio(true);
		iv.setFitHeight(height);
		if (iv.getBoundsInLocal().getWidth() > width) {
			iv.setFitWidth(width);
			y = (scene.getHeight() / 2) - ((iv.getBoundsInLocal().getHeight()/2) -dispY);
			x = (scene.getWidth() / 2) - (iv.getBoundsInLocal().getWidth() / 2)+dispX;
		} else {
			x = (scene.getWidth() / 2) - (iv.getBoundsInLocal().getWidth() / 2)+dispX;
			y =(scene.getHeight() / 2) - ((height/2) -dispY);
		}

		// CREATE NEW IMAGE THAT FITS SIZE OF SCENE
		try {
			image = new Image(new FileInputStream(fileAdd), iv.getBoundsInLocal().getWidth(),
					iv.getBoundsInLocal().getHeight(), false, false);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		gr.getChildren().remove(iv);
		pr = image.getPixelReader();
		wi = new WritableImage(pr, (int) image.getWidth(), (int) image.getHeight());
		pw = wi.getPixelWriter();
		this.iv = new ImageView(wi);
		gr.getChildren().add(iv);
		iv.setManaged(false);
		iv.setLayoutX(x);
		iv.setLayoutY(y);
	}

	public PixelWriter getPW() {
		return pw;
	}

	public PixelReader getPR() {
		return pr;
	}

	public WritableImage getWI() {
		return wi;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getHeight() {
		return image.getHeight();
	}

	public double getWidth() {
		return image.getWidth();
	}
	public void toFront() {
		iv.toFront();
	}
	public void resetPhoto() {
		wi =new WritableImage(pr,(int)image.getWidth(),(int)image.getHeight());
		iv.setImage(wi);
	}
	public void resetPhoto(PixelReader PR) {
		wi = new WritableImage(PR,(int)image.getWidth(),(int)image.getHeight());
		iv.setImage(wi);
	}
	// RESET READER TO GLITCH THE NEW PHOTO AFTER EDITS, OTHERWISE IT GLITCHES THE
		// ORIGINAL PHOTO
	public void resetReader() {
		pr = wi.getPixelReader();
	}
	public boolean getEdit() {
		return edited;
	}
	public void setEdit(boolean bool) {
		edited = bool;
	}
	public String getType() {
		return type;
	}
	public BufferedImage returnBI() {
		return  SwingFXUtils.fromFXImage((Image)wi, null);
	}
}
