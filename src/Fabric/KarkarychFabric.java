package Fabric;

import Character.Karkarych;
import Entity.Player;

public class KarkarychFabric implements EnemyFabricInterface {
    
    @Override
    public Player create(int i) {
        Player enemy;
        enemy = new Karkarych(1, 100, 12, 1);
        return enemy;
    }
}
