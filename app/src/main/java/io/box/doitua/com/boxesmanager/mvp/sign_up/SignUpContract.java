package io.box.doitua.com.boxesmanager.mvp.sign_up;

import android.content.Context;

import io.box.doitua.com.boxesmanager.api.models.User;

interface SignUpContract {

    interface View {
        Context getContext();

        void goToBoxActivity(User user);
    }

    interface EventListener {
        void onFailure(Throwable err);

        void onSuccess();

        void addNewUser(User user);

    }
}
