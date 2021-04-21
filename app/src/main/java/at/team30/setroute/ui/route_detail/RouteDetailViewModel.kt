package at.team30.setroute.ui.route_detail

import androidx.lifecycle.ViewModel
import at.team30.setroute.infrastructure.IRoutesRepository
import at.team30.setroute.models.Route
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RouteDetailViewModel @Inject constructor(
        private val routesRepository : IRoutesRepository
) : ViewModel() {
    fun getRoute(id: Int): Route? {
        return routesRepository.getRoutesById(id)
    }
}