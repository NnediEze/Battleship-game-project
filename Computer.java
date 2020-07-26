import java.util.Random; 
import java.util.ArrayList;

public class Computer{
    /****INSTANCE VARIABLES****/
    private ArrayList<Ship> ships;
    private ArrayList<Ship> misses;
    private int x;
    private int y;
    
    /****CONSTRUCTOR****/
    public Computer(){
        ships = new ArrayList<Ship>(); 
        misses = new ArrayList<Ship>();
    }
    
    /****METHODS****/
    public void deployShips(){
        for(int i=0; i < 5; i++){
            this.addShip();      
            System.out.println("Ship DEPLOYED");
        }   
    }
    
    public ArrayList<Ship> getShips(){
        return ships;       
    }
    
    public void addShip(){
        Random rand = new Random();
        x = rand.nextInt(10);
        y = rand.nextInt(10);
        
        ships.add(new Ship(x,y));
    }
    
    public ArrayList<Ship> getMisses(){
        return misses;
    }
}