package org.springframework.boot.loader.jar;

import org.springframework.boot.loader.data.RandomAccessData;

interface CentralDirectoryVisitor {
  void visitStart(CentralDirectoryEndRecord paramCentralDirectoryEndRecord, RandomAccessData paramRandomAccessData);
  
  void visitFileHeader(CentralDirectoryFileHeader paramCentralDirectoryFileHeader, int paramInt);
  
  void visitEnd();
}


/* Location:              C:\Users\aruizc01\Desktop\api-movilnet.war!\org\springframework\boot\loader\jar\CentralDirectoryVisitor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */