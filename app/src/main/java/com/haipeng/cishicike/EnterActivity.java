package com.haipeng.cishicike;

import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

public class EnterActivity extends ActionBarActivity implements OnTouchListener,AnimationListener {

    LinearLayout ll_left, ll_right;
    boolean left_all = false;
    boolean right_all = false;
    float init_floatX=0f;
    float init_floatY=0f;
    ActionBar actionBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter);
        actionBar = getSupportActionBar();
        actionBar.hide();
        ll_left = (LinearLayout) findViewById(R.id.left_linearLayout);
        ll_right = (LinearLayout) findViewById(R.id.right_linearLayout);

        ll_left.setOnTouchListener(this);
        ll_right.setOnTouchListener(this);

        if (savedInstanceState == null) {
            // getSupportFragmentManager().beginTransaction()
            // .add(R.id.container, new PlaceholderFragment()).commit();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.left_linearLayout, new InterWeather()).commit();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.right_linearLayout, new InterHealth()).commit();
        }
        int blue = Color.rgb(0, 0, 255);
        int green= Color.rgb(0, 255, 0);

        ll_left.setBackgroundColor(blue);
        ll_right.setBackgroundColor(green);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.enter, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public static class InterWeather extends Fragment {
        public InterWeather() {

        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // TODO Auto-generated method stub
            View rootView = inflater.inflate(R.layout.fragment_enter_left,
                    container, false);
            return rootView;
        }

    }

    public static class InterHealth extends Fragment {
        public InterHealth() {

        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // TODO Auto-generated method stub
            View rootView = inflater.inflate(R.layout.fragment_enter_right,
                    container, false);
            return rootView;
        }

    }

    public int getScreenWidth(){
        DisplayMetrics dm = new DisplayMetrics();
        dm = getResources().getDisplayMetrics();
        int widthPixels = dm.widthPixels;
        return widthPixels;
    }
    boolean isRightAll = false;
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        // TODO Auto-generated method stub

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                init_floatX = event.getX();
                init_floatY = event.getY();
                break;
            case MotionEvent.ACTION_UP:
                System.out.println(event.getX());
                float move_floatX = event.getX();
                float move_floatY = event.getY();
                if (init_floatX - move_floatX < -50) {
                    if (left_all == false && right_all == false) {
                        left_all = true;
                        left_All();
                    }

                    if (right_all == true) {
                        left_all= false;
                        right_all = false;
                        left_right_half();
                        ll_left.setAnimation(AnimationUtils.loadAnimation(this, R.anim.zero_left_half));
//                    ll_right.setAnimation(AnimationUtils.loadAnimation(this, R.anim.all_right_half));

//                    notify();
                    }
                } else if (init_floatX - move_floatX   > 50) {
                    if (left_all == false && right_all == false) {
                        right_all = true;
                        right_all();
                        isRightAll = true;

                    }

                    if (left_all == true) {
                        left_all = false;
                        right_all = false;
                        left_right_half();
//					ll_left.setAnimation(AnimationUtils.loadAnimation(this, R.anim.all_left_half));
                        ll_right.setAnimation(AnimationUtils.loadAnimation(this, R.anim.zero_right_half));

                    }

                    if (right_all == true) {
                        if (isRightAll == true) {
                            isRightAll = false;
                        }else{
                            Intent intent = new Intent(this,MainActivity.class);
                            startActivity(intent);
                        }
                    }

                }

                break;

            default:
                break;
        }
        return true;
    }

    public enum WhichExcute{
        LEFT_ALL,
        RIGHT_ALL,
        LEF_RIGHT_HALF
    }
    public void DelayedExcute(WhichExcute excute){
        final WhichExcute whichExcute = excute;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                switch (whichExcute) {
                    case LEFT_ALL:
                        set_right_little();
                        set_Left_all();
                        break;
                    case RIGHT_ALL:
                        set_Left_little();
                        set_right_all();
                        break;
                    case LEF_RIGHT_HALF:
                        set_Left_middle();
                        set_right_middle();
                        break;
                    default:
                        break;
                }
            }
        }, 100);
    }

    public void left_All() {
//		AnimationSet as = new AnimationSet(true);
//		TranslateAnimation ta = new TranslateAnimation(0, 50, 0, 0);

        DelayedExcute(WhichExcute.LEFT_ALL);
        ll_left.setAnimation(AnimationUtils.loadAnimation(EnterActivity.this, R.anim.half_left_all));
        // TODO Auto-generated method stub
//				ll_right.setAnimation(AnimationUtils.loadAnimation(InterActivity.this, R.anim.half_right_zero));


    }

    public void right_all() {
        DelayedExcute(WhichExcute.RIGHT_ALL);

        ll_right.setAnimation(AnimationUtils.loadAnimation(this, R.anim.half_right_all));
//        ll_left.setAnimation(AnimationUtils.loadAnimation(this, R.anim.half_left_zero));
    }

    public void left_right_half() {
        DelayedExcute(WhichExcute.LEF_RIGHT_HALF);

    }

    public void set_Left_little() {
        LinearLayout.LayoutParams little_left = (LayoutParams) ll_left
                .getLayoutParams();

        little_left.weight = 0;
        ll_left.setLayoutParams(little_left);

    }

    public void set_Left_middle() {
        LinearLayout.LayoutParams middle_left = (LayoutParams) ll_left
                .getLayoutParams();
        middle_left.weight = 1;
        ll_left.setLayoutParams(middle_left);
    }

    public void set_Left_all() {
        LinearLayout.LayoutParams all_left = (LayoutParams) ll_left
                .getLayoutParams();
        all_left.weight = 2;
        ll_left.setLayoutParams(all_left);

    }

    public void set_right_little() {
        LinearLayout.LayoutParams little_right = (LayoutParams) ll_right
                .getLayoutParams();
        little_right.weight = 0;
        ll_right.setLayoutParams(little_right);

    }

    public void set_right_middle() {
        LinearLayout.LayoutParams middle_right = (LayoutParams) ll_right
                .getLayoutParams();
        middle_right.weight = 1;
        ll_right.setLayoutParams(middle_right);

    }

    public void set_right_all() {
        LinearLayout.LayoutParams all_right = (LayoutParams) ll_right
                .getLayoutParams();
        all_right.weight = 2;
        ll_right.setLayoutParams(all_right);

    }

    @Override
    public void onAnimationStart(Animation animation) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onAnimationEnd(Animation animation) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onAnimationRepeat(Animation animation) {
        // TODO Auto-generated method stub

    }

}
