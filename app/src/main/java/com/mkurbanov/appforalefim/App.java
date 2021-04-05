package com.mkurbanov.appforalefim;

import android.app.Application;

import com.mkurbanov.appforalefim.data.server.Requests;
import com.mkurbanov.appforalefim.utils.Functions;


public class App extends Application {
    private Requests requests;
    private Functions functions;
    private static App app;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
    }

    public static App getInstance() {
        if (app == null)
            app = new App();
        return app;
    }

    public Requests getRequests() {
        if (requests == null)
            requests = new Requests();
        return requests;
    }

    public Functions getFunctions() {
        if (functions == null)
            functions = new Functions();
        return functions;
    }

}
