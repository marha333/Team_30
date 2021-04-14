package at.team30.setroute.ui.routes

import at.team30.setroute.infrastructure.IRoutesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RouteListViewModel @Inject constructor(
    private val routesRepository : IRoutesRepository
) {
    
}


