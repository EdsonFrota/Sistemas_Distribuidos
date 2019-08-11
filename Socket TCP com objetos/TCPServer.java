package tcp;

import java.net.*;
import java.io.*;

public class TCPServer {
	protected static Integer reqRespondidas = 0;
	
	public static void main(String args[]) {
		try {
			int serverPort = new Integer(args[0]);
			ServerSocket listenSocket = new ServerSocket(serverPort);
			while (true) {
				Socket clientSocket = listenSocket.accept();
				Connection c = new Connection(clientSocket);
			}
		} catch (IOException e) {
			System.out.println("Listen :" + e.getMessage());
		}
	}
}