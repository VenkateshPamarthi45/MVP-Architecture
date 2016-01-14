package com.venkatesh.loginapp.Interactor;

import android.content.Context;

import com.venkatesh.loginapp.DataBase.UserDb;
import com.venkatesh.loginapp.Interfaces.ProfilePresenterInterface;
import com.venkatesh.loginapp.Model.UserModel;

/**
 * Created by Venkatesh on 1/14/16.
 */
public class ProfileInterActor {
    private static final String TAG = "ProfileInterActor";
    public void getUserDetails(final Context context, final String userName, final String password, final ProfilePresenterInterface profilePresenter) {
        try{
            new Thread(new Runnable() {
                @Override
                public void run() {
                    UserDb userDb = new UserDb(context);
                    if(userDb.isUserPresent(userName,password)){
                        UserModel entryModel = userDb.getUser(userName,password);
                        profilePresenter.updatingUserDetailsInView(entryModel);
                    }
                }
            }).start();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
