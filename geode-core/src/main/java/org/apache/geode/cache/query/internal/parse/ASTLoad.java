package org.apache.geode.cache.query.internal.parse;


import antlr.*;
import org.apache.geode.cache.query.internal.QCompiler;
import org.apache.geode.internal.logging.LogService;
import org.apache.logging.log4j.Logger;

/**
 * Created by dylan on 6/9/17.
 */
public class ASTLoad extends GemFireAST {

  public ASTLoad() {
  }

        /** Creates a new instance of ASTSortCriterion */
  public ASTLoad(Token t) {
      super(t);
  }

  public void compile(QCompiler compiler){
      super.compile(compiler);

      final Logger logger = LogService.getLogger();
      logger.info("First child: " + getFirstChild().getText());
      logger.info("# children: " + getNumberOfChildren());

      //let's make this compiler load!
      compiler.load();
  }

}
