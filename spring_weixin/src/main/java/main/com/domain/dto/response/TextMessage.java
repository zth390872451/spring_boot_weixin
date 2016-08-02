package main.com.domain.dto.response;

/**
 * Created by Administrator on 2016/7/30 0030.
 * 响应 文本消息
 */
public class TextMessage extends BaseMessage{
    //回复的消息内容
    private String Content;

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }
}
