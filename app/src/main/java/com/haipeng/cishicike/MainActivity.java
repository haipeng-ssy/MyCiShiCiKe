package com.haipeng.cishicike;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

/**
 * Created by Administrator on 2015/1/11.
 */
public class MainActivity extends BaseActivity {
    Button myFriendBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Init(this,R.layout.activity_main);

    }

    @Override
    public void initView() {
        myFriendBtn = (Button) findViewById(R.id.main_btn_friend);
    }

    @Override
    public void setUpView() {
       myFriendBtn.setOnClickListener(this);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onPostCreate(savedInstanceState);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        // TODO Auto-generated method stub
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO Auto-generated method stub
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId())
        {
            case R.id.main_btn_friend:
                mStartActivity(MainActivity.this, new FriendsActivity(), null);
                break;
            default:
                break;
        }
    }
}
