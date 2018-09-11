package io.box.doitua.com.boxesmanager.mvp.sign_up;

import android.util.Log;
import android.widget.Toast;

import io.box.doitua.com.boxesmanager.api.BoxApi;
import io.box.doitua.com.boxesmanager.api.models.User;

public class SignUpPresenter implements SignUpContract.EventListener {

    private SignUpContract.View view;

    SignUpPresenter(SignUpContract.View view) {
        this.view = view;
    }

    @Override
    public void onFailure(Throwable err) {
        Toast.makeText(view.getContext(), err.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccess() {
        Log.d("SIGN_UP:", "success");
    }

    @Override
    public void addNewUser(User user) {
        BoxApi.getInstance().registerUser(user)
                .subscribe(() -> view.goToBoxActivity(user),
                        this::onFailure);
    }

}
