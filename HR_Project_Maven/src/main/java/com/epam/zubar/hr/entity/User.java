package com.epam.zubar.hr.entity;

/**
 * Basic entity class stores info about User.
 * @author Mikalay Zubar
 *
 */
public class User extends Entity {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private int id;
    private String login;
    private String password;
    private String role;
    private String status;

    public User(){}

    public User(int id, String login, String password, String role, String status) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.role = role;
        this.status = status;
    }


    public User(String login, String password, String role, String status) {
        this.login = login;
        this.password = password;
        this.role = role;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public String getStatus() {
        return status;
    }



    public void setId(int id) {
        this.id = id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", login=" + login + ", password=" + password + ", role=" + role + ", status="
                + status + "]";
    }

}

