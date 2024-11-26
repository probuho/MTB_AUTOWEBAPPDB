/*     */ package org.springframework.boot.loader;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.net.JarURLConnection;
/*     */ import java.net.URL;
/*     */ import java.net.URLClassLoader;
/*     */ import java.net.URLConnection;
/*     */ import java.security.AccessController;
/*     */ import java.security.PrivilegedActionException;
/*     */ import java.util.Enumeration;
/*     */ import java.util.jar.JarFile;
/*     */ import org.springframework.boot.loader.jar.Handler;
/*     */ import org.springframework.boot.loader.jar.JarFile;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LaunchedURLClassLoader
/*     */   extends URLClassLoader
/*     */ {
/*     */   static {
/*  42 */     ClassLoader.registerAsParallelCapable();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public LaunchedURLClassLoader(URL[] urls, ClassLoader parent) {
/*  51 */     super(urls, parent);
/*     */   }
/*     */ 
/*     */   
/*     */   public URL findResource(String name) {
/*  56 */     Handler.setUseFastConnectionExceptions(true);
/*     */     try {
/*  58 */       return super.findResource(name);
/*     */     } finally {
/*     */       
/*  61 */       Handler.setUseFastConnectionExceptions(false);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public Enumeration<URL> findResources(String name) throws IOException {
/*  67 */     Handler.setUseFastConnectionExceptions(true);
/*     */     try {
/*  69 */       return new UseFastConnectionExceptionsEnumeration(super.findResources(name));
/*     */     } finally {
/*     */       
/*  72 */       Handler.setUseFastConnectionExceptions(false);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
/*  78 */     Handler.setUseFastConnectionExceptions(true);
/*     */     try {
/*     */       try {
/*  81 */         definePackageIfNecessary(name);
/*     */       }
/*  83 */       catch (IllegalArgumentException ex) {
/*     */         
/*  85 */         if (getPackage(name) == null)
/*     */         {
/*     */ 
/*     */           
/*  89 */           throw new AssertionError("Package " + name + " has already been defined but it could not be found");
/*     */         }
/*     */       } 
/*     */       
/*  93 */       return super.loadClass(name, resolve);
/*     */     } finally {
/*     */       
/*  96 */       Handler.setUseFastConnectionExceptions(false);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void definePackageIfNecessary(String className) {
/* 107 */     int lastDot = className.lastIndexOf('.');
/* 108 */     if (lastDot >= 0) {
/* 109 */       String packageName = className.substring(0, lastDot);
/* 110 */       if (getPackage(packageName) == null) {
/*     */         try {
/* 112 */           definePackage(className, packageName);
/*     */         }
/* 114 */         catch (IllegalArgumentException ex) {
/*     */           
/* 116 */           if (getPackage(packageName) == null)
/*     */           {
/*     */ 
/*     */             
/* 120 */             throw new AssertionError("Package " + packageName + " has already been defined but it could not be found");
/*     */           }
/*     */         } 
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void definePackage(String className, String packageName) {
/*     */     try {
/* 130 */       AccessController.doPrivileged(() -> {
/*     */             String packageEntryName = packageName.replace('.', '/') + "/";
/*     */             
/*     */             String classEntryName = className.replace('.', '/') + ".class";
/*     */             
/*     */             for (URL url : getURLs()) {
/*     */               try {
/*     */                 URLConnection connection = url.openConnection();
/*     */                 if (connection instanceof JarURLConnection) {
/*     */                   JarFile jarFile = ((JarURLConnection)connection).getJarFile();
/*     */                   if (jarFile.getEntry(classEntryName) != null && jarFile.getEntry(packageEntryName) != null && jarFile.getManifest() != null) {
/*     */                     definePackage(packageName, jarFile.getManifest(), url);
/*     */                     return null;
/*     */                   } 
/*     */                 } 
/* 145 */               } catch (IOException iOException) {}
/*     */             } 
/*     */ 
/*     */             
/*     */             return null;
/* 150 */           }AccessController.getContext());
/*     */     }
/* 152 */     catch (PrivilegedActionException privilegedActionException) {}
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void clearCache() {
/* 161 */     for (URL url : getURLs()) {
/*     */       try {
/* 163 */         URLConnection connection = url.openConnection();
/* 164 */         if (connection instanceof JarURLConnection) {
/* 165 */           clearCache(connection);
/*     */         }
/*     */       }
/* 168 */       catch (IOException iOException) {}
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void clearCache(URLConnection connection) throws IOException {
/* 176 */     Object jarFile = ((JarURLConnection)connection).getJarFile();
/* 177 */     if (jarFile instanceof JarFile)
/* 178 */       ((JarFile)jarFile).clearCache(); 
/*     */   }
/*     */   
/*     */   private static class UseFastConnectionExceptionsEnumeration
/*     */     implements Enumeration<URL>
/*     */   {
/*     */     private final Enumeration<URL> delegate;
/*     */     
/*     */     UseFastConnectionExceptionsEnumeration(Enumeration<URL> delegate) {
/* 187 */       this.delegate = delegate;
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean hasMoreElements() {
/* 192 */       Handler.setUseFastConnectionExceptions(true);
/*     */       try {
/* 194 */         return this.delegate.hasMoreElements();
/*     */       } finally {
/*     */         
/* 197 */         Handler.setUseFastConnectionExceptions(false);
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public URL nextElement() {
/* 204 */       Handler.setUseFastConnectionExceptions(true);
/*     */       try {
/* 206 */         return this.delegate.nextElement();
/*     */       } finally {
/*     */         
/* 209 */         Handler.setUseFastConnectionExceptions(false);
/*     */       } 
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\aruizc01\Desktop\api-movilnet.war!\org\springframework\boot\loader\LaunchedURLClassLoader.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */