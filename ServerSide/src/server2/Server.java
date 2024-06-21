package server2;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import project.InsertUpdateDelete;

public class Server {
    private static final int PORT = 5000;
    private static List<ClientHandler> clients = new ArrayList<>();
    private String XorO = "X";

	public void StartServer(){
        try {
            //web socket
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("Server started. Listening on port: "+PORT);

            //Clients connect to server
            while (true){
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client connected: "+clientSocket.getInetAddress().getHostAddress());

                ClientHandler clientHandler = new ClientHandler(clientSocket, this);
                clients.add(clientHandler);

                new Thread(clientHandler).start();
                clientHandler.sendMessage(XorO);
                
                if(XorO == "X") {
        			XorO = "O";
        		}else {
        			XorO = "X";
        		}
                if(clients.size()==2) {
                	Thread.sleep(1000);
                	String Query;
					Query = "insert into Games (player1_id, player2_id) values('"+clients.get(0).getId()+"','"+clients.get(1).getId()+"')";
					InsertUpdateDelete.setData(Query, "");
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public static List<ClientHandler> getClients() {
		return clients;
	}

	public void setClients(List<ClientHandler> clients) {
		this.clients = clients;
	}

	public void broadcastMessage(String message){
        for (ClientHandler client : clients){
            client.sendMessage(message);
        }
    }
}
