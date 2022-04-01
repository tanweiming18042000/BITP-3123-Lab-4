package exercise4;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerTranslationApplication {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		ServerSocket serverSocket = null;
		
		try {
			//Bind Serversocket to a port
			int portNo = 4228;
			serverSocket = new ServerSocket(portNo);
			
			String text1 = "Good afternoon";
			System.out.println("Waiting for request");
			
			while (true) {
				// Accept client request for connection
				Socket clientSocket = serverSocket.accept();
				
				//Create Stream to write data on the network
				DataOutputStream outputStream = new DataOutputStream(clientSocket.getOutputStream());
				
				//send current data back to the client
				outputStream.writeUTF(text1);
				
				//close the socket
				clientSocket.close();
				
			}
			//closing is not neccesary because the code is unreachable
			
		}catch (IOException ioe) {
			
			if(serverSocket !=null)
				serverSocket.close();
			
			ioe.printStackTrace();
		}
	}

}