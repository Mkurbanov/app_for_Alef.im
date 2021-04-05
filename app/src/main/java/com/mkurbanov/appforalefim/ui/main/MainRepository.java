package com.mkurbanov.appforalefim.ui.main;

import com.mkurbanov.appforalefim.App;
import com.mkurbanov.appforalefim.data.server.main.MainResponse;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.Response;

public class MainRepository {

    public Observable<Response<List<String>>> getMain(){
        return App.getInstance().getRequests().getApi().getMain();
    }

}
