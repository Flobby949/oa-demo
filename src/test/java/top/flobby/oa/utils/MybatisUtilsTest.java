package top.flobby.oa.utils;

import org.junit.jupiter.api.Test;

class MybatisUtilsTest {
    @Test
    void test01() {
        String str = (String) MybatisUtils.executeQuery(sqlSession -> sqlSession.selectOne("test.sample"));
        System.out.println(str);
    }
}