package model;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.SwingUtilities;

import view.Board;

public class Client{
    private static final int PORT = 5000;
    private static final String URL = "localhost";

    public void createClient(){
        try {
            Socket socket = new Socket(URL, PORT);
            System.out.println("Connected to server");

            // Create Board object without UI components
            Board board = new Board(socket);
            
            // Start the Board thread (assuming it doesn't interact with UI directly)
            new Thread(board).start();
            
            // Call method to initialize UI components on the EDT (after Board creation)
            try {
				SwingUtilities.invokeLater(() -> board.initializeUI());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

        } catch (IOException e) {
            System.out.println("Can't connect to server");
        }
    }
}
