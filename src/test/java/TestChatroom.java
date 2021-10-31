import Model.Chatrooms.Chatroom;
import Model.Chatrooms.MessageFeed;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestChatroom {

    @Test
    public void testChatroomConstructor() {
        Chatroom chatroom = new Chatroom();

        //Test that the messageFeed is empty
        assertEquals(0, chatroom.getMessageFeed().getSize());
    }

    @Test
    public void testAddIncomingMessage() {
        Chatroom chatroom = new Chatroom();
        assertEquals(0, chatroom.getMessageFeed().getSize());
        String message = "Test Message";
        chatroom.addIncomingMessage(message);
        assertEquals(1, chatroom.getMessageFeed().getSize());
        assertEquals("Test Message", chatroom.getMessageFeed().getMessage(0));
    }

    @Test
    public void testMessageFeedConstructor() {
        MessageFeed messageFeed = new MessageFeed();

        assertEquals(0, messageFeed.getSize());
    }

    @Test
    public void testAddMessage() {
        MessageFeed messageFeed = new MessageFeed();
        assertEquals(0, messageFeed.getSize());
        String message = "Test Message";
        messageFeed.addMessage(message);
        assertEquals(1, messageFeed.getSize());
        assertEquals("Test Message", messageFeed.getMessage(0));
    }

}
