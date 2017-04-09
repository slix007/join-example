package com.example.joiner.impl;

import java.util.List;

/**
 * Created by Sergey Shurmin on 4/9/17.
 */
public interface Join {

    /**
     * Join two lists. Matching by column.
     *
     * @param leftTable
     * @param rightTable
     * @param leftColumn
     * @param rightCoumn
     *
     * @return joined table
     */
    List<String> joinAll(List<String> leftTable, List<String> rightTable,
                 Integer leftColumn, Integer rightCoumn);

    default String getField(String leftRow, int column) {
        return leftRow.split(";")[column];
    }

}
