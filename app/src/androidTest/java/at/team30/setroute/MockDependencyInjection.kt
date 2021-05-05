package at.team30.setroute

import at.team30.setroute.infrastructure.DependencyInjection
import at.team30.setroute.infrastructure.IRoutesRepository
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
}