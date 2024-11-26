/*    */ package WEB-INF.classes.ve.com.movilnet;
/*    */ 
/*    */ import org.apache.log4j.Logger;
/*    */ import org.apache.log4j.xml.DOMConfigurator;
/*    */ import org.springframework.boot.builder.SpringApplicationBuilder;
/*    */ import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
/*    */ import ve.com.movilnet.ApiBackendApplication;
/*    */ 
/*    */ public class ServletInitializer
/*    */   extends SpringBootServletInitializer {
/* 11 */   private static final Logger logger = Logger.getLogger(ve.com.movilnet.ServletInitializer.class);
/*    */ 
/*    */ 
/*    */   
/*    */   protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
/* 16 */     logger.info("ServletInitializer.configure carga de log4j en una ruta externa FASE_I 10Abril2024");
/* 17 */     DOMConfigurator.configure("/opt/configuraciones/log4j.xml");
/* 18 */     logger.info("ServletInitializer.configure ya esta cargado log4j FASE_I 10Abril2024 ");
/*    */     
/* 20 */     return application.sources(new Class[] { ApiBackendApplication.class });
/*    */   }
/*    */ }


/* Location:              C:\Users\aruizc01\Desktop\api-movilnet.war!\WEB-INF\classes\ve\com\movilnet\ServletInitializer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */