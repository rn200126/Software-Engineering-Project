import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class RoomTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class RoomTest
{
    /**
     * Default constructor for test class RoomTest
     */
    public RoomTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @BeforeEach
    public void setUp()
    {
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @AfterEach
    public void tearDown()
    {
    }
    
    /**
     * Test that a room is correctly initialised (name).
     */
    @Test
    public void testInit()
    {
        Room room1 = new Room("test room");
        assertEquals("test room", room1.getName());
    }
    
    /**
     * Test that a player can be added, and that the number of players is correct afterwards.
     */
    @Test
    public void testAddPlayer()
    {
        Room room1 = new Room("test room");
        room1.addPlayer("test player");
        assertEquals("test player", room1.getPlayers().get(0));
        assertEquals(1, room1.getPlayers().size());
    }
    
        /**
     * Test that a player can be removed, and that the number of players is correct afterwards.
     */
    @Test
    public void testRemovePlayer()
    {
        Room room1 = new Room("test room");
        room1.addPlayer("test player");
        room1.removePlayer("test player");
        assertEquals(0, room1.getPlayers().size());
    }
}


