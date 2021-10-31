package View.BotManager;

import Controller.SpawnBotAction;
import Model.Bots.BotManager;

import javax.swing.*;
import java.awt.event.KeyEvent;

/**
 * The buttons located on the left panel (InputPanel). If pressed it will calculate the values.
 */
public class SpawnBotButton extends JButton {

    public SpawnBotButton(BotManager botManager, JFormattedTextField botName, JFormattedTextField botGreeting, JCheckBox checkBox) {
        super(new SpawnBotAction(botManager, botName, botGreeting, checkBox));
        setButtonProperties();
    }

    /**
     * "ALT + S" will also trigger this button.
     */
    private void setButtonProperties() {
        setVerticalTextPosition(AbstractButton.CENTER);
        setHorizontalTextPosition(AbstractButton.CENTER);
        setMnemonic(KeyEvent.VK_S);
    }

}