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
 * Created by Sergey Shurmin on 4/8/17.
 */
public class FilesGenerator {

    public static void main(String[] args) throws IOException, URISyntaxException {

        List<String> lines = new ArrayList<>();

        final int maximum = 10000;


        final List<String> names = Arrays.asList("Сергей", "Иван", "Павел");
        final List<String> actionList = Arrays.asList("взял", "вернул");
        final List<String> deviceList = Arrays.asList("iphone5", "iphone6", "iphone5s", "iphone6s",
                "nexus5", "nexus6", "ipad");

        for (int i = 0; i < 10; i++) {

            final int random = (int) (Math.random() * maximum);
            final String action = actionList.get((int) (Math.random() * actionList.size()));
            final String device = deviceList.get((int) (Math.random() * deviceList.size()));
            final String name = names.get((int) (Math.random() * names.size()));

            lines.add(String.format("%s;%s;%s;%s", random, name, action, device));
        }


        final Path devicesLogPath = Paths.get("devicesLog.csv");
        Files.write(devicesLogPath, lines, StandardCharsets.UTF_8,
                StandardOpenOption.CREATE, StandardOpenOption.APPEND);

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
