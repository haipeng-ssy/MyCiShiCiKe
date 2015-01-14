package com.haipeng.cishicike;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.haipeng.adapter.ChatAdapter;
import com.haipeng.entity.MyChat;
import com.haipeng.util.myInterface.GetMsg;

import org.jivesoftware.smack.Chat;

import java.util.ArrayList;
import java.util.List;


public class ChatActivity extends ActionBarActivity {

    Bundle bundle;
    Intent intent;
    String friendName;
    String myName;
    MyXMPPConnection myXMPPConnection;
    TextView textView;
    Button   send_btn;
    ListView mListView;
    ChatAdapter myChatAdapter;
    List<MyChat> myChats;
    EditText myMsgET;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        bundle = new Bundle();
        intent = getIntent();
        bundle = intent.getBundleExtra("bundle");
        friendName = bundle.getString("friendName");
        myName     = bundle.getString("myName");
        textView = (TextView) findViewById(R.id.chat_account);
        send_btn = (Button) findViewById(R.id.chat_send);
        textView.setText(friendName);

        myMsgET = (EditText) findViewById(R.id.chat_content);
        myChats = new ArrayList<MyChat>();

        mListView = (ListView) findViewById(R.id.chat_ll);
        myChatAdapter = new ChatAdapter(ChatActivity.this,myChats);
        mListView.setAdapter(myChatAdapter);

        myXMPPConnection = MyXMPPConnection.getInstance();
        myXMPPConnection.createChat(myXMPPConnection.getXMPPTCPConnection(),friendName);
        myXMPPConnection.getMsg(myXMPPConnection.getChat(),myXMPPConnection.getChatManager(),new GetMsg() {
            @Override
            public void getMsg(String msg) {
                MyChat myChat= new MyChat();
                myChat.setAccount(friendName);
                myChat.setContent(msg);
                myChats.add(myChat);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        myChatAdapter.notifyDataSetChanged();
                    }
                });

            }
        });
        send_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myXMPPConnection.sendMsg(myXMPPConnection.getChat(),myXMPPConnection.getChatManager(),myMsgET.getText().toString());
                MyChat myChat= new MyChat();
                myChat.setAccount(myName);
                myChat.setContent(myMsgET.getText().toString());
                myChats.add(myChat);
                myChatAdapter.notifyDataSetChanged();
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_chat, menu);
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
