import java.io.*;
import java.net.*;
import java.util.*;

class Tracker{
   // mutlicast socket for group chat
   final static String INET_ADDR = "228.5.6.7";
   final static int PORT = 6789;

   // list of active peers
   private static List<String> activeList = new ArrayList<String> ();

   // tracker socket
   private static DatagramSocket trackerSocket;
   private static DatagramPacket receivePacket;

   public static void main(String args[]) throws Exception {
      
      // tracker listening on port 9876
      trackerSocket = new DatagramSocket(9876);
      byte[] receiveData = new byte[1024];
      byte[] sendData = new byte[1024];
     
      while(true){
         receivePacket = new DatagramPacket(receiveData, receiveData.length);
         trackerSocket.receive(receivePacket);
         String msg = new String(receivePacket.getData());
         System.out.println("RECEIVED: " + msg);
        
         if (msg.equals("enter")){
            addUser();
            // notify chat that new user has enter the chat
            notifyChat("new user has enter chat...");
         }else if (msg.equals("leave")){
            deleteUser();
            // notify chat that user has left the chat
            notifyChat("user has left the chat...");
         }

      }
   }

   private static void addUser(){
      InetAddress IPAddress;
      int newUserPort;
      String user, groupChatAddress;
      try {
         // get new user IP address and port #
         IPAddress = receivePacket.getAddress();
         newUserPort = receivePacket.getPort();
    
         // add new peer's information into active list
         user = IPAddress + " " + newUserPort;
         activeList.add(user);
         System.out.println("active list: " + activeList);

         // send new peer group chat's address and port #
         groupChatAddress = INET_ADDR + " " + PORT;
         byte[] sendData = groupChatAddress.getBytes();
         DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, newUserPort);
         trackerSocket.send(sendPacket);
      } catch (IOException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }

   private static void deleteUser(){
      InetAddress IPAddress;
      int newUserPort;
      String user, groupChatAddress;
      
      try {
         // get user IP address and port #
         IPAddress = receivePacket.getAddress();
         newUserPort = receivePacket.getPort();
    
         // delete new peer's information from active list
         user = IPAddress + " " + newUserPort;
         activeList.remove(user);
         System.out.println("active list: " + activeList);

      } catch (IOException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }

   private static void notifyChat(String msg){
      DatagramSocket chatSocket; 
      InetAddress group;
      byte[] sendData;
    
      try{
         sendData = msg.getBytes();
         chatSocket = new DatagramSocket();
         group = InetAddress.getByName(INET_ADDR);
         DatagramPacket packet = new DatagramPacket(sendData, sendData.length, group, PORT);
         chatSocket.send(packet);

      }catch (UnknownHostException e) {
      // TODO Auto-generated catch block
         e.printStackTrace();
      }catch (IOException e) {
      // TODO Auto-generated catch block
         e.printStackTrace();
      }
      
   }
}