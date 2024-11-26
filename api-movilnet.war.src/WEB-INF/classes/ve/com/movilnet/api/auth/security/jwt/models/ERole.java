/*    */ package WEB-INF.classes.ve.com.movilnet.api.auth.security.jwt.models;
/*    */ 
/*    */ import org.apache.log4j.Logger;
/*    */ import org.springframework.boot.SpringApplication;
/*    */ 
/*    */ 
/*    */ 
/*    */ public enum ERole
/*    */ {
/* 10 */   ROLE_USER,
/* 11 */   ROLE_MODERATOR,
/* 12 */   ROLE_ADMIN, ROLE_AUDITOR, ROLE_DEFAULT, ROLE_FULL;
/*    */   static {
/* 14 */     logger = Logger.getLogger(ve.com.movilnet.api.auth.security.jwt.models.ERole.class);
/*    */   }
/*    */   private static final Logger logger;
/*    */   public static void main(String[] args) {
/* 18 */     long time1 = 0L;
/* 19 */     String transaccionId = "Autogestion--" + time1;
/*    */     
/* 21 */     time1 = System.currentTimeMillis();
/*    */     
/* 23 */     String origen = "ERole";
/* 24 */     String tipoTransaccion = "APP";
/*    */     
/* 26 */     SpringApplication.run(ve.com.movilnet.api.auth.security.jwt.models.ERole.class, args);
/*    */   }
/*    */ }


/* Location:              C:\Users\aruizc01\Desktop\api-movilnet.war!\WEB-INF\classes\ve\com\movilnet\api\auth\security\jwt\models\ERole.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */