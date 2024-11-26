/*    */ package WEB-INF.classes.ve.com.movilnet.api.auth.security.auth.request;
/*    */ 
/*    */ import javax.validation.constraints.NotBlank;
/*    */ import javax.validation.constraints.Pattern;
/*    */ import org.apache.log4j.Logger;
/*    */ import org.springframework.boot.SpringApplication;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class KeyRequest
/*    */ {
/*    */   @NotBlank
/*    */   @Pattern(regexp = "^[a-zA-Z0-9]+$")
/*    */   private String username;
/*    */   @NotBlank
/*    */   @Pattern(regexp = "^[\\w\\s.-]+$")
/*    */   private String origin;
/*    */   @NotBlank
/*    */   @Pattern(regexp = "^[\\w\\s.-]+$")
/*    */   private String code;
/*    */   private String mail;
/*    */   
/*    */   public KeyRequest(String username, String origin, String code, String mail) {
/* 27 */     this.username = username;
/* 28 */     this.origin = origin;
/* 29 */     this.code = code;
/* 30 */     this.mail = mail;
/*    */   }
/*    */   
/*    */   public String getMail() {
/* 34 */     return this.mail;
/*    */   }
/*    */   
/*    */   public void setMail(String mail) {
/* 38 */     this.mail = mail;
/*    */   }
/*    */   
/*    */   public String getUsername() {
/* 42 */     return this.username;
/*    */   }
/*    */   
/*    */   public void setUsername(String username) {
/* 46 */     this.username = username;
/*    */   }
/*    */   
/*    */   public String getOrigin() {
/* 50 */     return this.origin;
/*    */   }
/*    */   
/*    */   public void setOrigin(String origin) {
/* 54 */     this.origin = origin;
/*    */   }
/*    */   
/*    */   public String getCode() {
/* 58 */     return this.code;
/*    */   }
/*    */   
/*    */   public void setCode(String code) {
/* 62 */     this.code = code;
/*    */   }
/*    */   
/* 65 */   private static final Logger logger = Logger.getLogger(ve.com.movilnet.api.auth.security.auth.request.KeyRequest.class);
/*    */ 
/*    */   
/*    */   public static void main(String[] args) {
/* 69 */     long time1 = 0L;
/* 70 */     String transaccionId = "Autogestion--" + time1;
/*    */     
/* 72 */     time1 = System.currentTimeMillis();
/*    */     
/* 74 */     String origen = "KeyRequest";
/* 75 */     String tipoTransaccion = "APP";
/*    */     
/* 77 */     SpringApplication.run(ve.com.movilnet.api.auth.security.auth.request.KeyRequest.class, args);
/*    */   }
/*    */ }


/* Location:              C:\Users\aruizc01\Desktop\api-movilnet.war!\WEB-INF\classes\ve\com\movilnet\api\auth\security\auth\request\KeyRequest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */