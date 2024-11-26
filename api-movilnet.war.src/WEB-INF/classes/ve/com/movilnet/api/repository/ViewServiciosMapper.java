/*    */ package WEB-INF.classes.ve.com.movilnet.api.repository;
/*    */ 
/*    */ import java.sql.ResultSet;
/*    */ import java.sql.SQLException;
/*    */ import org.springframework.jdbc.core.RowMapper;
/*    */ import ve.com.movilnet.api.repository.ViewServicios;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ViewServiciosMapper
/*    */   implements RowMapper<ViewServicios>
/*    */ {
/*    */   public ViewServicios mapRow(ResultSet rs, int rowNum) throws SQLException {
/* 19 */     return new ViewServicios(rs
/* 20 */         .getString("COD_SERVICE"), rs
/* 21 */         .getString("DESC_SERVICIO"), rs
/* 22 */         .getString("DESCRIPCION"), rs
/* 23 */         .getString("ALCO_NAME"), rs
/* 24 */         .getBigDecimal("COSTO"), rs
/* 25 */         .getString("ACTIVE"));
/*    */   }
/*    */ }


/* Location:              C:\Users\aruizc01\Desktop\api-movilnet.war!\WEB-INF\classes\ve\com\movilnet\api\repository\ViewServiciosMapper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */