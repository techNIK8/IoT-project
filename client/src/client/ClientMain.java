package client;

import java.io.IOException;

public class ClientMain {

	
	public static void main(String[] args) throws IOException {
		
		Thread client = new Thread(new Client());
		
		client.start();
	}

}
