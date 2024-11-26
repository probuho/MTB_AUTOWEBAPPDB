/*     */ package WEB-INF.classes.ve.com.movilnet.api.service.util;
/*     */ 
/*     */ import java.security.InvalidKeyException;
/*     */ import java.security.Key;
/*     */ import java.security.NoSuchAlgorithmException;
/*     */ import java.util.Base64;
/*     */ import java.util.Calendar;
/*     */ import java.util.regex.Pattern;
/*     */ import javax.crypto.BadPaddingException;
/*     */ import javax.crypto.Cipher;
/*     */ import javax.crypto.IllegalBlockSizeException;
/*     */ import javax.crypto.NoSuchPaddingException;
/*     */ import javax.crypto.spec.SecretKeySpec;
/*     */ import org.apache.log4j.Logger;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Operaciones
/*     */ {
/*  22 */   private static final Logger logger = Logger.getLogger(ve.com.movilnet.api.service.util.Operaciones.class);
/*     */ 
/*     */   
/*     */   public String obtenerNodo(String nodo) {
/*     */     String nodoRango;
/*  27 */     logger.debug("|Operaciones.obtenerNodo para el dato | " + nodo);
/*     */ 
/*     */ 
/*     */     
/*  31 */     int rangoNodo = Integer.parseInt(nodo.substring(3, 4));
/*     */     
/*  33 */     if (rangoNodo % 2 == 0) {
/*  34 */       nodoRango = "GSM02";
/*     */     } else {
/*  36 */       nodoRango = "GSM01";
/*     */     } 
/*     */     
/*  39 */     return nodoRango;
/*     */   }
/*     */ 
/*     */   
/*     */   public String NumeroSinCero(String numero) {
/*  44 */     String valor = "";
/*  45 */     if (numero.length() >= 10 && numero.length() < 12) {
/*     */       
/*  47 */       valor = numero;
/*     */       
/*  49 */       if (numero.length() == 11) {
/*  50 */         numero = numero.substring(1, numero.length());
/*  51 */         String codigo = numero.substring(0, 3);
/*  52 */         String digitos = numero.substring(3);
/*     */         
/*  54 */         logger.debug(" numeroMascara " + numero + "|" + codigo + "|" + digitos);
/*  55 */         if (codigo.equals("426") || (codigo.equals("416") && digitos.length() == 7)) {
/*  56 */           valor = numero;
/*     */         } else {
/*  58 */           logger.error("Error: numeroMascara " + numero + "|" + codigo + "|" + digitos);
/*  59 */           valor = null;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/*  66 */     return valor;
/*     */   }
/*     */   
/*     */   public String numeroMascara(String numero) {
/*  70 */     String valor = "";
/*  71 */     if (numero.length() >= 10 && numero.length() < 12) {
/*     */       
/*  73 */       valor = numero;
/*     */ 
/*     */       
/*  76 */       if (numero.length() == 11) {
/*  77 */         numero = numero.substring(1, numero.length());
/*  78 */         String codigo = numero.substring(0, 3);
/*  79 */         String digitos = numero.substring(3);
/*     */         
/*  81 */         logger.debug(" numeroMascara " + numero + "|" + codigo + "|" + digitos);
/*  82 */         if (codigo.equals("426") || (codigo.equals("416") && digitos.length() == 7)) {
/*  83 */           valor = numero;
/*     */         } else {
/*  85 */           logger.error("Error: numeroMascara " + numero + "|" + codigo + "|" + digitos);
/*  86 */           valor = null;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/*  93 */     return valor;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String encryptDecryptEAS(String originalText) throws InvalidKeyException {
/*  99 */     String resultadoBase = null;
/*     */     
/*     */     try {
/* 102 */       String secretKey = "09051985";
/* 103 */       int length = 16;
/* 104 */       int originalLength = 8;
/* 105 */       String paddedPassword = String.format("%-" + length + "s", new Object[] { secretKey }).replace(' ', '0');
/*     */ 
/*     */       
/* 108 */       Key aesKey = new SecretKeySpec(paddedPassword.getBytes(), "AES");
/* 109 */       Cipher cipher = Cipher.getInstance("AES");
/* 110 */       cipher.init(1, aesKey);
/* 111 */       byte[] encrypted = cipher.doFinal(originalText.getBytes());
/* 112 */       resultadoBase = Base64.getEncoder().encodeToString(encrypted);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     }
/* 118 */     catch (NoSuchAlgorithmException ex) {
/* 119 */       logger.error(" NoSuchAlgorithmException encryptDecryptEAS " + ex);
/* 120 */     } catch (NoSuchPaddingException ex) {
/* 121 */       logger.error(" NoSuchPaddingException encryptDecryptEAS " + ex);
/* 122 */     } catch (IllegalBlockSizeException ex) {
/* 123 */       logger.error(" IllegalBlockSizeException encryptDecryptEAS " + ex);
/* 124 */     } catch (BadPaddingException ex) {
/* 125 */       logger.error(" BadPaddingException encryptDecryptEAS " + ex);
/*     */     } 
/*     */     
/* 128 */     return resultadoBase;
/*     */   }
/*     */ 
/*     */   
/*     */   public String DecryptBse(String originalText) throws InvalidKeyException {
/* 133 */     String resultadoBase = null;
/*     */     
/*     */     try {
/* 136 */       String secretKey = "09051985";
/* 137 */       int length = 16;
/* 138 */       int originalLength = 8;
/* 139 */       String paddedPassword = String.format("%-" + length + "s", new Object[] { secretKey }).replace(' ', '0');
/*     */ 
/*     */       
/* 142 */       Key aesKey = new SecretKeySpec(paddedPassword.getBytes(), "AES");
/* 143 */       Cipher cipher = Cipher.getInstance("AES");
/* 144 */       cipher.init(2, aesKey);
/* 145 */       resultadoBase = new String(cipher.doFinal(Base64.getDecoder().decode(originalText)));
/*     */     
/*     */     }
/* 148 */     catch (Exception ex) {
/*     */       
/* 150 */       logger.error(" Exception DecryptBse.DECRYPT_MODE  " + ex);
/*     */     } 
/* 152 */     return resultadoBase;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getfechPago(String numero) {
/* 160 */     logger.debug("|Operaciones.getfechPago()| " + numero);
/* 161 */     String valor = "0";
/*     */     
/* 163 */     if (numero.length() >= 10) {
/* 164 */       if (numero.length() == 11) {
/* 165 */         numero = numero.substring(1, numero.length());
/*     */       }
/* 167 */       valor = numero.substring(numero.length() - 2, numero.length());
/*     */     } 
/*     */     
/* 170 */     return validarFechaPago(valor);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String validarFechaPago(String digito) {
/* 176 */     logger.debug("|Operaciones.validarFechaPago()| digito para calcular la fecha  " + digito);
/* 177 */     Calendar cal = Calendar.getInstance();
/*     */     
/* 179 */     String valor = "31 de cada mes";
/*     */     
/* 181 */     if (digito.equals("00") || digito.equals("01") || digito.equals("02") || digito.equals("03")) {
/* 182 */       valor = "02 de cada mes";
/*     */     }
/*     */     
/* 185 */     if (digito.equals("04") || digito.equals("05") || digito.equals("06") || digito.equals("07")) {
/* 186 */       valor = "03 de cada mes";
/*     */     }
/*     */     
/* 189 */     if (digito.equals("08") || digito.equals("09") || digito.equals("10") || digito.equals("11")) {
/* 190 */       valor = "04 de cada mes";
/*     */     }
/*     */     
/* 193 */     if (digito.equals("12") || digito.equals("13") || digito.equals("14") || digito.equals("15")) {
/* 194 */       valor = "05 de cada mes";
/*     */     }
/*     */     
/* 197 */     if (digito.equals("16") || digito.equals("17") || digito.equals("18") || digito.equals("19")) {
/* 198 */       valor = "06 de cada mes";
/*     */     }
/*     */     
/* 201 */     if (digito.equals("20") || digito.equals("21") || digito.equals("22") || digito.equals("23")) {
/* 202 */       valor = "07 de cada mes";
/*     */     }
/*     */     
/* 205 */     if (digito.equals("24") || digito.equals("25") || digito.equals("26") || digito.equals("27")) {
/* 206 */       valor = "08 de cada mes";
/*     */     }
/*     */     
/* 209 */     if (digito.equals("28") || digito.equals("29") || digito.equals("30") || digito.equals("31")) {
/* 210 */       valor = "09 de cada mes";
/*     */     }
/*     */     
/* 213 */     if (digito.equals("32") || digito.equals("33") || digito.equals("34") || digito.equals("35")) {
/* 214 */       valor = "10 de cada mes";
/*     */     }
/*     */     
/* 217 */     if (digito.equals("36") || digito.equals("37") || digito.equals("38") || digito.equals("39")) {
/* 218 */       valor = "11 de cada mes";
/*     */     }
/*     */     
/* 221 */     if (digito.equals("40") || digito.equals("41") || digito.equals("42") || digito.equals("43")) {
/* 222 */       valor = "12 de cada mes";
/*     */     }
/*     */     
/* 225 */     if (digito.equals("44") || digito.equals("45") || digito.equals("46") || digito.equals("47")) {
/* 226 */       valor = "13 de cada mes";
/*     */     }
/*     */     
/* 229 */     if (digito.equals("48") || digito.equals("49") || digito.equals("50") || digito.equals("51")) {
/* 230 */       valor = "14 de cada mes";
/*     */     }
/*     */     
/* 233 */     if (digito.equals("52") || digito.equals("53") || digito.equals("54") || digito.equals("55")) {
/* 234 */       valor = "15 de cada mes";
/*     */     }
/*     */     
/* 237 */     if (digito.equals("56") || digito.equals("57") || digito.equals("58") || digito.equals("59")) {
/* 238 */       valor = "16 de cada mes";
/*     */     }
/*     */     
/* 241 */     if (digito.equals("60") || digito.equals("61") || digito.equals("62") || digito.equals("63")) {
/* 242 */       valor = "17 de cada mes";
/*     */     }
/*     */     
/* 245 */     if (digito.equals("64") || digito.equals("65") || digito.equals("66") || digito.equals("67")) {
/* 246 */       valor = "18 de cada mes";
/*     */     }
/*     */     
/* 249 */     if (digito.equals("68") || digito.equals("69") || digito.equals("70") || digito.equals("71")) {
/* 250 */       valor = "19 de cada mes";
/*     */     }
/*     */     
/* 253 */     if (digito.equals("72") || digito.equals("73") || digito.equals("74") || digito.equals("75")) {
/* 254 */       valor = "20 de cada mes";
/*     */     }
/*     */     
/* 257 */     if (digito.equals("76") || digito.equals("77") || digito.equals("78") || digito.equals("79")) {
/* 258 */       valor = "21 de cada mes";
/*     */     }
/*     */     
/* 261 */     if (digito.equals("80") || digito.equals("81") || digito.equals("82") || digito.equals("83")) {
/* 262 */       valor = "22 de cada mes";
/*     */     }
/*     */     
/* 265 */     if (digito.equals("84") || digito.equals("85") || digito.equals("86") || digito.equals("87")) {
/* 266 */       valor = "23 de cada mes";
/*     */     }
/*     */     
/* 269 */     if (digito.equals("88") || digito.equals("89") || digito.equals("90") || digito.equals("91")) {
/* 270 */       valor = "24 de cada mes";
/*     */     }
/*     */     
/* 273 */     if (digito.equals("92") || digito.equals("93") || digito.equals("94") || digito.equals("95")) {
/* 274 */       valor = "25 de cada mes";
/*     */     }
/*     */     
/* 277 */     if (digito.equals("96") || digito.equals("97") || digito.equals("98") || digito.equals("99")) {
/* 278 */       valor = "26 de cada mes";
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 284 */     return valor;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean validarCorreo(String valor) {
/* 289 */     boolean respuesta = true;
/* 290 */     if (valor == null || valor.length() == 0) {
/* 291 */       respuesta = false;
/*     */     } else {
/*     */       
/* 294 */       Pattern pattern = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");
/* 295 */       if (!pattern.matcher(valor).matches()) {
/* 296 */         respuesta = false;
/*     */       }
/*     */     } 
/*     */     
/* 300 */     return respuesta;
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
/*     */   
/*     */   public boolean validarNumeros(String telefonoHabitacion, String telefonoOficina) {
/* 315 */     String regex = "\\d+";
/*     */     
/* 317 */     if (telefonoHabitacion == null || telefonoHabitacion.length() == 0 || !telefonoHabitacion.matches(regex)) {
/* 318 */       return false;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 324 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String tiempoDeEjecucion(long tiempoInicio, String codigoMetodo) {
/* 335 */     long tiempoFinal = System.currentTimeMillis();
/* 336 */     long tiempoEjecucion = tiempoFinal - tiempoInicio;
/*     */     
/* 338 */     return codigoMetodo + "|tiempoEjecucion:" + tiempoEjecucion;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean validarMascara(String numero) {
/* 344 */     if (numero.length() >= 10) {
/* 345 */       if (numero.length() == 11) {
/* 346 */         numero = numero.substring(1, numero.length());
/*     */       }
/* 348 */       String codigo = numero.substring(0, 3);
/* 349 */       String digitos = numero.substring(3);
/* 350 */       if ((codigo.equals("426") || codigo.equals("416") || codigo.equals("199") || codigo.equals("158")) && digitos.length() == 7) {
/* 351 */         return true;
/*     */       }
/*     */     } 
/* 354 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\aruizc01\Desktop\api-movilnet.war!\WEB-INF\classes\ve\com\movilnet\api\servic\\util\Operaciones.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */