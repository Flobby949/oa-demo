package top.flobby.oa.utils;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * @author : Flobby
 * @program : my-oa
 * @description :
 * @create : 2023-02-27 14:59
 **/

public class Md5Utils {

    /**
     * 对源数据生成 MD5 摘要
     *
     * @param source 源数据
     * @return string
     */
    public static String md5Digest(String source) {
        return DigestUtils.md2Hex(source);
    }

    /**
     * 对源数据进行加盐加密
     * @param source 源数据
     * @param salt 颜值
     * @return string
     */
    public static String md5Digest(String source, Integer salt) {
        char[] chars = source.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            chars[i] = (char) (chars[i] + salt);
        }
        String target = new String(chars);
        return md5Digest(target);
    }
}
