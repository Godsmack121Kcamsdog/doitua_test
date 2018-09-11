package io.box.doitua.com.boxesmanager.api;

import java.util.List;

import io.box.doitua.com.boxesmanager.api.models.Box;
import io.box.doitua.com.boxesmanager.api.models.User;
import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface BoxApiInterface {

    @POST("/register")
    public void registerUser(User user);

    @POST("/auth")
    public void authorizeUser(User user);

    @POST("/user/boxes/add")
    public void addBox(int userId, Box box);

    @GET("/user/boxes/get")
    public Flowable<List<Box>> getBoxes(int userId);
}
