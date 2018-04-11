package application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class photo {
	Group gr;
	Scene sc;
	Canvas cs;
	Image image;
	ImageView iv;
	
	public photo(Group gr, Scene scene,String fileAdd) {
		this.gr= gr;
		this.sc=scene;
		try {
			image = new Image(new FileInputStream(fileAdd));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		this.iv = new ImageView(image);
		gr.getChildren().add(iv);
		 iv.setPreserveRatio(true);
		    iv.setFitHeight(scene.getHeight());
		    if(iv.getBoundsInLocal().getWidth()>scene.getWidth())
		    {
		    	iv.setFitWidth(scene.getWidth());
		    	double y = (scene.getHeight()/2)-(iv.getBoundsInLocal().getHeight()/2);
		    	iv.setY(y);
		    }
		    else {
		    double x = (scene.getWidth()/2)-(iv.getBoundsInLocal().getWidth()/2);
		    iv.setX(x);
		    }
		  //  gr.getChildren().remove(iv);
		
	}
}
