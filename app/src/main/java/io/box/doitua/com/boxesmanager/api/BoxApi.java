package io.box.doitua.com.boxesmanager.api;

import android.util.Log;

import com.ihsanbal.logging.Level;
import com.ihsanbal.logging.LoggingInterceptor;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;
import io.box.doitua.com.boxesmanager.BuildConfig;
import io.box.doitua.com.boxesmanager.db.user.Box;
import io.box.doitua.com.boxesmanager.api.models.User;
import io.box.doitua.com.boxesmanager.adapters.ResponseAdapter;
import okhttp3.OkHttpClient;
import okhttp3.internal.platform.Platform;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author alex
 * Created on 4/11/17.
 * Threadsafe Singletone for making requests
 */
public class BoxApi {

    private static BoxApiInterface boxInterface;
    private static BoxApi apiCompetitions;
    private UsersManager manager;
    private static final String host = "https://box.io";

    public synchronized static BoxApi getInstance() {
        if (apiCompetitions == null) {
            apiCompetitions = new BoxApi();
            apiCompetitions.manager = UsersManager.getInstance();
        }
        return apiCompetitions;
    }

    private BoxApi() {
        createRetrofit();
    }

    private void createRetrofit() {
        //OkHttpClient3
        final OkHttpClient client = new OkHttpClient
                .Builder()
                .addInterceptor(logInterceptorBuilder())
                .build();

        //Retrofit2
        Retrofit retrofitAdapter = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(host)
                .client(client)
                .build();
        boxInterface = retrofitAdapter.create(BoxApiInterface.class);
    }

    private LoggingInterceptor logInterceptorBuilder() {
        return new LoggingInterceptor.Builder()
                .loggable(BuildConfig.DEBUG)
                .setLevel(Level.BASIC)
                .log(Platform.INFO)
                .request("RequestLog")
                .response("ResponseLog")
                .tag("info")
                .addHeader("version", BuildConfig.VERSION_NAME)
                .logger((level, tag, msg) -> Log.w(tag, msg))
                .build();
    }


    public Completable registerUser(User user) {
        return apiCompetitions.manager.insertUser(ResponseAdapter.castToDBUser(user));
    }

    public Single<io.box.doitua.com.boxesmanager.db.user.User> findUser(String email) {
        return apiCompetitions.manager.findByEmail(email);
    }

    public void insertBox(io.box.doitua.com.boxesmanager.api.models.Box box, int id) {
        apiCompetitions.manager.insertBox(ResponseAdapter.castToDBBox(box, id));
    }

    public Single<List<Box>> getBoxes(int userId){
        return apiCompetitions.manager.findBoxesById(userId);
    }

    public Single<List<Box>> getAll(){
        return apiCompetitions.manager.getAll();
    }

}