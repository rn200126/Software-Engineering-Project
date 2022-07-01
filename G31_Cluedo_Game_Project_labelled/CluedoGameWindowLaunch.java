import javax.swing.*;
import java.awt.*;
import javax.swing.plaf.basic.BasicArrowButton;
import java.awt.event.*;
import java.util.*;
/**
 * Main launcher class for the program. Handle all graphical elements and putting
 * all the classes together to form a working game.
 *
 * @author Group31
 * @version V.1
 */
public class CluedoGameWindowLaunch
{
    //Variables 
    Level cluedoGameLevel;
    JFrame cluedoGameWindowInstance;
    JPanel mainJPanel;
    JPanel inputField;
    JPanel gameBoard;
    String currentTurn;
    JLabel gameCurrentPlayer;
    
    JComboBox roomSelect;
    JComboBox weaponSelect;
    JComboBox personSelect;
    JFrame cardViewWindow;
    
    int currentAvailableMovement;
    boolean noMoreRoll;
    boolean suggestionAlreadyMade;
    boolean cardView;
    JButton viewMyCardButton;
    JButton diceRollButton;
    
    JButton endTurnButton;
    
    ArrayList<int[]> spawnPoints;
    ArrayList<Person> players;
    ArrayList<Room> rooms;
    Accusation accusationOpen;
    
    /**
     * Starts the game 
     * @param startGame starts game 
     */
    public static void main(String[] args)
    {
        //Starts the game
        CluedoGameWindowLaunch startGame = new CluedoGameWindowLaunch();
    }
    
    /**
     * Initialises Level and Board, room with boards, all person, weapons, rooms objects
     * Murder Envelope initialised, with random Person, Weapon, Room 
     * Remaining "cards" (representing players, rooms, weapons) are all assigned to players until no more cards left
     * Random player chosen as first turn, players take turns clockwise(predetermined order
     * @param currentTurn current run 
     * @param players players 
     * @param rooms rooms 
     */
    public CluedoGameWindowLaunch()
    {
        //Initialise Level and Board
        //Initialise rooms with boards
        //Initialise all Person objects, with relevant names and spawn positions (pieces not played are placed in random rooms)
        //Initialise weapons, and place in different rooms
        
        currentTurn = "Miss Scarlett";
        
        currentAvailableMovement = 0;
        
        noMoreRoll = false;
        
        suggestionAlreadyMade = false;
        
        players = new ArrayList<Person>();
        
        rooms = new ArrayList<Room>();
        
        CluedoGameWindow(currentTurn);
        
        accusationOpen = new Accusation();
        
        makeRooms();
        
        makePlayers();
        
        handOutCards();
        
        for (int i = 0; i < players.size(); i++) 
        {
            if(players.get(i).getName() == "Miss Scarlett")
            {
                players.get(i).setTurn();
            }
        }
    }
    
    /**
     * Rolls the dice and warns player whether it can be used or not 
     * @param dice dice in the game 
     */
    public void rollDices()
    {
        
        if(noMoreRoll == false)
        {
            Random roll = new Random();
            int firstRoll = roll.nextInt(6) + 1;
            int secondRoll = roll.nextInt(6) + 1;
            currentAvailableMovement = firstRoll + secondRoll;
            diceRollButton.setText("" + currentAvailableMovement);
            noMoreRoll = true;
        }
    }
    
    /**
     * Checks who the current player in the current turn is in order to update and change to the next player  
     * @param turn 
     */
    public void nextTurn()
    {
        
        for (int i = 0; i < players.size(); i++) 
        {
            if(players.get(i).getName() == currentTurn)
            {
                players.get(i).setTurn();
                i++;
                if(i >= players.size())
                {
                    i = 0;
                    players.get(i).setTurn();
                    currentTurn = players.get(i).getName();
                    if(players.get(i).getRoom() != null)
                    {
                        roomExitSelection();
                    }
                }
                else
                {
                    players.get(i).setTurn();
                    currentTurn = players.get(i).getName();
                    if(players.get(i).getRoom() != null)
                    {
                        roomExitSelection();
                    }
                }
                suggestionAlreadyMade = false;
                noMoreRoll = false;
                diceRollButton.setText("Roll");
                repaintInputField();
                cardView = true;
                viewMyCards();
                refreshWindow();
                return;
            }
        }
    }

    /**
     * Generates and adds players to the game 
     * @param players players 
     */
    private void makePlayers()
    {
        
        
        generateSpawnPoints();
        
        Person colMustard = new Person("Col Mustard", spawnPoints.get(0)[0], spawnPoints.get(0)[1]);
        
        Person profPlum = new Person("Prof Plum", spawnPoints.get(1)[0], spawnPoints.get(1)[1]); 
        
        Person revGreen = new Person("Rev Green", spawnPoints.get(2)[0], spawnPoints.get(2)[1]);
        
        Person mrsPeacock = new Person("Mrs Peacock", spawnPoints.get(3)[0], spawnPoints.get(3)[1]);
        
        Person missScarlett = new Person("Miss Scarlett", spawnPoints.get(4)[0], spawnPoints.get(4)[1]);
        
        Person mrsWhite = new Person("Mrs White", spawnPoints.get(5)[0], spawnPoints.get(5)[1]);
        
        players.add(colMustard);
        players.add(profPlum);
        players.add(revGreen);
        players.add(mrsPeacock);
        players.add(missScarlett);
        players.add(mrsWhite);
        
        Collections.shuffle(players);
        
        updatePlayerGrid();
    }
    
    /**
     * Updates the grid by getting the name, position of players
     * @param players players 
     */
    public void updatePlayerGrid()
    {
        
        for (int i = 0; i < players.size(); i++) 
        {
            cluedoGameLevel.inputTokenCoordinate(players.get(i).getName(), players.get(i).getPositionX(), players.get(i).getPositionY());
        }
        repaintGameBoardButtonGrid();
        refreshWindow();
    }
    
    /**
     * Generates spawn points 
     * @param spawnPoints spawn points
     */
    private void generateSpawnPoints()
    {
        
        spawnPoints = new ArrayList<int[]>();
        spawnPoints.add(new int[] {0,16});
        spawnPoints.add(new int[] {7,23});
        spawnPoints.add(new int[] {24,14});
        spawnPoints.add(new int[] {24,9});
        spawnPoints.add(new int[] {18,0});
        spawnPoints.add(new int[] {5,0});
        
        Collections.shuffle(spawnPoints);
    }

    /**
     * Generates and adds all rooms
     * @param rooms rooms 
     */
    public void makeRooms()
    {
        
        Room study = new Room("Study");
        Room hall = new Room("Hall");
        Room lounge = new Room("Lounge");
        Room library = new Room("Library");
        Room dining = new Room("Dining Room");
        Room billiard = new Room("Billiard");
        Room conservatory = new Room("Conservatory");
        Room ball = new Room("Ball Room");
        Room kitchen = new Room("Kitchen");
        
        rooms.add(study);
        rooms.add(hall);
        rooms.add(lounge);
        rooms.add(library);
        rooms.add(billiard );
        rooms.add(dining);
        rooms.add(conservatory);
        rooms.add(ball);
        rooms.add(kitchen);
    }
    
    /**
     * Keeps in check the movements of each player in the grid and lets players enter rooms through the door
     * @param name name 
     */
    public boolean playerMovementChecker(String name, int xMod, int yMod)
    {
        
        for (int i = 0; i < players.size(); i++)
        {
            if(players.get(i).getName() == name)
            {
               int x = players.get(i).getPositionX() + xMod;
               int y = players.get(i).getPositionY() + yMod;
               if(x < 0)
               {
                   return false;
               }
               if(y < 0)
               {
                   return false;
               }
               if(x >= cluedoGameLevel.getBoardRowNumbers())
               {
                   return false;
               }
               if(y >= cluedoGameLevel.getBoardColumnNumbers())
               {
                   return false;
               }
               if(currentAvailableMovement <= 0)
               {
                   return false;
               }
               if(cluedoGameLevel.getTokenArrayTag(x, y) != null)
               {
                   return false;
               }
               if(cluedoGameLevel.getTileTag(x, y) == "Tile")
               {
                   players.get(i).setPosition(x, y);
                   currentAvailableMovement--;
                   diceRollButton.setText("" + currentAvailableMovement);
                   updatePlayerGrid();
                   return true;
               }
               if(cluedoGameLevel.getTileTag(x, y).contains("Door"))
               {
                   players.get(i).setPosition(x, y);
                   currentAvailableMovement--;
                   diceRollButton.setText("" + currentAvailableMovement);
                   updatePlayerGrid();
                   return true;
               }
               if(cluedoGameLevel.getTileTag(x, y).contains("Spawn"))
               {
                   players.get(i).setPosition(x, y);
                   currentAvailableMovement--;
                   diceRollButton.setText("" + currentAvailableMovement);
                   updatePlayerGrid();
                   return true;
               }
               if(cluedoGameLevel.getTileTag(x, y).contains("Entrance"))
               {
                   String rID = getRoomTagFromDoor(cluedoGameLevel.getTileTag(players.get(i).getPositionX(), players.get(i).getPositionY()));
                   for(int j = 0; j < rooms.size(); j++)
                   {
                       if(rooms.get(j).getName() == rID)
                       {
                           rooms.get(j).addPlayer(name);
                           players.get(i).setRoom(rooms.get(j).getName());
                           roomSlotAssign(j);
                       }
                   }
                   currentAvailableMovement = 0;
                   diceRollButton.setText("" + currentAvailableMovement);
                   updatePlayerGrid();
                   return true;
               }
            }
        }
        return false;
    }
    
    /**
     * Lets the player move up
     * @param currentTurn current turn 
     */
    public void playerMoveUp()
    {
        
        playerMovementChecker(currentTurn, -1, 0);
    }
    
    /**
     * 
     * Lets the player move down
     * @param currentTurn current turn
     */
    public void playerMoveDown()
    {
        
        playerMovementChecker(currentTurn, +1, 0);
    }
    
    /**
     * Lets the player move left
     * @param currentTurn current turn
     */
     public void playerMoveLeft()
    {
        
        playerMovementChecker(currentTurn, 0, -1);
    }
    
    /**
     * Lets the player move right 
     * @param currentTurn current turn
     */
    public void playerMoveRight()
    {
        
        playerMovementChecker(currentTurn, 0, +1);
    }
    
    /**
     * Keeps check of which player occupies which room 
     * @param occupantName name of person in the room 
     */
    public void roomSlotAssign(int roomIndex)
    {
        
        String roomName = rooms.get(roomIndex).getName();
        ArrayList<String> roomOccupants = rooms.get(roomIndex).getPlayers();
        if(roomName == "Study")
        {
            for(int i = 0; i < roomOccupants.size(); i++)
            {
                String occupantName = roomOccupants.get(i);
                if(i == 0)
                {
                    movePlayer(occupantName, 2, 0);
                }
                if(i == 1)
                {
                    movePlayer(occupantName, 2, 1);
                }
                if(i == 2)
                {
                    movePlayer(occupantName, 2, 2);
                }
                if(i == 3)
                {
                    movePlayer(occupantName, 2, 3);
                }
                if(i == 4)
                {
                    movePlayer(occupantName, 2, 4);
                }
                if(i == 5)
                {
                    movePlayer(occupantName, 2, 5);
                }
            }
        }
        if(roomName == "Hall")
        {
            for(int i = 0; i < roomOccupants.size(); i++)
            {
                String occupantName = roomOccupants.get(i);
                if(i == 0)
                {
                    movePlayer(occupantName, 3, 10);
                }
                if(i == 1)
                {
                    movePlayer(occupantName, 3, 11);
                }
                if(i == 2)
                {
                    movePlayer(occupantName, 3, 12);
                }
                if(i == 3)
                {
                    movePlayer(occupantName, 3, 13);
                }
                if(i == 4)
                {
                    movePlayer(occupantName, 4, 11);
                }
                if(i == 5)
                {
                    movePlayer(occupantName, 4, 12);
                }
            }
        }        
        if(roomName == "Lounge")
        {
            for(int i = 0; i < roomOccupants.size(); i++)
            {
                String occupantName = roomOccupants.get(i);
                if(i == 0)
                {
                    movePlayer(occupantName, 3, 19);
                }
                if(i == 1)
                {
                    movePlayer(occupantName, 3, 20);
                }
                if(i == 2)
                {
                    movePlayer(occupantName, 3, 21);
                }
                if(i == 3)
                {
                    movePlayer(occupantName, 4, 19);
                }
                if(i == 4)
                {
                    movePlayer(occupantName, 4, 20);
                }
                if(i == 5)
                {
                    movePlayer(occupantName, 4, 21);
                }
            }
        }
        if(roomName == "Library")
        {
            for(int i = 0; i < roomOccupants.size(); i++)
            {
                String occupantName = roomOccupants.get(i);
                if(i == 0)
                {
                    movePlayer(occupantName, 8, 2);
                }
                if(i == 1)
                {
                    movePlayer(occupantName, 8, 3);
                }
                if(i == 2)
                {
                    movePlayer(occupantName, 8, 4);
                }
                if(i == 3)
                {
                    movePlayer(occupantName, 9, 2);
                }
                if(i == 4)
                {
                    movePlayer(occupantName, 9, 3);
                }
                if(i == 5)
                {
                    movePlayer(occupantName, 9, 4);
                }
            }
        }
        if(roomName == "Billiard Room")
        {
            for(int i = 0; i < roomOccupants.size(); i++)
            {
                String occupantName = roomOccupants.get(i);
                if(i == 0)
                {
                    movePlayer(occupantName, 14, 1);
                }
                if(i == 1)
                {
                    movePlayer(occupantName, 14, 4);
                }
                if(i == 2)
                {
                    movePlayer(occupantName, 15, 1);
                }
                if(i == 3)
                {
                    movePlayer(occupantName, 15, 2);
                }
                if(i == 4)
                {
                    movePlayer(occupantName, 15, 3);
                }
                if(i == 5)
                {
                    movePlayer(occupantName, 15, 4);
                }
            }
        }
        if(roomName == "Dining Room")
        {
            for(int i = 0; i < roomOccupants.size(); i++)
            {
                String occupantName = roomOccupants.get(i);
                if(i == 0)
                {
                    movePlayer(occupantName, 13, 17);
                }
                if(i == 1)
                {
                    movePlayer(occupantName, 13, 18);
                }
                if(i == 2)
                {
                    movePlayer(occupantName, 13, 19);
                }
                if(i == 3)
                {
                    movePlayer(occupantName, 13, 20);
                }
                if(i == 4)
                {
                    movePlayer(occupantName, 13, 21);
                }
                if(i == 5)
                {
                    movePlayer(occupantName, 13, 22);
                }
            }
        }
        if(roomName == "Conservatory")
        {
            for(int i = 0; i < roomOccupants.size(); i++)
            {
                String occupantName = roomOccupants.get(i);
                if(i == 0)
                {
                    movePlayer(occupantName, 23, 0);
                }
                if(i == 1)
                {
                    movePlayer(occupantName, 23, 1);
                }
                if(i == 2)
                {
                    movePlayer(occupantName, 23, 2);
                }
                if(i == 3)
                {
                    movePlayer(occupantName, 23, 3);
                }
                if(i == 4)
                {
                    movePlayer(occupantName, 23, 4);
                }
                if(i == 5)
                {
                    movePlayer(occupantName, 23, 5);
                }
            }
        }
        if(roomName == "Ball Room")
        {
            for(int i = 0; i < roomOccupants.size(); i++)
            {
                String occupantName = roomOccupants.get(i);
                if(i == 0)
                {
                    movePlayer(occupantName, 20, 9);
                }
                if(i == 1)
                {
                    movePlayer(occupantName, 20, 10);
                }
                if(i == 2)
                {
                    movePlayer(occupantName, 20, 11);
                }
                if(i == 3)
                {
                    movePlayer(occupantName, 20, 12);
                }
                if(i == 4)
                {
                    movePlayer(occupantName, 20, 13);
                }
                if(i == 5)
                {
                    movePlayer(occupantName, 20, 14);
                }
            }
        }
        if(roomName == "Kitchen")
        {
            for(int i = 0; i < roomOccupants.size(); i++)
            {
                String occupantName = roomOccupants.get(i);
                if(i == 0)
                {
                    movePlayer(occupantName, 21, 19);
                }
                if(i == 1)
                {
                    movePlayer(occupantName, 21, 20);
                }
                if(i == 2)
                {
                    movePlayer(occupantName, 21, 21);
                }
                if(i == 3)
                {
                    movePlayer(occupantName, 22, 19);
                }
                if(i == 4)
                {
                    movePlayer(occupantName, 22, 20);
                }
                if(i == 5)
                {
                    movePlayer(occupantName, 22, 21);
                }
            }
        }
    }
    
    /**
     * 
     * Checks which room it is based on the door id 
     * @param doorID  id of the door 
     */
    public String getRoomTagFromDoor(String doorID)
    {
        
        
        if(doorID.contains("Study"))
         {
            return "Study";
        }
         if(doorID.contains("Hall"))
        {
            return "Hall";
        }
        if(doorID.contains("Lounge"))
        {
            return "Lounge";
        }
        if(doorID.contains("Library"))
        {
            return "Library";
        }
        if(doorID.contains("Billiard"))
        {
            return "Billiard Room";
        }
        if(doorID.contains("Dining"))
        {
            return "Dining Room";
        }
        if(doorID.contains("Conservatory"))
        {
            return "Conservatory";
        }
        if(doorID.contains("Ball"))
        {
            return "Ball Room";
        }
        if(doorID.contains("Kitchen"))
        {
            return "Kitchen";
        }
        return null;
    }
    
    /**
     * 
     * Sets position of the player to the given position  
     * @param playerName name of the player 
     */
    public void movePlayer(String playerName, int x, int y)
    {
        
         for (int i = 0; i < players.size(); i++) 
        {
            if(players.get(i).getName() == playerName)
            {
                players.get(i).setPosition(x,y);
            }
        }
        updatePlayerGrid();
        repaintGameBoardButtonGrid();
    }
    
    /**
     * Distributes the cards to the players 
     * @param giveOutClueCard clue card 
     */
    public void handOutCards()
    {
        
        int i = 0;
         while(accusationOpen.checkHandoutCard() == true)
         {
            players.get(i).addCard(accusationOpen.giveOutClueCard());
            if(i == 5)
            {
                i = 0;
            }
            else
            {
                i++;
            }
        }
    }
    
    // BEGINNING OF INTERFACE SECTION
    
    public void CluedoGameWindow(String currentTurnInput)
    {
        //Initialises board interface 
        cluedoGameLevel = new Level();
        
        int levelRow = cluedoGameLevel.getCoordinateArrayRowNumbers();
        int levelColumn = cluedoGameLevel.getCoordinateArrayColumnNumbers();
        
        //Window instance
        cluedoGameWindowInstance = new JFrame();
        
        //Main JPanel
        mainJPanel = new JPanel();
        mainJPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        mainJPanel.setPreferredSize(new Dimension(1070,770));
        
        cluedoGameWindowInstance.add(mainJPanel);
        
        //External Input
        currentTurn = currentTurnInput;
        
        //Game board
        gameBoard = new JPanel();
        gameBoard.setBorder(BorderFactory.createEmptyBorder());
        gameBoard.setLayout(new GridLayout(levelRow,levelColumn, 0, 0));
        gameBoard.setPreferredSize(new Dimension(770,770));
        gameBoard.setBackground(new Color(169, 227, 141));
        mainJPanel.add(gameBoard);
        
        // Game board setup
        generateGameButtonGrid(gameBoard, levelRow, levelColumn); 
        
        // input field
        inputField = new JPanel();
        inputField.setLayout(new BoxLayout(inputField, BoxLayout.Y_AXIS));
        inputField.setBackground(new Color(207, 102, 37));
        inputField.setPreferredSize(new Dimension(300,770));
        mainJPanel.add(inputField);
        
        //Input field setup
        inputFieldContent();
        
        //Final game window setup
        cluedoGameWindowInstance.setVisible(true);
        cluedoGameWindowInstance.pack();
        cluedoGameWindowInstance.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }
    
    /**
     * Refreshes the window
     * @param cluedoGameWindowInstance instance window 
     */
    public void refreshWindow()
    {
        
        cluedoGameWindowInstance.revalidate();
    }
    
    /**
     * Keeps track of who the current player is 
     * @param name name 
     */
    public void updateCurrentPlayer(String name)
    {
        
        currentTurn = name;
    }
    
    /**
     * 
     * Iterface colour for input field
     * @param gameCurrentPlayer current player game 
     */
    public void repaintInputField()
    {
        gameCurrentPlayer.setBackground(playerTilePaint(currentTurn));
        gameCurrentPlayer.setText(currentTurn);
        gameCurrentPlayer.validate();
    }
    
    /**
     * Interface for the board button 
     * @param gameBoard game baord 
     */
    public void repaintGameBoardButtonGrid()
    {
        
        int r = cluedoGameLevel.getCoordinateArrayRowNumbers();
        int c = cluedoGameLevel.getCoordinateArrayColumnNumbers();
        gameBoard.removeAll();
        for (int x = 0; x < r; x++)
         {
            for (int y = 0; y < c; y++)
            {
                JButton cluedoLevelTile = new JButton();
                cluedoLevelTile.setMargin(new Insets(0, 0, 0, 0));
                cluedoLevelTile.setBackground(tileTypePainter(cluedoGameLevel.getTileTag(x,y)));
                cluedoLevelTile.setOpaque(true);
                if(cluedoGameLevel.getTileTag(x,y) == "Exterior")
                {
                    cluedoLevelTile.setVisible(false);
                }
                if(cluedoGameLevel.getTextArrayTag(x,y) != null)
                {
                    cluedoLevelTile.setText(cluedoGameLevel.getTextArrayTag(x,y));
                    cluedoLevelTile.setBackground(Color.WHITE);
                }
                if(cluedoGameLevel.getTileTag(x,y) != "Tile")
                {
                    cluedoLevelTile.setBorderPainted(false);
                }
                if(cluedoGameLevel.getTileTag(x,y).contains("Door"))
                {
                    cluedoLevelTile.setBorderPainted(true);
                }
                if(cluedoGameLevel.getTileTag(x,y).contains("Spawn"))
                {
                    cluedoLevelTile.setBorderPainted(true);
                }
                if(cluedoGameLevel.getTokenArrayTag(x,y) != null)
                {
                    cluedoLevelTile.setText(playerTileName(cluedoGameLevel.getTokenArrayTag(x,y)));
                    cluedoLevelTile.setBackground(playerTilePaint(cluedoGameLevel.getTokenArrayTag(x,y)));
                }
                gameBoard.add(cluedoLevelTile);
            }
        }
        cluedoGameWindowInstance.revalidate();
    }
    
    /**
    * Generates the Interface for the game button grid 
    * @param aPanel panel 
    */
    public void generateGameButtonGrid(JPanel aPanel, int r, int c)
    {
        
        for (int x = 0; x < r; x++)
        {
            for (int y = 0; y < c; y++)
            {
                JButton cluedoLevelTile = new JButton();
                cluedoLevelTile.setMargin(new Insets(0, 0, 0, 0));
                cluedoLevelTile.setBackground(tileTypePainter(cluedoGameLevel.getTileTag(x,y)));
                cluedoLevelTile.setOpaque(true);
                if(cluedoGameLevel.getTileTag(x,y) == "Exterior")
                {
                    cluedoLevelTile.setVisible(false);
                }
                if(cluedoGameLevel.getTextArrayTag(x,y) != null)
                {
                    cluedoLevelTile.setText(cluedoGameLevel.getTextArrayTag(x,y));
                    cluedoLevelTile.setBackground(Color.WHITE);
                }
                if(cluedoGameLevel.getTileTag(x,y) != "Tile")
                {
                    cluedoLevelTile.setBorderPainted(false);
                }
                if(cluedoGameLevel.getTileTag(x,y).contains("Door"))
                {
                    cluedoLevelTile.setBorderPainted(true);
                }
                if(cluedoGameLevel.getTileTag(x,y).contains("Spawn"))
                {
                    cluedoLevelTile.setBorderPainted(true);
                }
                if(cluedoGameLevel.getTokenArrayTag(x,y) != null)
                {
                    cluedoLevelTile.setText(playerTileName(cluedoGameLevel.getTokenArrayTag(x,y)));
                    cluedoLevelTile.setBackground(playerTilePaint(cluedoGameLevel.getTokenArrayTag(x,y)));
                }
                aPanel.add(cluedoLevelTile);
            }
        }
    }
    
    /**
     * Accusation and suggestion interface 
     * Weapon person and room selection menu 
     * @param accuseButton button to accuse
     * @param weaponSelectionList list to select weapons 
     * @param personSelectionList list to select persons 
     * @param roomselectionList list to select rooms 
     */
    private void inputFieldContent()
    {
        //Title Label
        JPanel titlePanel = new JPanel(new GridLayout(1,1,0,0));
        
        JLabel gameTitleLabel = new JLabel("Clue Digital Edition 31");
        gameTitleLabel.setBackground(new Color(115, 189, 125));
        gameTitleLabel.setPreferredSize(new Dimension(250,30));
        gameTitleLabel.setHorizontalAlignment(JLabel.CENTER);
        gameTitleLabel.setOpaque(true);
        
        titlePanel.add(gameTitleLabel);
        inputField.add(titlePanel);
        
        //Current Player Label
        JPanel currentPlayerPanel = new JPanel(new GridLayout(1,1,0,0));
        
        gameCurrentPlayer = new JLabel(currentTurn);
        gameCurrentPlayer.setBackground(playerTilePaint(currentTurn));
        gameCurrentPlayer.setPreferredSize(new Dimension(250,30));
        gameCurrentPlayer.setHorizontalAlignment(JLabel.CENTER);
        gameCurrentPlayer.setOpaque(true);
        
        currentPlayerPanel.add(gameCurrentPlayer);
        inputField.add(currentPlayerPanel);
        
        //Accuse and Suggest button
        JPanel asBox = new JPanel(new GridLayout(1,1, 0, 0));
        //Accuse button
        JButton accuseButton = new JButton("Accuse");
        accuseButton.setBackground(new Color(255, 117, 107));
        accuseButton.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                makeAccusation();
            }
        }
        );
        asBox.add(accuseButton);
        //Suggest button 
        JButton suggestButton = new JButton("Suggest");
        suggestButton.setBackground(new Color(243, 255, 107));
        suggestButton.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String suggestedWeapon = (String) weaponSelect.getSelectedItem();
                String suggestedPerson = (String) personSelect.getSelectedItem();
                
                makeSuggestion(suggestedWeapon, suggestedPerson);
            }
        }
        );
        asBox.add(suggestButton);
        
        inputField.add(asBox);
        
        //Weapon slection menu
        JPanel wsmBox =  new JPanel();
        
        JLabel wsmLabel = new JLabel("Select weapon to suggest");
        wsmBox.add(wsmLabel);
        
        String[] weaponSelectionList = {"Dagger","Candlestick","Revolver","Rope","Lead Piping","Spanner"};
        weaponSelect = new JComboBox(weaponSelectionList);
        wsmBox.add(weaponSelect);
        
        inputField.add(wsmBox);
        
        //Person selection menu
        JPanel psmBox =  new JPanel();
        
        JLabel psmLabel = new JLabel("Select person to suggest");
        psmBox.add(psmLabel);
        
        String[] personSelectionList = {"Col Mustard","Prof Plum","Rev Green","Mrs Peacock","Miss Scarlett","Mrs White"};
        personSelect = new JComboBox(personSelectionList);
        psmBox.add(personSelect);
        
        inputField.add(psmBox);
        
        //Room Exit menu
        JPanel rsmBox =  new JPanel();
        
        JLabel rsmLabel = new JLabel("Select Exit");
        rsmBox.add(rsmLabel);
        
        roomSelect = new JComboBox();
        rsmBox.add(roomSelect);
        
        JButton exitConfirm = new JButton("Confirm Exit");
        exitConfirm.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String selectedExit = (String) roomSelect.getSelectedItem();
                roomExitProtocol(selectedExit);
                roomSelect.removeAllItems();
            }
        }
        );
        rsmBox.add(exitConfirm);
        
        inputField.add(rsmBox);

        //End turn button;
        JPanel endTurnPanel = new JPanel(new GridLayout(1,1,0,0));
        endTurnButton = new JButton("End Turn");
        endTurnPanel.add(endTurnButton);
        endTurnButton.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                nextTurn();
            }
        }
        );
        inputField.add(endTurnPanel);
        
        //Movement panel
        JPanel movementPanel = new JPanel(new GridLayout(3,3,0,0));
        
        JButton tlb =  new JButton();
        tlb.setVisible(false);
        movementPanel.add(tlb);
        
        //Button to move up 
        BasicArrowButton upMove = new BasicArrowButton(BasicArrowButton.NORTH);
        movementPanel.add(upMove);
        upMove.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                playerMoveUp();
            }
        }
        );
        
        JButton trb =  new JButton();
        trb.setVisible(false);
        movementPanel.add(trb);
        //Button to move left 
        BasicArrowButton leftMove = new BasicArrowButton(BasicArrowButton.WEST);
        movementPanel.add(leftMove);
        leftMove.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                playerMoveLeft();
            }
        }
        );
        //Dice button 
        diceRollButton = new JButton("Roll");
        movementPanel.add(diceRollButton);
        diceRollButton.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                rollDices();
            }
        }
        );
        //Button to move right 
        BasicArrowButton rightMove = new BasicArrowButton(BasicArrowButton.EAST);
        movementPanel.add(rightMove);
        rightMove.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                playerMoveRight();
            }
        }
        );
        //Button to view cards
        viewMyCardButton =  new JButton("My Cards");
        movementPanel.add(viewMyCardButton);
        viewMyCardButton.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                viewMyCards();
            }
        }
        );
        //Button to move down 
        BasicArrowButton downMove = new BasicArrowButton(BasicArrowButton.SOUTH);
        movementPanel.add(downMove);
        downMove.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                playerMoveDown();
            }
        }
        );
        
        JButton brb =  new JButton();
        brb.setVisible(false);
        movementPanel.add(brb);
        
        inputField.add(movementPanel);
    }
    
    /**
     * 
     * Interface to view cards  
     * @param cardView view card 
     */
    public void viewMyCards()
    {
      
        if(cardView == false)
        {
            cardView = true;
            ArrayList<String> cardHandArray = players.get(getPlayerIndexByName(currentTurn)).getCards();
            String cardHandString = "";
            for(int i = 0; i < cardHandArray.size(); i++)
            {
                cardHandString = cardHandString + "     *     " + cardHandArray.get(i);
            }
            
            cardViewWindow = new JFrame();
            cardViewWindow.setLayout(new GridLayout(1,1,0,0));
            cardViewWindow.setPreferredSize(new Dimension(400, 100));
            
            JLabel cardViewLabel = new JLabel(cardHandString);
            cardViewWindow.add(cardViewLabel);
            
            cardViewWindow.add(cardViewLabel);
            
            cardViewWindow.setVisible(true);
            cardViewWindow.pack();
            cardViewWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        }
        else
        {
            cardView = false;
            cardViewWindow.dispose();
        }
    }
    
    /**
     * Interface to make an accusation 
     * @param JPanel panel for interface 
     */
    public void makeAccusation()
    {
        
        JFrame accusationWindow = new JFrame();
        accusationWindow.setLayout(new FlowLayout());
        accusationWindow.setPreferredSize(new Dimension(700, 150));
        
        JPanel conclusionWeaponPanel = new JPanel();
        JPanel conclusionPersonPanel = new JPanel();
        JPanel conclusionRoomPanel = new JPanel();
        
        JLabel cwLabel = new JLabel("Weapon Accusation");
        JLabel cpLabel = new JLabel("Person Accusation");
        JLabel crLabel = new JLabel("Room Accusation");
        
        String[] weaponList = {"Dagger","Candlestick","Revolver","Rope","Lead Piping","Spanner"};
        String[] personList = {"Col Mustard","Prof Plum","Rev Green","Mrs Peacock","Miss Scarlett","Mrs White"};
        String[] roomList = {"Study","Hall","Lounge","Library","Billiard Room","Dining Room","Conservatory","Ball Room","Kitchen"};
        //Interface to select weapon, person, room
        JComboBox weaponAccuseSelect = new JComboBox(weaponList);
        JComboBox personAccuseSelect = new JComboBox(personList);
        JComboBox roomAccuseSelect = new JComboBox(roomList);
        
        conclusionWeaponPanel.add(cwLabel);
        conclusionWeaponPanel.add(weaponAccuseSelect);
        
        conclusionPersonPanel.add(cpLabel);
        conclusionPersonPanel.add(personAccuseSelect);
        
        conclusionRoomPanel.add(crLabel);
        conclusionRoomPanel.add(roomAccuseSelect);
        //Widnow to make an accusation 
        accusationWindow.add(conclusionWeaponPanel);
        accusationWindow.add(conclusionPersonPanel);
        accusationWindow.add(conclusionRoomPanel);
        
        JPanel commitAccusationPanel = new JPanel(new GridLayout(2,1,0,0));
        
        JButton commitAccusationButton = new JButton("MAKE ACCUSATION!");
        commitAccusationPanel.add(commitAccusationButton);
        
        JLabel accusationCompleteMessage = new JLabel();
        commitAccusationPanel.add(accusationCompleteMessage);
        
        commitAccusationButton.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String accusedWeapon = (String) weaponAccuseSelect.getSelectedItem();
                String accusedPerson = (String) personAccuseSelect.getSelectedItem();
                String accusedRoom = (String) roomAccuseSelect.getSelectedItem();
                
                String[] fullAccusationList = new String[3];
                fullAccusationList[0] = accusedWeapon;
                fullAccusationList[1] = accusedPerson;
                fullAccusationList[2] = accusedRoom;
                  //Checks and warns player whether their acusation is right or wrong or whether they've already made one
                if(accusationOpen.getPlayerAlreadyMadeAccusationList().contains(currentTurn))
                {
                     accusationCompleteMessage.setText("You already made an accusation!");
                    return;
                }
                if(accusationOpen.makeAccusation(currentTurn, fullAccusationList) == true)
                {
                    accusationCompleteMessage.setText("CORRECT! " + accusedPerson + "killed Dr Phlox with a " + accusedWeapon + " in the " + accusedRoom + "!");
                }
                else
                {
                    accusationCompleteMessage.setText("Incorrect, you have lost all credibility and your accusation privilege!");
                }
            }
        }
        );
        
        accusationWindow.add(commitAccusationPanel);
        
        accusationWindow.setVisible(true);
        accusationWindow.pack();
        accusationWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    
    /**
     * 
     * Allows to make a suggestion by choosing a weapon, person, room 
     * @param weaponSelected selected weapon 
     * @param personSelected selected person 
     */
    public void makeSuggestion(String weaponSelected, String personSelected)
    {
        
         String currentRoom = players.get(getPlayerIndexByName(currentTurn)).getRoom();
        if(currentRoom == null)
        {
            return;
        }
        if(suggestionAlreadyMade == true)
        {
            return;
        }
        suggestionAlreadyMade = true;
        for(int i = 0; i < players.size(); i++)
        {
            if(players.get(i).getCards().contains(weaponSelected) || players.get(i).getCards().contains(personSelected) || players.get(i).getCards().contains(currentRoom))
            {
                ArrayList<String> possibleCardList = new ArrayList<String>();
                String matchedCardPlayerName = players.get(i).getName();
                for(int a = 0; a < players.get(i).getCards().size(); a++)
                {
                    if(players.get(i).getCards().get(a) == weaponSelected)
                    {
                         possibleCardList.add(weaponSelected);
                    }
                    if(players.get(i).getCards().get(a) == personSelected)
                    {
                        possibleCardList.add(personSelected);
                    }
                    if(players.get(i).getCards().get(a) == currentRoom)
                    {
                        possibleCardList.add(currentRoom);
                    }
                }
                Collections.shuffle(possibleCardList);
                
                JFrame matchingCardFoundWindow = new JFrame();
                
                JPanel cardDisplay = new JPanel(new GridLayout(1,1,0,0));
                matchingCardFoundWindow.add(cardDisplay);
                JLabel cardDisplayLabel = new JLabel(matchedCardPlayerName + " is currently holding the " + possibleCardList.get(0) + " card!");
                cardDisplay.add(cardDisplayLabel);
                matchingCardFoundWindow.setVisible(true);
                matchingCardFoundWindow.pack();
                matchingCardFoundWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                return;
            }
        }
    }
    
    /**
     * Selects exit rooms 
     * @param roomSelectionList
     */
    public void roomExitSelection()
    {
        if(!players.get(getPlayerIndexByName(currentTurn)).getRoom().isEmpty())
        {
        String[] roomSelectionList = roomExitList(players.get(getPlayerIndexByName(currentTurn)).getRoom());
        roomSelect.removeAllItems();
        for (int i = 0; i < roomSelectionList.length; i++)
        {
            roomSelect.addItem(roomSelectionList[i]);
        }
        refreshWindow();
        }
    }
    
    /**
     * Gets the index of the player by name 
     * @param name name of the player 
     */
    
    public int getPlayerIndexByName(String name)
    {
        for(int i = 0; i < players.size(); i++)
        {
            if(players.get(i).getName() == currentTurn)
            {
                return i;
            }
        }
        return 0;
    }
    
    /**
     * Gets the index of the room by name 
     * @param name name of the room 
     */
    public int getRoomIndexByName(String name)
    {
        for(int i = 0; i < rooms.size(); i++)
        {
            if(rooms.get(i).getName() == name)
            {
                return i;
            }
        }
        return 0;
    }
    
    /**
     * Coordinates for the door to exit a room 
     * @param exitName name of the exit door 
     */
    public void roomExitProtocol(String exitName)
    {
        
        if(exitName == "Study Door")
        {
            doorExit(4, 6);
        }
        if(exitName == "Kitchen")
        {
            roomSecretPath(exitName);
        }
        if(exitName == "Hall Left Door")
        {
            doorExit(4, 8);
        }
        if(exitName == "Hall Bottom Left Door")
        {
            doorExit(7, 11);
        }
        if(exitName == "Hall Bottom Right Door")
        {
            doorExit(7, 12);
        }
        if(exitName == "Lounge Door")
        {
            doorExit(6, 17);
        }
        if(exitName == "Conservatory")
        {
            roomSecretPath(exitName);
        }
        if(exitName == "Library Right Door")
        {
            doorExit(8, 7);
        }
        if(exitName == "Library Bottom Door")
        {
            doorExit(11, 3);
        }
        if(exitName == "Billiard Top Door")
        {
            doorExit(11, 1);
        }
        if(exitName == "Billiard Right Door")
        {
            doorExit(15, 6);
        }
        if(exitName == "Dining Top Door")
        {
            doorExit(8, 17);
        }
        if(exitName == "Dining Left Door")
        {
            doorExit(12, 15);
        }
        if(exitName == "Conservatory Door")
        {
            doorExit(19, 5);
        }
        if(exitName == "Lounge")
        {
            roomSecretPath(exitName);
        }
        if(exitName == "Ball Left Door")
        {
            doorExit(19, 7);
        }
        if(exitName == "Ball Right Door")
        {
            doorExit(19, 16);
        }
        if(exitName == "Ball Top Left Door")
        {
            doorExit(16, 9);
        }
        if(exitName == "Ball Top Right Door")
        {
            doorExit(16, 14);
        }
        if(exitName == "Kitchen Door")
        {
            doorExit(17, 19);
        }
        if(exitName == "Study")
        {
            roomSecretPath(exitName);
        }
    }
    
    /**
     * Exit door instance 
     * @param x
     * @param y
     */
    public void doorExit(int x, int y)
    {
        
        players.get(getPlayerIndexByName(currentTurn)).setPosition(x, y);
        rooms.get(getRoomIndexByName(players.get(getPlayerIndexByName(currentTurn)).getRoom())).removePlayer(currentTurn);
        players.get(getPlayerIndexByName(currentTurn)).setRoom(null);
        roomSelect.removeAllItems();
        updatePlayerGrid();
    }
    
    /**
     * Allows to get to the secret path 
     * @param roomName name of the room 
     */
    public void roomSecretPath(String roomName)
    {
        
        noMoreRoll = true;
        rooms.get(getRoomIndexByName(players.get(getPlayerIndexByName(currentTurn)).getRoom())).removePlayer(currentTurn);
        players.get(getPlayerIndexByName(currentTurn)).setRoom(roomName);
        rooms.get(getRoomIndexByName(roomName)).addPlayer(currentTurn);
        roomSlotAssign(getRoomIndexByName(players.get(getPlayerIndexByName(currentTurn)).getRoom()));
        roomSelect.removeAllItems();
        updatePlayerGrid();
    }
    
    /**
     * 
     * Lists of exit doors 
     * @param roomName name of the room 
     */
    public String[] roomExitList(String roomName)
    {
        
        String[] listOfExits;
        if(roomName == "Study")
        {
            listOfExits = new String[2];
            listOfExits[0] = "Study Door";
            listOfExits[1] = "Kitchen";
            return listOfExits;
        }
        if(roomName == "Hall")
        {
            listOfExits = new String[4];
            listOfExits[0] = "Hall Left Door";
            listOfExits[1] = "Hall Bottom Left Door";
            listOfExits[2] = "Hall Bottom Right Door";
            return listOfExits;
        }
        if(roomName == "Lounge")
        {
            listOfExits = new String[2];
            listOfExits[0] = "Lounge Door";
            listOfExits[1] = "Conservatory";
            return listOfExits;
        }
        if(roomName == "Library")
        {
            listOfExits = new String[2];
            listOfExits[0] = "Library Right Door";
            listOfExits[1] = "Library Bottom Door";
            return listOfExits;
        }
        if(roomName == "Billiard Room")
        {
            listOfExits = new String[2];
            listOfExits[0] = "Billiard Top Door";
            listOfExits[1] = "Billiard Right Door";
            return listOfExits;
        }
        if(roomName == "Dining Room")
        {
            listOfExits = new String[2];
            listOfExits[0] = "Dining Top Door";
            listOfExits[1] = "Dining Left Door";
            return listOfExits;
        }
        if(roomName == "Conservatory")
        {
            listOfExits = new String[2];
            listOfExits[0] = "Conservatory Door";
            listOfExits[1] = "Lounge";
            return listOfExits;
        }
        if(roomName == "Ball Room")
        {
            listOfExits = new String[4];
            listOfExits[0] = "Ball Left Door";
            listOfExits[1] = "Ball Right Door";
            listOfExits[2] = "Ball Top Left Door";
            listOfExits[3] = "Ball Top Right Door";
            return listOfExits;
        }
        if(roomName == "Kitchen")
        {
            listOfExits = new String[2];
            listOfExits[0] = "Kitchen Door";
            listOfExits[1] = "Study";
            return listOfExits;
        }
        return null;
    }
    
    /**
     * 
     * Coloured player tile interface
     *@param tokenName name of the token
     */
    private Color playerTilePaint(String tokenName)
    {
        
         if(tokenName == "Col Mustard")
         {
            return new Color(255, 173, 1);
        }
        if(tokenName == "Prof Plum")
        {
            return new Color(221,160,221);
        }
        if(tokenName == "Rev Green")
        {
            return new Color(0,128,0);
        }
        if(tokenName == "Mrs Peacock")
        {
            return new Color(0,95,105);
        }
        if(tokenName == "Miss Scarlett")
        {
            return new Color(255, 36, 0);
        }
        if(tokenName == "Mrs White")
        {
            return new Color(255, 255, 255);
        }
        return new Color(0, 0, 0);
    }
    
    /**
     * Player name tile initials 
     * @param tokenName name of the token
     */
    private String playerTileName(String tokenName)
    {
        
         if(tokenName == "Col Mustard")
        {
            return "CM";
        }
        if(tokenName == "Prof Plum")
        {
            return "PP";
        }
        if(tokenName == "Rev Green")
        {
            return "RG";
        }
        if(tokenName == "Mrs Peacock")
        {
            return "MP";
        }
        if(tokenName == "Miss Scarlett")
        {
            return "MS";
        }
        if(tokenName == "Mrs White")
        {
            return "MW";
        }
        return null;
    }
    
    /**
     * Colours the grid tiles depedning on the rooms and whther they're a spawn or door
     * @param tileTag tags for the tiles 
     */
    private Color tileTypePainter(String tileTag)
    {
        
        Color tileColour = Color.GRAY;
        
        if(tileTag == "Exterior")
        {
            tileColour = Color.WHITE;
        }
        if(tileTag == "Tile" || tileTag.contains("Door"))
        {
            tileColour = new Color(255, 219, 77);
        }
        if(tileTag.contains("Spawn"))
        {
            tileColour = new Color(255, 219, 77);
        }
        if(tileTag == "Entrance")
        {
            tileColour = new Color(153, 102, 51);
        }
        if(tileTag.contains("SecretPassage"))
        {
            tileColour = new Color(204, 102, 0);
        }
        if(tileTag == "Study")
        {
            tileColour = new Color(128, 0, 64);
        }
        if(tileTag == "Hall")
        {
            tileColour = new Color(92, 0, 230);
        }
        if(tileTag == "Lounge")
        {
            tileColour = new Color(179, 0, 89);
        }
        if(tileTag == "Library")
        {
            tileColour = new Color(77, 51, 25);
        }
        if(tileTag == "Billiard")
        {
            tileColour = new Color(0, 77, 26);
        }
        if(tileTag == "Dining")
        {
            tileColour = new Color(0, 89, 179);
        }
        if(tileTag == "Conservatory")
        {
            tileColour = new Color(92, 138, 138);
        }
        if(tileTag == "Ball")
        {
            tileColour = new Color(255, 179, 102);
        }
        if(tileTag == "Kitchen")
        {
            tileColour = new Color(128, 128, 128);
        }
        if(tileTag == "Stair")
        {
            tileColour = new Color(153, 0, 0);
        }
        if(tileTag == "Card")
        {
            tileColour = new Color(0, 179, 60);
        }
        return tileColour;
    }
}