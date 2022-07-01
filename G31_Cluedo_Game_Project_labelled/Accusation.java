import java.util.*;
/**
 * The Accusation class manages cards distribution and hold the group of cards(values) 
 * that is the correct accusation.
 *
 * @author Group31
 * @version V.1
 */
public class Accusation
{
    //Defines List variables needed later on 
    ArrayList<String> playerAlreadyMadeAccusation;
    ArrayList<String> cardsForPlayers;
    ArrayList<String> correctAccusation;
    
     /**
      * Initialises cards for the players
      * Generates the cards for the players
      * Shuffles the cards for the players
      * 
      * @param cardsForPlayers cards for players
      * 
      */
    public Accusation()
    {
       
        playerAlreadyMadeAccusation = new ArrayList<String>();
        
        cardsForPlayers = new ArrayList<String>();
        
        correctAccusation = new ArrayList<String>();
        
        generateWeaponsCards();
        generatePersonCards();
        generateRoomCards();
        
        Collections.shuffle(cardsForPlayers);
        
    }
    
    /**
     * Keeps track of the accusations that have been made
     * @return getPlayerAlreadyMadeAccusationList player has made an accusation already 
     */
     public ArrayList<String> getPlayerAlreadyMadeAccusationList()
    {
        
        return playerAlreadyMadeAccusation;
    }
    
    /**
     * Gives out clue card 
     * @return giveCard gives card
     */
     public String giveOutClueCard()
    {
        if (cardsForPlayers.isEmpty())
        {
            return null;
        }
        
        String giveCard = cardsForPlayers.get(0);
        cardsForPlayers.remove(0);
        return giveCard;
    }
    
    /**
     * Checks whether players have been handed out cards
     * @return boolean 
     */
    public boolean checkHandoutCard()
    {
        
        if(cardsForPlayers.isEmpty())
         {
            return false;
        }
        return true;
    }
    
    /**
     * Checks whether the accusation that has been made is correct and keeps one random card as the correct accusation 
     * @param playerName
     * @param accusationList
     */
      public boolean makeAccusation(String playerName, String[] accusationList)
    {
      
        playerAlreadyMadeAccusation.add(playerName);
        
       if(correctAccusation.contains(accusationList[0]))
        {
            if(correctAccusation.contains(accusationList[1]))
            {
                if(correctAccusation.contains(accusationList[2]))
                {
                    return true;
                }
            }
        }
        
        return false;
    }
    
      /**
       * 
      * Generates a random list of weapon cards and gets added to the card list of each player. 
      * @param cards cards for the weapons
      */
    private void generateWeaponsCards()
    {
        
        Random randomNumberGenerator = new Random();
        String[] aList = new String[6];
        
        aList[0] = "Dagger";
        aList[1] = "Candlestick";
        aList[2] = "Revolver";
        aList[3] = "Rope";
        aList[4] = "Lead Piping";
        aList[5] = "Spanner";
        
        int randomIndex = randomNumberGenerator.nextInt(6);
        
        for(int i = 0; i < 6; i++)
        {
            if(i != randomIndex)
            {
                cardsForPlayers.add(aList[i]);
            }
        }
        
        correctAccusation.add(aList[randomIndex]);
    }
    
     /**
     * Generates a random list of Persons cards and gets inserted into the card list of each player
     * @param cards cards for the persons 
     */
       private void generatePersonCards()
    {
       
        Random randomNumberGenerator = new Random();
        String[] aList = new String[6];
        
        aList[0] = "Col Mustard";
        aList[1] = "Prof Plum";
        aList[2] = "Rev Green";
        aList[3] = "Mrs Peacock";
        aList[4] = "Miss Scarlett";
        aList[5] = "Mrs White";
        
        int randomIndex = randomNumberGenerator.nextInt(6);
        
        for(int i = 0; i < 6; i++)
        {
            if(i != randomIndex)
            {
                cardsForPlayers.add(aList[i]);
            }
        }
        
        correctAccusation.add(aList[randomIndex]);
    }
    
    /**
     * Generated a random list of Room cards and gets inserted into the card list of each player
     * @param cards cards for the room
     */
    private void generateRoomCards()
    {
        
        Random randomNumberGenerator = new Random();
        String[] aList = new String[9];
        
        aList[0] = "Study";
        aList[1] = "Hall";
        aList[2] = "Lounge";
        aList[3] = "Library";
        aList[4] = "Billiard Room";
        aList[5] = "Dining Room";
        aList[6] = "Conservatory";
        aList[7] = "Ball Room";
        aList[8] = "Kitchen";
        
        int randomIndex = randomNumberGenerator.nextInt(9);
        
        for(int i = 0; i < 9; i++)
        {
            if(i != randomIndex)
            {
                cardsForPlayers.add(aList[i]);
            }
        }
        
        correctAccusation.add(aList[randomIndex]);
    }
}
