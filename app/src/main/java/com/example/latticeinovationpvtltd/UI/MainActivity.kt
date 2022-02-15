package com.example.latticeinovationpvtltd.UI

import android.app.SearchManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.latticeinovationpvtltd.API.NetworkHelperClass
import com.example.latticeinovationpvtltd.DATA.models.Article
import com.example.latticeinovationpvtltd.R
import com.example.latticeinovationpvtltd.UI.Adapter.ResponseAdapter
import com.example.latticeinovationpvtltd.UI.ViewModel.ResponseViewModel
import com.example.latticeinovationpvtltd.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    var list = ArrayList<Article>()
    lateinit var responseAdapter : ResponseAdapter
    private lateinit var responseViewModel: ResponseViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        responseViewModel = ViewModelProvider(this)[ResponseViewModel::class.java]
        /**
         * starting the shimmer effect
         */
        binding.shimmerFrameLayout.startShimmerAnimation()
        setAdapter()
        responseViewModel.callAPI()

        /**
         * observing each data inside the list using live data
         */
        this.responseViewModel.liveData.observe(this) {
            it.let {
                when (it) {
                    is NetworkHelperClass.OnSuccess -> {
                        /**
                         * stopping shimmer effect, setting visibility gone for shimmer layout and setting the visibility
                         * of recycler view to show data
                         */
                        binding.apply {
                            shimmerFrameLayout.stopShimmerAnimation()
                            recyclerView.visibility = View.VISIBLE
                            shimmerFrameLayout.visibility = View.GONE
                        }
                        list = it.responseList as ArrayList<Article>
                        setAdapter()
                    }
                    else -> {
                        Toast.makeText(this@MainActivity, "Error", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

    }
    /**
     * setting all the data in recyclerview
     */
    private fun setAdapter() {
        responseAdapter = ResponseAdapter(this,list)
        val linearLayoutManager = LinearLayoutManager(this)
        binding.recyclerView.apply {
            adapter = responseAdapter
            layoutManager = linearLayoutManager
        }
    }
}