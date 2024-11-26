/*     */ package WEB-INF.classes.ve.com.movilnet.api.perfilcliente;
/*     */ 
/*     */ import java.util.List;
/*     */ import org.apache.log4j.Logger;
/*     */ import org.springframework.boot.SpringApplication;
/*     */ import ve.com.movilnet.api.perfilcliente.DireccionTO_Name;
/*     */ import ve.com.movilnet.rtb.procesosnegocio.bp.BuscarTelefonoRespuestaTo;
/*     */ import ve.com.movilnet.rtb.procesosnegocio.factory.SaldoTo;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PerfilResponse
/*     */ {
/*     */   BuscarTelefonoRespuestaTo resultado;
/*     */   private List<SaldoTo> saldoTo;
/*  21 */   private String fechaPago = null;
/*  22 */   private String codigo = null;
/*     */   private String subCodigo;
/*     */   
/*     */   public List<SaldoTo> getSaldoTo() {
/*  26 */     return this.saldoTo;
/*     */   }
/*     */   private String transaccionId; private DireccionTO_Name direccionTO_Name;
/*     */   
/*     */   public void setSaldoTo(List<SaldoTo> saldoTo) {
/*  31 */     this.saldoTo = saldoTo;
/*     */   }
/*     */   
/*     */   public BuscarTelefonoRespuestaTo getResultado() {
/*  35 */     return this.resultado;
/*     */   }
/*     */   
/*     */   public void setResultado(BuscarTelefonoRespuestaTo resultado) {
/*  39 */     this.resultado = resultado;
/*     */   }
/*     */   
/*     */   public String getCodigo() {
/*  43 */     return this.codigo;
/*     */   }
/*     */   
/*     */   public void setCodigo(String codigo) {
/*  47 */     this.codigo = codigo;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  53 */   private static final Logger logger = Logger.getLogger(ve.com.movilnet.api.perfilcliente.PerfilResponse.class);
/*     */ 
/*     */   
/*     */   public static void main(String[] args) {
/*  57 */     long time1 = 0L;
/*  58 */     String transaccionId = "Autogestion--" + time1;
/*     */     
/*  60 */     time1 = System.currentTimeMillis();
/*     */     
/*  62 */     String origen = "PerfilResponse";
/*  63 */     String tipoTransaccion = "APP";
/*     */     
/*  65 */     SpringApplication.run(ve.com.movilnet.api.perfilcliente.PerfilResponse.class, args);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getFechaPago() {
/*  73 */     return this.fechaPago;
/*     */   }
/*     */   
/*     */   public void setFechaPago(String fechaPago) {
/*  77 */     this.fechaPago = fechaPago;
/*     */   }
/*     */   
/*     */   public String getSubCodigo() {
/*  81 */     return this.subCodigo;
/*     */   }
/*     */   
/*     */   public void setSubCodigo(String subCodigo) {
/*  85 */     this.subCodigo = subCodigo;
/*     */   }
/*     */   
/*     */   public String getTransaccionId() {
/*  89 */     return this.transaccionId;
/*     */   }
/*     */   
/*     */   public void setTransaccionId(String transaccionId) {
/*  93 */     this.transaccionId = transaccionId;
/*     */   }
/*     */   
/*     */   public DireccionTO_Name getDireccionTO_Name() {
/*  97 */     return this.direccionTO_Name;
/*     */   }
/*     */   
/*     */   public void setDireccionTO_Name(DireccionTO_Name direccionTO_Name) {
/* 101 */     this.direccionTO_Name = direccionTO_Name;
/*     */   }
/*     */ }


/* Location:              C:\Users\aruizc01\Desktop\api-movilnet.war!\WEB-INF\classes\ve\com\movilnet\api\perfilcliente\PerfilResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */