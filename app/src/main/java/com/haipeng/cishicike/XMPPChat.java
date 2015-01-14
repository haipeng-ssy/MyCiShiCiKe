package com.haipeng.cishicike;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class XMPPChat extends BaseActivity {

    Button btn_login,btn_send;
    private MyXMPPConnection myXMPPConnection;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Init(this, R.layout.activity_xmppchat);

    }

    @Override
    public void initView() {
        btn_login = (Button) findViewById(R.id.btn_login);
        btn_send  = (Button) findViewById(R.id.btn_send);
    }

    @Override
    public void setUpView() {

//        btn_login.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                myXMPPConnection = MyXMPPConnection.getInstance();
//                myXMPPConnection.mConnection();
//            }
//        });
//        btn_send.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                myXMPPConnection.sendMsg(myXMPPConnection.getXMPPTCPConnection());
//            }
//        });
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                myXMPPConnection = new MyXMPPConnection();
//                boolean result =myXMPPConnection.mConnection();
//                String str = String.valueOf(result);
//                Toast.makeText(XMPPChat.this,str+"",Toast.LENGTH_LONG).show();
//            }
//        });
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
}
