package com.venkatesh.loginapp.Interfaces;

/**
 * Created by venkatesh on 1/13/16.
 */
public interface SignInView {
    public void userNameError(String userNameError);
    public void passwordError(String passwordError);
    public void inValidUserError(String inValidUserError);
    public void switchToProfile();
}
