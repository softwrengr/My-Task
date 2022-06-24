package com.softwrengr.task.data.repository;

import com.google.gson.JsonObject
import com.softwrengr.task.data.datautils.DataState
import com.softwrengr.task.models.ItemModel

import kotlinx.coroutines.flow.Flow


interface MyRepository {

    suspend fun fetchData(q:String,sort:String,order:String): Flow<DataState<ItemModel>>

}
