package com.venkatesh.loginapp.Interactor;

import android.content.Context;
import android.util.Log;
import android.util.Patterns;

import com.venkatesh.loginapp.DataBase.UserDb;
import com.venkatesh.loginapp.Interfaces.RegisterPresenterInterface;
import com.venkatesh.loginapp.Model.UserModel;

/**
 * Created by Venkatesh on 1/14/16.
 */
public class RegisterInterActor {

    private static final String TAG = "RegisterInterActor";

    public void checkingRegisterValidations(final Context context, final String firstNameString, final String lastNameString, final String userNameString, final String passwordString, final String confirmPassword, final String phoneNoString, final String emailIdString, final RegisterPresenterInterface registerPresenterInterface) {
        Log.d(TAG, "checkingRegisterValidations() called with: " + "context = [" + context + "], firstNameString = [" + firstNameString + "], lastNameString = [" + lastNameString + "], userNameString = [" + userNameString + "], passwordString = [" + passwordString + "], confirmPassword = [" + confirmPassword + "], phoneNoString = [" + phoneNoString + "], emailIdString = [" + emailIdString + "], registerPresenterInterface = [" + registerPresenterInterface + "]");
        try{
            new Thread(new Runnable() {
                @Override
                public void run() {
                    if(firstNameString.isEmpty()){
                        registerPresenterInterface.setErrorEmptyField("Please Enter First Name");
                    }else if(lastNameString.isEmpty()){
                        registerPresenterInterface.setErrorEmptyField("Please Enter Last Name");
                    }else if(userNameString.isEmpty()){
                        registerPresenterInterface.setErrorEmptyField("Please Enter User Name");
                    }else if(passwordString.isEmpty()){
                        registerPresenterInterface.setErrorEmptyField("Please Enter Password");
                    }else if(confirmPassword.isEmpty()){
                        registerPresenterInterface.setErrorEmptyField("Please Enter Confirm Password");
                    }else if(!passwordString.matches(confirmPassword)){
                        registerPresenterInterface.setErrorEmptyField("Please Enter Same Password");
                    }else if(phoneNoString.length() != 10){
                        registerPresenterInterface.setErrorEmptyField("Please Enter Correct Phone Number");
                    }else if(emailIdString.isEmpty()){
                        registerPresenterInterface.setErrorEmptyField("Please Enter Email Address");
                    }else if(!Patterns.EMAIL_ADDRESS.matcher(emailIdString).matches()){
                        registerPresenterInterface.setErrorEmptyField("Please Enter Valid Email Address");
                    }else{
                        // ready for inserting database
                        Log.d(TAG, "run() called with: " + "ready for inserting database");
                        creatingEntryInDatabase(context, firstNameString,lastNameString,userNameString,passwordString,phoneNoString,emailIdString, registerPresenterInterface);
                    }
                }
            }).start();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void creatingEntryInDatabase(final Context context, final String firstNameString, final String lastNameString, final String userNameString, final String passwordString, final String phoneNoString, final String emailIdString, final RegisterPresenterInterface registerPresenterInterface) {
        try{
            new Thread(new Runnable() {
                @Override
                public void run() {
                    UserDb userDb = new UserDb(context);
                    if(userDb.isUserNameExists(userNameString)){
                        registerPresenterInterface.setErrorEmptyField("User Name Already Exists ");
                    }else{
                        UserModel entryModel = new UserModel(firstNameString,lastNameString,userNameString,phoneNoString,emailIdString,passwordString);
                        userDb.addUser(entryModel);
                        registerPresenterInterface.switchToLoginScreen();
                    }
                }
            }).start();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
