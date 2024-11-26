/*    */ package WEB-INF.classes.ve.com.movilnet.api.cambioplan;
/*    */ 
/*    */ import org.apache.log4j.Logger;
/*    */ import ve.com.movilnet.api.cambioplan.CambiaPlanGSMDao;
/*    */ import ve.com.movilnet.api.service.to.PrepagoUserMethodTO;
/*    */ import ve.com.movilnet.apicpprocy.CambiarPlanElement;
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
/*    */ public class CambiaPlanGSMServiceFacade
/*    */ {
/* 21 */   private static final Logger logger = Logger.getLogger(ve.com.movilnet.api.cambioplan.CambiaPlanGSMServiceFacade.class);
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
/*    */   public static PrepagoUserMethodTO ejecutarCambioPlan(String transaccionId, String celular, CambiarPlanElement requestCP, String wsdl) {
/* 36 */     PrepagoUserMethodTO objRespTransaccion = new PrepagoUserMethodTO();
/*    */     
/* 38 */     String dealer = null;
/*    */     
/* 40 */     String nombreCorto = "ejecutarCambioPlan: ";
/* 41 */     String inicio = "ve.com.movilnet";
/* 42 */     String errorSP = "ORA";
/*    */ 
/*    */     
/* 45 */     String tipoTrans = "Individual";
/* 46 */     String operacion = "0";
/* 47 */     String aplicaop = "N";
/* 48 */     int numDatos = 8;
/*    */ 
/*    */ 
/*    */     
/* 52 */     try { objRespTransaccion = CambiaPlanGSMDao.cambiarPlan(transaccionId, celular, requestCP, wsdl);
/* 53 */       logger.info(transaccionId + "Luego de invocar CambiaPlanGSMServiceFacade.Cambio!: " + objRespTransaccion);
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
/*    */ 
/*    */       
/* 96 */       return objRespTransaccion; } catch (Exception e) { logger.error(transaccionId + "Exception Cambio de Plan NOK!: " + e); objRespTransaccion.setStatus("0"); objRespTransaccion.setErrorCode("RA4010-2"); objRespTransaccion.setErrorMessage("RA4010-2"); return objRespTransaccion; } finally { Exception exception = null; }
/*    */   
/*    */   }
/*    */ }


/* Location:              C:\Users\aruizc01\Desktop\api-movilnet.war!\WEB-INF\classes\ve\com\movilnet\api\cambioplan\CambiaPlanGSMServiceFacade.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */