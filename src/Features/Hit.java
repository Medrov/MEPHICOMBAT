package Features;

import Entity.Player;

public class Hit extends Action {

    @Override
    public String getType() {
        return "Hit";
    }

    @Override
    public void realisation(Player human, Player enemy, String enemyActionType) {
        int damage = calculateDamage(human, enemy);
        switch (enemyActionType) {
            case "Hit" -> {
                enemy.addHealth(-damage);
            }
            case "Block" -> {
                if (Math.random() < 0.5) {
                    enemy.addHealth(-human.getDamage() / 2);
                }
            }
            case "Debuff" -> {
                enemy.addHealth((int) (-human.getDamage()*1.15));
            }
            case "Heal" -> {
                enemy.addHealth(-human.getDamage()*2);
            }
        }
    }

    private int calculateDamage(Player human, Player enemy){
        int damage = human.getDamage();
        if (enemy.isDebuffed() && human.isDebuffed()) {
            damage = (int) (damage * 1.25 / 2);
        }
        else if (enemy.isDebuffed() &&!human.isDebuffed()) {
            damage = (int) (damage * 1.25);
        }
        else if (!enemy.isDebuffed() && human.isDebuffed()) {
            damage = damage / 2;
        }
        return damage;
    }
}
