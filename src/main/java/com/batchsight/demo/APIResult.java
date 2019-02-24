package com.batchsight.demo;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>Title: ApiResult</p>
 * <p>Description: API 调用返回值</p>
 * <p>Copyright: Shanghai Batchsight Pharmaceutical Technologies, Inc. Copyright(c) 2016-2018</p>
 *
 * @author Zuo Yefeng
 * @version 1.0
 * <pre>Histroy:
 *       2018/9/2    Zuo Yefeng        Created
 * </pre>
 */

@Data
public class APIResult<T> implements Serializable
{
  private int errorCode;
  private T data;
  private String errorInfo;
  private Class<?> dataType;
  private int total;
  private int apiId;
  private long elapsed; //耗时(单位：ns)

  public static final int SUCCESS = 0;

  public void setErrorCode(int errorCode)
  {
    this.errorCode = errorCode;
  }

  public void setData(T data)
  {
    this.data = data;
  }

  public void setErrorInfo(String errorInfo)
  {
    this.errorInfo = errorInfo;
  }

  public void setDataType(Class<?> dataType)
  {
    this.dataType = dataType;
  }

  public void setTotal(int total)
  {
    this.total = total;
  }

  public boolean equals(Object o)
  {
    if (o == this) {
      return true;
    }
    if (!(o instanceof APIResult)) {
      return false;
    }
    APIResult<?> other = (APIResult)o;
    if (!other.canEqual(this)) {
      return false;
    }
    if (getErrorCode() != other.getErrorCode()) {
      return false;
    }
    Object this$data = getData();Object other$data = other.getData();
    if (this$data == null ? other$data != null : !this$data.equals(other$data)) {
      return false;
    }
    Object this$errorInfo = getErrorInfo();Object other$errorInfo = other.getErrorInfo();
    if (this$errorInfo == null ? other$errorInfo != null : !this$errorInfo.equals(other$errorInfo)) {
      return false;
    }
    Object this$dataType = getDataType();Object other$dataType = other.getDataType();
    if (this$dataType == null ? other$dataType != null : !this$dataType.equals(other$dataType)) {
      return false;
    }
    return getTotal() == other.getTotal();
  }

  protected boolean canEqual(Object other)
  {
    return other instanceof APIResult;
  }

  public int hashCode()
  {
    int PRIME = 59;int result = 1;result = result * 59 + getErrorCode();Object $data = getData();result = result * 59 + ($data == null ? 43 : $data.hashCode());Object $errorInfo = getErrorInfo();result = result * 59 + ($errorInfo == null ? 43 : $errorInfo.hashCode());Object $dataType = getDataType();result = result * 59 + ($dataType == null ? 43 : $dataType.hashCode());result = result * 59 + getTotal();return result;
  }

  public int getErrorCode()
  {
    return this.errorCode;
  }

  public T getData()
  {
    return (T)this.data;
  }

  public String getErrorInfo()
  {
    return this.errorInfo;
  }

  public int getTotal()
  {
    return this.total;
  }

  public Class<?> getDataType()
  {
    if (this.data == null) {
      return null;
    }
    this.dataType = this.data.getClass();
    return this.dataType;
  }

  public String toString()
  {
    return "APIResult{errorCode=" + this.errorCode + ", data=" + this.data + ", errorInfo='" + this.errorInfo + '\'' + '}';
  }

  public APIResult(T data)
  {
    this.data = data;
  }

  public APIResult(T data, int errorCode, String errorInfo)
  {
    this.errorCode = errorCode;
    this.data = data;
    this.errorInfo = errorInfo;
  }

  public APIResult(int errorCode, int apiId) {
    this.errorCode = errorCode;
    this.apiId = apiId;
  }

  public APIResult(int errorCode, String errorInfo)
  {
    this.errorCode = errorCode;
    this.errorInfo = errorInfo;
  }

  public boolean isSuccess()
  {
    return this.errorCode == 0;
  }

  public APIResult() {}
}
