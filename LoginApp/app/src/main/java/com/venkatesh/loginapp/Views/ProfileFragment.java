package com.venkatesh.loginapp.Views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.venkatesh.loginapp.Constants.Constants;
import com.venkatesh.loginapp.Interfaces.ProfileView;
import com.venkatesh.loginapp.Model.UserModel;
import com.venkatesh.loginapp.Presenter.ProfilePresenter;
import com.venkatesh.loginapp.R;

/**
 * Created by Venkatesh on 1/14/16.
 */
public class ProfileFragment extends Fragment implements ProfileView {

    public static final String TAG = "ProfileFragment";
    public String userName = "";
    public String password = "";
    ProfilePresenter profilePresenter;

    TextView mEditProfileFirstName, mEditProfileLastName, mEditProfileUserName, mEditProfilePassword, mEditProfilePhoneNumber, mEditProfileEmailAddress;

    public static ProfileFragment newInstance(String userNameString, String passwordString){
        ProfileFragment fragment = new ProfileFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constants.USERNAME, userNameString);
        bundle.putString(Constants.PASSWORD, passwordString);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null){
            userName = getArguments().getString(Constants.USERNAME);
            password = getArguments().getString(Constants.PASSWORD);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile,container,false);
        profilePresenter = new ProfilePresenter(this);
        profilePresenter.getDetailsFromUserName(getActivity(), userName,password);
        mEditProfileFirstName = (TextView) view.findViewById(R.id.editProfileFirstName);
        mEditProfileLastName = (TextView) view.findViewById(R.id.editProfileLastName);
        mEditProfileUserName = (TextView) view.findViewById(R.id.editProfileUserName);
        mEditProfilePassword = (TextView) view.findViewById(R.id.editProfilePassword);
        mEditProfilePhoneNumber = (TextView) view.findViewById(R.id.editProfilePhoneNumber);
        mEditProfileEmailAddress = (TextView) view.findViewById(R.id.editProfileEmailAddress);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void setDetailsInView(final UserModel entryModel) {
        try{
            mEditProfileFirstName.post(new Runnable() {
                @Override
                public void run() {
               mEditProfileFirstName.setText(entryModel.getFirstName());
                }
            });
            mEditProfileLastName.post(new Runnable() {
                @Override
                public void run() {
                    mEditProfileLastName.setText(entryModel.getLastName());
                }
            });
            mEditProfileUserName.post(new Runnable() {
                @Override
                public void run() {
                    mEditProfileUserName.setText(entryModel.getUserName());
                }
            });
            mEditProfilePassword.post(new Runnable() {
                @Override
                public void run() {
                    mEditProfilePassword.setText(entryModel.getPassword());
                }
            });
            mEditProfileEmailAddress.post(new Runnable() {
                @Override
                public void run() {
                    mEditProfileEmailAddress.setText(entryModel.getEmailId());
                }
            });
            mEditProfilePhoneNumber.post(new Runnable() {
                @Override
                public void run() {
                    mEditProfilePhoneNumber.setText(entryModel.getPhoneNo());
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
