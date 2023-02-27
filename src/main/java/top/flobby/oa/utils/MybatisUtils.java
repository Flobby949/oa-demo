package top.flobby.oa.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;
import java.util.function.Function;

/**
 * @author : Flobby
 * @program : my-oa
 * @description : mybatis工具类
 * @create : 2023-02-27 11:02
 **/

public class MybatisUtils {
    private static SqlSessionFactory sqlSessionFactory;

    static {
        Reader reader;
        try {
            reader = Resources.getResourceAsReader("mybatis-config.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 执行查询操作
     * @param function 执行查询的代码块
     * @return 查询结果
     */
    public static Object executeQuery(Function<SqlSession, Object> function) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        return function.apply(sqlSession);
    }

    /**
     * 执行增删改操作
     * @param function 执行语句代码块
     * @return 返回结果
     */
    public static Object executeUpdate(Function<SqlSession, Object> function) {
        // 需要手动提交 / 回滚事务
        SqlSession sqlSession = sqlSessionFactory.openSession(false);
        Object result;
        try {
            result = function.apply(sqlSession);
            // 手动提交事务
            sqlSession.commit();
            return result;
        } catch (Exception e) {
            // 回滚
            sqlSession.rollback();
            throw e;
        } finally {
            sqlSession.close();
        }
    }

}
