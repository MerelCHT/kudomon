package com.company;

import java.util.ArrayList;
import java.util.List;

public class Trainer {

    private final String name;
    private final Position position;
    private List<Kudomon> kudomonCollection = new ArrayList<>();

    public Trainer(String name, Position position) {
        this.name = name;
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public Position getPosition() {
        return position;
    }

    // In Kudomon-Go a Kudomon is nearby a Trainer if it's no further than 2 steps from the Trainer in either X or Y direction.
    public boolean isNearby(Kudomon kudomon) {
        Position trainerPosition = this.position;
        Position kudomonPosition = kudomon.getPosition();

        if (Math.abs(trainerPosition.getX() - kudomonPosition.getX()) > 2 ||
                Math.abs(trainerPosition.getY() - kudomonPosition.getY()) > 2) {
            return false;
        } else {
            return true;
        }
    }

    public void catchKudomon(Kudomon kudomon) throws Exception {
        if (isNearby(kudomon) && kudomon.getCaughtBy().get(this).getX() != -100) { // It's not necessary to check if both X and Y are -100
            kudomonCollection.add(kudomon);
            kudomon.updateCaughtBy(this, new Position(-100, -100));
        } else {
            throw new Exception("Kudomon is too far away to catch!");
        }
    }

    public int countKudomons() {
        return kudomonCollection.size();
    }

    public List<Kudomon> getKudomonCollection() {
        return kudomonCollection;
    }

    public void clearKudomonCollection() {
        kudomonCollection.clear();
    }

}
