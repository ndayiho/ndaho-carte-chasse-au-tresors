package com.ndaho.domain.models;

import com.ndaho.domain.enumerations.Action;
import com.ndaho.domain.enumerations.Orientation;

import java.util.List;

public class Adventurer {
    private String name;
    private Position advPosition;
    private Orientation orientation;
    private List<Action> actions;
    private int currentTresor = 0;


    public Adventurer(String name, Position advPosition, Orientation orientation, List<Action> actions) {
        this.name = name;
        this.advPosition = advPosition;
        this.orientation = orientation;
        this.actions = actions;
    }

    public int getCurrentTresor() {
        return currentTresor;
    }

    public void setCurrentTresor(int currentTresor) {
        this.currentTresor += currentTresor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Position getAdvPosition() {
        return advPosition;
    }

    public void setAdvPosition(Position advPosition) {
        this.advPosition = advPosition;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }

    public List<Action> getActions() {
        return actions;
    }

    public void setActionList(List<Action> actions) {
        this.actions = actions;
    }

    public void move(Action action) {
        action.perform(this);
    }

    /**
     * add new numbers of  treasures
     *
     * @param treasureNumber
     * @return total number of treasure
     */
    public int addTreasure(int treasureNumber) {
        return currentTresor += treasureNumber;
    }

    public void moveBack() {
        Action.WAIT.perform(this);
    }

}
