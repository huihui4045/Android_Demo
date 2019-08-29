package com.alizhezi.demo.navigation;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.alizhezi.demo.R;


import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;


public class NavigationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
    }


    @Override
    public boolean onSupportNavigateUp() {

        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.my_nav_host_fragment);
       // return NavHostFragment.findNavController(fragment).navigateUp();

        return Navigation.findNavController(this,R.id.my_nav_host_fragment).navigateUp();

    }

}
