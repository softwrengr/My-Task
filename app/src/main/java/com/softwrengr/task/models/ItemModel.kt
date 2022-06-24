package com.softwrengr.task.models

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

@Parcelize
class ItemModel(
    @Expose val total_count: String,
    @Expose val incomplete_results: String,
    @Expose val items: List<ItemsDataModel>,
) :Parcelable

