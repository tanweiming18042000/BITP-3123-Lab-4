package serverTxtLength;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * This class launch the server side application using TCP.
 * The server generates the length of the text.
 * Each connected client will received the length of the text from the server.
 * 
 * by Tan Wei Ming
 *
 */


public class ServerTxtApplication {
	
	public static void main(String[] args) throws IOException {
		
		// Binding to a port 
		int portNo = 4227;
		ServerSocket serverSocket = new ServerSocket(portNo);
		
		TxtCounter txtCounter = new TxtCounter();
		
		// Server needs to be alive forever
		while(true) {
			
			// accept client request for connection
			Socket clientSocket = serverSocket.accept();
			
			// connect to client OutputStream
			PrintWriter out = new PrintWriter
					(clientSocket.getOutputStream(), true);
			
			// Read text from the network
			BufferedReader bufferedReader = new BufferedReader(
					new InputStreamReader
					(clientSocket.getInputStream()));
			
			try {
				while(true) {
					// Read the request from the client
					String request = bufferedReader.readLine();
			
					// count the text length
					int textLength = txtCounter.getTextLength(request);

					out.println("The length of the text: " + textLength);
				}
			} finally {
			// Close everything
			out.close();
			bufferedReader.close();
			clientSocket.close();
			}
			
			
			
		}
	}
}
