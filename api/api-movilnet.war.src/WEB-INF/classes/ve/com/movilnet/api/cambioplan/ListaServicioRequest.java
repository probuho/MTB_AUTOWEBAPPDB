/*    */ package WEB-INF.classes.ve.com.movilnet.api.cambioplan;
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
/*    */ public class ListaServicioRequest
/*    */ {
/*    */   private String transaccionId;
/*    */   private String numero;
/*    */   private String plan;
/*    */   
/*    */   public ListaServicioRequest() {}
/*    */   
/*    */   public ListaServicioRequest(String transaccionId, String numero, String plan) {
/* 21 */     this.transaccionId = transaccionId;
/* 22 */     this.numero = numero;
/* 23 */     this.plan = plan;
/*    */   }
/*    */   
/*    */   public String getTransaccionId() {
/* 27 */     return this.transaccionId;
/*    */   }
/*    */   
/*    */   public void setTransaccionId(String transaccionId) {
/* 31 */     this.transaccionId = transaccionId;
/*    */   }
/*    */   
/*    */   public String getNumero() {
/* 35 */     return this.numero;
/*    */   }
/*    */   
/*    */   public void setNumero(String numero) {
/* 39 */     this.numero = numero;
/*    */   }
/*    */   
/*    */   public String getPlan() {
/* 43 */     return this.plan;
/*    */   }
/*    */   
/*    */   public void setPlan(String plan) {
/* 47 */     this.plan = plan;
/*    */   }
/*    */ }


/* Location:              C:\Users\aruizc01\Desktop\api-movilnet.war!\WEB-INF\classes\ve\com\movilnet\api\cambioplan\ListaServicioRequest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */