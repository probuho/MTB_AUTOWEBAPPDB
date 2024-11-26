/*    */ package WEB-INF.classes.ve.com.movilnet.api.service;
/*    */ 
/*    */ import org.apache.log4j.Logger;
/*    */ import ve.com.movilnet.api.service.EjecutarConsultaSaldoGsmDao;
/*    */ import ve.com.movilnet.api.service.to.PrepagoUserMethodTO;
/*    */ import ve.com.movilnet.commons2.servicios.exception.ManejadorExcepciones;
/*    */ import ve.com.movilnet.commons2.servicios.exception.MovilnetException;
/*    */ import ve.com.movilnet.rtb.procesosnegocio.factory.SolicitarSaldoElement;
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
/*    */ public class EjecutarConsultarSaldoPrepagoServiceFacade
/*    */ {
/* 22 */   static Logger logger = Logger.getLogger(ve.com.movilnet.api.service.EjecutarConsultarSaldoPrepagoServiceFacade.class);
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
/*    */   public static PrepagoUserMethodTO consultarSaldoGsmService(String transaccionId, String celular, SolicitarSaldoElement requestSD, String setEndpoint) {
/* 34 */     PrepagoUserMethodTO listSaldo = new PrepagoUserMethodTO();
/*    */     
/* 36 */     String origen = "EjecutarConsultarSaldoPrepagoServiceFacade.consultarSaldoGsmService()";
/* 37 */     long tl = System.currentTimeMillis();
/* 38 */     logger.info(origen + transaccionId);
/*    */ 
/*    */     
/* 41 */     try { logger.debug(origen + transaccionId + "-EjecutarConsultaSaldoGsmDao antes de getResultData  ");
/* 42 */       listSaldo = EjecutarConsultaSaldoGsmDao.obtenerSaldo(transaccionId, celular, requestSD, setEndpoint);
/* 43 */       logger.debug(origen + transaccionId + "-Fin EjecutarConsultaSaldoGsmDao  listSaldo.getResultData  " + listSaldo.getResultData());
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
/* 71 */       return listSaldo; } catch (Exception e) { logger.error(transaccionId + "-EjecutarConsultarSaldoPrepagoServiceFacade.consultarSaldoGsmService:Exception  " + e.getMessage(), e); MovilnetException ex = ManejadorExcepciones.manejar(e); listSaldo.setStatus("0"); listSaldo.setErrorCode(ex.getCodigo()); listSaldo.setErrorMessage(ex.getMensaje()); return listSaldo; }
/*    */     finally
/*    */     { Exception exception = null;
/*    */       logger.debug(origen + transaccionId + " finally  listSaldo  " + listSaldo); }
/*    */   
/*    */   }
/*    */ }


/* Location:              C:\Users\aruizc01\Desktop\api-movilnet.war!\WEB-INF\classes\ve\com\movilnet\api\service\EjecutarConsultarSaldoPrepagoServiceFacade.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */