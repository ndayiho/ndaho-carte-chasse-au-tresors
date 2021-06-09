package com.ndaho.service;

import com.ndaho.domain.models.Adventurer;
import com.ndaho.domain.models.Box;
import com.ndaho.domain.models.Position;
import com.ndaho.exception.OutOfLimitsException;
import com.ndaho.exception.UnknownActionException;
import com.ndaho.exception.UnknownOrientationException;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public interface GameService {

    void start(HashMap<Position, Box> mapPositionBox, HashMap<String, Adventurer> adventurers, Position limit) throws OutOfLimitsException;

    HashMap<String, Adventurer> getAdventurers(List<String> fileLines) throws UnknownOrientationException, UnknownActionException;

    HashMap<Position, Box> initiateMapBoxes(List<String> fileLines);

    void printResults(HashMap<String, Adventurer> adventurers, List<String> fileLines) throws IOException;
}
