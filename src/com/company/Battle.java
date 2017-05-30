package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Battle {

    private List<Kudomon> kudomons = new ArrayList<Kudomon>();

    public Battle(List<Kudomon> kudomons) {
        this.kudomons = kudomons;
    }

    public Kudomon battle() {
        int firstIndex = getRandomKudomonIndex(kudomons);
        Kudomon first = kudomons.get(firstIndex);
        Kudomon second = kudomons.get(1 - firstIndex);

        while (stillAlive(first, second)) {
            if (stillAlive(first, second)) {
                first.attack(second);
            }
            if (stillAlive(first, second)) {
                second.attack(first);
            }
        }

        return first.getHp() > 0 ? first : second;
    }

    private boolean stillAlive(Kudomon first, Kudomon second) {
        return (first.getHp() > 0 && second.getHp() > 0);
    }

    private int getRandomKudomonIndex(List<Kudomon> kudomons) {
        return new Random().nextInt(kudomons.size());
    }
}
