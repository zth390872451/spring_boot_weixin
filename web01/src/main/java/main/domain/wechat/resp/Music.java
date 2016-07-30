package main.domain.wechat.resp;

/**
 * Created by Administrator on 2016/7/30 0030.
 * 响应 音乐model
 */
public class Music {
    //标题 名称
    private String Title;
    //描述
    private String Description;
    //链接
    private String MusicUrl;
    //高质量音乐链接，WIFI环境下优先使用该链接播放音乐
    private String HQMusicUrl;

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

    public String getMusicUrl() {
        return MusicUrl;
    }

    public void setMusicUrl(String musicUrl) {
        MusicUrl = musicUrl;
    }

    public String getHQMusicUrl() {
        return HQMusicUrl;
    }

    public void setHQMusicUrl(String HQMusicUrl) {
        this.HQMusicUrl = HQMusicUrl;
    }
}
