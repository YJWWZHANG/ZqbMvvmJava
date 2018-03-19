package com.zqb.zqbmvvmjava;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

/**
 * Created by XXX on 2018/3/19.
 */

public class UserRepository {

    private static final UserRepository instance = new UserRepository();

    private UserRepository() {
    }

    public static UserRepository getInstance() {
        return instance;
    }

    public LiveData<Lcee<User>> getUser(String userName) {
//        final MutableLiveData<User> user = new MutableLiveData<>();
        final MutableLiveData<Lcee<User>> user = new MutableLiveData<>();
        user.setValue(Lcee.<User>loading());
        OkGo.<String>get("https://api.github.com/users/" + userName)
                .tag(this)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        User userBean = new Gson().fromJson(response.body(), User.class);
                        if (null == userBean) {
                            user.setValue(Lcee.<User>empty());
                            return;
                        }
                        user.setValue(Lcee.content(userBean));
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                    }
                });
        return user;
    }

}
