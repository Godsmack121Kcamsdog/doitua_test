package io.box.doitua.com.boxesmanager.api;

import android.arch.persistence.room.Room;
import android.content.Context;

import java.util.List;

import io.box.doitua.com.boxesmanager.App;
import io.box.doitua.com.boxesmanager.db.user.Box;
import io.box.doitua.com.boxesmanager.db.user.User;
import io.box.doitua.com.boxesmanager.db.user.UsersDatabase;
import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

class UsersManager {

    private UsersDatabase db;
    private static final String DB_NAME = "search.db";
    private static UsersManager _instance = null;

    private UsersManager(Context ctx) {
        db = Room.databaseBuilder(ctx, UsersDatabase.class, DB_NAME).build();
    }

    public static synchronized UsersManager getInstance() {
        if (_instance == null) {
            _instance = new UsersManager(App.getInstance().getApplicationContext());
        }
        return _instance;
    }

    public Single<User> findByEmail(String email) {
        return db.searchDao().findByEmail(email)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Single<List<Box>> findBoxesById(int id) {
        return db.searchDao().findById(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Completable insertUser(User user) {
        return Completable.fromAction(() -> db.searchDao()
                .insert(user)).subscribeOn(Schedulers.io());
    }

    public void insertBox(Box box) {
        Completable.fromAction(() -> db.searchDao()
                .insert(box)).subscribeOn(Schedulers.io()).subscribe();
    }

    public Single<List<Box>> getAll(){
        return db.searchDao().getAll().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }
}
