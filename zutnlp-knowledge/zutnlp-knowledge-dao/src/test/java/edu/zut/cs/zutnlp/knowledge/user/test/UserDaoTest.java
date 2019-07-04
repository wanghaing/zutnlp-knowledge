//package edu.zut.cs.zutnlp.knowledge.user.test;
//
//
//import edu.zut.cs.zutnlp.knowledge.base.config.JpaConfiguration;
//import edu.zut.cs.zutnlp.knowledge.dao.UserDao;
//import edu.zut.cs.zutnlp.knowledge.domain.User;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = {JpaConfiguration.class})
//public class  UserDaoTest {
//
//    @Autowired
//    UserDao userDao;
//    Long id;
//    private String username;
//    private String password;
//
//    @Test
//    public void add() {
//
//
//        User user = new User(id, username, password);
//        user.setPassword("p1");
//        this.userDao.save(user);
//    }
//
//}
