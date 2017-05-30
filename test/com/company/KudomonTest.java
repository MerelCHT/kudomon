package com.company;

import org.junit.Test;

import java.util.HashMap;

import static com.company.Kudomon.KudomonType.GRASS;
import static org.junit.Assert.assertEquals;

public class KudomonTest {

    @Test
    public void shouldCreateNewKudomonWithSpeciesAndTypeAndPosition() {
        // Given
        HashMap<Trainer, Position> caughtBy = new HashMap<>();
        Position position = new Position(0, 0);
        Kudomon kudomon1 = new Kudomon("Sourbulb", GRASS, position, 100, 20, caughtBy);

        // Then
        assertEquals("Sourbulb", kudomon1.getSpecies());
        assertEquals(GRASS, kudomon1.getType());
        assertEquals(position, kudomon1.getPosition());
    }
}