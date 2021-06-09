package com.ndaho.service.impl;

import com.ndaho.domain.enumerations.Action;
import com.ndaho.domain.models.*;
import com.ndaho.exception.UnknownActionException;
import com.ndaho.exception.UnknownOrientationException;
import com.ndaho.service.GameService;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class GameServiceImplTest {


    @Test
    void start_Test() {

    }

    @Test
    void getAdventurers_shouldReturnCorrectListOfAdventures() throws UnknownOrientationException, UnknownActionException {
        GameService gameServiceTest = new GameServiceImpl();
        List<String> lines = Arrays.asList("C - 3 - 4", "M - 1 - 1", "M - 2 - 2", "T - 0 - 3 - 2", "T - 1 - 3 - 1", "A - Lara - 1 -  1 -  S - AADG");
        HashMap<String, Adventurer> results = gameServiceTest.getAdventurers(lines);

        final Adventurer adventurer = results.get("Lara");
        assertThat(results).containsKey("Lara");
        assertThat(adventurer.getName()).isEqualTo("Lara");
        assertThat(adventurer.getAdvPosition()).isEqualToComparingFieldByField(new Position(1, 1));
        assertThat(adventurer.getActions()).containsExactly(Action.FORWARD, Action.FORWARD, Action.RIGHT, Action.LEFT);
    }

    @Test
    void initiateMapBoxes_shouldInitiateMapOfPositionBoxWithCorrectInfos() {
        GameService gameServiceTest = new GameServiceImpl();
        final List<String> lines = Arrays.asList("C - 3 - 4", "M - 1 - 1", "M - 2 - 2", "T - 0 - 3 - 2", "T - 1 - 3 - 1", "A - Lara - 1 -  1 -  S - AADG");
        final HashMap<Position, Box> mapPositionBox = gameServiceTest.initiateMapBoxes(lines);

        assertThat(mapPositionBox).hasSize(4);
        assertThat(mapPositionBox.keySet()).
                usingRecursiveFieldByFieldElementComparator()
                .contains(new Position(1, 1), new Position(2, 2), new Position(0, 3), new Position(1, 3));

        assertThat(mapPositionBox.values()).
                usingRecursiveFieldByFieldElementComparator()
                .contains(new Mountain(new Position(1, 1)),
                        new Mountain(new Position(2, 2)),
                        new Treasure(new Position(0, 3), 2),
                        new Treasure(new Position(1, 3), 1));
    }

}