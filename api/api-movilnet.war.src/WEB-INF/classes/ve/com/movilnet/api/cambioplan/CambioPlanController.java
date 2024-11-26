/*     */ package WEB-INF.classes.ve.com.movilnet.api.cambioplan;
/*     */ 
/*     */ import io.jsonwebtoken.Claims;
/*     */ import io.jsonwebtoken.Jwts;
/*     */ import java.time.LocalDateTime;
/*     */ import java.time.ZoneId;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import java.util.GregorianCalendar;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.TimeZone;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.validation.Valid;
/*     */ import javax.xml.datatype.DatatypeConfigurationException;
/*     */ import javax.xml.datatype.DatatypeFactory;
/*     */ import javax.xml.datatype.XMLGregorianCalendar;
/*     */ import org.apache.log4j.Logger;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.cache.Cache;
/*     */ import org.springframework.cache.CacheManager;
/*     */ import org.springframework.http.HttpStatus;
/*     */ import org.springframework.http.ResponseEntity;
/*     */ import org.springframework.stereotype.Controller;
/*     */ import org.springframework.validation.BindingResult;
/*     */ import org.springframework.web.bind.annotation.CrossOrigin;
/*     */ import org.springframework.web.bind.annotation.GetMapping;
/*     */ import org.springframework.web.bind.annotation.RequestBody;
/*     */ import org.springframework.web.bind.annotation.RequestHeader;
/*     */ import org.springframework.web.bind.annotation.RequestMapping;
/*     */ import ve.com.movilnet.api.cambioplan.CambiaPlanGSMServiceFacade;
/*     */ import ve.com.movilnet.api.cambioplan.CambioRequest;
/*     */ import ve.com.movilnet.api.cambioplan.CambioResponse;
/*     */ import ve.com.movilnet.api.component.ScheduledDataUpdater;
/*     */ import ve.com.movilnet.api.config.ConfProperties;
/*     */ import ve.com.movilnet.api.perfilcliente.PerfilResponse;
/*     */ import ve.com.movilnet.api.service.to.PrepagoUserMethodTO;
/*     */ import ve.com.movilnet.api.service.util.Operaciones;
/*     */ import ve.com.movilnet.api.service.util.TrazaLog;
/*     */ import ve.com.movilnet.apicpprocy.AplicacionTo;
/*     */ import ve.com.movilnet.apicpprocy.CambiarPlanElement;
/*     */ import ve.com.movilnet.apicpprocy.CambiarPlanTo;
/*     */ import ve.com.movilnet.apicpprocy.EntradaTo;
/*     */ import ve.com.movilnet.apicpprocy.List;
/*     */ import ve.com.movilnet.apicpprocy.PlanConsejoComunalTo;
/*     */ import ve.com.movilnet.apicpprocy.PlanTo;
/*     */ import ve.com.movilnet.apicpprocy.SeguridadTo;
/*     */ import ve.com.movilnet.apicpprocy.ServicioTo;
/*     */ import ve.com.movilnet.rtb.procesosnegocio.bp.List;
/*     */ import ve.com.movilnet.rtb.procesosnegocio.bp.ServicioTo;
/*     */ import ve.com.movilnet.rtb.procesosnegocio.to.ServicioTo;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @CrossOrigin(origins = {"*"}, maxAge = 3600L)
/*     */ @Controller
/*     */ @RequestMapping({"/api/cambio----"})
/*     */ public class CambioPlanController
/*     */ {
/*  64 */   TrazaLog log = new TrazaLog();
/*     */   
/*     */   @Autowired
/*     */   ConfProperties prop;
/*     */   
/*     */   @Autowired
/*     */   private HttpServletRequest request;
/*     */   
/*     */   @Autowired
/*     */   private CacheManager cacheManager;
/*  74 */   private static final Logger logger = Logger.getLogger(ve.com.movilnet.api.cambioplan.CambioPlanController.class);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ResponseEntity<CambioResponse> ejecutar(@Valid @RequestBody CambioRequest cambioRequest, @RequestHeader("Authorization") String bearerToken, BindingResult bindingResult) {
/*  80 */     CambioResponse response = new CambioResponse();
/*     */     
/*  82 */     String token = bearerToken.substring(7);
/*     */     
/*  84 */     long tx = System.currentTimeMillis();
/*  85 */     logger.debug(tx + "|CambioPlanController.ejecutar  |" + cambioRequest.getUsername());
/*     */ 
/*     */     
/*  88 */     String usernameToken = getUserNameFromJwtToken(token);
/*     */     
/*  90 */     ConcurrentHashMap<String, String> userSessions = ScheduledDataUpdater.getuserSessions();
/*     */ 
/*     */     
/*  93 */     String username = getUserNameFromJwtToken(token);
/*  94 */     String userIp = this.request.getRemoteAddr();
/*  95 */     if (userSessions.containsKey(usernameToken) && !((String)userSessions.get(usernameToken)).equals(userIp)) {
/*  96 */       response.setCodigo("RA2099");
/*  97 */       return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
/*     */     } 
/*  99 */     Operaciones operaciones = new Operaciones();
/*     */ 
/*     */     
/* 102 */     Cache cache = this.cacheManager.getCache("apiCache");
/* 103 */     ResponseEntity responseEntity = (cache != null) ? (ResponseEntity)cache.get(cambioRequest.getUsername()).get() : null;
/* 104 */     logger.debug(tx + "|CambioPlanController.ejecutar numero |" + cambioRequest.getUsername());
/* 105 */     PerfilResponse perfilResponse = (responseEntity != null) ? (PerfilResponse)responseEntity.getBody() : null;
/*     */     
/* 107 */     logger.debug(tx + "|CambioPlanController.cliente perfilResponse |" + perfilResponse);
/*     */ 
/*     */ 
/*     */     
/* 111 */     if (bindingResult.hasErrors()) {
/* 112 */       return new ResponseEntity(HttpStatus.BAD_REQUEST);
/*     */     }
/*     */     
/* 115 */     PrepagoUserMethodTO objRespTransaccion = null;
/* 116 */     String status = "0";
/*     */     
/* 118 */     PlanTo planTO = new PlanTo();
/*     */     
/* 120 */     String host = this.request.getRemoteAddr();
/*     */ 
/*     */     
/* 123 */     String transaccionId = cambioRequest.getOrigin() + "-" + this.prop.getEntradaCpidUsuario() + "-" + host + "-" + cambioRequest.getUsername() + "-" + tx;
/*     */ 
/*     */     
/* 126 */     response.setTransaccionId(transaccionId);
/* 127 */     String origen = "CambioPlanController";
/* 128 */     String tipoTransaccion = "WS";
/* 129 */     String codigo = "AR-XXX";
/* 130 */     String aplicacion = "Api-Autogestion";
/* 131 */     Map<String, Object> parametersIn = new HashMap<>();
/* 132 */     parametersIn.put("In:", cambioRequest);
/* 133 */     logger.debug(tx + "|ControllerCambioPlan - " + cambioRequest);
/*     */     
/* 135 */     CambiarPlanElement elementos = new CambiarPlanElement();
/*     */     
/* 137 */     CambiarPlanTo elementosCP = new CambiarPlanTo();
/*     */ 
/*     */     
/* 140 */     EntradaTo entrada = new EntradaTo();
/*     */ 
/*     */ 
/*     */     
/* 144 */     AplicacionTo apli = new AplicacionTo();
/*     */     
/* 146 */     apli.setNombre(this.prop.getAplicacionCpnombre());
/* 147 */     apli.setTipoAplicacion(this.prop.getAplicacionCptipoAplicacion());
/* 148 */     apli.setCodigo(this.prop.getAplicacionCpcodigo());
/*     */     
/* 150 */     entrada.setAplicacion(apli);
/*     */     
/* 152 */     SeguridadTo seguridad = new SeguridadTo();
/* 153 */     seguridad.setUsuario(this.prop.getSeguridadCpusuario());
/* 154 */     seguridad.setClave(this.prop.getSeguridadCpclave());
/*     */     
/* 156 */     entrada.setSeguridad(seguridad);
/*     */     
/* 158 */     entrada.setProveedor(this.prop.getEntradaCpproveedor());
/* 159 */     logger.debug(tx + "|" + cambioRequest.getUsername() + "- numero para el nodo " + operaciones.obtenerNodo(operaciones.numeroMascara(String.valueOf(cambioRequest.getUsername()))));
/* 160 */     entrada.setNodo(operaciones.obtenerNodo(operaciones.numeroMascara(String.valueOf(cambioRequest.getUsername()))));
/* 161 */     entrada.setTecnologia(this.prop.getEntradaCptecnologia());
/* 162 */     entrada.setIdUsuario(this.prop.getEntradaCpidUsuario());
/* 163 */     elementosCP.setEntrada(entrada);
/*     */ 
/*     */     
/* 166 */     PlanConsejoComunalTo pccomunal = new PlanConsejoComunalTo();
/* 167 */     pccomunal.setNombreConsejo(cambioRequest.getNombreConsejo());
/* 168 */     pccomunal.setCargoConsejo(cambioRequest.getCargoConsejo());
/* 169 */     pccomunal.setCodigoConsejo4(cambioRequest.getCodigoConsejo4());
/* 170 */     pccomunal.setCodigoConsejo2(cambioRequest.getCodigoConsejo2());
/* 171 */     pccomunal.setCodigoConsejo3(cambioRequest.getCodigoConsejo3());
/* 172 */     pccomunal.setCodigoConsejo1(cambioRequest.getCodigoConsejo1());
/* 173 */     elementosCP.setPlanConsejoComunal(pccomunal);
/*     */ 
/*     */     
/* 176 */     PlanTo plannew = new PlanTo();
/*     */     
/* 178 */     GregorianCalendar ahora = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
/* 179 */     XMLGregorianCalendar fecha = null;
/* 180 */     XMLGregorianCalendar xmlGregorianCalendar = null;
/*     */     try {
/* 182 */       fecha = DatatypeFactory.newInstance().newXMLGregorianCalendar(ahora);
/* 183 */       LocalDateTime now = LocalDateTime.now();
/* 184 */       GregorianCalendar gregorianCalendar = GregorianCalendar.from(now.atZone(ZoneId.systemDefault()));
/* 185 */       xmlGregorianCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(gregorianCalendar);
/*     */     
/*     */     }
/* 188 */     catch (DatatypeConfigurationException ex) {
/* 189 */       logger.warn(tx + "|DatatypeConfigurationException nok " + ex);
/*     */     } 
/*     */     
/* 192 */     if (perfilResponse != null && perfilResponse.getResultado() != null && perfilResponse.getResultado().getCliente() != null) {
/*     */       
/* 194 */       plannew.setFechaActivacion(xmlGregorianCalendar);
/* 195 */       plannew.setIdPlan(cambioRequest.getIdPlan());
/* 196 */       plannew.setDescripcion(cambioRequest.getDescripcion());
/* 197 */       plannew.setSufijoNombrePlan(cambioRequest.getSufijoNombrePlan());
/* 198 */       plannew.setIdTipoPlan(cambioRequest.getIdTipoPlan());
/* 199 */       plannew.setUnidadPlan(cambioRequest.getUnidadPlan());
/* 200 */       plannew.setCargoPeriodicoAsociado(cambioRequest.getCargoPeriodicoAsociado());
/* 201 */       elementosCP.setPlanNuevo(plannew);
/*     */ 
/*     */       
/* 204 */       ServicioTo servicios = new ServicioTo();
/* 205 */       servicios = null;
/*     */ 
/*     */ 
/*     */       
/* 209 */       logger.debug(tx + "|ControllerCambioPlan cambioRequest.getPlanActual() para sacr servicios su plan - " + cambioRequest.getPlanActual());
/* 210 */       List serviciosAfiliados = perfilResponse.getResultado().getServicios();
/* 211 */       logger.debug(tx + "|ControllerCambioPlan cambioRequest.getPlanActual() para sacr servicios - " + serviciosAfiliados);
/* 212 */       List<ServicioTo> cpArray = new ArrayList<>();
/*     */ 
/*     */       
/* 215 */       List serviciosAfiliadosBT = new List();
/* 216 */       for (int i = 0; i < serviciosAfiliados.getItem().size(); i++) {
/* 217 */         ServicioTo servicioTo = serviciosAfiliados.getItem().get(i);
/* 218 */         logger.debug("servicioTo getDescripcion " + servicioTo.getDescripcion());
/*     */         
/* 220 */         logger.debug("servicioTo convertirAServicioBT(servicioTo) " + convertirAServicioBT(servicioTo));
/* 221 */         cpArray.add(convertirAServicioBT(servicioTo));
/* 222 */         ServicioTo servicioTo1 = convertirAServicioBT(servicioTo);
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 227 */       logger.debug("servicioTo listo  " + cpArray.size());
/* 228 */       logger.debug("servicioTo cpArray  " + cpArray);
/*     */       
/* 230 */       ServicioTo bTo = new ServicioTo();
/* 231 */       bTo.setFechaActivacion(fecha);
/* 232 */       bTo.setMinimoLineasAfiliadas(0);
/* 233 */       bTo.setIndicadorProvisioning(status);
/* 234 */       bTo.setFechaInicioServicio(fecha);
/* 235 */       bTo.setIndicadorCargoMensual(status);
/* 236 */       bTo.setDescripcion(status);
/* 237 */       bTo.setMaximoLineasAfiliadas(0);
/* 238 */       bTo.setIdServicio(status);
/* 239 */       bTo.setFechaEstatus(fecha);
/* 240 */       bTo.setMontoServicio(Double.valueOf(10.0D));
/* 241 */       bTo.setTipoServicio(status);
/* 242 */       bTo.setFechaFinServicio(fecha);
/* 243 */       bTo.setNombreCargoMensual(status);
/* 244 */       logger.debug("  serviciosAfiliadosBT().bTo " + bTo);
/*     */ 
/*     */       
/* 247 */       ServicioTo di = new ServicioTo();
/* 248 */       List<ServicioTo> datos = new ArrayList<>();
/*     */       
/* 250 */       di.setFechaActivacion(new Date());
/* 251 */       di.setMinimoLineasAfiliadas(0);
/* 252 */       di.setIndicadorProvisioning(status);
/* 253 */       di.setFechaInicioServicio(new Date());
/* 254 */       di.setIndicadorCargoMensual(status);
/* 255 */       di.setDescripcion(status);
/* 256 */       di.setMaximoLineasAfiliadas(0);
/* 257 */       di.setIdServicio(status);
/* 258 */       di.setFechaEstatus(new Date());
/* 259 */       di.setMontoServicio(Double.valueOf(10.0D));
/* 260 */       di.setTipoServicio(status);
/* 261 */       di.setFechaFinServicio(new Date());
/* 262 */       di.setNombreCargoMensual(status);
/*     */ 
/*     */       
/* 265 */       logger.debug("  serviciosAfiliadosBT().bTo  add " + bTo);
/* 266 */       datos.add(di);
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 271 */       elementosCP.setServicios(serviciosAfiliadosBT);
/* 272 */       logger.debug("servicioTo getServicios  " + elementosCP.getServicios());
/*     */ 
/*     */       
/* 275 */       elementosCP.setNumeroCelular(Long.valueOf(Long.parseLong(formatearLinea(String.valueOf(cambioRequest.getUsername())))));
/* 276 */       logger.debug(tx + "|ControllerCambioPlan cambioRequest.getPlanActual() - " + cambioRequest.getPlanActual());
/* 277 */       elementosCP.setPlanActual(cambioRequest.getPlanActual());
/* 278 */       elementosCP.setCedulaCliente(cambioRequest.getCedulaCliente());
/* 279 */       elementosCP.setImei(cambioRequest.getImei());
/*     */ 
/*     */       
/* 282 */       logger.debug(tx + "|cliente.username.cambioRequest.getNumeroCelular   " + cambioRequest.getUsername());
/* 283 */       logger.debug(tx + "|cliente.NumeroCelular   " + Long.parseLong(formatearLinea(String.valueOf(cambioRequest.getUsername()))));
/*     */       
/* 285 */       if (cambioRequest.getUsername().equals(usernameToken)) {
/*     */         
/* 287 */         elementos.setCambiarPlan(elementosCP);
/* 288 */         elementos.setTransaccionId(cambioRequest.getTransaccionId());
/* 289 */         logger.debug(tx + "|cliente.getTransaccionId" + cambioRequest.getTransaccionId());
/*     */         
/* 291 */         logger.debug(tx + "|CambioPlanController requestSD  entrada.getTecnologia() : " + entrada.getTecnologia());
/* 292 */         logger.debug(tx + "|CambioPlanController requestSD  getProveedor: " + entrada.getProveedor());
/* 293 */         logger.debug(tx + "|CambioPlanController requestSD  getNodo: " + entrada.getNodo());
/* 294 */         logger.debug(tx + "|CambioPlanController requestSD  getIdUsuario: " + entrada.getIdUsuario());
/*     */         
/* 296 */         logger.debug(tx + "|CambioPlanController requestSD  .getAplicacion().getCodigo() : " + entrada.getAplicacion().getCodigo());
/* 297 */         logger.debug(tx + "|CambioPlanController requestSD  .getAplicacion().getNombre(): " + entrada.getAplicacion().getNombre());
/* 298 */         logger.debug(tx + "|CambioPlanController requestSD  .getAplicacion().getTipoAplicacion(): " + entrada.getAplicacion().getTipoAplicacion());
/*     */         
/* 300 */         logger.debug(tx + "|CambioPlanController requestSD  getSeguridad().getUsuario(): " + entrada.getSeguridad().getUsuario());
/*     */         
/* 302 */         if (this.prop.getState_Cp().equals("true")) {
/*     */           
/*     */           try {
/* 305 */             logger.debug(tx + "|CambioPlanController objRespTransaccion| " + objRespTransaccion);
/* 306 */             objRespTransaccion = CambiaPlanGSMServiceFacade.ejecutarCambioPlan(tx + "", formatearLinea(String.valueOf(cambioRequest.getUsername())), elementos, this.prop.getCambiarPlanCp());
/* 307 */             logger.info(tx + "|CambioPlanController_objRespTransaccion|" + operaciones.tiempoDeEjecucion(tx, "RA2006-1"));
/*     */           }
/* 309 */           catch (Exception e) {
/*     */             
/* 311 */             response.setCodigo("RA4010-1");
/* 312 */             logger.error(tx + "|CambioPlanController: ---Exception CambiaPlanGSMServiceFacade.ejecutarCambioPlan " + e.getMessage(), e);
/*     */           } 
/*     */         } else {
/*     */           
/* 316 */           logger.warn(transaccionId + "|CambioPlanController.objRespTransaccion_no_disponible|RA4010-3");
/*     */         } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 326 */         if (objRespTransaccion != null) {
/*     */           
/* 328 */           if (objRespTransaccion.getStatus() != null) {
/*     */             
/* 330 */             status = objRespTransaccion.getStatus();
/* 331 */             logger.info(tx + "|CambioPlanController objRespTransaccion status " + status);
/*     */           } else {
/*     */             
/* 334 */             logger.warn(tx + "|CambioPlanController objRespTransaccion status es null debe dar un error ");
/*     */           } 
/* 336 */           if (objRespTransaccion.getStatus().equalsIgnoreCase("1") && objRespTransaccion
/* 337 */             .getErrorCode().equalsIgnoreCase("AJ001")) {
/*     */             
/* 339 */             logger.info(tx + "|CambioPlanController objRespTransaccion " + objRespTransaccion);
/* 340 */             logger.info(tx + "|CambioPlanController objRespTransaccion getResultData" + objRespTransaccion.getResultData());
/*     */             
/* 342 */             planTO = (PlanTo)objRespTransaccion.getResultData();
/* 343 */             logger.debug(tx + "| luego CambioPlanController getResultData.planTO " + planTO);
/* 344 */             Map<String, Object> parametersOut = new HashMap<>();
/* 345 */             parametersOut.put("Out:", cambioRequest);
/* 346 */             long time2 = System.currentTimeMillis();
/* 347 */             long tiempoDeEjecucion = time2 - tx;
/* 348 */             this.log.escriTrazaInf(codigo, transaccionId, tipoTransaccion, origen, codigo, aplicacion, "CambioPlanController.ejecutarCambioPlan", parametersIn, parametersOut, tiempoDeEjecucion, "RA2006");
/*     */             
/* 350 */             response.setCodigo("RA2006");
/* 351 */             response.setSubCodigo(objRespTransaccion.getStatus());
/*     */           } else {
/*     */             
/* 354 */             response.setCodigo("RA5022");
/* 355 */             response.setSubCodigo(objRespTransaccion.getStatus());
/* 356 */             logger.error(tx + "|Problemas al Intentar cambiar el Plan: " + objRespTransaccion.getErrorMessage());
/*     */           }
/*     */         
/*     */         } else {
/*     */           
/* 361 */           response.setCodigo("RA5023-3");
/* 362 */           logger.error(tx + "|Problemas al Intentar cambiar el Plan:objRespTransaccion " + objRespTransaccion);
/*     */         } 
/*     */       } else {
/*     */         
/* 366 */         logger.warn(tx + "|no cliente.usernameToken iguales nok RA5023-1 " + usernameToken);
/* 367 */         response.setCodigo("RA5023-1");
/*     */       
/*     */       }
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 374 */       response.setCodigo("RA5023-2");
/* 375 */       logger.debug(tx + "|Problemas al Intentar cambiar el Plan datos del cliente no están en el caché no se actualiza RA5023-2|" + usernameToken);
/*     */     } 
/*     */ 
/*     */     
/* 379 */     logger.info(tx + "|luego_de_cp " + planTO);
/* 380 */     response.setPlanTO(planTO);
/* 381 */     return ResponseEntity.ok(response);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @GetMapping({"/cliente"})
/*     */   public String handleGetcliente() {
/* 388 */     return "redirect:/index.html";
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private String formatearLinea(String numero) {
/* 394 */     String numForma = null;
/* 395 */     if (numero.length() >= 10) {
/* 396 */       if (numero.length() == 11) {
/* 397 */         numero = numero.substring(1, numero.length());
/*     */       }
/*     */       
/* 400 */       if (numero.startsWith("158") && numero.length() == 7) {
/* 401 */         numForma = "416" + numero.substring(3, numero.length());
/* 402 */       } else if (numero.startsWith("199") && numero.length() == 7) {
/* 403 */         numForma = "426" + numero.substring(3, numero.length());
/*     */       } else {
/* 405 */         numForma = numero;
/*     */       } 
/*     */     } 
/* 408 */     return numForma;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ServicioTo convertirAServicioBT(ServicioTo serviciosAfiliados) {
/* 414 */     logger.debug("|ControllerCambioPlan cambioRequest.getPlanActual() - convertirAServicioBT ");
/* 415 */     ServicioTo bTo = new ServicioTo();
/* 416 */     bTo.setDescripcion(serviciosAfiliados.getDescripcion());
/*     */ 
/*     */     
/* 419 */     return bTo;
/*     */   }
/*     */   
/*     */   public String getUserNameFromJwtToken(String token) {
/* 423 */     String usernamesplit = null;
/* 424 */     if (!token.equals("")) {
/*     */       
/* 426 */       usernamesplit = ((Claims)Jwts.parser().setSigningKey(this.prop.getJwtSecret()).parseClaimsJws(token).getBody()).getSubject();
/*     */       
/* 428 */       if (!usernamesplit.equals("")) {
/* 429 */         String[] arrayUsername = usernamesplit.split(";");
/*     */         
/* 431 */         usernamesplit = arrayUsername[0];
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 436 */     return usernamesplit;
/*     */   }
/*     */ }


/* Location:              C:\Users\aruizc01\Desktop\api-movilnet.war!\WEB-INF\classes\ve\com\movilnet\api\cambioplan\CambioPlanController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */