package application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public class pixelSort {
	Image image; 
	Scene scene;
	ImageView iv;
	Group gr;
	WritableImage wi;
	public pixelSort(Group gr, Scene scene,String photoAdd){
		this.scene= scene;
		this.gr = gr;
		
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
	    gr.getChildren().remove(iv);
	    try {
			image = new Image(new FileInputStream(photoAdd),iv.getBoundsInLocal().getWidth(),iv.getBoundsInLocal().getHeight(),false,false);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	    PixelReader pixlr = image.getPixelReader();
		WritableImage wImage = new WritableImage(pixlr,(int)image.getWidth(), (int)image.getHeight());
		wi= wImage;
		PixelWriter writer = wImage.getPixelWriter();
		this.iv = new ImageView(wImage);
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
	
	public void pSort(selectionBox sb) {
    	for(int i =0;i<(int)sb.getEndX()-sb.getStartX();i++) {
    		Vector<Color> vc = new Vector<Color>();
    		for(int j =(int)sb.getStartY();j<(int)sb.getEndY();j++) {
    			vc.add(image.getPixelReader().getColor(i, j));
    		}
    		Collections.sort(vc,new colorSort());
    		for(int j =(int)sb.getStartY();j<(int)sb.getEndY();j++) {
    			wi.getPixelWriter().setColor(i,j, vc.get(j));
    		}
    	}
    }


}
