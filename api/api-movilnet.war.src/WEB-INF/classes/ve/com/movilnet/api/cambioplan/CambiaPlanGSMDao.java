/*    */ package WEB-INF.classes.ve.com.movilnet.api.cambioplan;
/*    */ 
/*    */ import java.net.URL;
/*    */ import org.apache.log4j.Logger;
/*    */ import ve.com.movilnet.api.service.to.PrepagoUserMethodTO;
/*    */ import ve.com.movilnet.apicpprocy.CambiarPlanElement;
/*    */ import ve.com.movilnet.apicpprocy.CambiarPlanResponseElement;
/*    */ import ve.com.movilnet.apicpprocy.CambiarPlanWs;
/*    */ import ve.com.movilnet.apicpprocy.CambiarPlanWs_Service;
/*    */ import ve.com.movilnet.apicpprocy.PlanTo;
/*    */ import ve.com.movilnet.commons2.servicios.exception.ManejadorExcepciones;
/*    */ import ve.com.movilnet.commons2.servicios.exception.MovilnetException;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CambiaPlanGSMDao
/*    */ {
/* 35 */   private static Logger logger = Logger.getLogger(ve.com.movilnet.api.cambioplan.CambiaPlanGSMDao.class);
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static PrepagoUserMethodTO cambiarPlan(String transaccionId, String celular, CambiarPlanElement requestCP, String wsdl) throws Exception {
/* 41 */     PrepagoUserMethodTO respuestaPrepago = new PrepagoUserMethodTO();
/* 42 */     CambiarPlanResponseElement resultado = new CambiarPlanResponseElement();
/* 43 */     PlanTo resultadoPlan = new PlanTo();
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 48 */     try { URL wsdlUrl = new URL(wsdl);
/* 49 */       CambiarPlanWs_Service service = new CambiarPlanWs_Service(wsdlUrl);
/* 50 */       CambiarPlanWs port = service.getCambiarPlanWsSoapHttpPort();
/*    */       
/* 52 */       logger.info(transaccionId + "|CambiaPlanGSMDao.ejecutarCambioPlan " + requestCP.getTransaccionId());
/* 53 */       resultado = port.cambiarPlan(requestCP);
/* 54 */       logger.info(transaccionId + "|resultadoejecutarCambioPlan " + resultado);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */       
/* 95 */       return respuestaPrepago; } catch (Exception e) { logger.error(transaccionId + "|RA4009|CambiaPlanGSMDao.respuestaPrepago:Exception  " + e.getMessage(), e); respuestaPrepago.setStatus("0"); logger.warn("CambiaPlanGSMDao.respuestaPrepago:Exception  setErrorCode "); respuestaPrepago.setErrorCode("RA4009"); logger.warn("CambiaPlanGSMDao.respuestaPrepago:Exception  setErrorMessage "); respuestaPrepago.setErrorMessage(e.toString()); MovilnetException ex = ManejadorExcepciones.manejar(e); if (ex instanceof ve.com.movilnet.commons2.servicios.exception.AdvertenciaFuncionalException) { logger.error("CambiaPlanGSMDao.respuestaPrepago.AdvertenciaFuncionalException: " + e.getMessage(), e); } else if (ex instanceof ve.com.movilnet.commons2.servicios.exception.SeguridadException) { logger.error("CambiaPlanGSMDao.respuestaPrepago.SeguridadException: " + e.getMessage(), e); } else if (ex instanceof ve.com.movilnet.commons2.servicios.exception.MantenimientoException) { logger.debug("CambiaPlanGSMDao.respuestaPrepago.MantenimientoException: " + e.getMessage(), e); } else if (ex instanceof ve.com.movilnet.commons2.servicios.exception.ErrorOperacionalException) { logger.error("CambiaPlanGSMDao.respuestaPrepago.ErrorOperacionalException: " + e.getMessage(), e); } else if (ex instanceof ve.com.movilnet.commons2.servicios.exception.PlataformaNoDisponibleException) { logger.debug("CambiaPlanGSMDao.respuestaPrepago.PlataformaNoDisponibleException: " + e.getMessage(), e); } else { logger.error("CambiaPlanGSMDao.respuestaPrepago: " + e.getMessage(), e); }  return respuestaPrepago; } finally { Exception exception = null; }
/*    */   
/*    */   }
/*    */ }


/* Location:              C:\Users\aruizc01\Desktop\api-movilnet.war!\WEB-INF\classes\ve\com\movilnet\api\cambioplan\CambiaPlanGSMDao.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */