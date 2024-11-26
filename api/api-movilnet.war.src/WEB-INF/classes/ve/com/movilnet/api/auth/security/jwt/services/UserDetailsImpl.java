/*     */ package WEB-INF.classes.ve.com.movilnet.api.auth.security.jwt.services;
/*     */ 
/*     */ import com.fasterxml.jackson.annotation.JsonIgnore;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ import java.util.Objects;
/*     */ import java.util.stream.Collectors;
/*     */ import org.apache.log4j.Logger;
/*     */ import org.springframework.boot.SpringApplication;
/*     */ import org.springframework.security.core.GrantedAuthority;
/*     */ import org.springframework.security.core.authority.SimpleGrantedAuthority;
/*     */ import org.springframework.security.core.userdetails.UserDetails;
/*     */ import ve.com.movilnet.api.auth.security.jwt.models.Role;
/*     */ import ve.com.movilnet.api.auth.security.jwt.models.User;
/*     */ 
/*     */ 
/*     */ public class UserDetailsImpl
/*     */   implements UserDetails
/*     */ {
/*  20 */   private static final Logger logger = Logger.getLogger(ve.com.movilnet.api.auth.security.jwt.services.UserDetailsImpl.class);
/*     */   private static final long serialVersionUID = 1L;
/*     */   
/*     */   public static void main(String[] args) {
/*  24 */     long time1 = 0L;
/*  25 */     String transaccionId = "Autogestion--" + time1;
/*     */     
/*  27 */     time1 = System.currentTimeMillis();
/*     */     
/*  29 */     String origen = "UserDetailsImpl";
/*  30 */     String tipoTransaccion = "APP";
/*     */     
/*  32 */     SpringApplication.run(ve.com.movilnet.api.auth.security.jwt.services.UserDetailsImpl.class, args);
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
/*     */   public UserDetailsImpl(Long id, String username, String email, String password, Collection<? extends GrantedAuthority> authorities) {
/*  54 */     this.id = id;
/*  55 */     this.username = username;
/*  56 */     this.email = email;
/*  57 */     this.password = password;
/*  58 */     this.authorities = authorities;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public UserDetailsImpl(Long id, String username, String email, String password) {
/*  64 */     this.id = id;
/*  65 */     this.username = username;
/*  66 */     this.email = email;
/*  67 */     this.password = password;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static ve.com.movilnet.api.auth.security.jwt.services.UserDetailsImpl build2(User user) {
/*  73 */     return new ve.com.movilnet.api.auth.security.jwt.services.UserDetailsImpl(user
/*  74 */         .getId(), user
/*  75 */         .getUsername(), user
/*  76 */         .getEmail(), user
/*  77 */         .getPassword());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static ve.com.movilnet.api.auth.security.jwt.services.UserDetailsImpl build(User user) {
/*  83 */     List<GrantedAuthority> authorities = (List<GrantedAuthority>)user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName().name())).collect(Collectors.toList());
/*     */     
/*  85 */     return new ve.com.movilnet.api.auth.security.jwt.services.UserDetailsImpl(user
/*  86 */         .getId(), user
/*  87 */         .getUsername(), user
/*  88 */         .getEmail(), user
/*  89 */         .getPassword(), authorities);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Collection<? extends GrantedAuthority> getAuthorities() {
/*  95 */     return this.authorities;
/*     */   }
/*     */   
/*     */   public Long getId() {
/*  99 */     return this.id;
/*     */   }
/*     */   
/*     */   public String getEmail() {
/* 103 */     return this.email;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getPassword() {
/* 108 */     return this.password;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getUsername() {
/* 113 */     return this.username;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isAccountNonExpired() {
/* 118 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isAccountNonLocked() {
/* 123 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isCredentialsNonExpired() {
/* 128 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isEnabled() {
/* 133 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object o) {
/* 138 */     if (this == o)
/* 139 */       return true; 
/* 140 */     if (o == null || getClass() != o.getClass())
/* 141 */       return false; 
/* 142 */     ve.com.movilnet.api.auth.security.jwt.services.UserDetailsImpl user = (ve.com.movilnet.api.auth.security.jwt.services.UserDetailsImpl)o;
/* 143 */     return Objects.equals(this.id, user.id);
/*     */   }
/*     */ }


/* Location:              C:\Users\aruizc01\Desktop\api-movilnet.war!\WEB-INF\classes\ve\com\movilnet\api\auth\security\jwt\services\UserDetailsImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */