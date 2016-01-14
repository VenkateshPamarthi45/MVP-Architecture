package com.venkatesh.loginapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.venkatesh.loginapp.Views.ProfileFragment;
import com.venkatesh.loginapp.Views.RegisterFragment;
import com.venkatesh.loginapp.Views.SignInFragment;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Fragment fragment = new SignInFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.container,fragment,SignInFragment.TAG);
        fragmentTransaction.commit();
    }

    public void openRegisterScreen(){
        Fragment fragment = new RegisterFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out, android.R.anim.slide_in_left,android.R.anim.slide_out_right);
        fragmentTransaction.replace(R.id.container,fragment,RegisterFragment.TAG);
        fragmentTransaction.addToBackStack(RegisterFragment.TAG);
        fragmentTransaction.commit();
    }

    public void openProfileScreen(String userNameString, String passwordString){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out, android.R.anim.slide_in_left,android.R.anim.slide_out_right);
        fragmentTransaction.replace(R.id.container,ProfileFragment.newInstance(userNameString,passwordString),ProfileFragment.TAG);
        fragmentTransaction.addToBackStack(ProfileFragment.TAG);
        fragmentTransaction.commit();
    }

    public void openLoginScreen(){

        FragmentManager fragmentManagerRemove = getSupportFragmentManager();
        int backStackCount = fragmentManagerRemove.getBackStackEntryCount();
        if (backStackCount > 0) {
            FragmentManager.BackStackEntry backStackEntry = fragmentManagerRemove.getBackStackEntryAt(backStackCount - 1);
            if (backStackEntry.getName() == RegisterFragment.TAG) {
                Log.d(TAG, "openLoginScreen()  removing Fragment : " + backStackEntry.getName());
                fragmentManagerRemove.popBackStack();
            }
        }

        Fragment fragment = new SignInFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out, android.R.anim.slide_in_left,android.R.anim.slide_out_right);
        fragmentTransaction.replace(R.id.container,fragment,SignInFragment.TAG);
        fragmentTransaction.commit();
    }
}
