/*    */ package WEB-INF.classes.ve.com.movilnet.api.perfilcliente;
/*    */ 
/*    */ import javax.validation.constraints.NotBlank;
/*    */ import javax.validation.constraints.Pattern;
/*    */ import org.apache.log4j.Logger;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PerfilRequest
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
/*    */   
/*    */   public String getUsername() {
/* 24 */     return this.username;
/*    */   }
/*    */   
/*    */   public void setUsername(String username) {
/* 28 */     this.username = username;
/*    */   }
/*    */   
/*    */   public String getOrigin() {
/* 32 */     return this.origin;
/*    */   }
/*    */   
/*    */   public void setOrigin(String origin) {
/* 36 */     this.origin = origin;
/*    */   }
/*    */   
/*    */   public String getCode() {
/* 40 */     return this.code;
/*    */   }
/*    */   
/*    */   public void setCode(String code) {
/* 44 */     this.code = code;
/*    */   }
/*    */   
/* 47 */   private static final Logger logger = Logger.getLogger(ve.com.movilnet.api.perfilcliente.PerfilRequest.class);
/*    */ }


/* Location:              C:\Users\aruizc01\Desktop\api-movilnet.war!\WEB-INF\classes\ve\com\movilnet\api\perfilcliente\PerfilRequest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */