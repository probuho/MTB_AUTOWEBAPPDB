/*    */ package WEB-INF.classes.ve.com.movilnet.api.auth.security.jwt.models;
/*    */ 
/*    */ import javax.persistence.Column;
/*    */ import javax.persistence.EnumType;
/*    */ import javax.persistence.Enumerated;
/*    */ import javax.persistence.GeneratedValue;
/*    */ import javax.persistence.GenerationType;
/*    */ import javax.persistence.Id;
/*    */ import org.apache.log4j.Logger;
/*    */ import org.springframework.boot.SpringApplication;
/*    */ import ve.com.movilnet.api.auth.security.jwt.models.ERole;
/*    */ 
/*    */ public class Role {
/* 14 */   private static final Logger logger = Logger.getLogger(ve.com.movilnet.api.auth.security.jwt.models.Role.class);
/*    */ 
/*    */   
/*    */   public static void main(String[] args) {
/* 18 */     long time1 = 0L;
/* 19 */     String transaccionId = "Autogestion--" + time1;
/*    */     
/* 21 */     time1 = System.currentTimeMillis();
/*    */     
/* 23 */     String origen = "Role";
/* 24 */     String tipoTransaccion = "APP";
/*    */     
/* 26 */     SpringApplication.run(ve.com.movilnet.api.auth.security.jwt.models.Role.class, args);
/*    */   }
/*    */ 
/*    */   
/*    */   @Id
/*    */   @GeneratedValue(strategy = GenerationType.IDENTITY)
/*    */   private Integer id;
/*    */   
/*    */   @Enumerated(EnumType.STRING)
/*    */   @Column(length = 20)
/*    */   private ERole name;
/*    */ 
/*    */   
/*    */   public Role() {}
/*    */   
/*    */   public Role(ERole name) {
/* 42 */     this.name = name;
/*    */   }
/*    */   
/*    */   public Integer getId() {
/* 46 */     return this.id;
/*    */   }
/*    */   
/*    */   public void setId(Integer id) {
/* 50 */     this.id = id;
/*    */   }
/*    */   
/*    */   public ERole getName() {
/* 54 */     return this.name;
/*    */   }
/*    */   
/*    */   public void setName(ERole name) {
/* 58 */     this.name = name;
/*    */   }
/*    */ }


/* Location:              C:\Users\aruizc01\Desktop\api-movilnet.war!\WEB-INF\classes\ve\com\movilnet\api\auth\security\jwt\models\Role.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */