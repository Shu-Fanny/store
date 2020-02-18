package com.pyp.cast.store.dao;

import com.pyp.cast.store.domain.PO.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

public interface IUserDao {
    /**
     * 根据用户名，查询该用户的详细信息
     * @param username
     * @return
     * @throws Exception
     */
    @Select("select * from user where username=#{username}")
    User findUserByUserName(String username)throws Exception;

    /**
     * 添加新用户
     * @param user
     * @throws Exception
     */
    @Insert("insert user values (#{uid},#{username},#{password},#{name},#{sex},#{email},#{telephone})")
    void addUser(User user)throws Exception;
}
