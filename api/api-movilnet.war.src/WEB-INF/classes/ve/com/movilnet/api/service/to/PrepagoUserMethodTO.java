/*    */ package WEB-INF.classes.ve.com.movilnet.api.service.to;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PrepagoUserMethodTO
/*    */ {
/* 11 */   private String Status = null;
/* 12 */   private Object ResultData = null;
/* 13 */   private String ErrorCode = null;
/* 14 */   private String ErrorMessage = null;
/* 15 */   private Long numContrato = null;
/* 16 */   private String mcpComando = null;
/*    */   
/*    */   public String getStatus() {
/* 19 */     return this.Status;
/*    */   }
/*    */   
/*    */   public Object getResultData() {
/* 23 */     return this.ResultData;
/*    */   }
/*    */   
/*    */   public String getErrorCode() {
/* 27 */     return this.ErrorCode;
/*    */   }
/*    */   
/*    */   public String getErrorMessage() {
/* 31 */     return this.ErrorMessage;
/*    */   }
/*    */   
/*    */   public void setStatus(String status) {
/* 35 */     this.Status = status;
/*    */   }
/*    */   
/*    */   public void setResultData(Object ResultData) {
/* 39 */     this.ResultData = ResultData;
/*    */   }
/*    */   
/*    */   public void setErrorCode(String ErrorCode) {
/* 43 */     this.ErrorCode = ErrorCode;
/*    */   }
/*    */   
/*    */   public void setErrorMessage(String ErrorMessage) {
/* 47 */     this.ErrorMessage = ErrorMessage;
/*    */   }
/*    */   
/*    */   public void setNumContrato(Long numContrato) {
/* 51 */     this.numContrato = numContrato;
/*    */   }
/*    */   
/*    */   public Long getNumContrato() {
/* 55 */     return this.numContrato;
/*    */   }
/*    */   
/*    */   public void setMcpComando(String mcpComando) {
/* 59 */     this.mcpComando = mcpComando;
/*    */   }
/*    */   
/*    */   public String getMcpComando() {
/* 63 */     return this.mcpComando;
/*    */   }
/*    */ }


/* Location:              C:\Users\aruizc01\Desktop\api-movilnet.war!\WEB-INF\classes\ve\com\movilnet\api\service\to\PrepagoUserMethodTO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */