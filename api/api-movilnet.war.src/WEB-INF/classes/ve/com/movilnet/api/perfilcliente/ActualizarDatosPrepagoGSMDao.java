/*     */ package WEB-INF.classes.ve.com.movilnet.api.perfilcliente;
/*     */ 
/*     */ import java.net.URL;
/*     */ import org.apache.log4j.Logger;
/*     */ import ve.com.movilnet.api.service.to.PrepagoUserMethodTO;
/*     */ import ve.com.movilnet.commons2.servicios.exception.ManejadorExcepciones;
/*     */ import ve.com.movilnet.commons2.servicios.exception.MovilnetException;
/*     */ import ve.com.movilnet.rtb.procesosnegocio.apiactualizadatos.ActualizarDatosClienteElement;
/*     */ import ve.com.movilnet.rtb.procesosnegocio.apiactualizadatos.ActualizarDatosClienteResponseElement;
/*     */ import ve.com.movilnet.rtb.procesosnegocio.apiactualizadatos.ActualizarDatosClienteWebService;
/*     */ import ve.com.movilnet.rtb.procesosnegocio.apiactualizadatos.ActualizarDatosClienteWebService_Service;
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
/*     */ public class ActualizarDatosPrepagoGSMDao
/*     */ {
/*  32 */   private static final Logger logger = Logger.getLogger(ve.com.movilnet.api.perfilcliente.ActualizarDatosPrepagoGSMDao.class);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static PrepagoUserMethodTO actualizarDatosGSM(String transaccionId, String wsdl, ActualizarDatosClienteElement actualizaClienteTo) throws Exception {
/*  39 */     String nombreCortoMetodo = "actualizarDatosGSM: ";
/*  40 */     PrepagoUserMethodTO objTo = new PrepagoUserMethodTO();
/*  41 */     ActualizarDatosClienteResponseElement respuestaActualizacion = new ActualizarDatosClienteResponseElement();
/*     */ 
/*     */ 
/*     */     
/*  45 */     try { URL wsdlUrl = new URL(wsdl);
/*  46 */       ActualizarDatosClienteWebService_Service actulizaService = new ActualizarDatosClienteWebService_Service(wsdlUrl);
/*  47 */       ActualizarDatosClienteWebService port = actulizaService.getActualizarDatosClienteWebServiceSoapHttpPort();
/*  48 */       logger.info("ActualizarDatosPrepagoGSMDao.actualizarDatosGSM " + actualizaClienteTo.getTransaccionId());
/*  49 */       respuestaActualizacion = port.actualizarDatosCliente(actualizaClienteTo);
/*  50 */       logger.info("ActualizarDatosPrepagoGSMDao.actualizarDatosGSM luego del port  " + actualizaClienteTo);
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
/* 108 */       return objTo; } catch (Exception e) { logger.error("RA4010|ActualizarDatosPrepagoGSMDao.actualizarDatosGSM: Error al ejecutar la Actualizacion de Datos " + e); logger.error("ActualizarDatosPrepagoGSMDao.actualizarDatosGSM-SOPORTE-FALLA---" + e.getMessage(), e); objTo.setStatus("0"); MovilnetException ex = ManejadorExcepciones.manejar(e); logger.error("ActualizarDatosPrepagoGSMDao.actualizarDatosGSM-SOPORTE-FALLA2---" + nombreCortoMetodo, e); objTo.setErrorCode(ex.getCodigo()); logger.error("para que ex.getCodigo() " + ex.getCodigo()); if (ex instanceof ve.com.movilnet.commons2.servicios.exception.AdvertenciaFuncionalException) { logger.error("ActualizarDatosPrepagoGSMDao.actualizarDatosGSM.AdvertenciaFuncionalException: Error al ejecutar la Actualizacion de Datos " + e.getMessage(), e); } else if (ex instanceof ve.com.movilnet.commons2.servicios.exception.SeguridadException) { logger.error("ActualizarDatosPrepagoGSMDao.actualizarDatosGSM.SeguridadException: Error al ejecutar la Actualizacion de Datos " + e.getMessage(), e); } else if (ex instanceof ve.com.movilnet.commons2.servicios.exception.MantenimientoException) { logger.debug("ActualizarDatosPrepagoGSMDao.actualizarDatosGSM.MantenimientoException: Servicio en Modo Mantenimiento " + e.getMessage(), e); } else if (ex instanceof ve.com.movilnet.commons2.servicios.exception.ErrorOperacionalException) { logger.error("ActualizarDatosPrepagoGSMDao.actualizarDatosGSM.ErrorOperacionalException: Error al ejecutar la Actualizacion de Datos  " + e.getMessage(), e); } else if (ex instanceof ve.com.movilnet.commons2.servicios.exception.PlataformaNoDisponibleException) { logger.debug("ActualizarDatosPrepagoGSMDao.actualizarDatosGSM.PlataformaNoDisponibleException: Plataforma no Disponible  " + e.getMessage(), e); }  return objTo; } finally { Exception exception = null; }
/*     */   
/*     */   }
/*     */ }


/* Location:              C:\Users\aruizc01\Desktop\api-movilnet.war!\WEB-INF\classes\ve\com\movilnet\api\perfilcliente\ActualizarDatosPrepagoGSMDao.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */