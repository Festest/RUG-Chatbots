package Controller;

import Model.Bots.BotManager;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * The action to be executed when the Spawn Bots button is pressed.
 */
public class SpawnBotAction extends AbstractAction {
    private final BotManager botManager;
    private final JFormattedTextField botName;
    private final JFormattedTextField botGreeting;
    private final JCheckBox checkBox;

    public SpawnBotAction(BotManager botManager, JFormattedTextField botName, JFormattedTextField botGreeting, JCheckBox checkBox) {
        super("Spawn Bot");
        this.botManager = botManager;
        this.botName = botName;
        this.botGreeting = botGreeting;
        this.checkBox = checkBox;
    }

    /**
     * Spawn a new bot
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        this.botManager.createBot(botName.getText(), botGreeting.getText(),checkBox.isSelected());
    }
}