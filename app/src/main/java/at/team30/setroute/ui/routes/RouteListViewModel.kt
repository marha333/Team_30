package at.team30.setroute.ui.routes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import at.team30.setroute.infrastructure.IRoutesRepository
import at.team30.setroute.models.Route
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RouteListViewModel @Inject constructor(
    private val routesRepository : IRoutesRepository
) : ViewModel() {

    private val routesList: MutableLiveData<List<Route>> = MutableLiveData()

    init {
        val result = routesRepository.getRoutes()
        routesList.value = result
    }

    fun getRoutes(): LiveData<List<Route>> {
        return routesList
    }
}


