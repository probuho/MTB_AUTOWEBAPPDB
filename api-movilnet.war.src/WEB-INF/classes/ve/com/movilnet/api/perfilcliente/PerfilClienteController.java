/*      */ package WEB-INF.classes.ve.com.movilnet.api.perfilcliente;
/*      */ import java.io.BufferedReader;
/*      */ import java.io.File;
/*      */ import java.io.FileReader;
/*      */ import java.util.ArrayList;
/*      */ import java.util.HashMap;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import java.util.concurrent.ConcurrentHashMap;
/*      */ import javax.validation.Valid;
/*      */ import javax.xml.namespace.QName;
/*      */ import javax.xml.soap.MessageFactory;
/*      */ import javax.xml.soap.SOAPBody;
/*      */ import javax.xml.soap.SOAPBodyElement;
/*      */ import javax.xml.soap.SOAPElement;
/*      */ import javax.xml.soap.SOAPException;
/*      */ import javax.xml.soap.SOAPMessage;
/*      */ import javax.xml.ws.Dispatch;
/*      */ import javax.xml.ws.Service;
/*      */ import org.apache.log4j.Logger;
/*      */ import org.springframework.beans.factory.annotation.Autowired;
/*      */ import org.springframework.cache.Cache;
/*      */ import org.springframework.http.HttpStatus;
/*      */ import org.springframework.http.ResponseEntity;
/*      */ import org.springframework.security.access.prepost.PreAuthorize;
/*      */ import org.springframework.stereotype.Controller;
/*      */ import org.springframework.web.bind.annotation.GetMapping;
/*      */ import org.springframework.web.bind.annotation.PostMapping;
/*      */ import org.springframework.web.bind.annotation.RequestBody;
/*      */ import org.springframework.web.bind.annotation.RequestHeader;
/*      */ import org.springframework.web.bind.annotation.RequestMapping;
/*      */ import ve.com.movilnet.api.component.ScheduledDataUpdater;
/*      */ import ve.com.movilnet.api.perfilcliente.DireccionTO_Name;
/*      */ import ve.com.movilnet.api.perfilcliente.PerfilActualizaClientRequest;
/*      */ import ve.com.movilnet.api.perfilcliente.PerfilActualizaClientResponse;
/*      */ import ve.com.movilnet.api.perfilcliente.PerfilRequest;
/*      */ import ve.com.movilnet.api.perfilcliente.PerfilRequestLinea;
/*      */ import ve.com.movilnet.api.perfilcliente.PerfilResponse;
/*      */ import ve.com.movilnet.api.response.ResultGSMTO;
/*      */ import ve.com.movilnet.api.response.RetrieveLinesResponseGSMTO;
/*      */ import ve.com.movilnet.api.service.ClientProfileValidationDao;
/*      */ import ve.com.movilnet.api.service.EjecutarConsultarSaldoPrepagoServiceFacade;
/*      */ import ve.com.movilnet.api.service.to.PrepagoUserMethodTO;
/*      */ import ve.com.movilnet.api.service.util.Operaciones;
/*      */ import ve.com.movilnet.api.service.util.TrazaLog;
/*      */ import ve.com.movilnet.gdis.cia.ws.to.commons.CityTO;
/*      */ import ve.com.movilnet.gdis.cia.ws.to.commons.MunicipalitieTO;
/*      */ import ve.com.movilnet.gdis.cia.ws.to.commons.ParisheTO;
/*      */ import ve.com.movilnet.gdis.cia.ws.to.commons.SectorTO;
/*      */ import ve.com.movilnet.gdis.cia.ws.to.commons.StateTO;
/*      */ import ve.com.movilnet.rtb.procesosnegocio.apiactualizadatos.ActualizaClienteTo;
/*      */ import ve.com.movilnet.rtb.procesosnegocio.apiactualizadatos.ActualizarDatosClienteElement;
/*      */ import ve.com.movilnet.rtb.procesosnegocio.apiactualizadatos.ActualizarDatosClienteResponseElement;
/*      */ import ve.com.movilnet.rtb.procesosnegocio.apiactualizadatos.AgenteTo;
/*      */ import ve.com.movilnet.rtb.procesosnegocio.apiactualizadatos.AplicacionTo;
/*      */ import ve.com.movilnet.rtb.procesosnegocio.apiactualizadatos.ClienteTo;
/*      */ import ve.com.movilnet.rtb.procesosnegocio.apiactualizadatos.DireccionTo;
/*      */ import ve.com.movilnet.rtb.procesosnegocio.apiactualizadatos.EntradaTo;
/*      */ import ve.com.movilnet.rtb.procesosnegocio.apiactualizadatos.PersonaTo;
/*      */ import ve.com.movilnet.rtb.procesosnegocio.apiactualizadatos.SeguridadTo;
/*      */ import ve.com.movilnet.rtb.procesosnegocio.bp.AplicacionTo;
/*      */ import ve.com.movilnet.rtb.procesosnegocio.bp.BuscarTelefonoRespuestaTo;
/*      */ import ve.com.movilnet.rtb.procesosnegocio.bp.EntradaTo;
/*      */ import ve.com.movilnet.rtb.procesosnegocio.bp.SeguridadTo;
/*      */ import ve.com.movilnet.rtb.procesosnegocio.factory.AplicacionTo;
/*      */ import ve.com.movilnet.rtb.procesosnegocio.factory.EntradaTo;
/*      */ import ve.com.movilnet.rtb.procesosnegocio.factory.SaldoTo;
/*      */ import ve.com.movilnet.rtb.procesosnegocio.factory.SeguridadTo;
/*      */ import ve.com.movilnet.rtb.procesosnegocio.factory.SolicitarSaldoElement;
/*      */ import ve.com.movilnet.rtb.procesosnegocio.factory.SolicitarSaldoResponseElement;
/*      */ 
/*      */ @CrossOrigin(origins = {"*"}, maxAge = 3600L)
/*      */ @Controller
/*      */ @RequestMapping({"/api/perfil"})
/*      */ public class PerfilClienteController {
/*   77 */   Operaciones op = new Operaciones();
/*   78 */   ConcurrentHashMap<String, RetrieveLinesResponseGSMTO> userRetrieveLinesCache = ScheduledDataUpdater.getuserRetrieveLinesCache();
/*      */   
/*   80 */   static final Logger logger = Logger.getLogger(ve.com.movilnet.api.perfilcliente.PerfilClienteController.class);
/*      */   
/*      */   @Autowired
/*      */   ConfProperties prop;
/*      */   @Autowired
/*      */   private CacheManager cacheManager;
/*      */   @Autowired
/*      */   private HttpServletRequest request;
/*   88 */   TrazaLog log = new TrazaLog();
/*      */ 
/*      */ 
/*      */   
/*      */   @GetMapping({"/cliente"})
/*      */   public String handleGetSignin() {
/*   94 */     return "redirect:/index.html";
/*      */   }
/*      */ 
/*      */   
/*      */   @PostMapping({"/cliente"})
/*      */   @PreAuthorize("hasRole('ROLE_USER')")
/*      */   @Cacheable(value = {"apiCache"}, key = "#perfilRequest.username")
/*      */   public ResponseEntity<?> show(@Valid @RequestBody PerfilRequest perfilRequest, @RequestHeader("Authorization") String bearerToken) {
/*  102 */     String token = bearerToken.substring(7);
/*      */ 
/*      */ 
/*      */     
/*  106 */     String username = getUserNameFromJwtToken(token);
/*      */     
/*  108 */     PerfilResponse daResponse = new PerfilResponse();
/*  109 */     boolean sdpValida = false;
/*  110 */     boolean mascaraValida = false;
/*  111 */     long t1 = System.currentTimeMillis();
/*  112 */     String host = this.request.getRemoteAddr();
/*      */     
/*  114 */     String transaccionId = perfilRequest.getOrigin() + "-" + host + "-" + perfilRequest.getUsername() + "-" + t1;
/*      */ 
/*      */     
/*  117 */     long time1 = 0L;
/*  118 */     time1 = System.currentTimeMillis();
/*  119 */     String origen = "PerfilClienteController";
/*  120 */     String tipoTransaccion = "WS";
/*  121 */     String codigo = "AR-XXX";
/*  122 */     String aplicacion = "Api-Autogestion";
/*      */     
/*  124 */     Map<String, Object> parametersIn = new HashMap<>();
/*  125 */     parametersIn.put("In:", perfilRequest);
/*      */     
/*  127 */     this.log.escriTrazaInf(codigo, transaccionId, tipoTransaccion, origen, codigo, aplicacion, "show", parametersIn, null, time1, codigo);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  133 */     if (this.prop.getState_Bt().equals("true") && this.prop.getState_Ss().equals("true")) {
/*      */       
/*  135 */       if (this.op.validarMascara(perfilRequest.getUsername())) {
/*  136 */         mascaraValida = true;
/*      */         
/*      */         try {
/*  139 */           String sdp_aux = valorSDP(time1 + "");
/*  140 */           logger.debug("|" + transaccionId + "|Chek.service antes de sacar la sdp| para: num entrada  " + perfilRequest
/*  141 */               .getUsername() + " " + sdp_aux);
/*      */           
/*  143 */           String suscriptorSDP = getSDP(formatearLinea(perfilRequest.getUsername(), time1 + ""), time1 + "");
/*  144 */           logger.debug("|" + transaccionId + "|Chek.service resultado de sacar la sdp|" + suscriptorSDP + "|suscriptorSDP " + suscriptorSDP);
/*      */ 
/*      */           
/*  147 */           String[] arrayToke_sdp = sdp_aux.split("/");
/*      */           
/*  149 */           for (int i = 0; i < arrayToke_sdp.length; i++) {
/*      */             
/*  151 */             String toke2_sdp = arrayToke_sdp[i];
/*      */             
/*  153 */             String[] Arreglotoke2 = toke2_sdp.split(";");
/*  154 */             for (int k = 0; k < Arreglotoke2.length; 
/*  155 */               k++) {
/*      */               
/*  157 */               String sdp_aux_arreglo = Arreglotoke2[0];
/*  158 */               String status = Arreglotoke2[1];
/*  159 */               if (k == 1 && 
/*  160 */                 suscriptorSDP.equals(sdp_aux_arreglo) && status
/*  161 */                 .equals("true")) {
/*  162 */                 sdpValida = true;
/*      */               }
/*      */             } 
/*      */           } 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  170 */           logger.debug(transaccionId + "|PerfilClienteController.service luego de calcular la SDP_Resultado " + sdpValida + "|");
/*      */         
/*      */         }
/*  173 */         catch (Exception e) {
/*  174 */           daResponse.setCodigo("AJ097-1");
/*  175 */           logger.error(transaccionId + " Para el trado de la sdp: " + e
/*  176 */               .getMessage(), e);
/*      */         } 
/*      */       } 
/*      */       
/*  180 */       if (sdpValida) {
/*  181 */         logger.debug(transaccionId + "|PerfilClienteController.service antes procesarConsulta|");
/*      */         
/*  183 */         daResponse = procesarTransaccionBuscarTelefono(t1, perfilRequest.getUsername(), transaccionId);
/*  184 */         Map<String, Object> parametersOut = new HashMap<>();
/*  185 */         parametersOut.put("Out:", daResponse);
/*  186 */         long time2 = System.currentTimeMillis();
/*  187 */         long tiempoDeEjecucion = time2 - time1;
/*  188 */         this.log.escriTrazaInf(codigo, transaccionId, tipoTransaccion, origen, codigo, aplicacion, "Salida_show", parametersIn, parametersOut, tiempoDeEjecucion, "RA2004-1");
/*      */ 
/*      */ 
/*      */       
/*      */       }
/*  193 */       else if (mascaraValida) {
/*  194 */         logger.debug(transaccionId + "|PerfilClienteController.service sdpValida NO|");
/*      */         
/*  196 */         daResponse.setCodigo("AJ095");
/*      */       }
/*      */       else {
/*      */         
/*  200 */         logger.debug(transaccionId + "|PerfilClienteController.service mascara incorrecta |" + mascaraValida);
/*      */ 
/*      */         
/*  203 */         daResponse.setCodigo("AJ097");
/*      */       }
/*      */     
/*      */     }
/*      */     else {
/*      */       
/*  209 */       logger.warn(transaccionId + "|PerfilClienteController_no_disponible|");
/*  210 */       daResponse.setCodigo("RA4006-1");
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  220 */     return new ResponseEntity(daResponse, HttpStatus.OK);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ResponseEntity<?> showLinea(@Valid @RequestBody PerfilRequestLinea perfilRequestlinea, @RequestHeader("Authorization") String bearerToken) {
/*  227 */     RetrieveLinesResponseGSMTO retrieveLinesResponseGSMTO = new RetrieveLinesResponseGSMTO();
/*  228 */     long t1 = System.currentTimeMillis();
/*  229 */     long t2 = 0L;
/*  230 */     long tiempoEjecucion = 0L;
/*  231 */     String cedula = perfilRequestlinea.getSsn();
/*  232 */     String host = this.request.getRemoteAddr();
/*  233 */     String origen = "PerfilClienteController.cliente-linea";
/*  234 */     String transaccionId = t1 + "-" + cedula;
/*      */     
/*  236 */     String tipoTransaccion = "WS";
/*  237 */     String codigo = "AR-XXX";
/*  238 */     String aplicacion = "Api-Autogestion";
/*      */ 
/*      */     
/*  241 */     String setEndpoint = this.prop.getSubscriber();
/*  242 */     String userIp = this.request.getRemoteAddr();
/*      */     
/*  244 */     String token = bearerToken.substring(7);
/*      */     
/*  246 */     ConcurrentHashMap<String, String> userSessions = ScheduledDataUpdater.getuserSessions();
/*      */ 
/*      */     
/*  249 */     String username = getUserNameFromJwtToken(token);
/*      */ 
/*      */     
/*  252 */     if (userSessions.containsKey(username) && !((String)userSessions.get(username)).equals(userIp)) {
/*  253 */       retrieveLinesResponseGSMTO.setResponseCode("RA2099");
/*  254 */       return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(retrieveLinesResponseGSMTO);
/*      */     } 
/*      */     
/*  257 */     if (this.userRetrieveLinesCache.containsKey(cedula)) {
/*  258 */       retrieveLinesResponseGSMTO = this.userRetrieveLinesCache.get(cedula);
/*  259 */       logger.info("ScheduledDataUpdater. userRetrieveLinesCache ---------|" + cedula);
/*      */     }
/*      */     else {
/*      */       
/*  263 */       t2 = System.currentTimeMillis();
/*  264 */       tiempoEjecucion = t2 - t1;
/*      */ 
/*      */       
/*  267 */       if (this.prop.getState_SUB().equals("true")) {
/*      */         
/*      */         try {
/*  270 */           retrieveLinesResponseGSMTO = subscriberFactoryManual(setEndpoint, transaccionId, perfilRequestlinea.getSsn(), perfilRequestlinea.getSsnType());
/*  271 */           this.userRetrieveLinesCache.put(cedula, retrieveLinesResponseGSMTO);
/*  272 */           logger.info(transaccionId + "|" + origen + "|-------- userRetrieveLinesCache.put |" + tiempoEjecucion);
/*  273 */         } catch (SOAPException ex) {
/*  274 */           logger.warn(origen + "|" + ex);
/*      */         }
/*      */       
/*      */       } else {
/*      */         
/*  279 */         logger.warn(transaccionId + "|PerfilClienteController.userRetrieveLinesCache_no_disponible|RA4014-1");
/*  280 */         retrieveLinesResponseGSMTO.setResponseCode("RA4014-1");
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  288 */     return new ResponseEntity(retrieveLinesResponseGSMTO, HttpStatus.OK);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   @GetMapping({"/actualiza"})
/*      */   public String handleGetactualiza() {
/*  295 */     return "redirect:/index.html";
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ResponseEntity<?> actualiza(@Valid @RequestBody PerfilActualizaClientRequest perfilActualizaClientRequest, @RequestHeader("Authorization") String bearerToken) {
/*  302 */     PerfilActualizaClientResponse perfilActualizaClientResponse = new PerfilActualizaClientResponse();
/*  303 */     String token = bearerToken.substring(7);
/*      */     
/*  305 */     ConcurrentHashMap<String, String> userSessions = ScheduledDataUpdater.getuserSessions();
/*      */ 
/*      */     
/*  308 */     String username = getUserNameFromJwtToken(token);
/*  309 */     String userIp = this.request.getRemoteAddr();
/*  310 */     if (userSessions.containsKey(username) && !((String)userSessions.get(username)).equals(userIp)) {
/*  311 */       perfilActualizaClientResponse.setCodigo("RA2099");
/*  312 */       return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(perfilActualizaClientResponse);
/*      */     } 
/*      */ 
/*      */     
/*  316 */     long t1 = System.currentTimeMillis();
/*  317 */     String host = this.request.getRemoteAddr();
/*      */     
/*  319 */     String transaccionId = perfilActualizaClientRequest.getOrigin() + "-" + perfilActualizaClientRequest.getTransaccionId() + "-" + host + "-" + perfilActualizaClientRequest.getUsername() + "-" + t1;
/*      */     
/*  321 */     String origen = "PerfilClienteController.actualiza";
/*  322 */     String tipoTransaccion = "WS";
/*  323 */     String codigo = "AR-XXX";
/*  324 */     String aplicacion = "Api-Autogestion";
/*      */     
/*  326 */     Map<String, Object> parametersIn = new HashMap<>();
/*  327 */     parametersIn.put("In:", perfilActualizaClientRequest);
/*      */     
/*  329 */     if (perfilActualizaClientRequest.getUsername().equals(username)) {
/*  330 */       logger.debug(transaccionId + "|actualiza.username iguales  antes de actualizarDatos " + username);
/*      */       
/*  332 */       if (this.prop.getState_AD().equals("true")) {
/*  333 */         perfilActualizaClientResponse = actualizarDatos(t1, transaccionId, perfilActualizaClientRequest);
/*      */       } else {
/*  335 */         logger.warn(transaccionId + "|PerfilClienteController.perfilActualizado_no_disponible|RA4010-1");
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  342 */       Map<String, Object> parametersOut = new HashMap<>();
/*  343 */       parametersOut.put("Out:", perfilActualizaClientResponse);
/*  344 */       long time2 = System.currentTimeMillis();
/*  345 */       long tiempoDeEjecucion = time2 - t1;
/*  346 */       this.log.escriTrazaInf(codigo, transaccionId, tipoTransaccion, origen, codigo, aplicacion, "PerfilClienteController.actualiza", parametersIn, parametersOut, tiempoDeEjecucion, "RA2007-1");
/*      */       
/*  348 */       if (perfilActualizaClientResponse != null) {
/*  349 */         if (perfilActualizaClientResponse.getCodigo().equals("RA2007")) {
/*  350 */           perfilActualizaClientResponse.setTransaccionId(transaccionId);
/*      */           
/*  352 */           logger.debug(transaccionId + "|PerfilClienteController.actualiza perfilActualizado ClientResponse CacheEvict|" + perfilActualizaClientRequest
/*  353 */               .getUsername());
/*      */         } else {
/*      */           
/*  356 */           perfilActualizaClientResponse.setTransaccionId(transaccionId);
/*  357 */           logger.debug(transaccionId + "|PerfilClienteController.actualiza perfilActualizaClientResponse NOK getCode|" + perfilActualizaClientResponse
/*  358 */               .getCodigo());
/*      */         } 
/*      */       } else {
/*      */         
/*  362 */         perfilActualizaClientResponse.setTransaccionId(transaccionId);
/*  363 */         perfilActualizaClientResponse.setCodigo("RA5026-4");
/*  364 */         logger.debug(transaccionId + "|PerfilClienteController.actualiza perfilActualizaClientResponse NOK getCode|" + perfilActualizaClientResponse
/*  365 */             .getCodigo());
/*      */       } 
/*      */     } else {
/*      */       
/*  369 */       logger.warn("no actualiza.username iguales nok  " + username);
/*  370 */       perfilActualizaClientResponse.setCodigo("RA5026-3");
/*  371 */       perfilActualizaClientResponse.setTransaccionId(transaccionId);
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  376 */     return new ResponseEntity(perfilActualizaClientResponse, HttpStatus.OK);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public PerfilResponse procesarTransaccionBuscarTelefono(long p_t1, String suscriptor, String idTrx) {
/*  390 */     PerfilResponse perfilResponse = new PerfilResponse();
/*  391 */     Operaciones operaciones = new Operaciones();
/*  392 */     String setEndpoint = this.prop.getBuscarTelefonoBp();
/*      */     
/*  394 */     long time1 = 0L;
/*  395 */     time1 = System.currentTimeMillis();
/*  396 */     logger.info(p_t1 + "|procesarTransaccionBuscarTelefono : " + suscriptor + "-" + idTrx);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/*  402 */       BuscarTelefonoRespuestaTo respuestaBT = null;
/*  403 */       PrepagoUserMethodTO resultadoBTTO = null;
/*  404 */       EntradaTo entrada = new EntradaTo();
/*  405 */       AplicacionTo aplicacion = new AplicacionTo();
/*  406 */       SeguridadTo seguridad = new SeguridadTo();
/*      */       
/*  408 */       aplicacion.setNombre(this.prop.getAplicacionBtNombre());
/*  409 */       aplicacion.setCodigo(this.prop.getAplicacionBtCodigo());
/*  410 */       aplicacion.setTipoAplicacion(this.prop.getAplicacionBtTipoAplicacion());
/*  411 */       entrada.setAplicacion(aplicacion);
/*  412 */       logger.debug(p_t1 + "-procesarTransaccionBuscarTelefono requestSD .getAplicacion().getCodigo() : " + entrada.getAplicacion().getCodigo());
/*  413 */       logger.debug(p_t1 + "-procesarTransaccionBuscarTelefono requestSD  .getAplicacion().getNombre(): " + entrada.getAplicacion().getNombre());
/*  414 */       logger.debug(p_t1 + "-procesarTransaccionBuscarTelefono requestSD  getAplicacion().getTipoAplicacion(): " + entrada.getAplicacion().getTipoAplicacion());
/*      */       
/*  416 */       seguridad.setUsuario(this.prop.getSeguridadBtUsuario());
/*  417 */       seguridad.setClave(this.prop.getSeguridadBtClave());
/*  418 */       entrada.setSeguridad(seguridad);
/*  419 */       logger.debug(p_t1 + "-procesarTransaccionBuscarTelefono requestSD  getSeguridad().getUsuario(): " + entrada.getSeguridad().getUsuario());
/*  420 */       logger.debug(p_t1 + "-procesarTransaccionBuscarTelefono requestSD  getSeguridad().getUsuario(): " + entrada.getSeguridad().getClave());
/*      */       
/*  422 */       entrada.setTecnologia(this.prop.getTecnologiaBt());
/*  423 */       entrada.setProveedor(this.prop.getProveedorBt());
/*  424 */       logger.debug(p_t1 + "-procesarTransaccionBuscarTelefono requestSD entrada.getTecnologia() : " + entrada.getTecnologia());
/*  425 */       logger.debug(p_t1 + "-procesarTransaccionBuscarTelefono requestSD  getProveedor: " + entrada.getProveedor());
/*  426 */       entrada.setNodo(operaciones.obtenerNodo(formatearLinea(suscriptor, time1 + "")));
/*  427 */       logger.debug(p_t1 + "-procesarTransaccionBuscarTelefono requestSD  getNodo: " + entrada.getNodo());
/*  428 */       entrada.setIdUsuario(this.prop.getIdUsuarioBt());
/*  429 */       logger.debug(p_t1 + "-procesarTransaccionBuscarTelefono requestSD  getIdUsuario: " + entrada.getIdUsuario());
/*      */       
/*  431 */       logger.debug(p_t1 + "-procesarTransaccionBuscarTelefono requestSD  suscriptor a BuscarTel importante!!!: " + formatearLinea(suscriptor, time1 + ""));
/*      */       
/*  433 */       ClientProfileValidationDao dao = new ClientProfileValidationDao();
/*      */       
/*  435 */       resultadoBTTO = dao.BuscarTelefono(this.prop.getString1() + "" + p_t1, 
/*  436 */           formatearLinea(suscriptor, time1 + ""), entrada, setEndpoint);
/*      */       
/*  438 */       long time2 = System.currentTimeMillis();
/*  439 */       long tiempoDeEjecucion = time2 - time1;
/*      */       
/*  441 */       logger.debug(formatearLinea(suscriptor, time1 + "") + "-" + p_t1 + "-PerfilClienteController.buscarTelefono en ClientProfileValidationDao listo resultadoBTTO" + resultadoBTTO + "|" + tiempoDeEjecucion);
/*  442 */       logger.debug(formatearLinea(suscriptor, time1 + "") + "-" + p_t1 + "-PerfilClienteController.buscarTelefono en ClientProfileValidationDao valores para ir a ss resultadoBTTO.getErrorCode()" + resultadoBTTO.getErrorCode());
/*  443 */       logger.debug(formatearLinea(suscriptor, time1 + "") + "-" + p_t1 + "-PerfilClienteController.buscarTelefono en ClientProfileValidationDao  valores para ir a ss Const.CODIGO_EXITO_PREPAGO " + "AJ001");
/*  444 */       logger.debug(formatearLinea(suscriptor, time1 + "") + "-" + p_t1 + "-PerfilClienteController.buscarTelefono en ClientProfileValidationDao valores para ir a ss   resultadoBTTO.getResultData() " + resultadoBTTO.getResultData());
/*      */       
/*  446 */       if (resultadoBTTO != null && resultadoBTTO
/*  447 */         .getErrorCode() != null && resultadoBTTO
/*  448 */         .getErrorCode().equalsIgnoreCase("AJ001")) {
/*      */         
/*  450 */         respuestaBT = (BuscarTelefonoRespuestaTo)resultadoBTTO.getResultData();
/*      */         
/*  452 */         if (respuestaBT != null) {
/*  453 */           time1 = System.currentTimeMillis();
/*      */           
/*  455 */           perfilResponse.setResultado(respuestaBT);
/*  456 */           perfilResponse.setCodigo("RA2004");
/*  457 */           perfilResponse.setTransaccionId(p_t1 + "");
/*      */           
/*  459 */           DireccionTO_Name direccionTO_Name = new DireccionTO_Name();
/*      */           
/*  461 */           direccionTO_Name = getNameFromDiereccion(respuestaBT.getCliente().getDireccion().getEstado(), respuestaBT.getCliente().getDireccion().getMunicipio(), respuestaBT
/*  462 */               .getCliente().getDireccion().getParroquiaUrbanizacion(), respuestaBT.getCliente().getDireccion().getCiudad(), respuestaBT
/*  463 */               .getCliente().getDireccion().getSector(), respuestaBT
/*  464 */               .getCliente().getDireccion().getNombreCalle(), respuestaBT
/*  465 */               .getCliente().getDireccion().getIdentificadorEdificacion(), respuestaBT
/*  466 */               .getCliente().getDireccion().getTipoEdificacion());
/*  467 */           perfilResponse.setDireccionTO_Name(direccionTO_Name);
/*      */ 
/*      */ 
/*      */           
/*  471 */           SolicitarSaldoElement requestSD = new SolicitarSaldoElement();
/*      */           
/*  473 */           requestSD.setString1(this.prop.getString1() + p_t1);
/*  474 */           logger.info(p_t1 + "-PerfilClienteController.buscarTelefono ir EjecutarConsultarSaldoPrepagoServiceFacade.entradaSD: valor para setString2 importante!!!" + formatearLinea(suscriptor, time1 + ""));
/*  475 */           requestSD.setString2(formatearLinea(suscriptor, time1 + ""));
/*      */ 
/*      */           
/*  478 */           EntradaTo entradaSD = new EntradaTo();
/*      */           
/*  480 */           entradaSD.setTecnologia(this.prop.getTecnologiaSs());
/*  481 */           entradaSD.setProveedor(this.prop.getProveedorSs());
/*  482 */           logger.info(p_t1 + "-PerfilClienteController.buscarTelefono ir EjecutarConsultarSaldoPrepagoServiceFacade.entradaSD: valor para setNodo " + operaciones.obtenerNodo(formatearLinea(suscriptor, time1 + "")));
/*  483 */           entradaSD.setNodo(operaciones.obtenerNodo(formatearLinea(suscriptor, time1 + "")));
/*  484 */           entradaSD.setIdUsuario(this.prop.getIdUsuarioSs());
/*      */ 
/*      */           
/*  487 */           SeguridadTo seguridadSD = new SeguridadTo();
/*  488 */           seguridadSD.setUsuario(this.prop.getSeguridadSsUsuario());
/*  489 */           seguridadSD.setClave(this.prop.getSeguridadSsClave());
/*  490 */           entradaSD.setSeguridad(seguridadSD);
/*      */           
/*  492 */           AplicacionTo aplicacionSD = new AplicacionTo();
/*  493 */           aplicacionSD.setNombre(this.prop.getAplicacionSsNombre());
/*  494 */           aplicacionSD.setTipoAplicacion(this.prop.getAplicacionSsTipoAplicacion());
/*  495 */           aplicacionSD.setCodigo(this.prop.getAplicacionSsCodigo());
/*      */           
/*  497 */           entradaSD.setAplicacion(aplicacionSD);
/*      */           
/*  499 */           requestSD.setEntradaTo3(entradaSD);
/*      */           
/*  501 */           List<SaldoTo> saldoArray = new ArrayList<>();
/*      */           
/*      */           try {
/*  504 */             logger.info(p_t1 + "-PerfilClienteController.buscarTelefono ir EjecutarConsultarSaldoPrepagoServiceFacade.consultarSaldoGsmService: valor suscriptor importante!!! " + formatearLinea(suscriptor, time1 + ""));
/*  505 */             PrepagoUserMethodTO objSaldo = new PrepagoUserMethodTO();
/*  506 */             objSaldo = EjecutarConsultarSaldoPrepagoServiceFacade.consultarSaldoGsmService(idTrx, formatearLinea(suscriptor, time1 + ""), requestSD, this.prop.getWs_solicitudSaldoBp());
/*  507 */             time2 = System.currentTimeMillis();
/*  508 */             tiempoDeEjecucion = time2 - time1;
/*  509 */             logger.debug(p_t1 + "-PerfilClienteController.buscarTelefono  listo EjecutarConsultarSaldoPrepagoServiceFacade sus valores de objSaldo " + objSaldo + "|" + tiempoDeEjecucion);
/*  510 */             logger.debug(p_t1 + "-PerfilClienteController.buscarTelefono  listo EjecutarConsultarSaldoPrepagoServiceFacade sus valores de objSaldo getErrorCode " + objSaldo.getErrorCode());
/*  511 */             logger.debug(p_t1 + "-PerfilClienteController.buscarTelefono listo EjecutarConsultarSaldoPrepagoServiceFacade sus valores de objSaldo.getResultData " + objSaldo.getResultData());
/*      */             
/*  513 */             logger.debug(p_t1 + "- PerfilClienteController.buscarTelefono listo EjecutarConsultarSaldoPrepagoServiceFacade sus valores de SolicitarSaldoResponseElement getResultData.responseElement ");
/*  514 */             SolicitarSaldoResponseElement responseElement = (SolicitarSaldoResponseElement)objSaldo.getResultData();
/*      */             
/*  516 */             logger.debug(p_t1 + "-luego SolicitarSaldoResponseElement getResultData.responseElement  preparando el fori " + responseElement);
/*      */             
/*  518 */             if (responseElement != null && responseElement.getResult() != null && responseElement.getResult().getItem() != null) {
/*  519 */               for (int i = 0; i < responseElement.getResult().getItem().size(); i++) {
/*  520 */                 logger.debug(p_t1 + "-for  " + responseElement.getResult().getItem().get(i));
/*      */                 
/*  522 */                 SaldoTo saldo = responseElement.getResult().getItem().get(i);
/*  523 */                 logger.debug(p_t1 + "-SaldoTo  " + saldo.getCodigoBalance());
/*  524 */                 saldoArray.add(saldo);
/*      */               } 
/*      */             } else {
/*  527 */               logger.error(p_t1 + "-PerfilClienteController.buscarTelefono en EjecutarConsultarSaldoPrepagoServiceFacade.obtenerSaldo fuen vacio o null");
/*  528 */               perfilResponse.setSaldoTo(null);
/*  529 */               perfilResponse.setCodigo("RA5019");
/*  530 */               perfilResponse.setTransaccionId(p_t1 + "");
/*      */             } 
/*      */ 
/*      */ 
/*      */             
/*  535 */             if (saldoArray != null) {
/*  536 */               if (!saldoArray.isEmpty()) {
/*  537 */                 perfilResponse.setSaldoTo(saldoArray);
/*  538 */                 perfilResponse.setCodigo("RA2005");
/*  539 */                 perfilResponse.setTransaccionId(p_t1 + "");
/*      */               } 
/*      */             } else {
/*      */               
/*  543 */               logger.error(p_t1 + "-PerfilClienteController.buscarTelefono en EjecutarConsultarSaldoPrepagoServiceFacade.obtenerSaldo fuen vacio o null");
/*  544 */               perfilResponse.setSaldoTo(null);
/*  545 */               perfilResponse.setCodigo("RA5019");
/*  546 */               perfilResponse.setTransaccionId(p_t1 + "");
/*      */             }
/*      */           
/*  549 */           } catch (Exception e) {
/*  550 */             logger.error(p_t1 + "-PerfilClienteController.buscarTelefono en EjecutarConsultaSaldoGsmDao.obtenerSaldo");
/*  551 */             perfilResponse.setSaldoTo(null);
/*  552 */             perfilResponse.setCodigo("RA4007");
/*  553 */             perfilResponse.setTransaccionId(p_t1 + "");
/*      */           } 
/*      */           
/*  556 */           perfilResponse.setFechaPago(operaciones.getfechPago(suscriptor));
/*      */         } else {
/*      */           
/*  559 */           logger.warn(p_t1 + "-PerfilClienteController.buscarTelefono respuestaBT Null");
/*  560 */           perfilResponse.setCodigo("RA5016");
/*  561 */           perfilResponse.setTransaccionId(p_t1 + "");
/*      */         } 
/*      */       } else {
/*  564 */         logger.warn(p_t1 + "-PerfilClienteController.buscarTelefono: " + suscriptor + "  code  " + resultadoBTTO.getErrorCode() + " message " + resultadoBTTO.getErrorMessage());
/*      */ 
/*      */         
/*  567 */         if (resultadoBTTO.getErrorCode() != null && resultadoBTTO
/*  568 */           .getErrorCode().equalsIgnoreCase("WS005")) {
/*  569 */           perfilResponse.setCodigo("RA5016-2");
/*  570 */           perfilResponse.setTransaccionId(p_t1 + "");
/*  571 */           logger.warn(p_t1 + "-PerfilClienteController.buscarTelefono Cliente con status invalido " + suscriptor + " " + resultadoBTTO.getErrorCode());
/*      */         }
/*  573 */         else if (resultadoBTTO.getErrorCode() != null && resultadoBTTO
/*  574 */           .getErrorCode().equalsIgnoreCase("4006")) {
/*  575 */           perfilResponse.setCodigo("RA5016-3");
/*  576 */           perfilResponse.setTransaccionId(p_t1 + "");
/*  577 */           logger.warn(p_t1 + "-PerfilClienteController.buscarTelefono Cliente no existe " + suscriptor);
/*      */         }
/*  579 */         else if (resultadoBTTO.getErrorCode() != null && resultadoBTTO
/*  580 */           .getErrorCode().equalsIgnoreCase("4005")) {
/*  581 */           perfilResponse.setCodigo("RA5016-5");
/*  582 */           perfilResponse.setTransaccionId(p_t1 + "");
/*  583 */           logger.warn(p_t1 + "-PerfilClienteController.buscarTelefono(4005,RA5016-5) Cliente Subscriber not in range map-" + suscriptor);
/*      */         }
/*  585 */         else if (resultadoBTTO.getErrorMessage() != null) {
/*      */           
/*  587 */           logger.warn(p_t1 + "-PerfilClienteController.buscarTelefono(4006,o 4005...etc) resultadoBTTO.getErrorMessage()  -" + resultadoBTTO.getErrorMessage());
/*      */ 
/*      */           
/*  590 */           if (resultadoBTTO.getErrorMessage().contains("range")) {
/*  591 */             perfilResponse.setCodigo("RA5016-5");
/*  592 */             perfilResponse.setTransaccionId(p_t1 + "");
/*  593 */             logger.warn(p_t1 + "-PerfilClienteController.buscarTelefono(4005,RA5016-5) Cliente Subscriber not in range map-" + suscriptor);
/*      */           } 
/*      */ 
/*      */           
/*  597 */           if (resultadoBTTO.getErrorMessage().contains("existe")) {
/*  598 */             perfilResponse.setCodigo("RA5016-3");
/*  599 */             perfilResponse.setTransaccionId(p_t1 + "");
/*  600 */             logger.warn(p_t1 + "-PerfilClienteController.buscarTelefono(4006,RA5016-3) Cliente no existe -" + suscriptor);
/*      */           } 
/*      */           
/*  603 */           if (resultadoBTTO.getErrorMessage().contains("PlataformaNoDisponibleException_Exception")) {
/*  604 */             perfilResponse.setCodigo("RA4006-2");
/*  605 */             perfilResponse.setTransaccionId(p_t1 + "");
/*  606 */             logger.warn(p_t1 + "-PerfilClienteController.buscarTelefono() PlataformaNoDisponibleException_Exception -" + suscriptor);
/*      */           } 
/*      */         } else {
/*      */           
/*  610 */           perfilResponse.setCodigo("RA5016");
/*  611 */           perfilResponse.setTransaccionId(p_t1 + "");
/*  612 */           logger.warn(p_t1 + "-PerfilClienteController.buscarTelefono Cliente Desconocido " + suscriptor);
/*      */         }
/*      */       
/*      */       }
/*      */     
/*  617 */     } catch (Exception e) {
/*  618 */       perfilResponse.setCodigo("RA4006-2");
/*  619 */       perfilResponse.setTransaccionId(p_t1 + "");
/*  620 */       logger.error(p_t1 + "-PerfilClienteController: Se produjo un error al procesar la transaccion, ws buscarTelefono Exception", e);
/*      */     } 
/*      */ 
/*      */     
/*  624 */     MDC.remove("ANI");
/*  625 */     return perfilResponse;
/*      */   }
/*      */ 
/*      */   
/*      */   public String getSDP(String numero, String p_t1) {
/*  630 */     if (numero.length() > 10 && numero.length() == 11) {
/*  631 */       numero = numero.substring(1, numero.length());
/*      */     }
/*      */     
/*  634 */     logger.debug("PerfilClienteController saber que sdp es el numero " + numero);
/*      */     
/*  636 */     String sdp = "8";
/*      */ 
/*      */     
/*  639 */     String codigo = numero.substring(0, 3);
/*      */     
/*  641 */     if (codigo.equals("426")) {
/*      */       
/*  643 */       int castRango = Integer.parseInt(numero.substring(3, 5));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  650 */       if (castRango >= 30 && castRango <= 39) {
/*  651 */         sdp = "1";
/*      */       }
/*      */       
/*  654 */       if (castRango >= 10 && castRango <= 19) {
/*  655 */         sdp = "2";
/*      */       }
/*      */       
/*  658 */       if ((castRango >= 70 && castRango <= 79) || (castRango >= 90 && castRango <= 99))
/*      */       {
/*  660 */         sdp = "3";
/*      */       }
/*      */ 
/*      */       
/*  664 */       if ((castRango >= 25 && castRango <= 29) || (castRango >= 0 && castRango <= 2))
/*      */       {
/*  666 */         sdp = "4";
/*      */       }
/*      */       
/*  669 */       if ((castRango >= 40 && castRango <= 49) || (castRango >= 3 && castRango <= 4))
/*      */       {
/*  671 */         sdp = "5";
/*      */       }
/*      */       
/*  674 */       if ((castRango >= 60 && castRango <= 69) || (castRango >= 80 && castRango <= 89) || (castRango >= 5 && castRango <= 7))
/*      */       {
/*      */         
/*  677 */         sdp = "6";
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*  682 */       if ((castRango >= 20 && castRango <= 24) || (castRango >= 8 && castRango <= 9)) {
/*  683 */         sdp = "8";
/*      */       
/*      */       }
/*      */     }
/*  687 */     else if (codigo.equals("416")) {
/*      */ 
/*      */       
/*  690 */       int castRango = Integer.parseInt(numero.substring(3, 5));
/*      */ 
/*      */       
/*  693 */       if (castRango >= 90 && castRango <= 99) {
/*  694 */         sdp = "3";
/*      */       }
/*      */       
/*  697 */       if (castRango >= 40 && castRango <= 49) {
/*  698 */         sdp = "4";
/*      */       }
/*      */ 
/*      */       
/*  702 */       if ((castRango >= 0 && castRango <= 9) || (castRango >= 60 && castRango <= 69) || (castRango >= 80 && castRango <= 89))
/*      */       {
/*      */         
/*  705 */         sdp = "6";
/*      */       }
/*      */       
/*  708 */       if ((castRango >= 50 && castRango <= 59) || (castRango >= 10 && castRango <= 19) || (castRango >= 30 && castRango <= 39) || (castRango >= 70 && castRango <= 79))
/*      */       {
/*      */ 
/*      */         
/*  712 */         sdp = "7";
/*      */       }
/*      */       
/*  715 */       if (castRango >= 20 && castRango <= 29) {
/*  716 */         sdp = "8";
/*      */       }
/*      */     }
/*      */     else {
/*      */       
/*  721 */       Object object = null;
/*      */     } 
/*      */ 
/*      */     
/*  725 */     return sdp;
/*      */   }
/*      */ 
/*      */   
/*      */   public String valorSDP(String p_t1) {
/*  730 */     logger.debug(p_t1 + "|Reader valorSDP");
/*  731 */     File archivo = null;
/*  732 */     FileReader fr = null;
/*  733 */     BufferedReader br = null;
/*  734 */     String AUX_SDP = "";
/*      */     try {
/*  736 */       archivo = new File(this.prop.getPath_file_sdp());
/*      */       
/*  738 */       fr = new FileReader(archivo);
/*  739 */       br = new BufferedReader(fr);
/*  740 */       AUX_SDP = br.readLine();
/*  741 */       logger.debug(p_t1 + "|PerfilClienteController.Reader valorSDP " + AUX_SDP);
/*  742 */     } catch (Exception e) {
/*  743 */       logger.error(p_t1 + "|PerfilClienteController Initialization Exception: " + e);
/*  744 */       e.printStackTrace();
/*      */     } finally {
/*      */       try {
/*  747 */         if (fr != null) {
/*  748 */           fr.close();
/*      */         }
/*  750 */       } catch (Exception localException1) {
/*  751 */         logger.error(p_t1 + "|PerfilClienteController.localException: " + localException1);
/*  752 */         localException1.printStackTrace();
/*      */       } 
/*      */     } 
/*      */     
/*  756 */     logger.debug(p_t1 + "|PerfilClienteController the end Reader valorSDP");
/*  757 */     return AUX_SDP;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private String formatearLinea(String numero, String p_t1) {
/*  792 */     logger.debug(p_t1 + "|PerfilClienteController tengo que cambiar este numero por las mascara correcta  " + numero);
/*  793 */     String numForma = null;
/*  794 */     if (numero.length() >= 10) {
/*  795 */       if (numero.length() == 11) {
/*  796 */         numero = numero.substring(1, numero.length());
/*      */       }
/*      */       
/*  799 */       if (numero.startsWith("158")) {
/*  800 */         numForma = "416" + numero.substring(3, numero.length());
/*  801 */       } else if (numero.startsWith("199")) {
/*  802 */         numForma = "426" + numero.substring(3, numero.length());
/*      */       } else {
/*  804 */         numForma = numero;
/*      */       } 
/*      */     } 
/*  807 */     logger.debug(p_t1 + "|PerfilClienteController sacando el numero con  mascara correcta  " + numero);
/*  808 */     return numForma;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public PerfilActualizaClientResponse actualizarDatos(long p_t1, String idTrx, PerfilActualizaClientRequest perfilActualizaClientRequest) {
/*  814 */     PerfilActualizaClientResponse actualizarDatos = new PerfilActualizaClientResponse();
/*  815 */     PrepagoUserMethodTO objActuDatosPrep = new PrepagoUserMethodTO();
/*  816 */     ActualizarDatosClienteElement actualizarDatosClienteElement = new ActualizarDatosClienteElement();
/*      */     
/*  818 */     Operaciones operaciones = new Operaciones();
/*      */     
/*  820 */     String suscriptor = perfilActualizaClientRequest.getUsername();
/*      */     
/*  822 */     logger.info(p_t1 + "|PerfilClienteController.actualiza numero request |" + suscriptor);
/*      */ 
/*      */     
/*  825 */     Cache cache = this.cacheManager.getCache("apiCache");
/*  826 */     ResponseEntity responseEntity = (cache != null) ? (ResponseEntity)cache.get(suscriptor).get() : null;
/*  827 */     logger.debug(p_t1 + "|PerfilClienteController.actualiza numero |" + suscriptor);
/*  828 */     PerfilResponse perfilResponse = (responseEntity != null) ? (PerfilResponse)responseEntity.getBody() : null;
/*      */     
/*  830 */     logger.debug(p_t1 + "|PerfilClienteController.actualiza perfilResponse |" + perfilResponse);
/*      */ 
/*      */ 
/*      */     
/*  834 */     suscriptor = formatearLinea(perfilActualizaClientRequest.getUsername(), p_t1 + "");
/*  835 */     logger.debug(p_t1 + "|PerfilClienteController.actualiza numero formatearLinea |" + suscriptor);
/*      */     
/*  837 */     if (perfilResponse != null && perfilResponse.getResultado() != null && perfilResponse.getResultado().getCliente() != null) {
/*      */       
/*  839 */       ClienteTo cliente = new ClienteTo();
/*      */       
/*  841 */       DireccionTo direccion = new DireccionTo();
/*      */       
/*  843 */       PersonaTo personaAutorizada = new PersonaTo();
/*  844 */       PersonaTo userAutenticado = new PersonaTo();
/*  845 */       AgenteTo agente = new AgenteTo();
/*  846 */       EntradaTo entrada = new EntradaTo();
/*      */       
/*  848 */       AplicacionTo aplicacion = new AplicacionTo();
/*      */       
/*  850 */       SeguridadTo seguridad = new SeguridadTo();
/*      */       
/*  852 */       ActualizaClienteTo actualizaClienteTo = new ActualizaClienteTo();
/*      */       
/*  854 */       aplicacion.setNombre(this.prop.getAplicacionADnombre());
/*  855 */       aplicacion.setCodigo(this.prop.getAplicacionADcodigo());
/*  856 */       aplicacion.setTipoAplicacion(this.prop.getAplicacionADtipoAplicacion());
/*  857 */       entrada.setAplicacion(aplicacion);
/*      */       
/*  859 */       seguridad.setUsuario(this.prop.getSeguridadADusuario());
/*  860 */       seguridad.setClave(this.prop.getSeguridadADclave());
/*  861 */       entrada.setSeguridad(seguridad);
/*      */       
/*  863 */       entrada.setTecnologia(this.prop.getEntradaADtecnologia());
/*  864 */       entrada.setProveedor(this.prop.getEntradaADproveedor());
/*  865 */       entrada.setIdUsuario(this.prop.getEntradaADidUsuario());
/*  866 */       entrada.setNodo(operaciones.obtenerNodo(suscriptor));
/*      */       
/*  868 */       cliente.setPrimerNombre(perfilResponse.getResultado().getCliente().getPrimerNombre());
/*  869 */       cliente.setSegundoNombre(perfilResponse.getResultado().getCliente().getSegundoNombre());
/*  870 */       cliente.setPrimerApellido(perfilResponse.getResultado().getCliente().getPrimerApellido());
/*  871 */       cliente.setSegundoApellido(perfilResponse.getResultado().getCliente().getSegundoApellido());
/*  872 */       cliente.setTipoDocumento(perfilResponse.getResultado().getCliente().getTipoDocumento());
/*  873 */       cliente.setDocumentoId(perfilResponse.getResultado().getCliente().getDocumentoId());
/*  874 */       cliente.setNacionalidad(perfilResponse.getResultado().getCliente().getNacionalidad());
/*  875 */       cliente.setSexo(perfilResponse.getResultado().getCliente().getSexo());
/*  876 */       cliente.setTitulo(perfilResponse.getResultado().getCliente().getTitulo());
/*  877 */       cliente.setProfesion(perfilResponse.getResultado().getCliente().getProfesion());
/*      */       
/*  879 */       logger.debug(p_t1 + "|PerfilClienteController.actualiza dato old perfilResponse.resultado.getCliente().getCorreoElectronico()|" + perfilResponse.resultado.getCliente().getCorreoElectronico());
/*      */       
/*  881 */       logger.debug(p_t1 + "|PerfilClienteController.actualiza dato perfilActualizaClientRequest.getCorreoElectronico()|" + perfilActualizaClientRequest.getMail());
/*      */       
/*  883 */       cliente.setCorreoElectronico(perfilActualizaClientRequest.getMail());
/*      */       
/*  885 */       cliente.setFechaNacimiento(perfilResponse.resultado.getCliente().getFechaNacimiento());
/*      */       
/*  887 */       logger.debug(p_t1 + "|PerfilClienteController.actualiza dato old perfilResponse.resultado.getCliente().getTelefonoHabitacion()|" + perfilResponse.resultado.getCliente().getTelefonoHabitacion());
/*  888 */       cliente.setTelefonoHabitacion(perfilActualizaClientRequest.getTelefonoHabitacion());
/*  889 */       logger.debug(p_t1 + "|PerfilClienteController.actualiza perfilActualizaClientRequest.getTelefonoHabitacion()|" + perfilActualizaClientRequest.getTelefonoHabitacion());
/*      */       
/*  891 */       logger.debug(p_t1 + "|PerfilClienteController.actualiza dato old perfilResponse.resultado.getCliente().getTelefonoOficina()|" + perfilResponse.resultado.getCliente().getTelefonoOficina());
/*  892 */       cliente.setTelefonoOficina(perfilActualizaClientRequest.getTelefonoOficina());
/*  893 */       logger.debug(p_t1 + "|PerfilClienteController.actualiza perfilActualizaClientRequest.getTelefonoOficina()|" + perfilActualizaClientRequest.getTelefonoOficina());
/*      */       
/*  895 */       cliente.setExtensionOficina(perfilResponse.getResultado().getCliente().getExtensionOficina());
/*  896 */       cliente.setFechaIngresoEmpresa(perfilResponse.getResultado().getCliente().getFechaIngresoEmpresa());
/*  897 */       cliente.setEmpresa(perfilResponse.getResultado().getCliente().getEmpresa());
/*  898 */       cliente.setCargo(perfilResponse.getResultado().getCliente().getCargo());
/*  899 */       cliente.setTipoCliente(perfilResponse.getResultado().getCliente().getTipoCliente());
/*      */ 
/*      */       
/*  902 */       direccion.setEstado(perfilResponse.getResultado().getCliente().getDireccion().getEstado());
/*  903 */       direccion.setCiudad(perfilResponse.getResultado().getCliente().getDireccion().getCiudad());
/*  904 */       direccion.setIdApartamento(perfilResponse.getResultado().getCliente().getDireccion().getIdApartamento());
/*  905 */       direccion.setIdentificadorEdificacion(perfilResponse.getResultado().getCliente().getDireccion().getIdentificadorEdificacion());
/*  906 */       direccion.setIdentificadorPiso(perfilResponse.getResultado().getCliente().getDireccion().getIdentificadorPiso());
/*  907 */       direccion.setMunicipio(perfilResponse.getResultado().getCliente().getDireccion().getMunicipio());
/*  908 */       direccion.setNombreCalle(perfilResponse.getResultado().getCliente().getDireccion().getNombreCalle());
/*  909 */       direccion.setParroquiaUrbanizacion(perfilResponse.getResultado().getCliente().getDireccion().getParroquiaUrbanizacion());
/*  910 */       direccion.setPuntoReferencia(perfilResponse.getResultado().getCliente().getDireccion().getPuntoReferencia());
/*  911 */       direccion.setSector(perfilResponse.getResultado().getCliente().getDireccion().getSector());
/*  912 */       direccion.setTipoApartamento(perfilResponse.getResultado().getCliente().getDireccion().getTipoApartamento());
/*  913 */       direccion.setTipoCalle(perfilResponse.getResultado().getCliente().getDireccion().getTipoCalle());
/*  914 */       direccion.setTipoEdificacion(perfilResponse.getResultado().getCliente().getDireccion().getTipoEdificacion());
/*  915 */       direccion.setTipoPiso(perfilResponse.getResultado().getCliente().getDireccion().getTipoPiso());
/*  916 */       direccion.setZonaPostal(perfilResponse.getResultado().getCliente().getDireccion().getZonaPostal());
/*      */       
/*  918 */       cliente.setDireccion(direccion);
/*      */ 
/*      */       
/*  921 */       personaAutorizada.setNombre(perfilResponse.getResultado().getCliente().getPrimerNombre());
/*  922 */       personaAutorizada.setApellido(perfilResponse.getResultado().getCliente().getPrimerApellido());
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  927 */       agente.setDescripcion(this.prop.getAgenteADdescripcion());
/*  928 */       agente.setIdAgente(this.prop.getAgenteADidAgente());
/*      */       
/*  930 */       actualizaClienteTo.setPersonaAutorizada(personaAutorizada);
/*  931 */       actualizaClienteTo.setCliente(cliente);
/*  932 */       actualizaClienteTo.setEntrada(entrada);
/*  933 */       actualizaClienteTo.setNumero(suscriptor);
/*  934 */       actualizaClienteTo.setAgente(agente);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  940 */       userAutenticado.setNombre(this.prop.getUserAutenADnombre());
/*  941 */       userAutenticado.setCedula(this.prop.getUserAutenADcedula());
/*      */       
/*  943 */       actualizaClienteTo.setUsuarioAutenticado(userAutenticado);
/*  944 */       actualizarDatosClienteElement.setTransaccionId(perfilActualizaClientRequest.getTransaccionId());
/*  945 */       actualizarDatosClienteElement.setCliente(actualizaClienteTo);
/*      */       
/*  947 */       logger.debug(p_t1 + "||PerfilClienteController.actualizarDatos antes de objActuDatosPrep validar los datos de entrada para el cambio:" + objActuDatosPrep);
/*      */       
/*  949 */       if (operaciones.validarCorreo(perfilActualizaClientRequest.getMail()) && operaciones.validarNumeros(perfilActualizaClientRequest.getTelefonoHabitacion(), perfilActualizaClientRequest.getTelefonoOficina()))
/*      */       {
/*      */         
/*      */         try {
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  957 */           objActuDatosPrep = ActualizarDatosPrepagoGSMDao.actualizarDatosGSM(idTrx, this.prop.getActualizaCliente(), actualizarDatosClienteElement);
/*      */         }
/*  959 */         catch (Exception ex) {
/*      */           
/*  961 */           logger.error("RA4010|PerfilClienteController.actualizarDatos: Error al ejecutar la Actualizacion de Datos " + ex);
/*      */           
/*  963 */           logger.error("RA4010|PerfilClienteController.actualizarDatos Exception: " + ex);
/*  964 */           actualizarDatos.setCodigo("RA4010");
/*      */         } 
/*      */ 
/*      */ 
/*      */         
/*  969 */         logger.debug(p_t1 + "|PerfilClienteController.actualizarDatos  luego de  getErrorCode " + objActuDatosPrep.getErrorCode());
/*  970 */         logger.debug(p_t1 + "|PerfilClienteController.actualizarDatos  luego de  getErrorMessage " + objActuDatosPrep.getErrorMessage());
/*  971 */         logger.debug(p_t1 + "|PerfilClienteController.actualizarDatos  luego de  getResultData " + objActuDatosPrep.getResultData());
/*  972 */         logger.debug(p_t1 + "|PerfilClienteController.actualizarDatos  luego de  getStatus " + objActuDatosPrep.getStatus());
/*      */         
/*  974 */         if (objActuDatosPrep.getStatus() != null)
/*      */         {
/*  976 */           if (objActuDatosPrep.getStatus().equals("1")) {
/*  977 */             actualizarDatos.setCodigo("RA2007");
/*  978 */             ActualizarDatosClienteResponseElement responseElement = (ActualizarDatosClienteResponseElement)objActuDatosPrep.getResultData();
/*  979 */             logger.info(p_t1 + "responseElement.getResult().getNumeroContrato() " + responseElement.getResult().getNumeroContrato());
/*      */             
/*  981 */             if (responseElement != null && responseElement.getResult() != null && responseElement.getResult().getNumeroContrato() > 0L) {
/*  982 */               logger.info(p_t1 + "responseElement.getResult().getNumeroContrato() " + responseElement.getResult().getNumeroContrato());
/*  983 */               actualizarDatos.setNumeroContrato(String.valueOf(responseElement.getResult().getNumeroContrato()));
/*      */             } else {
/*  985 */               logger.warn(p_t1 + "responseElement, responseElement.getResult(), responseElement.getResult().getNumeroContrato() is null or responseElement.getResult().getNumeroContrato() is not greater than 0");
/*      */             } 
/*      */ 
/*      */             
/*  989 */             logger.debug(p_t1 + "|PerfilClienteController.actualizarDatos Usa los datos del cliente para construir el request al servicio web de actualización |" + suscriptor);
/*      */           }
/*      */           else {
/*      */             
/*  993 */             actualizarDatos.setCodigo("RA5026");
/*      */             
/*  995 */             logger.warn(p_t1 + "|PerfilClienteController.actualizarDatos  NOK ws |RA5026|" + suscriptor);
/*      */           
/*      */           }
/*      */         
/*      */         }
/*      */       }
/*      */       else
/*      */       {
/* 1003 */         actualizarDatos.setCodigo("RA5026-1");
/*      */         
/* 1005 */         logger.debug(p_t1 + "|PerfilClienteController.actualizarDatos_NOK luego de validar los datos|" + perfilActualizaClientRequest.getMail() + " " + perfilActualizaClientRequest.getTelefonoHabitacion() + " " + perfilActualizaClientRequest.getTelefonoOficina());
/*      */       
/*      */       }
/*      */ 
/*      */     
/*      */     }
/*      */     else {
/*      */       
/* 1013 */       actualizarDatos.setCodigo("RA5026-2");
/*      */       
/* 1015 */       logger.debug(p_t1 + "|PerfilClienteController.actualizarDatos datos del cliente no están en el caché no se actualiza|" + suscriptor);
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1020 */     return actualizarDatos;
/*      */   }
/*      */   
/*      */   public PerfilActualizaClientResponse ejecutarActualizarDatos(String transaccionId, String celular, String wsdl, ActualizarDatosClienteElement actualizaClienteTo) {
/* 1024 */     PerfilActualizaClientResponse perfRespTransaccion = new PerfilActualizaClientResponse();
/*      */     
/* 1026 */     return perfRespTransaccion;
/*      */   }
/*      */   
/*      */   public String getUserNameFromJwtToken(String token) {
/* 1030 */     String usernamesplit = null;
/* 1031 */     if (!token.equals("")) {
/*      */       
/* 1033 */       usernamesplit = ((Claims)Jwts.parser().setSigningKey(this.prop.getJwtSecret()).parseClaimsJws(token).getBody()).getSubject();
/*      */       
/* 1035 */       if (!usernamesplit.equals("")) {
/* 1036 */         String[] arrayUsername = usernamesplit.split(";");
/*      */         
/* 1038 */         usernamesplit = arrayUsername[0];
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 1043 */     return usernamesplit;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public DireccionTO_Name getNameFromDiereccion(String estado, String municipio, String parroquia, String ciudad, String sector, String nombreCalle, String identificadorEdificacion, String tipoEdificacion) {
/* 1049 */     DireccionTO_Name direccionTO_Name = new DireccionTO_Name();
/* 1050 */     long t1 = System.currentTimeMillis();
/* 1051 */     long t2 = 0L;
/* 1052 */     long tiempoEjecucion = 0L;
/* 1053 */     logger.info("ScheduledDataUpdater.updateData Se esta ejecutando ");
/*      */     
/* 1055 */     logger.debug("getNameFromDiereccion  nombreCalle " + nombreCalle);
/* 1056 */     logger.debug("getNameFromDiereccion  identificadorEdificacion " + identificadorEdificacion);
/* 1057 */     logger.debug("getNameFromDiereccion  tipoEdificacion " + tipoEdificacion);
/*      */ 
/*      */     
/* 1060 */     String estado_in = getNameState(estado);
/* 1061 */     t2 = System.currentTimeMillis();
/* 1062 */     tiempoEjecucion = t2 - t1;
/* 1063 */     logger.debug("getNameFromDiereccion_estado " + estado + "|tiempoEjecucion|" + tiempoEjecucion);
/*      */     
/* 1065 */     String municipio_in = getNameStateMunicipio(estado, municipio);
/* 1066 */     t2 = System.currentTimeMillis();
/* 1067 */     tiempoEjecucion = t2 - t1;
/* 1068 */     logger.debug("getNameFromDiereccion_municipio " + municipio + "|tiempoEjecucion|" + tiempoEjecucion);
/*      */     
/* 1070 */     String parroquia_in = getNameMunicipioPrrq(municipio, parroquia);
/* 1071 */     t2 = System.currentTimeMillis();
/* 1072 */     tiempoEjecucion = t2 - t1;
/* 1073 */     logger.debug("getNameFromDiereccion_parroquia " + parroquia + "|tiempoEjecucion|" + tiempoEjecucion);
/*      */     
/* 1075 */     String city_in = getNameCityprrq(parroquia, ciudad);
/* 1076 */     t2 = System.currentTimeMillis();
/* 1077 */     tiempoEjecucion = t2 - t1;
/* 1078 */     logger.debug("getNameFromDiereccion_ciudad " + ciudad + "|tiempoEjecucion|" + tiempoEjecucion);
/*      */     
/* 1080 */     String sector_in = getNameSectorPrrq(parroquia, sector);
/* 1081 */     t2 = System.currentTimeMillis();
/* 1082 */     tiempoEjecucion = t2 - t1;
/* 1083 */     logger.debug("getNameFromDiereccion_sector " + sector + "|tiempoEjecucion|" + tiempoEjecucion);
/*      */     
/* 1085 */     direccionTO_Name.setEstado((estado_in != null) ? estado_in : "ESTADO");
/* 1086 */     direccionTO_Name.setMunicipio((municipio_in != null) ? municipio_in : "MUNICIPIO");
/* 1087 */     direccionTO_Name.setParroquiaUrbanizacion((parroquia_in != null) ? parroquia_in : "PARROQUIA");
/*      */     
/* 1089 */     direccionTO_Name.setCiudad((city_in != null) ? city_in : "CIUDAD");
/* 1090 */     direccionTO_Name.setSector((sector_in != null) ? sector_in : "SECTOR");
/* 1091 */     direccionTO_Name.setNombreCalle("CALLE");
/* 1092 */     direccionTO_Name.setIdentificadorEdificacion("EDIFICACION");
/* 1093 */     direccionTO_Name.setTipoEdificacion("TIPO_EDIFICACION");
/* 1094 */     direccionTO_Name.setPuntoReferencia("REFERENCIA");
/*      */     
/* 1096 */     return direccionTO_Name;
/*      */   }
/*      */   
/*      */   public String getNameState(String state_codigo) {
/* 1100 */     logger.debug("getNameState " + state_codigo + "||");
/* 1101 */     String name = null;
/* 1102 */     List<StateTO> getStates = ScheduledDataUpdater.getPkgState();
/*      */     
/* 1104 */     for (StateTO obj : getStates) {
/* 1105 */       if (obj.getStateCode().equals(state_codigo)) {
/* 1106 */         name = obj.getStateName();
/* 1107 */         return name;
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 1112 */     return name;
/*      */   }
/*      */   
/*      */   public String getNameStateMunicipio(String state_codigo, String code_municipio) {
/* 1116 */     logger.debug("getNameStateMunicipio " + state_codigo + "|| code_municipio " + code_municipio);
/* 1117 */     String name = null;
/* 1118 */     List<MunicipalitieTO> getPkgMunicipalitie = ScheduledDataUpdater.getPkgMunicipalitieTO();
/*      */     
/* 1120 */     for (MunicipalitieTO obj : getPkgMunicipalitie) {
/* 1121 */       if (obj.getStateCode().equals(state_codigo) && obj.getMunicipioCode().equals(code_municipio)) {
/* 1122 */         name = obj.getParroquiaName();
/* 1123 */         return name;
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 1128 */     return name;
/*      */   }
/*      */   
/*      */   public String getNameMunicipioPrrq(String code_municipio, String code_prrq) {
/* 1132 */     logger.debug("getNameMunicipioPrrq code_municipio" + code_municipio + "|| code_prrq " + code_prrq);
/* 1133 */     String name = null;
/* 1134 */     List<ParisheTO> getPkgParisheTO = ScheduledDataUpdater.getPkgParisheTO();
/*      */     
/* 1136 */     for (ParisheTO obj : getPkgParisheTO) {
/* 1137 */       if (obj.getParroquiaCode().equals(code_prrq) && obj.getMunicipioCode().equals(code_municipio)) {
/* 1138 */         name = obj.getParroquiaName();
/* 1139 */         return name;
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 1144 */     return name;
/*      */   }
/*      */   
/*      */   public String getNameSectorPrrq(String code_prrq, String code_sector) {
/* 1148 */     logger.debug("getNameMunicipioPrrq code_prrq" + code_prrq + "|| code_sector " + code_sector);
/* 1149 */     String name = null;
/* 1150 */     List<SectorTO> getPkgSectorTO = ScheduledDataUpdater.getPkgtSectores();
/*      */     
/* 1152 */     for (SectorTO obj : getPkgSectorTO) {
/* 1153 */       if (obj.getParroquiaCode().equals(code_prrq) && obj.getSectorCode().equals(code_sector)) {
/* 1154 */         name = obj.getSectorName();
/* 1155 */         return name;
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 1160 */     return name;
/*      */   }
/*      */   
/*      */   public String getNameCityprrq(String code_prrq, String code_city) {
/* 1164 */     logger.debug("getNameMunicipioPrrq code_prrq" + code_prrq + "|| code_city " + code_city);
/* 1165 */     String name = null;
/* 1166 */     List<CityTO> getPkgCityTO = ScheduledDataUpdater.getPkgCities();
/*      */     
/* 1168 */     for (CityTO obj : getPkgCityTO) {
/* 1169 */       if (obj.getStateCode().equals(code_prrq) && obj.getCityId() == Integer.parseInt(code_city)) {
/* 1170 */         name = obj.getCityName();
/* 1171 */         return name;
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 1176 */     return name;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public RetrieveLinesResponseGSMTO subscriberFactoryManual(String URL_WSDL, String tranID, String ssn, String ssnType) throws SOAPException {
/* 1186 */     RetrieveLinesResponseGSMTO retrieveLinesResponseGSMTO = new RetrieveLinesResponseGSMTO();
/* 1187 */     logger.info("|PerfilClienteController subscriberFactoryManual put  " + ssn + "," + ssnType);
/* 1188 */     String SERVICE_NAME = "subscriber";
/* 1189 */     String PORT_NAME = "subscriber";
/* 1190 */     String NAMESPACE = "http://to.ws.cia.gdis.movilnet.com.ve/types/";
/* 1191 */     QName serviceName = new QName(NAMESPACE, SERVICE_NAME);
/* 1192 */     QName portName = new QName(NAMESPACE, PORT_NAME);
/* 1193 */     Service service = Service.create(serviceName);
/* 1194 */     service.addPort(portName, "http://schemas.xmlsoap.org/wsdl/soap/http", URL_WSDL);
/*      */     
/* 1196 */     Dispatch<SOAPMessage> dispatch = service.createDispatch(portName, SOAPMessage.class, Service.Mode.MESSAGE);
/*      */ 
/*      */     
/* 1199 */     MessageFactory messageFactory = MessageFactory.newInstance();
/* 1200 */     SOAPMessage message = messageFactory.createMessage();
/*      */ 
/*      */     
/* 1203 */     SOAPBody body = message.getSOAPBody();
/*      */ 
/*      */     
/* 1206 */     QName methodName = new QName("http://service.subscriber.base.ws.prepay.cia.gdis.movilnet.com.ve/", "retrieveLinesByCIGSM");
/* 1207 */     SOAPBodyElement method = body.addBodyElement(methodName);
/*      */ 
/*      */     
/* 1210 */     QName requestName = new QName("http://service.subscriber.base.ws.prepay.cia.gdis.movilnet.com.ve/", "retrieveLinesbyCIRequestTO");
/* 1211 */     SOAPElement requestElement = method.addChildElement(requestName);
/*      */ 
/*      */     
/* 1214 */     QName applicationClientName = new QName("http://service.subscriber.base.ws.prepay.cia.gdis.movilnet.com.ve/", "applicationClient");
/* 1215 */     SOAPElement applicationClientElement = requestElement.addChildElement(applicationClientName);
/*      */ 
/*      */ 
/*      */     
/* 1219 */     applicationClientElement.addChildElement("application").addTextNode("AUTOGE_WEB");
/* 1220 */     applicationClientElement.addChildElement("code").addTextNode("AUTOGE_WEB");
/*      */ 
/*      */     
/* 1223 */     QName securityName = new QName("http://service.subscriber.base.ws.prepay.cia.gdis.movilnet.com.ve/", "security");
/* 1224 */     SOAPElement securityElement = requestElement.addChildElement(securityName);
/*      */ 
/*      */     
/* 1227 */     securityElement.addChildElement("ccwsPassword").addTextNode(this.prop.getSUBccwsPassword());
/* 1228 */     securityElement.addChildElement("ccwsUser").addTextNode(this.prop.getSUBccwsUser());
/* 1229 */     requestElement.addChildElement("serviceProvider").addTextNode(this.prop.getSUBserviceProvider());
/* 1230 */     requestElement.addChildElement("technology").addTextNode(this.prop.getSUBtechnology());
/* 1231 */     requestElement.addChildElement("transactionId").addTextNode(tranID);
/* 1232 */     requestElement.addChildElement("ssn").addTextNode(ssn);
/* 1233 */     requestElement.addChildElement("ssnType").addTextNode(ssnType);
/* 1234 */     requestElement.addChildElement("userId").addTextNode(this.prop.getSUBuserId());
/*      */ 
/*      */     
/* 1237 */     SOAPMessage response = dispatch.invoke(message);
/*      */ 
/*      */ 
/*      */     
/* 1241 */     SOAPBody soapBody = response.getSOAPBody();
/*      */ 
/*      */ 
/*      */     
/* 1245 */     Iterator<?> bodyIterator = soapBody.getChildElements();
/* 1246 */     while (bodyIterator.hasNext()) {
/* 1247 */       SOAPElement bodyElement = (SOAPElement)bodyIterator.next();
/* 1248 */       if (bodyElement.getLocalName().equals("retrieveLinesByCIGSMResponse")) {
/* 1249 */         SOAPElement resultElement = bodyElement.getChildElements().next();
/* 1250 */         String str = resultElement.getAttribute("href");
/*      */       } 
/*      */       
/* 1253 */       if (bodyElement.getLocalName().equals("RetrieveLinesResponseGSMTO")) {
/* 1254 */         Iterator<?> retrieveLinesResponseGSMTOIterator = bodyElement.getChildElements();
/* 1255 */         while (retrieveLinesResponseGSMTOIterator.hasNext()) {
/* 1256 */           SOAPElement retrieveLinesResponseGSMTOElement = (SOAPElement)retrieveLinesResponseGSMTOIterator.next();
/* 1257 */           String elementName = retrieveLinesResponseGSMTOElement.getLocalName();
/* 1258 */           String elementValue = retrieveLinesResponseGSMTOElement.getValue();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 1264 */           if (elementName.equalsIgnoreCase("executionTime")) {
/* 1265 */             retrieveLinesResponseGSMTO.setExecutionTime(Long.parseLong(elementValue)); continue;
/* 1266 */           }  if (elementName.equalsIgnoreCase("origin")) {
/* 1267 */             retrieveLinesResponseGSMTO.setOrigin(elementValue); continue;
/*      */           } 
/* 1269 */           if (elementName.equalsIgnoreCase("responseCode")) {
/* 1270 */             retrieveLinesResponseGSMTO.setResponseCode(elementValue); continue;
/*      */           } 
/* 1272 */           if (elementName.equalsIgnoreCase("responseDescription")) {
/* 1273 */             retrieveLinesResponseGSMTO.setResponseDescription(elementValue); continue;
/*      */           } 
/* 1275 */           if (elementName.equalsIgnoreCase("responseMessage")) {
/* 1276 */             retrieveLinesResponseGSMTO.setResponseMessage(elementValue); continue;
/*      */           } 
/* 1278 */           if (elementName.equalsIgnoreCase("transactionId")) {
/* 1279 */             retrieveLinesResponseGSMTO.setTransactionId(elementValue);
/*      */             continue;
/*      */           } 
/* 1282 */           if (elementName.equalsIgnoreCase("ocurrenciaTotal")) {
/* 1283 */             retrieveLinesResponseGSMTO.setOcurrenciaTotal(Integer.parseInt(elementValue));
/*      */           }
/*      */         } 
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1292 */     SOAPBody soapBody2 = response.getSOAPBody();
/* 1293 */     int i = 0;
/*      */     
/* 1295 */     ResultGSMTO[] resultado = new ResultGSMTO[retrieveLinesResponseGSMTO.getOcurrenciaTotal()];
/*      */ 
/*      */     
/* 1298 */     Iterator<?> bodyIterator2 = soapBody2.getChildElements();
/* 1299 */     while (bodyIterator2.hasNext()) {
/* 1300 */       SOAPElement bodyElement = (SOAPElement)bodyIterator2.next();
/* 1301 */       if (bodyElement.getLocalName().equals("ArrayOfResultGSMTO")) {
/* 1302 */         Iterator<?> arrayOfResultGSMTOIterator = bodyElement.getChildElements();
/* 1303 */         while (arrayOfResultGSMTOIterator.hasNext()) {
/* 1304 */           SOAPElement arrayOfResultGSMTOElement = (SOAPElement)arrayOfResultGSMTOIterator.next();
/* 1305 */           String href = arrayOfResultGSMTOElement.getAttribute("href");
/* 1306 */           href = href.replace("#", "");
/*      */           
/* 1308 */           Iterator<?> allBodyIterator = soapBody2.getChildElements();
/*      */ 
/*      */           
/* 1311 */           while (allBodyIterator.hasNext()) {
/* 1312 */             ResultGSMTO resultGSMTO = new ResultGSMTO();
/*      */             
/* 1314 */             SOAPElement allBodyElement = (SOAPElement)allBodyIterator.next();
/*      */             
/* 1316 */             if (allBodyElement.getAttribute("id").equals(href)) {
/*      */ 
/*      */ 
/*      */ 
/*      */               
/* 1321 */               Iterator<?> resultGSMTOIterator = allBodyElement.getChildElements();
/* 1322 */               while (resultGSMTOIterator.hasNext()) {
/*      */                 
/* 1324 */                 SOAPElement resultGSMTOElement = (SOAPElement)resultGSMTOIterator.next();
/* 1325 */                 String elementName = resultGSMTOElement.getLocalName();
/* 1326 */                 String elementValue = resultGSMTOElement.getValue();
/*      */ 
/*      */ 
/*      */ 
/*      */                 
/* 1331 */                 if (elementName.equalsIgnoreCase("cosName")) {
/* 1332 */                   resultGSMTO.setCosName(elementValue); continue;
/* 1333 */                 }  if (elementName.equalsIgnoreCase("fechaActivacion")) {
/* 1334 */                   resultGSMTO.setFechaActivacion(elementValue); continue;
/*      */                 } 
/* 1336 */                 if (elementName.equalsIgnoreCase("generico1")) {
/* 1337 */                   resultGSMTO.setGenerico1(elementValue); continue;
/*      */                 } 
/* 1339 */                 if (elementName.equalsIgnoreCase("nombreSubscriptor")) {
/* 1340 */                   resultGSMTO.setNombreSubscriptor(elementValue); continue;
/*      */                 } 
/* 1342 */                 if (elementName.equalsIgnoreCase("statusLinea")) {
/* 1343 */                   resultGSMTO.setStatusLinea(elementValue); continue;
/*      */                 } 
/* 1345 */                 if (elementName.equalsIgnoreCase("subscriberId")) {
/* 1346 */                   resultGSMTO.setSubscriberId(Long.parseLong(elementValue));
/*      */                   
/* 1348 */                   resultado[i] = resultGSMTO;
/* 1349 */                   i++;
/* 1350 */                   logger.debug(i + " subscriberFactoryManual operaciones : " + i);
/*      */                 } 
/*      */               } 
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1363 */     retrieveLinesResponseGSMTO.setResultado(resultado);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1377 */     return retrieveLinesResponseGSMTO;
/*      */   }
/*      */ }


/* Location:              C:\Users\aruizc01\Desktop\api-movilnet.war!\WEB-INF\classes\ve\com\movilnet\api\perfilcliente\PerfilClienteController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */