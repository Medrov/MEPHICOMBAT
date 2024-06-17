package Fabric;

import Entity.Player;

public class EnemyFabric {

    public Player create(int i, int j) {
        EnemyFabricInterface fabric = null;

        switch (i) {
            case 0 -> fabric = new KarkarychFabric();
            case 1 -> fabric = new GamblerFabric();
            case 2 -> fabric = new MathGeniusFabric();
            case 3 -> fabric = new MadameFabric();
            case 4 -> fabric = new BritishVillainFabric();
        }
        Player enemy = fabric.create(j);
        return enemy;
    }
}
