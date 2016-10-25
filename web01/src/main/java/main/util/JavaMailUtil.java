package main.util;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * 参考链接 http://blog.csdn.net/Coding_One/article/details/51354456
 */
public class JavaMailUtil {
    public static void main(String[] args) throws MessagingException {
        test();
    }

    public static void test() throws MessagingException {
        //发送邮箱验证
//        try {
            Properties prop = new Properties();
            prop.setProperty("mail.transport.protocol", "smtp");
            prop.setProperty("mail.smtp.host", "smtp.qq.com");
            prop.setProperty("mail.smtp.auth", "true");
            prop.put("mail.smtp.port","465");
            prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            prop.put("mail.smtp.socketFactory.port", "465");
            prop.put("mail.smtp.starttls.enable","true");
            prop.setProperty("mail.debug", "true");
            Authenticator authenticator = new PopAuthenticator("390872451@qq.com", "sxikmnhkvdikcaic");
            //创建会话
            Session session = Session.getInstance(prop,authenticator);
            //填写信封写信
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress("390872451@qq.com"));
            msg.setRecipient(Message.RecipientType.TO, new InternetAddress("617966855@qq.com"));
            msg.setSubject("激活邮箱!");
            msg.setText("你好请到这个地址激活你的账号");
            //验证用户名密码发送邮件
            Transport transport = session.getTransport();
            //transport.connect("1274444444@qq.com","4444444");
            transport.send(msg);
//        }

    }



}
