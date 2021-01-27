package com.example.nofapmainactivity.dao;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.nofapmainactivity.modals.UserModal;

@Database(entities = UserModal.class, version = 2)
public abstract class DatabaseDB extends RoomDatabase {
    public abstract DatabaseDAO dao();
}
