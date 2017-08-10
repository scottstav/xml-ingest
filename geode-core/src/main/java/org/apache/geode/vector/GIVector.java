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

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Created by tenpercent on 8/10/17.
 */
public class GIVector extends java.util.Vector<Integer> implements Serializable {
    private static final long serialVersionUID = -3023644046086741533L;

    private void writeObject(ObjectOutputStream oos) throws IOException {
        Integer size = this.size();
        oos.writeObject(size);
        for (Integer i : this) {
            oos.writeObject(i);
        }
    }

    private void readObject(ObjectInputStream ois) throws IOException {
        Integer size = ois.readInt();
        this.ensureCapacity(size);
        for (int i = 0; i < size; ++i) {
            this.addElement(ois.readInt());
        }
    }

    public GIVector(int size) {
        super(size);
    }

    public static GIVector zeros(int size) {
        GIVector gv = new GIVector(size);
        for (int i = 0; i < size; ++i) {
            gv.add(0);
        }
        return gv;
    }

    public static GIVector ones(int size) {
        GIVector gv = new GIVector(size);
        for (int i = 0; i < size; ++i) {
            gv.add(1);
        }
        return gv;
    }

}
