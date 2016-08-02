package main.com.service;

import main.com.domain.dto.response.TextMessage;
import main.com.util.WechatXmlUtil;
import org.dom4j.DocumentException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

/**
 * Created by Administrator on 2016/7/30 0030.
 */
@Service
public class CoreService {

    public  String processRequest(HttpServletRequest request){
        String respMessage = null;
        try{
            String respContent = "请求处理异常，请稍候尝试!";
            Map<String,String> requestMap = WechatXmlUtil.parseXml(request);
            //发送方账号(open_id)
            String fromUserName = requestMap.get("FromUserName");
            //公众账号(微信号)
            String toUserName = requestMap.get("ToUserName");
            String msgType = requestMap.get("MsgType");
            TextMessage textMessage = new TextMessage();
            textMessage.setFromUserName(toUserName);
            textMessage.setToUserName(fromUserName);
            textMessage.setCreateTime(new Date().getTime());
            textMessage.setFuncFlag(0);
            // 文本消息
            if (msgType.equals(WechatXmlUtil.REQ_MESSAGE_TYPE_TEXT)) {
                respContent = "您发送的是文本消息！";
            }
            // 图片消息
            else if (msgType.equals(WechatXmlUtil.REQ_MESSAGE_TYPE_IMAGE)) {
                respContent = "您发送的是图片消息！";
            }
            // 地理位置消息
            else if (msgType.equals(WechatXmlUtil.REQ_MESSAGE_TYPE_LOCATION)) {
                respContent = "您发送的是地理位置消息！";
            }
            // 链接消息
            else if (msgType.equals(WechatXmlUtil.REQ_MESSAGE_TYPE_LINK)) {
                respContent = "您发送的是链接消息！";
            }
            // 音频消息
            else if (msgType.equals(WechatXmlUtil.REQ_MESSAGE_TYPE_VOICE)) {
                respContent = "您发送的是音频消息！";
            }
            // 事件推送
            else if (msgType.equals(WechatXmlUtil.REQ_MESSAGE_TYPE_EVENT)) {
                // 事件类型
                String eventType = requestMap.get("Event");
                // 订阅
                if (eventType.equals(WechatXmlUtil.EVENT_TYPE_SUBSCRIBE)) {
                    respContent = "谢谢您的关注！";
                }
                // 取消订阅
                else if (eventType.equals(WechatXmlUtil.EVENT_TYPE_UNSUBSCRIBE)) {
                    // TODO 取消订阅后用户再收不到公众号发送的消息，因此不需要回复消息
                }
                // 自定义菜单点击事件
                else if (eventType.equals(WechatXmlUtil.EVENT_TYPE_CLICK)) {
                    // TODO 自定义菜单权没有开放，暂不处理该类消息
                }
            }
            textMessage.setContent(respContent);

            respMessage = WechatXmlUtil.textMessageToXml(textMessage);

        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return respMessage;
    }
}
