package Fabric;

import Character.BritishVillain;
import Entity.Player;

public class BritishVillainFabric implements EnemyFabricInterface {

    @Override
    public Player create(int i) {
        return new BritishVillain(3, 100, 30, 1);
    }
}
