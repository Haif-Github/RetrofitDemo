package com.example.retrofit;


import java.util.List;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by haif on 2019/4/13.
 */

public class MovieLoader extends BaseLoader {

    private final MovieService movieService;

    // 获取接口实例
    public MovieLoader() {
        movieService = RetrofitServiceManager.getInstance().create(MovieService.class);
    }

    // 获取电影列表
    public Observable<List<Movie>> getMovie(int start, int count) {
        Observable<MovieSubject> observe = observe(movieService.getTop250ByGet(start, count));  // observe方法在父类中，一些重复的操作
        Observable<List<Movie>> list = observe.map(new Func1<MovieSubject, List<Movie>>() {
            @Override
            public List<Movie> call(MovieSubject movieSubject) {
                return movieSubject.subject;
            }
        });
        return list;
    }

    // 获取电影评价
    public Observable<String> getMovieContent(String movieId) {
        Observable<String> observe = observe(movieService.getComment(movieId));
        Observable<String> str = observe.map(new Func1<String, String>() {
            @Override
            public String call(String s) {
                return s;
            }
        });
        return str;
    }

}
