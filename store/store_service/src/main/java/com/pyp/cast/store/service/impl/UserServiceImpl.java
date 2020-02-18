package com.pyp.cast.store.service.impl;

import com.pyp.cast.store.dao.IUserDao;
import com.pyp.cast.store.domain.PO.User;
import com.pyp.cast.store.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements IUserService {
    @Autowired
    private IUserDao userDao;

    /**
     * 根据用户名，查询该用户的详细信息
     * @param username
     * @return
     * @throws Exception
     */
    @Override
    public User findUserByUserName(String username) throws Exception {
        return userDao.findUserByUserName(username);
    }

    /**
     * 添加新用户
     * @param user
     * @throws Exception
     */
    @Override
    public void addUser(User user) throws Exception {
        userDao.addUser(user);
    }
}
