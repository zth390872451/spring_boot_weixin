package main.com.domain.dto.response;

/**
 * Created by Administrator on 2016/7/30 0030.
 * 响应 消息基类 (公众账号 -> 普通用户)
 */
public class BaseMessage {
    //接收方账号（收到的OpenId）
    private String ToUserName;
    //开发者微信号
    private String FromUserName;
    //消息创建时间
    private long CreateTime;
    //消息类型 （text、music、news）
    private String MsgType;
    //位0x0001被标志时，星标刚收到的消息
    private int FuncFlag;

    public String getToUserName() {
        return ToUserName;
    }

    public void setToUserName(String toUserName) {
        ToUserName = toUserName;
    }

    public String getFromUserName() {
        return FromUserName;
    }

    public void setFromUserName(String fromUserName) {
        FromUserName = fromUserName;
    }

    public long getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(long createTime) {
        CreateTime = createTime;
    }

    public String getMsgType() {
        return MsgType;
    }

    public void setMsgType(String msgType) {
        MsgType = msgType;
    }

    public int getFuncFlag() {
        return FuncFlag;
    }

    public void setFuncFlag(int funcFlag) {
        FuncFlag = funcFlag;
    }
}
