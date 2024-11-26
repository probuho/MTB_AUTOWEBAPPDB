/*    */ package WEB-INF.classes.ve.com.movilnet.api.auth.security.jwt.services;
/*    */ 
/*    */ import java.util.HashSet;
/*    */ import java.util.Set;
/*    */ import java.util.concurrent.ConcurrentHashMap;
/*    */ import org.apache.log4j.Logger;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.security.core.userdetails.UserDetails;
/*    */ import org.springframework.security.core.userdetails.UserDetailsService;
/*    */ import org.springframework.security.core.userdetails.UsernameNotFoundException;
/*    */ import org.springframework.security.crypto.password.PasswordEncoder;
/*    */ import org.springframework.stereotype.Service;
/*    */ import org.springframework.transaction.annotation.Transactional;
/*    */ import ve.com.movilnet.api.auth.security.jwt.models.ERole;
/*    */ import ve.com.movilnet.api.auth.security.jwt.models.Role;
/*    */ import ve.com.movilnet.api.auth.security.jwt.models.User;
/*    */ import ve.com.movilnet.api.auth.security.jwt.services.UserDetailsImpl;
/*    */ import ve.com.movilnet.api.component.ScheduledDataUpdater;
/*    */ import ve.com.movilnet.api.config.ConfProperties;
/*    */ import ve.com.movilnet.api.service.util.Operaciones;
/*    */ import ve.com.movilnet.api.ws.autenticate.WsauthenticateStub;
/*    */ 
/*    */ 
/*    */ @Service
/*    */ public class UserDetailsServiceImpl
/*    */   implements UserDetailsService
/*    */ {
/* 28 */   public String PASS = "";
/* 29 */   private static final Logger logger = Logger.getLogger(ve.com.movilnet.api.auth.security.jwt.services.UserDetailsServiceImpl.class);
/* 30 */   ConcurrentHashMap<String, UserDetails> userDetailsCache = ScheduledDataUpdater.getuserDetailsCache();
/*    */   @Autowired
/*    */   PasswordEncoder encoder;
/*    */   @Autowired
/*    */   ConfProperties prop;
/*    */   
/*    */   @Transactional
/*    */   public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
/*    */     UserDetailsImpl userDetailsImpl;
/* 39 */     Operaciones op = new Operaciones();
/* 40 */     long t1 = System.currentTimeMillis();
/* 41 */     String codigo = "";
/*    */     
/* 43 */     String p_url = this.prop.getWsAuthenticateStub();
/* 44 */     logger.info("UserDetailsServiceImpl.loadUserByUsername ");
/* 45 */     WsauthenticateStub stub = new WsauthenticateStub();
/* 46 */     UserDetails userDetails = null;
/* 47 */     User user = new User();
/* 48 */     if (username != null && 
/* 49 */       !username.equals("")) {
/*    */       
/* 51 */       String[] arrayUsername = username.split(";");
/*    */       
/* 53 */       String passBs = null;
/*    */ 
/*    */       
/*    */       try {
/* 57 */         passBs = op.DecryptBse(arrayUsername[1]);
/*    */       }
/* 59 */       catch (Exception e) {
/* 60 */         logger.error(" JwtUtils generateJwtToken Algo paso " + e);
/*    */       } 
/*    */ 
/*    */ 
/*    */       
/* 65 */       if (this.userDetailsCache.containsKey(username)) {
/* 66 */         userDetails = this.userDetailsCache.get(username);
/*    */       }
/*    */       else {
/*    */         
/* 70 */         codigo = stub.authenticateFullDigit(t1, p_url, arrayUsername[0], passBs, "autog", "autog");
/* 71 */         logger.debug("UserDetailsServiceImpl loadUserByUsername  authenticateFullDigit|codigo_" + codigo + "|" + op.tiempoDeEjecucion(t1, "RA2003-1"));
/* 72 */         if (codigo.equals("RA2003")) {
/*    */ 
/*    */           
/* 75 */           this.PASS = this.encoder.encode(arrayUsername[1]);
/*    */           
/* 77 */           user = new User(Long.valueOf(0L), arrayUsername[0], "mail...", this.PASS);
/* 78 */           Set<Role> roles = new HashSet<>();
/* 79 */           Role userRole2 = new Role(ERole.ROLE_USER);
/* 80 */           roles.add(userRole2);
/* 81 */           user.setRoles(roles);
/*    */         } else {
/*    */           
/* 84 */           logger.warn("fallo UserDetailsServiceImpl loadUserByUsername  authenticateFullDigit codigo " + codigo);
/* 85 */           return null;
/*    */         } 
/*    */         
/* 88 */         userDetailsImpl = UserDetailsImpl.build(user);
/* 89 */         this.userDetailsCache.put(username, userDetailsImpl);
/*    */       } 
/*    */     } 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 98 */     return (UserDetails)userDetailsImpl;
/*    */   }
/*    */ }


/* Location:              C:\Users\aruizc01\Desktop\api-movilnet.war!\WEB-INF\classes\ve\com\movilnet\api\auth\security\jwt\services\UserDetailsServiceImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */