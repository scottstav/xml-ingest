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

package org.apache.geode.cache.query.internal;

import org.apache.geode.DataSerializable;
import org.apache.geode.DataSerializer;
import org.apache.geode.vector.Vector;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Arrays;

public class NaiveBayesTrainData implements DataSerializable {

    Integer private_id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLabel() {
        return label;
    }

    public void setLabel(Integer label) {
        this.label = label;
    }

    public Vector getData() {
        return data;
    }

    public void setData(Vector data) {
        this.data = data;
    }

    Integer id;
    Integer label;
    Vector data;

    public NaiveBayesTrainData() {

    }

    public NaiveBayesTrainData(Integer id, String[] data) {
        this.private_id = id;
        this.id = Integer.parseInt(data[0]);
        this.label = Integer.parseInt(data[1]);
        String dataString = data[2];

        double[] vector_data = Arrays.stream(dataString.split(",")).mapToDouble(Double::parseDouble).toArray();
        this.data = new Vector(vector_data.length);
        this.data.setDataFromArray(vector_data);
    }

    @Override
    public void toData(DataOutput out) throws IOException {
        DataSerializer.writeInteger(id, out);
        DataSerializer.writeInteger(label, out);
        DataSerializer.writeDoubleArray(data.getData(), out);
    }

    @Override
    public void fromData(DataInput in) throws IOException, ClassNotFoundException {
        id = DataSerializer.readInteger(in);
        label = DataSerializer.readInteger(in);
        this.data.fromData(in);
    }
}
