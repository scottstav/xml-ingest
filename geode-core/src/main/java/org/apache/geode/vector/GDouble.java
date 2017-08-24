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

public class GDouble implements DataSerializable {
    private Double value;

    public GDouble(Double value) {
        this.value = value;
    }

    @Override
    public void toData(DataOutput out) throws IOException {
        DataSerializer.writeDouble(value, out);
    }

    @Override
    public void fromData(DataInput in) throws IOException, ClassNotFoundException {
        value = DataSerializer.readDouble(in);
    }

    public Double sqrt() {
        return Math.sqrt(value);
    }

    public Double logE() {
        return Math.log(value);
    }

    public Double expE() {
        return Math.exp(value);
    }

    public Double exp2() {
        return Math.pow(2, value);
    }

    public Double exp10() {
        return Math.pow(10, value);
    }

    public Double pow(Double exponent) {
        return Math.pow(value, exponent);
    }

    public Double exp(Double base) {
        return Math.pow(base, value);
    }

    public GDouble gPlusAssign(GDouble other) {
        this.value += other.value;
        return this;
    }

    public GDouble gMulAssign(GDouble other) {
        this.value *= other.value;
        return this;
    }

    public GDouble gMinusAssign(GDouble other) {
        this.value -= other.value;
        return this;
    }

    public GDouble gDivAssign(GDouble other) {
        this.value /= other.value;
        return this;
    }

    // let the show go on some time later
}
