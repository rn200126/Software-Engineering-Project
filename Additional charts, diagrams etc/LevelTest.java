

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class LevelTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class LevelTest
{
    /**
     * Default constructor for test class LevelTest
     */
    public LevelTest()
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
     * Test that a level object is correctly initialised:
     * has correct number of rows and columns
     * and spaces are correct tagged on the coordinate array.
     */
    @Test
    public void testInit()
    {
        Level level1 = new Level();
        assertEquals(25, level1.boardRowNumbers);
        assertEquals(24, level1.boardColumnNumbers);
        
        assertEquals("Spawn1",level1.coordinateArray[0][16]);
        assertEquals("Study",level1.coordinateArray[0][0]);
        assertEquals("StudyKitchenSecretPassage",level1.coordinateArray[3][0]);
        assertEquals("Exterior",level1.coordinateArray[0][6]);
        assertEquals("DoorStudy",level1.coordinateArray[4][6]);
        assertEquals("Tile",level1.coordinateArray[0][7]);
        assertEquals("S",level1.textArray[1][1]);      
    }
    
    /**
     * Test that player token can be added to token array.
     */
    @Test
    public void testInputTokenCoordinate()
    {
        Level level1 = new Level();
        level1.inputTokenCoordinate("player1",0,7);
        assertEquals("player1", level1.tokenArray[0][7]);   
    }
    
    /**
     * Test that player token can be removed from token array.
     */
    @Test
    public void testRemoveTokenCoordinate()
    {
        Level level1 = new Level();
        level1.inputTokenCoordinate("player1",0,7);
        assertEquals("player1", level1.tokenArray[0][7]); 
        level1.inputTokenCoordinate("player1",1,7);
        assertEquals(null, level1.tokenArray[0][7]); 
    }
    
    /**
     * Test that getBoardRowNumber, getBoardColumnNumber,
     * getCoordinateArrayRowNumbers, getCoordinateArrayColumnNumbers, 
     * getTileTag, getTextArrayTag and getTokennArrayTag are returning correct values.
     */
    @Test
    public void testGetMethods()
    {
        Level level1 = new Level();
        assertEquals(25, level1.getBoardRowNumbers());
        assertEquals(24, level1.getBoardColumnNumbers());
        assertEquals(25, level1.getCoordinateArrayRowNumbers());
        assertEquals(24, level1.getCoordinateArrayColumnNumbers());
        assertEquals("Spawn1",level1.getTileTag(0,16));
        assertEquals("S",level1.getTextArrayTag(1,1)); 
        level1.inputTokenCoordinate("player1",0,7);
        assertEquals("player1", level1.getTokenArrayTag(0,7)); 
    }
}
