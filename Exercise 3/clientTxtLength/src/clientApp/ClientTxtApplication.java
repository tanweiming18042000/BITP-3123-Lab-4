package clientApp;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * This method launch request the connection from server
 * and get the length of text from the server
 * 
 * by Tan Wei Ming
 *
 */


public class ClientTxtApplication {

	public static void main(String[] args) throws UnknownHostException, IOException {
		 
		// Connect to the server @localhost, port 4227
		Socket socket = new Socket(InetAddress.getLocalHost(), 4227);
		
		// read from the network
		BufferedReader bufferedReader = new BufferedReader
				(new InputStreamReader(socket.getInputStream()));
		
		// client enter String input
		BufferedReader keyboard = new BufferedReader
				(new InputStreamReader(System.in));
		
		// Create stream to write data on the network
		PrintWriter out = new PrintWriter
				(socket.getOutputStream(), true);
		
		while(true) {
			// read the client input
			System.out.println("> ");
			String command = keyboard.readLine();
			
			// if enter "exit", the system will be exited
			if(command.equals("exit")) 
				break;
			
			// send command to the server
			out.println(command);
			
			// get the response from server and print what server
			// said
			String serverResponse = bufferedReader.readLine();
			System.out.println("Server says: " + serverResponse);
		}
		
		
		// Close everything
		socket.close();
		System.exit(0);
		
	}
}
