import Model.Bots.Bot;
import Model.Bots.BotManager;
import Model.Bots.LocalBot;
import Model.Chatrooms.Chatroom;
import Model.Chatrooms.MessageFeed;
import Model.MainServer.MainServer;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;

import static java.lang.Integer.parseInt;
import static org.junit.jupiter.api.Assertions.*;

public class NetworkTesting {
    /**
     * Test the starting of a main server and the connecting between a chatroom and main server. This immediately checks too if the chatroom sends its portnumber, because if this is the case, the arraylist of the mainserver will have an entry for that port number
     */
    @Test
    public void testConnection() {
        // Start up a main server
        MainServer mainServer = new MainServer();
        assertFalse(mainServer.isAlive());
        mainServer.start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertTrue(mainServer.isAlive());

        // Start up a chatroom
        Chatroom chatroom = new Chatroom();
        chatroom.start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertEquals(1, mainServer.getNumberOfPorts());

        mainServer.interrupt();
    }

    /**
     * Tests if the main server stores the chatroom port correctly and is able to return it to a bot
     */
    @Test
    public void testChatRoomPorts() {
        MainServer mainServer = new MainServer();
        mainServer.start();

        try {
            Socket socket = new Socket();
            socket.connect(new InetSocketAddress("localhost", 4242), 1000);

            if (!socket.isConnected()) {
                System.out.println("Not connected");
                fail();
            }

            Random random = new Random();
            int portNumber = random.nextInt(10000 - 1000) + 1000;
            System.out.println(portNumber);

            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            printWriter.println(portNumber);
            printWriter.println("Get Chatroom Port");
            printWriter.println(-1);

            String line = bufferedReader.readLine();
            System.out.println(line);
            assertEquals(portNumber, parseInt(line));

            printWriter.println("Close Connection");

            printWriter.close();
            bufferedReader.close();
            socket.close();

            if (socket.isClosed()) System.out.println("Socket correctly closed :D");
            else fail(); // :c
        }
        catch (IOException e) {
            System.out.println("ERROR: Faulty connection with the Main Server");
            fail();
        }
        mainServer.interrupt();
    }
}
