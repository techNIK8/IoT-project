package client;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;

public class Client implements Runnable {
	static int port = 8887;
	Socket socket;
	InetAddress host;
	ObjectOutputStream oos;
    ObjectInputStream ois;
    boolean disconnect = false;
	 
	//final String host ="localhost";
	
	public Client() throws IOException {
		host = InetAddress.getLocalHost();
		socket = new Socket(host.getHostName(),port);
		
	}

	@Override
	public void run() {
		System.out.println("Connected to server");
		try {
			oos = new ObjectOutputStream(socket.getOutputStream());
	         ois = new ObjectInputStream(socket.getInputStream());
			while(true) {
				//Object messageIn = ois.readObject();
				
				if(disconnect)
				disconnect = disconnect();
				
				if (disconnect) {
					System.out.println("Closed correctly");
					return;
				}
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		
	}

	private boolean disconnect() throws ClassNotFoundException, IOException {
		System.out.println("Disconnecting...");
		Object message = new String("Disconnect");
		oos.writeObject(message);
		String ok =  (String) ois.readObject();
		if(ok.equals("Ok")) {
			oos.writeObject("Ok");
			ois.close();
			oos.close();
			socket.close();
			
			return true;
		}else 
			return false;
		
	}

	
}
