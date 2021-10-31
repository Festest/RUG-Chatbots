package View.BotManager;

import Model.Bots.BotManager;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * The right hand panel that displays the calculated values.
 */
public class RightPanel extends JPanel implements PropertyChangeListener {
    private final BotManager botManager;
    private final JLabel activeBots;
    private final KillAllBotsButton killAllBotsButton;

    /**
     * Panel constructor. The layout of the text is defined here.
     * @param botManager the calculator that calculates the values to be displayed
     */
    public RightPanel(BotManager botManager) {

        setBackground(Color.DARK_GRAY);

        this.botManager = botManager;
        this.activeBots = new JLabel();
        this.killAllBotsButton = new KillAllBotsButton(botManager);
        botManager.addListener(this);

        text();

        GroupLayout layout = new GroupLayout(this);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addGap(25)
                                .addComponent(activeBots)
                                .addComponent(killAllBotsButton)
                        )
        );
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addGap(25)
                        )
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(activeBots)
                        )
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(killAllBotsButton)
                        )
        );
        this.setLayout(layout);
    }

    /**
     * The text to be displayed and updated when new values are calculated
     */
    public void text() {
        activeBots.setText("<html><font color=\"white\">Active Bots: " + botManager.getActiveBotNumber() + "</font></html>");
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        text();
    }
}