package com.example.joiner.impl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sergey Shurmin on 4/9/17.
 */
public class UnsortedJoin implements Join {

    @Override
    public List<String> joinAll(List<String> leftTable, List<String> rightTable, Integer leftColumn, Integer rightCoumn) {
        return checkUnsortedJoin(leftTable, rightTable, leftColumn, rightCoumn);
    }

    private List<String> checkUnsortedJoin(List<String> leftTable, List<String> rightTable,
                                           Integer leftColumn, Integer rightCoumn) {
        // each loop increase the iterations counter
        int iterations = 0;
        List<String> result = new ArrayList<>();

        for (int mainIndex = 0; mainIndex < leftTable.size(); mainIndex++) {
            iterations++;
            //ID;Name;GuessPeopleCount
            final String leftRow = leftTable.get(mainIndex);

            iterations += findAllRowsInUnsorted(result, rightTable, leftRow, leftColumn, rightCoumn);
        }
        System.out.println("Unsorted iterations count: " + iterations);

        return result;
    }

    private int findAllRowsInUnsorted(List<String> result, List<String> unsorted, String leftRow,
                                       Integer leftColumn, Integer rightCoumn) {
        String resultRow = "";
        int iterations = 0;
        final String nameLeft = getField(leftRow, leftColumn);

        for (int baseIndex = 0; baseIndex < unsorted.size(); baseIndex++) {
            iterations++;
            final String rightRow = unsorted.get(baseIndex);
            final String nameRight = getField(rightRow, rightCoumn);

            if (nameLeft.equals(nameRight)) {
                resultRow = String.format("%s;%s;%s", iterations, leftRow, rightRow);
                result.add(resultRow);
            }
        }
        return iterations;
    }

}
