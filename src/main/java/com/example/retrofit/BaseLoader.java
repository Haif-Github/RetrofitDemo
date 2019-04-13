package com.example.retrofit;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by haif on 2019/4/13.
 */

public class BaseLoader {

    protected <T> Observable<T> observe(Observable<T> observable) {
        return observable.subscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread());
    }

}
