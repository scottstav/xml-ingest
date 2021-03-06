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

import antlr.Token;
import org.apache.geode.cache.query.internal.QCompiler;
import org.apache.geode.internal.logging.LogService;
import org.apache.logging.log4j.Logger;

public class ASTInto extends GemFireAST {

    private static final long serialVersionUID = -4890146535296193473L;

    public ASTInto(Token t) { super(t); }

    @Override
    public void compile(QCompiler qCompiler) {
        final Logger logger = LogService.getLogger();
        logger.info("First child: " + getFirstChild().getText());
        logger.info("# children: " + getNumberOfChildren());

        this.childrenCompile(qCompiler);
        // ASTIteratorDef ast = this.??
        // throw new UnsupportedOperationException("Using new class successfully, compilation not implemented yet");
    }

}