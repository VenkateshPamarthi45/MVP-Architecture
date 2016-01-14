package com.venkatesh.loginapp.Views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.venkatesh.loginapp.Interfaces.SignInView;
import com.venkatesh.loginapp.MainActivity;
import com.venkatesh.loginapp.Presenter.SignInPresenter;
import com.venkatesh.loginapp.R;

/**
 * Created by venkatesh on 1/13/16.
 */
public class SignInFragment extends Fragment implements SignInView{

    public static final String TAG = "SignInFragment";

    EditText mSignInUserNameEditText;
    EditText mSignInPasswordEditText;
    Button mSignInButton,mRegisterButton;
    SignInPresenter signInPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_signin,container,false);
        signInPresenter = new SignInPresenter(this);

        mSignInUserNameEditText = (EditText) view.findViewById(R.id.editTextSignInUserName);
        mSignInPasswordEditText = (EditText) view.findViewById(R.id.editTextSignInPassword);
        mSignInButton = (Button) view.findViewById(R.id.signInButton);
        mRegisterButton = (Button) view.findViewById(R.id.registerButton);

        mSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signInPresenter.checkingValidations(getActivity(), mSignInUserNameEditText.getText().toString(),mSignInPasswordEditText.getText().toString());
            }
        });
        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).openRegisterScreen();
            }
        });
        return view;
    }

    @Override
    public void userNameError(final String userNameError) {
        mSignInUserNameEditText.post(new Runnable() {
            @Override
            public void run() {
                mSignInUserNameEditText.setError(userNameError);
            }
        });
    }

    @Override
    public void passwordError(final String passwordError) {
        mSignInPasswordEditText.post(new Runnable() {
            @Override
            public void run() {
                mSignInPasswordEditText.setError(passwordError);
            }
        });
    }

    @Override
    public void inValidUserError(final String inValidUserError) {
        try {
                getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(getActivity(), inValidUserError, Toast.LENGTH_SHORT).show();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void switchToProfile() {
        try{
            ((MainActivity) getActivity()).openProfileScreen(mSignInUserNameEditText.getText().toString(), mSignInPasswordEditText.getText().toString());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
