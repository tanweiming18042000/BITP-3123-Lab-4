import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

/**
 * This class is the client side application
 * Client can send text and language to the server side
 * Get translated and get the desired translated text
 * @author WM TAN
 *
 */
public class TranslateClient {
	public static void main(String[] args) throws IOException {
		// Connect to the server at local host, port 4000
		Socket socket = new Socket(InetAddress.getLocalHost(), 4000);
		// Create a stream
		PrintWriter writeOut = new PrintWriter(socket.getOutputStream(), true);
		// Declare text and language
		String text = "Goodbye";
		String language = "Malay";
		// write the text and language
		writeOut.write(text + "\n");
		writeOut.write(language + "\n");
		// send the message
		writeOut.flush();
		// Create a bufferedReader to read from server 
		BufferedReader bufferedReader = 
				new BufferedReader
				(new InputStreamReader(socket.getInputStream()));
		// Read translated text from the socket
		String translatedText = bufferedReader.readLine();
		// Display the translated text
		System.out.println(translatedText);
		// Close all socket
		socket.close();
		bufferedReader.close();
	}
}
