package com.batchsight.demo;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.StringWriter;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Shanghai Batchsight Pharmaceutical Technologies, Inc. Copyright(c) 2016-2018</p>
 *
 * @author Zuo Yefeng
 * @version 1.0
 * <pre>Histroy:
 *       2018/10/11    Zuo Yefeng        Created
 * </pre>
 */

public class MyException extends RuntimeException {
  private int errorCode;
  private String message;
  private MyContext context;

  public MyException(int errorCode, String message, Throwable cause){
    super(message, cause);

    this.errorCode = errorCode;
    this.message = message;
  }

  public MyException(int errorCode, String message) {
    super(message);

    this.errorCode = errorCode;
    this.message = message;
  }

  public MyContext getContext() {
    if (context == null) {
      context = new MyContext();
    }

    return context;
  }

  private static String toJsonString(Object obj) throws IOException {
    StringWriter sw = new StringWriter();
    JsonGenerator jsonGenerator = new ObjectMapper().getFactory().createGenerator(sw);
    jsonGenerator.writeObject(obj);
    return sw.toString();
  }

  public String getContextString() {
    try {
      return MyException.toJsonString(this.context);
    } catch (Exception ignore) {}

    return "FAILED";
  }

  @Override
  public String getMessage() {
    return "[ERR" + this.errorCode + "] " + this.message + "\n" + getContextString();
  }

  public String getOrigMessage() {
    return this.message;
  }
}
