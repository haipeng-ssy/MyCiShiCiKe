package com.haipeng.cishicike;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.haipeng.adapter.FriendsAdapter;
import com.haipeng.entity.Friend;

import java.util.ArrayList;
import java.util.List;


public class FriendsActivity extends BaseActivity {

    ListView listView;
    FriendsAdapter friendsAdapter;
    List<Friend> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Init(this,R.layout.activity_friends);

    }

    @Override
    public void initView() {
        listView = (ListView) findViewById(R.id.activity_friend_lv);
        list = new ArrayList<Friend>();
    }

    @Override
    public void setUpView() {
        Friend friend = new Friend();
        friend.setFriendAvator("");
        friend.setFriendName("xiaowang");
        list.add(friend);
        friendsAdapter = new FriendsAdapter(this,list);
        listView.setAdapter(friendsAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundle = new Bundle();
                String friendName=friendsAdapter.getListFriends().get(position).getFriendName();
                bundle.putString("friendName",friendName);
                mStartActivity(FriendsActivity.this,new LoginActivity(),bundle);
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_friends, menu);
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
