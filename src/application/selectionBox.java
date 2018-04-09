package application;

import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/*selectionBox is only a visual lined box that defines the outline of the selected area,
 * does not have any filtering capabilities.
 */
public class selectionBox {
	private double startX;
	private double startY;

	private GraphicsContext gc;
	private Canvas cs;
	private Scene scene;
	private Rectangle rect;
	private Group root;

	public selectionBox(Group root, Scene scene) {
		this.root = root;
		this.scene = scene;
		cs = new Canvas(scene.getWidth(), scene.getHeight());
		cs.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				startX = e.getX();
				startY = e.getY();
				rect = new Rectangle(e.getX(), e.getY(), 0, 0);
				rect.setFill(Color.TRANSPARENT);
				rect.setStroke(Color.GREY);
				rect.setStrokeWidth(1);	
				rect.getStrokeDashArray().addAll(2d);
				root.getChildren().add(rect);

			}
		});
		cs.addEventHandler(MouseEvent.MOUSE_DRAGGED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				updateSize(e.getX(), e.getY());
				// drawBox();
			}
		});
	}

	public void updateSize(double x, double y) {
		if (startX > x) {
			if (startY > y) {
				rect.setX(x);
				rect.setY(y);
				rect.setWidth(startX - x);
				rect.setHeight(startY - y);
			} else {
				rect.setX(x);
				rect.setWidth(startX - x);
				rect.setHeight(y-startY);
			}
		} else {
			if (startY > y) {
				rect.setY(y);
				rect.setWidth(x - startX);
				rect.setHeight(startY - y);
			} else {
				rect.setX(startX);
				rect.setY(startY);
				rect.setHeight(y - startY);
				rect.setWidth(x - startX);
			}
		}
	}

	/*public void endActive() {
		gc.clearRect(startX, startY, endX, endY);
		gc.clearRect(endX, endY, startX, startY);
		gc.clearRect(startX, endY, endX, startY);
		gc.clearRect(endX, startY, startX, endY);
		startX = 0;
		startY = 0;
		endX = 0;
		endY = 0;
	}

	public void drawBox() {
		gc.strokeLine(startX, startY, startX, endY);
		gc.strokeLine(startX, startY, endX, startY);
		gc.strokeLine(endX, endY, startX, endY);
		gc.strokeLine(endX, endY, endX, startY);
	}*/

	public double getStartX() {
		return startX;
	}

	public double getStartY() {
		return startY;
	}


	public Canvas getCanvas() {
		return cs;
	}
}
