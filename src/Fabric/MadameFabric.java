package Fabric;

import Character.Madame;
import Entity.Player;

public class MadameFabric implements EnemyFabricInterface {

    @Override
    public Player create(int i) {
        Player enemy;
        enemy = new Madame(1, 80, 16, 1);
        return enemy;
    }

}
