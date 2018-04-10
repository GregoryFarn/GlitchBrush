package application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class pixelSort {
	Image image; 
	Scene scene;
	ImageView iv;
	Group gr;
	public pixelSort(Group gr, Scene scene,String photoAdd){
		this.scene= scene;
		this.gr = gr;
		try {
			image = new Image(new FileInputStream(photoAdd));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		this.iv = new ImageView(image);
		gr.getChildren().add(iv);
		
		//Setting image to center and bounds
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
	    
	}

}
