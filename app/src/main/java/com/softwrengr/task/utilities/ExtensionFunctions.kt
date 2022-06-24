package com.softwrengr.task.utilities

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

object ExtensionFunctions {

    fun setupRecylerView(recyclerView: RecyclerView, context: Context): RecyclerView {
        recyclerView.setHasFixedSize(true)
        val gridLayoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = gridLayoutManager

        return recyclerView
    }

}