package com.leanstartup.bean;

import java.io.Serializable;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "users")
public class User implements Serializable {
    @DatabaseField(id = true)
    private String username;
    @DatabaseField
    private String password;

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // public int getId() {
    // return id;
    // }
    //
    // public void setId(int id) {
    // this.id = id;
    // }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    @Override
    public String toString() {
        // return getId() + " | " + getUsername() + " | " + getPassword();
        return getUsername() + " | " + getPassword();

    }
}
