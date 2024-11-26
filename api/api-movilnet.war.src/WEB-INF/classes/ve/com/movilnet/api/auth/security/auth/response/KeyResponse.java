/*    */ package WEB-INF.classes.ve.com.movilnet.api.auth.security.auth.response;
/*    */ 
/*    */ import org.apache.log4j.Logger;
/*    */ import org.springframework.boot.SpringApplication;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class KeyResponse
/*    */ {
/*    */   private String username;
/*    */   private String mensaje;
/*    */   private String codigo;
/*    */   private String subcode;
/* 16 */   private static final Logger logger = Logger.getLogger(ve.com.movilnet.api.auth.security.auth.response.KeyResponse.class);
/*    */ 
/*    */   
/*    */   public static void main(String[] args) {
/* 20 */     long time1 = 0L;
/* 21 */     String transaccionId = "Autogestion--" + time1;
/*    */     
/* 23 */     time1 = System.currentTimeMillis();
/*    */     
/* 25 */     String origen = "KeyResponse";
/* 26 */     String tipoTransaccion = "APP";
/*    */     
/* 28 */     SpringApplication.run(ve.com.movilnet.api.auth.security.auth.response.KeyResponse.class, args);
/*    */   }
/*    */   
/*    */   public KeyResponse(String username, String mensaje, String codigo, String subcode) {
/* 32 */     this.username = username;
/* 33 */     this.mensaje = mensaje;
/* 34 */     this.codigo = codigo;
/* 35 */     this.subcode = subcode;
/*    */   }
/*    */   
/*    */   public String getSubcode() {
/* 39 */     return this.subcode;
/*    */   }
/*    */   
/*    */   public void setSubcode(String subcode) {
/* 43 */     this.subcode = subcode;
/*    */   }
/*    */   
/*    */   public String getCodigo() {
/* 47 */     return this.codigo;
/*    */   }
/*    */   
/*    */   public void setCodigo(String codigo) {
/* 51 */     this.codigo = codigo;
/*    */   }
/*    */   
/*    */   public String getUsername() {
/* 55 */     return this.username;
/*    */   }
/*    */   
/*    */   public void setUsername(String username) {
/* 59 */     this.username = username;
/*    */   }
/*    */   
/*    */   public String getMensaje() {
/* 63 */     return this.mensaje;
/*    */   }
/*    */   
/*    */   public void setMensaje(String mensaje) {
/* 67 */     this.mensaje = mensaje;
/*    */   }
/*    */ }


/* Location:              C:\Users\aruizc01\Desktop\api-movilnet.war!\WEB-INF\classes\ve\com\movilnet\api\auth\security\auth\response\KeyResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */