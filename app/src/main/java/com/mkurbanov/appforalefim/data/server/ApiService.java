package com.mkurbanov.appforalefim.data.server;

import com.mkurbanov.appforalefim.data.server.main.MainResponse;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.Response;
import retrofit2.http.GET;

public interface ApiService {
    @GET("task-m-001/list.php")
    Observable<Response<List<String>>> getMain();
}
