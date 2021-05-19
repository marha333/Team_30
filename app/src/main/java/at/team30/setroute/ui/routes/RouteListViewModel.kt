package at.team30.setroute.ui.routes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import at.team30.setroute.infrastructure.IRoutesRepository
import at.team30.setroute.infrastructure.ISettingRepository
import at.team30.setroute.models.*
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RouteListViewModel @Inject constructor(
    private val routesRepository : IRoutesRepository,
    private val settingsRepository : ISettingRepository
) : ViewModel() {

    var language : String = Language.ENGLISH.code

    private val routesList: MutableLiveData<List<Route>> = MutableLiveData()

    init {
        val result = routesRepository.getRoutes()
        result
        routesList.value = result
    }

    fun getRoutes(): LiveData<List<Route>> {
        routesList.postValue(getSortedRoutes())
        return routesList
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

    private fun getSortedRoutes() : List<Route> {
        return when(settingsRepository.getSortingOptions().order) {
            Order.DESCENDING -> routesRepository.getRoutes().sortedWith(routesComparator()).reversed()
            Order.ASCENDING -> routesRepository.getRoutes().sortedWith(routesComparator())
        }
    }

    fun applySorting(order: Int, field: String) {
        settingsRepository.storeSortingOptions(SortingOptions.fromValues(order, field))

        routesList.postValue(getSortedRoutes())
    }

}


