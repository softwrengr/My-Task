package com.softwrengr.task.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.softwrengr.task.baseClasses.BaseViewModel
import com.softwrengr.task.data.datautils.DataState
import com.softwrengr.task.data.usecases.MyUsecases
import com.softwrengr.task.models.ItemsDataModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TableViewModel @Inject constructor(
    private val myUsecases: MyUsecases,
) : BaseViewModel() {

    private var _searchList = MutableLiveData<List<ItemsDataModel>>()
    var searchListLiveData: LiveData<List<ItemsDataModel>> = _searchList


    //we can pass any value as per our requirements
    fun fetchData() {
        viewModelScope.launch {

            myUsecases.invoke("open", "created", "asc").collect { dataState ->
                when (dataState) {
                    is DataState.Success -> {
                        _searchList.postValue(dataState.data.items)
                        Log.d("response", "See the response " + dataState.data.total_count)
                    }
                    is DataState.Error -> {

                    }
                }
            }
        }
    }

}