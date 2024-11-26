/*    */ package WEB-INF.classes.ve.com.movilnet.api.perfilcliente;
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
/*    */ public class ConsultarLineaResponse
/*    */ {
/*    */   private String numero;
/*    */   private String status;
/*    */   
/*    */   public ConsultarLineaResponse() {}
/*    */   
/*    */   public String getStatus() {
/* 20 */     return this.status;
/*    */   }
/*    */   
/*    */   public void setStatus(String status) {
/* 24 */     this.status = status;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getNumero() {
/* 32 */     return this.numero;
/*    */   }
/*    */   
/*    */   public void setNumero(String numero) {
/* 36 */     this.numero = numero;
/*    */   }
/*    */   
/*    */   public ConsultarLineaResponse(String numero, String status) {
/* 40 */     this.numero = numero;
/* 41 */     this.status = status;
/*    */   }
/*    */ }


/* Location:              C:\Users\aruizc01\Desktop\api-movilnet.war!\WEB-INF\classes\ve\com\movilnet\api\perfilcliente\ConsultarLineaResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */