/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tp5communicationserver;

import java.sql.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class TP5CommunicationServer {

    Connection conn;
    ServerSocket serverSocket;
    static String name;
    static String firstname;
    static String pieceName;
    static int placeNumber;
    static List<String> listPlayFromDatabase;

    public static void main(String[] args) throws IOException {
        TP5CommunicationServer server = new TP5CommunicationServer();
        server.begin(4444);
    }

    public void begin(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        while (true) {
            System.out.println("Waiting for clients to connect on port " + port + "...");
            new ProtocolThread(serverSocket.accept()).start();
        }
    }

    class ProtocolThread extends Thread {

        Socket socket;
        PrintWriter out_socket;
        BufferedReader in_socket;

        public ProtocolThread(Socket socket) {
            System.out.println("Accepting connection from " + socket.getInetAddress() + "...");
            this.socket = socket;
            try {
                out_socket = new PrintWriter(socket.getOutputStream(), true);
                in_socket = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {

            int saisie;
            Boolean sendPlayList = false;
            try {
                sqlRequest.getListPlayFromDatabase();
                System.out.println(listPlayFromDatabase.size());
                
                out_socket.println(listPlayFromDatabase.size());
               /* for (String elem : listPlayFromDatabase) {
                    out_socket.println(elem);
                }*/
                if(sendPlayList){
                    System.out.println("zazazazazazaazazazaz");
                name = in_socket.readLine();
                firstname = in_socket.readLine();
                pieceName = in_socket.readLine();
                placeNumber = Integer.parseInt(in_socket.readLine());
                //System.out.println(name + " " + firstname + " " + pieceName + " " + placeNumber);
                //sqlRequest.getListPlayFromDatabase();
                }

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    System.out.println("Closing connection.");
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
