package com.example.nofapmainactivity.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.nofapmainactivity.modals.UserModal;

import java.util.List;

@Dao
public interface DatabaseDAO {
    @Insert
    public abstract Long InsertUser(UserModal user);

    @Delete
    void DeleteUser(UserModal user);

    @Query("SELECT * FROM UserModal")
    List<UserModal> AllUsers();
}
