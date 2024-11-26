/*     */ package WEB-INF.classes.ve.com.movilnet.api.response;
/*     */ 
/*     */ import java.math.BigDecimal;
/*     */ import ve.com.movilnet.api.response.Beneficios;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Planes
/*     */ {
/*     */   private String id;
/*     */   private String name;
/*     */   private BigDecimal costo;
/*     */   private String cos_plan_name;
/*     */   private String tipo_plan;
/*     */   private String active;
/*     */   private Beneficios beneficios;
/*     */   private String SUFIJO_NOMBRE_PLAN;
/*     */   private String ID_TIPO_PLAN;
/*     */   private String UNIDAD_PLAN;
/*     */   
/*     */   public String getId() {
/*  28 */     return this.id;
/*     */   }
/*     */   
/*     */   public void setId(String id) {
/*  32 */     this.id = id;
/*     */   }
/*     */   
/*     */   public String getName() {
/*  36 */     return this.name;
/*     */   }
/*     */   
/*     */   public void setName(String name) {
/*  40 */     this.name = name;
/*     */   }
/*     */   
/*     */   public Beneficios getBeneficios() {
/*  44 */     return this.beneficios;
/*     */   }
/*     */   
/*     */   public void setBeneficios(Beneficios beneficios) {
/*  48 */     this.beneficios = beneficios;
/*     */   }
/*     */   public BigDecimal getCosto() {
/*  51 */     return this.costo;
/*     */   }
/*     */   
/*     */   public void setCosto(BigDecimal costo) {
/*  55 */     this.costo = costo;
/*     */   }
/*     */   
/*     */   public String getCos_plan_name() {
/*  59 */     return this.cos_plan_name;
/*     */   }
/*     */   
/*     */   public void setCos_plan_name(String cos_plan_name) {
/*  63 */     this.cos_plan_name = cos_plan_name;
/*     */   }
/*     */   
/*     */   public String getTipo_plan() {
/*  67 */     return this.tipo_plan;
/*     */   }
/*     */   
/*     */   public void setTipo_plan(String tipo_plan) {
/*  71 */     this.tipo_plan = tipo_plan;
/*     */   }
/*     */   
/*     */   public String getActive() {
/*  75 */     return this.active;
/*     */   }
/*     */   
/*     */   public void setActive(String active) {
/*  79 */     this.active = active;
/*     */   }
/*     */   
/*     */   public String getSUFIJO_NOMBRE_PLAN() {
/*  83 */     return this.SUFIJO_NOMBRE_PLAN;
/*     */   }
/*     */   
/*     */   public void setSUFIJO_NOMBRE_PLAN(String SUFIJO_NOMBRE_PLAN) {
/*  87 */     this.SUFIJO_NOMBRE_PLAN = SUFIJO_NOMBRE_PLAN;
/*     */   }
/*     */   
/*     */   public String getID_TIPO_PLAN() {
/*  91 */     return this.ID_TIPO_PLAN;
/*     */   }
/*     */   
/*     */   public void setID_TIPO_PLAN(String ID_TIPO_PLAN) {
/*  95 */     this.ID_TIPO_PLAN = ID_TIPO_PLAN;
/*     */   }
/*     */   
/*     */   public String getUNIDAD_PLAN() {
/*  99 */     return this.UNIDAD_PLAN;
/*     */   }
/*     */   
/*     */   public void setUNIDAD_PLAN(String UNIDAD_PLAN) {
/* 103 */     this.UNIDAD_PLAN = UNIDAD_PLAN;
/*     */   }
/*     */ }


/* Location:              C:\Users\aruizc01\Desktop\api-movilnet.war!\WEB-INF\classes\ve\com\movilnet\api\response\Planes.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */