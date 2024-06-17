package Fabric;

import Character.Gambler;
import Entity.Player;

public class SubZeroFabric implements EnemyFabricInterface {

    @Override
    public Player create(int i) {
        Player enemy;
        enemy = new Gambler(1, 60, 16, 1);
        return enemy;
    }

}
