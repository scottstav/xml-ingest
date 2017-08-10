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

import org.apache.geode.pdx.PdxReader;
import org.apache.geode.pdx.PdxSerializable;
import org.apache.geode.pdx.PdxWriter;
import org.json.JSONArray;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Arrays;

/**
 * Created by tenpercent on 8/10/17.
 */
public class GIVectorPdx extends java.util.Vector<Integer> implements PdxSerializable {

    @Override
    public void toData(PdxWriter pw) {
        Integer size = this.size();
        pw.writeInt("size", size);
        Integer idx = 0;
        for (Integer i : this) {
            // treat an array as mapping from indices to values
            pw.writeObject(idx.toString(), i);
            ++idx;
        }
    }

    @Override
    public void fromData(PdxReader pr) {
        Integer size = pr.readInt("size");
        this.ensureCapacity(size);
        for (Integer i = 0; i < size; ++i) {
            this.addElement(pr.readInt(i.toString()));
        }
    }

    public GIVectorPdx() {
        super();
    }

    public GIVectorPdx(int size) {
        super(size);
    }

    public static GIVectorPdx zeros(int size) {
        GIVectorPdx gv = new GIVectorPdx(size);
        for (int i = 0; i < size; ++i) {
            gv.add(0);
        }
        return gv;
    }

    public static GIVectorPdx ones(int size) {
        GIVectorPdx gv = new GIVectorPdx(size);
        for (int i = 0; i < size; ++i) {
            gv.add(1);
        }
        return gv;
    }

    public void setGarray(Integer[] other) {
        this.removeAllElements();
        this.addAll(Arrays.asList(other));
    }

    public void setJsonArray(JSONArray array) {
        this.removeAllElements();
        this.addAll(array.getMyArrayList());
    }

    public Integer[] getGarray() {
        return (Integer[]) this.elementData;
    }
}
