package com.example.retrofit;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by haif on 2019/3/11.
 */

public interface MovieService {

    // 获取豆瓣Top250榜单
    // GET方式（参数标签用@Query标签，如果参数多的话可以用@QueryMap标签，接收一个Map）
    @GET("top250")
    Observable<MovieSubject> getTop250ByGet(@Query("start") int start, @Query("count") int count);  // 引号内的是"尾址"，完整的地址应该是 baseUrl+尾址

    // POST方式（参数标签用@Field或@Body或@FieldMap，注意使用POST方式必须要有参数，否则会抛异常）
    @FormUrlEncoded     // 使用POST方式时必须加 @FormUrlEncoded标签
    @POST("/comment")
    Observable<String> getComment(@Field("movieId") String movieId);

}
