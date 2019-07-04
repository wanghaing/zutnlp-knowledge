package edu.zut.cs.zutnlp.knowledge.dao;

import edu.zut.cs.zutnlp.knowledge.base.dao.GenericDao;
import edu.zut.cs.zutnlp.knowledge.domain.User;

public interface UserDao extends GenericDao<User,Long> {
    User findByUsername(String Username);
}
