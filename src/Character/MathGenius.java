package Character;

import Entity.Player;

public class MathGenius extends Player{
    
    public MathGenius(int level, int health, int  damage, int attack){
        super (level, health, damage, attack);
    }
    
    @Override
    public String getName(){
        return "Гений математики";
    }
}
