/*    */ package WEB-INF.classes.ve.com.movilnet.api.auth.security.auth.response;
/*    */ 
/*    */ import java.util.List;
/*    */ import org.apache.log4j.Logger;
/*    */ import org.springframework.boot.SpringApplication;
/*    */ 
/*    */ 
/*    */ public class JwtResponse
/*    */ {
/* 10 */   private static final Logger logger = Logger.getLogger(ve.com.movilnet.api.auth.security.auth.response.JwtResponse.class);
/*    */   private String token;
/* 12 */   private String type = "Bearer";
/*    */   private String username;
/*    */   private List<String> roles;
/*    */   private String code;
/*    */   
/*    */   public static void main(String[] args) {
/* 18 */     long time1 = 0L;
/* 19 */     String transaccionId = "Autogestion--" + time1;
/*    */     
/* 21 */     time1 = System.currentTimeMillis();
/*    */     
/* 23 */     String origen = "JwtResponse";
/* 24 */     String tipoTransaccion = "APP";
/*    */     
/* 26 */     SpringApplication.run(ve.com.movilnet.api.auth.security.auth.response.JwtResponse.class, args);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public JwtResponse(String accessToken, String username, List<String> roles, String code) {
/* 33 */     this.token = accessToken;
/* 34 */     this.username = username;
/* 35 */     this.roles = roles;
/* 36 */     this.code = code;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getToken() {
/* 41 */     return this.token;
/*    */   }
/*    */   
/*    */   public void setToken(String token) {
/* 45 */     this.token = token;
/*    */   }
/*    */   
/*    */   public String getType() {
/* 49 */     return this.type;
/*    */   }
/*    */   
/*    */   public void setType(String type) {
/* 53 */     this.type = type;
/*    */   }
/*    */   
/*    */   public String getCode() {
/* 57 */     return this.code;
/*    */   }
/*    */   
/*    */   public void setCode(String code) {
/* 61 */     this.code = code;
/*    */   }
/*    */   
/*    */   public String getAccessToken() {
/* 65 */     return this.token;
/*    */   }
/*    */   
/*    */   public void setAccessToken(String accessToken) {
/* 69 */     this.token = accessToken;
/*    */   }
/*    */   
/*    */   public String getTokenType() {
/* 73 */     return this.type;
/*    */   }
/*    */   
/*    */   public void setTokenType(String tokenType) {
/* 77 */     this.type = tokenType;
/*    */   }
/*    */   
/*    */   public String getUsername() {
/* 81 */     return this.username;
/*    */   }
/*    */   
/*    */   public void setUsername(String username) {
/* 85 */     this.username = username;
/*    */   }
/*    */   
/*    */   public List<String> getRoles() {
/* 89 */     return this.roles;
/*    */   }
/*    */ }


/* Location:              C:\Users\aruizc01\Desktop\api-movilnet.war!\WEB-INF\classes\ve\com\movilnet\api\auth\security\auth\response\JwtResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */