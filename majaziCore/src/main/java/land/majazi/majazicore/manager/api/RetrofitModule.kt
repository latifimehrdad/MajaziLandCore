package land.majazi.majazicore.manager.api

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.LocalDateTime
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class RetrofitModule {

    //______________________________________________________________________________________________ provideRetrofit
    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient, baseUrl: String, gson: Gson): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .build()
    }
    //______________________________________________________________________________________________ provideRetrofit


    //______________________________________________________________________________________________ provideHttpClient
    @Provides
    @Singleton
    fun provideHttpClient(
        interceptor: Interceptor,
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient()
            .newBuilder()
            .addInterceptor(interceptor)
            .addNetworkInterceptor(loggingInterceptor)
            .build()
    }
    //______________________________________________________________________________________________ provideHttpClient


    //______________________________________________________________________________________________ provideInterceptor
    @Provides
    @Singleton
    fun provideInterceptor(): Interceptor {
        return Interceptor { chain ->
            val newRequest: Request = chain.request().newBuilder()
                .addHeader("device", "android")
                .build()
            chain.proceed(newRequest)
        }
    }
    //______________________________________________________________________________________________ provideInterceptor


    //______________________________________________________________________________________________ provideLoggingInterceptor
    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }
    //______________________________________________________________________________________________ provideLoggingInterceptor


    //______________________________________________________________________________________________ provideGson
    @Provides
    @Singleton
    fun provideGson(): Gson {
        val gsonBuilder = GsonBuilder()
        gsonBuilder.registerTypeAdapter(LocalDateTime::class.java, LocalDateTimeDeserializerManager())
        return gsonBuilder.create()
    }
    //______________________________________________________________________________________________ provideGson

}