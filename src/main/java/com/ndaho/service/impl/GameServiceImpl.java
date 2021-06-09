package com.ndaho.service.impl;

import com.ndaho.domain.enumerations.Action;
import com.ndaho.domain.enumerations.Orientation;
import com.ndaho.domain.models.*;
import com.ndaho.exception.UnknownActionException;
import com.ndaho.exception.UnknownOrientationException;
import com.ndaho.service.GameService;
import com.ndaho.util.Helper;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class GameServiceImpl implements GameService {

    private static final Logger logger = LogManager.getLogger(GameServiceImpl.class);
    private int nbTours;
    private StringBuilder report = new StringBuilder();

    public GameServiceImpl() {
        //
    }

    @Override
    public void start(HashMap<Position, Box> mapPositionBox, HashMap<String, Adventurer> adventurers, Position limit) {
        logger.info("Start game with a map " + limit.getX() + "x" + limit.getY() + " with " + adventurers.size() + " adventures");
        MapCard carte = new MapCard(limit, mapPositionBox);

        this.nbTours = 0;
        Boolean fin = false;

        while (!fin) {
            fin = true;
            for (Map.Entry<String, Adventurer> entry : adventurers.entrySet()) {
                final Adventurer adventurer = entry.getValue();
                if (nbTours < adventurer.getActions().size()) {
                    final Action nextAction = adventurer.getActions().get(nbTours);
                    if (nextAction != null && nextAction.check(adventurer, carte, adventurers)) {
                        adventurer.move(nextAction);
                        final Box currrentAdventureBox = mapPositionBox.get(adventurer.getAdvPosition());
                        if (currrentAdventureBox instanceof Treasure) {
                            final int treasures = ((Treasure) currrentAdventureBox).getTreasuresNumbers();
                            adventurer.addTreasure(treasures);
                            //plus de tresor --> deja ramassé
                            ((Treasure) currrentAdventureBox).setTreasuresNumbers(0);
                        }
                        fin = fin && false;
                    }
                }
            }

            nbTours++;
        }
    }


    @Override
    public HashMap<String, Adventurer> getAdventurers(List<String> fileLines) throws UnknownOrientationException, UnknownActionException {
        HashMap<String, Adventurer> adventurersList = new HashMap<>();
        final List<String> adventurersLines = fileLines.stream()
                .filter(line -> line.startsWith("A"))
                .collect(Collectors.toList());

        for (String line : adventurersLines) {
            final String[] splitLine = StringUtils.replace(line, " ", "").split("-");
            final String name = splitLine[1];
            final Position position = Helper.getPositionFromStringArray(splitLine, Pair.of(2, 3));
            final Orientation orientation = Helper.getOrientationFromString(splitLine[4]);
            final List<Action> actions = Helper.getActionsFromString(splitLine[5]);
            Adventurer adventurer = new Adventurer(name, position, orientation, actions);
            adventurersList.put(name, adventurer);
        }

        return adventurersList;
    }

    @Override
    public HashMap<Position, Box> initiateMapBoxes(List<String> fileLines) {
        HashMap<Position, Box> mapPositionBox = new HashMap<>();

        for (String line : fileLines) {
            if (line.startsWith("T")) {
                addTreasure(line, mapPositionBox);
            } else if (line.startsWith("M")) {
                addMountain(line, mapPositionBox);
            }
        }
        return mapPositionBox;
    }

    @Override
    public void printResults(HashMap<String, Adventurer> adventurers, List<String> fileLines) throws IOException {
        StringBuilder report = new StringBuilder();
        //todo --> maybe use file.properties --> configuration exterieur
        File file = new File("result.txt");
        fileLines.stream().filter(v -> !v.startsWith("A") && !v.startsWith("T")).forEach(value -> report.append(value + "\n"));

        adventurers.values().forEach(adventurer -> report.append(
                "A - " + adventurer.getName() + " - "
                        + adventurer.getAdvPosition().getX() + " - "
                        + adventurer.getAdvPosition().getY() + " - " + adventurer.getOrientation() + " - " + adventurer.getCurrentTresor() + "\n"));

        FileUtils.write(file, report, StandardCharsets.UTF_8);
    }

    private void addTreasure(String line, HashMap<Position, Box> mapCase) {
        final String[] splitLine = StringUtils.replace(line, " ", "").split("-");
        final Position position = Helper.getPositionFromStringArray(splitLine, Pair.of(1, 2));
        final int treasures = Integer.parseInt(splitLine[3]);
        Treasure treasure = new Treasure(position, treasures);
        mapCase.put(position, treasure);
    }

    private void addMountain(String line, HashMap<Position, Box> mapCase) {
        final String[] splitLine = StringUtils.replace(line, " ", "").split("-");
        final Position position = Helper.getPositionFromStringArray(splitLine, Pair.of(1, 2));
        final Mountain montagne = new Mountain(position);
        mapCase.put(position, montagne);
    }
}
