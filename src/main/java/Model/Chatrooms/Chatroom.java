package Model.Chatrooms;

import View.Chatroom.ChatFrame;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Class that defines a chatroom, the chatroom will have two messageFeeds, display and messageLogs. Display is used for the frame/panel and contains everything that has to be added to the view. MessageLogs will just keep track of the messages and is used for the bots, so they wont have access to the displayed 'server' messages.
 */
public class Chatroom extends Thread {
    private final MessageFeed messageLogs;
    private final MessageFeed display;
    private ServerSocket serverSocket;

    /**
     * Construct a chatroom and start up a frame for the chatroom
     */
    public Chatroom() {
        this.messageLogs = new MessageFeed();
        this.display = new MessageFeed();
        new ChatFrame(display);
    }

    /**
     * Set up a server socket, send its port to the main server and then wait for incoming bot connections. When a bot is connected, a new thread is created for it and started
     */
    @Override
    public void run() {
        try (ServerSocket newServerSocket = new ServerSocket(0)) {
            int portNumber = newServerSocket.getLocalPort();

            connectToMainServer(portNumber);
            this.serverSocket = newServerSocket;

            display.addMessage("Waiting for bots to connect");

            while (true) {
                Socket socket = serverSocket.accept();
                display.addMessage("Someone has joined the chatroom");
                Thread thread = new ThreadedBotConnectionHandler(socket,this);
                thread.start();
            }

        } catch (IOException e) {
            display.addMessage("ERROR: Unable to create new port for Chatroom");
        }
    }

    /**
     * Connect to the main server to send the portnumber
     * @param portNumber The portnumber of the chatroom
     */
    private void connectToMainServer(int portNumber) {
        try {
            Socket socket = new Socket();
            socket.connect(new InetSocketAddress("localhost", 4242), 1000);

            if (!socket.isConnected()) {
                display.addMessage("ERROR: Unable to connect to Model.MainServer");
                return;
            }

            display.addMessage("Connected to main server");

            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
            printWriter.println(portNumber);
            display.addMessage("Sending portnumber "+ portNumber + " to main server");
            printWriter.println("Close Connection");

            printWriter.close();
            socket.close();
            if (socket.isClosed()) display.addMessage("Connection closed with main server");
        }
        catch (IOException e) {
            display.addMessage("ERROR: Faulty connection with the Main Server");
        }
    }

    /**
     * Get the messagefeed of this chatroom
     * @return the messageFeed of the chatroom
     */
    public synchronized MessageFeed getMessageFeed() {
        return this.messageLogs;
    }

    /**
     * Add an incoming message to both messageFeeds of the chatroom
     * @param message The message to be added
     */
    public synchronized void addIncomingMessage(String message){
        this.messageLogs.addMessage(message);
        this.display.addMessage(message);
    }

    /**
     * Message to be displayed on the frame when a bot terminates the connection with the chatroom server
     */
    protected void displayBotDisconnected() {
        this.display.addMessage("Bot disconnecting...");
    }
}
