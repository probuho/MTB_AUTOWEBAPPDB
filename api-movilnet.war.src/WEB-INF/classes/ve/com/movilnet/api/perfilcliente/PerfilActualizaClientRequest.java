/*     */ package WEB-INF.classes.ve.com.movilnet.api.perfilcliente;
/*     */ 
/*     */ import javax.validation.constraints.Email;
/*     */ import javax.validation.constraints.NotBlank;
/*     */ import javax.validation.constraints.Pattern;
/*     */ import javax.validation.constraints.Size;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PerfilActualizaClientRequest
/*     */ {
/*     */   @NotBlank
/*     */   @Pattern(regexp = "^[\\w\\s.-]+$")
/*     */   private String transaccionId;
/*     */   @NotBlank
/*     */   @Pattern(regexp = "^\\+?[0-9. ()-]{7,}$")
/*     */   private String telefonoHabitacion;
/*     */   private String telefonoOficina;
/*     */   @NotBlank
/*     */   @Email
/*     */   private String mail;
/*     */   @NotBlank
/*     */   @Size(min = 3, max = 50)
/*     */   private String username;
/*     */   @NotBlank
/*     */   private String origin;
/*     */   @NotBlank
/*     */   private String code;
/*     */   
/*     */   public PerfilActualizaClientRequest() {}
/*     */   
/*     */   public PerfilActualizaClientRequest(String transaccionId, String telefonoHabitacion, String telefonoOficina, String mail, String username, String origin, String code) {
/*  49 */     this.transaccionId = transaccionId;
/*  50 */     this.telefonoHabitacion = telefonoHabitacion;
/*  51 */     this.telefonoOficina = telefonoOficina;
/*  52 */     this.mail = mail;
/*  53 */     this.username = username;
/*  54 */     this.origin = origin;
/*  55 */     this.code = code;
/*     */   }
/*     */   
/*     */   public String getTransaccionId() {
/*  59 */     return this.transaccionId;
/*     */   }
/*     */   
/*     */   public void setTransaccionId(String transaccionId) {
/*  63 */     this.transaccionId = transaccionId;
/*     */   }
/*     */   
/*     */   public String getTelefonoHabitacion() {
/*  67 */     return this.telefonoHabitacion;
/*     */   }
/*     */   
/*     */   public void setTelefonoHabitacion(String telefonoHabitacion) {
/*  71 */     this.telefonoHabitacion = telefonoHabitacion;
/*     */   }
/*     */   
/*     */   public String getTelefonoOficina() {
/*  75 */     return this.telefonoOficina;
/*     */   }
/*     */   
/*     */   public void setTelefonoOficina(String telefonoOficina) {
/*  79 */     this.telefonoOficina = telefonoOficina;
/*     */   }
/*     */   
/*     */   public String getMail() {
/*  83 */     return this.mail;
/*     */   }
/*     */   
/*     */   public void setMail(String mail) {
/*  87 */     this.mail = mail;
/*     */   }
/*     */   
/*     */   public String getUsername() {
/*  91 */     return this.username;
/*     */   }
/*     */   
/*     */   public void setUsername(String username) {
/*  95 */     this.username = username;
/*     */   }
/*     */   
/*     */   public String getOrigin() {
/*  99 */     return this.origin;
/*     */   }
/*     */   
/*     */   public void setOrigin(String origin) {
/* 103 */     this.origin = origin;
/*     */   }
/*     */   
/*     */   public String getCode() {
/* 107 */     return this.code;
/*     */   }
/*     */   
/*     */   public void setCode(String code) {
/* 111 */     this.code = code;
/*     */   }
/*     */ }


/* Location:              C:\Users\aruizc01\Desktop\api-movilnet.war!\WEB-INF\classes\ve\com\movilnet\api\perfilcliente\PerfilActualizaClientRequest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */