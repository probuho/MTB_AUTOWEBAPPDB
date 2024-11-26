/*     */ package WEB-INF.classes.ve.com.movilnet.api.auth.security.jwt.models;
/*     */ 
/*     */ import java.util.HashSet;
/*     */ import java.util.Set;
/*     */ import javax.persistence.FetchType;
/*     */ import javax.persistence.GeneratedValue;
/*     */ import javax.persistence.GenerationType;
/*     */ import javax.persistence.Id;
/*     */ import javax.persistence.JoinColumn;
/*     */ import javax.persistence.JoinTable;
/*     */ import javax.persistence.ManyToMany;
/*     */ import javax.validation.constraints.Email;
/*     */ import javax.validation.constraints.NotBlank;
/*     */ import javax.validation.constraints.Size;
/*     */ import org.apache.log4j.Logger;
/*     */ import org.springframework.boot.SpringApplication;
/*     */ import ve.com.movilnet.api.auth.security.jwt.models.Role;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class User
/*     */ {
/*  23 */   private static final Logger logger = Logger.getLogger(ve.com.movilnet.api.auth.security.jwt.models.User.class);
/*     */ 
/*     */   
/*     */   public static void main(String[] args) {
/*  27 */     long time1 = 0L;
/*  28 */     String transaccionId = "Autogestion--" + time1;
/*     */     
/*  30 */     time1 = System.currentTimeMillis();
/*     */     
/*  32 */     String origen = "User";
/*  33 */     String tipoTransaccion = "APP";
/*     */     
/*  35 */     SpringApplication.run(ve.com.movilnet.api.auth.security.jwt.models.User.class, args);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @Id
/*     */   @GeneratedValue(strategy = GenerationType.IDENTITY)
/*     */   private Long id;
/*     */   
/*     */   @NotBlank
/*     */   @Size(max = 20)
/*     */   private String username;
/*     */   
/*     */   @NotBlank
/*     */   @Size(max = 50)
/*     */   @Email
/*     */   private String email;
/*     */   
/*     */   @NotBlank
/*     */   @Size(max = 120)
/*     */   private String password;
/*     */   
/*     */   @ManyToMany(fetch = FetchType.LAZY)
/*     */   @JoinTable(name = "user_roles", joinColumns = {@JoinColumn(name = "user_id")}, inverseJoinColumns = {@JoinColumn(name = "role_id")})
/*  59 */   private Set<Role> roles = new HashSet<>();
/*     */ 
/*     */ 
/*     */   
/*     */   public User() {}
/*     */ 
/*     */   
/*     */   public User(Long id, String username, String email, String password) {
/*  67 */     this.id = id;
/*  68 */     this.username = username;
/*  69 */     this.email = email;
/*  70 */     this.password = password;
/*     */   }
/*     */   
/*     */   public Long getId() {
/*  74 */     return this.id;
/*     */   }
/*     */   
/*     */   public void setId(Long id) {
/*  78 */     this.id = id;
/*     */   }
/*     */   
/*     */   public String getUsername() {
/*  82 */     return this.username;
/*     */   }
/*     */   
/*     */   public void setUsername(String username) {
/*  86 */     this.username = username;
/*     */   }
/*     */   
/*     */   public String getEmail() {
/*  90 */     return this.email;
/*     */   }
/*     */   
/*     */   public void setEmail(String email) {
/*  94 */     this.email = email;
/*     */   }
/*     */   
/*     */   public String getPassword() {
/*  98 */     return this.password;
/*     */   }
/*     */   
/*     */   public void setPassword(String password) {
/* 102 */     this.password = password;
/*     */   }
/*     */   
/*     */   public Set<Role> getRoles() {
/* 106 */     return this.roles;
/*     */   }
/*     */   
/*     */   public void setRoles(Set<Role> roles) {
/* 110 */     this.roles = roles;
/*     */   }
/*     */ }


/* Location:              C:\Users\aruizc01\Desktop\api-movilnet.war!\WEB-INF\classes\ve\com\movilnet\api\auth\security\jwt\models\User.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */