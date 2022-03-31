package serverTranslate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;

import controller.TranslateController;

/**
 * This class launch the server side application using TCP.
 * The server translate the English text entered by client.
 * The translate language can be chose by client
 * Each connected client will received the translated 
 * text from the server.
 * 
 * @author WM TAN
 *
 */

public class ServerTranslateApplication {
	
	public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {
		
		// Launch the server frame
		ServerTranslateFrame serverFrame = new ServerTranslateFrame();
		serverFrame.setVisible(true);
		
		// Binding to a port
		int portNo = 5040;
		ServerSocket serverSocket = new ServerSocket(portNo);
		
		TranslateController translate = new TranslateController(); 
		
		// declare array to store the client input
		String[] input = {"M", "W"};
		
		// Server needs to be alive forever
		while(true) {
			
			// Message to indicate server is alive
			serverFrame.updateServerStatus(false);
			
			// Accept client request for connection
			Socket clientSocket = serverSocket.accept();
			
			// connect to client OutputStream
			PrintWriter out = new PrintWriter
					(clientSocket.getOutputStream(), true);
			
			// Read text from the network
			BufferedReader bufferedReader = new BufferedReader
					(new InputStreamReader(clientSocket.getInputStream()));
			
			
			try {
				String line, englishText, languageTranslate;
				while(true) {
					// Read the requests from the client
					int counter = 0;
					while(counter < 2)
					{
						String curText = bufferedReader.readLine();
						input[counter] = curText;
						counter++;
					}
					// retrieve the translate language from database
					String ans = translate.findTranslate(input[0], input[1]);
					
					out.println("The translated language: " + ans);
					
					serverFrame.updateRequestStatus("Data sent to client: " + ans);
				}
			} finally {
				// close everything
				out.close();
				bufferedReader.close();
				clientSocket.close();
			}
			
			
		}
		
		
	}
}
