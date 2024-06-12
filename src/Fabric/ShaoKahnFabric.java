package Fabric;

import Character.ShaoKahn;
import Entity.Player;

public class ShaoKahnFabric implements EnemyFabricInterface {

    @Override
    public Player create(int i) {
        return new ShaoKahn(3, 100, 30, 1);
    }
}
