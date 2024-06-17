package Character;

import Entity.Player;

public class BritishVillain extends Player{
    
    public BritishVillain(int level, int health, int  damage, int attack){
        super (level, health, damage, attack);
    }
    
    @Override
    public String getName(){
        return "Злодей британец";
    }
}
