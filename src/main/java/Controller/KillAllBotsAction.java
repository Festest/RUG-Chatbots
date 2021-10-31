package Controller;

import Model.Bots.BotManager;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * The action to be executed when the Kill All Bots button is pressed.
 */
public class KillAllBotsAction extends AbstractAction {
    private final BotManager botManager;

    public KillAllBotsAction(BotManager botManager) {
        super("Kill All Bots");
        this.botManager = botManager;
    }

    /**
     * Kill all bots
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        this.botManager.killAllBots();
    }
}