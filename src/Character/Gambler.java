package Character;

import Entity.Player;

public class Gambler extends Player{
    
    public Gambler(int level, int health, int damage , int attack){
        super (level, health, damage, attack);
    }
    
    @Override
    public String getName(){
        return "Профессиональнй игрок";
    }
}
