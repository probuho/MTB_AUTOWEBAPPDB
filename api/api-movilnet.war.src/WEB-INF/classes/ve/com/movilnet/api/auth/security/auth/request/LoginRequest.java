/*    */ package WEB-INF.classes.ve.com.movilnet.api.auth.security.auth.request;
/*    */ 
/*    */ import javax.validation.constraints.NotBlank;
/*    */ import javax.validation.constraints.Pattern;
/*    */ import org.apache.log4j.Logger;
/*    */ import org.springframework.boot.SpringApplication;
/*    */ 
/*    */ public class LoginRequest
/*    */ {
/* 10 */   private static final Logger logger = Logger.getLogger(ve.com.movilnet.api.auth.security.auth.request.LoginRequest.class);
/*    */ 
/*    */   
/*    */   public static void main(String[] args) {
/* 14 */     long time1 = 0L;
/* 15 */     String transaccionId = "Autogestion--" + time1;
/*    */     
/* 17 */     time1 = System.currentTimeMillis();
/*    */     
/* 19 */     String origen = "LoginRequest";
/* 20 */     String tipoTransaccion = "APP";
/*    */     
/* 22 */     SpringApplication.run(ve.com.movilnet.api.auth.security.auth.request.LoginRequest.class, args);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   @NotBlank
/*    */   @Pattern(regexp = "^[a-zA-Z0-9]+$")
/*    */   private String username;
/*    */ 
/*    */   
/*    */   @NotBlank
/*    */   @Pattern(regexp = "^[a-zA-Z0-9]+$")
/*    */   private String password;
/*    */   
/*    */   @NotBlank
/*    */   @Pattern(regexp = "^[\\w\\s.-]+$")
/*    */   private String origin;
/*    */   
/*    */   @NotBlank
/*    */   @Pattern(regexp = "^[\\w\\s.-]+$")
/*    */   private String code;
/*    */ 
/*    */   
/*    */   public LoginRequest(String username, String password, String origin, String code) {
/* 46 */     this.username = username;
/* 47 */     this.password = password;
/* 48 */     this.origin = origin;
/* 49 */     this.code = code;
/*    */   }
/*    */   
/*    */   public String getOrigin() {
/* 53 */     return this.origin;
/*    */   }
/*    */   
/*    */   public void setOrigin(String origin) {
/* 57 */     this.origin = origin;
/*    */   }
/*    */   
/*    */   public String getCode() {
/* 61 */     return this.code;
/*    */   }
/*    */   
/*    */   public void setCode(String code) {
/* 65 */     this.code = code;
/*    */   }
/*    */   
/*    */   public String getUsername() {
/* 69 */     return this.username;
/*    */   }
/*    */   
/*    */   public void setUsername(String username) {
/* 73 */     this.username = username;
/*    */   }
/*    */   
/*    */   public String getPassword() {
/* 77 */     return this.password;
/*    */   }
/*    */   
/*    */   public void setPassword(String password) {
/* 81 */     this.password = password;
/*    */   }
/*    */ }


/* Location:              C:\Users\aruizc01\Desktop\api-movilnet.war!\WEB-INF\classes\ve\com\movilnet\api\auth\security\auth\request\LoginRequest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */