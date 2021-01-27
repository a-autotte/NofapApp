package com.example.nofapmainactivity.impl;

import android.content.Context;

import com.example.nofapmainactivity.MainPageActivity;
import com.example.nofapmainactivity.dao.DatabaseDB;
import com.example.nofapmainactivity.exceptions.ExistingUserException;
import com.example.nofapmainactivity.exceptions.PasswordNotEqualException;
import com.example.nofapmainactivity.interfaces.Service;
import com.example.nofapmainactivity.modals.UserModal;

public class AppService implements Service {

    private DatabaseDB BaseDeDonneeActuelle;

    public AppService(DatabaseDB baseDeDonneeAConnecter) {
        BaseDeDonneeActuelle = baseDeDonneeAConnecter;
    }


    public void AddUser(UserModal user) throws PasswordNotEqualException, ExistingUserException {

        if (user.userId != null) { }

        if (user.userName.isEmpty()) {  }

        if (user.userPassword != user.userConfirmPassword) { throw new PasswordNotEqualException();
        }


        for (UserModal users : BaseDeDonneeActuelle.dao().AllUsers())
        {
            if (users.userEmail.equalsIgnoreCase(user.userEmail))
            {
                throw new ExistingUserException();
            }
        }

        BaseDeDonneeActuelle.dao().InsertUser(user);

    }
}
