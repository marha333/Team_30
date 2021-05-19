package at.team30.setroute.infrastructure

import at.team30.setroute.models.SortingOptions

interface ISettingRepository {
    fun storeSortingOptions(options : SortingOptions)
    fun getSortingOptions() : SortingOptions
}