package at.team30.setroute

import at.team30.setroute.infrastructure.IRoutesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.mockk.mockk
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class MockDependencyInjection {
    @Singleton
    @Provides
    fun provideFakeRoutesRepository(): IRoutesRepository = mockk()
}