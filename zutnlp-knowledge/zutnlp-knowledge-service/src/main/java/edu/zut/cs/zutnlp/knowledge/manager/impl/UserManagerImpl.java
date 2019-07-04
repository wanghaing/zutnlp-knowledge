package edu.zut.cs.zutnlp.knowledge.manager.impl;


import edu.zut.cs.zutnlp.knowledge.base.service.impl.GenericManagerImpl;
import edu.zut.cs.zutnlp.knowledge.dao.UserDao;
import edu.zut.cs.zutnlp.knowledge.domain.User;
import edu.zut.cs.zutnlp.knowledge.manager.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Wangxiaoer
 * 
 */


@Component
public class UserManagerImpl extends GenericManagerImpl<User,Long> implements UserManager {

    UserDao userDao;

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
        this.dao=this.userDao;
    }

    @Override
    public User findByUsername(String username) {

        return userDao.findByUsername(username);
    }
}
