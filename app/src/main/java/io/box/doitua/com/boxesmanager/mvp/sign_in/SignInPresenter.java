package io.box.doitua.com.boxesmanager.mvp.sign_in;

import android.util.Log;
import android.widget.Toast;

import io.box.doitua.com.boxesmanager.api.BoxApi;
import io.box.doitua.com.boxesmanager.db.user.User;

public class SignInPresenter implements SignInContract.EventListener {

    private SignInContract.View view;

    SignInPresenter(SignInContract.View view) {
        this.view = view;
    }

    @Override
    public void onFailure(Throwable err) {
        Log.d("SIGN IN", err.getMessage());
        Toast.makeText(view.getContext(), "This email does not exist", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccess(User user) {
        view.goToBoxActivity(user);
    }

    @Override
    public void findUser(String email) {
        BoxApi.getInstance().findUser(email).subscribe(this::onSuccess, this::onFailure);
    }
}
