package com.ndaho.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileHelper {

    private static final Logger logger = LogManager.getLogger(FileHelper.class);

    /**
     * Returns lines in a file by excluding comments
     *
     * @param filePath
     * @return fileLines without comments
     */
    public static List<String> readFile(String filePath) {
        Stream<String> inputStream = getFileFromResourceAsStream(filePath);
        return inputStream
                .filter(line -> !line.startsWith("#"))
                .map(String::trim)
                .collect(Collectors.toList());
    }

    /**
     * Returns a stream of the rows in a file by excluding comments
     *
     * @param fileName
     * @return a Steam of game
     */
    private static Stream<String> getFileFromResourceAsStream(String fileName) {
        Stream<String> inputStream;
        try {
            inputStream = Files.lines(Paths.get(fileName));
            return inputStream;
        } catch (IOException e) {
            logger.debug("Continuer and search file in resources folder");
        }

        try {
            final URI url = ClassLoader.getSystemResource(fileName).toURI();
            return Files.lines(Paths.get(url));
        } catch (IOException | NullPointerException | URISyntaxException e) {
            logger.error("File " + fileName + " not found : " + e.getMessage());
        }
        return null;
    }
}
