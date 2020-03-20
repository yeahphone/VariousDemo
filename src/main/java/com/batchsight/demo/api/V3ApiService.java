package com.batchsight.demo.api;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJerseyServerCodegen", date = "2019-08-23T09:18:38.531+08:00[Asia/Shanghai]")
public abstract class V3ApiService {
    public abstract Response queryAuditTrailById(String id,SecurityContext securityContext) throws NotFoundException;
}
