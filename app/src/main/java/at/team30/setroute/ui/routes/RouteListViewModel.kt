package at.team30.setroute.ui.routes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import at.team30.setroute.infrastructure.IFilterRepository
import at.team30.setroute.infrastructure.IRoutesRepository
import at.team30.setroute.infrastructure.ISettingRepository
import at.team30.setroute.models.*
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RouteListViewModel @Inject constructor(
    private val routesRepository : IRoutesRepository,
    private val settingsRepository : ISettingRepository,
    private val filteringRepository: IFilterRepository
) : ViewModel() {

    var language : String = Language.ENGLISH.code

    private val routesList: MutableLiveData<List<Route>> = MutableLiveData()

    init {
        val result = routesRepository.getRoutes()
        routesList.value = result
    }

    fun getRoutes(): LiveData<List<Route>> {
        routesList.postValue(getSortedRoutes(getFilteredRoutes())) // combination of sorting and filtering sometimes fucks up
        return routesList                                          // but filtering and sorting on their own work as intended
    }

    fun getSortingOptions(): SortingOptions {
        return settingsRepository.getSortingOptions()
    }

    private fun routesComparator() : Comparator<Route> {
        return Comparator { r1 : Route, r2 : Route ->
            when(settingsRepository.getSortingOptions().field) {
                Field.TITLE -> r1.getLocalizedName(language).compareTo(r2.getLocalizedName(language))
                Field.DISTANCE -> r1.length.compareTo(r2.length)
                Field.DURATION -> r1.duration.compareTo(r2.duration)
            }
        }
    }

    private fun getSortedRoutes(filteredRoutes : List<Route>) : List<Route> {
        return when(settingsRepository.getSortingOptions().order) {
            Order.DESCENDING -> filteredRoutes.sortedWith(routesComparator()).reversed()
            Order.ASCENDING -> filteredRoutes.sortedWith(routesComparator())
        }
    }

    fun applySorting(order: Int, field: String) {
        settingsRepository.storeSortingOptions(SortingOptions.fromValues(order, field))

        routesList.postValue(getSortedRoutes(getFilteredRoutes()))
    }

    fun getFilteringOptions() : FilteringOptions {
        return filteringRepository.getFilteringOptions()
    }

    private fun getFilteredRoutes() : List<Route> {
        val options = filteringRepository.getFilteringOptions()

        return routesRepository.getRoutes().filter { route -> route.duration >= options.minDuration && route.duration <= options.maxDuration
                && route.length >= options.minDistance && route.length <= options.maxDistance && route.type.value in options.interests }
    }

    fun applyFiltering(interests: List<Int>, minDistance: Float, maxDistance: Float, minDuration: Float, maxDuration: Float) {
        filteringRepository.storeFilteringOptions(FilteringOptions.fromValues(interests, minDistance, maxDistance, minDuration, maxDuration))

        routesList.postValue(getSortedRoutes(getFilteredRoutes()))
    }

}

