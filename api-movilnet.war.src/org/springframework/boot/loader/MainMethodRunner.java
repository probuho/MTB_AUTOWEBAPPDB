/*    */ package org.springframework.boot.loader;
/*    */ 
/*    */ import java.lang.reflect.Method;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MainMethodRunner
/*    */ {
/*    */   private final String mainClassName;
/*    */   private final String[] args;
/*    */   
/*    */   public MainMethodRunner(String mainClass, String[] args) {
/* 41 */     this.mainClassName = mainClass;
/* 42 */     this.args = (args != null) ? (String[])args.clone() : null;
/*    */   }
/*    */   
/*    */   public void run() throws Exception {
/* 46 */     Class<?> mainClass = Thread.currentThread().getContextClassLoader().loadClass(this.mainClassName);
/* 47 */     Method mainMethod = mainClass.getDeclaredMethod("main", new Class[] { String[].class });
/* 48 */     mainMethod.invoke(null, new Object[] { this.args });
/*    */   }
/*    */ }


/* Location:              C:\Users\aruizc01\Desktop\api-movilnet.war!\org\springframework\boot\loader\MainMethodRunner.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */