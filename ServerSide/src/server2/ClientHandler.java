package server2;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.sql.ResultSet;
import java.sql.SQLException;

import project.InsertUpdateDelete;
import project.Select;

public class ClientHandler implements Runnable{
    private Socket mySocket;
    private Server chatServer;
    private String id;
    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	private InputStream input;
    private OutputStream output;

    public ClientHandler(Socket mySocket, Server chatServer){
        this.mySocket = mySocket;
        this.chatServer =chatServer;
        try {
            this.input = mySocket.getInputStream();
            this.output = mySocket.getOutputStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        try {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead=input.read(buffer))!=-1){
                String message = new String(buffer, 0, bytesRead);
                if(message.indexOf(',') == -1 && message.indexOf('W') == -1) {
                	setId(message);
                }else if(message.equals("XWinX")) {
                	ResultSet rs = Select.getData("select *from Games where game_id = (select max(game_id) from Games)");
    				System.out.println("1");
                	try {
                		if(rs.next()) {
                			String Query;
        					Query = "update Games set winner_id = "+id+",loser_id = "+rs.getInt(3)+" where game_id = (select max(game_id) from Games)";
        					InsertUpdateDelete.setData(Query, "");
                		}
    				} catch (SQLException e) {
    					e.printStackTrace();
    				}
                }else if(message.equals("XWinO")) {
                	
                }else if(message.equals("OWinO")) {
                	ResultSet rs = Select.getData("select *from Games where game_id = (select max(game_id) from Games)");
                	System.out.println("1");
                	try {
                		if(rs.next()) {
                			String Query;
                			Query = "update Games set winner_id = "+id+",loser_id = "+rs.getInt(2)+" where game_id = (select max(game_id) from Games)";
                			InsertUpdateDelete.setData(Query, "");
                		}
    					
    				} catch (SQLException e) {
    					e.printStackTrace();
    				}
                }else if(message.equals("oWinO")) {
                	
                }else if(message.equals("oWinX")) {
                	ResultSet rs = Select.getData("select *from Games where game_id = (select max(game_id) from Games)");
                	System.out.println("1");
                	try {
                		if(rs.next()) {
                			String Query;
                			Query = "update Games set winner_id = "+rs.getInt(3)+",loser_id = "+id+" where game_id = (select max(game_id) from Games)";
                			InsertUpdateDelete.setData(Query, "");
                		}
    					
    				} catch (SQLException e) {
    					e.printStackTrace();
    				}
                }else if(message.equals("xWinO")) {
                	ResultSet rs = Select.getData("select *from Games where game_id = (select max(game_id) from Games)");
                	System.out.println("1");
                	try {
                		if(rs.next()) {
                			String Query;
                			Query = "update Games set winner_id = "+rs.getInt(2)+",loser_id = "+id+" where game_id = (select max(game_id) from Games)";
                			InsertUpdateDelete.setData(Query, "");
                		}
    					
    				} catch (SQLException e) {
    					e.printStackTrace();
    				}
                }else if(message.equals("xWinX")) {
                	
                }else {
                	chatServer.broadcastMessage(message);
                }
            }
        } catch (IOException e) {
        	System.out.println("10");
            throw new RuntimeException(e);
        } finally {
        	System.out.println(id+ ": Disconnected from server");
        	Server.getClients().remove(0);
        }
    }

    public void sendMessage(String message){
        try {
            output.write(message.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
