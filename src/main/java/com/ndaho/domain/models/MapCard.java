package com.ndaho.domain.models;

import com.ndaho.exception.OutOfLimitsException;

import java.util.HashMap;

public class MapCard {

    //Limitation maximal de la carte
    private Position limitPosition;
    private HashMap<Position, Box> positionBoxHashMap;

    public MapCard(Position limitPosition, HashMap<Position, Box> positionBoxHashMap) {
        this.limitPosition = limitPosition;
        this.positionBoxHashMap = positionBoxHashMap;
    }

    public HashMap<Position, Box> getPositionBoxHashMap() {
        return positionBoxHashMap;
    }

    public void setPositionBoxHashMap(HashMap<Position, Box> positionBoxHashMap) {
        this.positionBoxHashMap = positionBoxHashMap;
    }

    public void setLimitPosition(Position limitPosition) {
        this.limitPosition = limitPosition;
    }

    /**
     * @param position
     * @return the box by the given position
     * @throws OutOfLimitsException
     */
    public Box getBox(Position position) throws OutOfLimitsException {
        Position limitInf = new Position(0, 0);
        if (position.isGreaterThan(limitPosition) || position.isLesserThan(limitInf)) {
            throw new OutOfLimitsException("The box with " + position.toString() + " is out of limit");
        }
        return positionBoxHashMap.get(position);
    }
}
