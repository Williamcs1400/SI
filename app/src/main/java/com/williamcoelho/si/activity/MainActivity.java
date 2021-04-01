package com.williamcoelho.si.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;
import com.williamcoelho.si.R;
import com.williamcoelho.si.fragment.AjudaFragment;
import com.williamcoelho.si.fragment.CalendarFragment;
import com.williamcoelho.si.fragment.ConfigFragment;
import com.williamcoelho.si.fragment.InicialFragment;

public class MainActivity extends AppCompatActivity {

    private ChipNavigationBar chipNavigationBar;
    private Fragment fragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chipNavigationBar = findViewById(R.id.chipNav);

        chipNavigationBar.setItemSelected(R.id.inicial, true);
        getSupportFragmentManager().beginTransaction().replace(R.id.viewPager, new InicialFragment()).commit();

        chipNavigationBar.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int i) {
                switch (i){
                    case R.id.inicial:
                        fragment = new InicialFragment();
                        break;
                    case R.id.calendar:
                        fragment = new CalendarFragment();
                        break;
                    case R.id.favorites:
                        fragment = new ConfigFragment();
                        break;
                    case R.id.settings:
                        fragment = new AjudaFragment();
                        break;
                }

                if(fragment != null){
                    getSupportFragmentManager().beginTransaction().replace(R.id.viewPager, fragment).commit();
                }

            }
        });

    }
}