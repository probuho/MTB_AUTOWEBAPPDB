/*    */ package WEB-INF.classes.ve.com.movilnet.api.auth.security.jwt;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import javax.servlet.ServletException;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import javax.servlet.http.HttpServletResponse;
/*    */ import org.slf4j.Logger;
/*    */ import org.slf4j.LoggerFactory;
/*    */ import org.springframework.security.core.AuthenticationException;
/*    */ import org.springframework.security.web.AuthenticationEntryPoint;
/*    */ import org.springframework.stereotype.Component;
/*    */ import ve.com.movilnet.api.service.util.Const;
/*    */ 
/*    */ 
/*    */ 
/*    */ @Component
/*    */ public class AuthEntryPointJwt
/*    */   implements AuthenticationEntryPoint
/*    */ {
/* 20 */   private static final Logger logger = LoggerFactory.getLogger(ve.com.movilnet.api.auth.security.jwt.AuthEntryPointJwt.class);
/*    */ 
/*    */ 
/*    */   
/*    */   public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
/* 25 */     Const.IP_REMOTA = request.getRemoteAddr();
/* 26 */     logger.error("AuthEntryPointJwt  commence: {}", authException.getLocalizedMessage());
/* 27 */     response.sendError(401, "Error: Unauthorized");
/*    */   }
/*    */ }


/* Location:              C:\Users\aruizc01\Desktop\api-movilnet.war!\WEB-INF\classes\ve\com\movilnet\api\auth\security\jwt\AuthEntryPointJwt.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */