package com.batchsight.demo.api.factories;

import com.batchsight.demo.api.V3ApiService;
import com.batchsight.demo.api.impl.V3ApiServiceImpl;

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJerseyServerCodegen", date = "2019-08-23T09:18:38.531+08:00[Asia/Shanghai]")
public class V3ApiServiceFactory {
    private final static V3ApiService service = new V3ApiServiceImpl();

    public static V3ApiService getV3Api() {
        return service;
    }
}
