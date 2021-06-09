package com.ndaho.domain.enumerations;

import com.ndaho.domain.models.Adventurer;
import com.ndaho.domain.models.Position;

import java.util.HashMap;

/**
 * Orientations that can be used
 */
public enum Orientation {
    EAST {
        @Override
        public void turnLeft(Adventurer adventurer) {
            adventurer.setOrientation(Orientation.NORTH);
        }

        @Override
        public void turnRight(Adventurer adventurer) {
            adventurer.setOrientation(Orientation.SOUTH);
        }

        @Override
        public void moveForward(Adventurer adventurer, HashMap<String, Adventurer> adventurers) {
            Position position = new Position(
                    adventurer.getAdvPosition().getX() + 1,
                    adventurer.getAdvPosition().getY());
            adventurer.setAdvPosition(position);

        }
    },
    WEST {
        @Override
        public void turnLeft(Adventurer adventurer) {
            adventurer.setOrientation(Orientation.SOUTH);
        }

        @Override
        public void turnRight(Adventurer adventurer) {
            adventurer.setOrientation(Orientation.NORTH);
        }

        @Override
        public void moveForward(Adventurer adventurer, HashMap<String, Adventurer> adventurers) {
            adventurer.setAdvPosition(new Position(
                    adventurer.getAdvPosition().getX() - 1,
                    adventurer.getAdvPosition().getY()));
        }
    },
    NORTH {
        @Override
        public void turnLeft(Adventurer adventurer) {
            adventurer.setOrientation(Orientation.WEST);
        }

        @Override
        public void turnRight(Adventurer adventurer) {
            adventurer.setOrientation(Orientation.EAST);
        }

        @Override
        public void moveForward(Adventurer adventurer, HashMap<String, Adventurer> adventurers) {
            adventurer.setAdvPosition(new Position(
                    adventurer.getAdvPosition().getX(),
                    adventurer.getAdvPosition().getY() - 1));
        }
    },
    SOUTH {
        @Override
        public void turnLeft(Adventurer adventurer) {
            adventurer.setOrientation(Orientation.EAST);
        }

        @Override
        public void turnRight(Adventurer adventurer) {
            adventurer.setOrientation(Orientation.WEST);
        }

        @Override
        public void moveForward(Adventurer adventurer, HashMap<String, Adventurer> adventurers) {
            adventurer.setAdvPosition(new Position(
                    adventurer.getAdvPosition().getX(),
                    adventurer.getAdvPosition().getY() + 1));
        }
    };

    public abstract void turnRight(Adventurer adventurer);

    public abstract void turnLeft(Adventurer adventurer);

    public abstract void moveForward(Adventurer adventurer, HashMap<String, Adventurer> adventurers);

}
