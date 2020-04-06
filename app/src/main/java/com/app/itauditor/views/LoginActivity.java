package com.app.itauditor.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import com.app.itauditor.R;
import com.app.itauditor.views.Fragments.LoginFragment;

public class LoginActivity extends AppCompatActivity {

    private Fragment mFragment = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        callFragment();
    }

    private void callFragment() {
        mFragment = new LoginFragment();  //Home
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.nav_host_fragment, mFragment,"LoginFragment")
                .commit();
    }
}
