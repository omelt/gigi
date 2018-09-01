package eleven.lc.gigi.myutil;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class MailUtil {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    Configuration configuration;

    @Async
    public void SendHrefMail(String sender,String id) throws IOException, MessagingException, TemplateException, InterruptedException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom("851736928@qq.com");
        helper.setTo(sender);
        helper.setSubject("GiGi：注册确认");

        Map<String, Object> model = new HashMap();
        model.put("uid",id);
        model.put("email",sender);

        Template t = configuration.getTemplate("mail/mailHref.ftl");
        String content= FreeMarkerTemplateUtils.processTemplateIntoString(t, model);
        helper.setText(content,true);

        mailSender.send(mimeMessage);
    }

    @Async
    public void SendCheckMail(String sender,String code) throws MessagingException, IOException, TemplateException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom("851736928@qq.com");
        helper.setTo(sender);
        helper.setSubject("GiGi：登录验证码");

        Map<String, Object> model = new HashMap();
        model.put("code",code);
        model.put("email",sender);

        Template t = configuration.getTemplate("mail/mailCheck.ftl");
        String content= FreeMarkerTemplateUtils.processTemplateIntoString(t, model);
        helper.setText(content,true);

        mailSender.send(mimeMessage);
    }
}
