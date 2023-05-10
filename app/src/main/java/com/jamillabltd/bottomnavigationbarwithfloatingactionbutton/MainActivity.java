package com.jamillabltd.bottomnavigationbarwithfloatingactionbutton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.jamillabltd.bottomnavigationbarwithfloatingactionbutton.bottomnavfragments.HomeFragment;
import com.jamillabltd.bottomnavigationbarwithfloatingactionbutton.bottomnavfragments.MessageFragment;
import com.jamillabltd.bottomnavigationbarwithfloatingactionbutton.bottomnavfragments.ProfileFragment;
import com.jamillabltd.bottomnavigationbarwithfloatingactionbutton.bottomnavfragments.SettingFragment;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;

    @SuppressLint({"MissingInflatedId", "NonConstantResourceId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottomNavigationViewId);
        bottomNavigationView.setBackground(null);
        bottomNavigationView.getMenu().getItem(2).setEnabled(false);

        FloatingActionButton fabButton = findViewById(R.id.fab);
        fabButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Fab Click", Toast.LENGTH_SHORT).show();
            }
        });


        //default fragment set - HomeFragment
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new HomeFragment()).commit();

        //bottom navigation setOnItemSelectedListener
        bottomNavigationView.setOnItemSelectedListener(item -> {
            Fragment selectedFragment = null;
            switch (item.getItemId()) {
                case R.id.home:
                    selectedFragment = new HomeFragment();
                    break;
                case R.id.Message:
                    selectedFragment = new MessageFragment();
                    break;
                case R.id.Profile:
                    selectedFragment = new ProfileFragment();
                    break;
                case R.id.Settings:
                    selectedFragment = new SettingFragment();
                    break;
            }
            assert selectedFragment != null;
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
            return true;
        });



    }


    //backPress to
    @Override
    public void onBackPressed() {
        if (bottomNavigationView.getSelectedItemId() == R.id.home) {
            super.onBackPressed();
            finish();
        }else {
            bottomNavigationView.setSelectedItemId(R.id.home);
        }
    }
}