/*     */ package org.springframework.boot.loader.jar;
/*     */ 
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.FilePermission;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import java.net.JarURLConnection;
/*     */ import java.net.MalformedURLException;
/*     */ import java.net.URL;
/*     */ import java.net.URLConnection;
/*     */ import java.net.URLEncoder;
/*     */ import java.net.URLStreamHandler;
/*     */ import java.security.Permission;
/*     */ import java.util.jar.JarEntry;
/*     */ import java.util.jar.JarFile;
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
/*     */ final class JarURLConnection
/*     */   extends JarURLConnection
/*     */ {
/*  41 */   private static ThreadLocal<Boolean> useFastExceptions = new ThreadLocal<>();
/*     */   
/*  43 */   private static final FileNotFoundException FILE_NOT_FOUND_EXCEPTION = new FileNotFoundException("Jar file or entry not found");
/*     */ 
/*     */   
/*  46 */   private static final IllegalStateException NOT_FOUND_CONNECTION_EXCEPTION = new IllegalStateException(FILE_NOT_FOUND_EXCEPTION);
/*     */   
/*     */   private static final String SEPARATOR = "!/";
/*     */   
/*     */   private static final URL EMPTY_JAR_URL;
/*     */ 
/*     */   
/*     */   static {
/*     */     try {
/*  55 */       EMPTY_JAR_URL = new URL("jar:", null, 0, "file:!/", new URLStreamHandler()
/*     */           {
/*     */             
/*     */             protected URLConnection openConnection(URL u) throws IOException
/*     */             {
/*  60 */               return null;
/*     */             }
/*     */           });
/*     */     }
/*  64 */     catch (MalformedURLException ex) {
/*  65 */       throw new IllegalStateException(ex);
/*     */     } 
/*     */   }
/*     */   
/*  69 */   private static final JarEntryName EMPTY_JAR_ENTRY_NAME = new JarEntryName(new StringSequence(""));
/*     */   
/*     */   private static final String READ_ACTION = "read";
/*     */   
/*  73 */   private static final JarURLConnection NOT_FOUND_CONNECTION = notFound();
/*     */   
/*     */   private final JarFile jarFile;
/*     */   
/*     */   private Permission permission;
/*     */   
/*     */   private URL jarFileUrl;
/*     */   
/*     */   private final JarEntryName jarEntryName;
/*     */   
/*     */   private JarEntry jarEntry;
/*     */ 
/*     */   
/*     */   private JarURLConnection(URL url, JarFile jarFile, JarEntryName jarEntryName) throws IOException {
/*  87 */     super(EMPTY_JAR_URL);
/*  88 */     this.url = url;
/*  89 */     this.jarFile = jarFile;
/*  90 */     this.jarEntryName = jarEntryName;
/*     */   }
/*     */ 
/*     */   
/*     */   public void connect() throws IOException {
/*  95 */     if (this.jarFile == null) {
/*  96 */       throw FILE_NOT_FOUND_EXCEPTION;
/*     */     }
/*  98 */     if (!this.jarEntryName.isEmpty() && this.jarEntry == null) {
/*  99 */       this.jarEntry = this.jarFile.getJarEntry(getEntryName());
/* 100 */       if (this.jarEntry == null) {
/* 101 */         throwFileNotFound(this.jarEntryName, this.jarFile);
/*     */       }
/*     */     } 
/* 104 */     this.connected = true;
/*     */   }
/*     */ 
/*     */   
/*     */   public JarFile getJarFile() throws IOException {
/* 109 */     connect();
/* 110 */     return this.jarFile;
/*     */   }
/*     */ 
/*     */   
/*     */   public URL getJarFileURL() {
/* 115 */     if (this.jarFile == null) {
/* 116 */       throw NOT_FOUND_CONNECTION_EXCEPTION;
/*     */     }
/* 118 */     if (this.jarFileUrl == null) {
/* 119 */       this.jarFileUrl = buildJarFileUrl();
/*     */     }
/* 121 */     return this.jarFileUrl;
/*     */   }
/*     */   
/*     */   private URL buildJarFileUrl() {
/*     */     try {
/* 126 */       String spec = this.jarFile.getUrl().getFile();
/* 127 */       if (spec.endsWith("!/")) {
/* 128 */         spec = spec.substring(0, spec.length() - "!/".length());
/*     */       }
/* 130 */       if (spec.indexOf("!/") == -1) {
/* 131 */         return new URL(spec);
/*     */       }
/* 133 */       return new URL("jar:" + spec);
/*     */     }
/* 135 */     catch (MalformedURLException ex) {
/* 136 */       throw new IllegalStateException(ex);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public JarEntry getJarEntry() throws IOException {
/* 142 */     if (this.jarEntryName == null || this.jarEntryName.isEmpty()) {
/* 143 */       return null;
/*     */     }
/* 145 */     connect();
/* 146 */     return this.jarEntry;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getEntryName() {
/* 151 */     if (this.jarFile == null) {
/* 152 */       throw NOT_FOUND_CONNECTION_EXCEPTION;
/*     */     }
/* 154 */     return this.jarEntryName.toString();
/*     */   }
/*     */ 
/*     */   
/*     */   public InputStream getInputStream() throws IOException {
/* 159 */     if (this.jarFile == null) {
/* 160 */       throw FILE_NOT_FOUND_EXCEPTION;
/*     */     }
/* 162 */     if (this.jarEntryName.isEmpty() && this.jarFile.getType() == JarFile.JarFileType.DIRECT) {
/* 163 */       throw new IOException("no entry name specified");
/*     */     }
/* 165 */     connect();
/*     */     
/* 167 */     InputStream inputStream = this.jarEntryName.isEmpty() ? this.jarFile.getData().getInputStream() : this.jarFile.getInputStream(this.jarEntry);
/* 168 */     if (inputStream == null) {
/* 169 */       throwFileNotFound(this.jarEntryName, this.jarFile);
/*     */     }
/* 171 */     return inputStream;
/*     */   }
/*     */   
/*     */   private void throwFileNotFound(Object entry, JarFile jarFile) throws FileNotFoundException {
/* 175 */     if (Boolean.TRUE.equals(useFastExceptions.get())) {
/* 176 */       throw FILE_NOT_FOUND_EXCEPTION;
/*     */     }
/* 178 */     throw new FileNotFoundException("JAR entry " + entry + " not found in " + jarFile.getName());
/*     */   }
/*     */ 
/*     */   
/*     */   public int getContentLength() {
/* 183 */     long length = getContentLengthLong();
/* 184 */     if (length > 2147483647L) {
/* 185 */       return -1;
/*     */     }
/* 187 */     return (int)length;
/*     */   }
/*     */ 
/*     */   
/*     */   public long getContentLengthLong() {
/* 192 */     if (this.jarFile == null) {
/* 193 */       return -1L;
/*     */     }
/*     */     try {
/* 196 */       if (this.jarEntryName.isEmpty()) {
/* 197 */         return this.jarFile.size();
/*     */       }
/* 199 */       JarEntry entry = getJarEntry();
/* 200 */       return (entry != null) ? (int)entry.getSize() : -1L;
/*     */     }
/* 202 */     catch (IOException ex) {
/* 203 */       return -1L;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public Object getContent() throws IOException {
/* 209 */     connect();
/* 210 */     return this.jarEntryName.isEmpty() ? this.jarFile : super.getContent();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getContentType() {
/* 215 */     return (this.jarEntryName != null) ? this.jarEntryName.getContentType() : null;
/*     */   }
/*     */ 
/*     */   
/*     */   public Permission getPermission() throws IOException {
/* 220 */     if (this.jarFile == null) {
/* 221 */       throw FILE_NOT_FOUND_EXCEPTION;
/*     */     }
/* 223 */     if (this.permission == null) {
/* 224 */       this.permission = new FilePermission(this.jarFile.getRootJarFile().getFile().getPath(), "read");
/*     */     }
/* 226 */     return this.permission;
/*     */   }
/*     */ 
/*     */   
/*     */   public long getLastModified() {
/* 231 */     if (this.jarFile == null || this.jarEntryName.isEmpty()) {
/* 232 */       return 0L;
/*     */     }
/*     */     try {
/* 235 */       JarEntry entry = getJarEntry();
/* 236 */       return (entry != null) ? entry.getTime() : 0L;
/*     */     }
/* 238 */     catch (IOException ex) {
/* 239 */       return 0L;
/*     */     } 
/*     */   }
/*     */   
/*     */   static void setUseFastExceptions(boolean useFastExceptions) {
/* 244 */     JarURLConnection.useFastExceptions.set(Boolean.valueOf(useFastExceptions));
/*     */   }
/*     */   
/*     */   static JarURLConnection get(URL url, JarFile jarFile) throws IOException {
/* 248 */     StringSequence spec = new StringSequence(url.getFile());
/* 249 */     int index = indexOfRootSpec(spec, jarFile.getPathFromRoot());
/* 250 */     if (index == -1) {
/* 251 */       return Boolean.TRUE.equals(useFastExceptions.get()) ? NOT_FOUND_CONNECTION : new JarURLConnection(url, null, EMPTY_JAR_ENTRY_NAME);
/*     */     }
/*     */     
/*     */     int separator;
/* 255 */     while ((separator = spec.indexOf("!/", index)) > 0) {
/* 256 */       JarEntryName entryName = JarEntryName.get(spec.subSequence(index, separator));
/* 257 */       JarEntry jarEntry = jarFile.getJarEntry(entryName.toCharSequence());
/* 258 */       if (jarEntry == null) {
/* 259 */         return notFound(jarFile, entryName);
/*     */       }
/* 261 */       jarFile = jarFile.getNestedJarFile(jarEntry);
/* 262 */       index = separator + "!/".length();
/*     */     } 
/* 264 */     JarEntryName jarEntryName = JarEntryName.get(spec, index);
/* 265 */     if (Boolean.TRUE.equals(useFastExceptions.get()) && !jarEntryName.isEmpty() && 
/* 266 */       !jarFile.containsEntry(jarEntryName.toString())) {
/* 267 */       return NOT_FOUND_CONNECTION;
/*     */     }
/* 269 */     return new JarURLConnection(url, jarFile, jarEntryName);
/*     */   }
/*     */   
/*     */   private static int indexOfRootSpec(StringSequence file, String pathFromRoot) {
/* 273 */     int separatorIndex = file.indexOf("!/");
/* 274 */     if (separatorIndex < 0 || !file.startsWith(pathFromRoot, separatorIndex)) {
/* 275 */       return -1;
/*     */     }
/* 277 */     return separatorIndex + "!/".length() + pathFromRoot.length();
/*     */   }
/*     */   
/*     */   private static JarURLConnection notFound() {
/*     */     try {
/* 282 */       return notFound((JarFile)null, (JarEntryName)null);
/*     */     }
/* 284 */     catch (IOException ex) {
/* 285 */       throw new IllegalStateException(ex);
/*     */     } 
/*     */   }
/*     */   
/*     */   private static JarURLConnection notFound(JarFile jarFile, JarEntryName jarEntryName) throws IOException {
/* 290 */     if (Boolean.TRUE.equals(useFastExceptions.get())) {
/* 291 */       return NOT_FOUND_CONNECTION;
/*     */     }
/* 293 */     return new JarURLConnection(null, jarFile, jarEntryName);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   static class JarEntryName
/*     */   {
/*     */     private final StringSequence name;
/*     */     
/*     */     private String contentType;
/*     */ 
/*     */     
/*     */     JarEntryName(StringSequence spec) {
/* 306 */       this.name = decode(spec);
/*     */     }
/*     */     
/*     */     private StringSequence decode(StringSequence source) {
/* 310 */       if (source.isEmpty() || source.indexOf('%') < 0) {
/* 311 */         return source;
/*     */       }
/* 313 */       ByteArrayOutputStream bos = new ByteArrayOutputStream(source.length());
/* 314 */       write(source.toString(), bos);
/*     */       
/* 316 */       return new StringSequence(AsciiBytes.toString(bos.toByteArray()));
/*     */     }
/*     */     
/*     */     private void write(String source, ByteArrayOutputStream outputStream) {
/* 320 */       int length = source.length();
/* 321 */       for (int i = 0; i < length; i++) {
/* 322 */         int c = source.charAt(i);
/* 323 */         if (c > 127) {
/*     */           try {
/* 325 */             String encoded = URLEncoder.encode(String.valueOf((char)c), "UTF-8");
/* 326 */             write(encoded, outputStream);
/*     */           }
/* 328 */           catch (UnsupportedEncodingException ex) {
/* 329 */             throw new IllegalStateException(ex);
/*     */           } 
/*     */         } else {
/*     */           
/* 333 */           if (c == 37) {
/* 334 */             if (i + 2 >= length) {
/* 335 */               throw new IllegalArgumentException("Invalid encoded sequence \"" + source
/* 336 */                   .substring(i) + "\"");
/*     */             }
/* 338 */             c = decodeEscapeSequence(source, i);
/* 339 */             i += 2;
/*     */           } 
/* 341 */           outputStream.write(c);
/*     */         } 
/*     */       } 
/*     */     }
/*     */     
/*     */     private char decodeEscapeSequence(String source, int i) {
/* 347 */       int hi = Character.digit(source.charAt(i + 1), 16);
/* 348 */       int lo = Character.digit(source.charAt(i + 2), 16);
/* 349 */       if (hi == -1 || lo == -1) {
/* 350 */         throw new IllegalArgumentException("Invalid encoded sequence \"" + source.substring(i) + "\"");
/*     */       }
/* 352 */       return (char)((hi << 4) + lo);
/*     */     }
/*     */     
/*     */     public CharSequence toCharSequence() {
/* 356 */       return this.name;
/*     */     }
/*     */ 
/*     */     
/*     */     public String toString() {
/* 361 */       return this.name.toString();
/*     */     }
/*     */     
/*     */     public boolean isEmpty() {
/* 365 */       return this.name.isEmpty();
/*     */     }
/*     */     
/*     */     public String getContentType() {
/* 369 */       if (this.contentType == null) {
/* 370 */         this.contentType = deduceContentType();
/*     */       }
/* 372 */       return this.contentType;
/*     */     }
/*     */ 
/*     */     
/*     */     private String deduceContentType() {
/* 377 */       String type = isEmpty() ? "x-java/jar" : null;
/* 378 */       type = (type != null) ? type : URLConnection.guessContentTypeFromName(toString());
/* 379 */       type = (type != null) ? type : "content/unknown";
/* 380 */       return type;
/*     */     }
/*     */     
/*     */     public static JarEntryName get(StringSequence spec) {
/* 384 */       return get(spec, 0);
/*     */     }
/*     */     
/*     */     public static JarEntryName get(StringSequence spec, int beginIndex) {
/* 388 */       if (spec.length() <= beginIndex) {
/* 389 */         return JarURLConnection.EMPTY_JAR_ENTRY_NAME;
/*     */       }
/* 391 */       return new JarEntryName(spec.subSequence(beginIndex));
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\aruizc01\Desktop\api-movilnet.war!\org\springframework\boot\loader\jar\JarURLConnection.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */