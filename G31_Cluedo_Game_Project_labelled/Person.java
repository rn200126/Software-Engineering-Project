import java.util.ArrayList;

/**
 * The Person class represent one player in the game and all details that needed 
 * to be store to a specific player.
 *
 * @author Group31
 * @version V.1
 */

public class Person
{
    //Defines variables needed later on 
     String personName;
    int positionX;
    int positionY;
    ArrayList<String> cardsList;
    String room;
    boolean accusationMade;
    boolean turn;
  
     /**
     * Initialises Person with their position, coordinates, card list and no accusation or turns on their name
     * @param name person name
     */
    public Person(String name, int posX, int posY){
        personName = name;
        positionX = posX;
        positionY = posY;
        cardsList = new ArrayList<String>();
        room = null; 
        accusationMade = false;
        turn = false;
    }
    
    /**
     * Returns turn
     * @return turn 
     */
    public boolean getTurn()
    {
        
        return turn;
    }
    
    /**
     * Checks whther it is the person's turn 
     * @param turn turns for players
     */
    public void setTurn()
    {
        
        if(turn == true)
        {
            turn = false;
        }
        else
        {
            turn = true;
        }
    }
    
    /**
     * Assigns positions 
     * @param posX position x 
     * @param posY position y
     */
    public void setPosition(int posX, int posY)
    {
        
        positionX = posX;
        positionY = posY;
    }
    
    /**
     * Assigns room 
     * @param aRoom a room 
     */
     public void setRoom(String aRoom)
    {
        room = aRoom;
    }
    
    /**
     * Gets room 
     * @return room rooms in the game 
     */
     public String getRoom()
    {
         return room;
    }
    
    /**
     * Adds cards to the list of the player's cards 
     * @param aCard card list
     */
    public void addCard(String aCard)
    {
       
        cardsList.add(aCard);
    }
    
    /**
     * Returns the list of cards
     * 
     * @return cardsList list of cards
     */
     public ArrayList<String> getCards()
    {
      
        return cardsList;
    }
    
    /**
     * Returns person's name 
     * @return personName name of the person 
     */
    public String getName()
    {
      
        return personName;
    }
    
    /**
     * Return position in coordinate X 
     * @param positionX posx
     * 
     */
      public int getPositionX()
    {
      
        return positionX;
    }
    
    /**
     * Returns position in coordinate Y
     * @return position y posy
     */
    public int getPositionY()
     {
       
        return positionY;
    }
}






