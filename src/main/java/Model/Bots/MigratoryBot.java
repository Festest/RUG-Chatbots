package Model.Bots;

import Model.Chatrooms.MessageFeed;

import java.io.*;
import java.net.Socket;
import java.util.Random;

public class MigratoryBot extends Bot {
    public MigratoryBot(String name, String greeting, int behaviour) {
        super(name, greeting, behaviour);
    }

    /**
     * Run method for bot that can switch between rooms.
     */
    @Override
    protected void writeMessages(Socket socket) {
        try {
            ObjectInputStream object = new ObjectInputStream(socket.getInputStream());
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);

            printWriter.println(this.name + ": " + this.greeting);

            while (this.running) {
                chanceToMigrate(printWriter);
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
            socket.close();
        } catch (IOException e) {
            System.out.println("Unable to send message");
        }
    }

    /**
     * Decide based on a random int if the bot will migrate to another chatroom
     * @param printWriter the printWriter to print a message if the bot leaves
     */
    private void chanceToMigrate(PrintWriter printWriter) {
        Random random = new Random();
        if (random.nextInt(100) > 80) {
            printWriter.println(this.name + ": I am going somewhere else...");
            printWriter.println("Close Connection");
            this.running = false;
            this.run();
        }
    }
}
