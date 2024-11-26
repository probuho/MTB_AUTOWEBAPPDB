/*     */ package WEB-INF.classes.ve.com.movilnet.api.config;
/*     */ @Component
/*     */ public class ConfProperties {
/*     */   @Value("${movilnet.app.jwtSecret}")
/*     */   private String jwtSecret;
/*     */   @Value("${path_file_sdp}")
/*     */   private String path_file_sdp;
/*     */   @Value("${variable.codigoOK}")
/*     */   private String codigoOK;
/*     */   @Value("${mensaje.codigoOK}")
/*     */   private String mensajeCodigoOK;
/*  12 */   private static final Logger logger = Logger.getLogger(ve.com.movilnet.api.config.ConfProperties.class); @Value("${url.WsAuthenticateStub}") private String wsAuthenticateStub; @Value("${state_Bt}") private String state_Bt; @Value("${url.buscarTelefonoBp}") private String buscarTelefonoBp; @Value("${seguridadBt.usuario}") private String seguridadBtUsuario; @Value("${seguridadBt.clave}") private String seguridadBtClave; @Value("${aplicacionBt.nombre}") private String aplicacionBtNombre; @Value("${aplicacionBt.tipoAplicacion}") private String aplicacionBtTipoAplicacion; @Value("${aplicacionBt.codigo}") private String aplicacionBtCodigo; @Value("${proveedorBt}") private String ProveedorBt; @Value("${nodoBt}") private String NodoBt; @Value("${tecnologiaBt}")
/*     */   private String TecnologiaBt; @Value("${idUsuarioBt}")
/*     */   private String IdUsuarioBt; @Value("${state_Ss}")
/*     */   private String state_Ss; @Value("${url.ws_solicitudSaldoBp}")
/*  16 */   private String ws_solicitudSaldoBp; public static void main(String[] args) { long time1 = 0L;
/*  17 */     String transaccionId = "Autogestion--" + time1;
/*     */     
/*  19 */     time1 = System.currentTimeMillis();
/*     */     
/*  21 */     String origen = "ConfProperties";
/*  22 */     String tipoTransaccion = "APP";
/*     */ 
/*     */     
/*     */     try {
/*  26 */       DOMConfigurator.configure("log4j.xml");
/*     */ 
/*     */       
/*  29 */       logger.info("ConfProperties.main cargar la configuracion del log4 : " + origen + transaccionId + tipoTransaccion + "AutogestionGSM" + String.valueOf(time1 - System.currentTimeMillis()));
/*     */     }
/*  31 */     catch (Exception e) {
/*     */ 
/*     */       
/*  34 */       logger.error("contextInitialized:Error al cargar la configuracion del log4j", e);
/*     */     } 
/*  36 */     SpringApplication.run(ve.com.movilnet.api.config.ConfProperties.class, args); }
/*     */   
/*     */   @Value("${seguridadSs.usuario}")
/*     */   private String seguridadSsUsuario; @Value("${string1}")
/*     */   private String string1; @Value("${seguridadSs.clave}")
/*     */   private String seguridadSsClave; @Value("${aplicacionSs.nombre}")
/*     */   private String aplicacionSsNombre; @Value("${aplicacionSs.tipoAplicacion}")
/*     */   private String aplicacionSsTipoAplicacion; @Value("${aplicacionSs.codigo}")
/*     */   private String aplicacionSsCodigo; @Value("${proveedorSs}")
/*     */   private String ProveedorSs; @Value("${nodoSs}")
/*     */   private String NodoSs; @Value("${tecnologiaSs}")
/*     */   private String TecnologiaSs; @Value("${idUsuarioSs}")
/*     */   private String IdUsuarioSs; @Value("${aplicaciontds.application}")
/*     */   private String aplicaciontds_application;
/*     */   @Value("${aplicaciontds.codigo}")
/*     */   private String aplicaciontds_code;
/*     */   @Value("${state_rtb}")
/*     */   private String state_rtb;
/*     */   @Value("${seguridadtds.usuario}")
/*     */   private String seguridadtds;
/*     */   @Value("${seguridadtds.clave}")
/*     */   private String seguridadtds_cv;
/*     */   @Value("${state_Cp}")
/*     */   private String state_Cp;
/*     */   @Value("${url.cambiarPlanCp}")
/*     */   private String cambiarPlanCp;
/*     */   @Value("${WSseguridadCp.usuario}")
/*     */   private String WSseguridadCp_usuario;
/*     */   @Value("${WSseguridadCp.contrasena}")
/*     */   private String WSseguridadCp_contrasena;
/*     */   @Value("${entradaCp.proveedor}")
/*     */   private String entradaCpproveedor;
/*     */   @Value("${entradaCp.nodo}")
/*     */   private String entradaCpnodo;
/*     */   @Value("${entradaCp.tecnologia}")
/*     */   private String entradaCptecnologia;
/*     */   @Value("${entradaCp.idUsuario}")
/*     */   private String entradaCpidUsuario;
/*     */   @Value("${seguridadCp.usuario}")
/*     */   private String seguridadCpusuario;
/*     */   @Value("${seguridadCp.clave}")
/*     */   private String seguridadCpclave;
/*     */   @Value("${aplicacionCp.nombre}")
/*     */   private String aplicacionCpnombre;
/*     */   @Value("${aplicacionCp.tipoAplicacion}")
/*     */   private String aplicacionCptipoAplicacion;
/*     */   @Value("${aplicacionCp.codigo}")
/*     */   private String aplicacionCpcodigo;
/*     */   @Value("${state_AD}")
/*     */   private String state_AD;
/*     */   @Value("${url.ActualizaCliente}")
/*     */   private String actualizaCliente;
/*     */   @Value("${WSseguridadAD.usuario}")
/*     */   private String WSseguridadAD_usuario;
/*     */   @Value("${WSseguridadAD.contrasena}")
/*     */   private String WSseguridadAD_contrasena;
/*     */   @Value("${agenteAD.idAgente}")
/*     */   private String agenteADidAgente;
/*     */   @Value("${agenteAD.descripcion}")
/*     */   private String agenteADdescripcion;
/*     */   @Value("${userAutenAD.cedula}")
/*     */   private String userAutenADcedula;
/*     */   @Value("${userAutenAD.nombre}")
/*     */   private String userAutenADnombre;
/*     */   @Value("${userAutenAD.apellido}")
/*     */   private String userAutenADapellido;
/*     */   @Value("${userAutenAD.telefono}")
/*     */   private String userAutenADtelefono;
/*     */   @Value("${aplicacionAD.nombre}")
/*     */   private String aplicacionADnombre;
/*     */   @Value("${aplicacionAD.tipoAplicacion}")
/*     */   private String aplicacionADtipoAplicacion;
/*     */   @Value("${aplicacionAD.codigo}")
/*     */   private String aplicacionADcodigo;
/*     */   @Value("${seguridadAD.usuario}")
/*     */   private String seguridadADusuario;
/*     */   @Value("${seguridadAD.clave}")
/*     */   private String seguridadADclave;
/*     */   @Value("${entradaAD.proveedor}")
/*     */   private String entradaADproveedor;
/*     */   @Value("${entradaAD.tecnologia}")
/*     */   private String entradaADtecnologia;
/*     */   @Value("${entradaAD.idUsuario}")
/*     */   private String entradaADidUsuario;
/*     */   @Value("${state_SUB}")
/*     */   private String state_SUB;
/*     */   @Value("${url.subscriber}")
/*     */   private String subscriber;
/*     */   @Value("${SUBapplication}")
/*     */   private String SUBapplication;
/*     */   @Value("${SUBcode}")
/*     */   private String SUBcode;
/*     */   @Value("${SUBccwsPassword}")
/*     */   private String SUBccwsPassword;
/*     */   @Value("${SUBccwsUser}")
/*     */   private String SUBccwsUser;
/*     */   @Value("${SUBserviceProvider}")
/*     */   private String SUBserviceProvider;
/*     */   @Value("${SUBtechnology}")
/*     */   private String SUBtechnology;
/*     */   @Value("${SUBuserId}")
/*     */   private String SUBuserId;
/*     */   
/*     */   public String getBuscarTelefonoBp() {
/* 140 */     return this.buscarTelefonoBp;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getWs_solicitudSaldoBp() {
/* 272 */     return this.ws_solicitudSaldoBp;
/*     */   }
/*     */   
/*     */   public String getCodigoOK() {
/* 276 */     return this.codigoOK;
/*     */   }
/*     */   
/*     */   public void setCodigoOK(String codigoOK) {
/* 280 */     this.codigoOK = codigoOK;
/*     */   }
/*     */   
/*     */   public String getMensajeCodigoOK() {
/* 284 */     return this.mensajeCodigoOK;
/*     */   }
/*     */   
/*     */   public void setMensajeCodigoOK(String mensajeCodigoOK) {
/* 288 */     this.mensajeCodigoOK = mensajeCodigoOK;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getWsAuthenticateStub() {
/* 293 */     return this.wsAuthenticateStub;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getState_Bt() {
/* 298 */     return this.state_Bt;
/*     */   }
/*     */   
/*     */   public String getSeguridadBtUsuario() {
/* 302 */     return this.seguridadBtUsuario;
/*     */   }
/*     */   
/*     */   public String getSeguridadBtClave() {
/* 306 */     return this.seguridadBtClave;
/*     */   }
/*     */   
/*     */   public String getAplicacionBtNombre() {
/* 310 */     return this.aplicacionBtNombre;
/*     */   }
/*     */   
/*     */   public String getAplicacionBtTipoAplicacion() {
/* 314 */     return this.aplicacionBtTipoAplicacion;
/*     */   }
/*     */   
/*     */   public String getAplicacionBtCodigo() {
/* 318 */     return this.aplicacionBtCodigo;
/*     */   }
/*     */   
/*     */   public String getProveedorBt() {
/* 322 */     return this.ProveedorBt;
/*     */   }
/*     */   
/*     */   public String getNodoBt() {
/* 326 */     return this.NodoBt;
/*     */   }
/*     */   
/*     */   public String getTecnologiaBt() {
/* 330 */     return this.TecnologiaBt;
/*     */   }
/*     */   
/*     */   public String getIdUsuarioBt() {
/* 334 */     return this.IdUsuarioBt;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getState_Ss() {
/* 339 */     return this.state_Ss;
/*     */   }
/*     */   
/*     */   public String getString1() {
/* 343 */     return this.string1;
/*     */   }
/*     */   
/*     */   public String getSeguridadSsUsuario() {
/* 347 */     return this.seguridadSsUsuario;
/*     */   }
/*     */   
/*     */   public String getSeguridadSsClave() {
/* 351 */     return this.seguridadSsClave;
/*     */   }
/*     */   
/*     */   public String getAplicacionSsNombre() {
/* 355 */     return this.aplicacionSsNombre;
/*     */   }
/*     */   
/*     */   public String getAplicacionSsTipoAplicacion() {
/* 359 */     return this.aplicacionSsTipoAplicacion;
/*     */   }
/*     */   
/*     */   public String getAplicacionSsCodigo() {
/* 363 */     return this.aplicacionSsCodigo;
/*     */   }
/*     */   
/*     */   public String getProveedorSs() {
/* 367 */     return this.ProveedorSs;
/*     */   }
/*     */   
/*     */   public String getNodoSs() {
/* 371 */     return this.NodoSs;
/*     */   }
/*     */   
/*     */   public String getTecnologiaSs() {
/* 375 */     return this.TecnologiaSs;
/*     */   }
/*     */   
/*     */   public String getIdUsuarioSs() {
/* 379 */     return this.IdUsuarioSs;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getAplicaciontds_application() {
/* 384 */     return this.aplicaciontds_application;
/*     */   }
/*     */   
/*     */   public String getAplicaciontds_code() {
/* 388 */     return this.aplicaciontds_code;
/*     */   }
/*     */   
/*     */   public String getState_rtb() {
/* 392 */     return this.state_rtb;
/*     */   }
/*     */   
/*     */   public String getSeguridadtds() {
/* 396 */     return this.seguridadtds;
/*     */   }
/*     */   
/*     */   public String getSeguridadtds_cv() {
/* 400 */     return this.seguridadtds_cv;
/*     */   }
/*     */   
/*     */   public String getPath_file_sdp() {
/* 404 */     return this.path_file_sdp;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getState_Cp() {
/* 412 */     return this.state_Cp;
/*     */   }
/*     */   
/*     */   public void setState_Cp(String state_Cp) {
/* 416 */     this.state_Cp = state_Cp;
/*     */   }
/*     */   
/*     */   public String getCambiarPlanCp() {
/* 420 */     return this.cambiarPlanCp;
/*     */   }
/*     */   
/*     */   public void setCambiarPlanCp(String cambiarPlanCp) {
/* 424 */     this.cambiarPlanCp = cambiarPlanCp;
/*     */   }
/*     */   
/*     */   public String getEntradaCpproveedor() {
/* 428 */     return this.entradaCpproveedor;
/*     */   }
/*     */   
/*     */   public void setEntradaCpproveedor(String entradaCpproveedor) {
/* 432 */     this.entradaCpproveedor = entradaCpproveedor;
/*     */   }
/*     */   
/*     */   public String getEntradaCpnodo() {
/* 436 */     return this.entradaCpnodo;
/*     */   }
/*     */   
/*     */   public void setEntradaCpnodo(String entradaCpnodo) {
/* 440 */     this.entradaCpnodo = entradaCpnodo;
/*     */   }
/*     */   
/*     */   public String getEntradaCptecnologia() {
/* 444 */     return this.entradaCptecnologia;
/*     */   }
/*     */   
/*     */   public void setEntradaCptecnologia(String entradaCptecnologia) {
/* 448 */     this.entradaCptecnologia = entradaCptecnologia;
/*     */   }
/*     */   
/*     */   public String getEntradaCpidUsuario() {
/* 452 */     return this.entradaCpidUsuario;
/*     */   }
/*     */   
/*     */   public void setEntradaCpidUsuario(String entradaCpidUsuario) {
/* 456 */     this.entradaCpidUsuario = entradaCpidUsuario;
/*     */   }
/*     */   
/*     */   public String getSeguridadCpusuario() {
/* 460 */     return this.seguridadCpusuario;
/*     */   }
/*     */   
/*     */   public void setSeguridadCpusuario(String seguridadCpusuario) {
/* 464 */     this.seguridadCpusuario = seguridadCpusuario;
/*     */   }
/*     */   
/*     */   public String getSeguridadCpclave() {
/* 468 */     return this.seguridadCpclave;
/*     */   }
/*     */   
/*     */   public void setSeguridadCpclave(String seguridadCpclave) {
/* 472 */     this.seguridadCpclave = seguridadCpclave;
/*     */   }
/*     */   
/*     */   public String getAplicacionCpnombre() {
/* 476 */     return this.aplicacionCpnombre;
/*     */   }
/*     */   
/*     */   public void setAplicacionCpnombre(String aplicacionCpnombre) {
/* 480 */     this.aplicacionCpnombre = aplicacionCpnombre;
/*     */   }
/*     */   
/*     */   public String getAplicacionCptipoAplicacion() {
/* 484 */     return this.aplicacionCptipoAplicacion;
/*     */   }
/*     */   
/*     */   public void setAplicacionCptipoAplicacion(String aplicacionCptipoAplicacion) {
/* 488 */     this.aplicacionCptipoAplicacion = aplicacionCptipoAplicacion;
/*     */   }
/*     */   
/*     */   public String getAplicacionCpcodigo() {
/* 492 */     return this.aplicacionCpcodigo;
/*     */   }
/*     */   
/*     */   public void setAplicacionCpcodigo(String aplicacionCpcodigo) {
/* 496 */     this.aplicacionCpcodigo = aplicacionCpcodigo;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getState_AD() {
/* 501 */     return this.state_AD;
/*     */   }
/*     */   
/*     */   public void setState_AD(String state_AD) {
/* 505 */     this.state_AD = state_AD;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getWSseguridadAD_usuario() {
/* 512 */     return this.WSseguridadAD_usuario;
/*     */   }
/*     */   
/*     */   public void setWSseguridadAD_usuario(String WSseguridadAD_usuario) {
/* 516 */     this.WSseguridadAD_usuario = WSseguridadAD_usuario;
/*     */   }
/*     */   
/*     */   public String getWSseguridadAD_contrasena() {
/* 520 */     return this.WSseguridadAD_contrasena;
/*     */   }
/*     */   
/*     */   public void setWSseguridadAD_contrasena(String WSseguridadAD_contrasena) {
/* 524 */     this.WSseguridadAD_contrasena = WSseguridadAD_contrasena;
/*     */   }
/*     */   
/*     */   public String getAgenteADidAgente() {
/* 528 */     return this.agenteADidAgente;
/*     */   }
/*     */   
/*     */   public void setAgenteADidAgente(String agenteADidAgente) {
/* 532 */     this.agenteADidAgente = agenteADidAgente;
/*     */   }
/*     */   
/*     */   public String getAgenteADdescripcion() {
/* 536 */     return this.agenteADdescripcion;
/*     */   }
/*     */   
/*     */   public void setAgenteADdescripcion(String agenteADdescripcion) {
/* 540 */     this.agenteADdescripcion = agenteADdescripcion;
/*     */   }
/*     */   
/*     */   public String getUserAutenADcedula() {
/* 544 */     return this.userAutenADcedula;
/*     */   }
/*     */   
/*     */   public void setUserAutenADcedula(String userAutenADcedula) {
/* 548 */     this.userAutenADcedula = userAutenADcedula;
/*     */   }
/*     */   
/*     */   public String getUserAutenADnombre() {
/* 552 */     return this.userAutenADnombre;
/*     */   }
/*     */   
/*     */   public void setUserAutenADnombre(String userAutenADnombre) {
/* 556 */     this.userAutenADnombre = userAutenADnombre;
/*     */   }
/*     */   
/*     */   public String getUserAutenADapellido() {
/* 560 */     return this.userAutenADapellido;
/*     */   }
/*     */   
/*     */   public void setUserAutenADapellido(String userAutenADapellido) {
/* 564 */     this.userAutenADapellido = userAutenADapellido;
/*     */   }
/*     */   
/*     */   public String getUserAutenADtelefono() {
/* 568 */     return this.userAutenADtelefono;
/*     */   }
/*     */   
/*     */   public void setUserAutenADtelefono(String userAutenADtelefono) {
/* 572 */     this.userAutenADtelefono = userAutenADtelefono;
/*     */   }
/*     */   
/*     */   public String getActualizaCliente() {
/* 576 */     return this.actualizaCliente;
/*     */   }
/*     */   
/*     */   public void setActualizaCliente(String actualizaCliente) {
/* 580 */     this.actualizaCliente = actualizaCliente;
/*     */   }
/*     */   
/*     */   public String getAplicacionADnombre() {
/* 584 */     return this.aplicacionADnombre;
/*     */   }
/*     */   
/*     */   public void setAplicacionADnombre(String aplicacionADnombre) {
/* 588 */     this.aplicacionADnombre = aplicacionADnombre;
/*     */   }
/*     */   
/*     */   public String getAplicacionADtipoAplicacion() {
/* 592 */     return this.aplicacionADtipoAplicacion;
/*     */   }
/*     */   
/*     */   public void setAplicacionADtipoAplicacion(String aplicacionADtipoAplicacion) {
/* 596 */     this.aplicacionADtipoAplicacion = aplicacionADtipoAplicacion;
/*     */   }
/*     */   
/*     */   public String getAplicacionADcodigo() {
/* 600 */     return this.aplicacionADcodigo;
/*     */   }
/*     */   
/*     */   public void setAplicacionADcodigo(String aplicacionADcodigo) {
/* 604 */     this.aplicacionADcodigo = aplicacionADcodigo;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getSeguridadADusuario() {
/* 609 */     return this.seguridadADusuario;
/*     */   }
/*     */   
/*     */   public void setSeguridadADusuario(String seguridadADusuario) {
/* 613 */     this.seguridadADusuario = seguridadADusuario;
/*     */   }
/*     */   
/*     */   public String getSeguridadADclave() {
/* 617 */     return this.seguridadADclave;
/*     */   }
/*     */   
/*     */   public void setSeguridadADclave(String seguridadADclave) {
/* 621 */     this.seguridadADclave = seguridadADclave;
/*     */   }
/*     */   
/*     */   public String getEntradaADproveedor() {
/* 625 */     return this.entradaADproveedor;
/*     */   }
/*     */   
/*     */   public void setEntradaADproveedor(String entradaADproveedor) {
/* 629 */     this.entradaADproveedor = entradaADproveedor;
/*     */   }
/*     */   
/*     */   public String getEntradaADtecnologia() {
/* 633 */     return this.entradaADtecnologia;
/*     */   }
/*     */   
/*     */   public void setEntradaADtecnologia(String entradaADtecnologia) {
/* 637 */     this.entradaADtecnologia = entradaADtecnologia;
/*     */   }
/*     */   
/*     */   public String getEntradaADidUsuario() {
/* 641 */     return this.entradaADidUsuario;
/*     */   }
/*     */   
/*     */   public void setEntradaADidUsuario(String entradaADidUsuario) {
/* 645 */     this.entradaADidUsuario = entradaADidUsuario;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getJwtSecret() {
/* 650 */     return this.jwtSecret;
/*     */   }
/*     */   
/*     */   public void setJwtSecret(String jwtSecret) {
/* 654 */     this.jwtSecret = jwtSecret;
/*     */   }
/*     */   
/*     */   public String getWSseguridadCp_usuario() {
/* 658 */     return this.WSseguridadCp_usuario;
/*     */   }
/*     */   
/*     */   public void setWSseguridadCp_usuario(String WSseguridadCp_usuario) {
/* 662 */     this.WSseguridadCp_usuario = WSseguridadCp_usuario;
/*     */   }
/*     */   
/*     */   public String getWSseguridadCp_contrasena() {
/* 666 */     return this.WSseguridadCp_contrasena;
/*     */   }
/*     */   
/*     */   public void setWSseguridadCp_contrasena(String WSseguridadCp_contrasena) {
/* 670 */     this.WSseguridadCp_contrasena = WSseguridadCp_contrasena;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getState_SUB() {
/* 676 */     return this.state_SUB;
/*     */   }
/*     */   
/*     */   public void setState_SUB(String state_SUB) {
/* 680 */     this.state_SUB = state_SUB;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getSubscriber() {
/* 685 */     return this.subscriber;
/*     */   }
/*     */   
/*     */   public void setSubscriber(String subscriber) {
/* 689 */     this.subscriber = subscriber;
/*     */   }
/*     */   
/*     */   public String getSUBapplication() {
/* 693 */     return this.SUBapplication;
/*     */   }
/*     */   
/*     */   public void setSUBapplication(String SUBapplication) {
/* 697 */     this.SUBapplication = SUBapplication;
/*     */   }
/*     */   
/*     */   public String getSUBcode() {
/* 701 */     return this.SUBcode;
/*     */   }
/*     */   
/*     */   public void setSUBcode(String SUBcode) {
/* 705 */     this.SUBcode = SUBcode;
/*     */   }
/*     */   
/*     */   public String getSUBccwsPassword() {
/* 709 */     return this.SUBccwsPassword;
/*     */   }
/*     */   
/*     */   public void setSUBccwsPassword(String SUBccwsPassword) {
/* 713 */     this.SUBccwsPassword = SUBccwsPassword;
/*     */   }
/*     */   
/*     */   public String getSUBccwsUser() {
/* 717 */     return this.SUBccwsUser;
/*     */   }
/*     */   
/*     */   public void setSUBccwsUser(String SUBccwsUser) {
/* 721 */     this.SUBccwsUser = SUBccwsUser;
/*     */   }
/*     */   
/*     */   public String getSUBserviceProvider() {
/* 725 */     return this.SUBserviceProvider;
/*     */   }
/*     */   
/*     */   public void setSUBserviceProvider(String SUBserviceProvider) {
/* 729 */     this.SUBserviceProvider = SUBserviceProvider;
/*     */   }
/*     */   
/*     */   public String getSUBtechnology() {
/* 733 */     return this.SUBtechnology;
/*     */   }
/*     */   
/*     */   public void setSUBtechnology(String SUBtechnology) {
/* 737 */     this.SUBtechnology = SUBtechnology;
/*     */   }
/*     */   
/*     */   public String getSUBuserId() {
/* 741 */     return this.SUBuserId;
/*     */   }
/*     */   
/*     */   public void setSUBuserId(String SUBuserId) {
/* 745 */     this.SUBuserId = SUBuserId;
/*     */   }
/*     */ }


/* Location:              C:\Users\aruizc01\Desktop\api-movilnet.war!\WEB-INF\classes\ve\com\movilnet\api\config\ConfProperties.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */