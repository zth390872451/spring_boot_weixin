package main.com.domain.dto.request;

/**
 * Created by Administrator on 2016/7/30 0030.
 * 请求  图片消息
 */
public class ImageMessage extends BaseMessage{
    private String PicUrl;

    public String getPicUrl() {
        return PicUrl;
    }

    public void setPicUrl(String picUrl) {
        PicUrl = picUrl;
    }
}
