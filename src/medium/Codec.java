package medium;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

/**
 * @author daiqing.ydq
 * @version 1.0.0
 * @ClassName Codec.java
 * @Description TODO
 * @createTime 2018年11月12日 10:36:00
 */
public class Codec {
    public final String dict = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public Map<String, String> long2short = new HashMap<String, String>();
    public Map<String, String> short2long = new HashMap<String, String>();

    public String encode(String longUrl) {
        if (long2short.containsKey(longUrl)) {
            return long2short.get(longUrl);
        }

        StringBuilder sb = new StringBuilder("tinyurl.com/");
        for (int i = 0; i < 6; i++) {
            sb.append(dict.charAt((int)(Math.random() * 62)));
        }
        String shortUrl = sb.toString();
        long2short.put(longUrl, shortUrl);
        short2long.put(shortUrl, longUrl);

        return shortUrl;
    }

    public String decode(String shortUrl) {
        return short2long.get(shortUrl);
    }
}
