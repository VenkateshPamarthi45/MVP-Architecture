package com.venkatesh.loginapp.Presenter;

import android.content.Context;

import com.venkatesh.loginapp.Interactor.RegisterInterActor;
import com.venkatesh.loginapp.Interfaces.RegisterPresenterInterface;
import com.venkatesh.loginapp.Interfaces.RegisterView;

/**
 * Created by Venkatesh on 1/14/16.
 */
public class RegisterPresenter implements RegisterPresenterInterface {

    RegisterView registerView;
    RegisterInterActor registerInterActor;

    public RegisterPresenter(RegisterView registerView) {
        this.registerView = registerView;
        registerInterActor = new RegisterInterActor();
    }
    public void checkingRegisterValidations(Context context, String firstNameString, String lastNameString, String userNameString, String passwordString, String confirmPassword, String phoneNoString, String emailIdString){
        registerInterActor.checkingRegisterValidations(context, firstNameString,lastNameString,userNameString,passwordString,confirmPassword,phoneNoString,emailIdString, this);
    }

    @Override
    public void setErrorEmptyField(String errorEmptyField) {
        registerView.setErrorEmptyField(errorEmptyField);
    }

    @Override
    public void switchToLoginScreen() {
        registerView.switchToLoginScreen();
    }
}
