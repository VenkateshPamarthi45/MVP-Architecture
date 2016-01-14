package com.venkatesh.loginapp.Presenter;

import android.content.Context;

import com.venkatesh.loginapp.Interactor.ProfileInterActor;
import com.venkatesh.loginapp.Interfaces.ProfilePresenterInterface;
import com.venkatesh.loginapp.Interfaces.ProfileView;
import com.venkatesh.loginapp.Model.UserModel;

/**
 * Created by Venkatesh on 1/14/16.
 */
public class ProfilePresenter implements ProfilePresenterInterface {
    
    ProfileView profileView;
    ProfileInterActor profileInterActor;
    
    public ProfilePresenter(ProfileView profileView) {
        this.profileView = profileView;
        profileInterActor = new ProfileInterActor();
    }

    public void getDetailsFromUserName(Context context, String userName, String password){
        profileInterActor.getUserDetails(context, userName, password, this);
    }

    @Override
    public void updatingUserDetailsInView(UserModel entryModel) {
        profileView.setDetailsInView(entryModel);
    }
}
