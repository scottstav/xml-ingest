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
import org.apache.geode.vector.Vector;

import java.lang.*;

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
        if (value != null && value != QueryService.UNDEFINED) {
          if (value instanceof Number) {
            bw.write("=== DEBUG === value is a number === " + value.getClass() + "\n");
            Number number = (Number) value;
            result += number.doubleValue();
          } else if (value instanceof Vector) {
            bw.write("=== DEBUG === value is a vector === " + value.getClass() + "\n");

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
