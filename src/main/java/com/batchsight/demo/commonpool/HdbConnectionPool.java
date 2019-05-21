package com.batchsight.demo.commonpool;

import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Shanghai Batchsight Pharmaceutical Technologies, Inc. Copyright(c) 2016-2019</p>
 *
 * @author Zuo Yefeng
 * @version 1.0
 * <pre>Histroy:
 *       2019/5/21    Zuo Yefeng        Created
 * </pre>
 */

public class HdbConnectionPool extends BasePooledObjectFactory<MockConnection> {
  @Override
  public MockConnection create() throws Exception {
    ConnectionFactory factory = new ConnectionFactory();
    return factory.getConnection();
  }

  @Override
  public PooledObject<MockConnection> wrap(MockConnection conn) {
    return new DefaultPooledObject<>(conn);
  }

  @Override
  public void passivateObject(PooledObject<MockConnection> p) throws Exception {
    p.getObject().reset();  // 回收到资源池后重置一下连接
  }
}
