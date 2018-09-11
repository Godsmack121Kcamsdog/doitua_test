package io.box.doitua.com.boxesmanager.mvp.sign_in;

import android.content.Context;

import io.box.doitua.com.boxesmanager.db.user.User;

interface SignInContract {

    interface View {
        void openSignUp();
        Context getContext();
        void goToBoxActivity(User user);
    }

    interface EventListener {
        void onFailure(Throwable err);
        void onSuccess(User user);
        void findUser(String email);
    }
}
