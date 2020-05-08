import java.io.*;
import java.net.*;
import java.util.*;

class Server implements Runnable{ 
	
	private Socket clientSocket = null;
	// Vector to store active clients 
    	static Vector<User> userList = new Vector<>();
	
	public static void main(String args[]) throws Exception { 
		
		ServerSocket myServer = new ServerSocket(8080);
		System.out.println("Chat Server is open...");
		
		while(true) {  
			//get a connection from client
            Socket cSocket = myServer.accept(); 
			System.out.println("Listening for connections on port 8080...\n");
			
			//create and start a thread
			Thread cThread = new Thread(new Server(cSocket)); 
			cThread.start(); 		
		}			
    }
	
	public Server (Socket csocket){
		this.clientSocket = csocket;
	}
	
	public void run(){

	 	DataInputStream inFromClient = null;
	  	DataOutputStream outToClient = null;
	  	
	  	try{
			inFromClient = new DataInputStream(clientSocket.getInputStream()); 
			outToClient = new DataOutputStream(clientSocket.getOutputStream()); 
			
			this.clientSocket.close();
		}catch(Exception ex){
			System.out.println("Exception ex: " + ex.getMessage());
	  	}
	}

}
