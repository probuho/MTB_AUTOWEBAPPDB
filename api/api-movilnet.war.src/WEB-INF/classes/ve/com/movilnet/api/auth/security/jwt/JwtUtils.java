/*    */ package WEB-INF.classes.ve.com.movilnet.api.auth.security.jwt;
/*    */ import io.jsonwebtoken.ExpiredJwtException;
/*    */ import io.jsonwebtoken.Jwts;
/*    */ import io.jsonwebtoken.MalformedJwtException;
/*    */ import io.jsonwebtoken.SignatureException;
/*    */ import io.jsonwebtoken.UnsupportedJwtException;
/*    */ import java.util.Date;
/*    */ import org.slf4j.Logger;
/*    */ import org.springframework.beans.factory.annotation.Value;
/*    */ import org.springframework.security.core.Authentication;
/*    */ import org.springframework.stereotype.Component;
/*    */ import ve.com.movilnet.api.auth.security.jwt.services.UserDetailsImpl;
/*    */ import ve.com.movilnet.api.service.util.Operaciones;
/*    */ 
/*    */ @Component
/*    */ public class JwtUtils {
/* 17 */   private static final Logger logger = LoggerFactory.getLogger(ve.com.movilnet.api.auth.security.jwt.JwtUtils.class);
/*    */   
/*    */   @Value("${movilnet.app.jwtSecret}")
/*    */   private String jwtSecret;
/*    */   
/*    */   @Value("${movilnet.app.jwtExpirationMs}")
/*    */   private int jwtExpirationMs;
/*    */ 
/*    */   
/*    */   public String generateJwtToken(Authentication authentication, String pass) {
/* 27 */     Operaciones op = new Operaciones();
/*    */     
/* 29 */     Date setexpiration = new Date((new Date()).getTime() + this.jwtExpirationMs);
/* 30 */     logger.debug("setexpiration: " + setexpiration);
/*    */     
/* 32 */     UserDetailsImpl userPrincipal = (UserDetailsImpl)authentication.getPrincipal();
/* 33 */     return Jwts.builder()
/* 34 */       .setSubject(userPrincipal.getUsername() + ";" + pass)
/* 35 */       .setIssuedAt(new Date())
/* 36 */       .setExpiration(new Date((new Date()).getTime() + this.jwtExpirationMs))
/* 37 */       .signWith(SignatureAlgorithm.HS512, this.jwtSecret)
/* 38 */       .compact();
/*    */   }
/*    */   
/*    */   public String getUserNameFromJwtToken(String token) {
/* 42 */     return ((Claims)Jwts.parser().setSigningKey(this.jwtSecret).parseClaimsJws(token).getBody()).getSubject();
/*    */   }
/*    */   
/*    */   public boolean validateJwtToken(String authToken) {
/*    */     try {
/* 47 */       logger.debug("validateJwtToken authToken ", authToken);
/* 48 */       Jwts.parser().setSigningKey(this.jwtSecret).parseClaimsJws(authToken);
/* 49 */       return true;
/* 50 */     } catch (SignatureException e) {
/* 51 */       logger.error("Invalid JWT signature: {}", e.getMessage());
/* 52 */     } catch (MalformedJwtException e) {
/* 53 */       logger.error("Invalid JWT token: {}", e.getMessage());
/* 54 */     } catch (ExpiredJwtException e) {
/* 55 */       logger.error("JWT token is expired: {}", e.getMessage());
/* 56 */     } catch (UnsupportedJwtException e) {
/* 57 */       logger.error("JWT token is unsupported: {}", e.getMessage());
/* 58 */     } catch (IllegalArgumentException e) {
/* 59 */       logger.error("JWT claims string is empty: {}", e.getMessage());
/*    */     } 
/*    */     
/* 62 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\aruizc01\Desktop\api-movilnet.war!\WEB-INF\classes\ve\com\movilnet\api\auth\security\jwt\JwtUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */