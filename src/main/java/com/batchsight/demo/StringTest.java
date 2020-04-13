package com.batchsight.demo;

import org.apache.commons.lang3.StringUtils;
import org.junit.*;


public class StringTest {
  @Test
  public void testReplaceFirst() {
    String sql = "(?)";
    String replacement = "'${PV,QM,Sign}'";
    String regex = "\\?";
    // replaceFirst 方法使用的是一个正则替换规则，其内部实现如下：
    // Pattern.compile(regex).matcher(sql).replaceFirst(replacement));
    // 因此 replacement 字符串中出现的 ${...} 会被安装正则替换匹配，即替换为一个分组查找结果
    // 而上述写法并不是指的查找结果，因此会抛出异常，将 $ 转义即可
    System.out.println(sql.replaceFirst(regex, replacement));
  }

  @Test
  public void testFormat1() {
        String className = "MyClass";
        String genArgU = "<U>";
        String argumentType = "U";
        String formula = "1 + 1";

        String classDefine = "public class %s %s extends com.batchsight.ibatchabacus.formula.AbstractFormulaTask<%s, Object> {\n@Override public Object call() {\ntry {\nreturn %s ;\n} catch (Exception e) {\nreturn \"N/A\";\n}\n}\n}\n";

        System.out.println(String.format(classDefine, className, genArgU, argumentType, formula));
  }

  @Test
  public void testFormat2() {
    System.out.println(String.format("str=%s", null));
  }

  @Test
  public void testEmptyJudgement() {
    Assert.assertTrue(StringUtils.isEmpty(""));
    Assert.assertTrue(StringUtils.isEmpty(null));

    Assert.assertFalse(StringUtils.isEmpty(" "));

    Assert.assertTrue(StringUtils.isBlank(""));
    Assert.assertTrue(StringUtils.isBlank(null));
    Assert.assertTrue(StringUtils.isBlank(" "));
    Assert.assertTrue(StringUtils.isBlank("　")); //全角空格
    Assert.assertTrue(StringUtils.isBlank("\n"));
    Assert.assertTrue(StringUtils.isBlank("\t"));
    Assert.assertTrue(StringUtils.isBlank(" 　\n\t"));
  }
}
