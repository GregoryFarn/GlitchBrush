package application;
//THIS IS SERVER THRED
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;

import javax.imageio.ImageIO;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;

public class updateThread extends Thread {
	private photo p;
	Socket socket;
	public updateThread(photo p) {
		this.p = p;
		this.start();
		try {
			socket = new Socket("localhost", 1234);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void run() {
		try {
					OutputStream outputStream = socket.getOutputStream();

					BufferedImage image = p.returnBI();

					ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
					ImageIO.write(image, p.getType(), byteArrayOutputStream);

					byte[] size = ByteBuffer.allocate(4).putInt(byteArrayOutputStream.size()).array();
					outputStream.write(size);
					outputStream.write(byteArrayOutputStream.toByteArray());
					outputStream.flush();
					socket.close();
		}catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
	}
