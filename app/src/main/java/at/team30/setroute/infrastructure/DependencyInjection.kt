package at.team30.setroute.infrastructure

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn (SingletonComponent::class)
object DependencyInjection {

    @Singleton
    @Provides
    fun provideRoutesRepository(): IRoutesRepository {
        return RoutesRepository()
    }
}