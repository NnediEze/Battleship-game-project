public class Ship{
    
    /****INSTANCE VARIABLES****/
    private int x;
    private int y; 
    
    /****CONSTRUCTOR****/
    public Ship(int xcoord, int ycoord){
        x = xcoord;
        y = ycoord;      
    }
    
    /****METHODS****/
    public int getXcoord(){
        return x;
    }
    
    public int getYcoord(){
        return y;
    }
    
}