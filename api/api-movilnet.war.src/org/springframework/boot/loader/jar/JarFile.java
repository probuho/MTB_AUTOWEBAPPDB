/*     */ package org.springframework.boot.loader.jar;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.lang.ref.SoftReference;
/*     */ import java.net.MalformedURLException;
/*     */ import java.net.URL;
/*     */ import java.util.Enumeration;
/*     */ import java.util.Iterator;
/*     */ import java.util.function.Supplier;
/*     */ import java.util.jar.JarEntry;
/*     */ import java.util.jar.JarFile;
/*     */ import java.util.jar.JarInputStream;
/*     */ import java.util.jar.Manifest;
/*     */ import java.util.zip.ZipEntry;
/*     */ import org.springframework.boot.loader.data.RandomAccessData;
/*     */ import org.springframework.boot.loader.data.RandomAccessDataFile;
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
/*     */ public class JarFile
/*     */   extends JarFile
/*     */ {
/*     */   private static final String MANIFEST_NAME = "META-INF/MANIFEST.MF";
/*     */   private static final String PROTOCOL_HANDLER = "java.protocol.handler.pkgs";
/*     */   private static final String HANDLERS_PACKAGE = "org.springframework.boot.loader";
/*  59 */   private static final AsciiBytes META_INF = new AsciiBytes("META-INF/");
/*     */   
/*  61 */   private static final AsciiBytes SIGNATURE_FILE_EXTENSION = new AsciiBytes(".SF");
/*     */ 
/*     */   
/*     */   private final RandomAccessDataFile rootFile;
/*     */ 
/*     */   
/*     */   private final String pathFromRoot;
/*     */ 
/*     */   
/*     */   private final RandomAccessData data;
/*     */ 
/*     */   
/*     */   private final JarFileType type;
/*     */   
/*     */   private URL url;
/*     */   
/*     */   private String urlString;
/*     */   
/*     */   private JarFileEntries entries;
/*     */   
/*     */   private Supplier<Manifest> manifestSupplier;
/*     */   
/*     */   private SoftReference<Manifest> manifest;
/*     */   
/*     */   private boolean signed;
/*     */ 
/*     */   
/*     */   public JarFile(File file) throws IOException {
/*  89 */     this(new RandomAccessDataFile(file));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   JarFile(RandomAccessDataFile file) throws IOException {
/*  98 */     this(file, "", (RandomAccessData)file, JarFileType.DIRECT);
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
/*     */   private JarFile(RandomAccessDataFile rootFile, String pathFromRoot, RandomAccessData data, JarFileType type) throws IOException {
/* 112 */     this(rootFile, pathFromRoot, data, null, type, null);
/*     */   }
/*     */ 
/*     */   
/*     */   private JarFile(RandomAccessDataFile rootFile, String pathFromRoot, RandomAccessData data, JarEntryFilter filter, JarFileType type, Supplier<Manifest> manifestSupplier) throws IOException {
/* 117 */     super(rootFile.getFile());
/* 118 */     this.rootFile = rootFile;
/* 119 */     this.pathFromRoot = pathFromRoot;
/* 120 */     CentralDirectoryParser parser = new CentralDirectoryParser();
/* 121 */     this.entries = parser.<JarFileEntries>addVisitor(new JarFileEntries(this, filter));
/* 122 */     this.type = type;
/* 123 */     parser.addVisitor(centralDirectoryVisitor());
/*     */     try {
/* 125 */       this.data = parser.parse(data, (filter == null));
/*     */     }
/* 127 */     catch (RuntimeException ex) {
/* 128 */       close();
/* 129 */       throw ex;
/*     */     } 
/* 131 */     this.manifestSupplier = (manifestSupplier != null) ? manifestSupplier : (() -> {
/*     */         try (InputStream inputStream = getInputStream("META-INF/MANIFEST.MF")) {
/*     */           if (inputStream == null) {
/*     */             return null;
/*     */           }
/*     */           
/*     */           return new Manifest(inputStream);
/* 138 */         } catch (IOException ex) {
/*     */           throw new RuntimeException(ex);
/*     */         } 
/*     */       });
/*     */   }
/*     */   
/*     */   private CentralDirectoryVisitor centralDirectoryVisitor() {
/* 145 */     return new CentralDirectoryVisitor()
/*     */       {
/*     */         public void visitStart(CentralDirectoryEndRecord endRecord, RandomAccessData centralDirectoryData) {}
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         public void visitFileHeader(CentralDirectoryFileHeader fileHeader, int dataOffset) {
/* 153 */           AsciiBytes name = fileHeader.getName();
/* 154 */           if (name.startsWith(JarFile.META_INF) && name.endsWith(JarFile.SIGNATURE_FILE_EXTENSION)) {
/* 155 */             JarFile.this.signed = true;
/*     */           }
/*     */         }
/*     */ 
/*     */ 
/*     */         
/*     */         public void visitEnd() {}
/*     */       };
/*     */   }
/*     */ 
/*     */   
/*     */   protected final RandomAccessDataFile getRootJarFile() {
/* 167 */     return this.rootFile;
/*     */   }
/*     */   
/*     */   RandomAccessData getData() {
/* 171 */     return this.data;
/*     */   }
/*     */ 
/*     */   
/*     */   public Manifest getManifest() throws IOException {
/* 176 */     Manifest manifest = (this.manifest != null) ? this.manifest.get() : null;
/* 177 */     if (manifest == null) {
/*     */       try {
/* 179 */         manifest = this.manifestSupplier.get();
/*     */       }
/* 181 */       catch (RuntimeException ex) {
/* 182 */         throw new IOException(ex);
/*     */       } 
/* 184 */       this.manifest = new SoftReference<>(manifest);
/*     */     } 
/* 186 */     return manifest;
/*     */   }
/*     */ 
/*     */   
/*     */   public Enumeration<JarEntry> entries() {
/* 191 */     final Iterator<JarEntry> iterator = this.entries.iterator();
/* 192 */     return new Enumeration<JarEntry>()
/*     */       {
/*     */         public boolean hasMoreElements()
/*     */         {
/* 196 */           return iterator.hasNext();
/*     */         }
/*     */ 
/*     */         
/*     */         public JarEntry nextElement() {
/* 201 */           return iterator.next();
/*     */         }
/*     */       };
/*     */   }
/*     */ 
/*     */   
/*     */   public JarEntry getJarEntry(CharSequence name) {
/* 208 */     return this.entries.getEntry(name);
/*     */   }
/*     */ 
/*     */   
/*     */   public JarEntry getJarEntry(String name) {
/* 213 */     return (JarEntry)getEntry(name);
/*     */   }
/*     */   
/*     */   public boolean containsEntry(String name) {
/* 217 */     return this.entries.containsEntry(name);
/*     */   }
/*     */ 
/*     */   
/*     */   public ZipEntry getEntry(String name) {
/* 222 */     return this.entries.getEntry(name);
/*     */   }
/*     */ 
/*     */   
/*     */   public synchronized InputStream getInputStream(ZipEntry entry) throws IOException {
/* 227 */     if (entry instanceof JarEntry) {
/* 228 */       return this.entries.getInputStream((JarEntry)entry);
/*     */     }
/* 230 */     return getInputStream((entry != null) ? entry.getName() : null);
/*     */   }
/*     */   
/*     */   InputStream getInputStream(String name) throws IOException {
/* 234 */     return this.entries.getInputStream(name);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized JarFile getNestedJarFile(ZipEntry entry) throws IOException {
/* 244 */     return getNestedJarFile((JarEntry)entry);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized JarFile getNestedJarFile(JarEntry entry) throws IOException {
/*     */     try {
/* 255 */       return createJarFileFromEntry(entry);
/*     */     }
/* 257 */     catch (Exception ex) {
/* 258 */       throw new IOException("Unable to open nested jar file '" + entry.getName() + "'", ex);
/*     */     } 
/*     */   }
/*     */   
/*     */   private JarFile createJarFileFromEntry(JarEntry entry) throws IOException {
/* 263 */     if (entry.isDirectory()) {
/* 264 */       return createJarFileFromDirectoryEntry(entry);
/*     */     }
/* 266 */     return createJarFileFromFileEntry(entry);
/*     */   }
/*     */   
/*     */   private JarFile createJarFileFromDirectoryEntry(JarEntry entry) throws IOException {
/* 270 */     AsciiBytes name = entry.getAsciiBytesName();
/* 271 */     JarEntryFilter filter = candidate -> 
/* 272 */       (candidate.startsWith(name) && !candidate.equals(name)) ? candidate.substring(name.length()) : null;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 277 */     return new JarFile(this.rootFile, this.pathFromRoot + "!/" + entry.getName().substring(0, name.length() - 1), this.data, filter, JarFileType.NESTED_DIRECTORY, this.manifestSupplier);
/*     */   }
/*     */ 
/*     */   
/*     */   private JarFile createJarFileFromFileEntry(JarEntry entry) throws IOException {
/* 282 */     if (entry.getMethod() != 0) {
/* 283 */       throw new IllegalStateException("Unable to open nested entry '" + entry
/* 284 */           .getName() + "'. It has been compressed and nested jar files must be stored without compression. Please check the mechanism used to create your executable jar file");
/*     */     }
/*     */ 
/*     */     
/* 288 */     RandomAccessData entryData = this.entries.getEntryData(entry.getName());
/* 289 */     return new JarFile(this.rootFile, this.pathFromRoot + "!/" + entry.getName(), entryData, JarFileType.NESTED_JAR);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int size() {
/* 295 */     return this.entries.getSize();
/*     */   }
/*     */ 
/*     */   
/*     */   public void close() throws IOException {
/* 300 */     super.close();
/* 301 */     if (this.type == JarFileType.DIRECT) {
/* 302 */       this.rootFile.close();
/*     */     }
/*     */   }
/*     */   
/*     */   String getUrlString() throws MalformedURLException {
/* 307 */     if (this.urlString == null) {
/* 308 */       this.urlString = getUrl().toString();
/*     */     }
/* 310 */     return this.urlString;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public URL getUrl() throws MalformedURLException {
/* 320 */     if (this.url == null) {
/* 321 */       Handler handler = new Handler(this);
/* 322 */       String file = this.rootFile.getFile().toURI() + this.pathFromRoot + "!/";
/* 323 */       file = file.replace("file:////", "file://");
/* 324 */       this.url = new URL("jar", "", -1, file, handler);
/*     */     } 
/* 326 */     return this.url;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 331 */     return getName();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getName() {
/* 336 */     return this.rootFile.getFile() + this.pathFromRoot;
/*     */   }
/*     */   
/*     */   boolean isSigned() {
/* 340 */     return this.signed;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setupEntryCertificates(JarEntry entry) {
/* 347 */     try (JarInputStream inputStream = new JarInputStream(getData().getInputStream())) {
/* 348 */       JarEntry certEntry = inputStream.getNextJarEntry();
/* 349 */       while (certEntry != null) {
/* 350 */         inputStream.closeEntry();
/* 351 */         if (entry.getName().equals(certEntry.getName())) {
/* 352 */           setCertificates(entry, certEntry);
/*     */         }
/* 354 */         setCertificates(getJarEntry(certEntry.getName()), certEntry);
/* 355 */         certEntry = inputStream.getNextJarEntry();
/*     */       }
/*     */     
/*     */     }
/* 359 */     catch (IOException ex) {
/* 360 */       throw new IllegalStateException(ex);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void setCertificates(JarEntry entry, JarEntry certEntry) {
/* 365 */     if (entry != null) {
/* 366 */       entry.setCertificates(certEntry);
/*     */     }
/*     */   }
/*     */   
/*     */   public void clearCache() {
/* 371 */     this.entries.clearCache();
/*     */   }
/*     */   
/*     */   protected String getPathFromRoot() {
/* 375 */     return this.pathFromRoot;
/*     */   }
/*     */   
/*     */   JarFileType getType() {
/* 379 */     return this.type;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void registerUrlProtocolHandler() {
/* 387 */     String handlers = System.getProperty("java.protocol.handler.pkgs", "");
/* 388 */     System.setProperty("java.protocol.handler.pkgs", 
/* 389 */         "".equals(handlers) ? "org.springframework.boot.loader" : (handlers + "|" + "org.springframework.boot.loader"));
/* 390 */     resetCachedUrlHandlers();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void resetCachedUrlHandlers() {
/*     */     try {
/* 400 */       URL.setURLStreamHandlerFactory(null);
/*     */     }
/* 402 */     catch (Error error) {}
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   enum JarFileType
/*     */   {
/* 412 */     DIRECT, NESTED_DIRECTORY, NESTED_JAR;
/*     */   }
/*     */ }


/* Location:              C:\Users\aruizc01\Desktop\api-movilnet.war!\org\springframework\boot\loader\jar\JarFile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */