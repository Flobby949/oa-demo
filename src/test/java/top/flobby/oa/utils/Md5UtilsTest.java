package top.flobby.oa.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Md5UtilsTest {

    @Test
    void md5Digest() {
        System.out.println(Md5Utils.md5Digest("test", 188));
    }
}