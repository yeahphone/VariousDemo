package com.batchsight.demo;

import org.junit.jupiter.api.Test;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Shanghai Batchsight Pharmaceutical Technologies, Inc. Copyright(c) 2016-2019</p>
 *
 * @author Zuo Yefeng
 * @version 1.0
 * <pre>Histroy:
 *       2019/1/3    Zuo Yefeng        Created
 * </pre>
 */

class UriDemo {
  @Test
  void testUri() throws URISyntaxException {
    URI uri = new URI("http://www.batchsight.com/test.jar");
    System.out.println(uri.getScheme());
    System.out.println(uri.getRawSchemeSpecificPart());
  }
}
