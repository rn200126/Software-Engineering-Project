import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

/**
 * The test class PersonTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class PersonTest
{
    /**
     * Default constructor for test class PersonTest
     */
    public PersonTest()
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
     * Test that a person is correctly initialised (name and position).
     */
    @Test
    public void testInit()
    {
        Person person1 = new Person("test person",10,20);
        assertEquals("test person", person1.getName());
        assertEquals(10, person1.getPositionX());
        assertEquals(20, person1.getPositionY());
    }
    
    /**
     * Test that the position of a person can be set
     */
    @Test
    public void testSetTurn()
    {
        Person person1 = new Person("test person",10,20);
        assertEquals(false, person1.getTurn());
        person1.setTurn();
        assertEquals(true, person1.getTurn());
    }
    
    /**
     * Test that the position of a person can be set
     */
    @Test
    public void testSetPosition()
    {
        Person person1 = new Person("test person",10,20);
        person1.setPosition(30,40);
        assertEquals(30, person1.getPositionX());
        assertEquals(40, person1.getPositionY());
    }
    
    /**
     * Test that the notes of a person can be set
     */
    @Test
    public void testSetNotes()
    {
        Person person1 = new Person("test person",10,20);
        String[] aNote = {"note1","note2","note3"};
        person1.setNotes(aNote);
        assertEquals("note1", person1.getNotes()[0]);
        assertEquals("note2", person1.getNotes()[1]);
        assertEquals("note3", person1.getNotes()[2]);
        assertEquals(3, person1.getNotes().length);
    }
    
    /**
     * Test that the room of a person can be set
     */
    @Test
    public void testSetRoom()
    {
        Person person1 = new Person("test person",10,20);
        person1.setRoom("test room");
        assertEquals("test room", person1.room);
    }
    
    /**
     * Test that card can be added to card list
     */
    @Test
    public void testAddRoom()
    {
        Person person1 = new Person("test person",10,20);
        person1.addCard("test card");
        assertEquals("test card", person1.getCards().get(0));
        assertEquals(1, person1.getCards().size());
    }
    
    /**
     * Test that same cards will be returned
     */
    @Test
    public void testCompareCards()
    {
        Person person1 = new Person("test person",10,20);
        person1.addCard("test card");
        ArrayList<String> checkCards = new ArrayList<String>();
        checkCards.add("test card");
        assertEquals("test card", person1.compareCards(checkCards).get(0));
        assertEquals(1, person1.compareCards(checkCards).size());
    }
    
        /**
     * Test that same cards will be returned
     */
    @Test
    public void testMakeAccusation()
    {
        Person person1 = new Person("test person",10,20);
        String[] aNote = {"note1","note2","note3"};
        person1.setNotes(aNote);
        person1.makeAccusation();
        assertEquals("note1", person1.makeAccusation()[0]);
        assertEquals("note2", person1.makeAccusation()[1]);
        assertEquals("note3", person1.makeAccusation()[2]);
        assertEquals(true, person1.accusationMade);
    }
}
