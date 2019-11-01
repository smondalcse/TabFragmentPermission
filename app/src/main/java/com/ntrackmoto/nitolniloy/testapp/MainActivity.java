package com.ntrackmoto.nitolniloy.testapp;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private TextView mTextMessage;

    private static final int CAMERA_PERMISSION_CODE = 121;
    private static final int LOCATION_PERMISSION_CODE = 100;
    private static final int CAMERA_REQUEST = 10;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Log.i(TAG, "navigation_home: ");
                    //addFragment(R.id.container, new HomeFragment(), "home");
                    replaceFragment(R.id.container, new HomeFragment(), "home", "home");
                    return true;
                case R.id.navigation_dashboard:
                    Log.i(TAG, "navigation_dashboard: ");
                    replaceFragment(R.id.container, new DashboardFragment(), "dashboard", "dashboard");
                    return true;
                case R.id.navigation_notifications:
                    Log.i(TAG, "navigation_notifications: ");
                    replaceFragment(R.id.container, new NotificationFragment(), "notification", "notification");
                    return true;
                case R.id.navigation_map:
                    //mTextMessage.setText(R.string.title_map);
                    Intent intent = new Intent(MainActivity.this, MapActivity.class);
                    startActivity(intent);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        mTextMessage = findViewById(R.id.message);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        addFragment(R.id.container, new HomeFragment(), "home");

    }

    protected void addFragment(@IdRes int containerViewId,
                               @NonNull Fragment fragment,
                               @NonNull String fragmentTag) {
        getSupportFragmentManager()
                .beginTransaction()
                .add(containerViewId, fragment, fragmentTag)
                .disallowAddToBackStack()
                .commit();
    }

    protected void replaceFragment(@IdRes int containerViewId,
                                   @NonNull Fragment fragment,
                                   @NonNull String fragmentTag,
                                   @Nullable String backStackStateName) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(containerViewId, fragment, fragmentTag)
                .addToBackStack(backStackStateName)
                .commit();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Log.i(TAG, "onRequestPermissionsResult: ");
        if (requestCode == CAMERA_PERMISSION_CODE)  {
            for (Fragment frag : getSupportFragmentManager().getFragments()) {
                if (frag != null) {
                    frag.onRequestPermissionsResult(requestCode, permissions, grantResults);
                    Log.i(TAG, "onRequestPermissionsResult: " + frag.getTag());
                }
            }
        } else if (requestCode == LOCATION_PERMISSION_CODE){
            for (Fragment frag : getSupportFragmentManager().getFragments()) {
                if (frag != null) {
                    frag.onRequestPermissionsResult(requestCode, permissions, grantResults);
                    Log.i(TAG, "onRequestPermissionsResult: " + frag.getTag());
                }
            }
        } else {
            Log.i(TAG, "onRequestPermissionsResult: Not match request code");
        }

    }

}
