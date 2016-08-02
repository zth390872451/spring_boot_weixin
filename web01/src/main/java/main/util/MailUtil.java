package main.util;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.velocity.VelocityEngineUtils;

import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Hashtable;
import java.util.Map;

/**
 * Created by Administrator on 2016/7/30 0030.
 * 邮件服务
 */
//@Service
public class MailUtil {
//    @Autowired
    private JavaMailSender mailSender;
//    @Autowired
    private VelocityEngine velocityEngine;

    /**
     * 发送邮件
     * @param from
     * @param to
     * @param subject 主题
     * @param textContent 内容
     */
    public void sendMail(String from,String to,String subject,String textContent)throws Exception{
        SimpleMailMessage message = new SimpleMailMessage();
       /* message.setFrom("zth390872451@gmail.com");
        message.setTo("390872451@qq.com");
        message.setSubject("测试邮件：主体");
        message.setText("测试邮件：内容");*/
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(textContent);
        mailSender.send(message);
    }

    /**
     *发送附件
     *
     */
    public void sendAttachmentsMail(
            String from,String to,
            String subject,String textContent,
            File file
    )throws Exception{
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true);
        helper.setFrom(from);
        helper.setTo(to);
        helper.setText(textContent);
        FileSystemResource fileSystemResource = new FileSystemResource(file);
        helper.addAttachment("附件01",fileSystemResource);
        helper.addAttachment("附件02",fileSystemResource);
        mailSender.send(mimeMessage);
    }

    /**
     * 嵌入静态资源 发送带文件的邮件
     *
     * 需要注意的是addInline函数中资源名称weixin需要与正文中cid:weixin对应起来
     */
    public void sendInlineMail() throws Exception {

        MimeMessage mimeMessage = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom("dyc87112@qq.com");
        helper.setTo("dyc87112@qq.com");
        helper.setSubject("主题：嵌入静态资源");
        helper.setText("<html><body><img src=\"cid:weixin\" ></body></html>", true);

        FileSystemResource file = new FileSystemResource(new File("weixin.jpg"));
        helper.addInline("weixin", file);
        mailSender.send(mimeMessage);

    }

    /**
     * 模板邮件
     */
    public void sendTemplateMail(String from,String to,
                                 String subject,String textContent) throws Exception {

        MimeMessage mimeMessage = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom("dyc87112@qq.com");
        helper.setTo("dyc87112@qq.com");
        helper.setSubject("主题：模板邮件");

        Map<String, Object> model = new Hashtable<String, Object>();
        model.put("username", "didi");
        String text = VelocityEngineUtils.mergeTemplateIntoString(
                velocityEngine, "templates.vm", "UTF-8", model);
        helper.setText(text, true);

        mailSender.send(mimeMessage);
    }


}
