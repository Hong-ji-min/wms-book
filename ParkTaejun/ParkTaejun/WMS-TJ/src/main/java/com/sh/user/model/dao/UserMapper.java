package com.sh.user.model.dao;

import com.sh.user.model.entity.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    User login (@Param("userId") int userId, @Param("password") String password);
}
