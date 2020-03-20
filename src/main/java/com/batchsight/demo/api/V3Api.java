package com.batchsight.demo.api;

import com.batchsight.demo.api.factories.V3ApiServiceFactory;
import com.batchsight.demo.dto.InlineResponse200DTO;
import io.swagger.annotations.ApiParam;

import javax.servlet.ServletConfig;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@Path("/v3")


@io.swagger.annotations.Api(description = "the v3 API")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJerseyServerCodegen", date = "2019-08-23T09:18:38.531+08:00[Asia/Shanghai]")
public class V3Api  {
   private final V3ApiService delegate;

   public V3Api(@Context ServletConfig servletContext) {
      V3ApiService delegate = null;

      if (servletContext != null) {
         String implClass = servletContext.getInitParameter("V3Api.implementation");
         if (implClass != null && !"".equals(implClass.trim())) {
            try {
               delegate = (V3ApiService) Class.forName(implClass).newInstance();
            } catch (Exception e) {
               throw new RuntimeException(e);
            }
         } 
      }

      if (delegate == null) {
         delegate = V3ApiServiceFactory.getV3Api();
      }

      this.delegate = delegate;
   }

    @GET
    @Path("/audit-trails/{id}")
    
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "API11164 根据ID/UUID获取审计追踪基本信息", notes = "通过审计追踪ID/UUID获取审计追踪基本信息（注:ID或UUID均放在路径中，后端进行统一处理）", response = InlineResponse200DTO.class, tags={ "AuditTrail", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "操作成功", response = InlineResponse200DTO.class) })
    public Response queryAuditTrailById(@ApiParam(value = "审计追踪ID/UUID",required=true) @PathParam("id") String id
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.queryAuditTrailById(id, securityContext);
    }
}
