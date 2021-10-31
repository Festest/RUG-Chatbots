package View.BotManager;

import Model.Bots.BotManager;

import javax.swing.*;
import java.awt.*;

/**
 * The left hand panel that manages the user input through textFields and checkBoxes.
 */
public class LeftPanel extends JPanel {

    /**
     * The constructor for the panel.
     * @param botManager
     */
    public LeftPanel(BotManager botManager) {
        setBackground(Color.DARK_GRAY);

        JLabel label1 = new JLabel("<html><font color=\"white\">Bot Name</font></html>");
        JFormattedTextField botName = new JFormattedTextField();
        botName.setValue("Bot");
        JLabel label2 = new JLabel("<html><font color=\"white\">Bot Greeting</font></html>");
        JFormattedTextField botGreeting = new JFormattedTextField();
        botGreeting.setValue("Hello! I am a bot.");
        JCheckBox checkBox = new JCheckBox("Migratory Bot",true);
        SpawnBotButton spawnBotButton = new SpawnBotButton(botManager, botName, botGreeting, checkBox);

        GroupLayout layout = new GroupLayout(this);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                .addComponent(label1)
                                .addComponent(label2)
                                .addComponent(checkBox)
                        )
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                .addComponent(botName)
                                .addComponent(botGreeting)
                                .addComponent(spawnBotButton)
                        )
        );
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                .addComponent(label1)
                                .addComponent(botName)
                        )
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                .addComponent(label2)
                                .addComponent(botGreeting)
                        )
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                .addComponent(checkBox)
                                .addComponent(spawnBotButton)
                        )
        );
        this.setLayout(layout);
    }
}