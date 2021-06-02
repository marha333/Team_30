package at.team30.setroute

import at.team30.setroute.Helper.EmailHelper
import at.team30.setroute.Helper.IEmailHelper
import at.team30.setroute.infrastructure.*
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import io.mockk.every
import io.mockk.mockk
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [DependencyInjection::class]
)
class MockDependencyInjection {
    private val mockRepository: IRoutesRepository = mockk()

    @Singleton
    @Provides
    fun provideFakeRoutesRepository(): IRoutesRepository {
        every { mockRepository.getRoutes() }.returns(FixturesAndroid.routes_many())
        for (route in FixturesAndroid.routes_many()) {
            every { mockRepository.getRoutesById(route.id) }.returns(route)
        }
        return mockRepository
    }

    @Singleton
    @Provides
    fun provideImageRepository(): IImageRepository {
        return ImageRepository(provideFakeRoutesRepository())
    }

    @Singleton
    @Provides
    fun provideSettingRepository(): ISettingRepository {
        return SettingRepository()
    }

    @Singleton
    @Provides
    fun provideEmailHelper(): IEmailHelper {
        return EmailHelper(
            smtpPort = 666,
            smtpHost = "localhost",
            userName = null,
            authenticate = false,
            password = null,
            sender = "testSender666@test.it"
        )
    }

    @Singleton
    @Provides
    fun provideFilteringRepository(): IFilterRepository {
        return FilteringRepository()
    }
}