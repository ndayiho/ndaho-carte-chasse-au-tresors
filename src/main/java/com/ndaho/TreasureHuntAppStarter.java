package com.ndaho;

import com.ndaho.domain.models.Adventurer;
import com.ndaho.domain.models.Box;
import com.ndaho.domain.models.Position;
import com.ndaho.service.GameService;
import com.ndaho.service.impl.GameServiceImpl;
import com.ndaho.util.FileHelper;
import com.ndaho.util.Helper;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Main class starter!
 */
public class TreasureHuntAppStarter {
    private static final Logger logger = LogManager.getLogger(TreasureHuntAppStarter.class);

    public static void main(String[] args) {
        startGame(args);
    }

    public static void startGame(String[] args) {
        try {
            //1. Get input File
            String inputFilePath = "inputFile.txt";
            if (args.length != 0 && !args[0].trim().isEmpty()) {
                logger.info("Use args to get inputFile");
                inputFilePath = args[0];
            }

            final GameService game = new GameServiceImpl();
            final List<String> fileLines = FileHelper.readFile(inputFilePath);

            //check if there is map and return map limit
            final Position limit = getMapDimensions(fileLines);
            final HashMap<Position, Box> mapPositionBox = game.initiateMapBoxes(fileLines);
            final HashMap<String, Adventurer> adventurers = game.getAdventurers(fileLines);

            if (adventurers == null || adventurers.isEmpty()) {
                logger.error("No adventurers to start the game, please check your input file");
                return;
            }

            //start game
            game.start(mapPositionBox, adventurers, limit);
            //end game
            game.printResults(adventurers, fileLines);

        } catch (Exception e) {
            //todo --> il y a mieux
            logger.error(e.getMessage(), e);
        }
    }

    private static Position getMapDimensions(List<String> fileLines) {
        List<String> listCarte = fileLines.stream()
                .filter(line -> line.startsWith("C"))
                .collect(Collectors.toList());

        if (CollectionUtils.isEmpty(listCarte)) {
            logger.error("No map defined in input file,no game!!? please add a file with map ");
            return null;
        } else if (listCarte.size() > 1) {
            // juste ajout d'un log --> a voir si on peut ajouter un log ou faire carrement une exception
            logger.error("Map conf has many lines in input file ");
        }
        //get map dimentions
        final String[] lineArray = StringUtils.replace(listCarte.get(0), " ", "").split("-");

        return Helper.getPositionFromStringArray(lineArray, Pair.of(1, 2));
    }

}
