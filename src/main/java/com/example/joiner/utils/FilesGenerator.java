package com.example.joiner.utils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * It's used to generate input files.
 *
 * Created by Sergey Shurmin on 4/8/17.
 */
public class FilesGenerator {

    public static void main(String[] args) throws IOException, URISyntaxException {

//        unsortNames();

        sortNames();
    }

    private static void sortNames() throws IOException {
        final Path namesFilePath = Paths.get("russian_names.csv");

        final List<String> strings = Files.readAllLines(namesFilePath);

        final List<String> sortedList = strings.stream()
                .sorted(Comparator.comparing(o -> o.split(";")[1]))
                .collect(Collectors.toList());

        final Path sortedPath = Paths.get("russian_names_sorted.csv");
        Files.write(sortedPath, sortedList, StandardCharsets.UTF_8,
                StandardOpenOption.CREATE, StandardOpenOption.CREATE);
    }

    private static void unsortNames() throws IOException {
        final Path namesFilePath = Paths.get("russian_names.csv");

        final List<String> strings = Files.readAllLines(namesFilePath);
        final List<String> randomStrings = new ArrayList<>();

        final int size = strings.size();
        List<Integer> added = new ArrayList<>();

        strings.forEach(s -> {
            Integer randomIndex = (int) (Math.random() * size);
            while (added.contains(randomIndex)) {
                randomIndex++;
                if (randomIndex >= size) {
                    randomIndex = 0;
                }
            }

            randomStrings.add(strings.get(randomIndex));
            added.add(randomIndex);
        });

        final Path randomNamesPath = Paths.get("russian_names_unsorted.csv");
        Files.write(randomNamesPath, randomStrings, StandardCharsets.UTF_8,
                StandardOpenOption.CREATE, StandardOpenOption.CREATE);

    }
}
