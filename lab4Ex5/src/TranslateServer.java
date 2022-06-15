import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


/**
 * This class is the server side of the application
 * The server will receive english word and language
 * Server will send the tsranslated text to the client
 * @author WM TAN
 *
 */
public class TranslateServer {
	public static void main(String[] args) throws IOException {
		// Bind serverSocket to a port
		int portNo = 4000;
		ServerSocket serverSocket = new ServerSocket(portNo);
		while(true) {
			// Accept client request for connection
			Socket socket = serverSocket.accept();
			// create bufferedReader to read data from client
			BufferedReader readIn = 
					new BufferedReader
					(new InputStreamReader(socket.getInputStream()));
			// read the text and language from client side
			String text = readIn.readLine();
			String language = readIn.readLine();
			
			// read the translateFile
			BufferedReader translateFile = 
					new BufferedReader(new FileReader("translateDatabase.txt"));
			// find the sequence of translatedText to pick 
			// from the language
			int sequence = 0;
			String malay="Malay";
			String arabic = "Arabic";
			String korean = "Korean";
			if(language.equals(malay)) {
				sequence = 1;
			}
			else if(language.equals(arabic)){
				sequence = 2;
			}
			else if(language.equals(korean)) {
				sequence = 3;
			}

			String translatedText = "";
			// find the translation in database
			while((translatedText = translateFile.readLine()) != null) {
				if(text.equals(translatedText)) {
					for(int i = 0; i < sequence; i++) {
						translatedText = translateFile.readLine();	
					}
					break;
				}
			}
			
			// Send the translatedText to the client
			PrintWriter writeOut = 
					new PrintWriter(socket.getOutputStream(), true);
			writeOut.write(translatedText + "\n");
			writeOut.flush();
			
			// close the file
			translateFile.close();
			socket.close();
		}
	}
}
