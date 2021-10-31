package Model.Chatrooms;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Class for the messageFeed, which keeps track of the messages stored
 */
public class MessageFeed implements Serializable {
    private static final long serialVersionUID = 42L;
    private final ArrayList<String> messageFeed;
    private final PropertyChangeSupport changeSupport;

    public MessageFeed() {
        this.messageFeed = new ArrayList<>();
        this.changeSupport = new PropertyChangeSupport(this);
    }

    /**
     * Add a message to the messageFeed, after this happens, a PropertyChangeEvent is fired to update the view with the new message
     * @param message The message to be added
     */
    public synchronized void addMessage(String message) {
        PropertyChangeEvent messageEvent = new PropertyChangeEvent(this, "message", this.messageFeed, message);
        this.messageFeed.add(message);
        changeSupport.firePropertyChange(messageEvent);
    }

    /**
     * Get a message out of the MessageFeed
     * @param index The index of the message to be retrieved
     * @return The message at that index
     */
    public synchronized String getMessage(int index) {
        return this.messageFeed.get(index);
    }

    /**
     * Get the size of the messageFeed
     * @return The size of the messageFeed
     */
    public synchronized int getSize() {
        return messageFeed.size();
    }

    /**
     * Add a listener to the messageFeed
     * @param listener the listener to be added
     */
    public synchronized void addListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }
}
