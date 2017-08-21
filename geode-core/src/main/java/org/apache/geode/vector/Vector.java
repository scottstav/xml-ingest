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

package org.apache.geode.vector;

import org.apache.geode.DataSerializable;
import org.apache.geode.DataSerializer;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;

public class Vector implements DataSerializable {

    private static final long serialVersionUID = -3367046450984931779L;

    @Override
    public void toData(DataOutput out) throws IOException {
        out.writeInt(size);
        DataSerializer.writeDoubleArray(data, out);
    }

    @Override
    public void fromData(DataInput in) throws IOException {
        size = in.readInt();
        data = DataSerializer.readDoubleArray(in);
    }

    public double[] getData() {
        return data;
    }

    public void setData(double[] data) {
        this.data = data;
    }

    double [] data;

    public int getSize() {
        return size;
    }

    int size;

    public Vector(int size){
        data = new double[size];
        Arrays.fill(data, 0.0);
        this.size = size;

    }

    public Vector plus(Vector v2){
        Vector sum = new Vector(size);
        for (int i = 0; i < size; i++) {
            sum.setData(i,getData(i) + v2.getData(i));
        }
        return sum;
    }

    public Vector difference(Vector v2){
        Vector minus = new Vector(size);
        for (int i = 0; i < size; i++){
            minus.setData(i, getData(i) - v2.getData(i));
        }

        return minus;
    }

    public Vector multiplyByScalar(double c){
        Vector times = new Vector(size);
        for (int i = 0; i < size; i++){
            times.data[i] = c * getData(i);
        }

        return times;
    }

    public boolean equals(Vector v2) {
        Vector eq = new Vector(size);
        for (int i = 0; i < size; i++) {
            int c = Double.compare(this.getData(i), v2.getData(i));
            if (c != 0)
                return false;
        }

        return true;

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

    public void setDataFromArray(double[] array) {
        this.data = array.clone();
        this.size = this.data.length;
    }

    public double getData(int index){
        return data[index];
    }

    @Override
    public String toString() {
        return Arrays.toString(data);
    }
}

