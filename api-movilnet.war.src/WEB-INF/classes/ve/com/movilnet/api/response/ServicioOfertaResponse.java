/*    */ package WEB-INF.classes.ve.com.movilnet.api.response;
/*    */ 
/*    */ import javax.persistence.GeneratedValue;
/*    */ import javax.persistence.GenerationType;
/*    */ import javax.persistence.Id;
/*    */ import ve.com.movilnet.api.response.Servicios;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ServicioOfertaResponse
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   @Id
/*    */   @GeneratedValue(strategy = GenerationType.AUTO)
/*    */   private Long id;
/*    */   private Servicios servicios;
/*    */   
/*    */   public ServicioOfertaResponse() {}
/*    */   
/*    */   public ServicioOfertaResponse(Servicios servicios) {
/* 24 */     this.servicios = servicios;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Long getId() {
/* 30 */     return this.id;
/*    */   }
/*    */   
/*    */   public void setId(Long id) {
/* 34 */     this.id = id;
/*    */   }
/*    */   
/*    */   public Servicios getServicios() {
/* 38 */     return this.servicios;
/*    */   }
/*    */   
/*    */   public void setServicios(Servicios servicios) {
/* 42 */     this.servicios = servicios;
/*    */   }
/*    */ }


/* Location:              C:\Users\aruizc01\Desktop\api-movilnet.war!\WEB-INF\classes\ve\com\movilnet\api\response\ServicioOfertaResponse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */