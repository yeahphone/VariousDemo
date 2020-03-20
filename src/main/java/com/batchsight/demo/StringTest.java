package com.batchsight.demo;

import org.junit.jupiter.api.Test;

import java.util.regex.Pattern;

class StringTest {
    @Test
    void testReplaceFirst() {
    String sql = "(?)";
    String replacement = "'${PV,QM,Sign}'";
    String regex = "\\?";
    // replaceFirst 方法使用的是一个正则替换规则，其内部实现如下：
    // Pattern.compile(regex).matcher(sql).replaceFirst(replacement));
    // 因此 replacement 字符串中出现的 ${...} 会被安装正则替换匹配，即替换为一个分组查找结果
    // 而上述写法并不是指的查找结果，因此会抛出异常，将 $ 转义即可
    System.out.println(sql.replaceFirst(regex, replacement));
  }
}
