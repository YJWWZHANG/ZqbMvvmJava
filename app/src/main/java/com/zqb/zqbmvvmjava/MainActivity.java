package com.zqb.zqbmvvmjava;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tv_id)
    TextView mTvId;
    @BindView(R.id.tv_name)
    TextView mTvName;

    private UserViewModel mUserViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mUserViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        mUserViewModel.getUser().observe(this, new Observer<User>() {
            @Override
            public void onChanged(@Nullable User user) {
                mTvId.setText(user.getId() + "");
                mTvName.setText(user.getName());
            }
        });
        mUserViewModel.setUserName("yjwwzhang");
    }
}
