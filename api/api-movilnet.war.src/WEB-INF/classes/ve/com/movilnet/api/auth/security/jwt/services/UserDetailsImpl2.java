/*     */ package WEB-INF.classes.ve.com.movilnet.api.auth.security.jwt.services;
/*     */ 
/*     */ import com.fasterxml.jackson.annotation.JsonIgnore;
/*     */ import java.util.Collection;
/*     */ import java.util.Objects;
/*     */ import org.apache.log4j.Logger;
/*     */ import org.springframework.boot.SpringApplication;
/*     */ import org.springframework.security.core.GrantedAuthority;
/*     */ import org.springframework.security.core.userdetails.UserDetails;
/*     */ import ve.com.movilnet.api.auth.security.jwt.models.User;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class UserDetailsImpl2
/*     */   implements UserDetails
/*     */ {
/*  31 */   private static final Logger logger = Logger.getLogger(ve.com.movilnet.api.auth.security.jwt.services.UserDetailsImpl2.class);
/*     */   private static final long serialVersionUID = 1L;
/*     */   
/*     */   public static void main(String[] args) {
/*  35 */     long time1 = 0L;
/*  36 */     String transaccionId = "Autogestion--" + time1;
/*     */     
/*  38 */     time1 = System.currentTimeMillis();
/*     */     
/*  40 */     String origen = "UserDetailsImpl";
/*  41 */     String tipoTransaccion = "APP";
/*     */     
/*  43 */     SpringApplication.run(ve.com.movilnet.api.auth.security.jwt.services.UserDetailsImpl2.class, args);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private Long id;
/*     */ 
/*     */   
/*     */   private String username;
/*     */ 
/*     */   
/*     */   private String email;
/*     */ 
/*     */   
/*     */   @JsonIgnore
/*     */   private String password;
/*     */ 
/*     */   
/*     */   private Collection<? extends GrantedAuthority> authorities;
/*     */ 
/*     */ 
/*     */   
/*     */   public UserDetailsImpl2(Long id, String username, String email, String password) {
/*  66 */     this.id = id;
/*  67 */     this.username = username;
/*  68 */     this.email = email;
/*  69 */     this.password = password;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static ve.com.movilnet.api.auth.security.jwt.services.UserDetailsImpl2 build2(User user) {
/*  75 */     return new ve.com.movilnet.api.auth.security.jwt.services.UserDetailsImpl2(user
/*  76 */         .getId(), user
/*  77 */         .getUsername(), user
/*  78 */         .getEmail(), user
/*  79 */         .getPassword());
/*     */   }
/*     */ 
/*     */   
/*     */   public Collection<? extends GrantedAuthority> getAuthorities() {
/*  84 */     return this.authorities;
/*     */   }
/*     */   
/*     */   public Long getId() {
/*  88 */     return this.id;
/*     */   }
/*     */   
/*     */   public String getEmail() {
/*  92 */     return this.email;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getPassword() {
/*  97 */     return this.password;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getUsername() {
/* 102 */     return this.username;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isAccountNonExpired() {
/* 107 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isAccountNonLocked() {
/* 112 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isCredentialsNonExpired() {
/* 117 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isEnabled() {
/* 122 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object o) {
/* 127 */     if (this == o)
/* 128 */       return true; 
/* 129 */     if (o == null || getClass() != o.getClass())
/* 130 */       return false; 
/* 131 */     ve.com.movilnet.api.auth.security.jwt.services.UserDetailsImpl2 user = (ve.com.movilnet.api.auth.security.jwt.services.UserDetailsImpl2)o;
/* 132 */     return Objects.equals(this.id, user.id);
/*     */   }
/*     */ }


/* Location:              C:\Users\aruizc01\Desktop\api-movilnet.war!\WEB-INF\classes\ve\com\movilnet\api\auth\security\jwt\services\UserDetailsImpl2.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */