package Model.Bots;

import Model.Chatrooms.MessageFeed;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

import static java.lang.Integer.parseInt;

/**
 * Abstract class that defines a bot, has subclasses Local Bot and Migratory Bot
 */
public abstract class Bot extends Thread {
    protected MessageFeed messageFeed;
    private Integer currentPort;
    protected boolean running;
    protected final String name;
    protected final String greeting;
    private final int behaviour;

    /**
     * Create a bot based on the name, greeting and behavior
     * @param name name of bot
     * @param greeting greeting of bot
     * @param behaviour behavior of bot
     */
    public Bot(String name, String greeting, int behaviour) {
        this.messageFeed = new MessageFeed();
        this.currentPort = -1;
        this.running = false;
        this.name = name;
        this.greeting = greeting;
        this.behaviour = behaviour;
    }

    /**
     * Connect to the main server to request a chatroom port to then connect to that chatroom
     */
    @Override
    public void run() {
        try {
            Socket socket = new Socket();
            socket.connect(new InetSocketAddress("localhost", 4242), 1000);

            if (!socket.isConnected()) {
                System.out.println("Unable to connect to Model.MainServer");
                return;
            }

            getChatRoomPort(socket);
            socket.close();

            this.connectToChatroom();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Receive a chatroom port from the main server
     * @param socket the socket of the bot
     */
    private void getChatRoomPort(Socket socket) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
            printWriter.println("Get Chatroom Port");
            printWriter.println(currentPort);

            String line = bufferedReader.readLine();
            this.currentPort = parseInt(line);

            printWriter.println("Close Connection");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Connect to a chatroom, set running to true and then write messages depending on whether the bot is local or migratory
     */
    protected void connectToChatroom() {
        try {
            Socket socket = new Socket();
            socket.connect(new InetSocketAddress("localhost", currentPort), 1000);

            if (socket.isClosed()) {
                System.out.println("Unable to connect to Chatroom");
                return;
            }

            running = true;
            writeMessages(socket);
        }
        catch (IOException e) {
            System.out.println("Error while connecting to chatroom");
        }
    }

    /**
     * Function to let the bot write messages, defined in subclasses
     * @param socket The socket of the bot
     */
    protected void writeMessages(Socket socket) {

    }

    /**
     * Terminate a bot by setting its boolean running to false
     */
    public void terminateBot() {
        this.running = false;
    }

    /**
     * Returns a message based on the behavior of the bot
     * @return the message string
     */
    private String newMessage() {
        String message = "";
        switch(behaviour) {
            case 0: message = randomLine();
            break;
            case 1: message = combineMessage();
            break;
        }
        return message;
    }

    /**
     * This function takes the last few messages of the chatroom's messageFeed and combines these. The new string will consists of randomly chosen words of those messages
     * @return The message string
     */
    private String combineMessage() {
        ArrayList<String> lastMessages = new ArrayList<>();

        for (int i = messageFeed.getSize() - 5; i < messageFeed.getSize(); i++) {
            if (i < 0) i = 0;
            String[] words = messageFeed.getMessage(i).split("\\W+");
            lastMessages.addAll(Arrays.asList(words));
        }

        Random random = new Random();
        int n = random.nextInt(6) + 10;
        if (lastMessages.size() < 16) n = lastMessages.size();

        String newMessage = this.name + ":";
        for(int i = 1; i < n; i++) {
            int rand = random.nextInt(10);
            if (rand < 6) newMessage = newMessage.concat(" " + lastMessages.get(i));
        }
        return newMessage;
    }

    /**
     * The thread will sleep for a random amount of time, when it wakes up it will send a new chat message
     * @param printWriter
     */
    protected void sendMessage(PrintWriter printWriter) {
        try {
            Thread.sleep(100);
            printWriter.println(this.newMessage());
            Thread.sleep(ThreadLocalRandom.current().nextInt(3000,10000));
        } catch (InterruptedException e) {
            System.out.println("Sleep interrupted");
        }
    }

    /**
     * Returns a random prebuilt dialog string
     * @return the string with the message to be sent
     */
    private String randomLine() {
        String string = "";

        try {
            Random random = new Random();
            BufferedReader br = new BufferedReader(new FileReader("./src/main/java/Model/Bots/Lines"));
            String line = br.readLine();

            for (int i = 0; i < random.nextInt(51); i++) { //Total of 50 strings on file Lines.txt
                line = br.readLine();
            }

            string = line;
            br.close();

        } catch (IOException e) {
            System.out.println("ERROR READING FILE");
        }
        return this.name + ": " + string;
    }
}
