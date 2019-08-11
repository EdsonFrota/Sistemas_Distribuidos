package tcp;

import java.net.*;
import java.io.*;

public class TCPClient {
	public static void main(String args[]) {
		Socket s = null;
		args = new String[2];
		args[0] = "SD é muito divertido!";
		args[1] = "127.0.0.1";
		try {
			int serverPort = 7896;
			s = new Socket(args[1], serverPort);
			DataInputStream in = new DataInputStream(s.getInputStream());
			DataOutputStream out = new DataOutputStream(s.getOutputStream());
			out.writeUTF(args[0]); 
			String data = in.readUTF();
			System.out.println(data);
		} catch (UnknownHostException e) {
			System.out.println("Sock:" + e.getMessage());
		} catch (EOFException e) {
			System.out.println("EOF:" + e.getMessage());
		} catch (IOException e) {
			System.out.println("IO:" + e.getMessage());
		} finally {
			if (s != null)
				try {
					s.close();
				} catch (IOException e) {
					System.out.println("close:" + e.getMessage());
				}
		}
	}
}