package server;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable {

	private static int port = 8887;
	ServerSocket socketServer;
	Socket socket;
	ObjectOutputStream oos;
    ObjectInputStream ois;
	
	public Server() throws IOException {
		
		socketServer = new ServerSocket(port);	
		System.out.println("Waiting for the client...");
		socket = socketServer.accept();
		System.out.println("Client connected!");
	}

	@Override
	public void run() {
		try {
			
			oos = new ObjectOutputStream(socket.getOutputStream());
	         ois = new ObjectInputStream(socket.getInputStream());
			while(true) {
				System.out.println("Waiting for a request...");
				Object messageIn =  ois.readObject();
				System.out.println("Elaborating request...");
				
				String message = func((String)messageIn);
				if (message.equals("Disconnect")) {
					System.out.println("Closed correctly");
					return;
				}
				else throw new IOException();
			}
		} catch (IOException e) {
			
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
		
	}

	private String func(String messageIn) throws IOException, ClassNotFoundException {
		/**
		 * Disconnect to the client
		 */
		if(messageIn.equals("Disconnect") ) {
			Object message = new String("Ok");
			oos.writeObject(message);
			String ok =  (String) ois.readObject();
			if(ok.equals("Ok")) {
				ois.close();
				oos.close();
				socket.close();
				return new String("Disconnect");
			}else 
				return "";
		}
		return "";
		
	}
}
