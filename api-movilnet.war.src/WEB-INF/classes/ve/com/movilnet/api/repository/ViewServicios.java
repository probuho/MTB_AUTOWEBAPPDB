/*    */ package WEB-INF.classes.ve.com.movilnet.api.repository;
/*    */ 
/*    */ import java.math.BigDecimal;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ViewServicios
/*    */ {
/*    */   private String COD_SERVICE;
/*    */   private String DESC_SERVICIO;
/*    */   private String DESCRIPCION;
/*    */   private String ALCO_NAME;
/*    */   private BigDecimal COSTO;
/*    */   private String ACTIVE;
/*    */   
/*    */   public ViewServicios(String COD_SERVICE, String DESC_SERVICIO, String DESCRIPCION, String ALCO_NAME, BigDecimal COSTO, String ACTIVE) {
/* 20 */     this.COD_SERVICE = COD_SERVICE;
/* 21 */     this.DESC_SERVICIO = DESC_SERVICIO;
/* 22 */     this.DESCRIPCION = DESCRIPCION;
/* 23 */     this.ALCO_NAME = ALCO_NAME;
/* 24 */     this.COSTO = COSTO;
/* 25 */     this.ACTIVE = ACTIVE;
/*    */   }
/*    */   
/*    */   public String getCOD_SERVICE() {
/* 29 */     return this.COD_SERVICE;
/*    */   }
/*    */   
/*    */   public void setCOD_SERVICE(String COD_SERVICE) {
/* 33 */     this.COD_SERVICE = COD_SERVICE;
/*    */   }
/*    */   
/*    */   public String getDESC_SERVICIO() {
/* 37 */     return this.DESC_SERVICIO;
/*    */   }
/*    */   
/*    */   public void setDESC_SERVICIO(String DESC_SERVICIO) {
/* 41 */     this.DESC_SERVICIO = DESC_SERVICIO;
/*    */   }
/*    */   
/*    */   public String getDESCRIPCION() {
/* 45 */     return this.DESCRIPCION;
/*    */   }
/*    */   
/*    */   public void setDESCRIPCION(String DESCRIPCION) {
/* 49 */     this.DESCRIPCION = DESCRIPCION;
/*    */   }
/*    */   
/*    */   public String getALCO_NAME() {
/* 53 */     return this.ALCO_NAME;
/*    */   }
/*    */   
/*    */   public void setALCO_NAME(String ALCO_NAME) {
/* 57 */     this.ALCO_NAME = ALCO_NAME;
/*    */   }
/*    */   
/*    */   public BigDecimal getCOSTO() {
/* 61 */     return this.COSTO;
/*    */   }
/*    */   
/*    */   public void setCOSTO(BigDecimal COSTO) {
/* 65 */     this.COSTO = COSTO;
/*    */   }
/*    */   
/*    */   public String getACTIVE() {
/* 69 */     return this.ACTIVE;
/*    */   }
/*    */   
/*    */   public void setACTIVE(String ACTIVE) {
/* 73 */     this.ACTIVE = ACTIVE;
/*    */   }
/*    */ }


/* Location:              C:\Users\aruizc01\Desktop\api-movilnet.war!\WEB-INF\classes\ve\com\movilnet\api\repository\ViewServicios.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */