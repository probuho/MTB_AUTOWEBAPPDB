/*     */ package org.springframework.boot.loader.archive;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.net.MalformedURLException;
/*     */ import java.net.URL;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.Enumeration;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.UUID;
/*     */ import java.util.jar.JarEntry;
/*     */ import java.util.jar.Manifest;
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
/*     */ public class JarFileArchive
/*     */   implements Archive
/*     */ {
/*     */   private static final String UNPACK_MARKER = "UNPACK:";
/*     */   private static final int BUFFER_SIZE = 32768;
/*     */   private final JarFile jarFile;
/*     */   private URL url;
/*     */   private File tempUnpackFolder;
/*     */   
/*     */   public JarFileArchive(File file) throws IOException {
/*  57 */     this(file, null);
/*     */   }
/*     */   
/*     */   public JarFileArchive(File file, URL url) throws IOException {
/*  61 */     this(new JarFile(file));
/*  62 */     this.url = url;
/*     */   }
/*     */   
/*     */   public JarFileArchive(JarFile jarFile) {
/*  66 */     this.jarFile = jarFile;
/*     */   }
/*     */ 
/*     */   
/*     */   public URL getUrl() throws MalformedURLException {
/*  71 */     if (this.url != null) {
/*  72 */       return this.url;
/*     */     }
/*  74 */     return this.jarFile.getUrl();
/*     */   }
/*     */ 
/*     */   
/*     */   public Manifest getManifest() throws IOException {
/*  79 */     return this.jarFile.getManifest();
/*     */   }
/*     */ 
/*     */   
/*     */   public List<Archive> getNestedArchives(Archive.EntryFilter filter) throws IOException {
/*  84 */     List<Archive> nestedArchives = new ArrayList<>();
/*  85 */     for (Archive.Entry entry : this) {
/*  86 */       if (filter.matches(entry)) {
/*  87 */         nestedArchives.add(getNestedArchive(entry));
/*     */       }
/*     */     } 
/*  90 */     return Collections.unmodifiableList(nestedArchives);
/*     */   }
/*     */ 
/*     */   
/*     */   public Iterator<Archive.Entry> iterator() {
/*  95 */     return new EntryIterator(this.jarFile.entries());
/*     */   }
/*     */   
/*     */   protected Archive getNestedArchive(Archive.Entry entry) throws IOException {
/*  99 */     JarEntry jarEntry = ((JarFileEntry)entry).getJarEntry();
/* 100 */     if (jarEntry.getComment().startsWith("UNPACK:")) {
/* 101 */       return getUnpackedNestedArchive(jarEntry);
/*     */     }
/*     */     try {
/* 104 */       JarFile jarFile = this.jarFile.getNestedJarFile(jarEntry);
/* 105 */       return new JarFileArchive(jarFile);
/*     */     }
/* 107 */     catch (Exception ex) {
/* 108 */       throw new IllegalStateException("Failed to get nested archive for entry " + entry.getName(), ex);
/*     */     } 
/*     */   }
/*     */   
/*     */   private Archive getUnpackedNestedArchive(JarEntry jarEntry) throws IOException {
/* 113 */     String name = jarEntry.getName();
/* 114 */     if (name.lastIndexOf('/') != -1) {
/* 115 */       name = name.substring(name.lastIndexOf('/') + 1);
/*     */     }
/* 117 */     File file = new File(getTempUnpackFolder(), name);
/* 118 */     if (!file.exists() || file.length() != jarEntry.getSize()) {
/* 119 */       unpack(jarEntry, file);
/*     */     }
/* 121 */     return new JarFileArchive(file, file.toURI().toURL());
/*     */   }
/*     */   
/*     */   private File getTempUnpackFolder() {
/* 125 */     if (this.tempUnpackFolder == null) {
/* 126 */       File tempFolder = new File(System.getProperty("java.io.tmpdir"));
/* 127 */       this.tempUnpackFolder = createUnpackFolder(tempFolder);
/*     */     } 
/* 129 */     return this.tempUnpackFolder;
/*     */   }
/*     */   
/*     */   private File createUnpackFolder(File parent) {
/* 133 */     int attempts = 0;
/* 134 */     while (attempts++ < 1000) {
/* 135 */       String fileName = (new File(this.jarFile.getName())).getName();
/* 136 */       File unpackFolder = new File(parent, fileName + "-spring-boot-libs-" + UUID.randomUUID());
/* 137 */       if (unpackFolder.mkdirs()) {
/* 138 */         return unpackFolder;
/*     */       }
/*     */     } 
/* 141 */     throw new IllegalStateException("Failed to create unpack folder in directory '" + parent + "'");
/*     */   }
/*     */   
/*     */   private void unpack(JarEntry entry, File file) throws IOException {
/* 145 */     try(InputStream inputStream = this.jarFile.getInputStream(entry); 
/* 146 */         OutputStream outputStream = new FileOutputStream(file)) {
/* 147 */       byte[] buffer = new byte[32768];
/*     */       int bytesRead;
/* 149 */       while ((bytesRead = inputStream.read(buffer)) != -1) {
/* 150 */         outputStream.write(buffer, 0, bytesRead);
/*     */       }
/* 152 */       outputStream.flush();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/*     */     try {
/* 159 */       return getUrl().toString();
/*     */     }
/* 161 */     catch (Exception ex) {
/* 162 */       return "jar archive";
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private static class EntryIterator
/*     */     implements Iterator<Archive.Entry>
/*     */   {
/*     */     private final Enumeration<JarEntry> enumeration;
/*     */ 
/*     */     
/*     */     EntryIterator(Enumeration<JarEntry> enumeration) {
/* 174 */       this.enumeration = enumeration;
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean hasNext() {
/* 179 */       return this.enumeration.hasMoreElements();
/*     */     }
/*     */ 
/*     */     
/*     */     public Archive.Entry next() {
/* 184 */       return new JarFileArchive.JarFileEntry(this.enumeration.nextElement());
/*     */     }
/*     */ 
/*     */     
/*     */     public void remove() {
/* 189 */       throw new UnsupportedOperationException("remove");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static class JarFileEntry
/*     */     implements Archive.Entry
/*     */   {
/*     */     private final JarEntry jarEntry;
/*     */ 
/*     */     
/*     */     JarFileEntry(JarEntry jarEntry) {
/* 202 */       this.jarEntry = jarEntry;
/*     */     }
/*     */     
/*     */     public JarEntry getJarEntry() {
/* 206 */       return this.jarEntry;
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean isDirectory() {
/* 211 */       return this.jarEntry.isDirectory();
/*     */     }
/*     */ 
/*     */     
/*     */     public String getName() {
/* 216 */       return this.jarEntry.getName();
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\aruizc01\Desktop\api-movilnet.war!\org\springframework\boot\loader\archive\JarFileArchive.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */