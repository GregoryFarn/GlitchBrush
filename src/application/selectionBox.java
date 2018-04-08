package application;

import javafx.scene.canvas.GraphicsContext;

public class selectionBox {
	private double startX;
	private double startY;
	private double endX;
	private double endY;
	private GraphicsContext gc;
	public selectionBox(double x, double y, GraphicsContext gc) {
		startX = x;
		startY = y;
		this.gc = gc;
	}
	public void updateSize(double x, double y) {
		endX = x;
		endY = y;
	}
	public void drawBox() {
		gc.clearRect(0,gc.getCanvas().getHeight(),0,gc.getCanvas().getHeight());
		gc.strokeLine(startX, startY, startX, endY);
		gc.strokeLine(startX, startY, startX, endY);
		gc.strokeLine(endX, endY, startX, endY);
		gc.strokeLine(endX, endY, startX, endY);
	}
}
