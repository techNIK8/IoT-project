package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMain {
	private static int port = 8887;
	static ServerSocket socketServer;
	
	public static void main(String[] args) throws IOException {
		socketServer = new ServerSocket(port);	
		while(true) {
			
			System.out.println("Waiting for the client...");
			Socket socket = socketServer.accept();
			System.out.println("Client connected!");
			
		Thread logic = new Thread(new Server(socket));
	      
        logic.start();
		}
		
	}

}
