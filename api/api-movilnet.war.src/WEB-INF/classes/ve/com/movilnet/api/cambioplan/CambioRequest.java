/*     */ package WEB-INF.classes.ve.com.movilnet.api.cambioplan;
/*     */ 
/*     */ import javax.validation.constraints.NotBlank;
/*     */ import javax.validation.constraints.Pattern;
/*     */ import org.springframework.security.web.savedrequest.FastHttpDateFormat;
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
/*     */ public class CambioRequest
/*     */ {
/*     */   @NotBlank
/*     */   @Pattern(regexp = "^[\\w\\s.-]+$")
/*     */   private String transaccionId;
/*     */   @NotBlank
/*     */   @Pattern(regexp = "^[a-zA-Z0-9_-]+$")
/*     */   private String origin;
/*     */   @NotBlank
/*     */   @Pattern(regexp = "^[a-zA-Z0-9]+$")
/*     */   private String username;
/*     */   @NotBlank
/*     */   @Pattern(regexp = "^[\\w\\s.-]+$")
/*     */   private String planActual;
/*     */   @NotBlank
/*     */   @Pattern(regexp = "^[a-zA-Z0-9]+$")
/*     */   private String cedulaCliente;
/*     */   @NotBlank
/*     */   @Pattern(regexp = "^[a-zA-Z0-9]+$")
/*     */   private String imei;
/*     */   @NotBlank
/*  39 */   private String fechaActivacion = FastHttpDateFormat.getCurrentDate();
/*     */ 
/*     */   
/*     */   @NotBlank
/*     */   @Pattern(regexp = "^[\\w\\s.-]+$")
/*     */   private String idPlan;
/*     */ 
/*     */   
/*     */   @NotBlank
/*     */   @Pattern(regexp = "^[\\w\\s.-]+$")
/*     */   private String descripcion;
/*     */ 
/*     */   
/*     */   @NotBlank
/*     */   @Pattern(regexp = "^[\\w\\s.-]+$")
/*     */   private String sufijoNombrePlan;
/*     */   
/*     */   @NotBlank
/*     */   @Pattern(regexp = "^[\\w\\s.-]+$")
/*     */   private String idTipoPlan;
/*     */   
/*     */   @NotBlank
/*     */   @Pattern(regexp = "^[a-zA-Z0-9_.-]+$")
/*     */   private String unidadPlan;
/*     */   
/*     */   @NotBlank
/*     */   @Pattern(regexp = "^[\\w\\s.-]+$")
/*     */   private String cargoPeriodicoAsociado;
/*     */   
/*     */   @NotBlank
/*     */   @Pattern(regexp = "^[a-zA-Z0-9_-]+$")
/*     */   private String nombreConsejo;
/*     */   
/*     */   @NotBlank
/*     */   @Pattern(regexp = "^[a-zA-Z0-9_-]+$")
/*     */   private String cargoConsejo;
/*     */   
/*     */   @NotBlank
/*     */   @Pattern(regexp = "^[a-zA-Z0-9_-]+$")
/*     */   private String codigoConsejo4;
/*     */   
/*     */   @NotBlank
/*     */   @Pattern(regexp = "^[a-zA-Z0-9_-]+$")
/*     */   private String codigoConsejo2;
/*     */   
/*     */   @NotBlank
/*     */   @Pattern(regexp = "^[a-zA-Z0-9_-]+$")
/*     */   private String codigoConsejo3;
/*     */   
/*     */   @NotBlank
/*     */   @Pattern(regexp = "^[a-zA-Z0-9_-]+$")
/*     */   private String codigoConsejo1;
/*     */ 
/*     */   
/*     */   public String getUsername() {
/*  94 */     return this.username;
/*     */   }
/*     */   
/*     */   public void setUsername(String username) {
/*  98 */     this.username = username;
/*     */   }
/*     */   
/*     */   public String getPlanActual() {
/* 102 */     return this.planActual;
/*     */   }
/*     */   
/*     */   public void setPlanActual(String planActual) {
/* 106 */     this.planActual = planActual;
/*     */   }
/*     */   
/*     */   public String getCedulaCliente() {
/* 110 */     return this.cedulaCliente;
/*     */   }
/*     */   
/*     */   public void setCedulaCliente(String cedulaCliente) {
/* 114 */     this.cedulaCliente = cedulaCliente;
/*     */   }
/*     */   
/*     */   public String getImei() {
/* 118 */     return this.imei;
/*     */   }
/*     */   
/*     */   public void setImei(String imei) {
/* 122 */     this.imei = imei;
/*     */   }
/*     */   
/*     */   public String getFechaActivacion() {
/* 126 */     return this.fechaActivacion;
/*     */   }
/*     */   
/*     */   public void setFechaActivacion(String fechaActivacion) {
/* 130 */     this.fechaActivacion = fechaActivacion;
/*     */   }
/*     */   
/*     */   public String getIdPlan() {
/* 134 */     return this.idPlan;
/*     */   }
/*     */   
/*     */   public void setIdPlan(String idPlan) {
/* 138 */     this.idPlan = idPlan;
/*     */   }
/*     */   
/*     */   public String getDescripcion() {
/* 142 */     return this.descripcion;
/*     */   }
/*     */   
/*     */   public void setDescripcion(String descripcion) {
/* 146 */     this.descripcion = descripcion;
/*     */   }
/*     */   
/*     */   public String getSufijoNombrePlan() {
/* 150 */     return this.sufijoNombrePlan;
/*     */   }
/*     */   
/*     */   public void setSufijoNombrePlan(String sufijoNombrePlan) {
/* 154 */     this.sufijoNombrePlan = sufijoNombrePlan;
/*     */   }
/*     */   
/*     */   public String getIdTipoPlan() {
/* 158 */     return this.idTipoPlan;
/*     */   }
/*     */   
/*     */   public void setIdTipoPlan(String idTipoPlan) {
/* 162 */     this.idTipoPlan = idTipoPlan;
/*     */   }
/*     */   
/*     */   public String getUnidadPlan() {
/* 166 */     return this.unidadPlan;
/*     */   }
/*     */   
/*     */   public void setUnidadPlan(String unidadPlan) {
/* 170 */     this.unidadPlan = unidadPlan;
/*     */   }
/*     */   
/*     */   public String getCargoPeriodicoAsociado() {
/* 174 */     return this.cargoPeriodicoAsociado;
/*     */   }
/*     */   
/*     */   public void setCargoPeriodicoAsociado(String cargoPeriodicoAsociado) {
/* 178 */     this.cargoPeriodicoAsociado = cargoPeriodicoAsociado;
/*     */   }
/*     */   
/*     */   public String getNombreConsejo() {
/* 182 */     return this.nombreConsejo;
/*     */   }
/*     */   
/*     */   public void setNombreConsejo(String nombreConsejo) {
/* 186 */     this.nombreConsejo = nombreConsejo;
/*     */   }
/*     */   
/*     */   public String getCargoConsejo() {
/* 190 */     return this.cargoConsejo;
/*     */   }
/*     */   
/*     */   public void setCargoConsejo(String cargoConsejo) {
/* 194 */     this.cargoConsejo = cargoConsejo;
/*     */   }
/*     */   
/*     */   public String getCodigoConsejo4() {
/* 198 */     return this.codigoConsejo4;
/*     */   }
/*     */   
/*     */   public void setCodigoConsejo4(String codigoConsejo4) {
/* 202 */     this.codigoConsejo4 = codigoConsejo4;
/*     */   }
/*     */   
/*     */   public String getCodigoConsejo2() {
/* 206 */     return this.codigoConsejo2;
/*     */   }
/*     */   
/*     */   public void setCodigoConsejo2(String codigoConsejo2) {
/* 210 */     this.codigoConsejo2 = codigoConsejo2;
/*     */   }
/*     */   
/*     */   public String getCodigoConsejo3() {
/* 214 */     return this.codigoConsejo3;
/*     */   }
/*     */   
/*     */   public void setCodigoConsejo3(String codigoConsejo3) {
/* 218 */     this.codigoConsejo3 = codigoConsejo3;
/*     */   }
/*     */   
/*     */   public String getCodigoConsejo1() {
/* 222 */     return this.codigoConsejo1;
/*     */   }
/*     */   
/*     */   public void setCodigoConsejo1(String codigoConsejo1) {
/* 226 */     this.codigoConsejo1 = codigoConsejo1;
/*     */   }
/*     */   
/*     */   public String getTransaccionId() {
/* 230 */     return this.transaccionId;
/*     */   }
/*     */   
/*     */   public void setTransaccionId(String transaccionId) {
/* 234 */     this.transaccionId = transaccionId;
/*     */   }
/*     */   
/*     */   public String getOrigin() {
/* 238 */     return this.origin;
/*     */   }
/*     */   
/*     */   public void setOrigin(String origin) {
/* 242 */     this.origin = origin;
/*     */   }
/*     */ }


/* Location:              C:\Users\aruizc01\Desktop\api-movilnet.war!\WEB-INF\classes\ve\com\movilnet\api\cambioplan\CambioRequest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */