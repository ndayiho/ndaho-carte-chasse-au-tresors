package com.ndaho.domain.models;

public class Treasure extends Box {
    private int treasuresNumbers;

    public Treasure(Position position, int treasuresNumbers) {
        super(position);
        this.treasuresNumbers = treasuresNumbers;
    }

    public int getTreasuresNumbers() {
        return treasuresNumbers;
    }

    public void setTreasuresNumbers(int treasuresNumbers) {
        this.treasuresNumbers = treasuresNumbers;
    }

    public int pickUpTreasure() {
        return 0;
    }

}