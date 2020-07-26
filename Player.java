import java.util.Scanner;
import java.util.ArrayList;

public class Player {
    /****INSTANCE VARIABLES****/
    private ArrayList<Ship> ships;
    private int x;
    private int y;
    
    /****CONSTRUCTOR****/
    public Player(){
         ships = new ArrayList<Ship>();
    }
    
    /****METHODS****/
    public void deployShips(){
        for(int i=0; i < 5; i++){
            this.addShip();
        }
            
        //checks for duplicates
        for(int i=0; i < 4; i++){
            for(int j=i+1; j < 5; j++){            
                while(ships.get(i).getXcoord() == ships.get(j).getXcoord() && 
                   ships.get(i).getYcoord() == ships.get(j).getYcoord()){ 
                   
                   System.out.println("Duplicate ship at (" + 
                   ships.get(j).getXcoord()+","+ships.get(j).getYcoord()+")"); 
                   System.out.println("Please enter new coordinates");             
                   
                   ships.remove(j);
                   this.addShip();
                }
            }
        }    
    }
    
    public ArrayList<Ship> getShips(){
        return ships;
    }
    
    public void addShip(){
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
        
        ships.add(new Ship(x,y));
        
        System.out.println("****Ship DEPLOYED at (" + x + "," + y + ")****");
        System.out.println();       
    }
}