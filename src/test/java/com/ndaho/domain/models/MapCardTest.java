package com.ndaho.domain.models;

import com.ndaho.exception.OutOfLimitsException;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class MapCardTest {

    @Test
    void getBox_shouldReturnsTheNoNullBoxWithGoodDimensions() throws OutOfLimitsException {
        HashMap<Position, Box> positionBoxHashMap = new HashMap<>();
        final Position position1 = new Position(2, 2);
        final Box box1 = new Treasure(position1, 2);
        positionBoxHashMap.put(position1, box1);
        MapCard carte = new MapCard(new Position(3, 4), positionBoxHashMap);
        Box box = carte.getBox(position1);

        //not null test and compare the fields values
        assertThat(box).isNotNull()
                .isEqualToComparingFieldByField(box1);
    }

    @Test
    void getBox_shouldThrowsOutOfLimitsException_whenGetBoxWithOutOfLimitPositon() {
        HashMap<Position, Box> positionBoxHashMap = new HashMap<>();
        final Position position1 = new Position(5, 2);
        final Box box1 = new Treasure(position1, 2);
        final Position position2 = new Position(2, 10);
        final Box box2 = new Treasure(position1, 2);

        positionBoxHashMap.put(position1, box1);
        positionBoxHashMap.put(position2, box2);
        MapCard carte = new MapCard(new Position(3, 4), positionBoxHashMap);
        // with X
        assertThatThrownBy(() -> {
            carte.getBox(position1);
        }).isInstanceOfAny(OutOfLimitsException.class);

        // with y
        assertThatThrownBy(() -> {
            carte.getBox(position2);
        }).isInstanceOfAny(OutOfLimitsException.class);
    }
}