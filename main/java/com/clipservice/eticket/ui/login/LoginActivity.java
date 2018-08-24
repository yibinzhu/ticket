package com.clipservice.eticket.ui.login;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.clipservice.eticket.R;

public class LoginActivity extends AppCompatActivity {
    private FragmentManager fragmentManager;
    private FragmentTransaction ft;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        LoginFragment loginFragment = new LoginFragment();
        fragmentManager = getFragmentManager();
        ft = getFragmentManager().beginTransaction();
        ft = fragmentManager.beginTransaction();
        ft.replace(R.id.fragment_container,loginFragment);
        ft.commit();
    }
}
