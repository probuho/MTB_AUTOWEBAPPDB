/*     */ package org.springframework.boot.loader.jar;
/*     */ 
/*     */ import java.io.IOException;
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
/*     */ class CentralDirectoryEndRecord
/*     */ {
/*     */   private static final int MINIMUM_SIZE = 22;
/*     */   private static final int MAXIMUM_COMMENT_LENGTH = 65535;
/*     */   private static final int MAXIMUM_SIZE = 65557;
/*     */   private static final int SIGNATURE = 101010256;
/*     */   private static final int COMMENT_LENGTH_OFFSET = 20;
/*     */   private static final int READ_BLOCK_SIZE = 256;
/*     */   private byte[] block;
/*     */   private int offset;
/*     */   private int size;
/*     */   
/*     */   CentralDirectoryEndRecord(RandomAccessData data) throws IOException {
/*  58 */     this.block = createBlockFromEndOfData(data, 256);
/*  59 */     this.size = 22;
/*  60 */     this.offset = this.block.length - this.size;
/*  61 */     while (!isValid()) {
/*  62 */       this.size++;
/*  63 */       if (this.size > this.block.length) {
/*  64 */         if (this.size >= 65557 || this.size > data.getSize()) {
/*  65 */           throw new IOException("Unable to find ZIP central directory records after reading " + this.size + " bytes");
/*     */         }
/*     */         
/*  68 */         this.block = createBlockFromEndOfData(data, this.size + 256);
/*     */       } 
/*  70 */       this.offset = this.block.length - this.size;
/*     */     } 
/*     */   }
/*     */   
/*     */   private byte[] createBlockFromEndOfData(RandomAccessData data, int size) throws IOException {
/*  75 */     int length = (int)Math.min(data.getSize(), size);
/*  76 */     return data.read(data.getSize() - length, length);
/*     */   }
/*     */   
/*     */   private boolean isValid() {
/*  80 */     if (this.block.length < 22 || Bytes.littleEndianValue(this.block, this.offset + 0, 4) != 101010256L) {
/*  81 */       return false;
/*     */     }
/*     */     
/*  84 */     long commentLength = Bytes.littleEndianValue(this.block, this.offset + 20, 2);
/*  85 */     return (this.size == 22L + commentLength);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long getStartOfArchive(RandomAccessData data) {
/*  96 */     long length = Bytes.littleEndianValue(this.block, this.offset + 12, 4);
/*  97 */     long specifiedOffset = Bytes.littleEndianValue(this.block, this.offset + 16, 4);
/*  98 */     long actualOffset = data.getSize() - this.size - length;
/*  99 */     return actualOffset - specifiedOffset;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RandomAccessData getCentralDirectory(RandomAccessData data) {
/* 109 */     long offset = Bytes.littleEndianValue(this.block, this.offset + 16, 4);
/* 110 */     long length = Bytes.littleEndianValue(this.block, this.offset + 12, 4);
/* 111 */     return data.getSubsection(offset, length);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getNumberOfRecords() {
/* 119 */     long numberOfRecords = Bytes.littleEndianValue(this.block, this.offset + 10, 2);
/* 120 */     if (numberOfRecords == 65535L) {
/* 121 */       throw new IllegalStateException("Zip64 archives are not supported");
/*     */     }
/* 123 */     return (int)numberOfRecords;
/*     */   }
/*     */ }


/* Location:              C:\Users\aruizc01\Desktop\api-movilnet.war!\org\springframework\boot\loader\jar\CentralDirectoryEndRecord.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */