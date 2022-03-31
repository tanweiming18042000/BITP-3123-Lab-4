package clientTranslate;

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
 * and printout a frame which allow the client
 * to enter English text and translate it
 * to different language based on their choices
 * 
 * by Tan Wei Ming
 *
 */


public class ClientTranslateApplication {

	public static void main(String[] args) throws UnknownHostException, IOException {
		 
		// Launch client-side frame
		ClientTranslateFrame clientTranslateFrame = 
				new ClientTranslateFrame();
		clientTranslateFrame.setVisible(true);
		
		// Connect to the server @localhost, port 5040
		Socket socket = new Socket(InetAddress.getLocalHost(), 5040);
		
		// Update the status of the connection
		clientTranslateFrame.updateConnectionStatus(socket.isConnected());
		
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
			System.out.println("Enter the English Text: ");
			String command = keyboard.readLine();
			
			System.out.println("Enter the language to be translated to: ");
			String command2 = keyboard.readLine();
			
			// if enter "exit", the system will be exited
			if(command.equals("exit") || command2.equals("exit")) 
				break;
			
			// send command to the server
			out.println(command);
			out.println(command2);
			
			// get the response from server and print what server
			// said
			String serverResponse = bufferedReader.readLine();
			clientTranslateFrame.updateServerDate(serverResponse);
		}
		
		
		// Close everything
		socket.close();
		System.exit(0);
		
	}
}


