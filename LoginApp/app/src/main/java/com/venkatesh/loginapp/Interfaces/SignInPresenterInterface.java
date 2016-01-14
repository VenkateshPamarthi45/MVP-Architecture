package com.venkatesh.loginapp.Interfaces;

/**
 * Created by Venkatesh on 1/13/16.
 */
public interface SignInPresenterInterface {
    public void setErrorUserName(String errorUserName);
    public void setErrorPassword(String errorPassword);
    public void setErrorInValidUser(String errorInValidUser);
    public void switchToProfile();
}
