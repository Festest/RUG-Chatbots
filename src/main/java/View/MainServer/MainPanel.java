package View.MainServer;

import Model.Chatrooms.MessageFeed;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * Class for the panel of the frame for the main server
 */
public class MainPanel extends JPanel implements PropertyChangeListener {
    private final MessageFeed messageFeed;
    private final JTextArea outputTextArea;

    private final int ROWS = 40;
    private final int COLUMNS = 50;
    private final int GAP = 3;

    /**
     * Initialise the GUI components
     * @param frameMessages The message feed that this class will listen to
     */
    public MainPanel(MessageFeed frameMessages) {
        this.messageFeed = frameMessages;
        frameMessages.addListener(this);

        outputTextArea = new JTextArea(ROWS, COLUMNS);

        outputTextArea.setFocusable(false);
        outputTextArea.setEditable(false);

        setBorder(BorderFactory.createEmptyBorder(GAP, GAP, GAP, GAP));
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        add(putInTitledScrollPane(outputTextArea, "Output Text"));

        if (frameMessages.getSize() > 0) {
            outputTextArea.append(frameMessages.getMessage(frameMessages.getSize() - 1));
        }
    }

    /**
     * Creates a new JPanel and sets its title and border
     * @param component The component to be put in the JPanel
     * @param title     The title that will be put on the JPanel
     * @return a JPanel containing a title and border
     */
    private JPanel putInTitledScrollPane(JComponent component, String title) {
        JPanel wrapperPanel = new JPanel(new BorderLayout());
        wrapperPanel.setBorder(BorderFactory.createTitledBorder(title));
        wrapperPanel.add(new JScrollPane(component));
        return wrapperPanel;
    }

    /**
     * Whenever a property is changed, we simply append the message stored in the messageFeed to the outputTextArea.
     * @param evt unused
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        outputTextArea.append(messageFeed.getMessage(messageFeed.getSize() - 1) + "\n");
    }
}