package View.BotManager;

import Controller.KillAllBotsAction;
import Model.Bots.BotManager;

import javax.swing.*;
import java.awt.event.KeyEvent;

/**
 * The buttons located on the left panel (InputPanel). If pressed it will calculate the values.
 */
public class KillAllBotsButton extends JButton {

    public KillAllBotsButton(BotManager botManager) {
        super(new KillAllBotsAction(botManager));
        setButtonProperties();
    }

    /**
     * "ALT + K" will also trigger this button.
     */
    private void setButtonProperties() {
        setVerticalTextPosition(AbstractButton.CENTER);
        setHorizontalTextPosition(AbstractButton.CENTER);
        setMnemonic(KeyEvent.VK_K);
    }

}