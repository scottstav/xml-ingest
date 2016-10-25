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
import no.uib.cipr.matrix.Vector;

import no.uib.cipr.matrix.VectorEntry;
import no.uib.cipr.matrix.sparse.SparseVector;

import java.io.BufferedReader;
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
    BufferedWriter bw = null;
    try {
      bw = new BufferedWriter(new FileWriter("/tmp/accumulate.log"));

      bw.write("=== DEBUG === AGGREGATE SUM ===\n");
      bw.write("=== DEBUG === OBJCLASS === " + value.getClass() + "\n");
      if (value != null && value != QueryService.UNDEFINED) {
        if (value instanceof Number) {
          Number number = (Number) value;
          result += number.doubleValue();
        } else if (value instanceof Vector) {
          isVectorAggregate = true;
          if (value == null) {
            value = new SparseVector(200000, 500);
          }
          VectorSumResult = VectorSumResult.add((Vector) value);
        } else {
//        throw new RuntimeException("Invalid SUM class");
          bw.write("=== DEBUG === INVALCLASS === " + value.getClass() + "\n");
        }
      }

      bw.flush();
    } catch (IOException e) {

    }

  }

  @Override
  public void init() {

  }

  @Override
  public Object terminate() {
    if ( !isVectorAggregate )
      return downCast(result);

    return VectorSumResult;
  }
}
