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

package org.apache.geode.cache.query.internal.parse;

import antlr.*;
//import antlr.collections.*;
//import org.apache.geode.cache.query.*;
import org.apache.geode.cache.query.internal.QCompiler;
import org.apache.logging.log4j.Logger;
import org.apache.geode.internal.logging.LogService;

/**
 *
 */
public class GemFireAST extends CommonAST {
  private static final long serialVersionUID = 779964802274305208L;
  final Logger logger = LogService.getLogger();
  public GemFireAST() {
    super();
  }
  
  public GemFireAST(Token tok) {
    super(tok);
  }
  
  @Override
  public String getText() {
    String txt = super.getText();
    if (txt == null) {
      return "[no text]";
    }
    return txt;
  }
  
  public void compile(QCompiler compiler)  {
    childrenCompile(compiler);
  }
  
  public void childrenCompile(QCompiler compiler) {
    final Logger logger = LogService.getLogger();

    GemFireAST child = (GemFireAST)getFirstChild();
    while (child != null) {
      logger.info("----GEMFIREAST.JAVA child: " + child.getText());
      child.compile(compiler);
      child = (GemFireAST)child.getNextSibling();

    }
  }
  
}
