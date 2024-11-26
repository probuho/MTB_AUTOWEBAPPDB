/*     */ package WEB-INF.classes.ve.com.movilnet.api.repository;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.apache.log4j.Logger;
/*     */ import org.apache.log4j.Priority;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.dao.DataAccessException;
/*     */ import org.springframework.jdbc.core.JdbcTemplate;
/*     */ import org.springframework.jdbc.core.RowMapper;
/*     */ import org.springframework.jdbc.core.SqlOutParameter;
/*     */ import org.springframework.jdbc.core.SqlParameter;
/*     */ import org.springframework.jdbc.core.simple.SimpleJdbcCall;
/*     */ import org.springframework.stereotype.Repository;
/*     */ import ve.com.movilnet.api.repository.ViewPlane;
/*     */ import ve.com.movilnet.api.repository.ViewPlanesMapper;
/*     */ import ve.com.movilnet.api.repository.ViewServicios;
/*     */ import ve.com.movilnet.api.repository.ViewServiciosMapper;
/*     */ import ve.com.movilnet.api.service.util.Operaciones;
/*     */ import ve.com.movilnet.commons2.servicios.log4j.MovilnetLevel;
/*     */ import ve.com.movilnet.gdis.cia.ws.to.commons.CityTO;
/*     */ import ve.com.movilnet.gdis.cia.ws.to.commons.MunicipalitieTO;
/*     */ import ve.com.movilnet.gdis.cia.ws.to.commons.ParisheTO;
/*     */ import ve.com.movilnet.gdis.cia.ws.to.commons.SectorTO;
/*     */ import ve.com.movilnet.gdis.cia.ws.to.commons.StateTO;
/*     */ 
/*     */ @Repository
/*     */ public class DataRepository {
/*     */   private JdbcTemplate jdbcTemplate;
/*  32 */   static final Logger logger = Logger.getLogger(ve.com.movilnet.api.repository.DataRepository.class);
/*  33 */   Operaciones op = new Operaciones();
/*     */ 
/*     */   
/*     */   @Autowired
/*     */   public DataRepository(JdbcTemplate jdbcTemplate) {
/*  38 */     this.jdbcTemplate = jdbcTemplate;
/*  39 */     logger.debug("DataRepository autowired jdbcTemplate ");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DataRepository() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<ViewPlane> buscarTodos(String valor) {
/*  57 */     List<ViewPlane> lista = null;
/*     */     
/*  59 */     long t1 = System.currentTimeMillis();
/*  60 */     String origen = "DataRepository.buscarTodosCp";
/*  61 */     String tipoTransaccion = "APP";
/*     */ 
/*     */     
/*     */     try {
/*  65 */       logger.info("DataRepository.buscarTodos() PlanOfertaResponse ");
/*     */       
/*  67 */       lista = this.jdbcTemplate.query("SELECT * FROM SYM_view_planesa ", (RowMapper)new ViewPlanesMapper());
/*     */       
/*  69 */       logger.info("DataRepository.SYM_view_planesa|" + this.op.tiempoDeEjecucion(t1, "RA2008-1"));
/*     */     }
/*  71 */     catch (DataAccessException e) {
/*  72 */       logger.warn("DataRepository.SYM_view_planesa Error al ejecutar la consulta SQL|RA4011");
/*  73 */       logger.error("Error al ejecutar la consulta SQL: ", (Throwable)e);
/*     */     } 
/*  75 */     return lista;
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
/*     */   public List<ViewPlane> buscarTodosCp(String PLAN) {
/*  88 */     List<ViewPlane> lista = null;
/*     */     
/*  90 */     long t1 = System.currentTimeMillis();
/*  91 */     String origen = "DataRepository.buscarTodosCp", tipoTransaccion = "APP";
/*     */ 
/*     */     
/*     */     try {
/*  95 */       if (logger.isEnabledFor((Priority)MovilnetLevel.PERF)) {
/*  96 */         logger.log((Priority)MovilnetLevel.PERF, new String[] { "Desempeño", origen, "transaccionId", "tipoTransaccion", "METHOD", "AtencionEnLineaSn", "--", this.op.tiempoDeEjecucion(t1, "RA2010-1") });
/*     */       }
/*  98 */       if (logger.isEnabledFor((Priority)MovilnetLevel.INFO)) {
/*  99 */         logger.log((Priority)MovilnetLevel.INFO, new String[] { "Auditoria", origen, "transaccionId", "tipoTransaccion", "METHOD", "AtencionEnLineaSn", "--", this.op.tiempoDeEjecucion(t1, "RA2010-1") });
/*     */       }
/* 101 */       if (logger.isEnabledFor((Priority)MovilnetLevel.STATS)) {
/* 102 */         logger.log((Priority)MovilnetLevel.STATS, new String[] { "Auditoria", origen, "transaccionId", "tipoTransaccion", "METHOD", "AtencionEnLineaSn", "--", this.op.tiempoDeEjecucion(t1, "RA2010-1") });
/*     */       }
/*     */       
/* 105 */       logger.info("DataRepository.buscarTodosCp() PlanOfertaResponse  PLAN " + PLAN);
/* 106 */       logger.debug("sql: SELECT * FROM SYM_view_planesa vp where vp.cos_plan_name in (select COS_PLAN_NAME_NEW from SYM_VIEW_CAM t where t.cos_plan_name_old = ? ) " + PLAN);
/* 107 */       lista = this.jdbcTemplate.query("SELECT * FROM SYM_view_planesa vp where vp.cos_plan_name in (select COS_PLAN_NAME_NEW from SYM_VIEW_CAM t where t.cos_plan_name_old = ? ) ", new Object[] { PLAN }, (RowMapper)new ViewPlanesMapper());
/*     */       
/* 109 */       logger.info("DataRepository.SYM_view_planesa vp where vp.cos_plan_name|" + this.op.tiempoDeEjecucion(t1, "RA2010-1"));
/* 110 */     } catch (DataAccessException e) {
/* 111 */       logger.warn("DataRepository.buscarTodosCp Error al ejecutar la consulta SQL|RA4013");
/* 112 */       logger.error("Error al ejecutar la consulta SQL: ", (Throwable)e);
/*     */     } 
/* 114 */     return lista;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<ViewServicios> buscarTodosServ(String valor) {
/* 119 */     List<ViewServicios> lista = null;
/*     */     
/* 121 */     long t1 = System.currentTimeMillis();
/*     */     try {
/* 123 */       logger.info("DataRepository.buscarTodosServ() ServicioOfertaResponse ");
/*     */       
/* 125 */       lista = this.jdbcTemplate.query("SELECT * FROM SYM_view_servicea", (RowMapper)new ViewServiciosMapper());
/*     */       
/* 127 */       logger.info("DataRepository.SYM_view_servicea|" + this.op.tiempoDeEjecucion(t1, "RA2009-1"));
/*     */     }
/* 129 */     catch (DataAccessException e) {
/* 130 */       logger.warn("DataRepository.SYM_view_servicea Error al ejecutar la consulta SQL|RA4012");
/* 131 */       logger.error("Error al ejecutar la consulta SQL: ", (Throwable)e);
/*     */     } 
/* 133 */     return lista;
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
/*     */   public List<StateTO> getStates() {
/* 176 */     List<StateTO> listStates = new ArrayList<>();
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 182 */       SimpleJdbcCall jdbcCall = (new SimpleJdbcCall(this.jdbcTemplate)).withCatalogName("prepaypkg.address_and_name_data_pkg").withFunctionName("show_state").withoutProcedureColumnMetaDataAccess().declareParameters(new SqlParameter[] { (SqlParameter)new SqlOutParameter("result", 4), (SqlParameter)new SqlOutParameter("out_states_list", -10, (RowMapper)new Object(this)), (SqlParameter)new SqlOutParameter("out_cod_error", 12), (SqlParameter)new SqlOutParameter("out_mes_error", 12) });
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
/* 197 */       Map<String, Object> result = jdbcCall.execute(new HashMap<>());
/*     */       
/* 199 */       Number returnValue = (Number)result.get("return_value");
/* 200 */       listStates = (List<StateTO>)result.get("out_states_list");
/* 201 */       String codError = (String)result.get("out_cod_error");
/* 202 */       String mesError = (String)result.get("out_mes_error");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 212 */       logger.debug("Código de error getStates: " + codError);
/* 213 */       logger.debug("Mensaje de error getStates : " + mesError);
/*     */     }
/* 215 */     catch (Exception e) {
/* 216 */       logger.error("Ocurrió un error al obtener los estados: ", e);
/*     */     } 
/* 218 */     return listStates;
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
/*     */   public List<MunicipalitieTO> getMunicipios() {
/* 265 */     List<MunicipalitieTO> listMunicipios = new ArrayList<>();
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 271 */       SimpleJdbcCall jdbcCall = (new SimpleJdbcCall(this.jdbcTemplate)).withCatalogName("prepaypkg.address_and_name_data_pkg").withFunctionName("show_municipio").withoutProcedureColumnMetaDataAccess().declareParameters(new SqlParameter[] { (SqlParameter)new SqlOutParameter("result", 4), (SqlParameter)new SqlOutParameter("out_municipio_list", -10, (RowMapper)new Object(this)), (SqlParameter)new SqlOutParameter("out_cod_error", 12), (SqlParameter)new SqlOutParameter("out_mes_error", 12) });
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
/* 287 */       Map<String, Object> result = jdbcCall.execute(new HashMap<>());
/*     */       
/* 289 */       Number returnValue = (Number)result.get("return_value");
/* 290 */       listMunicipios = (List<MunicipalitieTO>)result.get("out_municipio_list");
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 295 */       String codError = (String)result.get("out_cod_error");
/* 296 */       String mesError = (String)result.get("out_mes_error");
/*     */ 
/*     */ 
/*     */       
/* 300 */       logger.debug("Código de error getMunicipios : " + codError);
/* 301 */       logger.debug("Mensaje de error getMunicipios : " + mesError);
/*     */     }
/* 303 */     catch (Exception e) {
/* 304 */       logger.error("Ocurrió un error al obtener los municipios: ", e);
/*     */     } 
/* 306 */     return listMunicipios;
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
/*     */   public List<ParisheTO> getParroquias() {
/* 361 */     List<ParisheTO> listParroquias = new ArrayList<>();
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 367 */       SimpleJdbcCall jdbcCall = (new SimpleJdbcCall(this.jdbcTemplate)).withCatalogName("prepaypkg.address_and_name_data_pkg").withFunctionName("show_parroquia").withoutProcedureColumnMetaDataAccess().declareParameters(new SqlParameter[] { (SqlParameter)new SqlOutParameter("result", 4), (SqlParameter)new SqlOutParameter("out_parroquia_list", -10, (RowMapper)new Object(this)), (SqlParameter)new SqlOutParameter("out_cod_error", 12), (SqlParameter)new SqlOutParameter("out_mes_error", 12) });
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
/* 383 */       Map<String, Object> result = jdbcCall.execute(new HashMap<>());
/*     */       
/* 385 */       Number returnValue = (Number)result.get("return_value");
/* 386 */       listParroquias = (List<ParisheTO>)result.get("out_parroquia_list");
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 391 */       String codError = (String)result.get("out_cod_error");
/* 392 */       String mesError = (String)result.get("out_mes_error");
/*     */ 
/*     */ 
/*     */       
/* 396 */       logger.debug("Código de error getParroquias: " + codError);
/* 397 */       logger.debug("Mensaje de error getParroquias: " + mesError);
/*     */     }
/* 399 */     catch (Exception e) {
/* 400 */       logger.error("Ocurrió un error al obtener las parroquias: ", e);
/*     */     } 
/* 402 */     return listParroquias;
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
/*     */   public List<SectorTO> getSectores() {
/* 450 */     List<SectorTO> listSectores = new ArrayList<>();
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 456 */       SimpleJdbcCall jdbcCall = (new SimpleJdbcCall(this.jdbcTemplate)).withCatalogName("prepaypkg.address_and_name_data_pkg").withFunctionName("show_sector").withoutProcedureColumnMetaDataAccess().declareParameters(new SqlParameter[] { (SqlParameter)new SqlOutParameter("result", 4), (SqlParameter)new SqlOutParameter("out_sector_list", -10, (RowMapper)new Object(this)), (SqlParameter)new SqlOutParameter("out_cod_error", 12), (SqlParameter)new SqlOutParameter("out_mes_error", 12) });
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
/* 472 */       Map<String, Object> result = jdbcCall.execute(new HashMap<>());
/*     */       
/* 474 */       Number returnValue = (Number)result.get("return_value");
/* 475 */       listSectores = (List<SectorTO>)result.get("out_sector_list");
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 480 */       String codError = (String)result.get("out_cod_error");
/* 481 */       String mesError = (String)result.get("out_mes_error");
/*     */ 
/*     */ 
/*     */       
/* 485 */       logger.debug("Código de error getSectores: " + codError);
/* 486 */       logger.debug("Mensaje de error getSectores: " + mesError);
/*     */     }
/* 488 */     catch (Exception e) {
/* 489 */       logger.error("Ocurrió un error al obtener los sectores: ", e);
/*     */     } 
/* 491 */     return listSectores;
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
/*     */   public List<CityTO> getCities() {
/* 539 */     List<CityTO> listCities = new ArrayList<>();
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 545 */       SimpleJdbcCall jdbcCall = (new SimpleJdbcCall(this.jdbcTemplate)).withCatalogName("prepaypkg.address_and_name_data_pkg").withFunctionName("show_cities").withoutProcedureColumnMetaDataAccess().declareParameters(new SqlParameter[] { (SqlParameter)new SqlOutParameter("result", 4), (SqlParameter)new SqlOutParameter("out_cities_list", -10, (RowMapper)new Object(this)), (SqlParameter)new SqlOutParameter("out_cod_error", 12), (SqlParameter)new SqlOutParameter("out_mes_error", 12) });
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
/* 561 */       Map<String, Object> result = jdbcCall.execute(new HashMap<>());
/*     */       
/* 563 */       Number returnValue = (Number)result.get("return_value");
/* 564 */       listCities = (List<CityTO>)result.get("out_cities_list");
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 569 */       String codError = (String)result.get("out_cod_error");
/* 570 */       String mesError = (String)result.get("out_mes_error");
/*     */ 
/*     */ 
/*     */       
/* 574 */       logger.debug("Código de error:  getCities" + codError);
/* 575 */       logger.debug("Mensaje de error: getCities " + mesError);
/*     */     }
/* 577 */     catch (Exception e) {
/* 578 */       logger.error("Ocurrió un error al obtener las ciudades: ", e);
/*     */     } 
/* 580 */     return listCities;
/*     */   }
/*     */ }


/* Location:              C:\Users\aruizc01\Desktop\api-movilnet.war!\WEB-INF\classes\ve\com\movilnet\api\repository\DataRepository.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */