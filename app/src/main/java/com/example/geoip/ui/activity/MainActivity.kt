package com.example.geoip.ui.activity

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.geoip.data.network.NetState
import com.example.geoip.ui.adapter.MainRecyclerViewAdapter
import com.example.geoip.ui.layout.activity.MainActivityLayout
import com.example.geoip.ui.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val layout by lazy { MainActivityLayout(this).build() }
    private val viewModel by viewModels<MainViewModel>()
    private lateinit var recyclerViewAdapter: MainRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.mainContainer)
        setupUi()
        handleObservers()
        viewModel.updateInfo()
    }

    private fun setupUi() {
        recyclerViewAdapter = MainRecyclerViewAdapter()
        layout.recyclerView.apply {
            adapter = recyclerViewAdapter
        }
        layout.toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                0 -> {
                    viewModel.updateInfo()
                    true
                }
                else -> false
            }
        }
    }

    private fun handleObservers() {
        lifecycleScope.launchWhenStarted {
            viewModel.netState.collect {
                when (it) {
                    is NetState.Loading -> {
                        Log.e("Net", "Loading")
                        layout.setLoadingState()
                    }
                    is NetState.Error -> {
                        Log.e("Net", "Error")
                        layout.setErrorState(it.msg)
                    }
                    is NetState.Success -> {
                        Log.e("Net", "Success")
                        layout.setSuccessState()
                    }
                }
            }
        }
        lifecycleScope.launchWhenStarted {
            viewModel.geoIpInfo.collect {
                val recyclerViewList = viewModel.parseToGeoIpItemList(it)
                recyclerViewAdapter.submitList(recyclerViewList)
            }
        }
    }
}