package edu.zut.cs.zutnlp.knowledge.domain;

import edu.zut.cs.zutnlp.knowledge.base.domain.BaseEntity;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author Wangxiaoer
 * @// TODO: 2018/11/3
 * @param
 *
 */

@Table(name="User")
@Entity
public class User extends BaseEntity {
    private Long id;
    private String username;
    private String password;

    public User(int id, String username, String password) {
        super();
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
