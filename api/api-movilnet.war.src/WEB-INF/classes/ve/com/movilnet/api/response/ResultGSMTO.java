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
/*    */ public class ResultGSMTO
/*    */   implements Serializable
/*    */ {
/*    */   protected String cosName;
/*    */   protected String fechaActivacion;
/*    */   protected String generico1;
/*    */   protected String nombreSubscriptor;
/*    */   protected String statusLinea;
/*    */   protected long subscriberId;
/*    */   
/*    */   public String getCosName() {
/* 27 */     return this.cosName;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCosName(String cosName) {
/* 32 */     this.cosName = cosName;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getFechaActivacion() {
/* 37 */     return this.fechaActivacion;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setFechaActivacion(String fechaActivacion) {
/* 42 */     this.fechaActivacion = fechaActivacion;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getGenerico1() {
/* 47 */     return this.generico1;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setGenerico1(String generico1) {
/* 52 */     this.generico1 = generico1;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getNombreSubscriptor() {
/* 57 */     return this.nombreSubscriptor;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setNombreSubscriptor(String nombreSubscriptor) {
/* 62 */     this.nombreSubscriptor = nombreSubscriptor;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getStatusLinea() {
/* 67 */     return this.statusLinea;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setStatusLinea(String statusLinea) {
/* 72 */     this.statusLinea = statusLinea;
/*    */   }
/*    */ 
/*    */   
/*    */   public long getSubscriberId() {
/* 77 */     return this.subscriberId;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setSubscriberId(long subscriberId) {
/* 82 */     this.subscriberId = subscriberId;
/*    */   }
/*    */ }


/* Location:              C:\Users\aruizc01\Desktop\api-movilnet.war!\WEB-INF\classes\ve\com\movilnet\api\response\ResultGSMTO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */