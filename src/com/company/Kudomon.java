package com.company;

import java.util.HashMap;

public class Kudomon {

    private final String species;
    private final KudomonType type;
    private Position position;
    private int hp;
    private int cp;

    // Every Kudomon has a hashmap which keeps track of the position of that kudomon with respect to a Trainer.
    // If the position of the Kudomon is (-100, -100) this means that that Trainer has caught the Kudomon.
    private HashMap<Trainer, Position> caughtBy;

    public enum KudomonType {
        GRASS,
        WATER,
        FIRE,
        PSYCHIC,
        ELECTRIC,
        ROCK;
    }

    public Kudomon(String species, KudomonType type, Position position, int hp, int cp, HashMap<Trainer, Position> caughtBy) {
        this.species = species;
        this.type = type;
        this.position = position;
        this.hp = hp;
        this.cp = cp;
        this.caughtBy = caughtBy;
    }

    public KudomonType getType() {
        return type;
    }

    public Position getPosition() {
        return position;
    }

    public String getSpecies() {
        return species;
    }

    public void setPosition(Position p) {
        this.position = p;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getCp() {
        return cp;
    }

    public HashMap<Trainer, Position> getCaughtBy() {
        return caughtBy;
    }

    public void updateCaughtBy(Trainer trainer, Position position) {
        this.caughtBy.put(trainer, position);
    }

    public void attack(Kudomon enemy) {
        enemy.setHp(enemy.getHp() - (getEffectiveness(this, enemy) * this.cp));
    }

    private int getEffectiveness(Kudomon attacker, Kudomon enemy) {
        int effectiveness = 1;
        KudomonType enemyType = enemy.getType();
        switch (attacker.getType()) {
            case WATER:
                if (enemyType.equals(KudomonType.FIRE)) {
                    effectiveness = 2;
                }
                break;
            case FIRE:
                if (enemyType.equals(KudomonType.GRASS)) {
                    effectiveness = 2;
                }
                break;
            case GRASS:
                if (enemyType.equals(KudomonType.ROCK)) {
                    effectiveness = 2;
                }
                break;
            case ROCK:
                if (enemyType.equals(KudomonType.ELECTRIC)) {
                    effectiveness = 2;
                }
                break;
            case ELECTRIC:
                if (enemyType.equals(KudomonType.WATER)) {
                    effectiveness = 2;
                }
                break;
            case PSYCHIC:
                if (!enemyType.equals(KudomonType.PSYCHIC)) {
                    effectiveness = 2;
                }
                break;

        }
        return effectiveness;
    }

}

