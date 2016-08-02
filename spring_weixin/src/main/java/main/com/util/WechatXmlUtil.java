package main.com.util;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;
import main.com.domain.dto.response.Article;
import main.com.domain.dto.response.MusicMessage;
import main.com.domain.dto.response.NewsMessage;
import main.com.domain.dto.response.TextMessage;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/7/30 0030.
 * 微信 消息xml解析
 */
public class WechatXmlUtil {
    /**
     * 返回消息类型：文本
     */
    public static final String RESP_MESSAGE_TYPE_TEXT = "text";

    /**
     * 返回消息类型：音乐
     */
    public static final String RESP_MESSAGE_TYPE_MUSIC = "music";

    /**
     * 返回消息类型：图文
     */
    public static final String RESP_MESSAGE_TYPE_NEWS = "news";

    /**
     * 请求消息类型：文本
     */
    public static final String REQ_MESSAGE_TYPE_TEXT = "text";

    /**
     * 请求消息类型：图片
     */
    public static final String REQ_MESSAGE_TYPE_IMAGE = "image";

    /**
     * 请求消息类型：链接
     */
    public static final String REQ_MESSAGE_TYPE_LINK = "link";

    /**
     * 请求消息类型：地理位置
     */
    public static final String REQ_MESSAGE_TYPE_LOCATION = "location";

    /**
     * 请求消息类型：音频
     */
    public static final String REQ_MESSAGE_TYPE_VOICE = "voice";
    /**
     * 请求消息类型：推送
     */
    public static final String REQ_MESSAGE_TYPE_EVENT = "event";

    /**
     * 事件类型：subscribe(订阅)
     */
    public static final String EVENT_TYPE_SUBSCRIBE = "subscribe";

    /**
     * 事件类型：unsubscribe(取消订阅)
     */
    public static final String EVENT_TYPE_UNSUBSCRIBE = "unsubscribe";

    /**
     * 事件类型：CLICK(自定义菜单点击事件)
     */
    public static final String EVENT_TYPE_CLICK = "CLICK";


    /*====================将xml转换成 Map<String,String>====================*/
    /**
     * 解析微信发来的请求 (xml格式的数据流)
     * @param request
     * @return
     * @throws IOException
     * @throws DocumentException
     */
    public static Map<String,String> parseXml(HttpServletRequest request) throws IOException, DocumentException {
        //将存储解析结果
        Map<String,String> result = new HashMap<String, String>();
        //从request获取输入流
        InputStream inputStream = request.getInputStream();
        //读取输入流
        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read(inputStream);
        //得到xml根元素
        Element rootElement = document.getRootElement();
        List<Element> elementList = rootElement.elements();
        //遍历所有子节点，存入map
        for (Element e: elementList) {
            result.put(e.getName(),e.getText());
        }
        //释放资源
        inputStream.close();
        inputStream = null;
        return result;
    }

    /*====================将响应对象 转换成xml====================*/

    /**
     * 文本消息转换成xml
     * @param textMessage
     * @return
     */
    public static String textMessageToXml(TextMessage textMessage){
        xstream.alias("xml",textMessage.getClass());
        return xstream.toXML(textMessage);
    }
    /**
     * 音乐消息对象转换成xml
     *
     * @param musicMessage 音乐消息对象
     * @return xml
     */
    public static String musicMessageToXml(MusicMessage musicMessage) {
        xstream.alias("xml", musicMessage.getClass());
        return xstream.toXML(musicMessage);
    }

    /**
     * 图文消息对象转换成xml
     *
     * @param newsMessage 图文消息对象
     * @return xml
     */
    public static String newsMessageToXml(NewsMessage newsMessage) {
        xstream.alias("xml", newsMessage.getClass());
        xstream.alias("item", new Article().getClass());
        return xstream.toXML(newsMessage);
    }



    /**
     * 这个类必须引入 xpp3 和 xstream 的包才可以使用
     * 扩展xstream，使其支持CDATA块
     *
     * @date 2013-05-19
     */
    private static XStream xstream = new XStream(new XppDriver() {
        public HierarchicalStreamWriter createWriter(Writer out) {
            return new PrettyPrintWriter(out) {
                // 对所有xml节点的转换都增加CDATA标记
                boolean cdata = true;
                @SuppressWarnings("unchecked")
                public void startNode(String name, Class clazz) {
                    super.startNode(name, clazz);
                }
                protected void writeText(QuickWriter writer, String text) {
                    if (cdata) {
                        writer.write("<![CDATA[");
                        writer.write(text);
                        writer.write("]]>");
                    } else {
                        writer.write(text);
                    }
                }
            };
        }
    });


}
