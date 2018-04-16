package application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;

public class photo {
	Group gr;
	Scene sc;
	Canvas cs;
	Image image;
	ImageView iv;
	double x;
	double y;
	PixelReader pr;
	WritableImage wi;
	PixelWriter pw;

	// WILL FIT TO SIZE OF THE SCENE PROVIDED (fileAdd is ADDRESS OF PICTURE)
	public photo(Group gr, Scene scene, String fileAdd) {
		this.gr = gr;
		this.sc = scene;
		try {
			image = new Image(new FileInputStream(fileAdd));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		// CODE TO CENTER AND FIT IMAGE TO SCENE SIZE
		this.iv = new ImageView(image);
		gr.getChildren().add(iv);
		iv.setPreserveRatio(true);
		iv.setFitHeight(scene.getHeight());
		if (iv.getBoundsInLocal().getWidth() > scene.getWidth()) {
			iv.setFitWidth(scene.getWidth());
			y = (scene.getHeight() / 2) - (iv.getBoundsInLocal().getHeight() / 2);
			iv.setY(y);
			x = 0;
		} else {
			x = (scene.getWidth() / 2) - (iv.getBoundsInLocal().getWidth() / 2);
			iv.setX(x);
			y = 0;
		}
		
		//CREATE NEW IMAGE THAT FITS SIZE OF SCENE
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
		iv.setX(x);
		iv.setY(y);
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

	//RESET READER TO GLITCH THE NEW PHOTO AFTER EDITS, OTHERWISE IT GLITCHES THE ORIGINAL PHOTO
	public void resetReader() {
		pr = wi.getPixelReader();
	}
}
