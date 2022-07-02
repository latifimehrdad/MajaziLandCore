package land.majazi.majazilandcore.hilt

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import land.majazi.majazicore.manager.MessageManager
import land.majazi.majazilandcore.ApiInterface
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApiModel {

    @Provides
    @Singleton
    fun provideBaseUrl(): String {
        return "https://belfi.aspkar.ir"
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiInterface {
        return retrofit.create(ApiInterface::class.java)
    }

}