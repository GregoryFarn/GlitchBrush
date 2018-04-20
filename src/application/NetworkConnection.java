package application;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.function.Consumer;

public abstract class NetworkConnection {

    private ConnectionThread connThread = new ConnectionThread();
    private Consumer<Serializable> onReceiveCallback;

    public NetworkConnection(Consumer<Serializable> onReceiveCallback) {
    	
    	
        this.onReceiveCallback = onReceiveCallback;
        connThread.setDaemon(true);
    }

    public void startConnection() throws Exception {
        connThread.start();
    }

    public void send(Serializable data) throws Exception {
        connThread.out.writeObject(data);
    }

    public void closeConnection() throws Exception {
        connThread.socket.close();
    }
    public Socket getSocket() {
    	return connThread.socket;
    }

    protected abstract boolean isServer();
    
    //for client, find the server and connect to it
    protected abstract String getIP();
    
    //for server, port on which to listen to connections
    //client, port on which to connect to server
    protected abstract int getPort();

    private class ConnectionThread extends Thread {
        private Socket socket;
        private ObjectOutputStream out;

        @Override
        public void run() {
        	System.out.println("Connected");
            try (ServerSocket server = isServer() ? new ServerSocket(getPort()) : null;
                    Socket socket = isServer() ? server.accept() : new Socket(getIP(), getPort());
                    ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                    ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {

                this.socket = socket;
                this.out = out;
                System.out.println("Connected");
                socket.setTcpNoDelay(true);

                
                //acceptng messages and handling them
                while (true) {
                    Serializable data = (Serializable) in.readObject();
                    
                    //do whatever you want with data here
                    onReceiveCallback.accept(data);
                }
            }
            catch (Exception e) {
                onReceiveCallback.accept("Connection closed");
            }
        }
       
    }
}