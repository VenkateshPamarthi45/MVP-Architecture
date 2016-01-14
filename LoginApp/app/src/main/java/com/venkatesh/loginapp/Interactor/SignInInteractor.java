package com.venkatesh.loginapp.Interactor;

import android.content.Context;
import android.util.Log;

import com.venkatesh.loginapp.DataBase.UserDb;
import com.venkatesh.loginapp.Interfaces.SignInPresenterInterface;

/**
 * Created by Venkatesh on 1/13/16.
 */
public class SignInInteractor {
    private static final String TAG = "SignInInteractor";
    public void checkingSignInValidations(final Context context, final String userNameString, final String passwordString, final SignInPresenterInterface signInPresenterInterface) {
        Log.d(TAG, "checkingSignInValidations() called with: " + "context = [" + context + "], userNameString = [" + userNameString + "], passwordString = [" + passwordString + "], signInPresenterInterface = [" + signInPresenterInterface + "]");
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(userNameString.isEmpty()){
                    signInPresenterInterface.setErrorUserName("Please Enter User Name");
                }else if(passwordString.isEmpty()){
                    signInPresenterInterface.setErrorPassword("Please Enter Password");
                }else if(passwordString.length() < 6){
                    signInPresenterInterface.setErrorPassword("Please Enter Valid Password");
                }else{
                    isUserPresentInDataBase(context, userNameString, passwordString, signInPresenterInterface);
                }
            }
        }).start();

    }

    private void isUserPresentInDataBase(final Context context, final String userNameString, final String passwordString, final SignInPresenterInterface signInPresenterInterface) {
        Log.d(TAG, "isUserPresentInDataBase() called with: " + "context = [" + context + "], userNameString = [" + userNameString + "], passwordString = [" + passwordString + "], signInPresenterInterface = [" + signInPresenterInterface + "]");
        new Thread(new Runnable() {
            @Override
            public void run() {
                UserDb userDb = new UserDb(context);
                if(userDb.isUserPresent(userNameString,passwordString)){
                    Log.d(TAG, "run() called with: " + "User presenter in database");
                    signInPresenterInterface.switchToProfile();
                }else{
                    signInPresenterInterface.setErrorInValidUser("Invalid User/Password");
                }
            }
        }).start();
    }
}
