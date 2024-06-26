package Actions;

import Entity.Human;
import Entity.Player;
import Entity.Result;
import Features.*;

import java.util.ArrayList;

public class Fight {
    Mediator mediator;
    Human human;
    Player enemy;
    public Location location = new Location();
    public ArrayList<Action> actionsList = new ArrayList<>() {
        {
            add(new Hit());
            add(new Block());
            add(new Debuff());
            add(new Heal());
        }
    };

    public void setMediator(Mediator mediator){
        this.mediator = mediator;
    }
    
    public void setHuman(Human human) {
        this.human = human;
    }

    public void setEnemy(Player enemy) {
        this.enemy = enemy;
    }

    public Human getHuman() {
        return this.human;
    }

    public Player getEnemy() {
        return this.enemy;
    }

    public void playerMove(Action enemyAction, Action playerAction) {
        mediator.setActionLabels(enemy, human, enemyAction, playerAction);
        playerAction.realisation(human, enemy, enemyAction.getType());
    }

    public void enemyMove(Action enemyAction, Action playerAction) {
        mediator.setActionLabels(human, enemy, enemyAction, playerAction);
        playerAction.realisation(enemy, human, enemyAction.getType());
    }

    public void checkDebuff() {
        if (!enemy.isDebuffed()) {
            mediator.setDebuffLabel(enemy, false);
        }
        if (enemy.isDebuffed()) {
            mediator.setDebuffLabel(enemy, true);
            enemy.loseDebuffTurn();
        }
        if (!human.isDebuffed()) {
            mediator.setDebuffLabel(human, false);
        }
        if (human.isDebuffed()) {
            mediator.setDebuffLabel(enemy, true);
            human.loseDebuffTurn();
        }
    }

    public void hit(int a, ArrayList<Result> results, int locationsNumber, Player[] enemiesList) {
        GameLogic action = new GameLogic();
        Action enemyAction = action.chooseEnemyAction(enemy, new ArrayList<>(actionsList));
        switch (a) {
            case 0 -> {
                playerMove(enemyAction,
                        actionsList.get(1));
                if (enemy.getHealth() > 0) {
                    enemyMove(actionsList.get(1), enemyAction);
                }
                if (human.getHealth() <= 0) {
                    mediator.gameEnding("Ты проиграл!", true);
                }
            }
            case 1 -> {
                playerMove(enemyAction, actionsList.get(0));
                if (enemy.getHealth() > 0) {
                    enemyMove(actionsList.get(0),
                            enemyAction);
                }
                if (human.getHealth() <= 0) {
                    mediator.gameEnding("Ты проиграл!", true);
                }
            }
            case 2 -> {
                playerMove(enemyAction, actionsList.get(2));
                if (enemy.getHealth() > 0) {
                    enemyMove(actionsList.get(2),
                            enemyAction);
                }
                if (human.getHealth() <= 0) {
                    mediator.gameEnding("Ты проиграл!", true);
                }
            }
        }
        mediator.setRoundTexts(human, enemy);
        checkDebuff();
        mediator.setHealthBar(human);
        mediator.setHealthBar(enemy);
        checkDeath(results, locationsNumber, enemiesList);
    }

    public void checkDeath(ArrayList<Result> results, int locationsNumber, Player[] enemiesList) {
        if (human.getHealth() <= 0 & human.getItems()[2].getCount() > 0) {
            human.setHealth((int) (human.getMaxHealth() * 0.05));
            human.getItems()[2].setCount(-1);
            mediator.setHealthBar(human);
            mediator.revive(human, human.getItems());
        }
        if (human.getHealth() <= 0 | enemy.getHealth() <= 0) {
            if (location.getCurrentLocation() == locationsNumber & "Злодей британец".equals(enemy.getName())) {
                location.resetLocation(false, 1);
                endFinalRound(results, enemiesList);
            } else {
                endRound(enemiesList);
            }
        }
    }

    public void endRound(Player[] enemiesList) {
        GameLogic action = new GameLogic();
        mediator.setEndFightDialog();

        if (human.getHealth() > 0) {
            mediator.setRoundEndText("Ты молодец");
            mediator.setGIF(true);
            if ("Злодей британец".equals(enemy.getName())) {
                action.addItems(38, 23, 8, human.getItems());
                action.addPointsBoss(human);
                location.resetLocation(true, human.getLevel());
            } else {
                action.addItems(25, 15, 5, human.getItems());
                action.addPoints(human);
            }
        } else {
            reset(enemiesList);
            mediator.setRoundEndText("Ты проиграл");
            mediator.setGIF(false);
        }
    }

    public void reset(Player[] enemiesList) {
        GameLogic action = new GameLogic();
        human.setDamage(16);
        human.setHealth(80);
        human.setMaxHealth(80);
        action.resetEnemies(enemiesList);
        human.setLevel(0);
        human.resetPoints();
        human.resetExperience();
        human.setNextExperience(40);
        location.setFullEnemiesList(enemiesList);
        location.resetLocation(false, human.getLevel());
    }

    public void endFinalRound(ArrayList<Result> results, Player[] enemiesList ) {
        GameLogic action = new GameLogic();
        action.resetEnemies(enemiesList);
        String text = "Победа не на вашей стороне";
        if (human.getHealth() > 0) {
            action.addPoints(human);
            text = "Победа на вашей стороне";
        }
        boolean top = false;
        if (results == null) {
            top = true;
        } else {
            int a = 0;
            for (int j = 0; j < results.size(); j++) {
                if (human.getPoints() < results.get(j).getPoints()) {
                    a++;
                }
            }
            if (a < 10) {
                top = true;
            }
        }
        mediator.gameEnding(text, top);
    }

    public void newRound() {
        mediator.setPlayerMaxHealthBar(human);
        mediator.setEnemyMaxHealthBar(enemy);
        human.setHealth(human.getMaxHealth());
        enemy.setHealth(enemy.getMaxHealth());
        mediator.setHealthBar(human);
        mediator.setHealthBar(enemy);
    }

}
