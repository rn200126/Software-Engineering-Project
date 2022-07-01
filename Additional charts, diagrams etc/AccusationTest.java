

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class AccusationTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class AccusationTest
{
    /**
     * Default constructor for test class AccusationTest
     */
    public AccusationTest()
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
     * Test that an accusation object is correctly initialised:
     * the cards are correctly generated,
     * correctAccusation list(murder envelope) contains 3 cards,
     * cardsForPlayers list contains 18 cards to be handed out to players
     * and no player has made any accusation.
     */
    @Test
    public void testInit()
    {
        Accusation accusation1 = new Accusation();
        assertEquals(3, accusation1.correctAccusation.size());
        assertEquals(18, accusation1.cardsForPlayers.size());
        assertEquals(0, accusation1.getPlayerAlreadyMadeAccusationList().size());
    }
    
    /**
     * Test that a card can be give out to a player and removed from cardsForPlayers list.
     */
    @Test
    public void testGiveOutClueCard(){
        Accusation accusation1 = new Accusation();
        assertEquals(18, accusation1.cardsForPlayers.size());
        accusation1.giveOutClueCard();
        assertEquals(17, accusation1.cardsForPlayers.size());
    }
    
    /**
     * Test that checkHandoutCard method will return false if cardsForPlayers list is empty and true elsewise.
     */
    @Test
    public void testCheckHandoutCard(){
        Accusation accusation1 = new Accusation();
        assertEquals(18, accusation1.cardsForPlayers.size());
        assertEquals(true, accusation1.checkHandoutCard());
        for(int i=0;i<18;i++){
            accusation1.giveOutClueCard();
        }
        assertEquals(0, accusation1.cardsForPlayers.size());
        assertEquals(false, accusation1.checkHandoutCard());
    }
    
    /**
     * Test that makeAccusation method will return true if the accusation is correct and false elsewise,
     * and will add name of the player who made the accusation to playerAlreadyMadeAccusation list.
     */
    @Test
    public void testMakeAccusation(){
        Accusation accusation1 = new Accusation();
        assertEquals(0, accusation1.getPlayerAlreadyMadeAccusationList().size());
        String[] accusationList1 = {"","",""};
        assertEquals(false,accusation1.makeAccusation("player1",accusationList1));
        assertEquals(1, accusation1.getPlayerAlreadyMadeAccusationList().size());
        assertEquals("player1", accusation1.getPlayerAlreadyMadeAccusationList().get(0));
        
        String[] accusationList2 = new String[3];
        for(int i=0;i<3;i++){
            accusationList2[i] = accusation1.correctAccusation.get(i);
        }
        assertEquals(true,accusation1.makeAccusation("player2",accusationList2));
        assertEquals(2, accusation1.getPlayerAlreadyMadeAccusationList().size());
        assertEquals("player2", accusation1.getPlayerAlreadyMadeAccusationList().get(1));
    }
}
