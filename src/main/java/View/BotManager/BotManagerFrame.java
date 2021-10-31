package View.BotManager;

import Model.Bots.BotManager;

import javax.swing.*;
import java.awt.*;

/**
 * The frame containing the two panels. This frame is not resizable and is 450x160.
 */
public class BotManagerFrame extends JFrame {
    public BotManagerFrame(BotManager botManager) {
        /* Create a frame for the GUI */
        super("Bot Manager");

        /* Make sure our program exits when we close the frame */
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        /* Create a view for the Bot Manager */
        LeftPanel leftPanel = new LeftPanel(botManager);
        RightPanel rightPanel = new RightPanel(botManager);
        JPanel filler = new JPanel();

        /* Position each panel */
        leftPanel.setBounds(0,0,230,125);
        rightPanel.setBounds(230,0,150,125);


        /* Add the view to the frame */
        add(leftPanel);
        add(rightPanel);
        add(filler);


        /* Set the size of the frame */
        setPreferredSize(new Dimension(380, 160));

        /* Set minimum size*/
        setResizable(false);

        /* Try to make all the components at or above their preferred size */
        pack();

        /* Center the frame on the screen */
        setLocationRelativeTo(null);

        /* Make sure we can actually see the frame */
        setVisible(true);
    }
}