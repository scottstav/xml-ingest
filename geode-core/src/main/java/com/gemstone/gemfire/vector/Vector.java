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

import java.io.Serializable;
import java.util.Arrays;

public class Vector implements Serializable {

    double [] data;
    int size;

    public Vector(int size){
        data = new double[size];
        this.size=size;

    }

    public Vector plus(Vector v2){
        Vector sum = new Vector(size);
        for (int i = 0; i < size; i++) {
            sum.setData(i,getData(i) + v2.getData(i));
        }
        return sum;
    }

    public double dot(Vector v2){
        double sum=0;
        for(int i = 0; i < size; i++){
            sum += getData(i) * v2.getData(i);
        }
        return sum;
    }

    public void setData(int index, double value) {
        data[index] = value;
    }

    public double getData(int index){
        return data[index];
    }

    @Override
    public String toString() {
        return Arrays.toString(data);
    }
}

