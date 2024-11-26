/*     */ package WEB-INF.classes.ve.com.movilnet.controller;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.validation.Valid;
/*     */ import org.apache.log4j.Logger;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.cache.CacheManager;
/*     */ import org.springframework.cache.annotation.CacheEvict;
/*     */ import org.springframework.cache.annotation.Cacheable;
/*     */ import org.springframework.security.access.prepost.PreAuthorize;
/*     */ import org.springframework.web.bind.annotation.CrossOrigin;
/*     */ import org.springframework.web.bind.annotation.GetMapping;
/*     */ import org.springframework.web.bind.annotation.PostMapping;
/*     */ import org.springframework.web.bind.annotation.RequestBody;
/*     */ import org.springframework.web.bind.annotation.RequestMapping;
/*     */ import org.springframework.web.bind.annotation.RestController;
/*     */ import ve.com.movilnet.api.cambioplan.ListaPlanRequest;
/*     */ import ve.com.movilnet.api.cambioplan.ListaServicioRequest;
/*     */ import ve.com.movilnet.api.component.ScheduledDataUpdater;
/*     */ import ve.com.movilnet.api.repository.DataRepository;
/*     */ import ve.com.movilnet.api.repository.ViewPlane;
/*     */ import ve.com.movilnet.api.repository.ViewServicios;
/*     */ import ve.com.movilnet.api.response.Beneficios;
/*     */ import ve.com.movilnet.api.response.BeneficiosServicio;
/*     */ import ve.com.movilnet.api.response.PlanOfertaResponse;
/*     */ import ve.com.movilnet.api.response.Planes;
/*     */ import ve.com.movilnet.api.response.ServicioOfertaResponse;
/*     */ import ve.com.movilnet.api.response.Servicios;
/*     */ 
/*     */ @CrossOrigin(origins = {"*"}, maxAge = 3600L)
/*     */ @RestController
/*     */ @RequestMapping({"/api/"})
/*     */ public class Controller
/*     */ {
/*  37 */   static final Logger logger = Logger.getLogger(ve.com.movilnet.controller.Controller.class);
/*     */ 
/*     */   
/*     */   @Autowired
/*     */   DataRepository dataRepository;
/*     */   
/*     */   @Autowired
/*     */   private HttpServletRequest request;
/*     */   
/*     */   @Autowired
/*     */   private CacheManager cacheManager;
/*     */ 
/*     */   
/*     */   @GetMapping({"/plan"})
/*     */   public String handleGetplan() {
/*  52 */     return "redirect:/index.html";
/*     */   }
/*     */ 
/*     */   
/*     */   @PostMapping({"/plan"})
/*     */   @PreAuthorize("hasRole('ROLE_USER')")
/*     */   public List<PlanOfertaResponse> showPlanesCosto(@Valid @RequestBody ListaPlanRequest listaPlanRequest) {
/*  59 */     List<PlanOfertaResponse> lista = new ArrayList<>();
/*  60 */     int count = 0;
/*     */ 
/*     */     
/*  63 */     List<ViewPlane> listaPlanes = ScheduledDataUpdater.getViewPlanes();
/*     */ 
/*     */     
/*  66 */     for (ViewPlane objeto : listaPlanes) {
/*  67 */       Planes planjsom = new Planes();
/*  68 */       planjsom.setId(objeto.getPLAN_OFFERING_CODE());
/*  69 */       planjsom.setName(objeto.getCOMMERCIAL_LAUNCH_NAME());
/*  70 */       planjsom.setCosto(objeto.getCOSTO());
/*  71 */       planjsom.setCos_plan_name(objeto.getCOS_PLAN_NAME());
/*  72 */       planjsom.setTipo_plan(objeto.getTIPO_PLAN());
/*  73 */       planjsom.setActive(objeto.getACTIVE_CAMBIO_PLAN());
/*  74 */       planjsom.setSUFIJO_NOMBRE_PLAN(objeto.getSUFIJO_NOMBRE_PLAN());
/*  75 */       planjsom.setID_TIPO_PLAN(objeto.getID_TIPO_PLAN());
/*  76 */       planjsom.setUNIDAD_PLAN(objeto.getUNIDAD_PLAN());
/*  77 */       Beneficios beneficios = new Beneficios();
/*  78 */       beneficios.setMegas(objeto.getACCUMULATED_MB() + "");
/*  79 */       beneficios.setLdi("-");
/*  80 */       beneficios.setLibres_movilnet_cantv("-");
/*  81 */       beneficios.setLibres_otras_operadoras("-");
/*  82 */       beneficios.setLibres_todas_operadoras(objeto.getACCUMULATED_USAGE() + "");
/*  83 */       beneficios.setSms(objeto.getACCUMULATED_SMS() + "");
/*  84 */       beneficios.setSms_internacional("-");
/*  85 */       planjsom.setBeneficios(beneficios);
/*  86 */       PlanOfertaResponse planOfertaResponse = new PlanOfertaResponse();
/*  87 */       planOfertaResponse.setPlanes(planjsom);
/*  88 */       planOfertaResponse.setId(Long.valueOf(Long.parseLong(String.valueOf(count))));
/*  89 */       lista.add(planOfertaResponse);
/*  90 */       count++;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/*  95 */     return lista;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @GetMapping({"/servicio"})
/*     */   public String handleGetservicio() {
/* 103 */     return "redirect:/index.html";
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @PostMapping({"/servicio"})
/*     */   @PreAuthorize("hasRole('ROLE_USER')")
/*     */   public List<ServicioOfertaResponse> showServiciosCosto(@Valid @RequestBody ListaServicioRequest listaPlanRequest) {
/* 111 */     List<ServicioOfertaResponse> lista = new ArrayList<>();
/* 112 */     int count = 0;
/* 113 */     List<ViewServicios> listaServicios = new ArrayList<>();
/* 114 */     listaServicios = ScheduledDataUpdater.getViewServices();
/* 115 */     logger.debug("lista servicios ...." + listaServicios);
/* 116 */     for (ViewServicios objeto : listaServicios) {
/* 117 */       Servicios serviciojson = new Servicios();
/* 118 */       serviciojson.setCod_serviceServ(objeto.getCOD_SERVICE());
/* 119 */       serviciojson.setDesc_servicioServ(objeto.getDESC_SERVICIO());
/* 120 */       serviciojson.setDescripcionServ(objeto.getDESCRIPCION());
/* 121 */       serviciojson.setAlco_nameServ(objeto.getALCO_NAME());
/* 122 */       serviciojson.setCostoServ(objeto.getCOSTO());
/* 123 */       serviciojson.setActiveServ(objeto.getACTIVE());
/* 124 */       BeneficiosServicio beneficiosServ = new BeneficiosServicio();
/* 125 */       beneficiosServ.setMegasServicios("200 Mb");
/* 126 */       serviciojson.setBeneficiosServicio(beneficiosServ);
/*     */       
/* 128 */       ServicioOfertaResponse servicioOferta = new ServicioOfertaResponse();
/* 129 */       servicioOferta.setId(Long.valueOf(String.valueOf(count)));
/* 130 */       servicioOferta.setServicios(serviciojson);
/* 131 */       lista.add(servicioOferta);
/*     */     } 
/*     */     
/* 134 */     return lista;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @GetMapping({"/changeOffer"})
/*     */   public String handleGetchangeOffer() {
/* 141 */     return "redirect:/index.html";
/*     */   }
/*     */ 
/*     */   
/*     */   @PostMapping({"/changeOffer"})
/*     */   @PreAuthorize("hasRole('ROLE_USER')")
/*     */   @Cacheable(value = {"apiCacheCP"}, key = "#listaPlanRequest.plan")
/*     */   public List<PlanOfertaResponse> showPlanesCP(@Valid @RequestBody ListaPlanRequest listaPlanRequest) {
/* 149 */     String host = this.request.getRemoteAddr();
/* 150 */     long tx = System.currentTimeMillis();
/*     */     
/* 152 */     String transaccionId = listaPlanRequest.getTransaccionId() + "--" + host + "-" + listaPlanRequest.getNumero() + "-" + tx;
/*     */     
/* 154 */     logger.info(transaccionId + " Controller.showPlanesCP:  " + listaPlanRequest.getPlan());
/*     */     
/* 156 */     List<PlanOfertaResponse> lista = new ArrayList<>();
/* 157 */     int count = 0;
/* 158 */     List<ViewPlane> listaPlanes = this.dataRepository.buscarTodosCp(listaPlanRequest.getPlan());
/* 159 */     logger.debug("Controller.showPlanesCP lista plan ...." + listaPlanes);
/* 160 */     for (ViewPlane objeto : listaPlanes) {
/* 161 */       Planes planjsom = new Planes();
/* 162 */       planjsom.setId(objeto.getPLAN_OFFERING_CODE());
/* 163 */       planjsom.setName(objeto.getCOMMERCIAL_LAUNCH_NAME());
/* 164 */       planjsom.setCosto(objeto.getCOSTO());
/* 165 */       planjsom.setCos_plan_name(objeto.getCOS_PLAN_NAME());
/* 166 */       planjsom.setTipo_plan(objeto.getTIPO_PLAN());
/* 167 */       planjsom.setActive(objeto.getACTIVE_CAMBIO_PLAN());
/* 168 */       planjsom.setSUFIJO_NOMBRE_PLAN(objeto.getSUFIJO_NOMBRE_PLAN());
/* 169 */       planjsom.setID_TIPO_PLAN(objeto.getID_TIPO_PLAN());
/* 170 */       planjsom.setUNIDAD_PLAN(objeto.getUNIDAD_PLAN());
/* 171 */       Beneficios beneficios = new Beneficios();
/* 172 */       beneficios.setMegas(objeto.getACCUMULATED_MB() + "");
/* 173 */       beneficios.setLdi("-");
/* 174 */       beneficios.setLibres_movilnet_cantv("-");
/* 175 */       beneficios.setLibres_otras_operadoras("-");
/* 176 */       beneficios.setLibres_todas_operadoras(objeto.getACCUMULATED_USAGE() + "");
/* 177 */       beneficios.setSms(objeto.getACCUMULATED_SMS() + "");
/* 178 */       beneficios.setSms_internacional("-");
/* 179 */       planjsom.setBeneficios(beneficios);
/* 180 */       PlanOfertaResponse planOfertaResponse = new PlanOfertaResponse();
/* 181 */       planOfertaResponse.setPlanes(planjsom);
/* 182 */       planOfertaResponse.setId(Long.valueOf(Long.parseLong(String.valueOf(count))));
/* 183 */       lista.add(planOfertaResponse);
/* 184 */       count++;
/*     */     } 
/* 186 */     logger.debug("Controller.showPlanesCP.lista add ...." + lista);
/*     */     
/* 188 */     return lista;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @GetMapping({"/removerChangeOffer"})
/*     */   public String handleGetremoverChangeOffer() {
/* 195 */     return "redirect:/index.html";
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @PostMapping({"/removerChangeOffer"})
/*     */   @PreAuthorize("hasRole('ROLE_USER')")
/*     */   @CacheEvict(value = {"apiCacheCP"}, key = "#listaPlanRequest.plan")
/*     */   public List<PlanOfertaResponse> removerPlanesCP(@Valid @RequestBody ListaPlanRequest listaPlanRequest) {
/* 204 */     String host = this.request.getRemoteAddr();
/* 205 */     logger.info("Controller.removerPlanesCP del plan:  " + listaPlanRequest.getPlan());
/* 206 */     List<PlanOfertaResponse> lista = new ArrayList<>();
/* 207 */     int count = 0;
/*     */     
/* 209 */     logger.warn(listaPlanRequest.getTransaccionId() + "|Controller.removerPlanesCP|" + listaPlanRequest.getPlan() + "|" + host + "|RA5039");
/*     */     
/* 211 */     return lista;
/*     */   }
/*     */ }


/* Location:              C:\Users\aruizc01\Desktop\api-movilnet.war!\WEB-INF\classes\ve\com\movilnet\controller\Controller.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */