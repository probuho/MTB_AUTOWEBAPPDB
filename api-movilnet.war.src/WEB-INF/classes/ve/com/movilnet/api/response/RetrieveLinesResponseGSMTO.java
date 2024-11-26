/*    */ package WEB-INF.classes.ve.com.movilnet.api.response;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import ve.com.movilnet.api.response.ResponseTO;
/*    */ import ve.com.movilnet.api.response.ResultGSMTO;
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
/*    */ public class RetrieveLinesResponseGSMTO
/*    */   extends ResponseTO
/*    */   implements Serializable
/*    */ {
/*    */   protected int ocurrenciaTotal;
/*    */   protected ResultGSMTO[] resultado;
/*    */   
/*    */   public int getOcurrenciaTotal() {
/* 24 */     return this.ocurrenciaTotal;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setOcurrenciaTotal(int ocurrenciaTotal) {
/* 29 */     this.ocurrenciaTotal = ocurrenciaTotal;
/*    */   }
/*    */ 
/*    */   
/*    */   public ResultGSMTO[] getResultado() {
/* 34 */     return this.resultado;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setResultado(ResultGSMTO[] resultado) {
/* 39 */     this.resultado = resultado;
/*    */   }
/*    */ }


/* Location:              C:\Users\aruizc01\Desktop\api-movilnet.war!\WEB-INF\classes\ve\com\movilnet\api\response\RetrieveLinesResponseGSMTO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */