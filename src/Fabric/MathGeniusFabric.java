package Fabric;

import Character.MathGenius;
import Entity.Player;

public class MathGeniusFabric implements EnemyFabricInterface {

    @Override
    public Player create(int i) {
        Player enemy;
        enemy = new MathGenius(1, 70, 20, 1);
        return enemy;
    }
}
