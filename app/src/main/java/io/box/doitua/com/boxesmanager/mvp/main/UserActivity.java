package io.box.doitua.com.boxesmanager.mvp.main;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import io.box.doitua.com.boxesmanager.BaseActivity;
import io.box.doitua.com.boxesmanager.R;
import io.box.doitua.com.boxesmanager.adapters.BoxRecyclerAdapter;
import io.box.doitua.com.boxesmanager.adapters.ResponseAdapter;
import io.box.doitua.com.boxesmanager.api.BoxApi;
import io.box.doitua.com.boxesmanager.api.models.User;
import io.box.doitua.com.boxesmanager.databinding.ActivityBoxBinding;
import io.box.doitua.com.boxesmanager.db.user.Box;
import io.box.doitua.com.boxesmanager.view.custom.BoxDialog;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

public class UserActivity extends BaseActivity {

    private ActivityBoxBinding binding;
    private BoxRecyclerAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("USER", "ON CREATE");
        binding = DataBindingUtil.setContentView(this, R.layout.activity_box);
        binding.recyclerBoxes.setLayoutManager(new LinearLayoutManager(this));
        User user = (User) getIntent().getSerializableExtra("user");
        binding.recyclerBoxes.setAdapter(adapter = new BoxRecyclerAdapter(this, user.getName()));
        binding.addBtn.setOnClickListener(v ->
                new BoxDialog(this, b -> {
                    adapter.addBox(b);
                    BoxApi.getInstance().insertBox(b, user.getId());
                }).show());

        BoxApi.getInstance().getAll().subscribe(new SingleObserver<List<Box>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(List<Box> boxes) {
                for (Box b : boxes) {
                    Log.d("BOX", b.toString());
                }
            }

            @Override
            public void onError(Throwable e) {
                System.out.println();
            }
        });

        BoxApi.getInstance().findUser(user.getEmail()).subscribe(
                it -> {
                    user.setId(it.getId());
                    BoxApi.getInstance()
                            .getBoxes(it.getId())
                            .subscribe(this::onSuccess, this::onFailure);
                },
                this::onFailure);

        binding.userName.setText(user.getEmail());
    }

    private void onSuccess(List<Box> list) {
        for (Box b : list) {
            adapter.addBox(ResponseAdapter.castToServerBox(b));
        }
        adapter.notifyDataSetChanged();
    }

    private void onFailure(Throwable err) {
        Toast.makeText(this, "error", Toast.LENGTH_SHORT).show();
        Log.d("USER DATA", err.getMessage());
    }
}
