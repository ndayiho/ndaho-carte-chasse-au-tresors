package com.ndaho.domain.models;

public abstract class Box {
    private final Position position;

    public Box(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }

}
