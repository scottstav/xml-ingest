package org.apache.geode.vector;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;
import weka.filters.unsupervised.attribute.StringToWordVector;

/**
 * Created by tenpercent on 8/15/17.
 */
public class StringVectorizer {

    private String string;
    private Collection<String> bagOfWords;

    public StringVectorizer() {}

    private void adoptString(String s) {
        string = s;
    }

    public StringVectorizer(String input) {
        this();
        adoptString(input);
    }

    private Collection<String> splitIntoWords() {
        String[] words = string.split(" ");
        bagOfWords = Arrays.asList(words);
        return bagOfWords;
    }

    private Collection<String> translateIntoAscii() {
        bagOfWords = bagOfWords.stream().map(s -> s.replaceAll("[^a-zA-Z0-9]", "")).collect(Collectors.toList());
        return bagOfWords;
    }

    private Collection<String> filterStopWords() {
        // TBD
        return bagOfWords;
    }

    private Collection<String> filterNonWords() {
        // TBD
        return bagOfWords;
    }

    private Vector vectorizeBagOfWords() {
        // TBD
        StringToWordVector filter = new StringToWordVector();

        return new Vector(bagOfWords.size());
    }

    public Vector toVector() {
        splitIntoWords();
        translateIntoAscii();
        filterNonWords();
        filterStopWords();
        return vectorizeBagOfWords();
    }

    public Vector getVector() {
        return toVector();
    }
}
