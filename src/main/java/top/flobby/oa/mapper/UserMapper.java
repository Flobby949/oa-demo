package top.flobby.oa.mapper;

import top.flobby.oa.entity.User;
import top.flobby.oa.utils.MybatisUtils;

/**
 * @author : Flobby
 * @program : my-oa
 * @description :
 * @create : 2023-02-27 14:43
 **/

public class UserMapper {

    /**
     * 根据username获取用户信息
     * @param username 用户名
     * @return user
     */
    public User selectByUsername(String username) {
        return (User) MybatisUtils.executeQuery(sqlSession ->
                sqlSession.selectOne("top.flobby.oa.mapper.UserMapper.selectByUsername", username));
    }
}
