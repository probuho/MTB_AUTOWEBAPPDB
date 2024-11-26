/*    */ package WEB-INF.classes.ve.com.movilnet.api.dao;
/*    */ 
/*    */ import java.sql.Connection;
/*    */ import java.sql.SQLException;
/*    */ import javax.naming.Context;
/*    */ import javax.naming.InitialContext;
/*    */ import javax.sql.DataSource;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ConexionDao
/*    */ {
/*    */   private static ve.com.movilnet.api.dao.ConexionDao instancia;
/*    */   private DataSource dataSource;
/*    */   
/*    */   private ConexionDao() {
/*    */     try {
/* 23 */       Context ctx = new InitialContext();
/* 24 */       this.dataSource = (DataSource)ctx.lookup("java:miDatasource");
/* 25 */     } catch (Exception exception) {}
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public static ve.com.movilnet.api.dao.ConexionDao getInstancia() {
/* 31 */     if (instancia == null) {
/* 32 */       instancia = new ve.com.movilnet.api.dao.ConexionDao();
/*    */     }
/* 34 */     return instancia;
/*    */   }
/*    */   
/*    */   public Connection getConexion() throws SQLException {
/* 38 */     return this.dataSource.getConnection();
/*    */   }
/*    */ }


/* Location:              C:\Users\aruizc01\Desktop\api-movilnet.war!\WEB-INF\classes\ve\com\movilnet\api\dao\ConexionDao.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */