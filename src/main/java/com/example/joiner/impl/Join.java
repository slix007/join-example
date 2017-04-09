package com.example.joiner.impl;

import java.util.List;

/**
 * Created by Sergey Shurmin on 4/9/17.
 */
public abstract class Join {

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
    abstract List<String> joinAll(List<String> leftTable, List<String> rightTable,
                 Integer leftColumn, Integer rightCoumn);

    protected final String getField(String leftRow, int column) {
        return leftRow.split(";")[column];
    }

    protected int lastIterationsCount = 0;

    public int getLastIterationsCount() {
        return lastIterationsCount;
    }
}
