/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.gemstone.gemfire.vector;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


public class TrainFeaturesReader {
    InputStreamReader inputStreamReader;

    double [] features;
    double [] classIDs;
    //double [] featureVectors;

    int numberReadLines = 0;

    TrainFeaturesReader(InputStream inputStream) {
        this.inputStreamReader = new InputStreamReader(inputStream);
        features = new double[200000];
        classIDs = new double[200000];
        //featureVectors  = new LinkedList<>();
    }

    void readLines() {
        new BufferedReader(inputStreamReader)
                .lines()
                .forEach(this::parseLine);
    }

    private void parseLine(String line) {
        List<String> entries = new LinkedList<>(Arrays.asList(line.split("\\s+")));

        final Integer docId = Integer.parseInt(entries.remove(0));

        // next lines are same for test data reader

        double[] feature = new double[200000];
        final double classID = docId;

        for (String wordIdAndOccurences : entries) {
            final String[] wordIdAndOccurencesSplit = wordIdAndOccurences.split(":");
            final Integer wordId = Integer.parseInt(wordIdAndOccurencesSplit[0]);
            final Integer wordOccurences = Integer.parseInt(wordIdAndOccurencesSplit[1]);
            feature[wordId] = wordOccurences;
        }

        //featureVectors.add(new FeatureVector(feature, classID, numberReadLines));
        ++numberReadLines;
    }
}
