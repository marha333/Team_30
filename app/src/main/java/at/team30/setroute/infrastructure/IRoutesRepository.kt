package at.team30.setroute.infrastructure

import at.team30.setroute.models.Route

interface IRoutesRepository {
    fun getRoutes() : List<Route>
    fun getRoutesById(id: Int) : Route?
}