package at.team30.setroute.infrastructure

import at.team30.setroute.models.FilteringOptions

interface IFilterRepository {
    fun storeFilteringOptions(options : FilteringOptions)
    fun getFilteringOptions() : FilteringOptions
}