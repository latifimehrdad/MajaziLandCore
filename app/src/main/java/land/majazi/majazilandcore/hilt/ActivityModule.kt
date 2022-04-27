package land.majazi.majazilandcore.hilt

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn

import dagger.hilt.components.SingletonComponent
import land.majazi.majazicore.manager.api.RemoteErrorEmitter
import land.majazi.majazilandcore.MainActivity
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ActivityModule {

    @Provides
    @Singleton
    fun provideRemoteErrorEmitter() : RemoteErrorEmitter {
        return MainActivity()
    }
}