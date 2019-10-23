package com.soul.pojo;



import javax.persistence.*;

/**
 * Created by Administrator on 2019/3/26.
 */
@Entity
@Table(name="User")
public class User {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

   @Column
   private String loginName;

   @Column
   private String username;

   @Column
   private String password;

    public User() {
    }

    public User(String loginName, String username, String password) {
        this.loginName = loginName;
        this.username = username;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
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
                "loginName='" + loginName + '\'' +
                ", userName='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
