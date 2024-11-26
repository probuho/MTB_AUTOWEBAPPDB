/*     */ package WEB-INF.classes.ve.com.movilnet.api.perfilcliente;
/*     */ 
/*     */ import org.apache.log4j.Logger;
/*     */ import ve.com.movilnet.api.perfilcliente.ActualizarDatosPrepagoGSMDao;
/*     */ import ve.com.movilnet.api.service.to.PrepagoUserMethodTO;
/*     */ import ve.com.movilnet.rtb.procesosnegocio.apiactualizadatos.ActualizarDatosClienteElement;
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
/*     */ public class ActualizarDatosPrepagoGSMServiceFacade
/*     */ {
/*  23 */   private static final Logger logger = Logger.getLogger(ve.com.movilnet.api.perfilcliente.ActualizarDatosPrepagoGSMServiceFacade.class);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static PrepagoUserMethodTO actualizarDatosCliente(String transaccionId, String celular, String wsdl, ActualizarDatosClienteElement actualizaClienteTo) {
/*  29 */     String nombreMetodo = "ve.com.movilnet.api.perfilcliente.ActualizarDatosPrepagoGSMServiceFacade: ";
/*  30 */     String nombreCortoMetodo = "actualizarDatosCliente: ";
/*     */     
/*  32 */     int codTransaccion = 0;
/*     */     
/*  34 */     long celularRegistAuditoria = 0L;
/*  35 */     PrepagoUserMethodTO codRegistro = null;
/*  36 */     PrepagoUserMethodTO codAuditoria = null;
/*  37 */     int codAccion = 0;
/*  38 */     int codServicioWeb = 0;
/*  39 */     String observaciones = null;
/*  40 */     String respuestaSMS = null;
/*  41 */     String mensajeSMS = null;
/*  42 */     PrepagoUserMethodTO objActuDatosPrep = new PrepagoUserMethodTO();
/*  43 */     PrepagoUserMethodTO objRespTransaccion = new PrepagoUserMethodTO();
/*     */ 
/*     */     
/*  46 */     String mensajeError = null;
/*     */     
/*  48 */     boolean actualizarDatosOK = false;
/*  49 */     String numeroCambios = "OK";
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  54 */     long time = 0L;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/*  64 */       if (numeroCambios.equalsIgnoreCase("OK"))
/*     */       {
/*  66 */         objActuDatosPrep = ActualizarDatosPrepagoGSMDao.actualizarDatosGSM(transaccionId, wsdl, actualizaClienteTo);
/*     */         
/*  68 */         if (objActuDatosPrep != null && objActuDatosPrep
/*  69 */           .getStatus() != null && objActuDatosPrep
/*  70 */           .getStatus().equalsIgnoreCase("1") && objActuDatosPrep
/*  71 */           .getErrorCode().equalsIgnoreCase("AJ001"))
/*     */         {
/*     */           
/*  74 */           objRespTransaccion.setStatus("1");
/*  75 */           objRespTransaccion = objActuDatosPrep;
/*  76 */           actualizarDatosOK = true;
/*     */         
/*     */         }
/*     */ 
/*     */       
/*     */       }
/*     */     
/*     */     }
/*  84 */     catch (NumberFormatException numberFormatException) {
/*     */       
/*  86 */       objRespTransaccion.setStatus("0");
/*  87 */       objRespTransaccion.setErrorCode("(NUMBER_FORMAT)ERROR");
/*  88 */       objRespTransaccion.setErrorMessage(nombreMetodo + numberFormatException
/*  89 */           .getMessage());
/*  90 */       objRespTransaccion.setResultData("<br>Estimado cliente, tu requerimiento no puede ser procesado en estos momentos.");
/*  91 */       logger.error(nombreCortoMetodo + objActuDatosPrep.getErrorCode() + " " + numberFormatException
/*  92 */           .getMessage().toString());
/*  93 */     } catch (Exception ex) {
/*     */       
/*  95 */       objRespTransaccion.setStatus("0");
/*  96 */       objRespTransaccion.setErrorCode("(GEN)ERROR");
/*  97 */       objRespTransaccion.setErrorMessage(nombreMetodo + ex.getMessage());
/*  98 */       objRespTransaccion.setResultData("<br>Estimado cliente, tu requerimiento no puede ser procesado en estos momentos.");
/*  99 */       logger.error(nombreCortoMetodo + objActuDatosPrep.getErrorCode() + " " + ex
/* 100 */           .getMessage().toString());
/*     */     } 
/*     */     
/* 103 */     return objRespTransaccion;
/*     */   }
/*     */ }


/* Location:              C:\Users\aruizc01\Desktop\api-movilnet.war!\WEB-INF\classes\ve\com\movilnet\api\perfilcliente\ActualizarDatosPrepagoGSMServiceFacade.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */