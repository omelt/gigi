package eleven.lc.gigi.web;

import eleven.lc.gigi.myutil.RSAUtil;
import eleven.lc.gigi.web.error.MyException;
import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * 页面测试
 */
@Controller
public class TestAction {

    //发起error
    @RequestMapping("/error500")
    public String error500() throws Exception{
        throw new MyException("异常测试");
    }

    @RequestMapping("/error400/${id}")
    public String error400() throws Exception{
        return "";
    }


    @RequestMapping("/RSAtest")
    public String RSAtest(){
        return "RSAtest";
    }

    @RequestMapping("/testPassword")
    @ResponseBody
    public String testPassword(@RequestParam String encryptPwd,@SessionAttribute PrivateKey privateKey){

        String newSource = RSAUtil.decryptString(privateKey, encryptPwd);

        System.out.println(newSource);

        return "ok";
    }
}
