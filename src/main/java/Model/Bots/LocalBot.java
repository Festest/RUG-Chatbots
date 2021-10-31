package Model.Bots;

import Model.Chatrooms.MessageFeed;

import java.io.*;
import java.net.Socket;

public class LocalBot extends Bot {

    public LocalBot(String name, String greeting, int behaviour) {
        super(name, greeting, behaviour);
    }

    /**
     * Run method for bot that stays in the same chatroom. It sends its name and greeting, tries to receive the messageFeed of the chatroom and then sends a message
     */
    @Override
    protected void writeMessages(Socket socket) {
        try {
            ObjectInputStream object = new ObjectInputStream(socket.getInputStream());
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);

            printWriter.println(this.name + ": " + this.greeting);

            while (this.running) {
                try {
                    printWriter.println("Get Message Feed");
                    this.messageFeed = (MessageFeed)object.readObject();
                } catch (ClassNotFoundException e) {
                    System.out.println("Unable to receive message feed from server");
                }

                sendMessage(printWriter);
            }
            object.close();
            printWriter.close();
        } catch (IOException e) {
            System.out.println("Unable to send message");
        }
    }


}
