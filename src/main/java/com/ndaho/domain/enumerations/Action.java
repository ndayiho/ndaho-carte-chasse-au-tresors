package com.ndaho.domain.enumerations;

/**
 * Actions that can be used
 */

import com.ndaho.domain.models.Adventurer;
import com.ndaho.domain.models.Box;
import com.ndaho.domain.models.MapCard;
import com.ndaho.domain.models.Mountain;
import com.ndaho.exception.OutOfLimitsException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;

public enum Action {

    FORWARD {
        @Override
        public void perform(Adventurer adventurer) {
            adventurer.getOrientation().moveForward(adventurer, null);
        }

        @Override
        public boolean check(Adventurer adv, MapCard carte, HashMap<String, Adventurer> adventurers) {
            Adventurer adventurer = new Adventurer(adv.getName(), adv.getAdvPosition(), adv.getOrientation(), adv.getActions());
            adventurer.setCurrentTresor(adv.getCurrentTresor());
            adventurer.getOrientation().moveForward(adventurer, adventurers);
            try {
                Box currentMapBox = carte.getBox(adventurer.getAdvPosition());
                if (currentMapBox instanceof Mountain) {
                    logger.info("You're in front of a mountain");
                    return false;
                }
            } catch (OutOfLimitsException e) {
                return false;
            }
            return true;
        }
    },
    RIGHT {
        @Override
        public void perform(Adventurer adventurer) {
            adventurer.getOrientation().turnRight(adventurer);
        }

        @Override
        public boolean check(Adventurer adventurer, MapCard carte, HashMap<String, Adventurer> adventurers) {
            return true;
        }
    },
    LEFT {
        @Override
        public void perform(Adventurer adventurer) {
            adventurer.getOrientation().turnLeft(adventurer);
        }

        @Override
        public boolean check(Adventurer adventurer, MapCard carte, HashMap<String, Adventurer> adventurers) {
            return true;
        }
    },
    WAIT {
        @Override
        public void perform(Adventurer adventurer) {

        }

        @Override
        public boolean check(Adventurer adventurer, MapCard carte, HashMap<String, Adventurer> adventurers) {
            return true;
        }

    };

    private static final Logger logger = LogManager.getLogger(Action.class);

    public abstract void perform(Adventurer adventurer);

    public abstract boolean check(Adventurer adventurer, MapCard carte, HashMap<String, Adventurer> adventurers);
}