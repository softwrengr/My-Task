package com.softwrengr.task.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.softwrengr.task.models.ItemsDataModel

class ItemListViewModel() : ViewModel() {

    private var model: ItemsDataModel? = null

    var title = MutableLiveData<String>()
    var id = MutableLiveData<String>()
    var state = MutableLiveData<String>()


    fun getModel(): ItemsDataModel? {
        return model
    }

    fun setModel(model: ItemsDataModel?) {
        this.model = model
    }


    fun getTitleName(): MutableLiveData<String> {
        title.value = model?.title.toString()
        return title
    }

    fun getTitleNumber(): MutableLiveData<String> {
        title.value = model?.numbers.toString()
        return title
    }

    fun getID(): MutableLiveData<String> {
        id.value = model?.id.toString()
        return id
    }

    fun getStateValue(): MutableLiveData<String> {
        state.value = model?.state.toString()
        return state
    }

}