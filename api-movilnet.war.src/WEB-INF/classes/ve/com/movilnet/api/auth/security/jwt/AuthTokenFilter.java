/*     */ package WEB-INF.classes.ve.com.movilnet.api.auth.security.jwt;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ import javax.servlet.FilterChain;
/*     */ import javax.servlet.ServletException;
/*     */ import javax.servlet.ServletRequest;
/*     */ import javax.servlet.ServletResponse;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import org.apache.log4j.Logger;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
/*     */ import org.springframework.security.core.Authentication;
/*     */ import org.springframework.security.core.context.SecurityContextHolder;
/*     */ import org.springframework.security.core.userdetails.UserDetails;
/*     */ import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
/*     */ import org.springframework.transaction.annotation.Transactional;
/*     */ import org.springframework.util.StringUtils;
/*     */ import org.springframework.web.filter.OncePerRequestFilter;
/*     */ import ve.com.movilnet.api.auth.security.jwt.JwtUtils;
/*     */ import ve.com.movilnet.api.auth.security.jwt.services.UserDetailsServiceImpl;
/*     */ import ve.com.movilnet.api.component.ScheduledDataUpdater;
/*     */ import ve.com.movilnet.api.service.util.Const;
/*     */ 
/*     */ public class AuthTokenFilter
/*     */   extends OncePerRequestFilter {
/*     */   @Autowired
/*     */   private JwtUtils jwtUtils;
/*  30 */   ConcurrentHashMap<String, String> userSessions = ScheduledDataUpdater.getuserSessions();
/*  31 */   ConcurrentHashMap<String, UserDetails> userDetailsCache = ScheduledDataUpdater.getuserDetailsCache();
/*     */   
/*     */   @Autowired
/*     */   private HttpServletRequest request;
/*     */   @Autowired
/*     */   private UserDetailsServiceImpl userDetailsService;
/*  37 */   private static final Logger logger = Logger.getLogger(ve.com.movilnet.api.auth.security.jwt.AuthTokenFilter.class);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Transactional
/*     */   protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
/*     */     try {
/*  45 */       Const.IP_REMOTA = request.getRemoteHost();
/*  46 */       String userIp = request.getRemoteAddr();
/*  47 */       Const.URL = request.getHeader("referer");
/*  48 */       Const.USERbrowser = request.getServletContext().getServerInfo();
/*  49 */       String jwt = parseJwt(request);
/*  50 */       String servenameLocal = request.getServerName();
/*     */ 
/*     */ 
/*     */       
/*  54 */       if (jwt != null && this.jwtUtils.validateJwtToken(jwt)) {
/*  55 */         UserDetails userDetails; String username = this.jwtUtils.getUserNameFromJwtToken(jwt);
/*     */ 
/*     */ 
/*     */         
/*  59 */         if (this.userSessions.containsKey(username) && !((String)this.userSessions.get(username)).equals(userIp)) {
/*  60 */           logger.debug("AuthTokenFilter.doFilterInternal Verificamos si el usuario ya tiene una sesión iniciada desde otra IP no deberiamos ir a buscar en ws  ");
/*     */           
/*  62 */           response.sendError(401, "Error: Unauthorized validateJwtToken");
/*     */         } 
/*     */ 
/*     */ 
/*     */         
/*  67 */         logger.debug("AuthTokenFilter.doFilterInternal Verificamos si el usuario ya tiene una sesión userDetails");
/*     */         
/*  69 */         if (this.userDetailsCache.containsKey(username)) {
/*  70 */           userDetails = this.userDetailsCache.get(username);
/*  71 */           logger.debug("AuthTokenFilter.doFilterInternal Verificamos si el usuario ya tiene una sesión userDetails get ");
/*     */         } else {
/*     */           
/*  74 */           userDetails = this.userDetailsService.loadUserByUsername(username);
/*  75 */           logger.debug("AuthTokenFilter.doFilterInternal Verificamos si el usuario ya tiene una sesión userDetails put ");
/*  76 */           this.userDetailsCache.put(username, userDetails);
/*     */         } 
/*     */ 
/*     */         
/*  80 */         UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
/*     */         
/*  82 */         authentication.setDetails((new WebAuthenticationDetailsSource()).buildDetails(request));
/*  83 */         SecurityContextHolder.getContext().setAuthentication((Authentication)authentication);
/*     */ 
/*     */       
/*     */       }
/*     */ 
/*     */     
/*     */     }
/*  90 */     catch (Exception e) {
/*  91 */       response.sendError(401, "Error: Unauthorized validateJwtToken");
/*  92 */       logger.error("Cannot set user authentication: {}", e);
/*     */     } 
/*     */     
/*  95 */     filterChain.doFilter((ServletRequest)request, (ServletResponse)response);
/*     */   }
/*     */   
/*     */   public String getUri(HttpServletRequest request) {
/*  99 */     String r = (String)request.getAttribute("javax.servlet.forward.request_uri");
/* 100 */     return (r == null) ? request.getRequestURI() : r;
/*     */   }
/*     */   
/*     */   private String parseJwt(HttpServletRequest request) {
/* 104 */     String headerAuth = request.getHeader("Authorization");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 114 */     if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
/* 115 */       return headerAuth.substring(7, headerAuth.length());
/*     */     }
/*     */     
/* 118 */     return null;
/*     */   }
/*     */ }


/* Location:              C:\Users\aruizc01\Desktop\api-movilnet.war!\WEB-INF\classes\ve\com\movilnet\api\auth\security\jwt\AuthTokenFilter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */