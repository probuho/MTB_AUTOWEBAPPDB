/*     */ package WEB-INF.classes.ve.com.movilnet.api.component;
/*     */ 
/*     */ import java.util.List;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ import javax.annotation.PostConstruct;
/*     */ import org.apache.log4j.Logger;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.scheduling.annotation.Scheduled;
/*     */ import org.springframework.security.core.userdetails.UserDetails;
/*     */ import org.springframework.stereotype.Component;
/*     */ import ve.com.movilnet.api.repository.DataRepository;
/*     */ import ve.com.movilnet.api.repository.ViewPlane;
/*     */ import ve.com.movilnet.api.repository.ViewServicios;
/*     */ import ve.com.movilnet.api.response.RetrieveLinesResponseGSMTO;
/*     */ import ve.com.movilnet.gdis.cia.ws.to.commons.CityTO;
/*     */ import ve.com.movilnet.gdis.cia.ws.to.commons.MunicipalitieTO;
/*     */ import ve.com.movilnet.gdis.cia.ws.to.commons.ParisheTO;
/*     */ import ve.com.movilnet.gdis.cia.ws.to.commons.SectorTO;
/*     */ import ve.com.movilnet.gdis.cia.ws.to.commons.StateTO;
/*     */ 
/*     */ @Component
/*     */ public class ScheduledDataUpdater {
/*  23 */   static final Logger logger = Logger.getLogger(ve.com.movilnet.api.component.ScheduledDataUpdater.class);
/*     */   private static List<ViewPlane> viewPlanes;
/*     */   private static List<ViewPlane> viewPlanesCP;
/*     */   private static List<ViewServicios> viewService;
/*     */   private static List<StateTO> getStates;
/*     */   private static List<MunicipalitieTO> getMunicipalitieTO;
/*     */   private static List<ParisheTO> getParroquias;
/*     */   private static List<SectorTO> getSectores;
/*  31 */   private static ConcurrentHashMap<String, String> userSessions = new ConcurrentHashMap<>();
/*  32 */   private static ConcurrentHashMap<String, UserDetails> userDetailsCache = new ConcurrentHashMap<>();
/*  33 */   private static ConcurrentHashMap<String, RetrieveLinesResponseGSMTO> userRetrieveLinesCache = new ConcurrentHashMap<>();
/*     */ 
/*     */ 
/*     */   
/*     */   private static List<CityTO> getCities;
/*     */ 
/*     */   
/*     */   @Autowired
/*     */   private DataRepository dataRepository;
/*     */ 
/*     */ 
/*     */   
/*     */   @PostConstruct
/*     */   @Scheduled(cron = "0 0 5 * * ?")
/*     */   public void updateData() {
/*  48 */     long t1 = System.currentTimeMillis();
/*  49 */     long t2 = 0L;
/*  50 */     long tiempoEjecucion = 0L;
/*  51 */     logger.info("ScheduledDataUpdater.updateData Se esta ejecutando ");
/*  52 */     viewPlanes = this.dataRepository.buscarTodos("valor");
/*  53 */     viewService = this.dataRepository.buscarTodosServ("valor");
/*  54 */     t2 = System.currentTimeMillis();
/*  55 */     tiempoEjecucion = t2 - t1;
/*     */     
/*  57 */     getStates = this.dataRepository.getStates();
/*  58 */     t2 = System.currentTimeMillis();
/*  59 */     tiempoEjecucion = t2 - t1;
/*  60 */     logger.info("ScheduledDataUpdater.updateData LISTO getStates---------|" + tiempoEjecucion);
/*     */     
/*  62 */     getMunicipalitieTO = this.dataRepository.getMunicipios();
/*  63 */     t2 = System.currentTimeMillis();
/*  64 */     tiempoEjecucion = t2 - t1;
/*  65 */     logger.info("ScheduledDataUpdater.updateData LISTO getMunicipios---------|" + tiempoEjecucion);
/*     */     
/*  67 */     getParroquias = this.dataRepository.getParroquias();
/*  68 */     t2 = System.currentTimeMillis();
/*  69 */     tiempoEjecucion = t2 - t1;
/*  70 */     logger.info("ScheduledDataUpdater.updateData LISTO getParroquias---------|" + tiempoEjecucion);
/*  71 */     getSectores = this.dataRepository.getSectores();
/*  72 */     t2 = System.currentTimeMillis();
/*  73 */     tiempoEjecucion = t2 - t1;
/*  74 */     logger.info("ScheduledDataUpdater.updateData LISTO getSectores---------|" + tiempoEjecucion);
/*  75 */     getCities = this.dataRepository.getCities();
/*  76 */     t2 = System.currentTimeMillis();
/*  77 */     tiempoEjecucion = t2 - t1;
/*  78 */     logger.info("ScheduledDataUpdater.updateData getCities|" + tiempoEjecucion);
/*  79 */     userRetrieveLinesCache.clear();
/*  80 */     t2 = System.currentTimeMillis();
/*  81 */     tiempoEjecucion = t2 - t1;
/*  82 */     logger.debug("ScheduledDataUpdater.updateData :    userRetrieveLinesCache.clear()| " + tiempoEjecucion);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Scheduled(cron = "0 0/9 * * * ?")
/*     */   public void updateDataSession() {
/*  95 */     long t1 = System.currentTimeMillis();
/*     */     
/*  97 */     long elemntos = userSessions.size();
/*  98 */     long t2 = 0L;
/*  99 */     long tiempoEjecucion = 0L;
/* 100 */     userSessions.clear();
/* 101 */     t2 = System.currentTimeMillis();
/* 102 */     tiempoEjecucion = t2 - t1;
/* 103 */     logger.debug("ScheduledDataUpdater.updateDataSession elemntos: " + elemntos + "  userSessions.clear()| " + tiempoEjecucion);
/*     */     
/* 105 */     userDetailsCache.clear();
/* 106 */     t2 = System.currentTimeMillis();
/* 107 */     tiempoEjecucion = t2 - t1;
/* 108 */     logger.debug("ScheduledDataUpdater.updateDataSession :    userDetailsCache.clear()| " + tiempoEjecucion);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static ConcurrentHashMap<String, RetrieveLinesResponseGSMTO> getuserRetrieveLinesCache() {
/* 116 */     return userRetrieveLinesCache;
/*     */   }
/*     */   
/*     */   public static ConcurrentHashMap<String, UserDetails> getuserDetailsCache() {
/* 120 */     return userDetailsCache;
/*     */   }
/*     */   
/*     */   public static ConcurrentHashMap<String, String> getuserSessions() {
/* 124 */     return userSessions;
/*     */   }
/*     */   
/*     */   public static List<ViewPlane> getViewPlanes() {
/* 128 */     return viewPlanes;
/*     */   }
/*     */   
/*     */   public static List<ViewServicios> getViewServices() {
/* 132 */     return viewService;
/*     */   }
/*     */ 
/*     */   
/*     */   public static List<StateTO> getPkgState() {
/* 137 */     return getStates;
/*     */   }
/*     */ 
/*     */   
/*     */   public static List<MunicipalitieTO> getPkgMunicipalitieTO() {
/* 142 */     return getMunicipalitieTO;
/*     */   }
/*     */ 
/*     */   
/*     */   public static List<ParisheTO> getPkgParisheTO() {
/* 147 */     return getParroquias;
/*     */   }
/*     */ 
/*     */   
/*     */   public static List<SectorTO> getPkgtSectores() {
/* 152 */     return getSectores;
/*     */   }
/*     */ 
/*     */   
/*     */   public static List<CityTO> getPkgCities() {
/* 157 */     return getCities;
/*     */   }
/*     */ }


/* Location:              C:\Users\aruizc01\Desktop\api-movilnet.war!\WEB-INF\classes\ve\com\movilnet\api\component\ScheduledDataUpdater.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */