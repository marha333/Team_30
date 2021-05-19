package at.team30.setroute.infrastructure

import at.team30.setroute.models.SortingOptions

class SettingRepository : ISettingRepository{
    private var sortingOptions : SortingOptions = SortingOptions()
    override fun storeSortingOptions(options: SortingOptions) {
        sortingOptions = options
    }

    override fun getSortingOptions() = sortingOptions
}