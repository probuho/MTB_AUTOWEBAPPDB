/*    */ package WEB-INF.classes.ve.com.movilnet;
/*    */ 
/*    */ import org.springframework.cache.CacheManager;
/*    */ import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
/*    */ import org.springframework.context.annotation.Bean;
/*    */ import org.springframework.context.annotation.Configuration;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Configuration
/*    */ public class CacheConfig
/*    */ {
/*    */   @Bean
/*    */   public CacheManager cacheManager() {
/* 21 */     return (CacheManager)new ConcurrentMapCacheManager(new String[] { "apiCache", "apiCacheCP" });
/*    */   }
/*    */ }


/* Location:              C:\Users\aruizc01\Desktop\api-movilnet.war!\WEB-INF\classes\ve\com\movilnet\CacheConfig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */