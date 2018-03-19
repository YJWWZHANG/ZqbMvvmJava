package com.zqb.zqbmvvmjava;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.persistence.room.util.StringUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tv_id)
    TextView mTvId;
    @BindView(R.id.tv_name)
    TextView mTvName;
    @BindView(R.id.et_name)
    EditText mEtName;

    private UserViewModel mUserViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mUserViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        mUserViewModel.getUser().observe(this, new Observer<Lcee<User>>() {
            @Override
            public void onChanged(@Nullable Lcee<User> user) {
                updateView(user);
            }
        });
    }

    private void updateView(Lcee<User> user) {
        switch (user.status) {
            case Content:
                mTvId.setText(user.data.getId() + "");
                mTvName.setText(user.data.getName());
                break;
            case Loading:
                Toast.makeText(this, "Loading", Toast.LENGTH_SHORT).show();
                break;
            case Empty:
                Toast.makeText(this, "Empty", Toast.LENGTH_SHORT).show();
                break;
            case Error:
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @OnClick({R.id.btn_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_search:
                String userName = mEtName.getText().toString();
                mUserViewModel.reload(userName);
                break;
        }
    }
}
