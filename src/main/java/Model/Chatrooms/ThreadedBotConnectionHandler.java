package Model.Chatrooms;

import java.io.*;
import java.net.Socket;

/**
 * Class to handle the connection between bots and chatrooms
 */
public class ThreadedBotConnectionHandler extends Thread{
    private final Socket socket;
    private final Chatroom chatroom;

    public ThreadedBotConnectionHandler (Socket socket, Chatroom chatroom) {
        this.socket = socket;
        this.chatroom = chatroom;
    }

    /**
     * Read incoming messages and add them to the MessageFeed of the chatroom, then send the messageFeed to the connected bots
     */
    @Override
    public void run() {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            String line;

            ObjectOutputStream object = new ObjectOutputStream(socket.getOutputStream());

            while ((line = bufferedReader.readLine()) != null && !line.trim().equals("Close Connection")) {
                if (line.equals("Get Message Feed")) {
                    object.writeObject(chatroom.getMessageFeed());
                }
                else chatroom.addIncomingMessage(line);
            }
            if (socket.isConnected()) {
                chatroom.displayBotDisconnected();
                bufferedReader.close();
                object.close();
                socket.close();
            }
        }
        catch (IOException e) {
            System.out.println("Bot Socket Error");
        }
        finally {
            this.interrupt();
        }
    }
}
