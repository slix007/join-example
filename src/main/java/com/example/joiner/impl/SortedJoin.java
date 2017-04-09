package com.example.joiner.impl;

import java.util.ArrayList;
import java.util.List;

/**
 * Assume that the rightTable is sorted.
 * Use binary tree search on the rightTable.
 *
 * Created by Sergey Shurmin on 4/9/17.
 */
public class SortedJoin implements Join {

    @Override
    public List<String> joinAll(List<String> leftTable, List<String> rightTable, Integer leftColumn, Integer rightCoumn) {
        return checkSortedJoin(leftTable, rightTable, leftColumn, rightCoumn);
    }

    /**
     * <p>input columns:</p>
     * ID;Name;GuessPeopleCount<br>
     * ID;Name;Sex;PeoplesCount;WhenPeoplesCount;Source<br>
     *
     * <p>output columns:</p>
     * ID;Name;GuessPeopleCount;ID;Name;Sex;PeoplesCount;WhenPeoplesCount;Source
     */
    private List<String> checkSortedJoin(List<String> leftTable, List<String> sortedRightTable,
                                         Integer leftColumn, Integer rightCoumn) {
        // each loop increase the iterations counter
        int iterations = 0;
        List<String> result = new ArrayList<>();

        for (int mainIndex = 0; mainIndex < leftTable.size(); mainIndex++) {
            iterations++;
            //ID;Name;GuessPeopleCount
            final String leftRow = leftTable.get(mainIndex);

            // All matched tables on rightFile
            iterations += findAllRowsInSorted(result,
                    sortedRightTable, iterations, leftRow, leftColumn, rightCoumn);

        }

        System.out.println("Sorted iterations count: " + iterations);

        return result;
    }

    private int findAllRowsInSorted(List<String> resultTable, List<String> sorted, int passedIterations, String leftRow,
                                       Integer leftColumn, Integer rightCoumn) {
        int iterations = passedIterations;
        final String nameLeft = getField(leftRow, leftColumn);

        // 1. find the matching row
        iterations++; // first iteration
        int currentIndex = sorted.size() / 2;
        int rightIndex = sorted.size();
        int leftIndex = 0;
        String rightRow = sorted.get(currentIndex);
        String fieldValue = getField(rightRow, rightCoumn);

        while (!nameLeft.equals(fieldValue)) {
            iterations++;
            if (nameLeft.compareTo(fieldValue) < 0) {
                rightIndex = currentIndex;
            } else {
                leftIndex = currentIndex;
            }

            currentIndex = calcNextCurrentIndex(currentIndex, rightIndex, leftIndex);
            rightRow = sorted.get(currentIndex);
            fieldValue = getField(rightRow, rightCoumn);
        }
        resultTable.add(String.format("%s;%s;%s", iterations, leftRow, rightRow));

        // 2. iterate on previous rows to find duplicates
        int indexBefore = currentIndex - 1;
        rightRow = sorted.get(indexBefore);
        fieldValue = getField(rightRow, rightCoumn);
        while (nameLeft.equals(fieldValue)) {
            iterations++;
            resultTable.add(String.format("%s;%s;%s", iterations, leftRow, rightRow));

            indexBefore--;
            rightRow = sorted.get(indexBefore);
            fieldValue = getField(rightRow, rightCoumn);
        }
        // 3. iterate on following rows to find duplicates
        int indexAfter = currentIndex + 1;
        rightRow = sorted.get(indexAfter);
        fieldValue = getField(rightRow, rightCoumn);
        while (nameLeft.equals(fieldValue)) {
            iterations++;
            resultTable.add(String.format("%s;%s;%s", iterations, leftRow, rightRow));

            indexAfter++;
            rightRow = sorted.get(indexAfter);
            fieldValue = getField(rightRow, rightCoumn);
        }

        return iterations;
    }

    private int calcNextCurrentIndex(int currentIndex, int rightIndex, int leftIndex) {
        int nextCurrectIndex =  leftIndex + (rightIndex - leftIndex) / 2;
        if (nextCurrectIndex == currentIndex) {
            throw new IllegalArgumentException("The right table have no matches or it is not sorted.");
        }
        return nextCurrectIndex;
    }

}
