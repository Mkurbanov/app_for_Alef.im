package com.mkurbanov.appforalefim.ui.main;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mkurbanov.appforalefim.global.GlobalResult;
import com.mkurbanov.appforalefim.utils.Functions;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainViewModel extends ViewModel {
    private MainRepository repository = new MainRepository();
    public MutableLiveData<GlobalResult> result = new MutableLiveData<>();
    public MutableLiveData<List<String>> items = new MutableLiveData<>(new ArrayList<>());

    public void getMainData() {
        repository.getMain()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((response) -> {
                    if (response.isSuccessful()) {
                        result.setValue(new GlobalResult());
                        if (response.body() != null)
                            items.setValue(response.body());
                    } else {
                        result.setValue(new GlobalResult(Functions.getNetworkError()));
                    }
                }, t -> {
                    t.printStackTrace();
                    result.setValue(new GlobalResult(Functions.getNetworkError()));
                });
    }

}
