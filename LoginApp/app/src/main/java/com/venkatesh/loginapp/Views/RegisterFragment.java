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

import com.venkatesh.loginapp.Interfaces.RegisterView;
import com.venkatesh.loginapp.MainActivity;
import com.venkatesh.loginapp.Presenter.RegisterPresenter;
import com.venkatesh.loginapp.R;

/**
 * Created by Venkatesh on 1/14/16.
 */
public class RegisterFragment extends Fragment implements RegisterView {

    public static final String TAG = "RegisterFragment";

    EditText mFirstNameEditText, mLastNameEditText, mUserNameEditText, mPasswordEditText, mConfirmPasswordEditText, mPhoneNoEditText, mEmailAddressEditText;
    Button mSignUpButton;
    RegisterPresenter registerPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register,container,false);

        mFirstNameEditText = (EditText) view.findViewById(R.id.editTextRegisterFirstName);
        mLastNameEditText = (EditText) view.findViewById(R.id.editTextRegisterLastName);
        mUserNameEditText = (EditText) view.findViewById(R.id.editTextRegisterUserName);
        mPasswordEditText = (EditText) view.findViewById(R.id.editTextRegisterPassword);
        mConfirmPasswordEditText = (EditText) view.findViewById(R.id.editTextRegisterRepeatPassword);
        mPhoneNoEditText = (EditText) view.findViewById(R.id.editTextRegisterPhoneNo);
        mEmailAddressEditText = (EditText) view.findViewById(R.id.editTextRegisterEmailId);
        registerPresenter = new RegisterPresenter(this);
        mSignUpButton = (Button) view.findViewById(R.id.signUpButton);
        mSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerPresenter.checkingRegisterValidations(getActivity(),mFirstNameEditText.getText().toString(),mLastNameEditText.getText().toString(),mUserNameEditText.getText().toString(),mPasswordEditText.getText().toString(),mConfirmPasswordEditText.getText().toString(),mPhoneNoEditText.getText().toString(),mEmailAddressEditText.getText().toString());
            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void setErrorEmptyField(final String errorEmptyField) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getActivity(), errorEmptyField, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void switchToLoginScreen() {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ((MainActivity) getActivity()).openLoginScreen();
            }
        });
    }
}
