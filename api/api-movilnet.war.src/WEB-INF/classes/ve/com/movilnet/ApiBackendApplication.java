/*    */ package WEB-INF.classes.ve.com.movilnet;
/*    */ 
/*    */ import org.apache.log4j.Logger;
/*    */ import org.springframework.boot.SpringApplication;
/*    */ import org.springframework.boot.autoconfigure.SpringBootApplication;
/*    */ import org.springframework.cache.annotation.EnableCaching;
/*    */ import org.springframework.context.annotation.PropertySource;
/*    */ import org.springframework.scheduling.annotation.EnableScheduling;
/*    */ import springfox.documentation.swagger2.annotations.EnableSwagger2;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @SpringBootApplication
/*    */ @EnableCaching
/*    */ @EnableScheduling
/*    */ @PropertySource({"file:/opt/configuraciones/application.properties"})
/*    */ @EnableSwagger2
/*    */ public class ApiBackendApplication
/*    */ {
/* 24 */   private static final Logger logger = Logger.getLogger(ve.com.movilnet.ApiBackendApplication.class);
/*    */   
/*    */   static {
/* 27 */     logger.info("ApiBackendApplication.static infome que ya se inicio el deploy de FASE_I 10Abril2024");
/*    */   }
/*    */ 
/*    */   
/*    */   public static void main(String[] args) {
/* 32 */     SpringApplication.run(ve.com.movilnet.ApiBackendApplication.class, args);
/*    */   }
/*    */ }


/* Location:              C:\Users\aruizc01\Desktop\api-movilnet.war!\WEB-INF\classes\ve\com\movilnet\ApiBackendApplication.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */