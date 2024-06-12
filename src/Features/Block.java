package Features;

import Entity.Player;

public class Block extends Action {

    @Override
    public String getType() {
        return "Block";
    }

    @Override
    public void realisation(Player human, Player enemy, String enemyActionType) {
    }
}
