/*    */ package WEB-INF.classes.ve.com.movilnet.api.cambioplan;
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
/*    */ public class ListaPlanRequest
/*    */ {
/*    */   @NotBlank
/*    */   @Pattern(regexp = "^[a-zA-Z0-9_-]+$")
/*    */   private String transaccionId;
/*    */   @NotBlank
/*    */   @Pattern(regexp = "^[a-zA-Z0-9]+$")
/*    */   private String numero;
/*    */   @NotBlank
/*    */   @Pattern(regexp = "^[a-zA-Z0-9_-]+$")
/*    */   private String plan;
/*    */   
/*    */   public ListaPlanRequest() {}
/*    */   
/*    */   public ListaPlanRequest(String transaccionId, String numero, String plan) {
/* 31 */     this.transaccionId = transaccionId;
/* 32 */     this.numero = numero;
/* 33 */     this.plan = plan;
/*    */   }
/*    */   
/*    */   public String getTransaccionId() {
/* 37 */     return this.transaccionId;
/*    */   }
/*    */   
/*    */   public void setTransaccionId(String transaccionId) {
/* 41 */     this.transaccionId = transaccionId;
/*    */   }
/*    */   
/*    */   public String getNumero() {
/* 45 */     return this.numero;
/*    */   }
/*    */   
/*    */   public void setNumero(String numero) {
/* 49 */     this.numero = numero;
/*    */   }
/*    */   
/*    */   public String getPlan() {
/* 53 */     return this.plan;
/*    */   }
/*    */   
/*    */   public void setPlan(String plan) {
/* 57 */     this.plan = plan;
/*    */   }
/*    */ }


/* Location:              C:\Users\aruizc01\Desktop\api-movilnet.war!\WEB-INF\classes\ve\com\movilnet\api\cambioplan\ListaPlanRequest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */