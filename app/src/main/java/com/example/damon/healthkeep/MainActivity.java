package com.example.damon.healthkeep;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;


public class MainActivity extends AppCompatActivity {

    private HomeFragment homeFragment;
    private DiscoverFragment discoverFragment;
    private ProfileFragment profileFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        homeFragment=new HomeFragment();
        selectFragment(homeFragment);

        BottomNavigationView mMainNav=(BottomNavigationView)findViewById(R.id.navigation);

        mMainNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.navigation_home:
                        selectFragment(homeFragment);
                        return true;
                    case R.id.navigation_discover:
                        discoverFragment=new DiscoverFragment();
                        selectFragment(discoverFragment);
                        return true;
                    case R.id.navigation_profile:
                        profileFragment=new ProfileFragment();
                        selectFragment(profileFragment);
                        return true;
                    default:
                        return  false;
                }
            }

        });
    }

    private void selectFragment(android.support.v4.app.Fragment fragment) {
        android.support.v4.app.FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_holder,fragment);
        fragmentTransaction.commit();
    }
}
