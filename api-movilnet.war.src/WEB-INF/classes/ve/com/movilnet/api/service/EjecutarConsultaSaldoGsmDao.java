/*     */ package WEB-INF.classes.ve.com.movilnet.api.service;
/*     */ 
/*     */ import java.net.URL;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.apache.log4j.Logger;
/*     */ import ve.com.movilnet.api.service.to.PrepagoUserMethodTO;
/*     */ import ve.com.movilnet.commons2.servicios.exception.ManejadorExcepciones;
/*     */ import ve.com.movilnet.commons2.servicios.exception.MovilnetException;
/*     */ import ve.com.movilnet.rtb.procesosnegocio.factory.SaldoTo;
/*     */ import ve.com.movilnet.rtb.procesosnegocio.factory.SolicitarSaldoElement;
/*     */ import ve.com.movilnet.rtb.procesosnegocio.factory.SolicitarSaldoResponseElement;
/*     */ import ve.com.movilnet.rtb.procesosnegocio.factory.SolicitudSaldoWebService;
/*     */ import ve.com.movilnet.rtb.procesosnegocio.factory.SolicitudSaldoWebService_Service;
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
/*     */ public class EjecutarConsultaSaldoGsmDao
/*     */ {
/*  34 */   private static final Logger logger = Logger.getLogger(ve.com.movilnet.api.service.EjecutarConsultaSaldoGsmDao.class);
/*     */   
/*     */   public static PrepagoUserMethodTO obtenerSaldo(String transaccionId, String celular, SolicitarSaldoElement requestSD, String setEndpoint) throws Exception {
/*  37 */     PrepagoUserMethodTO objSaldo = new PrepagoUserMethodTO();
/*  38 */     List resultado = null;
/*  39 */     List<SaldoTo> saldoArray = new ArrayList<>();
/*  40 */     long time = System.currentTimeMillis();
/*  41 */     String tipoTransaccion = "APP";
/*     */     
/*  43 */     String tipoObjeto = "Api-Autogestion";
/*  44 */     String plataforma = "PREPAGO";
/*  45 */     String origen = "-EjecutarConsultaSaldoGsmDao.obtenerSaldo()";
/*  46 */     time = System.currentTimeMillis() - time;
/*     */     
/*  48 */     tipoObjeto = time + "|" + tipoObjeto + "|" + plataforma + "|" + origen;
/*  49 */     logger.info(transaccionId + origen);
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/*  54 */       URL wsdlUrl = new URL(setEndpoint);
/*     */       
/*  56 */       SolicitudSaldoWebService_Service service = new SolicitudSaldoWebService_Service(wsdlUrl);
/*  57 */       SolicitudSaldoWebService port = service.getSolicitudSaldoWebServiceSoapHttpPort();
/*     */ 
/*     */ 
/*     */       
/*  61 */       logger.debug(transaccionId + "-EjecutarConsultaSaldoGsmDao requestSD  getString1 : " + requestSD.getString1());
/*  62 */       logger.debug(transaccionId + "-EjecutarConsultaSaldoGsmDao requestSD  getString2: " + requestSD.getString2());
/*  63 */       logger.debug(transaccionId + "-EjecutarConsultaSaldoGsmDao requestSD  getIdUsuario: " + requestSD.getEntradaTo3().getIdUsuario());
/*  64 */       logger.debug(transaccionId + "-EjecutarConsultaSaldoGsmDao requestSD  getNodo: " + requestSD.getEntradaTo3().getNodo());
/*  65 */       logger.debug(transaccionId + "-EjecutarConsultaSaldoGsmDao requestSD  getProveedor : " + requestSD.getEntradaTo3().getProveedor());
/*  66 */       logger.debug(transaccionId + "-EjecutarConsultaSaldoGsmDao requestSD  getTecnologia : " + requestSD.getEntradaTo3().getTecnologia());
/*  67 */       logger.debug(transaccionId + "-EjecutarConsultaSaldoGsmDao requestSD  getAplicacion().getCodigo() : " + requestSD.getEntradaTo3().getAplicacion().getCodigo());
/*  68 */       logger.debug(transaccionId + "-EjecutarConsultaSaldoGsmDao requestSD  getAplicacion().getNombre(): " + requestSD.getEntradaTo3().getAplicacion().getNombre());
/*  69 */       logger.debug(transaccionId + "-EjecutarConsultaSaldoGsmDao requestSD  getAplicacion().getTipoAplicacion(): " + requestSD.getEntradaTo3().getAplicacion().getTipoAplicacion());
/*  70 */       logger.debug(transaccionId + "-EjecutarConsultaSaldoGsmDao requestSD  getSeguridad().getUsuario(): " + requestSD.getEntradaTo3().getSeguridad().getClave());
/*  71 */       logger.debug(transaccionId + "-EjecutarConsultaSaldoGsmDao requestSD  getSeguridad().getUsuario(): " + requestSD.getEntradaTo3().getSeguridad().getUsuario());
/*     */       
/*  73 */       logger.debug(transaccionId + "-EjecutarConsultaSaldoGsmDao  port.solicitarSaldotipoObjeto : " + tipoObjeto);
/*  74 */       SolicitarSaldoResponseElement responseElement = port.solicitarSaldo(requestSD);
/*  75 */       logger.debug(transaccionId + "-EjecutarConsultaSaldoGsmDao  SolicitarSaldoResponseElement : " + responseElement);
/*     */       
/*  77 */       if (responseElement != null) {
/*  78 */         objSaldo.setStatus("1");
/*  79 */         objSaldo.setErrorCode("AJ001");
/*  80 */         objSaldo.setErrorMessage("Operacion Completada Exitosamente");
/*  81 */         objSaldo.setResultData(responseElement);
/*     */       } 
/*  83 */       logger.debug(transaccionId + "-EjecutarConsultaSaldoGsmDao  SolicitarSaldoResponseElement  listo objSaldo.getResultData debe tener valores : " + objSaldo.getResultData());
/*     */     }
/*  85 */     catch (Exception e) {
/*  86 */       logger.error(transaccionId + "-EjecutarConsultaSaldoGsmDao.obtenerSaldo:Exception  " + e.getMessage(), e);
/*     */       
/*  88 */       MovilnetException ex = ManejadorExcepciones.manejar(e);
/*  89 */       objSaldo.setStatus("0");
/*  90 */       logger.warn(transaccionId + "-EjecutarConsultaSaldoGsmDao.obtenerSaldo:Exception  setErrorCode " + ex.getCodigo());
/*  91 */       objSaldo.setErrorCode(ex.getCodigo());
/*  92 */       logger.warn(transaccionId + "-EjecutarConsultaSaldoGsmDao.obtenerSaldo:Exception  setErrorMessage " + ex.toString());
/*  93 */       objSaldo.setErrorMessage(ex.toString());
/*     */       
/*  95 */       if (ex instanceof ve.com.movilnet.commons2.servicios.exception.AdvertenciaFuncionalException) {
/*  96 */         logger.error("EjecutarConsultaSaldoGsmDao.obtenerSaldo.AdvertenciaFuncionalException: " + e.getMessage(), e);
/*  97 */       } else if (ex instanceof ve.com.movilnet.commons2.servicios.exception.SeguridadException) {
/*  98 */         logger.error("EjecutarConsultaSaldoGsmDao.obtenerSaldo.SeguridadException: " + e.getMessage(), e);
/*  99 */       } else if (ex instanceof ve.com.movilnet.commons2.servicios.exception.MantenimientoException) {
/* 100 */         logger.debug("EjecutarConsultaSaldoGsmDao.obtenerSaldo.MantenimientoException: " + e.getMessage(), e);
/* 101 */       } else if (ex instanceof ve.com.movilnet.commons2.servicios.exception.ErrorOperacionalException) {
/* 102 */         logger.error("EjecutarConsultaSaldoGsmDao.obtenerSaldo.ErrorOperacionalException: " + e.getMessage(), e);
/* 103 */       } else if (ex instanceof ve.com.movilnet.commons2.servicios.exception.PlataformaNoDisponibleException) {
/* 104 */         logger.debug("EjecutarConsultaSaldoGsmDao.obtenerSaldo.PlataformaNoDisponibleException: " + e.getMessage(), e);
/*     */       } else {
/* 106 */         logger.error("EjecutarConsultaSaldoGsmDao.obtenerSaldo: " + e.getMessage(), e);
/*     */       } 
/*     */     } 
/* 109 */     return objSaldo;
/*     */   }
/*     */ }


/* Location:              C:\Users\aruizc01\Desktop\api-movilnet.war!\WEB-INF\classes\ve\com\movilnet\api\service\EjecutarConsultaSaldoGsmDao.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */