package com.softwrengr.task.models

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ItemsDataModel(
    @Expose val id: Long,
    @Expose val url: String,
    @Expose val title: String,
    @Expose val numbers: Long,
    @Expose val state: String,
    @Expose val comments: String,
    @Expose val node_id: String
) : Parcelable
