/*     */ package org.springframework.boot.loader.jar;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.lang.ref.SoftReference;
/*     */ import java.net.MalformedURLException;
/*     */ import java.net.URI;
/*     */ import java.net.URL;
/*     */ import java.net.URLConnection;
/*     */ import java.net.URLStreamHandler;
/*     */ import java.util.Map;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ import java.util.regex.Pattern;
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
/*     */ public class Handler
/*     */   extends URLStreamHandler
/*     */ {
/*     */   private static final String JAR_PROTOCOL = "jar:";
/*     */   private static final String FILE_PROTOCOL = "file:";
/*     */   private static final String SEPARATOR = "!/";
/*     */   private static final String CURRENT_DIR = "/./";
/*  54 */   private static final Pattern CURRENT_DIR_PATTERN = Pattern.compile("/./", 16);
/*     */   
/*     */   private static final String PARENT_DIR = "/../";
/*     */   
/*  58 */   private static final String[] FALLBACK_HANDLERS = new String[] { "sun.net.www.protocol.jar.Handler" };
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  63 */   private static SoftReference<Map<File, JarFile>> rootFileCache = new SoftReference<>(null);
/*     */   
/*     */   private final JarFile jarFile;
/*     */   
/*     */   private URLStreamHandler fallbackHandler;
/*     */ 
/*     */   
/*     */   public Handler() {
/*  71 */     this(null);
/*     */   }
/*     */   
/*     */   public Handler(JarFile jarFile) {
/*  75 */     this.jarFile = jarFile;
/*     */   }
/*     */ 
/*     */   
/*     */   protected URLConnection openConnection(URL url) throws IOException {
/*  80 */     if (this.jarFile != null && isUrlInJarFile(url, this.jarFile)) {
/*  81 */       return JarURLConnection.get(url, this.jarFile);
/*     */     }
/*     */     try {
/*  84 */       return JarURLConnection.get(url, getRootJarFileFromUrl(url));
/*     */     }
/*  86 */     catch (Exception ex) {
/*  87 */       return openFallbackConnection(url, ex);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean isUrlInJarFile(URL url, JarFile jarFile) throws MalformedURLException {
/*  93 */     return (url.getPath().startsWith(jarFile.getUrl().getPath()) && url
/*  94 */       .toString().startsWith(jarFile.getUrlString()));
/*     */   }
/*     */   
/*     */   private URLConnection openFallbackConnection(URL url, Exception reason) throws IOException {
/*     */     try {
/*  99 */       return openConnection(getFallbackHandler(), url);
/*     */     }
/* 101 */     catch (Exception ex) {
/* 102 */       if (reason instanceof IOException) {
/* 103 */         log(false, "Unable to open fallback handler", ex);
/* 104 */         throw (IOException)reason;
/*     */       } 
/* 106 */       log(true, "Unable to open fallback handler", ex);
/* 107 */       if (reason instanceof RuntimeException) {
/* 108 */         throw (RuntimeException)reason;
/*     */       }
/* 110 */       throw new IllegalStateException(reason);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void log(boolean warning, String message, Exception cause) {
/*     */     try {
/* 116 */       Level level = warning ? Level.WARNING : Level.FINEST;
/* 117 */       Logger.getLogger(getClass().getName()).log(level, message, cause);
/*     */     }
/* 119 */     catch (Exception ex) {
/* 120 */       if (warning) {
/* 121 */         System.err.println("WARNING: " + message);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private URLStreamHandler getFallbackHandler() {
/* 127 */     if (this.fallbackHandler != null) {
/* 128 */       return this.fallbackHandler;
/*     */     }
/* 130 */     for (String handlerClassName : FALLBACK_HANDLERS) {
/*     */       try {
/* 132 */         Class<?> handlerClass = Class.forName(handlerClassName);
/* 133 */         this.fallbackHandler = (URLStreamHandler)handlerClass.newInstance();
/* 134 */         return this.fallbackHandler;
/*     */       }
/* 136 */       catch (Exception exception) {}
/*     */     } 
/*     */ 
/*     */     
/* 140 */     throw new IllegalStateException("Unable to find fallback handler");
/*     */   }
/*     */   
/*     */   private URLConnection openConnection(URLStreamHandler handler, URL url) throws Exception {
/* 144 */     return (new URL(null, url.toExternalForm(), handler)).openConnection();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void parseURL(URL context, String spec, int start, int limit) {
/* 149 */     if (spec.regionMatches(true, 0, "jar:", 0, "jar:".length())) {
/* 150 */       setFile(context, getFileFromSpec(spec.substring(start, limit)));
/*     */     } else {
/*     */       
/* 153 */       setFile(context, getFileFromContext(context, spec.substring(start, limit)));
/*     */     } 
/*     */   }
/*     */   
/*     */   private String getFileFromSpec(String spec) {
/* 158 */     int separatorIndex = spec.lastIndexOf("!/");
/* 159 */     if (separatorIndex == -1) {
/* 160 */       throw new IllegalArgumentException("No !/ in spec '" + spec + "'");
/*     */     }
/*     */     try {
/* 163 */       new URL(spec.substring(0, separatorIndex));
/* 164 */       return spec;
/*     */     }
/* 166 */     catch (MalformedURLException ex) {
/* 167 */       throw new IllegalArgumentException("Invalid spec URL '" + spec + "'", ex);
/*     */     } 
/*     */   }
/*     */   
/*     */   private String getFileFromContext(URL context, String spec) {
/* 172 */     String file = context.getFile();
/* 173 */     if (spec.startsWith("/")) {
/* 174 */       return trimToJarRoot(file) + "!/" + spec.substring(1);
/*     */     }
/* 176 */     if (file.endsWith("/")) {
/* 177 */       return file + spec;
/*     */     }
/* 179 */     int lastSlashIndex = file.lastIndexOf('/');
/* 180 */     if (lastSlashIndex == -1) {
/* 181 */       throw new IllegalArgumentException("No / found in context URL's file '" + file + "'");
/*     */     }
/* 183 */     return file.substring(0, lastSlashIndex + 1) + spec;
/*     */   }
/*     */   
/*     */   private String trimToJarRoot(String file) {
/* 187 */     int lastSeparatorIndex = file.lastIndexOf("!/");
/* 188 */     if (lastSeparatorIndex == -1) {
/* 189 */       throw new IllegalArgumentException("No !/ found in context URL's file '" + file + "'");
/*     */     }
/* 191 */     return file.substring(0, lastSeparatorIndex);
/*     */   }
/*     */   
/*     */   private void setFile(URL context, String file) {
/* 195 */     String path = normalize(file);
/* 196 */     String query = null;
/* 197 */     int queryIndex = path.lastIndexOf('?');
/* 198 */     if (queryIndex != -1) {
/* 199 */       query = path.substring(queryIndex + 1);
/* 200 */       path = path.substring(0, queryIndex);
/*     */     } 
/* 202 */     setURL(context, "jar:", null, -1, null, null, path, query, context.getRef());
/*     */   }
/*     */   
/*     */   private String normalize(String file) {
/* 206 */     if (!file.contains("/./") && !file.contains("/../")) {
/* 207 */       return file;
/*     */     }
/* 209 */     int afterLastSeparatorIndex = file.lastIndexOf("!/") + "!/".length();
/* 210 */     String afterSeparator = file.substring(afterLastSeparatorIndex);
/* 211 */     afterSeparator = replaceParentDir(afterSeparator);
/* 212 */     afterSeparator = replaceCurrentDir(afterSeparator);
/* 213 */     return file.substring(0, afterLastSeparatorIndex) + afterSeparator;
/*     */   }
/*     */   
/*     */   private String replaceParentDir(String file) {
/*     */     int parentDirIndex;
/* 218 */     while ((parentDirIndex = file.indexOf("/../")) >= 0) {
/* 219 */       int precedingSlashIndex = file.lastIndexOf('/', parentDirIndex - 1);
/* 220 */       if (precedingSlashIndex >= 0) {
/* 221 */         file = file.substring(0, precedingSlashIndex) + file.substring(parentDirIndex + 3);
/*     */         continue;
/*     */       } 
/* 224 */       file = file.substring(parentDirIndex + 4);
/*     */     } 
/*     */     
/* 227 */     return file;
/*     */   }
/*     */   
/*     */   private String replaceCurrentDir(String file) {
/* 231 */     return CURRENT_DIR_PATTERN.matcher(file).replaceAll("/");
/*     */   }
/*     */ 
/*     */   
/*     */   protected int hashCode(URL u) {
/* 236 */     return hashCode(u.getProtocol(), u.getFile());
/*     */   }
/*     */   
/*     */   private int hashCode(String protocol, String file) {
/* 240 */     int result = (protocol != null) ? protocol.hashCode() : 0;
/* 241 */     int separatorIndex = file.indexOf("!/");
/* 242 */     if (separatorIndex == -1) {
/* 243 */       return result + file.hashCode();
/*     */     }
/* 245 */     String source = file.substring(0, separatorIndex);
/* 246 */     String entry = canonicalize(file.substring(separatorIndex + 2));
/*     */     try {
/* 248 */       result += (new URL(source)).hashCode();
/*     */     }
/* 250 */     catch (MalformedURLException ex) {
/* 251 */       result += source.hashCode();
/*     */     } 
/* 253 */     result += entry.hashCode();
/* 254 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean sameFile(URL u1, URL u2) {
/* 259 */     if (!u1.getProtocol().equals("jar") || !u2.getProtocol().equals("jar")) {
/* 260 */       return false;
/*     */     }
/* 262 */     int separator1 = u1.getFile().indexOf("!/");
/* 263 */     int separator2 = u2.getFile().indexOf("!/");
/* 264 */     if (separator1 == -1 || separator2 == -1) {
/* 265 */       return super.sameFile(u1, u2);
/*     */     }
/* 267 */     String nested1 = u1.getFile().substring(separator1 + "!/".length());
/* 268 */     String nested2 = u2.getFile().substring(separator2 + "!/".length());
/* 269 */     if (!nested1.equals(nested2)) {
/* 270 */       String canonical1 = canonicalize(nested1);
/* 271 */       String canonical2 = canonicalize(nested2);
/* 272 */       if (!canonical1.equals(canonical2)) {
/* 273 */         return false;
/*     */       }
/*     */     } 
/* 276 */     String root1 = u1.getFile().substring(0, separator1);
/* 277 */     String root2 = u2.getFile().substring(0, separator2);
/*     */     try {
/* 279 */       return super.sameFile(new URL(root1), new URL(root2));
/*     */     }
/* 281 */     catch (MalformedURLException malformedURLException) {
/*     */ 
/*     */       
/* 284 */       return super.sameFile(u1, u2);
/*     */     } 
/*     */   }
/*     */   private String canonicalize(String path) {
/* 288 */     return path.replace("!/", "/");
/*     */   }
/*     */   
/*     */   public JarFile getRootJarFileFromUrl(URL url) throws IOException {
/* 292 */     String spec = url.getFile();
/* 293 */     int separatorIndex = spec.indexOf("!/");
/* 294 */     if (separatorIndex == -1) {
/* 295 */       throw new MalformedURLException("Jar URL does not contain !/ separator");
/*     */     }
/* 297 */     String name = spec.substring(0, separatorIndex);
/* 298 */     return getRootJarFile(name);
/*     */   }
/*     */   
/*     */   private JarFile getRootJarFile(String name) throws IOException {
/*     */     try {
/* 303 */       if (!name.startsWith("file:")) {
/* 304 */         throw new IllegalStateException("Not a file URL");
/*     */       }
/* 306 */       File file = new File(URI.create(name));
/* 307 */       Map<File, JarFile> cache = rootFileCache.get();
/* 308 */       JarFile result = (cache != null) ? cache.get(file) : null;
/* 309 */       if (result == null) {
/* 310 */         result = new JarFile(file);
/* 311 */         addToRootFileCache(file, result);
/*     */       } 
/* 313 */       return result;
/*     */     }
/* 315 */     catch (Exception ex) {
/* 316 */       throw new IOException("Unable to open root Jar file '" + name + "'", ex);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static void addToRootFileCache(File sourceFile, JarFile jarFile) {
/* 326 */     Map<File, JarFile> cache = rootFileCache.get();
/* 327 */     if (cache == null) {
/* 328 */       cache = new ConcurrentHashMap<>();
/* 329 */       rootFileCache = new SoftReference<>(cache);
/*     */     } 
/* 331 */     cache.put(sourceFile, jarFile);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void setUseFastConnectionExceptions(boolean useFastConnectionExceptions) {
/* 341 */     JarURLConnection.setUseFastExceptions(useFastConnectionExceptions);
/*     */   }
/*     */ }


/* Location:              C:\Users\aruizc01\Desktop\api-movilnet.war!\org\springframework\boot\loader\jar\Handler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */