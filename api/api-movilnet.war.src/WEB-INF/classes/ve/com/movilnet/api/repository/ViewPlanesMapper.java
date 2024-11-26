/*    */ package WEB-INF.classes.ve.com.movilnet.api.repository;
/*    */ 
/*    */ import java.sql.ResultSet;
/*    */ import java.sql.SQLException;
/*    */ import org.springframework.jdbc.core.RowMapper;
/*    */ import ve.com.movilnet.api.repository.ViewPlane;
/*    */ 
/*    */ public class ViewPlanesMapper
/*    */   implements RowMapper<ViewPlane>
/*    */ {
/*    */   public ViewPlane mapRow(ResultSet rs, int rowNum) throws SQLException {
/* 12 */     return new ViewPlane(rs.getString("PLAN_OFFERING_CODE"), rs
/* 13 */         .getString("COMMERCIAL_LAUNCH_NAME"), rs
/* 14 */         .getBigDecimal("COSTO"), rs
/* 15 */         .getString("COS_PLAN_NAME"), rs
/* 16 */         .getBigDecimal("ACCUMULATED_SMS"), rs
/* 17 */         .getBigDecimal("ACCUMULATED_MB"), rs
/* 18 */         .getString("TIPO_PLAN"), rs
/* 19 */         .getBigDecimal("ACCUMULATED_USAGE"), rs
/* 20 */         .getString("ACTIVE_CAMBIO_PLAN"), rs.getString("SUFIJO_NOMBRE_PLAN"), rs.getString("ID_TIPO_PLAN"), rs.getString("UNIDAD_PLAN"));
/*    */   }
/*    */ }


/* Location:              C:\Users\aruizc01\Desktop\api-movilnet.war!\WEB-INF\classes\ve\com\movilnet\api\repository\ViewPlanesMapper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */