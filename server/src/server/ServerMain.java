package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMain {
	
	
	public static void main(String[] args) throws IOException {
		
		
		Thread logic = new Thread(new Server());
	      
        logic.start();
		
	}

}
