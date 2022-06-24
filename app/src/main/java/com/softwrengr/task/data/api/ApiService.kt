package com.softwrengr.task.data.api;

import com.softwrengr.task.data.remote.ApiResponse
import com.softwrengr.task.models.ItemModel
import retrofit2.http.*

interface ApiService {

   //search/issues?q=windows+label:bug+language:python+state:open&sort=created&order=asc"

    @GET("search/issues")
    suspend fun fetchData(
        @Query("q")         q : String,
        @Query("sort")      sort : String,
        @Query("order")     order : String,
    ): ApiResponse<ItemModel>

    companion object {
        const val BASE_API_URL = "https://api.github.com/"
    }
}
