package com.mkurbanov.appforalefim.data.server;


import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory;
import com.mkurbanov.appforalefim.App;
import com.mkurbanov.appforalefim.config;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

public class Requests {
    private Retrofit retrofit;
    private ApiService api;
    private final App app = App.getInstance();
    private OkHttpClient okHttpClient;
    private DrawableCrossFadeFactory factory;

    public Requests() {
        factory = new DrawableCrossFadeFactory.Builder().setCrossFadeEnabled(true).build();
        initRetrofit();
    }

    private void initRetrofit() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .retryOnConnectionFailure(true)
                .addNetworkInterceptor(new Interceptor() {
                    @NotNull
                    @Override
                    public Response intercept(@NotNull Chain chain) throws IOException {
                        Request.Builder requestBuilder = chain.request().newBuilder();
                        requestBuilder.addHeader("User-Agent", config.APP_NAME);
                        return chain.proceed(requestBuilder.build());
                    }
                })
                .build();

        this.retrofit = new Retrofit.Builder()
                .baseUrl(config.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .client(okHttpClient)
                .build();

        api = retrofit.create(ApiService.class);
    }

    public void cancellAllRequests(){
        okHttpClient.dispatcher().cancelAll();
    }

    public ApiService getApi() {
        return api;
    }

    public void getImage(final String url, final ImageView imageView) {
        Glide.with(App.getInstance().getApplicationContext())
                .load(url)
                .transition(withCrossFade(factory))
                .skipMemoryCache(false)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);
    }

}
