package com.bb.fourstarhotel.presenter;

import com.bb.fourstarhotel.database.UserEntity;

public interface Contract {

    interface Presenter{
        void loginUser(String userName, String userPassword);
        void logoutUser();
        UserEntity getUserInstance();
    }

    interface View{
        void userLoginSuccess();
        void userLoginFailed();
        void userLoggedOut();
    }
}
