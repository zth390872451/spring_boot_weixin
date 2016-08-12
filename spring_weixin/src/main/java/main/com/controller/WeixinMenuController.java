package main.com.controller;

import main.com.domain.Menu;
import main.com.domain.factory.MenuManager;
import main.com.util.HttpUtil;
import net.sf.json.JSONObject;
import org.springframework.mail.MailParseException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/8/2 0002.
 * 微信菜单接口:
 * 1、创建
 * 2、查询
 * 3、删除
 * 4、事件推送(**无**)
 * 5、个性化菜单(**无**)
 * 6、获取公众号菜单配置
 */
@RestController
@RequestMapping("menu")
public class WeixinMenuController {
    public static final String TOKEN = "access_token";
    //查询菜单
    public static final String GET_MENU = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token=";
    //创建菜单
    public static final String CREATE_MENU = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=?";
    //删除菜单
    public static final String DELETE_MENU = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=?";
    //个性化菜单
//    public static final String ADDCONDITIONAL_MENU = "https://api.weixin.qq.com/cgi-bin/menu/addconditional?access_token=?";
    //获取公众号的菜单配置
    public static final String GET_CURRENT_SELFMENU_INFO = "https://api.weixin.qq.com/cgi-bin/get_current_selfmenu_info?access_token=?";

    @RequestMapping(value = "get",method = RequestMethod.GET)
    public String getMenu(String token){
        return HttpUtil.httpGetRequest(null, GET_MENU +TOKEN);
    }
    @RequestMapping(value = "createMenu",method = RequestMethod.GET)
    public String createMenu(String token){
        //创建默认菜单
        Menu defaultMenu = MenuManager.getDefaultMenuByCreateButtons();
        String jsonDefaultMenu = JSONObject.fromObject(defaultMenu).toString();
        // 调用接口创建菜单
        /*JSONObject jsonObject = WeixinUtil.httpRequest( CREATE_MENU +TOKEN, "POST", jsonDefaultMenu);
//        HttpUtil.httpPostRequest(null, CREATE_MENU +TOKEN)
        return jsonObject.toString();*/
        return jsonDefaultMenu;
    }
    @RequestMapping(value = "deleteMenu",method = RequestMethod.GET)
    public String deleteMenu(String token){
        return HttpUtil.httpPostRequest(null, DELETE_MENU +TOKEN);
    }
    /*@RequestMapping(value = "addConditionnalMenu",method = RequestMethod.POST)
    public String addConditionnalMenu(String token){
        return HttpUtil.httpPostRequest(null, ADDCONDITIONAL_MENU +TOKEN);
    }*/
    @RequestMapping(value = "get_current_selfmenu_info",method = RequestMethod.GET)
    public String get_current_selfmenu_info(String token){
        return HttpUtil.httpPostRequest(null, GET_CURRENT_SELFMENU_INFO +TOKEN);
    }


    public static void main(String[] args) {
        Map<String,Object>  mp = new HashMap<>();
        mp.put("a","b");
        String c = (String) mp.get("no");
        System.out.println("c = " + c);
    }





}
