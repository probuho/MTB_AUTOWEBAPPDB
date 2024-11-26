/*     */ package org.springframework.boot.loader;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.net.URI;
/*     */ import java.net.URL;
/*     */ import java.security.CodeSource;
/*     */ import java.security.ProtectionDomain;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.springframework.boot.loader.archive.Archive;
/*     */ import org.springframework.boot.loader.archive.ExplodedArchive;
/*     */ import org.springframework.boot.loader.archive.JarFileArchive;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class Launcher
/*     */ {
/*     */   protected void launch(String[] args) throws Exception {
/*  49 */     JarFile.registerUrlProtocolHandler();
/*  50 */     ClassLoader classLoader = createClassLoader(getClassPathArchives());
/*  51 */     launch(args, getMainClass(), classLoader);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected ClassLoader createClassLoader(List<Archive> archives) throws Exception {
/*  61 */     List<URL> urls = new ArrayList<>(archives.size());
/*  62 */     for (Archive archive : archives) {
/*  63 */       urls.add(archive.getUrl());
/*     */     }
/*  65 */     return createClassLoader(urls.<URL>toArray(new URL[0]));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected ClassLoader createClassLoader(URL[] urls) throws Exception {
/*  75 */     return new LaunchedURLClassLoader(urls, getClass().getClassLoader());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void launch(String[] args, String mainClass, ClassLoader classLoader) throws Exception {
/*  86 */     Thread.currentThread().setContextClassLoader(classLoader);
/*  87 */     createMainMethodRunner(mainClass, args, classLoader).run();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected MainMethodRunner createMainMethodRunner(String mainClass, String[] args, ClassLoader classLoader) {
/*  98 */     return new MainMethodRunner(mainClass, args);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected abstract String getMainClass() throws Exception;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected abstract List<Archive> getClassPathArchives() throws Exception;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected final Archive createArchive() throws Exception {
/* 116 */     ProtectionDomain protectionDomain = getClass().getProtectionDomain();
/* 117 */     CodeSource codeSource = protectionDomain.getCodeSource();
/* 118 */     URI location = (codeSource != null) ? codeSource.getLocation().toURI() : null;
/* 119 */     String path = (location != null) ? location.getSchemeSpecificPart() : null;
/* 120 */     if (path == null) {
/* 121 */       throw new IllegalStateException("Unable to determine code source archive");
/*     */     }
/* 123 */     File root = new File(path);
/* 124 */     if (!root.exists()) {
/* 125 */       throw new IllegalStateException("Unable to determine code source archive from " + root);
/*     */     }
/* 127 */     return root.isDirectory() ? (Archive)new ExplodedArchive(root) : (Archive)new JarFileArchive(root);
/*     */   }
/*     */ }


/* Location:              C:\Users\aruizc01\Desktop\api-movilnet.war!\org\springframework\boot\loader\Launcher.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */