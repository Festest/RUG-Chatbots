package Model.MainServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.ThreadLocalRandom;

import static java.lang.Integer.parseInt;

/**
 * Class for the way threads are handled
 */
public class ThreadedConnectionHandler extends Thread{
    private final Socket socket;
    private final MainServer server;

    public ThreadedConnectionHandler (Socket socket, MainServer server) {
        this.socket = socket;
        this.server = server;
    }

    /**
     * Method that handles the connection with the threads, when a bot connects it gives this bot a portnumber, in case a chatroom connection is established the portnumber of this chatroom will be added to the arraylist of the server
     */
    @Override
    public void run() {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true)) {
            String line;

            while ((line = bufferedReader.readLine()) != null && !line.trim().equals("Close Connection")) {
                if (line.equals("Get Chatroom Port")) {
                    server.getFrameMessages().addMessage("A bot is requesting a port number");

                    int randomNum, currentBotPort;
                    currentBotPort = parseInt(bufferedReader.readLine());

                    do {
                        randomNum = ThreadLocalRandom.current().nextInt(server.getNumberOfPorts());
                    } while (server.getChatroomPort(randomNum) == currentBotPort);

                    printWriter.println(server.getChatroomPort(randomNum));
                    server.getFrameMessages().addMessage("Bot has been given portnumber " + server.getChatroomPort(randomNum));
                }
                else {
                    server.setChatroomPorts(parseInt(line));
                }
            }
            printWriter.close();
            bufferedReader.close();
            socket.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            this.interrupt();
        }
    }
}
