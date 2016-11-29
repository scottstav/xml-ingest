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
package org.apache.geode.cache.query.internal.aggregate;

import org.apache.geode.cache.query.QueryService;
import com.fasterxml.jackson.annotation.JsonFormat;
import no.uib.cipr.matrix.DenseVector;
import com.gemstone.gemfire.extra.CustomExample;
import no.uib.cipr.matrix.Vector;
import no.uib.cipr.matrix.VectorEntry;
import java.io.PrintWriter;
import java.lang.*;
import no.uib.cipr.matrix.sparse.SparseVector;
import com.gemstone.gemfire.vector.Vector;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
/**
 * Computes the sum for replicated & PR based queries.
 * 
 *
 */
public class Sum extends AbstractAggregator {

  private boolean isVectorAggregate = false;
  private double result = 0;
  private Vector VectorSumResult;
  public Sum() {
    //...
  }
  @Override
  public void accumulate(Object value) {
      try (BufferedWriter bw = new BufferedWriter(new FileWriter("/tmp/accumulate" + Thread.currentThread().getId() + ".log", true))) {
        bw.write("=== DEBUG === entered aggregate sum ===\n");
        bw.write("=== DEBUG === value class is === " + value.getClass() + "\n");
<<<<<<< HEAD:geode-core/src/main/java/org/apache/geode/cache/query/internal/aggregate/Sum.java
        CustomClass d = new CustomClass();
        final int c1 = d.c;
        try (BufferedWriter bw1 = new BufferedWriter(new FileWriter("/tmp/testclass" + Thread.currentThread().getId() + ".log", true))) {
          bw1.write("=== DEBUG === entered custom class ===\n");
          bw1.write(c1);
        }catch (IOException e){
          //..
=======

<<<<<<< HEAD:geode-core/src/main/java/org/apache/geode/cache/query/internal/aggregate/Sum.java
        CustomExample ce = new CustomExample();
        try (BufferedWriter bw1 = new BufferedWriter(new FileWriter("/tmp/custom" + Thread.currentThread().getId() + ".log", true))) {
          bw1.write(String.format("=== DEBUG === custom class value %d\n", ce.value));
            SparseVector sv = (SparseVector) ce.svalue;
        } catch (IOException e) {
          // ...
>>>>>>> try loading custom class as suggested by Dr Gubanov:geode-core/src/main/java/com/gemstone/gemfire/cache/query/internal/aggregate/Sum.java
        }

=======
>>>>>>> try to sum dense vectors in server:geode-core/src/main/java/com/gemstone/gemfire/cache/query/internal/aggregate/Sum.java
        if (value != null && value != QueryService.UNDEFINED) {
          if (value instanceof Number) {
            bw.write("=== DEBUG === value is a number === " + value.getClass() + "\n");
            Number number = (Number) value;
            result += number.doubleValue();
<<<<<<< HEAD:geode-core/src/main/java/org/apache/geode/cache/query/internal/aggregate/Sum.java
          } else if (value instanceof SparseVector
                  || Objects.equals(value.getClass().getSuperclass().toString(),
                                    no.uib.cipr.matrix.sparse.SparseVector.class.toString())
                  ) {
            bw.write("=== DEBUG === value is a sparse vector === " + value.getClass() + "\n");
//
//
            try {
              Class c = value.getClass().getClassLoader().loadClass("no.uib.cipr.matrix.sparse.SparseVector");

              SparseVector svvalue = (SparseVector) value;
              isVectorAggregate = true;
              if (VectorSumResult == null) {
                VectorSumResult = new SparseVector(200000, 500);
              }
              VectorSumResult = VectorSumResult.add(svvalue);
              bw.write("=== DEBUG === add has been called");
            } catch (ClassNotFoundException e) {
              bw.write("=== DEBUG === class not found");
            }
=======
          } else if (value instanceof com.gemstone.gemfire.vector.Vector) {
            bw.write("=== DEBUG === value is a vector === " + value.getClass() + "\n");
>>>>>>> try to sum dense vectors in server:geode-core/src/main/java/com/gemstone/gemfire/cache/query/internal/aggregate/Sum.java

            Vector svvalue = (Vector) value;
            isVectorAggregate = true;
            if (VectorSumResult == null) {
              VectorSumResult = new Vector(200000);
            }
            VectorSumResult = VectorSumResult.plus(svvalue);
          } else {
            // ... unknown object
          }
        }
      } catch (IOException e) {
        // TODO do something
      }
  }

  @Override
  public void init() {

  }

  @Override
  public Object terminate() {
    if ( !isVectorAggregate )
      return downCast(result);

    if (VectorSumResult != null)
      return VectorSumResult;

    return 42;
  }
}
