package application;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

public class receiveUpdate extends Thread {
	photo p;

	public receiveUpdate(photo p) {
		this.p = p;
		this.start();
	}

	public void run() {
		
			try {
				ServerSocket serverSocket = new ServerSocket(1234);
while(true) {
				Socket socket = serverSocket.accept();
				InputStream inputStream = socket.getInputStream();

				byte[] sizeAr = new byte[4];
				inputStream.read(sizeAr);
				int size = ByteBuffer.wrap(sizeAr).asIntBuffer().get();

				byte[] imageAr = new byte[size];
				inputStream.read(imageAr);

				BufferedImage image = ImageIO.read(new ByteArrayInputStream(imageAr));

				Image i = SwingFXUtils.toFXImage(image, null);
				p.resetPhoto(i.getPixelReader());
				serverSocket.close();
}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	}
}