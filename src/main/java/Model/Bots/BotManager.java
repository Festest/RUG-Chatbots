package Model.Bots;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Random;

public class BotManager {
    private final ArrayList<Bot> bots;
    private final PropertyChangeSupport propertyChangeSupport;

    public BotManager() {
        this.bots = new ArrayList<>();
        this.propertyChangeSupport = new PropertyChangeSupport(this);
    }

    /**
     * Execute the createBot function and specify a name and greeting for the bot. Also set if the bot should be migratory
     */
    public void start() {
        try {
            createBot("Simon" ,"Hello! How is everyone doing?", false);
            Thread.sleep(10);
            createBot("Gabrielle" ,"Hi! I enjoyed my day quite a lot. How about you?", false);
            Thread.sleep(10);
            createBot("Leslie" ,"It is quite a fine evening. Did you have a good day?", true);
            Thread.sleep(10);
            createBot("Kelly" ,"Working on anything exciting at the moment?", false);
            Thread.sleep(10);
            createBot("Matthew" ,"When's the last time you laughed so hard you couldn't breathe?", false);
            Thread.sleep(10);
            createBot("Sylvie" ,"What's the one restaurant you'd recommend everyone visit in your hometown?", true);
            Thread.sleep(10);
            createBot("Herman" ,"What's the most interesting thing you've read recently?", false);
            Thread.sleep(10);
            createBot("Donna" ,"What's the high-point and low-point of your day so far?", false);
            Thread.sleep(10);
            createBot("Marcel" ,"Hey! Let's get together, when are you free so we can meet up?", true);
            Thread.sleep(10);
            createBot("Brent" ,"Good day, I am an engineer!", false);
            Thread.sleep(10);
            createBot("Brittany" ,"I wanna scream and shout and let it all out", false);
            Thread.sleep(10);
            createBot("Stephen" ,"The history of time is here, my friends", false);
            Thread.sleep(10);
            createBot("Davus" ,"Breaked? Is that a real word?!", true);
            Thread.sleep(10);
            createBot("Miguel" ,"Is it almost Christmas yet? I want to set up my tree", false);
            Thread.sleep(10);
            createBot("Philip" ,"I am drunk and think that humanity is bad and should be terminated", false);
            Thread.sleep(10);
            createBot("Fox" ,"What does the fox say? Meow! Meow! I am a cow", true);
            Thread.sleep(10);
            createBot("Luna" ,"The stars are pretty tonight", false);
            Thread.sleep(10);
            createBot("Dennis" ,"I am not gonna say anything, unless I do", false);
            Thread.sleep(10);
            createBot("Tristan" ,"Barbie is life, we should all obey Barbie. Yes I am doing fine.", true);
        } catch (InterruptedException e) {
            System.out.println("Bot Startup Interrupted");
        }
    }

    /**
     * Creates a bot with the specified name, greeting and boolean migratory. It will also assign a random behavior to the bot
     * @param name Name of the bot
     * @param greeting Greeting of the bot
     * @param migratory Whether or not the bot should be migratory
     */
    public void createBot(String name, String greeting, boolean migratory) {
        Random random = new Random();
        Bot bot;

        if (migratory) {
            bot = new MigratoryBot(name ,greeting, random.nextInt(2));
        }
        else {
            bot = new LocalBot(name ,greeting, random.nextInt(2));
        }

        bots.add(bot);

        PropertyChangeEvent sizeChangeEvent = new PropertyChangeEvent(this, "bot number changed", bots.size() - 1, bots.size());
        propertyChangeSupport.firePropertyChange(sizeChangeEvent);

        bot.start();

        //Sleep so that there is no interference on bot startup
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            System.out.println("Interrupted");
        }
    }

    /**
     * Returns the number of active bots
     * @return number of active bots
     */
    public int getActiveBotNumber() {
        return this.bots.size();
    }

    /**
     * Terminates all active bots
     */
    public void killAllBots() {
        for (Bot bot : bots) {
            bot.terminateBot();
        }

        bots.clear();
        PropertyChangeEvent sizeChangeEvent = new PropertyChangeEvent(this, "bot number changed", bots.size() - 1, bots.size());
        propertyChangeSupport.firePropertyChange(sizeChangeEvent);
    }

    /**
     * Adds a property change listener
     * @param listener the listener to be added
     */
    public synchronized void addListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }
}
