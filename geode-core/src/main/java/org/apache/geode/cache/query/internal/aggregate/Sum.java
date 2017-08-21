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
import org.apache.geode.internal.logging.LogService;
import org.apache.geode.vector.Vector;
import org.apache.logging.log4j.Logger;

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
    Logger logger = LogService.getLogger();

    logger.info("=== INFO === entered Sum::accumulate() ===\n");
    logger.info("=== INFO === value class is === " + value.getClass() + "\n");
        if (value != null && value != QueryService.UNDEFINED) {
          if (value instanceof Number) {
            logger.info("=== INFO === value is a number === " + value.getClass() + "\n");
            Number number = (Number) value;
            result += number.doubleValue();
            logger.info("=== INFO === result: " + result);
          } else if (value instanceof Vector) {
            logger.info("=== INFO === value is a vector === " + value.getClass() + "\n");

            Vector svvalue = (Vector) value;
            isVectorAggregate = true;
            if (VectorSumResult == null) {
              VectorSumResult = new Vector(svvalue.getSize());
            }
            VectorSumResult = VectorSumResult.plus(svvalue);

            logger.info("=== INFO === VectorSumResult: " + VectorSumResult.toString());
          } else {
            // ... unknown object
          }
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
