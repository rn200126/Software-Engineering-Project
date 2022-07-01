import java.util.ArrayList;

/**
 * The Room class represent a room on the game board, it is used 
 *
 * @author Group31
 * @version V.1
 */
public class Room
{
    
    private String roomName;
    private ArrayList<String> playerInRoom;
    
    /**
     * Initialises room 
     * @param name name of the room
     */
    public Room(String name)
    {
        roomName = name;
        playerInRoom = new ArrayList<String>();
    }

    /**
     * Returns the name of the room 
     * @return room name
     */
    public String getName()
    {
        return roomName;
    }
    
    /**
     * Returns list of players in a room 
     * @return players in the room
     */
    public ArrayList<String> getPlayers()
    {
        return playerInRoom;
    }
    
    /**
     * Removes player from a room
     * @param name name of player 
     */
    public void removePlayer(String name)
      {
       
        
        for (int i = 0; i < playerInRoom.size(); i++) 
        {
            if(playerInRoom.get(i) == name)
            {
                playerInRoom.remove(i);
                return;
            }
        } 
    }
    
    /**
     * Adds player from a room
     * @param name player name
     */
    public void addPlayer(String name)
    {
        
         if(!playerInRoom.contains(name))
        {
            playerInRoom.add(name);
        }
    }
}
