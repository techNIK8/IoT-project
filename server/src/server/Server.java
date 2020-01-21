package server;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable {

	
	Socket socket;
	ObjectOutputStream oos;
    ObjectInputStream ois;
	
	public Server(Socket socket) throws IOException {
		this.socket = socket;
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
					socket.close();
					return;
				}
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
				return new String("Disconnect");
			}else 
				return "";
		}
		
		if(messageIn.equals("ask_info")) {
			
		}
		return "";
		
	}
}
