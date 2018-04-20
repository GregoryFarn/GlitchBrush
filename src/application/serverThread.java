package application;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

public class serverThread extends Thread{
	photo p;
	ServerSocket serverSocket;
	public serverThread(photo p) {
		this.p=p;
		this.run();
		try {
			serverSocket = new ServerSocket(1234);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void run() {
		try {
			while(true) {
				System.out.println("waiting");
				Socket socket = serverSocket.accept();
		        InputStream inputStream = socket.getInputStream();
		        byte[] sizeAr = new byte[4];
		        inputStream.read(sizeAr);
		        int size = ByteBuffer.wrap(sizeAr).asIntBuffer().get();

		        byte[] imageAr = new byte[size];
		        inputStream.read(imageAr);

		        BufferedImage image = ImageIO.read(new ByteArrayInputStream(imageAr));
		        
		        p.resetPhoto(SwingFXUtils.toFXImage(image, null).getPixelReader());
		        serverSocket.close();
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	public void update() {
		try {
			OutputStream outputStream = serverSocket.getOutputStream();

	        BufferedImage image = SwingFXUtils.fromFXImage((Image)p.getWI(), null);

	        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
	        ImageIO.write(image, p.getType(), byteArrayOutputStream);

	        byte[] size = ByteBuffer.allocate(4).putInt(byteArrayOutputStream.size()).array();
	        outputStream.write(size);
	        outputStream.write(byteArrayOutputStream.toByteArray());
	        outputStream.flush();
	        socket.close();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
