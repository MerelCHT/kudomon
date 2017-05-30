package com.company;

import org.junit.Test;

import java.util.HashMap;

import static com.company.Kudomon.KudomonType.ELECTRIC;
import static com.company.Kudomon.KudomonType.GRASS;
import static org.junit.Assert.*;

public class TrainerTest {

    Position trainer1Position = new Position(1, 2);
    Trainer trainer1 = new Trainer("Alice", trainer1Position);

    Position trainer2Position = new Position(2, 1);
    Trainer trainer2 = new Trainer("Bob", trainer2Position);

    HashMap<Trainer, Position> caughtBy = new HashMap<>();

    @Test
    public void shouldCreateNewTrainerWithPosition() {
        assertEquals("Alice", trainer1.getName());
        assertEquals(trainer1Position, trainer1.getPosition());
    }

    @Test
    public void shouldCheckIfKudomonIsNearby() {
        // Given
        HashMap<Trainer, Position> caughtBy1 = new HashMap<>();
        HashMap<Trainer, Position> caughtBy2 = new HashMap<>();
        Position kudomon1Position = new Position(0, 0);
        Position kudomon2Position = new Position(5, 2);

        Kudomon kudomon1 = new Kudomon("Sourbulb", GRASS, kudomon1Position, 100, 20, caughtBy1);
        Kudomon kudomon2 = new Kudomon("Chikapu", ELECTRIC, kudomon2Position, 110, 25, caughtBy2);

        // Then
        assertTrue(trainer1.isNearby(kudomon1));
        assertFalse(trainer1.isNearby(kudomon2));
    }

    @Test
    public void shouldCatchNearbyKudomon() throws Exception {
        // Given
        Position kudomon1Position = new Position(0, 0);
        caughtBy.put(trainer1, kudomon1Position);
        Kudomon kudomon1 = new Kudomon("Sourbulb", GRASS, kudomon1Position, 100, 20, caughtBy);

        assertEquals(0, trainer1.countKudomons());

        // When
        trainer1.catchKudomon(kudomon1);

        // Then
        assertEquals(1, trainer1.countKudomons());
        assertEquals("Sourbulb", trainer1.getKudomonCollection().get(0).getSpecies());
    }

    @Test(expected = Exception.class)
    public void shouldNotCatchKudomonThatIsTooFarAway() throws Exception {
        // Given
        HashMap<Trainer, Position> caughtBy = new HashMap<>();
        Position kudomon2Position = new Position(5, 2);
        caughtBy.put(trainer1, kudomon2Position);
        Kudomon kudomon2 = new Kudomon("Chikapu", ELECTRIC, kudomon2Position, 110, 25, caughtBy);

        // When & Then
        trainer1.catchKudomon(kudomon2);
    }

    @Test
    public void shouldAllowTwoTrainersToCatchSameKudomon() throws Exception {
        // Given
        Position kudomon1Position = new Position(0, 0);
        caughtBy.put(trainer1, kudomon1Position);
        caughtBy.put(trainer2, kudomon1Position);
        Kudomon kudomon1 = new Kudomon("Sourbulb", GRASS, kudomon1Position, 100, 20, caughtBy);

        assertEquals(0, trainer1.countKudomons());
        assertEquals(0, trainer2.countKudomons());

        trainer1.catchKudomon(kudomon1);
        trainer2.catchKudomon(kudomon1);

        // Then
        assertEquals(1, trainer1.countKudomons());
        assertEquals("Sourbulb", trainer1.getKudomonCollection().get(0).getSpecies());

        assertEquals(1, trainer2.countKudomons());
        assertEquals("Sourbulb", trainer2.getKudomonCollection().get(0).getSpecies());
    }
}