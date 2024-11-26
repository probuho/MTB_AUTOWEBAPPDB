/*     */ package WEB-INF.classes.ve.com.movilnet.api.auth.security;
/*     */ 
/*     */ import java.util.Arrays;
/*     */ import javax.servlet.Filter;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import org.apache.log4j.Logger;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.boot.SpringApplication;
/*     */ import org.springframework.context.annotation.Bean;
/*     */ import org.springframework.context.annotation.Configuration;
/*     */ import org.springframework.security.authentication.AuthenticationManager;
/*     */ import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
/*     */ import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
/*     */ import org.springframework.security.config.annotation.web.builders.HttpSecurity;
/*     */ import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
/*     */ import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
/*     */ import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
/*     */ import org.springframework.security.config.http.SessionCreationPolicy;
/*     */ import org.springframework.security.core.userdetails.UserDetailsService;
/*     */ import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
/*     */ import org.springframework.security.crypto.password.PasswordEncoder;
/*     */ import org.springframework.security.web.AuthenticationEntryPoint;
/*     */ import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
/*     */ import org.springframework.web.cors.CorsConfiguration;
/*     */ import ve.com.movilnet.ApiBackendApplication;
/*     */ import ve.com.movilnet.api.auth.security.jwt.AuthEntryPointJwt;
/*     */ import ve.com.movilnet.api.auth.security.jwt.AuthTokenFilter;
/*     */ import ve.com.movilnet.api.auth.security.jwt.services.UserDetailsServiceImpl;
/*     */ 
/*     */ @Configuration
/*     */ @EnableWebSecurity
/*     */ @EnableGlobalMethodSecurity(prePostEnabled = true)
/*     */ public class WebSecurityConfig
/*     */   extends WebSecurityConfigurerAdapter {
/*  35 */   private static final Logger logger = Logger.getLogger(ve.com.movilnet.api.auth.security.WebSecurityConfig.class);
/*     */ 
/*     */   
/*     */   public static void main(String[] args) {
/*  39 */     long time1 = 0L;
/*  40 */     String transaccionId = "Autogestion--" + time1;
/*  41 */     time1 = System.currentTimeMillis();
/*  42 */     String origen = "WebSecurityConfig";
/*  43 */     String tipoTransaccion = "APP";
/*     */     
/*  45 */     SpringApplication.run(ApiBackendApplication.class, args);
/*     */   }
/*     */ 
/*     */   
/*     */   @Autowired
/*     */   UserDetailsServiceImpl muserDetailsService;
/*     */   @Autowired
/*     */   private AuthEntryPointJwt unauthorizedHandler;
/*     */   
/*     */   @Bean
/*     */   public AuthTokenFilter authenticationJwtTokenFilter() {
/*  56 */     return new AuthTokenFilter();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
/*  62 */     logger.info("WebSecurityConfig.configure " + this.muserDetailsService);
/*  63 */     authenticationManagerBuilder.userDetailsService((UserDetailsService)this.muserDetailsService).passwordEncoder(passwordEncoder());
/*     */   }
/*     */ 
/*     */   
/*     */   @Bean
/*     */   public AuthenticationManager authenticationManagerBean() throws Exception {
/*  69 */     return super.authenticationManagerBean();
/*     */   }
/*     */   
/*     */   @Bean
/*     */   public PasswordEncoder passwordEncoder() {
/*  74 */     return (PasswordEncoder)new BCryptPasswordEncoder();
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
/*     */   protected void configure(HttpSecurity http) throws Exception {
/* 177 */     ((ExpressionUrlAuthorizationConfigurer.AuthorizedUrl)((ExpressionUrlAuthorizationConfigurer.AuthorizedUrl)((ExpressionUrlAuthorizationConfigurer.AuthorizedUrl)((HttpSecurity)((HttpSecurity)((HttpSecurity)((HttpSecurity)http.cors().configurationSource(request -> {
/*     */           CorsConfiguration cors = new CorsConfiguration();
/*     */           
/*     */           cors.setAllowedOrigins(Arrays.asList(new String[] { "https://movilnet.com.ve", "https://www.movilnet.com.ve", "http://aplicaciones.movilnet.com.ve", "http://aplicaciones.movilnet.com.ve/tumovilnetenlinea/api" }));
/*     */           
/*     */           cors.setAllowedMethods(Arrays.asList(new String[] { "GET", "POST", "PUT", "DELETE" }));
/*     */           cors.addAllowedHeader("*");
/*     */           return cors;
/* 185 */         }).and()).csrf().disable())
/* 186 */       .exceptionHandling().authenticationEntryPoint((AuthenticationEntryPoint)this.unauthorizedHandler).and())
/* 187 */       .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and())
/* 188 */       .authorizeRequests().antMatchers(new String[] { "/api/auth/**" })).permitAll()
/* 189 */       .antMatchers(new String[] { "/**" })).permitAll()
/* 190 */       .anyRequest()).authenticated();
/*     */     
/* 192 */     http.addFilterBefore((Filter)authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
/*     */   }
/*     */ }


/* Location:              C:\Users\aruizc01\Desktop\api-movilnet.war!\WEB-INF\classes\ve\com\movilnet\api\auth\security\WebSecurityConfig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */