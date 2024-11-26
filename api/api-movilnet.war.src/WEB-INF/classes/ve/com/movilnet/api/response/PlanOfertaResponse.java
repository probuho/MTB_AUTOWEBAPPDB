/*    */ package WEB-INF.classes.ve.com.movilnet.api.response;
/*    */ 
/*    */ import javax.persistence.GeneratedValue;
/*    */ import javax.persistence.GenerationType;
/*    */ import javax.persistence.Id;
/*    */ import org.apache.log4j.Logger;
/*    */ import ve.com.movilnet.api.response.Planes;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PlanOfertaResponse
/*    */ {
/* 14 */   private static final Logger logger = Logger.getLogger(ve.com.movilnet.api.response.PlanOfertaResponse.class);
/*    */   
/*    */   private static final long serialVersionUID = 1L;
/*    */   
/*    */   @Id
/*    */   @GeneratedValue(strategy = GenerationType.AUTO)
/*    */   private Long id;
/*    */   
/*    */   private Planes planes;
/*    */   
/*    */   public PlanOfertaResponse() {}
/*    */   
/*    */   public PlanOfertaResponse(Planes planes) {
/* 27 */     this.planes = planes;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Planes getPlanes() {
/* 33 */     return this.planes;
/*    */   }
/*    */   
/*    */   public void setPlanes(Planes planes) {
/* 37 */     this.planes = planes;
/*    */   }
/*    */   
/*    */   public Long getId() {
/* 41 */     return this.id;
/*    */   }
/*    */   
/*    */   public void setId(Long id) {
/* 45 */     this.id = id;
/*    */   }
/*    */ }


/* Location:              C:\Users\aruizc01\Desktop\api-movilnet.war!\WEB-INF\classes\ve\com\movilnet\api\response\PlanOfertaResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */