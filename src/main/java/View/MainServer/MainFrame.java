package View.MainServer;

import Model.Chatrooms.MessageFeed;

import javax.swing.*;
import java.awt.*;

/**
 * Class for the frame of the Main Server
 */
public class MainFrame extends JFrame{

    private static final int DIMENSION_WIDTH = 600;
    private static final int DIMENSION_HEIGHT = 750;

    /**
     * Constructor for the MainFrame
     * @param messageFeed the messagefeed that is used for the panel
     */

    public MainFrame(MessageFeed messageFeed) {
        /* Create a frame for the GUI */
        super("Main Server");
        /* Make sure our program exits when we close the frame */
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        /* Create a view */
        MainPanel panel = new MainPanel(messageFeed);

        /* Add the view to the frame */
        add(panel);

        /* Set the size of the frame */
        setPreferredSize(new Dimension(DIMENSION_WIDTH, DIMENSION_HEIGHT));

        /* Set minimum size*/
        setMinimumSize(new Dimension(200, 480));

        /* Try to make all the components at or above their preferred size */
        pack();

        /* Center the frame on the screen */
        setLocationRelativeTo(null);

        /* Make sure we can actually see the frame */
        setVisible(true);
    }
}