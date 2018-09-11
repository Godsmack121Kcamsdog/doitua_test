package io.box.doitua.com.boxesmanager.mvp.sign_up;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import io.box.doitua.com.boxesmanager.BaseActivity;
import io.box.doitua.com.boxesmanager.R;
import io.box.doitua.com.boxesmanager.api.models.User;
import io.box.doitua.com.boxesmanager.databinding.ActivitySignUpBinding;
import io.box.doitua.com.boxesmanager.mvp.main.UserActivity;
import io.box.doitua.com.boxesmanager.utils.Validator;

public class SignUpActivity extends BaseActivity implements SignUpContract.View {

    private ActivitySignUpBinding binding;
    private Validator validator;
    private SignUpPresenter presenter;
    private User user;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up);
        presenter = new SignUpPresenter(this);
        validator = new Validator();

        binding.confirm.setOnClickListener(v -> {

            if (validator.isValidableEmail(binding.email.getText().toString())) {
                Toast.makeText(this, "invalid email", Toast.LENGTH_SHORT).show();
                return;
            }

            if (binding.name.getText().toString().equals("")) {
                Toast.makeText(this, "user name MUST be indicated", Toast.LENGTH_SHORT).show();
                return;
            }

            user = new User();
            user.setName(binding.name.getText().toString());
            user.setEmail(binding.email.getText().toString());

            presenter.addNewUser(user);
        });
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void goToBoxActivity(User user) {
        Intent intent = new Intent(SignUpActivity.this, UserActivity.class);
        intent.putExtra("user", user);
        startActivity(intent);
        finish();
    }
}
