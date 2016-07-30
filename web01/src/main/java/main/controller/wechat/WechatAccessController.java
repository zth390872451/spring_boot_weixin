package main.controller.wechat;

import main.service.wechat.CoreService;
import main.util.SHA1;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

/**
 * Created by Administrator on 2016/7/30 0030.
 */
@RestController
@RequestMapping("wechatAccess")
public class WechatAccessController {

    public static final String TOKEN ="test";
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(method = RequestMethod.GET)
    public String get(String signature,String timestamp,
     String nonce,String echostr){
        logger.info("微信接入验证***1~~~!");
        String[] strings = {TOKEN,timestamp,nonce};
        Arrays.sort(strings);//字典排序 FE7AC1ADA3918020A3CE4D40B62B613D450E9902
        String unionStr = strings[0]+strings[1]+strings[2];
        String digest = new SHA1().getDigestOfString(unionStr.getBytes());
        if (digest.equals(signature)){
            logger.info("微信接入验证:签名成功!");
            return echostr;
        }
        logger.info("微信接入验证:签名失败!");
        return echostr;
    }

    @Autowired
    private CoreService coreService;

    @RequestMapping(method = RequestMethod.POST)
    public String post(HttpServletRequest request, HttpServletResponse response){
       String responseContent ;
        responseContent = coreService.processRequest(request);
        return responseContent;
    }


}
