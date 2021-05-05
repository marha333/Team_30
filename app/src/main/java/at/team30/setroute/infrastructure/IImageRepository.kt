package at.team30.setroute.infrastructure

import android.graphics.Bitmap

interface IImageRepository {
    suspend fun getImage(route_id : Int) : Bitmap?

}