package com.example.nofapmainactivity.modals;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class UserModal implements Serializable {
    @PrimaryKey(autoGenerate = true)
    public Long userId;

    @ColumnInfo(name = "user_name")
    public String userName;

    @ColumnInfo(name = "user_password")
    public String userPassword;

    @ColumnInfo(name = "user_email")
    public String userEmail;

    @ColumnInfo(name = "user_confirmPassword")
    public String userConfirmPassword;

    public UserModal()
    {
        userName = "";
        userId = null;
        userPassword = "";
        userEmail = "";
        userConfirmPassword = "";

    }

    public UserModal(Long id, String name, String password, String email, String confirmPassword)
    {
        userId = id;
        userName = name;
        userPassword = password;
        userEmail = email;
        userConfirmPassword = confirmPassword;
    }


}
