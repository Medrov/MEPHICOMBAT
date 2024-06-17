package Character;

import Entity.Player;

public class Madame extends Player{
    
    public Madame(int level, int health, int  damage, int attack){
        super (level, health, damage, attack);
    }
    
    @Override
    public String getName(){
        return "Госпожа";
    }
}
