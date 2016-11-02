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
import java.io.PrintWriter;
import java.lang.*;
import no.uib.cipr.matrix.sparse.SparseVector;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


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
    try {
      BufferedWriter bw = null;
      try {
        bw = new BufferedWriter(new FileWriter("/tmp/accumulate.log", true));

        bw.write("=== DEBUG === entered aggregate sum ===\n");
        bw.write("=== DEBUG === value class is === " + value.getClass() + "\n");
        if (value != null && value != QueryService.UNDEFINED) {
          if (value instanceof Number) {
//            bw.write("=== DEBUG === value is a number === " + value.getClass() + "\n");
            Number number = (Number) value;
            result += number.doubleValue();
          } else if (value instanceof SparseVector) {
//            bw.write("=== DEBUG === value is a sparse vector === " + value.getClass() + "\n");
//
//            SparseVector svvalue = (SparseVector) value;
//            isVectorAggregate = true;
//            if (VectorSumResult == null) {
//              bw.write("=== DEBUG === create sum result === " + value.getClass() + "\n");
//              VectorSumResult = new SparseVector(200000, 500);
//            }
//
//            VectorSumResult = VectorSumResult.add(svvalue);
          } else {
//            bw.write("=== DEBUG === value is a sparse vector === " + value.getClass() + "\n");
//
//            SparseVector svvalue = (SparseVector) value;
//            isVectorAggregate = true;
//            if (VectorSumResult == null) {
//              bw.write("=== DEBUG === create sum result === " + value.getClass() + "\n");
//              VectorSumResult = new SparseVector(200000, 500);
//            }
//            VectorSumResult = VectorSumResult.add(svvalue);
//
//            bw.write("=== DEBUG === unknown class === " + value.getClass() + "\n");
          }
        }

        bw.flush();
      } catch (IOException e) {
        PrintWriter out = null;
        try {
          out = new PrintWriter(new FileWriter("0.log", true));
        } catch (IOException e1) {
          e1.printStackTrace();
        }
        e.printStackTrace(out);
      }
    } catch (Exception e) {
      PrintWriter out = null;
      try {
        out = new PrintWriter(new FileWriter("0.log", true));
      } catch (IOException e1) {
        e1.printStackTrace();
      }
      e.printStackTrace(out);
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
