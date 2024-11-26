/*    */ package WEB-INF.classes.ve.com.movilnet;
/*    */ 
/*    */ import javax.naming.Context;
/*    */ import javax.naming.InitialContext;
/*    */ import javax.naming.NamingException;
/*    */ import javax.sql.DataSource;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ConfiguracionBD
/*    */ {
/*    */   public DataSource dataSource() throws IllegalArgumentException, NamingException {
/*    */     try {
/* 17 */       Context ctx = new InitialContext();
/* 18 */       return (DataSource)ctx.lookup("java:jboss/datasources/datainf");
/* 19 */     } catch (NamingException e) {
/*    */       
/* 21 */       throw new NamingException("Error al buscar el DataSource: " + e.getMessage());
/* 22 */     } catch (IllegalArgumentException e) {
/*    */       
/* 24 */       throw new IllegalArgumentException("Argumento ilegal: " + e.getMessage());
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\aruizc01\Desktop\api-movilnet.war!\WEB-INF\classes\ve\com\movilnet\ConfiguracionBD.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */