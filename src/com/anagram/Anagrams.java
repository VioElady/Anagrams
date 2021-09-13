package com.anagram;

import java.io.*;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.*;

public class Anagrams {
    private static String canonicalize(String s) {
        return Stream.of(s.split("")).sorted().collect(Collectors.joining());
    }
    public static List<Set<String>> getAnagrams(Reader reader) {
        Map<String, Set<String>> map = new BufferedReader(reader).lines()
                .flatMap(Pattern.compile("\\W+")::splitAsStream)
                .collect(Collectors.groupingBy(Anagrams::canonicalize, Collectors.toSet()));
        return map.values().stream().filter(list -> list.size() > 1).collect(Collectors.toList());
    }

    public static void main(String[] args) throws IOException {
        InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream("D:\\anagram\\external-sort.txt"));
        getAnagrams(inputStreamReader).forEach(System.out::println);

    }
}

