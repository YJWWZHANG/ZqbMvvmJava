package com.zqb.zqbmvvmjava;

import android.arch.core.util.Function;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;

/**
 * Created by XXX on 2018/3/8.
 */

public class UserViewModel extends ViewModel {
    private UserRepository mUserRepository = UserRepository.getInstance();

    private MutableLiveData<String> mUserName;
    private LiveData<Lcee<User>> mUser;

    public LiveData<Lcee<User>> getUser() {
        if (mUser == null) {
            mUserName = new MutableLiveData<>();
            mUser = Transformations.switchMap(mUserName, new Function<String, LiveData<Lcee<User>>>() {
                @Override
                public LiveData<Lcee<User>> apply(String userName) {
                    return mUserRepository.getUser(userName);
                }
            });
        }
        return mUser;
    }

    public void reload(String username) {
        mUserName.setValue(username);
    }

}
