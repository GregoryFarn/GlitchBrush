package application;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;

public class selectionBox {
	private double startX;
	private double startY;
	private double endX;
	private double endY;
	private GraphicsContext gc;
	private Canvas cs;
	private Scene scene;
	public selectionBox(Scene scene) {
		cs  = new Canvas(scene.getWidth(),scene.getHeight());
		gc = cs.getGraphicsContext2D();
		cs.addEventHandler(MouseEvent.MOUSE_PRESSED, 
	     	       new EventHandler<MouseEvent>() {
	     	           @Override
	     	           public void handle(MouseEvent e) {
	     	        	  gc.clearRect(startX, startY, endX, endY);
	     	     		gc.clearRect(endX, endY, startX,startY);
	     	     		gc.clearRect(startX, endY, endX, startY);
	     	     		gc.clearRect(endX, startY, startX, endY);
	     	     		startY = e.getY() ;
	     	     		startX = e.getX();
	     	     		
	     	           }
	     	       });
	        cs.addEventHandler(MouseEvent.MOUSE_DRAGGED, 
	        	       new EventHandler<MouseEvent>() {
	        	           @Override
	        	           public void handle(MouseEvent e) {
	        	        	   updateSize(e.getX(), e.getY());
	        	        	   drawBox();
	        	           }
	        	       });
	}
	public void updateSize(double x, double y) {
		gc.clearRect(startX, startY, endX, endY);
		gc.clearRect(endX, endY, startX,startY);
		gc.clearRect(startX, endY, endX, startY);
		gc.clearRect(endX, startY, startX, endY);
		endX = x;
		endY = y;
	}
	public void drawBox() {
		gc.strokeLine(startX, startY, startX, endY);
		gc.strokeLine(startX, startY, endX, startY);
		gc.strokeLine(endX, endY, startX, endY);
		gc.strokeLine(endX, endY, endX, startY);
	}
	public double getStartX() {
		return startX;
	}
	public double getStartY() {
		return startY;
	}
	public double getEndX() {
		return endX;
	}
	public double getEndY() {
		return endY;
	}
	public Canvas getCanvas() {
		return cs;
	}
}
