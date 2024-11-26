/*     */ package WEB-INF.classes.ve.com.movilnet.api.ws.autenticate;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import javax.xml.namespace.QName;
/*     */ import javax.xml.soap.MessageFactory;
/*     */ import javax.xml.soap.SOAPBody;
/*     */ import javax.xml.soap.SOAPBodyElement;
/*     */ import javax.xml.soap.SOAPElement;
/*     */ import javax.xml.soap.SOAPException;
/*     */ import javax.xml.soap.SOAPMessage;
/*     */ import javax.xml.ws.Dispatch;
/*     */ import javax.xml.ws.Service;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import ve.com.movilnet.api.response.ResultGSMTO;
/*     */ import ve.com.movilnet.api.response.RetrieveLinesResponseGSMTO;
/*     */ 
/*     */ public class ClienteSOAP {
/*     */   private static final String URL_WSDL = "http://192.168.0.17:8888/wsppaybasesubscriber/subscriber";
/*  20 */   private static String NAMESPACE = "http://service.subscriber.base.ws.prepay.cia.gdis.movilnet.com.ve/";
/*     */   
/*     */   private static final String SERVICE_NAME = "subscriber";
/*     */   private static final String PORT_NAME = "subscriber";
/*  24 */   static final Logger logger = LoggerFactory.getLogger(ve.com.movilnet.api.ws.autenticate.ClienteSOAP.class);
/*     */ 
/*     */   
/*     */   public void messageFactoryManual() throws SOAPException {
/*  28 */     RetrieveLinesResponseGSMTO retrieveLinesResponseGSMTO = new RetrieveLinesResponseGSMTO();
/*     */     
/*  30 */     NAMESPACE = "http://to.ws.cia.gdis.movilnet.com.ve/types/";
/*  31 */     QName serviceName = new QName(NAMESPACE, "subscriber");
/*  32 */     QName portName = new QName(NAMESPACE, "subscriber");
/*  33 */     Service service = Service.create(serviceName);
/*  34 */     service.addPort(portName, "http://schemas.xmlsoap.org/wsdl/soap/http", "http://192.168.0.17:8888/wsppaybasesubscriber/subscriber");
/*     */     
/*  36 */     Dispatch<SOAPMessage> dispatch = service.createDispatch(portName, SOAPMessage.class, Service.Mode.MESSAGE);
/*     */ 
/*     */     
/*  39 */     MessageFactory messageFactory = MessageFactory.newInstance();
/*  40 */     SOAPMessage message = messageFactory.createMessage();
/*     */ 
/*     */     
/*  43 */     SOAPBody body = message.getSOAPBody();
/*     */ 
/*     */     
/*  46 */     QName methodName = new QName("http://service.subscriber.base.ws.prepay.cia.gdis.movilnet.com.ve/", "retrieveLinesByCIGSM");
/*  47 */     SOAPBodyElement method = body.addBodyElement(methodName);
/*     */ 
/*     */     
/*  50 */     QName requestName = new QName("http://service.subscriber.base.ws.prepay.cia.gdis.movilnet.com.ve/", "retrieveLinesbyCIRequestTO");
/*  51 */     SOAPElement requestElement = method.addChildElement(requestName);
/*     */ 
/*     */     
/*  54 */     QName applicationClientName = new QName("http://service.subscriber.base.ws.prepay.cia.gdis.movilnet.com.ve/", "applicationClient");
/*  55 */     SOAPElement applicationClientElement = requestElement.addChildElement(applicationClientName);
/*     */ 
/*     */ 
/*     */     
/*  59 */     applicationClientElement.addChildElement("application").addTextNode("AUTOGE_WEB");
/*  60 */     applicationClientElement.addChildElement("code").addTextNode("AUTOGE_WEB");
/*     */ 
/*     */     
/*  63 */     QName securityName = new QName("http://service.subscriber.base.ws.prepay.cia.gdis.movilnet.com.ve/", "security");
/*  64 */     SOAPElement securityElement = requestElement.addChildElement(securityName);
/*     */ 
/*     */     
/*  67 */     securityElement.addChildElement("ccwsPassword").addTextNode("AUTOGE_WEB1");
/*  68 */     securityElement.addChildElement("ccwsUser").addTextNode("AUTOGE_WEB");
/*  69 */     requestElement.addChildElement("serviceProvider").addTextNode("MOVILNET");
/*  70 */     requestElement.addChildElement("technology").addTextNode("3");
/*  71 */     requestElement.addChildElement("transactionId").addTextNode("PRUEBAS-1");
/*  72 */     requestElement.addChildElement("ssn").addTextNode("I");
/*  73 */     requestElement.addChildElement("ssnType").addTextNode("18189160");
/*  74 */     requestElement.addChildElement("userId").addTextNode("AUTOGE_WEB");
/*     */ 
/*     */     
/*  77 */     SOAPMessage response = dispatch.invoke(message);
/*     */ 
/*     */ 
/*     */     
/*  81 */     SOAPBody soapBody = response.getSOAPBody();
/*     */ 
/*     */ 
/*     */     
/*  85 */     Iterator<?> bodyIterator = soapBody.getChildElements();
/*  86 */     while (bodyIterator.hasNext()) {
/*  87 */       SOAPElement bodyElement = (SOAPElement)bodyIterator.next();
/*  88 */       if (bodyElement.getLocalName().equals("retrieveLinesByCIGSMResponse")) {
/*  89 */         SOAPElement resultElement = bodyElement.getChildElements().next();
/*  90 */         String str = resultElement.getAttribute("href");
/*     */       } 
/*     */       
/*  93 */       if (bodyElement.getLocalName().equals("RetrieveLinesResponseGSMTO")) {
/*  94 */         Iterator<?> retrieveLinesResponseGSMTOIterator = bodyElement.getChildElements();
/*  95 */         while (retrieveLinesResponseGSMTOIterator.hasNext()) {
/*  96 */           SOAPElement retrieveLinesResponseGSMTOElement = (SOAPElement)retrieveLinesResponseGSMTOIterator.next();
/*  97 */           String elementName = retrieveLinesResponseGSMTOElement.getLocalName();
/*  98 */           String elementValue = retrieveLinesResponseGSMTOElement.getValue();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 104 */           if (elementName.equalsIgnoreCase("executionTime")) {
/* 105 */             retrieveLinesResponseGSMTO.setExecutionTime(Long.parseLong(elementValue)); continue;
/* 106 */           }  if (elementName.equalsIgnoreCase("origin")) {
/* 107 */             retrieveLinesResponseGSMTO.setOrigin(elementValue); continue;
/*     */           } 
/* 109 */           if (elementName.equalsIgnoreCase("responseCode")) {
/* 110 */             retrieveLinesResponseGSMTO.setResponseCode(elementValue); continue;
/*     */           } 
/* 112 */           if (elementName.equalsIgnoreCase("responseDescription")) {
/* 113 */             retrieveLinesResponseGSMTO.setResponseDescription(elementValue); continue;
/*     */           } 
/* 115 */           if (elementName.equalsIgnoreCase("responseMessage")) {
/* 116 */             retrieveLinesResponseGSMTO.setResponseMessage(elementValue); continue;
/*     */           } 
/* 118 */           if (elementName.equalsIgnoreCase("transactionId")) {
/* 119 */             retrieveLinesResponseGSMTO.setTransactionId(elementValue);
/*     */             continue;
/*     */           } 
/* 122 */           if (elementName.equalsIgnoreCase("ocurrenciaTotal")) {
/* 123 */             retrieveLinesResponseGSMTO.setOcurrenciaTotal(Integer.parseInt(elementValue));
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 132 */     SOAPBody soapBody2 = response.getSOAPBody();
/* 133 */     int i = 0;
/*     */     
/* 135 */     ResultGSMTO[] resultado = new ResultGSMTO[retrieveLinesResponseGSMTO.getOcurrenciaTotal()];
/*     */ 
/*     */     
/* 138 */     Iterator<?> bodyIterator2 = soapBody2.getChildElements();
/* 139 */     while (bodyIterator2.hasNext()) {
/* 140 */       SOAPElement bodyElement = (SOAPElement)bodyIterator2.next();
/* 141 */       if (bodyElement.getLocalName().equals("ArrayOfResultGSMTO")) {
/* 142 */         Iterator<?> arrayOfResultGSMTOIterator = bodyElement.getChildElements();
/* 143 */         while (arrayOfResultGSMTOIterator.hasNext()) {
/* 144 */           SOAPElement arrayOfResultGSMTOElement = (SOAPElement)arrayOfResultGSMTOIterator.next();
/* 145 */           String href = arrayOfResultGSMTOElement.getAttribute("href");
/* 146 */           href = href.replace("#", "");
/*     */           
/* 148 */           Iterator<?> allBodyIterator = soapBody2.getChildElements();
/*     */ 
/*     */           
/* 151 */           while (allBodyIterator.hasNext()) {
/* 152 */             ResultGSMTO resultGSMTO = new ResultGSMTO();
/*     */             
/* 154 */             SOAPElement allBodyElement = (SOAPElement)allBodyIterator.next();
/*     */             
/* 156 */             if (allBodyElement.getAttribute("id").equals(href)) {
/*     */ 
/*     */ 
/*     */ 
/*     */               
/* 161 */               Iterator<?> resultGSMTOIterator = allBodyElement.getChildElements();
/* 162 */               while (resultGSMTOIterator.hasNext()) {
/*     */                 
/* 164 */                 SOAPElement resultGSMTOElement = (SOAPElement)resultGSMTOIterator.next();
/* 165 */                 String elementName = resultGSMTOElement.getLocalName();
/* 166 */                 String elementValue = resultGSMTOElement.getValue();
/*     */ 
/*     */ 
/*     */ 
/*     */                 
/* 171 */                 if (elementName.equalsIgnoreCase("cosName")) {
/* 172 */                   resultGSMTO.setCosName(elementValue); continue;
/* 173 */                 }  if (elementName.equalsIgnoreCase("fechaActivacion")) {
/* 174 */                   resultGSMTO.setFechaActivacion(elementValue); continue;
/*     */                 } 
/* 176 */                 if (elementName.equalsIgnoreCase("generico1")) {
/* 177 */                   resultGSMTO.setGenerico1(elementValue); continue;
/*     */                 } 
/* 179 */                 if (elementName.equalsIgnoreCase("nombreSubscriptor")) {
/* 180 */                   resultGSMTO.setNombreSubscriptor(elementValue); continue;
/*     */                 } 
/* 182 */                 if (elementName.equalsIgnoreCase("statusLinea")) {
/* 183 */                   resultGSMTO.setStatusLinea(elementValue); continue;
/*     */                 } 
/* 185 */                 if (elementName.equalsIgnoreCase("subscriberId")) {
/* 186 */                   resultGSMTO.setSubscriberId(Long.parseLong(elementValue));
/*     */                   
/* 188 */                   resultado[i] = resultGSMTO;
/* 189 */                   i++;
/* 190 */                   logger.info(i + " resultGSMTOIterator subscriberId operaciones : " + i);
/*     */                 } 
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 206 */     retrieveLinesResponseGSMTO.setResultado(resultado);
/*     */   }
/*     */ }


/* Location:              C:\Users\aruizc01\Desktop\api-movilnet.war!\WEB-INF\classes\ve\com\movilnet\api\ws\autenticate\ClienteSOAP.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */