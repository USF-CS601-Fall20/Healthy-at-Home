package com.example.healthyathome;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.healthyathome.HomeFragment;
import com.google.firebase.auth.FirebaseAuth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

/** MainActivity class representing the main activity of the application after logging in. */
public class MainActivity extends AppCompatActivity {

    /**
     * Loads the home page for the user when the main activity is triggered.
     * @param savedInstanceState Bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadFragment(new HomeFragment());
    }

    /**
     * Loads a particular page if possible.
     * @param fragment Fragment
     * @return boolean true or false
     */
    public boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
        }
        return false;
    }

    /**
     * Logs the user out of the application and returns to the login page.
     * @param view View
     */
    public void logout(View view){
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
    }
}