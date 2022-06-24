package com.softwrengr.task.data.repository;

import com.google.gson.JsonObject
import com.softwrengr.task.data.api.ApiService
import com.softwrengr.task.data.datautils.DataState
import com.softwrengr.task.data.remote.message
import com.softwrengr.task.data.remote.onErrorSuspend
import com.softwrengr.task.data.remote.onExceptionSuspend
import com.softwrengr.task.data.remote.onSuccessSuspend
import com.softwrengr.task.models.ItemModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject


class MyRepositoryImpl @Inject constructor(
    private val apiService: ApiService) : MyRepository {

    val string = "issues?q=windows+label:bug+language:python+state:open&sort=created"


    override suspend fun fetchData(q:String,sort:String,order:String): Flow<DataState<ItemModel>> {

        return flow {
            apiService.fetchData(q,sort,order).apply {

                this.onSuccessSuspend {

                    data?.let {
                        emit(DataState.success(it))
                    }

                }.onErrorSuspend {

                   emit(DataState.error<ItemModel>(message()))

                }.onExceptionSuspend {

                    if(this.exception is IOException){
                      emit(DataState.error<ItemModel>("No Internet Connection"))
                    }
                    else{
                        emit(DataState.error<ItemModel>("Something went wrong"))
                    }

                }
            }
        }

    }


}
