package main.wechat;

import main.util.WeixinUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2016/8/2 0002.
 */
@RestController("accessToken")
public class AccessTokenController {


    @RequestMapping(value = "getToken",method = RequestMethod.GET)
    public AccessToken createMenu(String token){
        AccessToken accessToken= WeixinUtil.getAccessToken("补充appid","秘钥");

        return accessToken;
    }





}
