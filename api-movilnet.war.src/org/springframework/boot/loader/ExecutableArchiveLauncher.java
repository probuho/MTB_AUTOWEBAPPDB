/*    */ package org.springframework.boot.loader;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import java.util.jar.Manifest;
/*    */ import org.springframework.boot.loader.archive.Archive;
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
/*    */ public abstract class ExecutableArchiveLauncher
/*    */   extends Launcher
/*    */ {
/*    */   private final Archive archive;
/*    */   
/*    */   public ExecutableArchiveLauncher() {
/*    */     try {
/* 39 */       this.archive = createArchive();
/*    */     }
/* 41 */     catch (Exception ex) {
/* 42 */       throw new IllegalStateException(ex);
/*    */     } 
/*    */   }
/*    */   
/*    */   protected ExecutableArchiveLauncher(Archive archive) {
/* 47 */     this.archive = archive;
/*    */   }
/*    */   
/*    */   protected final Archive getArchive() {
/* 51 */     return this.archive;
/*    */   }
/*    */ 
/*    */   
/*    */   protected String getMainClass() throws Exception {
/* 56 */     Manifest manifest = this.archive.getManifest();
/* 57 */     String mainClass = null;
/* 58 */     if (manifest != null) {
/* 59 */       mainClass = manifest.getMainAttributes().getValue("Start-Class");
/*    */     }
/* 61 */     if (mainClass == null) {
/* 62 */       throw new IllegalStateException("No 'Start-Class' manifest entry specified in " + this);
/*    */     }
/* 64 */     return mainClass;
/*    */   }
/*    */ 
/*    */   
/*    */   protected List<Archive> getClassPathArchives() throws Exception {
/* 69 */     List<Archive> archives = new ArrayList<>(this.archive.getNestedArchives(this::isNestedArchive));
/* 70 */     postProcessClassPathArchives(archives);
/* 71 */     return archives;
/*    */   }
/*    */   
/*    */   protected abstract boolean isNestedArchive(Archive.Entry paramEntry);
/*    */   
/*    */   protected void postProcessClassPathArchives(List<Archive> archives) throws Exception {}
/*    */ }


/* Location:              C:\Users\aruizc01\Desktop\api-movilnet.war!\org\springframework\boot\loader\ExecutableArchiveLauncher.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */