package demo.common;

import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.concurrent.TimeUnit;

/**
 * Retrofit构造器
 * @author Sean
 * @createDate 2021-09-13
 */
public class RetrofitBuilder {

    public static Retrofit create() {
        final Logger logger = LoggerFactory.getLogger(RetrofitBuilder.class);
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(message -> logger.info(">>>>>>workWeXinHttpClient 请求:{}", message))
                .setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .addInterceptor(chain -> {
                    Request request = chain.request();
                    Request.Builder requestBuilder = request.newBuilder().addHeader("Connection", "close");
                    return chain.proceed(requestBuilder.build());
                })
                .hostnameVerifier(((s, sslSession) -> true))
                //是否开启缓存
                .retryOnConnectionFailure(true)
                //链接池
                .connectionPool(new ConnectionPool(200, 5, TimeUnit.MINUTES))
                //链接超时时间
                .connectTimeout(10L, TimeUnit.SECONDS)
                //读取超时时间
                .readTimeout(10L, TimeUnit.SECONDS)
                .build();
        return new Retrofit.Builder()
                .baseUrl("https://qyapi.weixin.qq.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build();
    }

}
