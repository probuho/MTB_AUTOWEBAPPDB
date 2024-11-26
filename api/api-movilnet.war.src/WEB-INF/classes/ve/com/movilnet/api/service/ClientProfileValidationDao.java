/*     */ package WEB-INF.classes.ve.com.movilnet.api.service;
/*     */ 
/*     */ import java.net.URL;
/*     */ import org.apache.log4j.Logger;
/*     */ import ve.com.movilnet.api.service.to.PrepagoUserMethodTO;
/*     */ import ve.com.movilnet.commons2.servicios.exception.ManejadorExcepciones;
/*     */ import ve.com.movilnet.commons2.servicios.exception.MovilnetException;
/*     */ import ve.com.movilnet.rtb.procesosnegocio.bp.BuscarTelefonoElement;
/*     */ import ve.com.movilnet.rtb.procesosnegocio.bp.BuscarTelefonoResponseElement;
/*     */ import ve.com.movilnet.rtb.procesosnegocio.bp.BuscarTelefonoRespuestaTo;
/*     */ import ve.com.movilnet.rtb.procesosnegocio.bp.BuscarTelefonoWebService;
/*     */ import ve.com.movilnet.rtb.procesosnegocio.bp.BuscarTelefonoWebService_Service;
/*     */ import ve.com.movilnet.rtb.procesosnegocio.bp.EntradaTo;
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
/*     */ public class ClientProfileValidationDao
/*     */ {
/*  35 */   static Logger logger = Logger.getLogger(ve.com.movilnet.api.service.ClientProfileValidationDao.class);
/*     */ 
/*     */   
/*     */   public PrepagoUserMethodTO BuscarTelefono(String transaccionId, String celular, EntradaTo entrada1, String setEndpoint) {
/*  39 */     BuscarTelefonoRespuestaTo resultado = new BuscarTelefonoRespuestaTo();
/*  40 */     PrepagoUserMethodTO objPrepagoBuscarTlf = new PrepagoUserMethodTO();
/*  41 */     logger.info(transaccionId + "-Luego ClientProfileValidationDao.BuscarTelefono numero importante!!!:" + celular);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  47 */     try { BuscarTelefonoElement request = new BuscarTelefonoElement();
/*  48 */       request.setTransaccionId(transaccionId);
/*  49 */       request.setNumeroCelular(celular);
/*  50 */       request.setEntrada(entrada1);
/*  51 */       logger.debug(transaccionId + "-ClientProfileValidationDao BuscarTelefonoElement  getNumeroCelular: " + request.getNumeroCelular());
/*  52 */       logger.debug(transaccionId + "-ClientProfileValidationDao BuscarTelefonoElement  getTransaccionId: " + request.getTransaccionId());
/*  53 */       logger.debug(transaccionId + "-ClientProfileValidationDao BuscarTelefonoElement  getIdUsuario: " + entrada1.getIdUsuario());
/*  54 */       logger.debug(transaccionId + "-ClientProfileValidationDao BuscarTelefonoElement  getNodo: " + entrada1.getNodo());
/*  55 */       logger.debug(transaccionId + "-ClientProfileValidationDao BuscarTelefonoElement  getProveedor: " + entrada1.getProveedor());
/*  56 */       logger.debug(transaccionId + "-ClientProfileValidationDao BuscarTelefonoElement  getTecnologia: " + entrada1.getTecnologia());
/*  57 */       logger.debug(transaccionId + "-ClientProfileValidationDao BuscarTelefonoElement  getAplicacion().getCodigo(): " + entrada1.getAplicacion().getCodigo());
/*  58 */       logger.debug(transaccionId + "-ClientProfileValidationDao BuscarTelefonoElement  getAplicacion().getNombre(): " + entrada1.getAplicacion().getNombre());
/*  59 */       logger.debug(transaccionId + "-ClientProfileValidationDao BuscarTelefonoElement  getAplicacion().getTipoAplicacion(): " + entrada1.getAplicacion().getTipoAplicacion());
/*  60 */       logger.debug(transaccionId + "-ClientProfileValidationDao BuscarTelefonoElement  getSeguridad().getUsuario(): " + entrada1.getSeguridad().getClave());
/*  61 */       logger.debug(transaccionId + "-ClientProfileValidationDao BuscarTelefonoElement  getSeguridad().getUsuario(): " + entrada1.getSeguridad().getUsuario());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  70 */       URL wsdlUrl = new URL(setEndpoint);
/*  71 */       logger.debug(transaccionId + "-BuscarTelefonoElement wsdlUrl antes de parametros: ");
/*     */       
/*  73 */       BuscarTelefonoWebService_Service servicio = new BuscarTelefonoWebService_Service(wsdlUrl);
/*  74 */       logger.debug(transaccionId + "-ClientProfileValidationDao.BuscarTelefono BuscarTelefonoWebService_Service:  " + transaccionId);
/*  75 */       BuscarTelefonoWebService port = servicio.getBuscarTelefonoWebServiceSoapHttpPort();
/*     */ 
/*     */ 
/*     */       
/*  79 */       logger.debug(transaccionId + "-ClientProfileValidationDao.BuscarTelefono request:  " + transaccionId);
/*     */       
/*  81 */       BuscarTelefonoResponseElement responseElement = port.buscarTelefono(request);
/*  82 */       logger.debug(transaccionId + "-ClientProfileValidationDao.BuscarTelefono listo  port:   responseElement " + responseElement);
/*     */       
/*  84 */       BuscarTelefonoRespuestaTo response = responseElement.getResult();
/*     */ 
/*     */       
/*  87 */       logger.info(transaccionId + "-ClientProfileValidationDao.BuscarTelefono listo  responseElement.response():  " + response.getCliente());
/*     */       
/*  89 */       resultado = response;
/*     */ 
/*     */       
/*  92 */       logger.debug(transaccionId + "-ClientProfileValidationDao. setEndpoint  buscarTelefono resultado: " + resultado);
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
/* 130 */       return objPrepagoBuscarTlf; } catch (Exception ex) { logger.error(transaccionId + "-ClientProfileValidationDao. Exception|" + ex); objPrepagoBuscarTlf.setStatus("0"); objPrepagoBuscarTlf.setErrorCode("(GEN)ERROR"); objPrepagoBuscarTlf.setErrorMessage(ex.toString()); logger.error(transaccionId + "-ClientProfileValidationDao. Exception getErrorMessage|" + objPrepagoBuscarTlf.getErrorMessage()); MovilnetException movilnetException = ManejadorExcepciones.manejar(ex); if (movilnetException instanceof ve.com.movilnet.commons2.servicios.exception.AdvertenciaFuncionalException) { logger.error("ClientProfileValidationDao.BuscarTelefono.AdvertenciaFuncionalException: " + movilnetException.getMessage(), (Throwable)movilnetException); MovilnetException exc = ManejadorExcepciones.manejar(ex); objPrepagoBuscarTlf.setErrorCode(exc.getCodigo()); }  if (movilnetException instanceof ve.com.movilnet.commons2.servicios.exception.ErrorOperacionalException) { logger.error("ClientProfileValidationDao.BuscarTelefono.ErrorOperacionalException: " + movilnetException.getMessage(), (Throwable)movilnetException); MovilnetException exc = ManejadorExcepciones.manejar(ex); objPrepagoBuscarTlf.setErrorCode(exc.getCodigo()); } else if (movilnetException instanceof ve.com.movilnet.commons2.servicios.exception.MantenimientoException) { MovilnetException exc = ManejadorExcepciones.manejar(ex); objPrepagoBuscarTlf.setErrorCode(exc.getCodigo()); logger.debug("ClientProfileValidationDao.BuscarTelefono.MantenimientoException: " + movilnetException.getMessage(), (Throwable)movilnetException); } else if (movilnetException instanceof ve.com.movilnet.commons2.servicios.exception.SeguridadException) { MovilnetException exc = ManejadorExcepciones.manejar(ex); objPrepagoBuscarTlf.setErrorCode(exc.getCodigo()); logger.debug("ClientProfileValidationDao.BuscarTelefono.SeguridadException: " + movilnetException.getMessage(), (Throwable)movilnetException); }  return objPrepagoBuscarTlf; } finally { Exception exception = null; }
/*     */   
/*     */   }
/*     */ }


/* Location:              C:\Users\aruizc01\Desktop\api-movilnet.war!\WEB-INF\classes\ve\com\movilnet\api\service\ClientProfileValidationDao.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */