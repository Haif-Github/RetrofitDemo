package com.example.retrofit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.List;

import rx.functions.Action1;

public class MainActivity extends AppCompatActivity {

    private MovieLoader movieLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 创建Loader实例
        movieLoader = new MovieLoader();
        getMovieList();
    }

    private void getMovieList() {
        movieLoader.getMovie(0, 250)
                .subscribe(
                        new Action1<List<Movie>>() {
                            @Override
                            public void call(List<Movie> movies) {
                                Log.e("getMovie", movies.get(0).name);
                            }
                        }, new Action1<Throwable>() {
                            @Override
                            public void call(Throwable throwable) {
                                Log.e("Error", throwable.getMessage());
                            }
                        }
                );

    }

}
