package com.company;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import static com.company.Kudomon.KudomonType.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BattleTest {

    HashMap<Trainer, Position> caughtBy = new HashMap<>();

    @Test
    public void shouldLetTwoKudomonsBattleUntilOneDies() {
        // Given
        Position kudomon1Position = new Position(0, 0);
        Kudomon kudomon1 = new Kudomon("Sourbulb", GRASS, kudomon1Position, 130, 20, caughtBy);

        Position kudomon2Position = new Position(2, 0);
        Kudomon kudomon2 = new Kudomon("Chikapu", ELECTRIC, kudomon2Position, 110, 25, caughtBy);

        Battle battle = new Battle(new ArrayList<>(Arrays.asList(kudomon1, kudomon2)));

        // When
        Kudomon winner = battle.battle();

        // Then
        System.out.println("The winner is: " + winner.getSpecies());
        assertTrue(winner.getHp() > 0);
    }

    @Test
    public void waterShouldBeatFire() {
        // Given
        Position kudomon1Position = new Position(0, 0);
        Kudomon kudomon1 = new Kudomon("Mancharred", FIRE, kudomon1Position, 130, 20, caughtBy);

        Position kudomon2Position = new Position(2, 0);
        Kudomon kudomon2 = new Kudomon("Letuirsq", WATER, kudomon2Position, 110, 25, caughtBy);

        Battle battle = new Battle(new ArrayList<>(Arrays.asList(kudomon1, kudomon2)));

        // When
        Kudomon winner = battle.battle();

        // Then
        assertEquals(WATER, winner.getType());
        assertTrue(winner.getHp() > 0);
    }

    @Test
    public void psychicBeatsAnytype() {
        // Given
        Position kudomon1Position = new Position(0, 0);
        Kudomon kudomon1 = new Kudomon("Ewm", PSYCHIC, kudomon1Position, 130, 20, caughtBy);

        Position kudomon2Position = new Position(2, 0);
        Kudomon kudomon2 = new Kudomon("Letuirsq", WATER, kudomon2Position, 110, 25, caughtBy);

        Battle battle = new Battle(new ArrayList<>(Arrays.asList(kudomon1, kudomon2)));

        // When
        Kudomon winner = battle.battle();

        // Then
        assertEquals(PSYCHIC, winner.getType());
        assertTrue(winner.getHp() > 0);
    }


}