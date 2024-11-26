/*    */ package WEB-INF.classes.ve.com.movilnet.api.response;
/*    */ 
/*    */ import java.io.Serializable;
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
/*    */ public class ResponseTO
/*    */   implements Serializable
/*    */ {
/*    */   protected long executionTime;
/*    */   protected String origin;
/*    */   protected String responseCode;
/*    */   protected String responseDescription;
/*    */   protected String responseMessage;
/*    */   protected String responseSubCode;
/*    */   protected String transactionId;
/*    */   
/*    */   public long getExecutionTime() {
/* 28 */     return this.executionTime;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setExecutionTime(long executionTime) {
/* 33 */     this.executionTime = executionTime;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getOrigin() {
/* 38 */     return this.origin;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setOrigin(String origin) {
/* 43 */     this.origin = origin;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getResponseCode() {
/* 48 */     return this.responseCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setResponseCode(String responseCode) {
/* 53 */     this.responseCode = responseCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getResponseDescription() {
/* 58 */     return this.responseDescription;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setResponseDescription(String responseDescription) {
/* 63 */     this.responseDescription = responseDescription;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getResponseMessage() {
/* 68 */     return this.responseMessage;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setResponseMessage(String responseMessage) {
/* 73 */     this.responseMessage = responseMessage;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getResponseSubCode() {
/* 78 */     return this.responseSubCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setResponseSubCode(String responseSubCode) {
/* 83 */     this.responseSubCode = responseSubCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getTransactionId() {
/* 88 */     return this.transactionId;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setTransactionId(String transactionId) {
/* 93 */     this.transactionId = transactionId;
/*    */   }
/*    */ }


/* Location:              C:\Users\aruizc01\Desktop\api-movilnet.war!\WEB-INF\classes\ve\com\movilnet\api\response\ResponseTO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */