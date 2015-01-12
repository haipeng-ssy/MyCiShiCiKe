package com.haipeng.cishicike;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tyler on 2015/1/11.
 */
public class BaseActivity extends ActionBarActivity{

    FrameLayout frameLayout=null;
    LayoutInflater layoutInflater;
    ActionBarDrawerToggle actionBarDrawerToggle;
    ListView listView;
    DrawerAdapter drawerAdapter;
    DrawerLayout drawerLayout;
    List<String> mList = new ArrayList<String>();

    ActionBar actionBar;
    TextView mTitleView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_layout);

    }
    public void Init(ActionBarActivity actionBarActivity,int layoutId){

        //FrameLayout的视图
        frameLayout = (FrameLayout) findViewById(R.id.toggle_frameLayout);
        View view = layoutInflater.from(actionBarActivity).inflate(layoutId, null);
        frameLayout.addView(view);
        //
        drawerLayout = (DrawerLayout) findViewById(R.id.toggle_container);

       // actionBarDrawerToggle视图
        listView = (ListView) findViewById(R.id.toggle_listView);

        actionBarDrawerToggle = new ActionBarDrawerToggle(actionBarActivity, drawerLayout,
                R.drawable.ic_drawer, R.string.string_drawer_open) {

            @Override
            public void onDrawerClosed(View drawerView) {
                // TODO Auto-generated method stub
                super.onDrawerClosed(drawerView);
                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                // TODO Auto-generated method stub
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();
            }

        };
        drawerLayout.setDrawerListener(actionBarDrawerToggle);

        String[] drawers = getResources().getStringArray(
                R.array.string_array_drawer);
        for (String str : drawers) {
            mList.add(str);
        }
        drawerAdapter = new DrawerAdapter(actionBarActivity, mList);
        listView.setAdapter(drawerAdapter);
        drawerLayout.setDrawerShadow(R.drawable.drawer_shadow,
                GravityCompat.START);

        //ActionBar 视图
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        View actionBarView = layoutInflater.from(actionBarActivity).inflate(R.layout.customer_main, null);
        if (actionBarView != null) {
            mTitleView = (TextView) actionBarView.findViewById(R.id.headerTitle);

            actionBar.setDisplayShowCustomEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setLogo(getResources().getDrawable(R.drawable.ic_wfa));
            actionBar.setCustomView(actionBarView);
        }


    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        // TODO Auto-generated method stub
        super.onConfigurationChanged(newConfig);
        actionBarDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

//          return true;
        if (null == actionBarDrawerToggle) {
            return false;
        }
        return actionBarDrawerToggle.onOptionsItemSelected(item);

    }
}
