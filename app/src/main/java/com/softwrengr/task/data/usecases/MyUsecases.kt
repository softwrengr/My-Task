package com.softwrengr.task.data.usecases
import com.google.gson.JsonObject
import com.softwrengr.task.data.repository.MyRepository
import javax.inject.Inject


class MyUsecases @Inject constructor(private val repository: MyRepository) {

    suspend operator  fun invoke(q:String,sort:String,order:String) = repository.fetchData(q,sort,order)


}
