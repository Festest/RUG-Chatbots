import Model.MainServer.MainServer;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class TestMainServer {

    @Test
    public void testConstructor() {
        MainServer mainServer = new MainServer();
        //Test that the messageFeed is empty and that there are no chatrooms connected
        assertEquals(0, mainServer.getNumberOfPorts());
        assertEquals(0, mainServer.getFrameMessages().getSize());
    }


}
