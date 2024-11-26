/*     */ package org.springframework.boot.loader.jar;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collections;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.Map;
/*     */ import java.util.NoSuchElementException;
/*     */ import java.util.jar.Attributes;
/*     */ import java.util.jar.Manifest;
/*     */ import org.springframework.boot.loader.data.RandomAccessData;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class JarFileEntries
/*     */   implements CentralDirectoryVisitor, Iterable<JarEntry>
/*     */ {
/*     */   private static final String META_INF_PREFIX = "META-INF/";
/*     */   
/*     */   static {
/*     */     int version;
/*     */   }
/*     */   
/*  52 */   private static final Attributes.Name MULTI_RELEASE = new Attributes.Name("Multi-Release");
/*     */   
/*     */   private static final int BASE_VERSION = 8;
/*     */   
/*     */   private static final int RUNTIME_VERSION;
/*     */   private static final long LOCAL_FILE_HEADER_SIZE = 30L;
/*     */   
/*     */   static {
/*     */     try {
/*  61 */       Object runtimeVersion = Runtime.class.getMethod("version", new Class[0]).invoke(null, new Object[0]);
/*  62 */       version = ((Integer)runtimeVersion.getClass().getMethod("major", new Class[0]).invoke(runtimeVersion, new Object[0])).intValue();
/*     */     }
/*  64 */     catch (Throwable ex) {
/*  65 */       version = 8;
/*     */     } 
/*  67 */     RUNTIME_VERSION = version;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static final char SLASH = '/';
/*     */   
/*     */   private static final char NO_SUFFIX = '\000';
/*     */   
/*     */   protected static final int ENTRY_CACHE_SIZE = 25;
/*     */   
/*     */   private final JarFile jarFile;
/*     */   
/*     */   private final JarEntryFilter filter;
/*     */   
/*     */   private RandomAccessData centralDirectoryData;
/*     */   
/*     */   private int size;
/*     */   
/*     */   private int[] hashCodes;
/*     */   
/*     */   private int[] centralDirectoryOffsets;
/*     */   
/*     */   private int[] positions;
/*     */   
/*     */   private Boolean multiReleaseJar;
/*     */ 
/*     */   
/*  95 */   private final Map<Integer, FileHeader> entriesCache = Collections.synchronizedMap(new LinkedHashMap<Integer, FileHeader>(16, 0.75F, true)
/*     */       {
/*     */         protected boolean removeEldestEntry(Map.Entry<Integer, FileHeader> eldest)
/*     */         {
/*  99 */           if (JarFileEntries.this.jarFile.isSigned()) {
/* 100 */             return false;
/*     */           }
/* 102 */           return (size() >= 25);
/*     */         }
/*     */       });
/*     */ 
/*     */   
/*     */   JarFileEntries(JarFile jarFile, JarEntryFilter filter) {
/* 108 */     this.jarFile = jarFile;
/* 109 */     this.filter = filter;
/* 110 */     if (RUNTIME_VERSION == 8) {
/* 111 */       this.multiReleaseJar = Boolean.valueOf(false);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void visitStart(CentralDirectoryEndRecord endRecord, RandomAccessData centralDirectoryData) {
/* 117 */     int maxSize = endRecord.getNumberOfRecords();
/* 118 */     this.centralDirectoryData = centralDirectoryData;
/* 119 */     this.hashCodes = new int[maxSize];
/* 120 */     this.centralDirectoryOffsets = new int[maxSize];
/* 121 */     this.positions = new int[maxSize];
/*     */   }
/*     */ 
/*     */   
/*     */   public void visitFileHeader(CentralDirectoryFileHeader fileHeader, int dataOffset) {
/* 126 */     AsciiBytes name = applyFilter(fileHeader.getName());
/* 127 */     if (name != null) {
/* 128 */       add(name, dataOffset);
/*     */     }
/*     */   }
/*     */   
/*     */   private void add(AsciiBytes name, int dataOffset) {
/* 133 */     this.hashCodes[this.size] = name.hashCode();
/* 134 */     this.centralDirectoryOffsets[this.size] = dataOffset;
/* 135 */     this.positions[this.size] = this.size;
/* 136 */     this.size++;
/*     */   }
/*     */ 
/*     */   
/*     */   public void visitEnd() {
/* 141 */     sort(0, this.size - 1);
/* 142 */     int[] positions = this.positions;
/* 143 */     this.positions = new int[positions.length];
/* 144 */     for (int i = 0; i < this.size; i++) {
/* 145 */       this.positions[positions[i]] = i;
/*     */     }
/*     */   }
/*     */   
/*     */   int getSize() {
/* 150 */     return this.size;
/*     */   }
/*     */ 
/*     */   
/*     */   private void sort(int left, int right) {
/* 155 */     if (left < right) {
/* 156 */       int pivot = this.hashCodes[left + (right - left) / 2];
/* 157 */       int i = left;
/* 158 */       int j = right;
/* 159 */       while (i <= j) {
/* 160 */         while (this.hashCodes[i] < pivot) {
/* 161 */           i++;
/*     */         }
/* 163 */         while (this.hashCodes[j] > pivot) {
/* 164 */           j--;
/*     */         }
/* 166 */         if (i <= j) {
/* 167 */           swap(i, j);
/* 168 */           i++;
/* 169 */           j--;
/*     */         } 
/*     */       } 
/* 172 */       if (left < j) {
/* 173 */         sort(left, j);
/*     */       }
/* 175 */       if (right > i) {
/* 176 */         sort(i, right);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private void swap(int i, int j) {
/* 182 */     swap(this.hashCodes, i, j);
/* 183 */     swap(this.centralDirectoryOffsets, i, j);
/* 184 */     swap(this.positions, i, j);
/*     */   }
/*     */   
/*     */   private void swap(int[] array, int i, int j) {
/* 188 */     int temp = array[i];
/* 189 */     array[i] = array[j];
/* 190 */     array[j] = temp;
/*     */   }
/*     */ 
/*     */   
/*     */   public Iterator<JarEntry> iterator() {
/* 195 */     return new EntryIterator();
/*     */   }
/*     */   
/*     */   public boolean containsEntry(CharSequence name) {
/* 199 */     return (getEntry(name, FileHeader.class, true) != null);
/*     */   }
/*     */   
/*     */   public JarEntry getEntry(CharSequence name) {
/* 203 */     return getEntry(name, JarEntry.class, true);
/*     */   }
/*     */   
/*     */   public InputStream getInputStream(String name) throws IOException {
/* 207 */     FileHeader entry = getEntry(name, FileHeader.class, false);
/* 208 */     return getInputStream(entry);
/*     */   }
/*     */   
/*     */   public InputStream getInputStream(FileHeader entry) throws IOException {
/* 212 */     if (entry == null) {
/* 213 */       return null;
/*     */     }
/* 215 */     InputStream inputStream = getEntryData(entry).getInputStream();
/* 216 */     if (entry.getMethod() == 8) {
/* 217 */       inputStream = new ZipInflaterInputStream(inputStream, (int)entry.getSize());
/*     */     }
/* 219 */     return inputStream;
/*     */   }
/*     */   
/*     */   public RandomAccessData getEntryData(String name) throws IOException {
/* 223 */     FileHeader entry = getEntry(name, FileHeader.class, false);
/* 224 */     if (entry == null) {
/* 225 */       return null;
/*     */     }
/* 227 */     return getEntryData(entry);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private RandomAccessData getEntryData(FileHeader entry) throws IOException {
/* 234 */     RandomAccessData data = this.jarFile.getData();
/* 235 */     byte[] localHeader = data.read(entry.getLocalHeaderOffset(), 30L);
/* 236 */     long nameLength = Bytes.littleEndianValue(localHeader, 26, 2);
/* 237 */     long extraLength = Bytes.littleEndianValue(localHeader, 28, 2);
/* 238 */     return data.getSubsection(entry.getLocalHeaderOffset() + 30L + nameLength + extraLength, entry
/* 239 */         .getCompressedSize());
/*     */   }
/*     */   
/*     */   private <T extends FileHeader> T getEntry(CharSequence name, Class<T> type, boolean cacheEntry) {
/* 243 */     T entry = doGetEntry(name, type, cacheEntry, null);
/* 244 */     if (!isMetaInfEntry(name) && isMultiReleaseJar()) {
/* 245 */       int version = RUNTIME_VERSION;
/*     */       
/* 247 */       AsciiBytes nameAlias = (entry instanceof JarEntry) ? ((JarEntry)entry).getAsciiBytesName() : new AsciiBytes(name.toString());
/* 248 */       while (version > 8) {
/* 249 */         T versionedEntry = doGetEntry("META-INF/versions/" + version + "/" + name, type, cacheEntry, nameAlias);
/* 250 */         if (versionedEntry != null) {
/* 251 */           return versionedEntry;
/*     */         }
/* 253 */         version--;
/*     */       } 
/*     */     } 
/* 256 */     return entry;
/*     */   }
/*     */   
/*     */   private boolean isMetaInfEntry(CharSequence name) {
/* 260 */     return name.toString().startsWith("META-INF/");
/*     */   }
/*     */   
/*     */   private boolean isMultiReleaseJar() {
/* 264 */     Boolean multiRelease = this.multiReleaseJar;
/* 265 */     if (multiRelease != null) {
/* 266 */       return multiRelease.booleanValue();
/*     */     }
/*     */     try {
/* 269 */       Manifest manifest = this.jarFile.getManifest();
/* 270 */       if (manifest == null) {
/* 271 */         multiRelease = Boolean.valueOf(false);
/*     */       } else {
/*     */         
/* 274 */         Attributes attributes = manifest.getMainAttributes();
/* 275 */         multiRelease = Boolean.valueOf(attributes.containsKey(MULTI_RELEASE));
/*     */       }
/*     */     
/* 278 */     } catch (IOException ex) {
/* 279 */       multiRelease = Boolean.valueOf(false);
/*     */     } 
/* 281 */     this.multiReleaseJar = multiRelease;
/* 282 */     return multiRelease.booleanValue();
/*     */   }
/*     */ 
/*     */   
/*     */   private <T extends FileHeader> T doGetEntry(CharSequence name, Class<T> type, boolean cacheEntry, AsciiBytes nameAlias) {
/* 287 */     int hashCode = AsciiBytes.hashCode(name);
/* 288 */     T entry = getEntry(hashCode, name, false, type, cacheEntry, nameAlias);
/* 289 */     if (entry == null) {
/* 290 */       hashCode = AsciiBytes.hashCode(hashCode, '/');
/* 291 */       entry = getEntry(hashCode, name, '/', type, cacheEntry, nameAlias);
/*     */     } 
/* 293 */     return entry;
/*     */   }
/*     */ 
/*     */   
/*     */   private <T extends FileHeader> T getEntry(int hashCode, CharSequence name, char suffix, Class<T> type, boolean cacheEntry, AsciiBytes nameAlias) {
/* 298 */     int index = getFirstIndex(hashCode);
/* 299 */     while (index >= 0 && index < this.size && this.hashCodes[index] == hashCode) {
/* 300 */       T entry = getEntry(index, type, cacheEntry, nameAlias);
/* 301 */       if (entry.hasName(name, suffix)) {
/* 302 */         return entry;
/*     */       }
/* 304 */       index++;
/*     */     } 
/* 306 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   private <T extends FileHeader> T getEntry(int index, Class<T> type, boolean cacheEntry, AsciiBytes nameAlias) {
/*     */     try {
/* 312 */       FileHeader cached = this.entriesCache.get(Integer.valueOf(index));
/*     */       
/* 314 */       FileHeader entry = (cached != null) ? cached : CentralDirectoryFileHeader.fromRandomAccessData(this.centralDirectoryData, this.centralDirectoryOffsets[index], this.filter);
/* 315 */       if (CentralDirectoryFileHeader.class.equals(entry.getClass()) && type.equals(JarEntry.class)) {
/* 316 */         entry = new JarEntry(this.jarFile, (CentralDirectoryFileHeader)entry, nameAlias);
/*     */       }
/* 318 */       if (cacheEntry && cached != entry) {
/* 319 */         this.entriesCache.put(Integer.valueOf(index), entry);
/*     */       }
/* 321 */       return (T)entry;
/*     */     }
/* 323 */     catch (IOException ex) {
/* 324 */       throw new IllegalStateException(ex);
/*     */     } 
/*     */   }
/*     */   
/*     */   private int getFirstIndex(int hashCode) {
/* 329 */     int index = Arrays.binarySearch(this.hashCodes, 0, this.size, hashCode);
/* 330 */     if (index < 0) {
/* 331 */       return -1;
/*     */     }
/* 333 */     while (index > 0 && this.hashCodes[index - 1] == hashCode) {
/* 334 */       index--;
/*     */     }
/* 336 */     return index;
/*     */   }
/*     */   
/*     */   public void clearCache() {
/* 340 */     this.entriesCache.clear();
/*     */   }
/*     */   
/*     */   private AsciiBytes applyFilter(AsciiBytes name) {
/* 344 */     return (this.filter != null) ? this.filter.apply(name) : name;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private class EntryIterator
/*     */     implements Iterator<JarEntry>
/*     */   {
/* 352 */     private int index = 0;
/*     */ 
/*     */     
/*     */     public boolean hasNext() {
/* 356 */       return (this.index < JarFileEntries.this.size);
/*     */     }
/*     */ 
/*     */     
/*     */     public JarEntry next() {
/* 361 */       if (!hasNext()) {
/* 362 */         throw new NoSuchElementException();
/*     */       }
/* 364 */       int entryIndex = JarFileEntries.this.positions[this.index];
/* 365 */       this.index++;
/* 366 */       return (JarEntry)JarFileEntries.this.getEntry(entryIndex, (Class)JarEntry.class, false, null);
/*     */     }
/*     */     
/*     */     private EntryIterator() {}
/*     */   }
/*     */ }


/* Location:              C:\Users\aruizc01\Desktop\api-movilnet.war!\org\springframework\boot\loader\jar\JarFileEntries.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */