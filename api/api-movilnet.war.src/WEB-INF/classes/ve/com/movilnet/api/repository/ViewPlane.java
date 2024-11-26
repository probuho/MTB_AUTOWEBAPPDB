/*     */ package WEB-INF.classes.ve.com.movilnet.api.repository;
/*     */ 
/*     */ import java.math.BigDecimal;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ViewPlane
/*     */ {
/*     */   private String PLAN_OFFERING_CODE;
/*     */   private String COMMERCIAL_LAUNCH_NAME;
/*     */   private BigDecimal COSTO;
/*     */   private String COS_PLAN_NAME;
/*     */   private BigDecimal ACCUMULATED_SMS;
/*     */   private BigDecimal ACCUMULATED_MB;
/*     */   private String TIPO_PLAN;
/*     */   private BigDecimal ACCUMULATED_USAGE;
/*     */   private String ACTIVE_CAMBIO_PLAN;
/*     */   private String SUFIJO_NOMBRE_PLAN;
/*     */   private String ID_TIPO_PLAN;
/*     */   private String UNIDAD_PLAN;
/*     */   
/*     */   public ViewPlane(String PLAN_OFFERING_CODE, String COMMERCIAL_LAUNCH_NAME, BigDecimal COSTO, String COS_PLAN_NAME, BigDecimal ACCUMULATED_SMS, BigDecimal ACCUMULATED_MB, String TIPO_PLAN, BigDecimal ACCUMULATED_USAGE, String ACTIVE_CAMBIO_PLAN, String SUFIJO_NOMBRE_PLAN, String ID_TIPO_PLAN, String UNIDAD_PLAN) {
/*  26 */     this.PLAN_OFFERING_CODE = PLAN_OFFERING_CODE;
/*  27 */     this.COMMERCIAL_LAUNCH_NAME = COMMERCIAL_LAUNCH_NAME;
/*  28 */     this.COSTO = COSTO;
/*  29 */     this.COS_PLAN_NAME = COS_PLAN_NAME;
/*  30 */     this.ACCUMULATED_SMS = ACCUMULATED_SMS;
/*  31 */     this.ACCUMULATED_MB = ACCUMULATED_MB;
/*  32 */     this.TIPO_PLAN = TIPO_PLAN;
/*  33 */     this.ACCUMULATED_USAGE = ACCUMULATED_USAGE;
/*  34 */     this.ACTIVE_CAMBIO_PLAN = ACTIVE_CAMBIO_PLAN;
/*  35 */     this.SUFIJO_NOMBRE_PLAN = SUFIJO_NOMBRE_PLAN;
/*  36 */     this.ID_TIPO_PLAN = ID_TIPO_PLAN;
/*  37 */     this.UNIDAD_PLAN = UNIDAD_PLAN;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getPLAN_OFFERING_CODE() {
/*  55 */     return this.PLAN_OFFERING_CODE;
/*     */   }
/*     */   
/*     */   public void setPLAN_OFFERING_CODE(String PLAN_OFFERING_CODE) {
/*  59 */     this.PLAN_OFFERING_CODE = PLAN_OFFERING_CODE;
/*     */   }
/*     */   
/*     */   public String getCOMMERCIAL_LAUNCH_NAME() {
/*  63 */     return this.COMMERCIAL_LAUNCH_NAME;
/*     */   }
/*     */   
/*     */   public void setCOMMERCIAL_LAUNCH_NAME(String COMMERCIAL_LAUNCH_NAME) {
/*  67 */     this.COMMERCIAL_LAUNCH_NAME = COMMERCIAL_LAUNCH_NAME;
/*     */   }
/*     */   
/*     */   public BigDecimal getCOSTO() {
/*  71 */     return this.COSTO;
/*     */   }
/*     */   
/*     */   public void setCOSTO(BigDecimal COSTO) {
/*  75 */     this.COSTO = COSTO;
/*     */   }
/*     */   
/*     */   public String getCOS_PLAN_NAME() {
/*  79 */     return this.COS_PLAN_NAME;
/*     */   }
/*     */   
/*     */   public void setCOS_PLAN_NAME(String COS_PLAN_NAME) {
/*  83 */     this.COS_PLAN_NAME = COS_PLAN_NAME;
/*     */   }
/*     */   
/*     */   public BigDecimal getACCUMULATED_SMS() {
/*  87 */     return this.ACCUMULATED_SMS;
/*     */   }
/*     */   
/*     */   public void setACCUMULATED_SMS(BigDecimal ACCUMULATED_SMS) {
/*  91 */     this.ACCUMULATED_SMS = ACCUMULATED_SMS;
/*     */   }
/*     */   
/*     */   public BigDecimal getACCUMULATED_MB() {
/*  95 */     return this.ACCUMULATED_MB;
/*     */   }
/*     */   
/*     */   public void setACCUMULATED_MB(BigDecimal ACCUMULATED_MB) {
/*  99 */     this.ACCUMULATED_MB = ACCUMULATED_MB;
/*     */   }
/*     */   
/*     */   public String getTIPO_PLAN() {
/* 103 */     return this.TIPO_PLAN;
/*     */   }
/*     */   
/*     */   public void setTIPO_PLAN(String TIPO_PLAN) {
/* 107 */     this.TIPO_PLAN = TIPO_PLAN;
/*     */   }
/*     */   
/*     */   public BigDecimal getACCUMULATED_USAGE() {
/* 111 */     return this.ACCUMULATED_USAGE;
/*     */   }
/*     */   
/*     */   public void setACCUMULATED_USAGE(BigDecimal ACCUMULATED_USAGE) {
/* 115 */     this.ACCUMULATED_USAGE = ACCUMULATED_USAGE;
/*     */   }
/*     */   
/*     */   public String getACTIVE_CAMBIO_PLAN() {
/* 119 */     return this.ACTIVE_CAMBIO_PLAN;
/*     */   }
/*     */   
/*     */   public void setACTIVE_CAMBIO_PLAN(String ACTIVE_CAMBIO_PLAN) {
/* 123 */     this.ACTIVE_CAMBIO_PLAN = ACTIVE_CAMBIO_PLAN;
/*     */   }
/*     */   
/*     */   public String getSUFIJO_NOMBRE_PLAN() {
/* 127 */     return this.SUFIJO_NOMBRE_PLAN;
/*     */   }
/*     */   
/*     */   public void setSUFIJO_NOMBRE_PLAN(String SUFIJO_NOMBRE_PLAN) {
/* 131 */     this.SUFIJO_NOMBRE_PLAN = SUFIJO_NOMBRE_PLAN;
/*     */   }
/*     */   
/*     */   public String getID_TIPO_PLAN() {
/* 135 */     return this.ID_TIPO_PLAN;
/*     */   }
/*     */   
/*     */   public void setID_TIPO_PLAN(String ID_TIPO_PLAN) {
/* 139 */     this.ID_TIPO_PLAN = ID_TIPO_PLAN;
/*     */   }
/*     */   
/*     */   public String getUNIDAD_PLAN() {
/* 143 */     return this.UNIDAD_PLAN;
/*     */   }
/*     */   
/*     */   public void setUNIDAD_PLAN(String UNIDAD_PLAN) {
/* 147 */     this.UNIDAD_PLAN = UNIDAD_PLAN;
/*     */   }
/*     */ }


/* Location:              C:\Users\aruizc01\Desktop\api-movilnet.war!\WEB-INF\classes\ve\com\movilnet\api\repository\ViewPlane.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */