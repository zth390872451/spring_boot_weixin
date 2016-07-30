package main.domain.wechat.req;

/**
 * Created by Administrator on 2016/7/30 0030.
 * 请求 音频消息
 */
public class VoiceMessage extends BaseMessage {
    //媒体ID
    private String MediaId;
    //语音格式
    private String Format;

    public String getMediaId() {
        return MediaId;
    }

    public void setMediaId(String mediaId) {
        MediaId = mediaId;
    }

    public String getFormat() {
        return Format;
    }

    public void setFormat(String format) {
        Format = format;
    }
}
