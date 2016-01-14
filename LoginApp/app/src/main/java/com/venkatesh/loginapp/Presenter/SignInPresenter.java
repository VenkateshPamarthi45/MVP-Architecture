package com.venkatesh.loginapp.Presenter;

import android.content.Context;

import com.venkatesh.loginapp.Interactor.SignInInteractor;
import com.venkatesh.loginapp.Interfaces.SignInPresenterInterface;
import com.venkatesh.loginapp.Interfaces.SignInView;

/**
 * Created by Venkatesh on 1/13/16.
 */
public class SignInPresenter implements SignInPresenterInterface {

    SignInView signInView;
    SignInInteractor signInInteractor;

    public SignInPresenter(SignInView signInView) {
        this.signInView = signInView;
        signInInteractor = new SignInInteractor();
    }

    public void checkingValidations(Context context, String userNameString, String passwordString){
        signInInteractor.checkingSignInValidations(context, userNameString, passwordString, this);
    }

    @Override
    public void setErrorUserName(String errorUserName) {
        signInView.userNameError(errorUserName);
    }

    @Override
    public void setErrorPassword(String errorPassword) {
        signInView.passwordError(errorPassword);
    }

    @Override
    public void setErrorInValidUser(String errorInValidUser) {
        signInView.inValidUserError(errorInValidUser);
    }

    @Override
    public void switchToProfile() {
        signInView.switchToProfile();
    }
}
