package com.softwrengr.task.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.softwrengr.task.databinding.CustomListLayoutBinding
import com.softwrengr.task.models.ItemsDataModel
import com.softwrengr.task.viewmodels.ItemListViewModel

class MyAdapter(var viewModel: ItemListViewModel) : RecyclerView.Adapter<MyAdapter.ViewHolder>(), Filterable {

    var itemsList = mutableListOf<ItemsDataModel>()
    var filterList = mutableListOf<ItemsDataModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = CustomListLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        viewModel = ItemListViewModel()
        viewModel.setModel(filterList[position])
        holder.bind(viewModel)

    }

    override fun getItemCount() = filterList.size


    @SuppressLint("NotifyDataSetChanged")
    fun updateItems(list: List<ItemsDataModel>) {
       // itemsList.clear()
        itemsList.addAll(list)

        filterList = itemsList
        notifyDataSetChanged()

    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence): FilterResults {
                val charString = charSequence.toString()
                if (charString.isEmpty()) {
                    filterList = itemsList
                } else {
                    val filteredList: MutableList<ItemsDataModel> = ArrayList()
                    for (row in itemsList) {

                        if (row.id.toString().toLowerCase().contains(charString.toLowerCase()) ||
                            row.state.contains(charSequence)) {
                            filteredList.add(row)
                        }
                    }
                    filterList = filteredList
                }
                val filterResults = FilterResults()
                filterResults.values = filterList
                return filterResults
            }

            override fun publishResults(charSequence: CharSequence, filterResults: FilterResults) {
                filterList = filterResults.values as MutableList<ItemsDataModel>
                notifyDataSetChanged()
            }
        }
    }


    inner class ViewHolder(private val itemBinding: CustomListLayoutBinding) : RecyclerView.ViewHolder(itemBinding.root) {

        //val ivLogo: ImageView = itemView.findViewById(R.id.iv_marker)

        fun bind(viewModel: ItemListViewModel) {
            itemBinding.viewModel = viewModel

        }
    }
}