package com.example.joiner;

import com.example.joiner.impl.SortedJoin;
import com.example.joiner.impl.UnsortedJoin;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * Created by Sergey Shurmin on 4/8/17.
 */
public class JoinerExample {

    public static void main(String[] args) throws IOException {

        //ID;Name;GuessPeopleCount
        final List<String> namesForCompare = Files.readAllLines(Paths.get("namesForCompare.csv"));
        //ID;Name;Sex;PeoplesCount;WhenPeoplesCount;Source
        final List<String> sorted = Files.readAllLines(Paths.get("russian_names_sorted.csv"));
        final List<String> unsorted = Files.readAllLines(Paths.get("russian_names_unsorted.csv"));

        final UnsortedJoin unsortedJoin = new UnsortedJoin();
        final List<String> unsortedResult = unsortedJoin.joinAll(namesForCompare, unsorted, 1, 1);
        // output columns:
        // ID;Name;GuessPeopleCount;ID;Name;Sex;PeoplesCount;WhenPeoplesCount;Source
        System.out.println("Unsorted results:");
        System.out.println("FoundOnIteration;ID;Name;GuessPeopleCount;ID;Name;Sex;PeoplesCount;WhenPeoplesCount;Source");
        unsortedResult.forEach(System.out::println);


        final SortedJoin sortedJoin = new SortedJoin();
        final List<String> sortedResult = sortedJoin.joinAll(namesForCompare, sorted, 1, 1);
        System.out.println("Sorted results:");
        System.out.println("FoundOnIteration;ID;Name;GuessPeopleCount;ID;Name;Sex;PeoplesCount;WhenPeoplesCount;Source");
        sortedResult.forEach(System.out::println);

        System.out.println("=============================================");
        System.out.println("All unsorted iterations count: " + unsortedJoin.getLastIterationsCount());
        System.out.println("All sorted iterations count: " + sortedJoin.getLastIterationsCount());


    }


}
