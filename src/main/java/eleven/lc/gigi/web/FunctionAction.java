package eleven.lc.gigi.web;

import eleven.lc.gigi.myutil.CodeUtil;
import eleven.lc.gigi.myutil.DrawImage;
import eleven.lc.gigi.myutil.MailUtil;
import eleven.lc.gigi.myutil.RSAUtil;
import freemarker.template.TemplateException;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.PrivateKey;
import java.security.PublicKey;

@Controller
public class FunctionAction {

    @RequestMapping(value = "/getPublicKey",method = RequestMethod.POST)
    @ResponseBody
    public String getPublicKey(HttpSession httpSession){
        new RSAUtil();
        PublicKey publicKey = RSAUtil.getRSAPublicKey(RSAUtil.publicModulus, RSAUtil.publicExponent);
        PrivateKey privateKey = RSAUtil.getRSAPrivateKey(RSAUtil.privateModulus, RSAUtil.privateExponent);
        httpSession.setAttribute("privateKey",privateKey);
        return Base64.encodeBase64String(publicKey.getEncoded());
    }

    @RequestMapping(value = "/verificationCode",method = RequestMethod.GET)
    public void DrawCode(HttpServletRequest request, HttpServletResponse response){
        try {
            DrawImage.Create(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Autowired
    MailUtil mailUtil;

    @RequestMapping(value = "/sendCheckCode",method = RequestMethod.POST)
    public void sendCheckCode(HttpSession httpSession,@RequestParam String email) throws MessagingException, IOException, TemplateException {
        String code= CodeUtil.CheckCode();
        httpSession.setAttribute("loginCode",code);
        mailUtil.SendCheckMail(email,code);
    }



}
