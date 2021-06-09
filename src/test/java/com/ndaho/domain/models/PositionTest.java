package com.ndaho.domain.models;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


class PositionTest {

    @Test
    void isGreaterThan_shouldReturnTrue_withXaxis() {
        Position position1 = new Position(2, 2);
        Position position2 = new Position(1, 2);
        position1.isGreaterThan(position2);
        assertThat(position1.isGreaterThan(position2)).isTrue();
    }

    @Test
    void isGreaterThan_shouldReturnTrue_withYaxis() {
        Position position1 = new Position(3, 2);
        Position position2 = new Position(3, 1);
        position1.isGreaterThan(position2);
        assertThat(position1.isGreaterThan(position2)).isTrue();
    }

    @Test
    void isLesserThan_shouldReturnTrue_withXaxis() {
        Position position1 = new Position(2, 2);
        Position position2 = new Position(4, 2);
        position1.isGreaterThan(position2);
        assertThat(position1.isLesserThan(position2)).isTrue();
    }

    @Test
    void isLesserThan_shouldReturnTrue_withYaxis() {
        Position position1 = new Position(3, 0);
        Position position2 = new Position(3, 1);
        position1.isGreaterThan(position2);
        assertThat(position1.isLesserThan(position2)).isTrue();
    }

    @Test
    void compareTo_shouldReturnZero_WithTwoEqualsPositions() {
        Position position1 = new Position(3, 3);
        Position position2 = new Position(3, 3);
        assertThat(position1.compareTo(position2)).isEqualTo(0);
    }

    @Test
    void compareTo_shouldReturnMinusOne_WithNoPositionObject() {
        Position position = new Position(0, 0);
        Object object = new Object();
        assertThat(position.compareTo(object)).isEqualTo(-1);
    }
}