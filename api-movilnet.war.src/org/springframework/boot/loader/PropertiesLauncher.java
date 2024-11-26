/*     */ package org.springframework.boot.loader;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import java.net.HttpURLConnection;
/*     */ import java.net.URL;
/*     */ import java.net.URLConnection;
/*     */ import java.net.URLDecoder;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.LinkedHashSet;
/*     */ import java.util.List;
/*     */ import java.util.Locale;
/*     */ import java.util.Properties;
/*     */ import java.util.Set;
/*     */ import java.util.jar.Manifest;
/*     */ import java.util.regex.Matcher;
/*     */ import java.util.regex.Pattern;
/*     */ import org.springframework.boot.loader.archive.Archive;
/*     */ import org.springframework.boot.loader.archive.ExplodedArchive;
/*     */ import org.springframework.boot.loader.archive.JarFileArchive;
/*     */ import org.springframework.boot.loader.util.SystemPropertyUtils;
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
/*     */ public class PropertiesLauncher
/*     */   extends Launcher
/*     */ {
/*     */   private static final String DEBUG = "loader.debug";
/*     */   public static final String MAIN = "loader.main";
/*     */   public static final String PATH = "loader.path";
/*     */   public static final String HOME = "loader.home";
/*     */   public static final String ARGS = "loader.args";
/*     */   public static final String CONFIG_NAME = "loader.config.name";
/*     */   public static final String CONFIG_LOCATION = "loader.config.location";
/*     */   public static final String SET_SYSTEM_PROPERTIES = "loader.system";
/* 126 */   private static final Pattern WORD_SEPARATOR = Pattern.compile("\\W+");
/*     */   
/* 128 */   private static final String NESTED_ARCHIVE_SEPARATOR = "!" + File.separator;
/*     */   
/*     */   private final File home;
/*     */   
/* 132 */   private List<String> paths = new ArrayList<>();
/*     */   
/* 134 */   private final Properties properties = new Properties();
/*     */   
/*     */   private Archive parent;
/*     */   
/*     */   public PropertiesLauncher() {
/*     */     try {
/* 140 */       this.home = getHomeDirectory();
/* 141 */       initializeProperties();
/* 142 */       initializePaths();
/* 143 */       this.parent = createArchive();
/*     */     }
/* 145 */     catch (Exception ex) {
/* 146 */       throw new IllegalStateException(ex);
/*     */     } 
/*     */   }
/*     */   
/*     */   protected File getHomeDirectory() {
/*     */     try {
/* 152 */       return new File(getPropertyWithDefault("loader.home", "${user.dir}"));
/*     */     }
/* 154 */     catch (Exception ex) {
/* 155 */       throw new IllegalStateException(ex);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void initializeProperties() throws Exception, IOException {
/* 160 */     List<String> configs = new ArrayList<>();
/* 161 */     if (getProperty("loader.config.location") != null) {
/* 162 */       configs.add(getProperty("loader.config.location"));
/*     */     } else {
/*     */       
/* 165 */       String[] names = getPropertyWithDefault("loader.config.name", "loader").split(",");
/* 166 */       for (String name : names) {
/* 167 */         configs.add("file:" + getHomeDirectory() + "/" + name + ".properties");
/* 168 */         configs.add("classpath:" + name + ".properties");
/* 169 */         configs.add("classpath:BOOT-INF/classes/" + name + ".properties");
/*     */       } 
/*     */     } 
/* 172 */     for (String config : configs) {
/* 173 */       try (InputStream resource = getResource(config)) {
/* 174 */         if (resource != null) {
/* 175 */           debug("Found: " + config);
/* 176 */           loadResource(resource);
/*     */           
/*     */           return;
/*     */         } 
/*     */         
/* 181 */         debug("Not found: " + config);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void loadResource(InputStream resource) throws IOException, Exception {
/* 188 */     this.properties.load(resource);
/* 189 */     for (Object key : Collections.list(this.properties.propertyNames())) {
/* 190 */       String text = this.properties.getProperty((String)key);
/* 191 */       String value = SystemPropertyUtils.resolvePlaceholders(this.properties, text);
/* 192 */       if (value != null) {
/* 193 */         this.properties.put(key, value);
/*     */       }
/*     */     } 
/* 196 */     if ("true".equals(getProperty("loader.system"))) {
/* 197 */       debug("Adding resolved properties to System properties");
/* 198 */       for (Object key : Collections.list(this.properties.propertyNames())) {
/* 199 */         String value = this.properties.getProperty((String)key);
/* 200 */         System.setProperty((String)key, value);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private InputStream getResource(String config) throws Exception {
/* 206 */     if (config.startsWith("classpath:")) {
/* 207 */       return getClasspathResource(config.substring("classpath:".length()));
/*     */     }
/* 209 */     config = handleUrl(config);
/* 210 */     if (isUrl(config)) {
/* 211 */       return getURLResource(config);
/*     */     }
/* 213 */     return getFileResource(config);
/*     */   }
/*     */   
/*     */   private String handleUrl(String path) throws UnsupportedEncodingException {
/* 217 */     if (path.startsWith("jar:file:") || path.startsWith("file:")) {
/* 218 */       path = URLDecoder.decode(path, "UTF-8");
/* 219 */       if (path.startsWith("file:")) {
/* 220 */         path = path.substring("file:".length());
/* 221 */         if (path.startsWith("//")) {
/* 222 */           path = path.substring(2);
/*     */         }
/*     */       } 
/*     */     } 
/* 226 */     return path;
/*     */   }
/*     */   
/*     */   private boolean isUrl(String config) {
/* 230 */     return config.contains("://");
/*     */   }
/*     */   
/*     */   private InputStream getClasspathResource(String config) {
/* 234 */     while (config.startsWith("/")) {
/* 235 */       config = config.substring(1);
/*     */     }
/* 237 */     config = "/" + config;
/* 238 */     debug("Trying classpath: " + config);
/* 239 */     return getClass().getResourceAsStream(config);
/*     */   }
/*     */   
/*     */   private InputStream getFileResource(String config) throws Exception {
/* 243 */     File file = new File(config);
/* 244 */     debug("Trying file: " + config);
/* 245 */     if (file.canRead()) {
/* 246 */       return new FileInputStream(file);
/*     */     }
/* 248 */     return null;
/*     */   }
/*     */   
/*     */   private InputStream getURLResource(String config) throws Exception {
/* 252 */     URL url = new URL(config);
/* 253 */     if (exists(url)) {
/* 254 */       URLConnection con = url.openConnection();
/*     */       try {
/* 256 */         return con.getInputStream();
/*     */       }
/* 258 */       catch (IOException ex) {
/*     */         
/* 260 */         if (con instanceof HttpURLConnection) {
/* 261 */           ((HttpURLConnection)con).disconnect();
/*     */         }
/* 263 */         throw ex;
/*     */       } 
/*     */     } 
/* 266 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean exists(URL url) throws IOException {
/* 271 */     URLConnection connection = url.openConnection();
/*     */     try {
/* 273 */       connection.setUseCaches(connection.getClass().getSimpleName().startsWith("JNLP"));
/* 274 */       if (connection instanceof HttpURLConnection) {
/* 275 */         HttpURLConnection httpConnection = (HttpURLConnection)connection;
/* 276 */         httpConnection.setRequestMethod("HEAD");
/* 277 */         int responseCode = httpConnection.getResponseCode();
/* 278 */         if (responseCode == 200) {
/* 279 */           return true;
/*     */         }
/* 281 */         if (responseCode == 404) {
/* 282 */           return false;
/*     */         }
/*     */       } 
/* 285 */       return (connection.getContentLength() >= 0);
/*     */     } finally {
/*     */       
/* 288 */       if (connection instanceof HttpURLConnection) {
/* 289 */         ((HttpURLConnection)connection).disconnect();
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private void initializePaths() throws Exception {
/* 295 */     String path = getProperty("loader.path");
/* 296 */     if (path != null) {
/* 297 */       this.paths = parsePathsProperty(path);
/*     */     }
/* 299 */     debug("Nested archive paths: " + this.paths);
/*     */   }
/*     */   
/*     */   private List<String> parsePathsProperty(String commaSeparatedPaths) {
/* 303 */     List<String> paths = new ArrayList<>();
/* 304 */     for (String path : commaSeparatedPaths.split(",")) {
/* 305 */       path = cleanupPath(path);
/*     */       
/* 307 */       path = "".equals(path) ? "/" : path;
/* 308 */       paths.add(path);
/*     */     } 
/* 310 */     if (paths.isEmpty()) {
/* 311 */       paths.add("lib");
/*     */     }
/* 313 */     return paths;
/*     */   }
/*     */   
/*     */   protected String[] getArgs(String... args) throws Exception {
/* 317 */     String loaderArgs = getProperty("loader.args");
/* 318 */     if (loaderArgs != null) {
/* 319 */       String[] defaultArgs = loaderArgs.split("\\s+");
/* 320 */       String[] additionalArgs = args;
/* 321 */       args = new String[defaultArgs.length + additionalArgs.length];
/* 322 */       System.arraycopy(defaultArgs, 0, args, 0, defaultArgs.length);
/* 323 */       System.arraycopy(additionalArgs, 0, args, defaultArgs.length, additionalArgs.length);
/*     */     } 
/* 325 */     return args;
/*     */   }
/*     */ 
/*     */   
/*     */   protected String getMainClass() throws Exception {
/* 330 */     String mainClass = getProperty("loader.main", "Start-Class");
/* 331 */     if (mainClass == null) {
/* 332 */       throw new IllegalStateException("No 'loader.main' or 'Start-Class' specified");
/*     */     }
/* 334 */     return mainClass;
/*     */   }
/*     */ 
/*     */   
/*     */   protected ClassLoader createClassLoader(List<Archive> archives) throws Exception {
/* 339 */     Set<URL> urls = new LinkedHashSet<>(archives.size());
/* 340 */     for (Archive archive : archives) {
/* 341 */       urls.add(archive.getUrl());
/*     */     }
/* 343 */     ClassLoader loader = new LaunchedURLClassLoader(urls.<URL>toArray(new URL[0]), getClass().getClassLoader());
/* 344 */     debug("Classpath: " + urls);
/* 345 */     String customLoaderClassName = getProperty("loader.classLoader");
/* 346 */     if (customLoaderClassName != null) {
/* 347 */       loader = wrapWithCustomClassLoader(loader, customLoaderClassName);
/* 348 */       debug("Using custom class loader: " + customLoaderClassName);
/*     */     } 
/* 350 */     return loader;
/*     */   }
/*     */ 
/*     */   
/*     */   private ClassLoader wrapWithCustomClassLoader(ClassLoader parent, String loaderClassName) throws Exception {
/* 355 */     Class<ClassLoader> loaderClass = (Class)Class.forName(loaderClassName, true, parent);
/*     */     
/*     */     try {
/* 358 */       return loaderClass.getConstructor(new Class[] { ClassLoader.class }).newInstance(new Object[] { parent });
/*     */     }
/* 360 */     catch (NoSuchMethodException noSuchMethodException) {
/*     */ 
/*     */       
/*     */       try {
/* 364 */         return loaderClass.getConstructor(new Class[] { URL[].class, ClassLoader.class }).newInstance(new Object[] { new URL[0], parent });
/*     */       }
/* 366 */       catch (NoSuchMethodException noSuchMethodException1) {
/*     */ 
/*     */         
/* 369 */         return loaderClass.newInstance();
/*     */       } 
/*     */     } 
/*     */   } private String getProperty(String propertyKey) throws Exception {
/* 373 */     return getProperty(propertyKey, (String)null, (String)null);
/*     */   }
/*     */   
/*     */   private String getProperty(String propertyKey, String manifestKey) throws Exception {
/* 377 */     return getProperty(propertyKey, manifestKey, (String)null);
/*     */   }
/*     */   
/*     */   private String getPropertyWithDefault(String propertyKey, String defaultValue) throws Exception {
/* 381 */     return getProperty(propertyKey, (String)null, defaultValue);
/*     */   }
/*     */   
/*     */   private String getProperty(String propertyKey, String manifestKey, String defaultValue) throws Exception {
/* 385 */     if (manifestKey == null) {
/* 386 */       manifestKey = propertyKey.replace('.', '-');
/* 387 */       manifestKey = toCamelCase(manifestKey);
/*     */     } 
/* 389 */     String property = SystemPropertyUtils.getProperty(propertyKey);
/* 390 */     if (property != null) {
/* 391 */       String value = SystemPropertyUtils.resolvePlaceholders(this.properties, property);
/* 392 */       debug("Property '" + propertyKey + "' from environment: " + value);
/* 393 */       return value;
/*     */     } 
/* 395 */     if (this.properties.containsKey(propertyKey)) {
/* 396 */       String value = SystemPropertyUtils.resolvePlaceholders(this.properties, this.properties
/* 397 */           .getProperty(propertyKey));
/* 398 */       debug("Property '" + propertyKey + "' from properties: " + value);
/* 399 */       return value;
/*     */     } 
/*     */     try {
/* 402 */       if (this.home != null) {
/*     */         
/* 404 */         Manifest manifest1 = (new ExplodedArchive(this.home, false)).getManifest();
/* 405 */         if (manifest1 != null) {
/* 406 */           String value = manifest1.getMainAttributes().getValue(manifestKey);
/* 407 */           if (value != null) {
/* 408 */             debug("Property '" + manifestKey + "' from home directory manifest: " + value);
/* 409 */             return SystemPropertyUtils.resolvePlaceholders(this.properties, value);
/*     */           }
/*     */         
/*     */         } 
/*     */       } 
/* 414 */     } catch (IllegalStateException illegalStateException) {}
/*     */ 
/*     */ 
/*     */     
/* 418 */     Manifest manifest = createArchive().getManifest();
/* 419 */     if (manifest != null) {
/* 420 */       String value = manifest.getMainAttributes().getValue(manifestKey);
/* 421 */       if (value != null) {
/* 422 */         debug("Property '" + manifestKey + "' from archive manifest: " + value);
/* 423 */         return SystemPropertyUtils.resolvePlaceholders(this.properties, value);
/*     */       } 
/*     */     } 
/* 426 */     return (defaultValue != null) ? SystemPropertyUtils.resolvePlaceholders(this.properties, defaultValue) : defaultValue;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected List<Archive> getClassPathArchives() throws Exception {
/* 432 */     List<Archive> lib = new ArrayList<>();
/* 433 */     for (String path : this.paths) {
/* 434 */       for (Archive archive : getClassPathArchives(path)) {
/* 435 */         if (archive instanceof ExplodedArchive) {
/* 436 */           List<Archive> nested = new ArrayList<>(archive.getNestedArchives(new ArchiveEntryFilter()));
/* 437 */           nested.add(0, archive);
/* 438 */           lib.addAll(nested);
/*     */           continue;
/*     */         } 
/* 441 */         lib.add(archive);
/*     */       } 
/*     */     } 
/*     */     
/* 445 */     addNestedEntries(lib);
/* 446 */     return lib;
/*     */   }
/*     */   
/*     */   private List<Archive> getClassPathArchives(String path) throws Exception {
/* 450 */     String root = cleanupPath(handleUrl(path));
/* 451 */     List<Archive> lib = new ArrayList<>();
/* 452 */     File file = new File(root);
/* 453 */     if (!"/".equals(root)) {
/* 454 */       if (!isAbsolutePath(root)) {
/* 455 */         file = new File(this.home, root);
/*     */       }
/* 457 */       if (file.isDirectory()) {
/* 458 */         debug("Adding classpath entries from " + file);
/* 459 */         ExplodedArchive explodedArchive = new ExplodedArchive(file, false);
/* 460 */         lib.add(explodedArchive);
/*     */       } 
/*     */     } 
/* 463 */     Archive archive = getArchive(file);
/* 464 */     if (archive != null) {
/* 465 */       debug("Adding classpath entries from archive " + archive.getUrl() + root);
/* 466 */       lib.add(archive);
/*     */     } 
/* 468 */     List<Archive> nestedArchives = getNestedArchives(root);
/* 469 */     if (nestedArchives != null) {
/* 470 */       debug("Adding classpath entries from nested " + root);
/* 471 */       lib.addAll(nestedArchives);
/*     */     } 
/* 473 */     return lib;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean isAbsolutePath(String root) {
/* 478 */     return (root.contains(":") || root.startsWith("/"));
/*     */   }
/*     */   
/*     */   private Archive getArchive(File file) throws IOException {
/* 482 */     if (isNestedArchivePath(file)) {
/* 483 */       return null;
/*     */     }
/* 485 */     String name = file.getName().toLowerCase(Locale.ENGLISH);
/* 486 */     if (name.endsWith(".jar") || name.endsWith(".zip")) {
/* 487 */       return (Archive)new JarFileArchive(file);
/*     */     }
/* 489 */     return null;
/*     */   }
/*     */   
/*     */   private boolean isNestedArchivePath(File file) {
/* 493 */     return file.getPath().contains(NESTED_ARCHIVE_SEPARATOR);
/*     */   }
/*     */   private List<Archive> getNestedArchives(String path) throws Exception {
/*     */     JarFileArchive jarFileArchive;
/* 497 */     Archive parent = this.parent;
/* 498 */     String root = path;
/* 499 */     if ((!root.equals("/") && root.startsWith("/")) || parent.getUrl().equals(this.home.toURI().toURL()))
/*     */     {
/* 501 */       return null;
/*     */     }
/* 503 */     int index = root.indexOf('!');
/* 504 */     if (index != -1) {
/* 505 */       File file = new File(this.home, root.substring(0, index));
/* 506 */       if (root.startsWith("jar:file:")) {
/* 507 */         file = new File(root.substring("jar:file:".length(), index));
/*     */       }
/* 509 */       jarFileArchive = new JarFileArchive(file);
/* 510 */       root = root.substring(index + 1);
/* 511 */       while (root.startsWith("/")) {
/* 512 */         root = root.substring(1);
/*     */       }
/*     */     } 
/* 515 */     if (root.endsWith(".jar")) {
/* 516 */       File file = new File(this.home, root);
/* 517 */       if (file.exists()) {
/* 518 */         jarFileArchive = new JarFileArchive(file);
/* 519 */         root = "";
/*     */       } 
/*     */     } 
/* 522 */     if (root.equals("/") || root.equals("./") || root.equals("."))
/*     */     {
/* 524 */       root = "";
/*     */     }
/* 526 */     Archive.EntryFilter filter = new PrefixMatchingArchiveFilter(root);
/* 527 */     List<Archive> archives = new ArrayList<>(jarFileArchive.getNestedArchives(filter));
/* 528 */     if (("".equals(root) || ".".equals(root)) && !path.endsWith(".jar") && jarFileArchive != this.parent)
/*     */     {
/*     */       
/* 531 */       archives.add(jarFileArchive);
/*     */     }
/* 533 */     return archives;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void addNestedEntries(List<Archive> lib) {
/*     */     try {
/* 541 */       lib.addAll(this.parent.getNestedArchives(entry -> entry.isDirectory() ? entry.getName().equals("BOOT-INF/classes/") : entry.getName().startsWith("BOOT-INF/lib/")));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     }
/* 548 */     catch (IOException iOException) {}
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private String cleanupPath(String path) {
/* 554 */     path = path.trim();
/*     */     
/* 556 */     if (path.startsWith("./")) {
/* 557 */       path = path.substring(2);
/*     */     }
/* 559 */     String lowerCasePath = path.toLowerCase(Locale.ENGLISH);
/* 560 */     if (lowerCasePath.endsWith(".jar") || lowerCasePath.endsWith(".zip")) {
/* 561 */       return path;
/*     */     }
/* 563 */     if (path.endsWith("/*")) {
/* 564 */       path = path.substring(0, path.length() - 1);
/*     */ 
/*     */     
/*     */     }
/* 568 */     else if (!path.endsWith("/") && !path.equals(".")) {
/* 569 */       path = path + "/";
/*     */     } 
/*     */     
/* 572 */     return path;
/*     */   }
/*     */   
/*     */   public static void main(String[] args) throws Exception {
/* 576 */     PropertiesLauncher launcher = new PropertiesLauncher();
/* 577 */     args = launcher.getArgs(args);
/* 578 */     launcher.launch(args);
/*     */   }
/*     */   
/*     */   public static String toCamelCase(CharSequence string) {
/* 582 */     if (string == null) {
/* 583 */       return null;
/*     */     }
/* 585 */     StringBuilder builder = new StringBuilder();
/* 586 */     Matcher matcher = WORD_SEPARATOR.matcher(string);
/* 587 */     int pos = 0;
/* 588 */     while (matcher.find()) {
/* 589 */       builder.append(capitalize(string.subSequence(pos, matcher.end()).toString()));
/* 590 */       pos = matcher.end();
/*     */     } 
/* 592 */     builder.append(capitalize(string.subSequence(pos, string.length()).toString()));
/* 593 */     return builder.toString();
/*     */   }
/*     */   
/*     */   private static String capitalize(String str) {
/* 597 */     return Character.toUpperCase(str.charAt(0)) + str.substring(1);
/*     */   }
/*     */   
/*     */   private void debug(String message) {
/* 601 */     if (Boolean.getBoolean("loader.debug")) {
/* 602 */       System.out.println(message);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static final class PrefixMatchingArchiveFilter
/*     */     implements Archive.EntryFilter
/*     */   {
/*     */     private final String prefix;
/*     */ 
/*     */     
/* 614 */     private final PropertiesLauncher.ArchiveEntryFilter filter = new PropertiesLauncher.ArchiveEntryFilter();
/*     */     
/*     */     private PrefixMatchingArchiveFilter(String prefix) {
/* 617 */       this.prefix = prefix;
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean matches(Archive.Entry entry) {
/* 622 */       if (entry.isDirectory()) {
/* 623 */         return entry.getName().equals(this.prefix);
/*     */       }
/* 625 */       return (entry.getName().startsWith(this.prefix) && this.filter.matches(entry));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private static final class ArchiveEntryFilter
/*     */     implements Archive.EntryFilter
/*     */   {
/*     */     private static final String DOT_JAR = ".jar";
/*     */     
/*     */     private static final String DOT_ZIP = ".zip";
/*     */ 
/*     */     
/*     */     private ArchiveEntryFilter() {}
/*     */ 
/*     */     
/*     */     public boolean matches(Archive.Entry entry) {
/* 642 */       return (entry.getName().endsWith(".jar") || entry.getName().endsWith(".zip"));
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\aruizc01\Desktop\api-movilnet.war!\org\springframework\boot\loader\PropertiesLauncher.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */