/*    */ package WEB-INF.classes.ve.com.movilnet.api.service.util;
/*    */ 
/*    */ import java.util.Map;
/*    */ import org.apache.log4j.Logger;
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
/*    */ public class TrazaLog
/*    */ {
/* 24 */   private static Logger logger = Logger.getLogger(ve.com.movilnet.api.service.util.TrazaLog.class);
/*    */ 
/*    */   
/*    */   public boolean escriTrazaDebug(String codigo, String transaccionId, String tipoTransaccion, String origen, String destino, String aplicacion, String metodo, Map<String, Object> parametersIn, Map<String, Object> parametersOut, long timeEjecution, String mensaje) {
/* 28 */     boolean estado = true;
/*    */     
/* 30 */     logger.debug("|" + transaccionId + "|" + codigo + "|" + mensaje + "|" + tipoTransaccion + "|" + origen + "|" + aplicacion + "|" + destino + "|" + metodo + parametersIn + "|" + parametersOut + "|" + timeEjecution + "|ex");
/*    */     
/* 32 */     return estado;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean escriTrazaInf(String codigo, String transaccionId, String tipoTransaccion, String origen, String destino, String aplicacion, String metodo, Map<String, Object> parametersIn, Map<String, Object> parametersOut, long timeEjecution, String mensaje) {
/* 38 */     boolean estado = true;
/*    */     
/* 40 */     logger.info("|" + transaccionId + "|" + codigo + "|" + mensaje + "|" + tipoTransaccion + "|" + origen + "|" + aplicacion + "|" + destino + "|" + metodo + "|" + parametersIn + "|" + parametersOut + "|" + timeEjecution + "|ex");
/*    */     
/* 42 */     return estado;
/*    */   }
/*    */   public boolean escriTrazaInfToString(String codigo, String transaccionId, String tipoTransaccion, String origen, String destino, String aplicacion, String metodo, Map<String, Object> parametersIn, Map<String, Object> parametersOut, long timeEjecution, String mensaje) {
/* 45 */     boolean estado = true;
/*    */     
/* 47 */     String parametersInString = parametersIn.toString();
/* 48 */     String parametersOutString = parametersOut.toString();
/*    */     
/* 50 */     logger.info("|" + transaccionId + "|" + codigo + "|" + mensaje + "|" + tipoTransaccion + "|" + origen + "|" + aplicacion + "|" + destino + "|" + metodo + "|" + parametersInString + "|" + parametersOutString + "|" + timeEjecution + "|ex");
/*    */     
/* 52 */     return estado;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean escriTrazaError(String codigo, String transaccionId, String tipoTransaccion, String origen, String destino, String aplicacion, String metodo, Map<String, Object> parametersIn, Map<String, Object> parametersOut, long timeEjecution, Throwable ex, String mensaje) {
/* 57 */     boolean estado = true;
/*    */     
/* 59 */     logger.error("|" + transaccionId + "|" + codigo + "|" + mensaje + "|" + tipoTransaccion + "|" + origen + "|" + aplicacion + "|" + destino + "|" + metodo + parametersIn + "|" + parametersOut + "|" + timeEjecution + "|ex");
/*    */     
/* 61 */     return estado;
/*    */   }
/*    */ }


/* Location:              C:\Users\aruizc01\Desktop\api-movilnet.war!\WEB-INF\classes\ve\com\movilnet\api\servic\\util\TrazaLog.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */