package com.batchsight.demo.api.impl;

import com.batchsight.demo.api.ApiResponseMessage;
import com.batchsight.demo.api.NotFoundException;
import com.batchsight.demo.api.V3ApiService;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJerseyServerCodegen", date = "2019-08-23T09:18:38.531+08:00[Asia/Shanghai]")
public class V3ApiServiceImpl extends V3ApiService {
    @Override
    public Response queryAuditTrailById(String id, SecurityContext securityContext) throws NotFoundException {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
}
