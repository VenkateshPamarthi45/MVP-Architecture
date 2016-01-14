package com.venkatesh.loginapp.Model;

/**
 * Created by Venkatesh on 1/13/16.
 */
public class UserModel {

    String firstName;
    String lastName;
    String userName;
    String phoneNo;
    String emailId;
    String password;

    public UserModel() {
    }

    public UserModel(String firstName, String lastName, String userName, String phoneNo, String emailId, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.phoneNo = phoneNo;
        this.emailId = emailId;
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
