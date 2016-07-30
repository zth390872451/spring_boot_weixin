package main.domain.wechat.resp;

/**
 * Created by Administrator on 2016/7/30 0030.
 * 响应 图文model
 */
public class Article {
    //图文消息名称
    private String Title;
    //图文消息描述
    private String Description;
    //图片链接  支持JPG、PNG格式，较好的效果为大图640*320，小图80*80，限制图片链接的域名需要与开发者填写的基本资料中的Url一致
    private String PicUrl;

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getPicUrl() {
        return PicUrl;
    }

    public void setPicUrl(String picUrl) {
        PicUrl = picUrl;
    }
}
