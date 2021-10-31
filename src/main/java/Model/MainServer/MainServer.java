package Model.MainServer;

import Model.Chatrooms.MessageFeed;
import View.MainServer.MainFrame;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class MainServer extends Thread {
    private final ArrayList<Integer> chatroomPorts;
    private final MessageFeed frameMessages;

    /**
     * Create a Main Server with a new ArrayList and Messagefeed. Also start up the frame for the Main Server
     */
    public MainServer() {
        this.chatroomPorts = new ArrayList<>();
        this.frameMessages = new MessageFeed();
        new MainFrame(frameMessages);
    }

    /**
     * Start up the main server and wait for incoming connections. When a connection is established, a new thread will be started
     */
    @Override
    public void run() {
        frameMessages.addMessage("Server started");
        try (ServerSocket serverSocket = new ServerSocket(4242)) {
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Connection successful");
                frameMessages.addMessage("Connection successful");
                Thread thread = new ThreadedConnectionHandler(socket, this);
                thread.start();
            }
        } catch (IOException e) {
            System.out.println("Another instance of Main Server is already running (or another program with port 4242)");
        }
    }

    /**
     * Add the chatroom port for a chatroom to the ArrayList of the main server to mark the chatroom as active
     * @param portNumber the portNumber to be added
     */
    public synchronized void setChatroomPorts(int portNumber) {
        chatroomPorts.add(portNumber);
    }

    /**
     * Get a chatroom port from the array
     * @param index The index in the array of the port to be retrieved
     * @return The chatroom port number
     */
    public Integer getChatroomPort(int index) {
        return this.chatroomPorts.get(index);
    }

    /**
     * Get the size of the array of portnumbers
     * @return the size of the array
     */
    public Integer getNumberOfPorts() {
        return this.chatroomPorts.size();
    }

    /**
     * Retrieve the messageFeed of the server
     * @return the messageFeed of the server
     */
    public MessageFeed getFrameMessages() {
        return this.frameMessages;
    }
}
