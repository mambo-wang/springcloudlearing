package com.h3c.common.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by dell on 2017/7/2.
 */
public class User implements Serializable{

    private static final long serialVersionUID = 2696455551572668372L;
    public static final Integer ROLE_TYPE_ADMIN = 1;
    public static final Integer ROLE_TYPE_COMMON = 0;

    public static final Integer STATE_NORMAL = 1;
    public static final Integer STATE_LOCKED = 0;

    private Long id;

    private String loginName;

    private String username;

    private String password;

    private Integer comeYear;

    private String salt;

    private Integer state;

    List<Role> roleList;

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

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public Integer getComeYear() {
        return comeYear;
    }

    public void setComeYear(Integer comeYear) {
        this.comeYear = comeYear;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    public String getCredentialsSalt() {
        return username + salt;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", loginName='" + loginName + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", comeYear=" + comeYear +
                ", salt='" + salt + '\'' +
                ", state=" + state +
                ", roleList=" + roleList +
                '}';
    }
}
