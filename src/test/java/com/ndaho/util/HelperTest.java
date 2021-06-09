package com.ndaho.util;

import com.ndaho.domain.enumerations.Action;
import com.ndaho.domain.enumerations.Orientation;
import com.ndaho.domain.models.Position;
import com.ndaho.exception.UnknownActionException;
import com.ndaho.exception.UnknownOrientationException;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class HelperTest {

    @Test
    void getPositionFromStringArray_shouldReturnCorrectPosition() {
        String[] values = new String[]{"C", "3", "4", "3", "grtgrtg"};
        Position position = Helper.getPositionFromStringArray(values, Pair.of(1, 2));
        assertThat(position.getX()).isEqualTo(3);
        assertThat(position.getY()).isEqualTo(4);
    }

    @Test
    void getOrientationFromString_shouldReturnCorrectOrientation() throws UnknownOrientationException {
        assertThat(Helper.getOrientationFromString("S")).isEqualTo(Orientation.SOUTH);
    }

    @Test
    void getOrientationFromString_shouldThrowsUnknownOrientationException() {
        assertThatThrownBy(() -> {
            Helper.getOrientationFromString("t");
        }).isInstanceOfAny(UnknownOrientationException.class);
    }

    @Test
    void getActionsFromString_shouldReturnCorrectActions() throws UnknownActionException {
        List<Action> results = Helper.getActionsFromString("AADG");
        assertThat(results).containsExactly(Action.FORWARD, Action.FORWARD, Action.RIGHT, Action.LEFT);
    }


    @Test
    void getActionsFromString_shouldThrowsUnknownActionException() {
        assertThatThrownBy(() -> {
            Helper.getActionsFromString("AATESTD");
        }).isInstanceOfAny(UnknownActionException.class);
    }
}