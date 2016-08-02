package main.com.domain.dto.response;


/**
 * Created by Administrator on 2016/7/30 0030.
 * 响应 音乐消息
 */
public class MusicMessage extends BaseMessage{
    //音乐
    private Music music;

    public Music getMusic() {
        return music;
    }

    public void setMusic(Music music) {
        this.music = music;
    }
}
