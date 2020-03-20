package com.batchsight.demo;

import antlr.ParseTree;
import com.batchsight.demo.antlr4.MathLexer;
import com.batchsight.demo.antlr4.MathParser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.jupiter.api.Test;


/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Shanghai Batchsight Pharmaceutical Technologies, Inc. Copyright(c) 2016-2019</p>
 *
 * @author Zuo Yefeng
 * @version 1.0
 * <pre>Histroy:
 *       2019/8/6    Zuo Yefeng        Created
 * </pre>
 */

public class AntlrDemo {
  @Test
  void testMath() {
    CharStream input = CharStreams.fromString("12*2+12\r\n");
    MathLexer lexer=new MathLexer(input);
    CommonTokenStream tokens = new CommonTokenStream(lexer);
    MathParser parser = new MathParser(tokens);
  }
}
