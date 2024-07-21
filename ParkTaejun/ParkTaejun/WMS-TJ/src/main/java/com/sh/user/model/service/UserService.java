package com.sh.user.model.service;

import com.sh.user.model.dao.UserMapper;
import com.sh.user.model.entity.User;
import org.apache.ibatis.session.SqlSession;

import static com.sh.common.MyBatisTemplate.getSqlSession;

public class UserService {
    public User login(int userId, String password) {
        SqlSession sqlSession = getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        User user = null;
        try {
            user = userMapper.login(userId,password);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            sqlSession.close();
        }
        return  user;
    }
}
