/*     */ package WEB-INF.classes.ve.com.movilnet.api.ws.autenticate;
/*     */ 
/*     */ import java.net.MalformedURLException;
/*     */ import java.net.URL;
/*     */ import java.rmi.RemoteException;
/*     */ import java.util.Vector;
/*     */ import javax.xml.rpc.ServiceException;
/*     */ import oracle.soap.transport.http.OracleSOAPHTTPConnection;
/*     */ import org.apache.soap.Fault;
/*     */ import org.apache.soap.SOAPException;
/*     */ import org.apache.soap.encoding.SOAPMappingRegistry;
/*     */ import org.apache.soap.encoding.soapenc.BeanSerializer;
/*     */ import org.apache.soap.rpc.Call;
/*     */ import org.apache.soap.rpc.Parameter;
/*     */ import org.apache.soap.rpc.Response;
/*     */ import org.apache.soap.transport.SOAPTransport;
/*     */ import org.apache.soap.util.xml.Deserializer;
/*     */ import org.apache.soap.util.xml.QName;
/*     */ import org.apache.soap.util.xml.Serializer;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import ws_authenticate.client.proxy.Ws_authenticate;
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
/*     */ public class WsauthenticateStub
/*     */ {
/*  37 */   private OracleSOAPHTTPConnection m_httpConnection = null;
/*  38 */   private SOAPMappingRegistry m_smr = null;
/*  39 */   static final Logger logger = LoggerFactory.getLogger(ve.com.movilnet.api.ws.autenticate.WsauthenticateStub.class);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Ws_authenticate myWs_authenticate;
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
/*     */   public String generateKey(String p_url, String p_subscriber, String p_mail, String p_application_name, String p_origin_application) throws SOAPException, ServiceException, RemoteException, MalformedURLException {
/*  61 */     long t1 = System.currentTimeMillis();
/*  62 */     logger.info(t1 + "|WsauthenticateStub generateKey  antes  OracleSOAPHTTPConnection  p_subscriber " + p_subscriber + " p_mail " + p_mail + " p_application_name " + p_application_name + " " + p_origin_application);
/*     */     
/*  64 */     String returnVal = null;
/*  65 */     this.m_httpConnection = new OracleSOAPHTTPConnection();
/*  66 */     this.m_smr = new SOAPMappingRegistry();
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/*  72 */       OracleSOAPHTTPConnection m_httpConnection = null;
/*  73 */       SOAPMappingRegistry m_smr = null;
/*  74 */       String _endpoint = p_url;
/*  75 */       URL endpointURL = new URL(_endpoint);
/*  76 */       m_httpConnection = new OracleSOAPHTTPConnection();
/*  77 */       m_smr = new SOAPMappingRegistry();
/*  78 */       BeanSerializer beanSer = new BeanSerializer();
/*  79 */       m_smr.mapTypes("http://schemas.xmlsoap.org/soap/envelope/", new QName("http://ve.com.movilnet.aster_validate.gateway/IWs_authenticate.xsd", "return"), String.class, (Serializer)beanSer, (Deserializer)beanSer);
/*     */ 
/*     */ 
/*     */       
/*  83 */       Call call = new Call();
/*  84 */       call.setSOAPMappingRegistry(m_smr);
/*  85 */       call.setMethodName("GenerateKey");
/*  86 */       call.setEncodingStyleURI("http://schemas.xmlsoap.org/soap/encoding/");
/*  87 */       call.setTargetObjectURI("ws_authenticate");
/*  88 */       call.setSOAPTransport((SOAPTransport)m_httpConnection);
/*  89 */       Vector<Parameter> params = new Vector();
/*  90 */       params.addElement(new Parameter("p_subscriber", String.class, p_subscriber, "http://schemas.xmlsoap.org/soap/encoding/"));
/*     */       
/*  92 */       params.addElement(new Parameter("p_mail", String.class, p_mail, "http://schemas.xmlsoap.org/soap/encoding/"));
/*  93 */       params.addElement(new Parameter("p_application_name", String.class, p_application_name, "http://schemas.xmlsoap.org/soap/encoding/"));
/*     */       
/*  95 */       params.addElement(new Parameter("p_origin_application", String.class, p_origin_application, "http://schemas.xmlsoap.org/soap/encoding/"));
/*     */       
/*  97 */       call.setParams(params);
/*  98 */       Response responseCall = call.invoke(endpointURL, "");
/*  99 */       logger.info(t1 + "|Perform| superado el Response luego del invoke");
/*     */       
/* 101 */       if (!responseCall.generatedFault()) {
/* 102 */         Parameter result = responseCall.getReturnValue();
/* 103 */         returnVal = valorFormatRA((String)result.getValue());
/* 104 */         logger.debug(t1 + "||perform| superado el Response luego del GenerateKey " + returnVal);
/*     */       } else {
/* 106 */         Fault fault = responseCall.getFault();
/* 107 */         throw new SOAPException(fault.getFaultCode(), fault.getFaultString());
/*     */       }
/*     */     
/*     */     }
/* 111 */     catch (Exception e) {
/* 112 */       logger.error(t1 + "|  SOAPException |...", e);
/* 113 */       return "RA4002";
/*     */     } 
/*     */     
/* 116 */     return returnVal;
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
/*     */ 
/*     */ 
/*     */   
/*     */   public String authenticateFullDigit(long t1, String p_url, String p_user, String p_password, String p_app, String p_origin_application) {
/* 134 */     logger.info(t1 + "|WsauthenticateStub authenticateFullDigit  antes de OracleSOAPHTTPConnection  p_subscriber " + p_user);
/*     */     
/* 136 */     String returnVal = null;
/* 137 */     this.m_httpConnection = new OracleSOAPHTTPConnection();
/* 138 */     this.m_smr = new SOAPMappingRegistry();
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 144 */       OracleSOAPHTTPConnection m_httpConnection = null;
/* 145 */       SOAPMappingRegistry m_smr = null;
/* 146 */       String _endpoint = p_url;
/* 147 */       URL endpointURL = new URL(_endpoint);
/* 148 */       m_httpConnection = new OracleSOAPHTTPConnection();
/* 149 */       m_smr = new SOAPMappingRegistry();
/* 150 */       BeanSerializer beanSer = new BeanSerializer();
/* 151 */       m_smr.mapTypes("http://schemas.xmlsoap.org/soap/envelope/", new QName("http://ve.com.movilnet.aster_validate.gateway/IWs_authenticate.xsd", "return"), String.class, (Serializer)beanSer, (Deserializer)beanSer);
/*     */ 
/*     */ 
/*     */       
/* 155 */       Call call = new Call();
/* 156 */       call.setSOAPMappingRegistry(m_smr);
/* 157 */       call.setMethodName("AuthenticateFullDigit");
/* 158 */       call.setEncodingStyleURI("http://schemas.xmlsoap.org/soap/encoding/");
/* 159 */       call.setTargetObjectURI("ws_authenticate");
/* 160 */       call.setSOAPTransport((SOAPTransport)m_httpConnection);
/* 161 */       Vector<Parameter> params = new Vector();
/* 162 */       params.addElement(new Parameter("p_user", String.class, p_user, "http://schemas.xmlsoap.org/soap/encoding/"));
/* 163 */       params.addElement(new Parameter("p_password", String.class, p_password, "http://schemas.xmlsoap.org/soap/encoding/"));
/*     */       
/* 165 */       params.addElement(new Parameter("p_app", String.class, p_app, "http://schemas.xmlsoap.org/soap/encoding/"));
/* 166 */       call.setParams(params);
/* 167 */       Response responseCall = call.invoke(endpointURL, "");
/*     */       
/* 169 */       if (!responseCall.generatedFault()) {
/* 170 */         Parameter result = responseCall.getReturnValue();
/* 171 */         returnVal = valorFormatRA((String)result.getValue());
/* 172 */         logger.debug(t1 + "||perform| superado el Response luego del AuthenticateFullDigit " + returnVal);
/*     */       } else {
/* 174 */         Fault fault = responseCall.getFault();
/* 175 */         throw new SOAPException(fault.getFaultCode(), fault.getFaultString());
/*     */       }
/*     */     
/*     */     }
/* 179 */     catch (Exception e) {
/* 180 */       logger.error(t1 + "|SOAPException |...", e);
/* 181 */       return "RA4005";
/*     */     } 
/*     */     
/* 184 */     return returnVal;
/*     */   }
/*     */   
/*     */   public String valorFormatRA(String valor) {
/* 188 */     String returnVal = "RA4005";
/*     */     
/* 190 */     if (valor.equals("WS002"))
/* 191 */       return returnVal = "RA5003"; 
/* 192 */     if (valor.equals("WS020"))
/* 193 */       return returnVal = "RA5004 "; 
/* 194 */     if (valor.equals("WS021"))
/* 195 */       return returnVal = "RA5005"; 
/* 196 */     if (valor.equals("WS006"))
/* 197 */       return returnVal = "RA5002"; 
/* 198 */     if (valor.equals("WS001"))
/* 199 */       return returnVal = "RA2002"; 
/* 200 */     if (valor.equals("S"))
/* 201 */       return returnVal = "RA2003"; 
/* 202 */     if (valor.equals("N"))
/* 203 */       return returnVal = "RA5008"; 
/* 204 */     if (valor.equals("NE"))
/* 205 */       return returnVal = "RA5009"; 
/* 206 */     if (valor.equals("NA"))
/* 207 */       return returnVal = "RA5010"; 
/* 208 */     if (valor.equals("XE"))
/* 209 */       return returnVal = "RA5011"; 
/* 210 */     if (valor.equals("PV")) {
/* 211 */       return returnVal = "RA5012";
/*     */     }
/*     */     
/* 214 */     return returnVal;
/*     */   }
/*     */ }


/* Location:              C:\Users\aruizc01\Desktop\api-movilnet.war!\WEB-INF\classes\ve\com\movilnet\api\ws\autenticate\WsauthenticateStub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */