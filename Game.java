import java.util.Arrays;
import java.util.Scanner;
import java.util.Random; 

public class Game {

    /****INSTANCE VARIABLES****/
    private String[][] map;
    private Player p;
    private Computer c;
    
    /****METHODS****/
    public Game(){           
        p = new Player();
        c = new Computer();
        map = new String[12][14];
        
        int counter = 0;
        for(int j=0; j < map[0].length; j++){
            for(int i=0; i < map.length; i++){
                if((j == 0 || j == 13) && (i >= 1 && i <= 10)){
                    map[i][j] = " " + String.valueOf(i-1);                    
                }              
                else if((j == 1 || j == 12) && (i >= 1 && i <= 10)){
                    map[i][j] = "|";    
                }              
                else if((i == 0 || i == 11) && (j >= 2 && j <= 11)){
                    map[i][j] = String.valueOf(counter) + " ";
                    if(map[0][j].equals(map[11][j])){
                        counter++;
                    }
                }
                else{
                    map[i][j] = "  ";
                }
            }
        }
    }
    
    public void play(){
        //Deploying Ships
        this.printMap();
        System.out.println("-----------------------------------");
        
        System.out.println("DEPLOY 5 SHIPS 🚢: ");       
        p.deployShips();   
        for(int i = 0; i < 5; i++){
            map[p.getShips().get(i).getYcoord()+1][p.getShips().get(i).getXcoord()+2] = " @";
        }             
        this.printMap();
        
        System.out.println();
        System.out.println("COMPUTER IS DEPLOYING 5 SHIPS 🚢: ");
        c.deployShips();
        
        //checks for overlap btw 
        for(int j=0; j < 4; j++){
            for(int k=j+1; k < 5; k++){
                while(c.getShips().get(j).getXcoord() == p.getShips().get(k).getXcoord() 
                      && c.getShips().get(j).getYcoord() == p.getShips().get(k).getYcoord()){
                    c.getShips().remove(k);
                    c.addShip();
                }
            }            
        }
        System.out.println("-------------------------");
        System.out.println("⚓️💥LET THE BATTLE BEGIN💥⚓️");
        System.out.println();
        
        while(p.getShips().size() > 0 && c.getShips().size() > 0){
            this.playerTurn();
            this.computerTurn();
            this.printMap();
            System.out.print("Your ships: " + p.getShips().size() + " |");
            System.out.println(" Computer ships: " + c.getShips().size());
            System.out.println("--------------------------------------------");
        }
        
        System.out.print("Your ships: " + p.getShips().size() + " |");
        System.out.println(" Computer ships: " + c.getShips().size());
        if(c.getShips().size() == 0){
            System.out.println("Hooray! You won the battle😁");
        }
        else{
            System.out.println("Sorry, you lost😔");
        }
           
    
    }
   
    public void printMap(){
        for(int i=0; i < map.length; i++){
            for(int j=0; j < map[0].length; j++){
                System.out.print(map[i][j]);
            }
            
            System.out.println();
            System.out.println();
        }        
    }
    
   public void playerTurn(){
       int x;
       int y;
       boolean result = false;
       
       System.out.println("YOUR TURN");
       Scanner input = new Scanner(System.in);
        
       System.out.println("Enter X coordinate : ");
       x = input.nextInt();
        
       while(x > 9 || x < 0){
           System.out.println("Please enter a legal coordinate");
           x = input.nextInt();
       }
        
       System.out.println("Enter Y coordinate : "); 
       y = input.nextInt();
        
       while(y > 9 || y < 0){
           System.out.println("Please enter a legal coordinate");
           y = input.nextInt();
       }
       
       for(int i=0; i < c.getShips().size(); i++){
           if(c.getShips().get(i).getXcoord() == x && 
              c.getShips().get(i).getYcoord() == y){                      
               map[y+1][x+2] = " !";               
               c.getShips().remove(i); 
               System.out.println("Boom💥 You sunk the ship!");
               result = true;
           }
       }
           
       for(int j=0; j < p.getShips().size(); j++){
           if(p.getShips().get(j).getXcoord() == x && 
              p.getShips().get(j).getYcoord() == y){
               map[y+1][x+2] = " x";
               p.getShips().remove(j);
               System.out.println("Oh no, you sunk your own ship😕");
               result = true;
           }
       }
       
       if(result == false){
           map[y+1][x+2] = " -";
           System.out.println("You missed");
       }     
   }
    
   public void computerTurn(){
       int x;
       int y;
       boolean result = false;
       
       System.out.println("COMPUTER'S TURN");      
       Random rand = new Random();
       x = rand.nextInt(10);
       y = rand.nextInt(10);
       
       //checks array list of incorrect guesses 
       if(c.getMisses().size() > 0){
           for(int i=0; i < c.getMisses().size(); i++){
               while(c.getMisses().get(i).getXcoord() == x && 
               c.getMisses().get(i).getYcoord() == y){
                   x = rand.nextInt(10);
                   y = rand.nextInt(10);               
               }
           }
       }
       
       for(int i=0; i < c.getShips().size(); i++){
           if(c.getShips().get(i).getXcoord() == x && 
              c.getShips().get(i).getYcoord() == y){                      
               map[y+1][x+2] = " !";               
               c.getShips().remove(i); 
               System.out.println("The Computer sunk one of its own ships");
               result = true;
           }
       }
       
       for(int j=0; j < p.getShips().size(); j++){
           if(p.getShips().get(j).getXcoord() == x && 
              p.getShips().get(j).getYcoord() == y){
               map[y+1][x+2] = " x";
               p.getShips().remove(j);
               System.out.println("The Computer sunk one of your ships!");
               result = true;
           }
       }
       
       if(result == false){
           map[y+1][x+2] = " -";
           System.out.println("Computer missed");
           c.getMisses().add(new Ship(x,y));
       }       
   }
}