/*     */ package WEB-INF.classes.ve.com.movilnet.api.auth.security.auth;
/*     */ 
/*     */ import io.jsonwebtoken.Claims;
/*     */ import io.jsonwebtoken.Jwts;
/*     */ import io.swagger.annotations.ApiOperation;
/*     */ import java.net.MalformedURLException;
/*     */ import java.rmi.RemoteException;
/*     */ import java.util.List;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.validation.Valid;
/*     */ import javax.xml.rpc.ServiceException;
/*     */ import org.apache.log4j.Logger;
/*     */ import org.apache.soap.SOAPException;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.cache.annotation.CacheEvict;
/*     */ import org.springframework.http.HttpStatus;
/*     */ import org.springframework.http.ResponseEntity;
/*     */ import org.springframework.security.authentication.AuthenticationManager;
/*     */ import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
/*     */ import org.springframework.security.core.Authentication;
/*     */ import org.springframework.security.core.context.SecurityContextHolder;
/*     */ import org.springframework.security.crypto.password.PasswordEncoder;
/*     */ import org.springframework.stereotype.Controller;
/*     */ import org.springframework.web.bind.annotation.CrossOrigin;
/*     */ import org.springframework.web.bind.annotation.GetMapping;
/*     */ import org.springframework.web.bind.annotation.PostMapping;
/*     */ import org.springframework.web.bind.annotation.RequestBody;
/*     */ import org.springframework.web.bind.annotation.RequestHeader;
/*     */ import org.springframework.web.bind.annotation.RequestMapping;
/*     */ import ve.com.movilnet.api.auth.security.auth.request.KeyRequest;
/*     */ import ve.com.movilnet.api.auth.security.auth.request.LoginRequest;
/*     */ import ve.com.movilnet.api.auth.security.auth.response.JwtResponse;
/*     */ import ve.com.movilnet.api.auth.security.auth.response.KeyResponse;
/*     */ import ve.com.movilnet.api.auth.security.jwt.JwtUtils;
/*     */ import ve.com.movilnet.api.auth.security.jwt.services.UserDetailsImpl;
/*     */ import ve.com.movilnet.api.component.ScheduledDataUpdater;
/*     */ import ve.com.movilnet.api.config.ConfProperties;
/*     */ import ve.com.movilnet.api.service.util.Operaciones;
/*     */ import ve.com.movilnet.api.ws.autenticate.WsauthenticateStub;
/*     */ 
/*     */ @CrossOrigin(origins = {"*"}, maxAge = 3600L)
/*     */ @Controller
/*     */ @RequestMapping({"/api/auth"})
/*     */ public class AuthController
/*     */ {
/*  47 */   private static final Logger logger = Logger.getLogger(ve.com.movilnet.api.auth.security.auth.AuthController.class);
/*     */   
/*  49 */   ConcurrentHashMap<String, String> userSessions = ScheduledDataUpdater.getuserSessions(); @Autowired
/*     */   private HttpServletRequest request; @Autowired
/*     */   ConfProperties prop;
/*     */   
/*     */   @PostMapping({"/signin"})
/*     */   @CacheEvict(value = {"apiCache"}, key = "#loginRequest.username")
/*     */   @ApiOperation("Describe la operación de este endpoint")
/*     */   public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
/*  57 */     String userIp = this.request.getRemoteAddr();
/*  58 */     String serverName = this.request.getServerName();
/*  59 */     String host = this.request.getRemoteAddr();
/*  60 */     long tx = System.currentTimeMillis();
/*     */     
/*  62 */     String transaccionId = loginRequest.getOrigin() + "-" + serverName + "-" + host + "-" + loginRequest.getUsername() + "-" + tx;
/*     */     
/*  64 */     logger.debug("AuthController.authenticateUser. authenticateUser " + transaccionId);
/*     */ 
/*     */     
/*  67 */     if (this.userSessions.containsKey(loginRequest.getUsername()) && !((String)this.userSessions.get(loginRequest.getUsername())).equals(userIp)) {
/*  68 */       return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new JwtResponse(null, loginRequest
/*  69 */             .getUsername(), null, "RA2099"));
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*  74 */     this.userSessions.put(loginRequest.getUsername(), userIp);
/*  75 */     String url = this.prop.getWsAuthenticateStub();
/*  76 */     Operaciones op = new Operaciones();
/*  77 */     String passBs = null;
/*  78 */     String code = "RA2003";
/*     */     
/*     */     try {
/*  81 */       passBs = op.encryptDecryptEAS(loginRequest.getPassword());
/*  82 */     } catch (Exception e) {
/*  83 */       logger.error("AuthController.pasoalgo.encryptDecryptEAS " + e);
/*     */     } 
/*     */ 
/*     */     
/*  87 */     Authentication authentication = this.authenticationManager.authenticate((Authentication)new UsernamePasswordAuthenticationToken(loginRequest
/*  88 */           .getUsername() + ";" + passBs, passBs));
/*     */ 
/*     */     
/*  91 */     SecurityContextHolder.getContext().setAuthentication(authentication);
/*     */     
/*  93 */     String jwt = this.jwtUtils.generateJwtToken(authentication, passBs);
/*  94 */     UserDetailsImpl userDetails = (UserDetailsImpl)authentication.getPrincipal();
/*  95 */     List<String> roles = null;
/*  96 */     logger.info(" AuthController.signin|codigo_" + code + "|" + op.tiempoDeEjecucion(tx, "RA2002-1"));
/*  97 */     return ResponseEntity.ok(new JwtResponse(jwt, userDetails
/*  98 */           .getUsername(), roles, code));
/*     */   }
/*     */   @Autowired
/*     */   AuthenticationManager authenticationManager;
/*     */   
/*     */   @GetMapping({"/signin"})
/*     */   public String handleGetSignin() {
/* 105 */     return "redirect:/index.html";
/*     */   } @Autowired
/*     */   PasswordEncoder encoder; @Autowired
/*     */   JwtUtils jwtUtils; @PostMapping({"/signout"})
/*     */   @ApiOperation("Describe la operación de este endpoint")
/*     */   public ResponseEntity<?> signOut(HttpServletRequest request, @Valid @RequestBody LoginRequest loginRequest, @RequestHeader("Authorization") String bearerToken) {
/* 111 */     request.getSession().invalidate();
/* 112 */     String token = bearerToken.substring(7);
/* 113 */     JwtResponse jwtResponse = new JwtResponse(null, null, null, "RA2099");
/*     */ 
/*     */ 
/*     */     
/* 117 */     String username = getUserNameFromJwtToken(token);
/* 118 */     String userIp = request.getRemoteAddr();
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 123 */     if (this.userSessions.containsKey(username) && ((String)this.userSessions.get(loginRequest.getUsername())).equals(userIp)) {
/* 124 */       this.userSessions.remove(username);
/* 125 */       logger.info(" AuthController.signOut|se logro remover | token " + username);
/* 126 */       jwtResponse = new JwtResponse(null, null, null, "RA2999");
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 131 */     return ResponseEntity.ok(jwtResponse);
/*     */   }
/*     */ 
/*     */   
/*     */   @GetMapping({"/signout"})
/*     */   public String handleGetsignout() {
/* 137 */     return "redirect:/index.html";
/*     */   }
/*     */ 
/*     */   
/*     */   @PostMapping({"/key"})
/*     */   @ApiOperation("Describe la operación de este endpoint")
/*     */   public ResponseEntity<?> keyUser(@Valid @RequestBody KeyRequest keyRequest) throws ServiceException, SOAPException, RemoteException, MalformedURLException, MalformedURLException {
/* 144 */     long tx = System.currentTimeMillis();
/* 145 */     String codigo = "RA2002";
/* 146 */     String subcodigo = "RA2002";
/* 147 */     String host = this.request.getRemoteAddr();
/* 148 */     String serverName = this.request.getServerName();
/*     */     
/* 150 */     String transaccionId = keyRequest.getOrigin() + "-" + serverName + "-" + host + "-" + keyRequest.getUsername() + "-" + tx;
/*     */ 
/*     */     
/* 153 */     String url = this.prop.getWsAuthenticateStub();
/* 154 */     Operaciones op = new Operaciones();
/*     */     
/* 156 */     WsauthenticateStub stub = new WsauthenticateStub();
/* 157 */     codigo = stub.generateKey(url, keyRequest.getUsername(), keyRequest.getMail(), keyRequest.getOrigin(), keyRequest.getCode());
/*     */     
/* 159 */     logger.info("AuthController.key|codigo_" + codigo + "|" + op.tiempoDeEjecucion(tx, "RA2002-1"));
/* 160 */     return ResponseEntity.ok(new KeyResponse("Envio de Clave ", "Se proceso su solicitud de forma exitosa", codigo, subcodigo));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @GetMapping({"/key"})
/*     */   public String handleGetkey() {
/* 169 */     return "redirect:/index.html";
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
/*     */   public String getUserNameFromJwtToken(String token) {
/* 187 */     String usernamesplit = null;
/* 188 */     if (!token.equals("")) {
/*     */       
/* 190 */       usernamesplit = ((Claims)Jwts.parser().setSigningKey(this.prop.getJwtSecret()).parseClaimsJws(token).getBody()).getSubject();
/*     */       
/* 192 */       if (!usernamesplit.equals("")) {
/* 193 */         String[] arrayUsername = usernamesplit.split(";");
/*     */         
/* 195 */         usernamesplit = arrayUsername[0];
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 200 */     return usernamesplit;
/*     */   }
/*     */ }


/* Location:              C:\Users\aruizc01\Desktop\api-movilnet.war!\WEB-INF\classes\ve\com\movilnet\api\auth\security\auth\AuthController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */