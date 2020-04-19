import java.io.*; 
import java.net.*; 
class Client { 

    public static void main(String argv[]) throws Exception 
    { 
        String userInput; 
        String serverResponse;  

        Socket clientSocket = new Socket("localhost", 8080); 
        //for translation to a separate machine, change localhost to HOSTNAME and change number to IP address
        //InetAddress address = InetAddress.getByName("172.20.10.4");
        //Socket clientSocket = new Socket(address, 1952);
        
        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream()); 
        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())); 

        /*Read user input*/
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
        userInput = inFromUser.readLine(); 

        /*Write to server and receive response*/
        outToServer.writeBytes(userInput + '\n');	// writeBytes() may cause issues. can use this line OR call writeToServer(). 
        serverResponse = inFromServer.readLine(); 
        System.out.println("FROM SERVER: " + serverResponse); 

        clientSocket.close(); 
                   
    } 
    
    private void writeToServer(Socket socket, String message) throws IOException {
    	PrintWriter writeSocket = new PrintWriter(socket.getOutputStream());
        writeSocket.println(message);
        //Finish output to Server
		writeSocket.println("");
        writeSocket.flush();
    }
} 
