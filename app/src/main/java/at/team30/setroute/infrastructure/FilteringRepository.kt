package at.team30.setroute.infrastructure

import at.team30.setroute.models.FilteringOptions

class FilteringRepository : IFilterRepository{
    private var filteringOptions : FilteringOptions = FilteringOptions()
    override fun storeFilteringOptions(options: FilteringOptions) {
        filteringOptions = options
    }

    override fun getFilteringOptions() = filteringOptions
}