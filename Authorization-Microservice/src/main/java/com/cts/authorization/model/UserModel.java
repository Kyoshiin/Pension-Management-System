package com.cts.authorization.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "users")
public class UserModel {

    @Id
    @GeneratedValue
    private int id;
    private String userName;
    private String password;

    public UserModel(int id, String userName, String password) {
        this.id = id;
        this.userName = userName;
        this.password = password;
    }

    public UserModel() {

    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }
}
