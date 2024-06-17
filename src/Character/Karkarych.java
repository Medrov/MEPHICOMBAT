package Character;

import Entity.Player;

public class Karkarych extends Player{
    
    public Karkarych(int level, int health, int  damage, int attack){
        super (level, health, damage, attack);
    }
    
    @Override
    public String getName(){
        return "Каркарыч";
    }
    
}
