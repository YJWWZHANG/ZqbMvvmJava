package com.zqb.zqbmvvmjava;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

/**
 * Created by XXX on 2018/3/8.
 */

public class UserViewModel extends ViewModel {
    private MutableLiveData<User> user;

    public MutableLiveData<User> getUser() {
        if (user == null) {
            user = new MutableLiveData<>();
        }
        return user;
    }

    public void setUserName(String userName) {
        user.setValue(new User(1, userName));
    }
}
