package com.softwrengr.task.ui.activities


import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.softwrengr.task.viewmodels.ItemListViewModel
import com.softwrengr.task.adapters.MyAdapter
import com.softwrengr.task.baseClasses.BaseActivity
import com.softwrengr.task.databinding.ActivityTableBinding
import com.softwrengr.task.utilities.ExtensionFunctions.setupRecyclerView
import com.softwrengr.task.viewmodels.TableViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TableActivity : BaseActivity<ActivityTableBinding>() {

    private val viewModel: TableViewModel by viewModels()

    private lateinit var itemViewModel: ItemListViewModel
    private lateinit var adapter: MyAdapter


    override fun getViewBinding(): ActivityTableBinding {
        return ActivityTableBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        itemViewModel = ItemListViewModel()
        super.onCreate(savedInstanceState)

        val recyclerView = setupRecyclerView(binding.rvItems, this)
        adapter = MyAdapter(itemViewModel)
        adapter.stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
        recyclerView.adapter = adapter

        progressHUD?.show()
        viewModel.fetchData()
        initObservation()


        binding.search.addTextChangedListener {
            if (!it.isNullOrEmpty()) {
                adapter.filter.filter(it)
            }else{
                adapter.filter.filter("")
            }
        }


    }

    override fun setUpView() {

    }

    private fun initObservation() {

        this.lifecycleScope.launch {
            viewModel.searchListLiveData.observe(this@TableActivity) {
                progressHUD?.dismiss()
                adapter.updateItems(it)
            }
        }

    }

}