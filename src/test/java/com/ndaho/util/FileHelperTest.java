package com.ndaho.util;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class FileHelperTest {


    @Test
    public void readFile_shouldReturnLinesOfFileWithoutComments() {
        final String inputFilePath = "inputFileTest.txt";
        final List<String> lines = FileHelper.readFile(inputFilePath);
        assertThat(lines).containsExactly("C - 3 - 4", "M - 1 - 1", "M - 2 - 2", "T - 0 - 3 - 2", "T - 1 - 3 - 1","A - Lara - 1 -  1 -  S - AADADAGGA");
    }

    @Test
    public void readFile_shouldThrowsException_whenNofileFound() {
        final String inputFilePath = "Hello.txt";
        assertThatThrownBy(() -> {
            FileHelper.readFile(inputFilePath);
        }).isInstanceOfAny(IOException.class, NullPointerException.class);
    }
}
