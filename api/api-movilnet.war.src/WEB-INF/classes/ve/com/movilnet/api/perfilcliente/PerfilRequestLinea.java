/*    */ package WEB-INF.classes.ve.com.movilnet.api.perfilcliente;
/*    */ 
/*    */ import javax.validation.constraints.NotBlank;
/*    */ import javax.validation.constraints.Pattern;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PerfilRequestLinea
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
/*    */   @NotBlank
/*    */   @Pattern(regexp = "^[a-zA-Z0-9]+$")
/*    */   private String ssnType;
/*    */   @NotBlank
/*    */   @Pattern(regexp = "^[a-zA-Z0-9]+$")
/*    */   private String ssn;
/*    */   
/*    */   public String getUsername() {
/* 38 */     return this.username;
/*    */   }
/*    */   
/*    */   public void setUsername(String username) {
/* 42 */     this.username = username;
/*    */   }
/*    */   
/*    */   public String getOrigin() {
/* 46 */     return this.origin;
/*    */   }
/*    */   
/*    */   public void setOrigin(String origin) {
/* 50 */     this.origin = origin;
/*    */   }
/*    */   
/*    */   public String getCode() {
/* 54 */     return this.code;
/*    */   }
/*    */   
/*    */   public void setCode(String code) {
/* 58 */     this.code = code;
/*    */   }
/*    */   
/*    */   public String getSsnType() {
/* 62 */     return this.ssnType;
/*    */   }
/*    */   
/*    */   public void setSsnType(String ssnType) {
/* 66 */     this.ssnType = ssnType;
/*    */   }
/*    */   
/*    */   public String getSsn() {
/* 70 */     return this.ssn;
/*    */   }
/*    */   
/*    */   public void setSsn(String ssn) {
/* 74 */     this.ssn = ssn;
/*    */   }
/*    */ }


/* Location:              C:\Users\aruizc01\Desktop\api-movilnet.war!\WEB-INF\classes\ve\com\movilnet\api\perfilcliente\PerfilRequestLinea.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */