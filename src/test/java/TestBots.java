import Model.Bots.Bot;
import Model.Bots.BotManager;
import Model.Bots.LocalBot;
import Model.Bots.MigratoryBot;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestBots {

    @Test
    public void testBotConstructor() {
        Bot bot = new LocalBot("bot", "hello", 0);
        Bot bot1 = new MigratoryBot("bot1", "goodbye", 1);
        assertFalse(bot.isAlive());
        assertFalse(bot1.isAlive());
        bot.start();
        bot1.start();
        assertTrue(bot.isAlive());
        assertTrue(bot1.isAlive());
    }

    @Test
    public void testBotManagerConstructor() {
        BotManager botManager = new BotManager();
        assertEquals(0, botManager.getActiveBotNumber());
    }

    @Test
    public void testCreateBot() {
        BotManager botManager = new BotManager();
        assertEquals(0, botManager.getActiveBotNumber());
        botManager.createBot("Test", "Hello, I'm a test bot", false);
        assertEquals(1, botManager.getActiveBotNumber());

    }

    @Test
    public void testKillAllBots() {
        BotManager botManager = new BotManager();
        assertEquals(0, botManager.getActiveBotNumber());
        botManager.killAllBots();
        assertEquals(0, botManager.getActiveBotNumber());

        botManager.createBot("Test", "Hello, I'm a test bot", false);
        botManager.createBot("Bot5" ,"When's the last time you laughed so hard you couldn't breathe?", false);
        botManager.createBot("Bot6" ,"What's the one restaurant you'd recommend everyone visit in your hometown?", true);
        assertEquals(3, botManager.getActiveBotNumber());
        botManager.killAllBots();
        assertEquals(0, botManager.getActiveBotNumber());
    }
}
