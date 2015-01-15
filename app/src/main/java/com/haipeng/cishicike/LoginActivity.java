package com.haipeng.cishicike;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.haipeng.util.myInterface.IsLogin;

import org.jivesoftware.smack.tcp.XMPPTCPConnection;

import java.util.TooManyListenersException;


public class LoginActivity extends ActionBarActivity{

    Button btn_login;
    EditText editText_account;
    EditText editText_password;
    MyXMPPConnection myXMPPConnection;
    Bundle bundle;
    Intent intent;
    String friendName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        bundle = new Bundle();
        intent = getIntent();
        bundle = intent.getBundleExtra("bundle");
        friendName = bundle.getString("friendName");

        btn_login = (Button) findViewById(R.id.sign_in);
        editText_account = (EditText) findViewById(R.id.my_account);
        editText_password = (EditText) findViewById(R.id.my_password);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               myXMPPConnection = MyXMPPConnection.getInstance();
               myXMPPConnection.mConnection(editText_account.getText().toString(),
                       editText_password.getText().toString() ,new IsLogin() {
                           @Override
                           public void LoginSuccessed() {
                               Intent intent1 = new Intent();
                               Bundle bundle1 = new Bundle();
                               bundle1.putString("friendName",friendName);
                               bundle1.putString("myName",editText_account.getText().toString());
                               intent1.putExtra("bundle",bundle1);
                               intent1.setClass(LoginActivity.this,ChatActivity.class);
                               startActivity(intent1);
                               finish();
                           }

                           @Override
                           public void LoginFailed() {
                               Toast.makeText(LoginActivity.this,friendName+" 登录失败",Toast.LENGTH_LONG).show();
                           }
                       });

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
