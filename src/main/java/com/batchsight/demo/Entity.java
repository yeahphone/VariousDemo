package com.batchsight.demo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Shanghai Batchsight Pharmaceutical Technologies, Inc. Copyright(c) 2016-2018</p>
 *
 * @author Zuo Yefeng
 * @version 1.0
 * <pre>Histroy:
 *       2018/8/2    Zuo Yefeng        Created
 * </pre>
 */

@Getter
@Setter
@NoArgsConstructor
public class Entity<T> {
  private int id;
  @JsonProperty("key")
  private String configKey;
  private byte[] uuid;
  private T generalVariable;
  private Class<?> valueType;

  public Class<?> getValueType() {
    if (generalVariable == null) return null;
    valueType = generalVariable.getClass();
    return valueType;
  }

  public void setGeneralVariable(T value) {
    this.generalVariable = value;
  }
}
