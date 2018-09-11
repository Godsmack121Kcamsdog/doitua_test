package io.box.doitua.com.boxesmanager.mvp.sign_in;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import io.box.doitua.com.boxesmanager.BaseActivity;
import io.box.doitua.com.boxesmanager.R;
import io.box.doitua.com.boxesmanager.adapters.ResponseAdapter;
import io.box.doitua.com.boxesmanager.databinding.ActivitySignInBinding;
import io.box.doitua.com.boxesmanager.db.user.User;
import io.box.doitua.com.boxesmanager.mvp.main.UserActivity;
import io.box.doitua.com.boxesmanager.mvp.sign_up.SignUpActivity;
import io.box.doitua.com.boxesmanager.utils.Validator;

public class SignInActivity extends BaseActivity implements SignInContract.View {

    private ActivitySignInBinding binding;
    private Validator validator;
    private SignInPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_in);
        presenter = new SignInPresenter(this);
        validator = new Validator();

        binding.confirm.setOnClickListener(v -> {
            if (validator.isValidableEmail(binding.email.getText().toString())) {
                Toast.makeText(this, "invalid email", Toast.LENGTH_SHORT).show();
                return;
            }
            presenter.findUser(binding.email.getText().toString());
        });

        binding.noAccount.setOnClickListener(v -> openSignUp());
    }

    @Override
    public void openSignUp() {
        startActivity(new Intent(this, SignUpActivity.class));
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void goToBoxActivity(User user) {
        Log.d("SIGN IN", "goToBoxActivity: ");
        Intent intent = new Intent(SignInActivity.this, UserActivity.class);
        intent.putExtra("user", ResponseAdapter.castToServerUser(user));
        startActivity(intent);
        finish();
    }
}
