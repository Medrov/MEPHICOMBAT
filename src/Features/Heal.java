package Features;

import Entity.Player;

public class Heal extends Action {

    @Override
    public String getType() {
        return "Heal";
    }

    @Override
    public void realisation(Player human, Player enemy, String enemyActionType) {
        switch (enemyActionType) {
            case "Hit", "Heal", "Debuff" -> {
            }
            case "Block" ->{
                human.addHealth((human.getMaxHealth() - human.getHealth())/2);
            }
        }
    }
}
