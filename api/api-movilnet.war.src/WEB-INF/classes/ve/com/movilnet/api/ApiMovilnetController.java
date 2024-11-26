/*    */ package WEB-INF.classes.ve.com.movilnet.api;
/*    */ 
/*    */ import org.springframework.stereotype.Controller;
/*    */ import org.springframework.web.bind.annotation.GetMapping;
/*    */ import org.springframework.web.bind.annotation.RequestMapping;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Controller
/*    */ @RequestMapping({"/api"})
/*    */ public class ApiMovilnetController
/*    */ {
/*    */   @GetMapping({"/"})
/*    */   public String handleGetSignin() {
/* 22 */     return "redirect:/index.html";
/*    */   }
/*    */ }


/* Location:              C:\Users\aruizc01\Desktop\api-movilnet.war!\WEB-INF\classes\ve\com\movilnet\api\ApiMovilnetController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */