package edu.zut.cs.zutnlp.knowledge.manager;


import edu.zut.cs.zutnlp.knowledge.base.service.GenericManager;
import edu.zut.cs.zutnlp.knowledge.domain.User;

public interface UserManager extends GenericManager<User,Long> {
    User findByUsername(String username);

}
