package main.util;

import java.io.UnsupportedEncodingException;

/**
 * Created by Administrator on 2016/7/30 0030.
 */
public class EncodingUtil {
    /**
     * 计算采用utf-8编码方式时字符串所占字节数
     * 不同的编码，字符串所占 字节不同
     * @param content
     * @return
     */
    public static int getByteSize(String content) {
        int size = 0;
        if (null != content) {
            try {
                // 汉字采用utf-8编码时占3个字节
                size = content.getBytes("utf-8").length;
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return size;
    }
}
