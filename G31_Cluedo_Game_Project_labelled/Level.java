import java.util.*;

/**
 * The Level Class store a string array map for the grahics elements and 
 * class representing moving tokens to reference from.
 *
 * @author Group31
 * @version V.1
 */
public class Level
{
    //Defines variables needed for the baord level later on 
    String[][] coordinateArray;
    String[][] textArray;
    String[][] tokenArray;
    int boardRowNumbers;
    int boardColumnNumbers;
    
    /**
     * Initialises board with set numbers and an array of coordinates
     * Generates level features
     * @param boardRowNumbers number of rows
     * @param boardColumnNumbers number of columns
     */
     public Level()
    {
        
        boardRowNumbers = 25;
        boardColumnNumbers = 24;
        
        coordinateArray = new String[boardRowNumbers][boardColumnNumbers];
        textArray = new String[boardRowNumbers][boardColumnNumbers];
        tokenArray = new String[boardRowNumbers][boardColumnNumbers];
        
        generateSpawnPoint();
        generateRooms();
        generateSecretPassage();
        generateExterior();
        generateDoors();
        generateFloorTiles();
        
        generateTextLayer();
    }
    
    /**
     * Returns the row numbers in the board
     * @return boardRowNumbers
     */
    public int getBoardRowNumbers()
    {
       
        return boardRowNumbers;
    }
    
    /**
     * Returns the column numbers in the board 
     * @return boardColumnNumbers 
     */
    public int getBoardColumnNumbers()
    {
        
        return boardColumnNumbers;
    }
    
    /**
     * Input token coordinates
     * @param name name token 
     */
     public void inputTokenCoordinate(String name, int x, int y)
    {
        removeTokenCoordinate(name);
        tokenArray[x][y] = name;
    }
    
    /**
     * Removes token coordinates 
     * @param name name token
     */
    private void removeTokenCoordinate(String name)
     {
        for (int x = 0; x < boardRowNumbers; x++)
        {
            for (int y = 0; y < boardColumnNumbers; y++)
            {
                if(tokenArray[x][y] == name)
                {
                    tokenArray[x][y] = null;
                }
            }
        }
    }
    
    /**
     * Gets token array tag 
     * @return toeknArray
     */
    public String getTokenArrayTag(int x, int y)
    {
        return tokenArray[x][y];
    }
    
    /**
     * Gets text array tag 
     * @return textArray
     */
     public String getTextArrayTag(int x, int y)
    {
        return textArray[x][y];
    }
     
    /**
      * Returns the coordinates for the rows in the board 
      * @return boardRowNumbers number of rows 
     */
    public int getCoordinateArrayRowNumbers()
    {
        
        return boardRowNumbers;
    }
    
    /**
     * Returns the coordinates for the columns in the board 
     * @return boardColumnNumbers
     */
    public int getCoordinateArrayColumnNumbers()
    {
        
        return boardColumnNumbers;
    }
    
    /**
    * Tile gets linked to the coordinates in the board 
    * @return coordinateArray array of coordinates 
    */
     public String getTileTag(int x, int y)
    {
        
        return coordinateArray[x][y];
    }
    
    /**
     * Generates spawn points at specific coordinates  
     * @param coordinateArray
     */
     private void generateSpawnPoint()
    {
       
        coordinateArray[0][16] = "Spawn1";
        coordinateArray[7][23] = "Spawn2";
        coordinateArray[24][14] = "Spawn3";
        coordinateArray[24][9] = "Spawn4";
        coordinateArray[18][0] = "Spawn5";
        coordinateArray[5][0] = "Spawn6";
    }
    
    /**
     * Generates Study Room at given coordinates 
     * @room rooms 
     */
    private void generateRooms()
    {
        // Generates Study Room at given coordinates 
        for (int x = 0; x <= 3; x++)
        {
            for (int y = 0; y <= 6; y++)
            {
                coordinateArray[x][y] = "Study";
            }
        }
        
        // Generates Hall Room at given coordinates 
        for (int x = 0; x <= 6; x++)
        {
            for (int y = 9; y <= 14; y++)
            {
                coordinateArray[x][y] = "Hall";
            }
        }
        
        // // Generates Lounge Room at given coordinates 
        for (int x = 0; x <= 5; x++)
        {
            for (int y = 17; y <= 23; y++)
            {
                coordinateArray[x][y] = "Lounge";
            }
        }
        
        // // Generates Library Room at given coordinates  
        for (int x = 6; x <= 10; x++)
        {
            for (int y = 0; y <= 5; y++)
            {
                coordinateArray[x][y] = "Library";
            }
        }
        coordinateArray[7][6] = "Library";
        coordinateArray[8][6] = "Library";
        coordinateArray[9][6] = "Library";
        
        // // Generates Staircase at given coordinates
        for (int x = 8; x <= 11; x++)
        {
            for (int y = 9; y <= 13; y++)
            {
                coordinateArray[x][y] = "Stair";
            }
        }
        
        // // Generates Card Slot at given coordinates 
        for (int x = 12; x <= 14; x++)
        {
            for (int y = 9; y <= 13; y++)
            {
                coordinateArray[x][y] = "Card";
            }
        }
        
        // // Generates Dining Room at given coordinates  
        for (int x = 9; x <= 14; x++)
        {
            for (int y = 16; y <= 23; y++)
            {
                coordinateArray[x][y] = "Dining";
            }
        }
        coordinateArray[15][19] = "Dining";
        coordinateArray[15][20] = "Dining";
        coordinateArray[15][21] = "Dining";
        coordinateArray[15][22] = "Dining";
        coordinateArray[15][23] = "Dining";
        
        // Generates Billiard Room at given coordinates 
        for (int x = 12; x <= 16; x++)
        {
            for (int y = 0; y <= 5; y++)
            {
                coordinateArray[x][y] = "Billiard";
            }
        }
        
        // Generates Conservatory Room at given coordinates 
        for (int x = 19; x <= 23; x++)
        {
            for (int y = 0; y <= 4; y++)
            {
                coordinateArray[x][y] = "Conservatory";
            }
        }
        coordinateArray[20][5] = "Conservatory";
        coordinateArray[21][5] = "Conservatory";
        coordinateArray[22][5] = "Conservatory";
        coordinateArray[23][5] = "Conservatory";
        
        // Generates Ball Room at given coordinates 
        for (int x = 17; x <= 22; x++)
        {
            for (int y = 8; y <= 15; y++)
            {
                coordinateArray[x][y] = "Ball";
            }
        }
        coordinateArray[23][10] = "Ball";
        coordinateArray[23][11] = "Ball";
        coordinateArray[23][12] = "Ball";
        coordinateArray[23][13] = "Ball";
        
        // Generates Kitchen Room at given coordinates 
        for (int x = 18; x <= 23; x++)
        {
            for (int y = 18; y <= 23; y++)
            {
                coordinateArray[x][y] = "Kitchen";
            }
        }
    }
    
    /**
     * Generates secret passages at a determined set of coordinates 
     * @param coordinateArray Array of coordinates 
     */
    private void generateSecretPassage()
    {
        
        coordinateArray[3][0] = "StudyKitchenSecretPassage";
        coordinateArray[23][18] = "KitchenStudySecretPassage";
        coordinateArray[5][23] = "LoungeConservatorySecretPassage";
        coordinateArray[19][1] = "ConservatoryLoungeSecretPassage";
    }
    
    /**
     * Generates Exterior 
     * @param coordinateArray array of coordinates 
     */
    private void generateExterior()
    {
        // Top
        coordinateArray[0][6] = "Exterior";
        coordinateArray[0][8] = "Exterior";
        coordinateArray[0][9] = "Exterior";
        coordinateArray[0][15] = "Exterior";
        coordinateArray[0][14] = "Exterior";
        coordinateArray[0][17] = "Exterior";
        
        // Left
        coordinateArray[4][0] = "Exterior";
        coordinateArray[6][0] = "Exterior";
        coordinateArray[10][0] = "Exterior";
        coordinateArray[11][0] = "Exterior";
        coordinateArray[17][0] = "Exterior";
        coordinateArray[19][0] = "Exterior";
        
        // Right
        coordinateArray[6][23] = "Exterior";
        coordinateArray[8][23] = "Exterior";
        coordinateArray[16][23] = "Exterior";
        coordinateArray[18][23] = "Exterior";
        
        // Bottom
        coordinateArray[23][6] = "Exterior";
        coordinateArray[23][17] = "Exterior";
        coordinateArray[24][0] = "Exterior";
        coordinateArray[24][1] = "Exterior";
        coordinateArray[24][2] = "Exterior";
        coordinateArray[24][3] = "Exterior";
        coordinateArray[24][4] = "Exterior";
        coordinateArray[24][5] = "Exterior";
        coordinateArray[24][6] = "Exterior";
        coordinateArray[24][7] = "Exterior";
        coordinateArray[24][8] = "Exterior";
        coordinateArray[24][10] = "Exterior";
        coordinateArray[24][11] = "Exterior";
        coordinateArray[24][12] = "Exterior";
        coordinateArray[24][13] = "Exterior";
        coordinateArray[24][15] = "Exterior";
        coordinateArray[24][16] = "Exterior";
        coordinateArray[24][17] = "Exterior";
        coordinateArray[24][18] = "Exterior";
        coordinateArray[24][19] = "Exterior";
        coordinateArray[24][20] = "Exterior";
        coordinateArray[24][21] = "Exterior";
        coordinateArray[24][22] = "Exterior";
        coordinateArray[24][23] = "Exterior";
    }
    
    /**
     * Generates doors 
     * @param coordinateArray
     */
    private void generateDoors()
    {
        // Generates Study Room at specified coordinates 
        coordinateArray[4][6] = "DoorStudy";
        coordinateArray[3][6] = "Entrance";
        
        // Generates Hall Room at specified coordinates 
        coordinateArray[4][8] = "DoorHallLeft";
        coordinateArray[4][9] = "Entrance";
        coordinateArray[7][11] = "DoorHallBottomLeft";
        coordinateArray[6][11] = "Entrance";
        coordinateArray[7][12] = "DoorHallBottomRight";
        coordinateArray[6][12] = "Entrance";
        
        // Generates Lounge Room at specified coordinates 
        coordinateArray[6][17] = "DoorLounge";
        coordinateArray[5][17] = "Entrance";
        
        // Generates Library Room at specified coordinates 
        coordinateArray[8][7] = "DoorLibraryRight";
        coordinateArray[8][6] = "Entrance";
        coordinateArray[11][3] = "DoorLibraryBottom";
        coordinateArray[10][3] = "Entrance";
        
        // Generates Dining Room at specified coordinates 
        coordinateArray[8][17] = "DoorDiningTop";
        coordinateArray[9][17] = "Entrance";
        coordinateArray[12][15] = "DoorDiningLeft";
        coordinateArray[12][16] = "Entrance";
        
        // Generates Billiard Room at specified coordinates
        coordinateArray[11][1] = "DoorBilliardTop";
        coordinateArray[12][1] = "Entrance";
        coordinateArray[15][6] = "DoorBilliardRight";
        coordinateArray[15][5] = "Entrance";
        
        // Generates Conservatory Room at specified coordinates
        coordinateArray[19][5] = "DoorConservatory";
        coordinateArray[19][4] = "Entrance";
        
        // Generates Ball Room at specified coordinates 
        coordinateArray[19][7] = "DoorBallLeft";
        coordinateArray[19][8] = "Entrance";
        coordinateArray[19][16] = "DoorBallRight";
        coordinateArray[19][15] = "Entrance";
        coordinateArray[16][9] = "DoorBallTopLeft";
        coordinateArray[17][9] = "Entrance";
        coordinateArray[16][14] = "DoorBallTopRight";
        coordinateArray[17][14] = "Entrance";
        
        // Generates Kitchen Room at specified coordinates
        coordinateArray[17][19] = "DoorKitchen";
        coordinateArray[18][19] = "Entrance";
    }
    
    /**
     * Generates the floor tiles in the grid 
     * 
     * @param coordinateArray
     */
    private void generateFloorTiles()
    {
        
        for (int x = 0; x <= 24; x++)
        {
            for (int y = 0; y <= 23; y++)
            {
                if(coordinateArray[x][y] == null)
                {
                    coordinateArray[x][y] = "Tile";
                }
            }
        }
    }
    
    /**
     * Visual text representation for Study Room
     * @param textArray 
     */
    private void generateTextLayer()
    {
        // Visual text representation for Study Room
        textArray[1][1] = "S";
        textArray[1][2] = "T";
        textArray[1][3] = "U";
        textArray[1][4] = "D";
        textArray[1][5] = "Y";
        
        // Visual text representation for Hall Room
        textArray[2][10] = "H";
        textArray[2][11] = "A";
        textArray[2][12] = "L";
        textArray[2][13] = "L";
        
        // Visual text representation for Lounge Room
        textArray[2][18] = "L";
        textArray[2][19] = "O";
        textArray[2][20] = "U";
        textArray[2][21] = "N";
        textArray[2][22] = "G  E";
        
        // Visual text representation for Library
        textArray[7][1] = "L";
        textArray[7][2] = "I   B";
        textArray[7][3] = "R";
        textArray[7][4] = "A  R";
        textArray[7][5] = "Y";
        
        // Visual text representation for Billiard Room
        textArray[13][1] = "B  I";
        textArray[13][2] = "L  L";
        textArray[13][3] = "I  A";
        textArray[13][4] = "R  D";
        textArray[14][2] = "R  O";
        textArray[14][3] = "O M";
        
        // Visual text representation for Dining Room
        textArray[11][17] = "D";
        textArray[11][18] = "I";
        textArray[11][19] = "N";
        textArray[11][20] = "I";
        textArray[11][21] = "N";
        textArray[11][22] = "G";
        textArray[12][19] = "R";
        textArray[12][20] = "O";
        textArray[12][21] = "O";
        textArray[12][22] = "M";
        
        // Visual text representation forConservatory
        textArray[21][0] = "C  O";
        textArray[21][1] = "N  S";
        textArray[21][2] = "E  R";
        textArray[21][3] = "V  A";
        textArray[21][4] = "T  O";
        textArray[21][5] = "R  Y";
        
        // Visual text representation for Ball Room
        textArray[18][11] = "B  A";
        textArray[18][12] = "L   L";
        textArray[19][11] = "R  O";
        textArray[19][12] = "O  M";
        
        // Visual text representation for Kitchen
        textArray[20][19] = "K  I";
        textArray[20][20] = "T  C";
        textArray[20][21] = "H   E";
        textArray[20][22] = "N";
    }
    
    /**
     * Prints coordinates 
     * @param coordinateArray 
     */
     public void printCoordinateArray()
    {
       
        System.out.println(Arrays.deepToString(coordinateArray).replace("], ","]\n"));
    }
}
