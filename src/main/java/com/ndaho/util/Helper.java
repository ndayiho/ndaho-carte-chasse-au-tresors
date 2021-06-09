package com.ndaho.util;

import com.ndaho.domain.enumerations.Action;
import com.ndaho.domain.enumerations.Orientation;
import com.ndaho.domain.models.Position;
import com.ndaho.exception.UnknownActionException;
import com.ndaho.exception.UnknownOrientationException;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.List;


public class Helper {

    public static Position getPositionFromStringArray(String[] splitLine, Pair<Integer, Integer> positionIndexes) {
        final int x = Integer.parseInt(splitLine[positionIndexes.getLeft()]);
        final int y = Integer.parseInt(splitLine[positionIndexes.getRight()]);
        Position position = new Position(x, y);
        return position;
    }

    public static Orientation getOrientationFromString(String oriAsString) throws UnknownOrientationException {
        Orientation orientation = null;
        switch (oriAsString) {
            case "S":
                orientation = Orientation.SOUTH;
                break;
            case "N":
                orientation = Orientation.NORTH;
                break;
            case "E":
                orientation = Orientation.EAST;
                break;
            case "O":
                orientation = Orientation.WEST;
                break;
            default:
                // cas de de n'ai pas avoir l'oriantation
                throw new UnknownOrientationException("Unknown orientation " + oriAsString);
        }

        return orientation;
    }

    public static List<Action> getActionsFromString(String actionsAsString) throws UnknownActionException {
        List<Action> actions = new ArrayList<>();
        final String[] splitValue = StringUtils.replace(actionsAsString, " ", "").split("");
        for (String value : splitValue) {

            switch (value) {
                case "A":
                    actions.add(Action.FORWARD);
                    break;
                case "D":
                    actions.add(Action.RIGHT);
                    break;
                case "G":
                    actions.add(Action.LEFT);
                    break;
                default:
                    throw new UnknownActionException("Unknown action " + value);
            }
        }

        return actions;
    }
}
